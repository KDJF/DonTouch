package com.example.david.dontouch.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.david.dontouch.Adapter.ViewPagerAdapter;
import com.example.david.dontouch.Fragment.AssessFragment;
import com.example.david.dontouch.Fragment.HomeFragment;
import com.example.david.dontouch.Fragment.JournalFragment;
import com.example.david.dontouch.Fragment.NotifFragment;
import com.example.david.dontouch.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    HomeFragment homeFragment;
    JournalFragment journalFragment;
    AssessFragment assessFragment;
    NotifFragment notifFragment;
    MenuItem prevMenuItem;
    BottomNavigationView navigation;

    private ViewPager.OnPageChangeListener mOnViewPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            invalidateOptionsMenu();
            if (prevMenuItem != null) {
                prevMenuItem.setChecked(false);
            }
            else
            {
                navigation.getMenu().getItem(0).setChecked(false);
            }
            Log.d("page", "onPageSelected: "+position);
            navigation.getMenu().getItem(position).setChecked(true);
            prevMenuItem = navigation.getMenu().getItem(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_journal:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_assessment:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(mOnViewPageChangeListener);
        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        journalFragment = new JournalFragment();
        assessFragment = new AssessFragment();
        notifFragment = new NotifFragment();
        adapter.add(homeFragment);
        adapter.add(journalFragment);
        adapter.add(assessFragment);
        adapter.add(notifFragment);
        viewPager.setAdapter(adapter);
    }

}
