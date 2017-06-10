package com.example.david.dontouch.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.david.dontouch.Dao.TimerDB;
import com.example.david.dontouch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/6/7.
 */

public class ContactUsActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        Button b1= (Button) findViewById(R.id.deleteTime);
        Button b2= (Button) findViewById(R.id.temp);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //一定要删除数据
                TimerDB.getInstance(getApplication()).clearTime();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list=new ArrayList<String>();
                list=TimerDB.getInstance(getApplication()).loadTime("0",1);
                for (String str:list){
                    Log.i("off",str);
                }
                list=TimerDB.getInstance(getApplication()).loadTime("1",1);
                for (String str:list){
                    Log.i("on",str);
                }
                Log.i("TOTAL_TIME",TimerDB.getInstance(getApplication()).totaltime(1)+"");
            }
        });

    }
}
