package top.koguma.gymclub.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import com.laputapp.rx.RxBus;
import top.koguma.gymclub.R;
import top.koguma.gymclub.event.ProfileUpdateEvent;
import top.koguma.gymclub.model.User;
import top.koguma.gymclub.utils.Toaster;
import top.koguma.gymclub.utils.UserUtils;

public class RegisterActivity extends GymClubBaseActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private EditText mEtUserName;
    private EditText mEtEmailAddress;
    private EditText mEtUserPassword;
    private EditText mEtUserPasswordConfirm;
    private Button mBtnRegister;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);
        initView();
    }

    private void initView(){
        mEtUserName = (EditText) findViewById(R.id.register_et_user_name);
        mEtEmailAddress = (EditText) findViewById(R.id.register_et_email);
        mEtUserPassword = (EditText) findViewById(R.id.register_et_password);
        mEtUserPasswordConfirm = (EditText) findViewById(R.id.register_et_confirm_password);
        mBtnRegister = (Button) findViewById(R.id.register_btn_register);
        mBtnRegister.setOnClickListener(this);
    }


    @Override protected void onSetupToolbar(Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onClick(View v) {
        String userName = mEtUserName.getText().toString();
        String emailAddress = mEtEmailAddress.getText().toString();
        String userPassword = mEtUserPasswordConfirm.getText().toString();
        String userPasswordConfirm = mEtUserPasswordConfirm.getText().toString();
        if(TextUtils.isEmpty(userName)){
            Toaster.showToast("plz input your user name");
            return;
        }else if(TextUtils.isEmpty(emailAddress)){
            Toaster.showToast("plz input your email address");
            return;
        } else if (TextUtils.isEmpty(userPassword)) {
            Toaster.showToast("plz input your password");
            return;
        }else if (TextUtils.isEmpty(userPasswordConfirm)){
            Toaster.showToast("plz input your password confirm");
            return;
        }else if (!userPassword.equals(userPasswordConfirm)){
            Toaster.showToast("the two passwords you typed do not match");
            return;
        }
        User user = new User();
        user.setUsername(userName);
        user.setEmail(emailAddress);
        user.setPassword(userPassword);
        user.signUp(new SaveListener<User>() {
            @Override public void done(User user, BmobException e) {
                if( e == null){
                    //注册即登陆
                    Toaster.showToast("register success");
                    UserUtils.login(user);
                    finish();
                }else {
                    Log.e(TAG,e.getMessage());
                }
            }
        });
    }
}
