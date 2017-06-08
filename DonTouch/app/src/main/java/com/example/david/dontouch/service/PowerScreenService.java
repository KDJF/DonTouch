package com.example.david.dontouch.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.example.david.dontouch.Dao.TimerDB;

public class PowerScreenService extends Service {

    public final static String TAG = "PowerScreenService";
    private ScreenBroadcastReceiver mScreenBroadcastReceiver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startScreenBroadcastReceiver();
        return START_STICKY;
    }

    class ScreenBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String intentActionString = intent.getAction();
                Log.i(TAG, "-------------- action = " + intentActionString);
                if (intentActionString.equals(Intent.ACTION_SCREEN_ON)) {
                    Log.i(TAG, "-------------- on !");
                } else if (intentActionString.equals(Intent.ACTION_SCREEN_OFF)) {
                    Log.i(TAG, "-------------- off !");
                    int result = TimerDB.getInstance(getApplication()).saveTime(0);
//                    TimeUtil.getInstance().closeTimeList = TimerDB.getInstance(getApplication()).loadTime("0");
//                    for (int i = 0; i < TimeUtil.getInstance().closeTimeList.size(); i++) {
//                        Log.i("close", "Screen is off: " + TimeUtil.getInstance().closeTimeList.get(i));
//                    }
                    Log.i("unlocktimes",""+TimerDB.getInstance(getApplication()).unlocktimes(1));
                    Log.i("ontimes",""+TimerDB.getInstance(getApplication()).loadTime("0").size()+"");
                } else if (intentActionString.equals(Intent.ACTION_USER_PRESENT)) {
                    Log.i(TAG, "-------------- unlock !");
                    int result = TimerDB.getInstance(getApplication()).saveTime(1);
//                    TimeUtil.getInstance().openTimeList = TimerDB.getInstance(getApplication()).loadTime("1");
//                    Log.i(TAG, "Screen is on: " + TimeUtil.getUseTime());
//                    for (int i = 0; i < TimeUtil.getInstance().openTimeList.size(); i++) {
//                        Log.i("unlock", "Screen is on: " + TimeUtil.getInstance().openTimeList.get(i));
//                    }
                    Log.i("unlocktimes",""+TimerDB.getInstance(getApplication()).unlocktimes(1));
                    Log.i("ontimes",""+TimerDB.getInstance(getApplication()).loadTime("0").size());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startScreenBroadcastReceiver() {
        try {
            IntentFilter filter = new IntentFilter();
            if (mScreenBroadcastReceiver == null) {
                mScreenBroadcastReceiver = new ScreenBroadcastReceiver();
            }

            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_USER_PRESENT);
            registerReceiver(mScreenBroadcastReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
