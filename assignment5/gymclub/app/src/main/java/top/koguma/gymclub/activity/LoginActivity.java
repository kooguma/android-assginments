package top.koguma.gymclub.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import com.laputapp.rx.RxBus;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;
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

    ImageView mImgWX;
    ImageView mImgQQ;
    ImageView mImgSina;

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
        AuthClickListener listener = new AuthClickListener();
        mImgWX = (ImageView) findViewById(R.id.img_wx);
        mImgQQ = (ImageView) findViewById(R.id.img_qq);
        mImgSina = (ImageView) findViewById(R.id.img_sina);
        mImgWX.setOnClickListener(listener);
        mImgQQ.setOnClickListener(listener);
        mImgSina.setOnClickListener(listener);
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

    class AuthClickListener implements View.OnClickListener, UMAuthListener {

        @Override public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_wx:
                    UMShareAPI.get(LoginActivity.this)
                        .doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN, this);
                    break;
                case R.id.img_qq:
                    UMShareAPI.get(LoginActivity.this)
                        .doOauthVerify(LoginActivity.this, SHARE_MEDIA.QQ, this);
                    break;
                case R.id.img_sina:
                    UMShareAPI.get(LoginActivity.this)
                        .doOauthVerify(LoginActivity.this, SHARE_MEDIA.SINA, this);
                    break;
                default:
                    break;
            }
        }

        @Override public void onStart(SHARE_MEDIA share_media) {

        }

        @Override public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            finish();
        }

        @Override public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Log.e("TAG", "error = " + throwable.getMessage());

        }

        @Override public void onCancel(SHARE_MEDIA share_media, int i) {

        }
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
