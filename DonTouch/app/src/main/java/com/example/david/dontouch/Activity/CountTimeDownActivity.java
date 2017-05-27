package com.example.david.dontouch.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.david.dontouch.R;
import com.example.david.dontouch.View.CircleSeekBar;

public class CountTimeDownActivity extends AppCompatActivity {
    private Activity activity;
    private int hour;
    private int min;
    private int hour_now;
    private int min_now;
    private final boolean DEBUG = true;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity = this;
        //CommonUtil.setNoTitleBar(activity);
        //CommonUtil.setFullScreen(activity);
        setContentView(R.layout.activity_count_time_down);

        CircleSeekBar circleSeekBar = (CircleSeekBar) findViewById(R.id.circle_seekbar);
        circleSeekBar.setProgress(100);
        circleSeekBar.setOnSeekBarChangeListener(new CircleSeekBarOnChangeListener());

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
        //透明状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        init();
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(CountTimeDownActivity.this, CountDownActivity.class);
                Log.i("timeaaaaa", "" + (min-min_now));
                intent.putExtra("time", (hour-hour_now)*3600 + (min-min_now)*60000);
                startActivity(intent);
                finish();
            }
        });*/

        /*timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                min = minute;

            }
        });*/
    }

    private class CircleSeekBarOnChangeListener implements CircleSeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(int progress) {
            if(DEBUG) Log.d(TAG, "onProgressChanged progress = " + progress);
        }

        @Override
        public void onStartTrackingTouch() {
            if(DEBUG) Log.d(TAG, "onStartTrackingTouch");
        }

        @Override
        public void onStopTrackingTouch() {
            if(DEBUG) Log.d(TAG, "onStopTrackingTouch");
        }

    }

    public void init() {
        long time = System.currentTimeMillis();
        //button = (Button) findViewById(R.id.count_down_btn);
        //timePicker = (TimePicker) findViewById(R.id.time_picker);
        //calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(time);
        //hour_now = calendar.get(Calendar.HOUR);
        //min_now = calendar.get(Calendar.MINUTE);
    }
}
