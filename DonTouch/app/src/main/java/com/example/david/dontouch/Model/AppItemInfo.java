package com.example.david.dontouch.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by david on 2017/5/9.
 */

public class AppItemInfo implements Comparable<AppItemInfo> {
    private Drawable icon; // 存放图片
    private String label; // 存放应用程序名
    private String packageName; // 存放应用程序包名
    private long startTime ;  //程序运行时间
    public Drawable getIcon() {
        return icon;
    }
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public long getStartTime() {
        return startTime;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    @Override
    public int compareTo(AppItemInfo another) {
        return (int) (startTime-another.getStartTime());
    }
}
