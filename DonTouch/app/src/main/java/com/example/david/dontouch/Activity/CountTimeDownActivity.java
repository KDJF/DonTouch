package com.example.david.dontouch.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.david.dontouch.R;
import com.example.david.dontouch.View.CircleSeekBar;

public class CountTimeDownActivity extends AppCompatActivity {
    private int time;
    private final boolean DEBUG = true;
    private final String TAG = "CountTimeDownActivity";
    private Button button;
    private TextView tryit;
    private TextView success;
    private TextView time_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_time_down);

        final CircleSeekBar circleSeekBar = (CircleSeekBar) findViewById(R.id.circle_seekbar);
        circleSeekBar.setProgress(0);
        circleSeekBar.setOnSeekBarChangeListener(new CircleSeekBarOnChangeListener());

        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(CountTimeDownActivity.this, CountDownActivity.class);
                time = circleSeekBar.getProgress();
                Log.i(TAG, Integer.toString(circleSeekBar.getProgress()));
                intent.putExtra("time", time * 60000);
                startActivity(intent);
                finish();
                int try_count = Integer.parseInt(tryit.getText().toString());
                //tryit.setText(Integer.toString(try_count+1));
                int suc_count = Integer.parseInt(success.getText().toString());
                //success.setText(Integer.toString(suc_count+1));
                //time_count.setText(time);
            }
        });
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
        button = (Button) findViewById(R.id.bAddOne);
        tryit = (TextView) findViewById(R.id.textView3);
        success = (TextView) findViewById(R.id.textView4);
        time_count = (TextView) findViewById(R.id.textView5);
    }
}
