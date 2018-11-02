package top.koguma.gymclub.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import top.koguma.gymclub.R;
import top.koguma.gymclub.adapter.BottomNavigationViewAdapter;
import top.koguma.gymclub.fragment.CoursesFragment;
import top.koguma.gymclub.fragment.DashboardFragment;
import top.koguma.gymclub.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity
    implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new DashboardFragment());
        mFragments.add(new CoursesFragment());
        mFragments.add(new ProfileFragment());

        BottomNavigationViewAdapter adapter = new BottomNavigationViewAdapter(
            getSupportFragmentManager(), mFragments);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }

    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
                return true;
        }
        return false;
    }
}
