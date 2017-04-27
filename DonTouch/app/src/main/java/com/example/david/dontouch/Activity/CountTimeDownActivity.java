package com.example.david.dontouch.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.david.dontouch.R;
import com.example.david.dontouch.Util.CommonUtil;

import java.sql.Time;
import java.util.Calendar;

public class CountTimeDownActivity extends AppCompatActivity {
    private Button button;
    private Activity activity;
    private TimePicker timePicker;
    private int hour;
    private int min;
    private Calendar calendar;
    private int hour_now;
    private int min_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        //CommonUtil.setNoTitleBar(activity);
        CommonUtil.setFullScreen(activity);
        setContentView(R.layout.activity_count_time_down);

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

        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(CountTimeDownActivity.this, CountDownActivity.class);
                Log.i("timeaaaaa", "" + (min-min_now));
                intent.putExtra("time", (hour-hour_now)*3600 + (min-min_now)*60000);
                startActivity(intent);
                finish();
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                min = minute;

            }
        });
    }

    public void init() {
        long time = System.currentTimeMillis();
        button = (Button) findViewById(R.id.count_down_btn);
        timePicker = (TimePicker) findViewById(R.id.time_picker);
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        hour_now = calendar.get(Calendar.HOUR);
        min_now = calendar.get(Calendar.MINUTE);
    }
}
