package com.example.david.dontouch.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.david.dontouch.Adapter.ViewPagerAdapter;
import com.example.david.dontouch.Fragment.AssessFragment;
import com.example.david.dontouch.Fragment.HomeFragment;
import com.example.david.dontouch.Fragment.JournalFragment;
import com.example.david.dontouch.Fragment.NotifFragment;
import com.example.david.dontouch.R;
import com.example.david.dontouch.Util.BottomNavigationViewHelper;
import com.example.david.dontouch.service.PowerScreenService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    ViewPager viewPager;
    HomeFragment homeFragment;
    JournalFragment journalFragment;
    AssessFragment assessFragment;
    NotifFragment notifFragment;
    MenuItem prevMenuItem;
    BottomNavigationView navigation;
//    private String TAG = "ScreenObserverActivity";
//    private ScreenObserver mScreenObserver;
//    private int times = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(mOnViewPageChangeListener);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        setupViewPager(viewPager);
//        mScreenObserver = new ScreenObserver(this);
//        mScreenObserver.requestScreenStateUpdate(new ScreenObserver.ScreenStateListener() {
//            @Override
//            public void onScreenOn() {
//                doSomethingOnScreenOn();
//            }
//
//            @Override
//            public void onScreenOff() {
//                doSomethingOnScreenOff();
//            }
//        });

        //service监控屏幕解锁与锁
        Intent serviceOne = new Intent();
        serviceOne.setClass(MainActivity.this, PowerScreenService.class);
        startService(serviceOne);
    }

//    private void doSomethingOnScreenOn() {
//        int result = TimerDB.getInstance(getApplication()).saveTime(1);
//        TimeUtil.getInstance().openTimeList = TimerDB.getInstance(getApplication()).loadTime("1");
//        Log.i(TAG, "Screen is on: " + TimeUtil.getUseTime());
//        for (int i = 0; i < TimeUtil.getInstance().openTimeList.size(); i++) {
//            Log.i(TAG, "Screen is on: " + TimeUtil.getInstance().openTimeList.get(i));
//        }
//    }

//    private void doSomethingOnScreenOff() {
//        int result = TimerDB.getInstance(getApplication()).saveTime(0);
//        TimeUtil.getInstance().closeTimeList = TimerDB.getInstance(getApplication()).loadTime("0");
//        SharedPreferences mySharedPreferences= getSharedPreferences("locktimes",
//                Activity.MODE_PRIVATE);
//        SharedPreferences.Editor editor = mySharedPreferences.edit();
//        times = times + 1;
//        editor.putInt("times", times);
//        editor.commit();
//        for (int i = 0; i < TimeUtil.getInstance().closeTimeList.size(); i++) {
//            Log.i(TAG, "Screen is off: " + TimeUtil.getInstance().closeTimeList.get(i));
//        }
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止监听screen状态
//        mScreenObserver.stopScreenStateUpdate();
    }


    private ViewPager.OnPageChangeListener mOnViewPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            invalidateOptionsMenu();
            if (prevMenuItem != null) {
                prevMenuItem.setChecked(false);
            } else {
                navigation.getMenu().getItem(0).setChecked(false);
            }
            Log.d("page", "onPageSelected: " + position);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, WhiteListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {

            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ContactUsActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_help) {

            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ContactUsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
