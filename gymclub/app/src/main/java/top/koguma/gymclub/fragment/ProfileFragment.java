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
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.laputapp.rx.RxBus;
import com.laputapp.ui.BaseFragment;
import io.reactivex.functions.Consumer;
import top.koguma.gymclub.Navigator;
import top.koguma.gymclub.R;
import top.koguma.gymclub.event.ProfileUpdateEvent;
import top.koguma.gymclub.model.User;
import top.koguma.gymclub.utils.Toaster;
import top.koguma.gymclub.utils.UserUtils;

public class ProfileFragment extends GymClubBaseFragment {

    SimpleDraweeView mAvatar;
    TextView mTxtName;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerSubscription(
            RxBus.getDefault().toFlowable(ProfileUpdateEvent.class)
                .doOnNext(new Consumer<ProfileUpdateEvent>() {
                    @Override public void accept(ProfileUpdateEvent event)
                        throws Exception {
                        if (mAvatar != null) {
                            mAvatar.setImageURI(event.getAvatarUrl());
                        }
                        if (mTxtName != null) {
                            mTxtName.setText(event.getUserName());
                        }
                    }
                })
                .subscribe()
        );
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, null);
        mAvatar = v.findViewById(R.id.avatar);
        mTxtName = v.findViewById(R.id.txt_name);
        updateView();
        return v;
    }

    public void updateView() {
        User user = UserUtils.getCurrentUser();
        if(user == null) return;
        if (mAvatar != null) {
            mAvatar.setImageURI(user.getAvatarUrl());
        }
        if (mTxtName != null) {
            mTxtName.setText(user.getUsername());
        }
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
        switch (item.getItemId()) {
            case R.id.profile_register:
                Navigator.startRegisterActivity(getContext());
                break;
            case R.id.profile_Login:
                Navigator.startLoginActivity(getContext());
                break;
            case R.id.profile_logout:
                UserUtils.logout();
                Toaster.showToast("Logout!");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
