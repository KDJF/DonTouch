package com.example.david.dontouch.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Created by david on 2017/6/1.
 */

public class TimerDB {
    public static final String DB_NAME = "sqlite_db";
    public static final int VERSION = 3;
    private static TimerDB timerDB;
    private SQLiteDatabase db;
    private static String TAG = "TimerDB";

    private TimerDB(Context context) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static TimerDB getInstance(Context context) {
        if (timerDB == null) {
            timerDB = new TimerDB(context);
        }
        return timerDB;
    }

    public int saveTime(int isOpen) {
        try {
            String sql = "INSERT INTO Time(timer, isOpen) VALUES(CURRENT_TIMESTAMP, " + isOpen + ")";
            db.execSQL(sql);
        } catch (Exception e) {
            Log.d("错误", e.getMessage().toString());
            return 0;
        }
        return 1;
    }

    public List<String> loadTime(String isOpen) {
        Date now = new Date(System.currentTimeMillis());
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(now);
        //可以根据需要设置时区
        //cal.setTimeZone(TimeZone.getDefault());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //毫秒可根据系统需要清除或不清除
        cal.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM Time WHERE " + "timer>=? and isOpen=?";
        Cursor cursor = db.rawQuery(sql, new String[]{dateFormat.format(cal.getTime()).toString(), isOpen});
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex("timer")));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
