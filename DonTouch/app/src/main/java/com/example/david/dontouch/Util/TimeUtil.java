package com.example.david.dontouch.Util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 2017/6/7.
 */

public class TimeUtil {

    public static List<String> openTimeList = new ArrayList<>();
    public static List<String> closeTimeList = new ArrayList<>();
    private static TimeUtil timeUtil;

    public synchronized static TimeUtil getInstance() {
        if (timeUtil == null) {
            timeUtil = new TimeUtil();
        }
        return timeUtil;
    }

    public static long getIntervalTime() {
        long time = 0;

        int size = 0;
        if (openTimeList.size() > closeTimeList.size()) {
            size = closeTimeList.size();
        } else {
            size = openTimeList.size();
        }

        for (int i = 1; i < size; i++) {
            long local_time = getTime(openTimeList.get(i), closeTimeList.get(i-1));
            if (local_time < 0) {
                time -= local_time;
            } else {
                time += local_time;
            }
        }

        return time;
    }

    public static long getTime(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long use = 0;

        try {
            long start = sdf.parse(startTime).getTime();
            long end = sdf.parse(endTime).getTime();
            use = (end - start) / (1000 * 60);
            Log.i("TimeUtilTest", "start time = " + startTime);
            Log.i("TimeUtilTest", "end time = " + endTime);
            Log.i("TimeUtilTest", "time = " + use);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return use;
    }

    public static long getUseTime() {
        long time = 0;

        int size = 0;
        if (openTimeList.size() > closeTimeList.size()) {
            size = closeTimeList.size();
        } else {
            size = openTimeList.size();
        }

        for (int i = 0; i < size; i++) {
            long local_time = getTime(closeTimeList.get(i), openTimeList.get(i));
            if (local_time < 0) {
                time -= local_time;
            } else {
                time += local_time;
            }
        }

        return time;
    }

}
