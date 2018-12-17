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

public class LoginActivity extends GymClubBaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    EditText mEtUserName;
    EditText mEtPassWord;
    Button mBtnLogin;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mEtUserName = (EditText) findViewById(R.id.login_et_user_name);
        mEtPassWord = (EditText) findViewById(R.id.login_et_password);
        mBtnLogin = (Button) findViewById(R.id.login_btn_login);
        mBtnLogin.setOnClickListener(this);
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
        String userPassword = mEtPassWord.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            Toaster.showToast("plz input your user name");
            return;
        } else if (TextUtils.isEmpty(userPassword)) {
            Toaster.showToast("plz input your password");
            return;
        }
        User user = new User();
        user.setUsername(userName);
        user.setPassword(userPassword);
        user.login(new SaveListener<User>() {
            @Override public void done(User user, BmobException e) {
                if (e == null) {
                    Toaster.showToast("login success");
                    UserUtils.login(user);
                    finish();
                } else {
                    Toaster.showToast(e.getMessage());
                }
            }
        });
    }
}
