package top.koguma.gymclub.fragment;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.laputapp.ui.BaseFragment;
import top.koguma.gymclub.R;
import top.koguma.gymclub.activity.GymClubBaseActivity;

public class GymClubBaseFragment extends BaseFragment {

    private Toolbar mToolbar;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            ((GymClubBaseActivity) getActivity()).setSupportActionBar(mToolbar);
            onSetupToolbar(mToolbar);
        }
    }

    protected void onSetupToolbar(Toolbar toolbar) {

    }

    protected void setCenterTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle("");
            TextView view = (TextView) mToolbar.findViewById(R.id.txt_toolbar_title);
            if (view != null) {
                view.setText(title);
            }
        }
    }

    protected void setCenterTitle(@StringRes int resId) {
        setCenterTitle(getString(resId));
    }
}
