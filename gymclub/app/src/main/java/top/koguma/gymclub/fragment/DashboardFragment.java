package top.koguma.gymclub.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import top.koguma.gymclub.R;
import top.koguma.gymclub.model.Category;
import top.koguma.gymclub.utils.Toaster;

public class DashboardFragment extends GymClubBaseFragment {

    private List<Fragment> mFragments;
    private List<Category> mCategories = new ArrayList<>();

    @Nullable @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, null);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }

    @Override protected void onSetupToolbar(android.support.v7.widget.Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        toolbar.setTitle(R.string.main_dashboard);
    }

    private void setupViews(final View view) {
        showProgressLoading("");
        BmobQuery<Category> categoryQuery = new BmobQuery<>();
        categoryQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        categoryQuery.findObjects(new FindListener<Category>() {
            @Override public void done(List<Category> list, BmobException e) {
                dismissProgressLoading();
                if (e == null) {
                    mCategories.clear();
                    mCategories.addAll(list);
                    mFragments = new ArrayList<>();
                    for (int i = 0; i < mCategories.size(); i++) {
                        mFragments.add(new ListFragment());
                    }
                    DashboardAdapter adapter = new DashboardAdapter(getChildFragmentManager());
                    ViewPager viewPager = view.findViewById(R.id.view_pager);
                    viewPager.setAdapter(adapter);
                    viewPager.setOffscreenPageLimit(3);

                    TabLayout tabLayout = view.findViewById(R.id.tab_layout);
                    tabLayout.setupWithViewPager(viewPager);
                } else {
                    Toaster.showToast(e.getErrorCode());
                }
            }
        });

    }

    class DashboardAdapter extends FragmentStatePagerAdapter {

        public DashboardAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Nullable @Override public CharSequence getPageTitle(int position) {
            return mCategories.get(position).name;
        }

        @Override public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }
    }
}
