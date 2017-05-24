package com.example.david.dontouch.Util;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.david.dontouch.Model.AppItemInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by david on 2017/5/10.
 */

public class UStats {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy HH:mm:ss");
    public static final String TAG = UStats.class.getSimpleName();
    @SuppressWarnings("ResourceType")
    public static void getStats(Context context){
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        int interval = UsageStatsManager.INTERVAL_YEARLY;
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, -1);
        long startTime = calendar.getTimeInMillis();

        Log.d(TAG, "Range start:" + dateFormat.format(startTime) );
        Log.d(TAG, "Range end:" + dateFormat.format(endTime));

        UsageEvents uEvents = usm.queryEvents(startTime,endTime);
        while (uEvents.hasNextEvent()){
            UsageEvents.Event e = new UsageEvents.Event();
            uEvents.getNextEvent(e);

            if (e != null){
                Log.d(TAG, "Event: " + e.getPackageName() + "\t" +  e.getTimeStamp());
            }
        }
    }

    public static ArrayList<AppItemInfo> getUsageStatsList(Context context){
        UsageStatsManager usm = getUsageStatsManager(context);
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, -1);
        long startTime = calendar.getTimeInMillis();
        ArrayList<AppItemInfo> list = new ArrayList<>();
        AppItemInfo ai = null;

        Log.d(TAG, "Range start:" + dateFormat.format(startTime) );
        Log.d(TAG, "Range end:" + dateFormat.format(endTime));

        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,startTime,endTime);
        for(UsageStats usageStats: usageStatsList) {
            ai = new AppItemInfo();
            String packageName = usageStats.getPackageName();
            ai.setLabel(packageName);
            ai.setIcon(getProgramIcon(context, packageName));
            ai.setStartTime(usageStats.getTotalTimeInForeground());
            if (!isSystemProgram(context, packageName)) {
                list.add(ai);
            }
        }
        return list;
    }

    public static Drawable getProgramIcon(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        Drawable name = null;
        try {
            name = pm.getApplicationIcon(pm.getApplicationInfo(packageName,
                    PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static String getProgramLabel(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        String name = null;
        try {
            name = pm.getApplicationLabel(pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA)).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static boolean isSystemProgram(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean flag = false;
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0 ) {
                flag = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static ArrayList<AppItemInfo> printUsageStats(Context context, List<UsageStats> usageStatsList){
        ArrayList<AppItemInfo> list = new ArrayList<>();
        AppItemInfo ai = null;
        for (UsageStats u : usageStatsList){
            String packageName = u.getPackageName();
            ai.setLabel(getProgramLabel(context, packageName));
            ai.setIcon(getProgramIcon(context, packageName));
            ai.setStartTime(u.getTotalTimeInForeground());
            if (!isSystemProgram(context, packageName)) {
                list.add(ai);
            }
            Log.d(TAG, "Pkg: " + u.getPackageName() +  "\t" + "ForegroundTime: "
                    + u.getTotalTimeInForeground()) ;
        }
        return list;
    }

    @SuppressWarnings("ResourceType")
    private static UsageStatsManager getUsageStatsManager(Context context){
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        return usm;
    }
}
