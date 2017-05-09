package com.example.david.dontouch.Util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.david.dontouch.Model.AppItemInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by david on 2017/5/9.
 */

public class AppTime {
    private Context context;
    public AppTime(Context context) {
        this.context = context;
    }
    public Drawable getProgramIcon(String packageName) {
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
    public ArrayList<AppItemInfo> getRunPkgUsageStats() {
        ArrayList<AppItemInfo> list = new ArrayList<>();
        AppItemInfo ai;
        try {
            Class<?> cServiceManager = Class
                    .forName("android.os.ServiceManager");
            Method mGetService = cServiceManager.getMethod("getService",
                    java.lang.String.class);
            Object oRemoteService = mGetService.invoke(null, "usagestats");
            Class<?> cStub = Class
                    .forName("com.android.internal.app.IUsageStats$Stub");
            Method mUsageStatsService = cStub.getMethod("asInterface",
                    android.os.IBinder.class);
            Object oIUsageStats = mUsageStatsService.invoke(null,
                    oRemoteService);
            Class<?> cIUsageStatus = Class
                    .forName("com.android.internal.app.IUsageStats");
            Method mGetAllPkgUsageStats = cIUsageStatus.getMethod(
                    "getAllPkgUsageStats", (Class[]) null);
            Object[] oPkgUsageStatsArray = (Object[]) mGetAllPkgUsageStats
                    .invoke(oIUsageStats, (Object[]) null);
            Class<?> cPkgUsageStats = Class
                    .forName("com.android.internal.os.PkgUsageStats");
            for (Object pkgUsageStats : oPkgUsageStatsArray) {
                ai = new AppItemInfo();
                String packageName = (String) cPkgUsageStats.getDeclaredField(
                        "packageName").get(pkgUsageStats);
                ai.setLabel(packageName);
                ai.setIcon(getProgramIcon(packageName));
                int launchCount = cPkgUsageStats
                        .getDeclaredField("launchCount").getInt(pkgUsageStats);
                long usageTime = cPkgUsageStats.getDeclaredField("usageTime")
                        .getLong(pkgUsageStats);
                ai.setStartTime(usageTime);
                if (launchCount > 0) {
                    Log.i("TAG", "[getPkgUsageStats] " + packageName
                            + "  count: " + launchCount + "  time:  "
                            + usageTime);
                    list.add(ai);
                } else {
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(list!=null)
            Collections.sort(list);
        return list;
    }
}
