package top.koguma.gymclub.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class BottomNavigationViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public BottomNavigationViewAdapter(FragmentManager fm) {
        super(fm);
    }

    public BottomNavigationViewAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override public Fragment getItem(int position) {
        return mFragments == null ? null : mFragments.get(position);
    }

    @Override public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

}
