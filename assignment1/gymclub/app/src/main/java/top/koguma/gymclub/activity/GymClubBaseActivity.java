package top.koguma.gymclub.activity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;
import com.laputapp.ui.BaseActivity;
import top.koguma.gymclub.R;

public class GymClubBaseActivity extends BaseActivity {
    private boolean mHasParent;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        try {
            mHasParent = NavUtils.getParentActivityName(this, getComponentName()) != null;
        } catch (PackageManager.NameNotFoundException e) {
            //do nothing
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            if (mHasParent) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            onSetupToolbar(mToolbar);
        }
    }

    protected void onSetupToolbar(Toolbar toolbar) {
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mHasParent) {
                    onBackPressed();
                    return true;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
