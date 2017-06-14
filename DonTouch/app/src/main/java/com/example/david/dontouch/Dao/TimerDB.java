package com.example.david.dontouch.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
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

    public int clearTime() {
        try {
            String sql = "delete from Time";
            db.execSQL(sql);
        } catch (Exception e) {
            Log.d("错误", e.getMessage().toString());
            return 0;
        }
        return 1;
    }

    public List<String> loadTime(String isOpen, int type) {
        Date now = new Date(System.currentTimeMillis());
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (type == 1) {
            //可以根据需要设置时区
            //cal.setTimeZone(TimeZone.getDefault());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            //毫秒可根据系统需要清除或不清除
            cal.set(Calendar.MILLISECOND, 0);
//            Log.i("day", dateFormat.format(cal.getTime()).toString());
        } else if (type == 2) {
            cal.add(Calendar.DAY_OF_MONTH, -6);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
//            Log.i("day", dateFormat.format(cal.getTime()).toString());
        } else if (type == 3) {
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
//            Log.i("day", dateFormat.format(cal.getTime()).toString());
        }

        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM Time WHERE " + "timer>=? and isOpen=?";
        Cursor cursor = db.rawQuery(sql, new String[]{dateFormat.format(cal.getTime()).toString(), isOpen});
        if (cursor.moveToFirst()) {
            do {
                String temp = cursor.getString(cursor.getColumnIndex("timer"));
                list.add(temp);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //type 1 日
    //type 2 周
    //type 3 月
    public int unlocktimes(int type) {

        Date now = new Date(System.currentTimeMillis());
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (type == 1) {
            //可以根据需要设置时区
            //cal.setTimeZone(TimeZone.getDefault());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            //毫秒可根据系统需要清除或不清除
            cal.set(Calendar.MILLISECOND, 0);
//            Log.i("day", dateFormat.format(cal.getTime()).toString());
        } else if (type == 2) {
            cal.add(Calendar.DAY_OF_MONTH, -6);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
//            Log.i("day", dateFormat.format(cal.getTime()).toString());
        } else if (type == 3) {
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
//            Log.i("day", dateFormat.format(cal.getTime()).toString());
        }
        String sql = "SELECT count(*) FROM Time WHERE " + "timer>=? and isOpen=1";
        Cursor cursor = db.rawQuery(sql, new String[]{dateFormat.format(cal.getTime()).toString()});
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    //type 1 2 3
    public long totaltime(int type) {
        long total_off = 0L;
        long total_on = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_off = null;
        Date date_on = null;
        List<String> list_time_on = new ArrayList<String>();
        List<String> list_time_off = new ArrayList<String>();
        try {
            list_time_off = loadTime("0", type);
            list_time_on = loadTime("1", type);
            if (list_time_off.size() > 2 && list_time_on.size() > 2) {
                list_time_off.remove(0);//删除第一个的锁频时间
                int size = list_time_on.size();
                list_time_on.remove(size - 1);

                for (String datestr : list_time_off) {
                    date_off = sdf.parse(datestr);
                    Log.i("date_off.getTime()", date_off.getTime() + "");
                    total_off = total_off + date_off.getTime();
                }
                for (String datestr : list_time_on) {
                    date_on = sdf.parse(datestr);
                    Log.i("date_on.getTime()", date_on.getTime() + "");
                    total_on = total_on + date_on.getTime();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return total_off - total_on + 1L;
    }

    public long getinterval() {
        long total_off = 0L;
        long total_on = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_off = null;
        Date date_on = null;
        List<String> list_time_on = new ArrayList<String>();
        List<String> list_time_off = new ArrayList<String>();

        try {
            list_time_on = loadTime("1", 1);
            int size = list_time_on.size();
            if(size>0) {
                date_on = sdf.parse(list_time_on.get(size - 1));
                total_on = date_on.getTime();
            }else{
                total_on=0L;
            }
            list_time_off = loadTime("0", 1);
            size = list_time_off.size();
            if(size>0){
                date_off = sdf.parse(list_time_off.get(size - 1));
                total_off = date_off.getTime();
            }else {
                total_off=0L;
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.i("interval", total_on - total_off + 1L + "");
        return total_on - total_off + 1L;
    }

    public long getlongest(int type) {
        long longest = 0L;
        long off = 0L;
        long on = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_off = null;
        Date date_on = null;
        List<String> list_time_on = new ArrayList<String>();
        List<String> list_time_off = new ArrayList<String>();
        try {
            list_time_off = loadTime("0", type);
            list_time_on = loadTime("1", type);
            if (list_time_off.size() > 2 && list_time_on.size() > 2 && list_time_off.size() == list_time_on.size()) {

                int size = list_time_off.size();
                for (int i = 1; i < size; i++) {
                    date_off = sdf.parse(list_time_off.get(i));
                    date_on = sdf.parse(list_time_on.get(i - 1));
                    off = date_off.getTime();
                    on = date_on.getTime();
                    if ((off - on) > longest) {
                        longest = off - on;
                    }
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return longest + 1L;
    }

    //type 1 日 return 24
    //type 2 周 return 7
    public  int []gettimes(int type) {
        List<String>list=loadTime("1",type);
        int []arrayDay=new int[24];
        int []arrayWeek=new int[7];
        for(int i=0;i<24;i++){
            arrayDay[i]=0;
        }
        for(int i=0;i<7;i++){
            arrayWeek[i]=0;
        }
        if(type==1){
            for(String str:list){
                int n=judge(str,type);
                arrayDay[n]++;
            }
            return arrayDay;
        }
        if(type==2){
            for(String str:list){
                int n=judge(str,type);
                arrayWeek[n]++;
            }
            return arrayWeek;
        }
        return null;
    }
    //判断是哪个时间段
    public int judge(String time,int type){
        Date now = new Date(System.currentTimeMillis());
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        long givendate=0L;
        long currentdate=0L;
        try {
            date=dateFormat.parse(time);
            givendate=date.getTime();
            if (type == 1) {
                //可以根据需要设置时区
                //cal.setTimeZone(TimeZone.getDefault());
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                //毫秒可根据系统需要清除或不清除
                cal.set(Calendar.MILLISECOND, 0);
                currentdate=cal.getTime().getTime();
                int temp=(int) ((givendate-currentdate)/1000/3600);
                return temp;
            }
            if (type==2) {
                cal.add(Calendar.DAY_OF_MONTH, -6);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                currentdate=cal.getTime().getTime();
                int temp=(int) ((givendate-currentdate)/1000/3600/24);
                return temp;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }



}
