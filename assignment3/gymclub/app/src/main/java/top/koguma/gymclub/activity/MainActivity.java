package top.koguma.gymclub.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.MenuItem;
import com.laputapp.ui.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import top.koguma.gymclub.R;
import top.koguma.gymclub.fragment.CoursesFragment;
import top.koguma.gymclub.fragment.DashboardFragment;
import top.koguma.gymclub.fragment.ProfileFragment;

public class MainActivity extends GymClubBaseActivity
    implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_DASHBOARD = "dashboard";
    private static final String TAG_COURSES = "courses";
    private static final String TAG_PROFILE = "profile";

    private List<Pair<Fragment, String>> mFragments;
    private Fragment mCurFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSwipeBackEnable(false);
        setupViews();
    }

    private void setupViews() {

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        mFragments = new ArrayList<>();
        mFragments.add(new Pair<Fragment, String>(new DashboardFragment(), TAG_DASHBOARD));
        mFragments.add(new Pair<Fragment, String>(new CoursesFragment(), TAG_COURSES));
        mFragments.add(new Pair<Fragment, String>(new ProfileFragment(), TAG_PROFILE));
        setCurrentFragment(0);
    }

    private void setCurrentFragment(int position) {
        if (position < 0 || position > mFragments.size()) return;
        Fragment nextFragment = getSupportFragmentManager().findFragmentByTag(
            mFragments.get(position).second);
        if (mCurFragment != null && mCurFragment == nextFragment) return;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mCurFragment != null) ft.detach(mCurFragment);
        mCurFragment = nextFragment;
        if (mCurFragment != null) {
            ft.attach(mCurFragment);
        } else {
            mCurFragment = mFragments.get(position).first;
            ft.add(R.id.frags_container, mCurFragment, mFragments.get(position).second);
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_dashboard:
                setCurrentFragment(0);
                return true;
            case R.id.navigation_courses:
                setCurrentFragment(1);
                return true;
            case R.id.navigation_profile:
                setCurrentFragment(2);
                return true;
        }
        return false;
    }

}
