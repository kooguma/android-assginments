package top.koguma.gymclub.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.laputapp.ui.BaseFragment;
import top.koguma.gymclub.Navigator;
import top.koguma.gymclub.R;
import top.koguma.gymclub.utils.Toaster;

public class ProfileFragment extends GymClubBaseFragment {

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, null);
    }

    @Override protected void onSetupToolbar(Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        setHasOptionsMenu(true);
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile, menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile_register:
                Navigator.startRegisterActivity(getContext());
                break;
            case R.id.profile_Login:
                Navigator.startLoginActivity(getContext());
                break;
            case R.id.profile_logout:
                Toaster.showToast("Logout!");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
