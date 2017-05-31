package com.example.david.dontouch.Activity;

/**
 * Created by DELL on 2017/5/21.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.david.dontouch.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WhiteListActivity extends Activity {

    // 用来记录应用程序的信息
    List<AppsItemInfo> list;

    private Switch white_list_switch;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private ListView listview;
    private PackageManager pManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_whitelist);

        sp = getSharedPreferences("white_list", 0);
        editor = sp.edit();

        //临时加入switch
//        editor.putBoolean("white_list_switch", true);
//        editor.commit();
        ////////

        listview = (ListView) findViewById(R.id.list_white);
        white_list_switch = (Switch) findViewById(R.id.switch_white);
        if (sp.getBoolean("white_list_switch", false)) {

            listview.setVisibility(View.VISIBLE);
            white_list_switch.setChecked(true);
            // 获取图片、应用名、包名
            pManager = WhiteListActivity.this.getPackageManager();
            List<PackageInfo> appList = getAllApps(WhiteListActivity.this);

            list = new ArrayList<AppsItemInfo>();

            for (int i = 0; i < appList.size(); i++) {
                PackageInfo pinfo = appList.get(i);
                AppsItemInfo shareItem = new AppsItemInfo();
                // 设置图片
                shareItem.setIcon(pManager
                        .getApplicationIcon(pinfo.applicationInfo));
                // 设置应用程序名字
                shareItem.setLabel(pManager.getApplicationLabel(
                        pinfo.applicationInfo).toString());
                // 设置应用程序的包名
                shareItem.setPackageName(pinfo.applicationInfo.packageName);

                list.add(shareItem);

            }

            // 设置listview的Adapter
            listview.setAdapter(new baseAdapter());
        }

        white_list_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editor.putBoolean("white_list_switch", true);
                    editor.commit();

                    // 获取图片、应用名、包名
                    pManager = WhiteListActivity.this.getPackageManager();
                    List<PackageInfo> appList = getAllApps(WhiteListActivity.this);

                    list = new ArrayList<AppsItemInfo>();

                    for (int i = 0; i < appList.size(); i++) {
                        PackageInfo pinfo = appList.get(i);
                        AppsItemInfo shareItem = new AppsItemInfo();
                        // 设置图片
                        shareItem.setIcon(pManager
                                .getApplicationIcon(pinfo.applicationInfo));
                        // 设置应用程序名字
                        shareItem.setLabel(pManager.getApplicationLabel(
                                pinfo.applicationInfo).toString());
                        // 设置应用程序的包名
                        shareItem.setPackageName(pinfo.applicationInfo.packageName);
                        list.add(shareItem);

                    }

                    // 设置listview的Adapter
                    listview.setAdapter(new baseAdapter());
                    listview.setVisibility(View.VISIBLE);

                } else {
                    editor.putBoolean("white_list_switch", false);
                    editor.commit();
                    listview.setVisibility(View.INVISIBLE);

                }


            }
        });
    }

    public static List<PackageInfo> getAllApps(Context context) {

        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = context.getPackageManager();
        // 获取手机内所有应用
        List<PackageInfo> packlist = pManager.getInstalledPackages(0);
        for (int i = 0; i < packlist.size(); i++) {
            PackageInfo pak = (PackageInfo) packlist.get(i);

            // 判断是否为非系统预装的应用程序
            // 这里还可以添加系统自带的，这里就先不添加了，如果有需要可以自己添加
            // if()里的值如果<=0则为自己装的程序，否则为系统工程自带
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                // 添加自己已经安装的应用程序
                apps.add(pak);
            }

        }
        return apps;
    }

    private class baseAdapter extends BaseAdapter {

        List<Boolean> mChecked;

        LayoutInflater inflater = LayoutInflater.from(WhiteListActivity.this);

        Set<String> apps;
        List<String> packagenames;

        public baseAdapter() {
            apps = sp.getStringSet("white_list_apps", new HashSet<String>());
            mChecked = new ArrayList<Boolean>();
            packagenames = new ArrayList<String>();
            for (int i = 0; i < list.size(); i++) {
                mChecked.add(false);
                packagenames.add(list.get(i).getPackageName());
            }
            System.out.println("mChecked初始化:   "+mChecked.size() + mChecked);
            System.out.println("apps初始化:   " + apps.size() + apps);
            for (String temp : apps) {
                int index = packagenames.indexOf(temp);
                mChecked.set(index, true);
            }
            System.out.println("第一次修改后的mChecked:   " + mChecked.size() + mChecked);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;


            convertView = inflater.inflate(R.layout.white_list_item, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView
                    .findViewById(R.id.apps_image);
            holder.label = (TextView) convertView
                    .findViewById(R.id.apps_textview);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox cb = (CheckBox) view;
                    Boolean b = cb.isChecked();
                    if (b) {
                        mChecked.set(position, true);
                        apps.add(packagenames.get(position));
                        editor.remove("white_list_apps");
                        editor.commit();
                        editor.putStringSet("white_list_apps", apps);
                        editor.commit();
                        System.out.println("从"+sp.getStringSet("white_list_apps",null));
                    } else {
                        mChecked.set(position, false);
                        apps.remove(packagenames.get(position));
                        editor.remove("white_list_apps");
                        editor.commit();
                        editor.putStringSet("white_list_apps", apps);
                        editor.commit();
                        System.out.println("从"+sp.getStringSet("white_list_apps",null));
                    }
//                    mChecked.set(position, cb.isChecked());
                }
            });
//            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                        @Override
//                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                            if (b) {
//                                mChecked.remove(position);
//                                mChecked.set(position, true);
//                                apps.add(list.get(position).getPackageName());
//                                System.out.println("switch mChecked:   "+mChecked);
//                                System.out.println("switch+apps:   "+apps);
//                                editor.putStringSet("white_list_apps",apps);
//                                editor.commit();
//                            } else {
//                                mChecked.remove(position);
//                                mChecked.set(position,false);
//                                apps.remove(list.get(position).getPackageName());
//                                System.out.println("switch mChecked:   "+mChecked);
//                                System.out.println("switch+apps:   "+apps);
//                                editor.putStringSet("white_list_apps",apps);
//                                editor.commit();
//                            }
//                }
//            });
            convertView.setTag(holder);


            holder.checkBox.setChecked(mChecked.get(position));
            holder.icon.setImageDrawable(list.get(position).getIcon());
            holder.label.setText(list.get(position).getLabel().toString());
            return convertView;

        }

    }

    private class ViewHolder {
        private ImageView icon;
        private TextView label;
        private CheckBox checkBox;
    }


    // 自定义一个 AppsItemInfo 类，用来存储应用程序的相关信息
    private class AppsItemInfo {

        private Drawable icon; // 存放图片
        private String label; // 存放应用程序名
        private String packageName; // 存放应用程序包名

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

    }

}