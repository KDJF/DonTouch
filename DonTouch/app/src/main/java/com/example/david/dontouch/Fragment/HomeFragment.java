package com.example.david.dontouch.Fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.dontouch.Adapter.ViewPagerAdapterHome;
import com.example.david.dontouch.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private List<View> lists;
    private Random random;//用于产生随机数字
    private TextView timesView;

    private ViewPagerAdapterHome viewPagerAdapterHome;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //初始化viewpage
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lists = new ArrayList<View>();
        View dayview = inflater.inflate(R.layout.homepage_day, null);
        View weekview = inflater.inflate(R.layout.homepage_day, null);
        View monthview = inflater.inflate(R.layout.homepage_day, null);
        lists.add(dayview);
        lists.add(weekview);
        lists.add(monthview);
        viewPagerAdapterHome = new ViewPagerAdapterHome(lists);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_viewpager);
        viewPager.setAdapter(viewPagerAdapterHome);

        //初始化日/周/月的view
        initTextView(dayview, 6, 35, 1);
        initTextView(weekview, 50, 28, 2);
        initTextView(monthview, 236, 55, 3);
        random = new Random();
        ArrayList<BarEntry> yVals = new ArrayList<>();//Y轴方向第一组数组
        for (int i = 1; i < 25; i++) {//添加数据源
            yVals.add(new BarEntry(random.nextInt(60), i));

        }

        TextView currentTime= (TextView) view.findViewById(R.id.current_time);
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日");
        Date curDate =  new Date(System.currentTimeMillis());
        currentTime.setText(formatter.format(curDate));
        //初始化barchart
        initBarChart(view, yVals);
        view.postInvalidate();
        return view;
    }

    private void initTextView(View view, int time_hour, int time_minute, int page) {
        TextView textView_time = (TextView) view.findViewById(R.id.text_time);
        TextView textView_tip = (TextView) view.findViewById(R.id.text_tip);

        SharedPreferences sharedPreferences= view.getContext().getSharedPreferences("locktimes",
                Activity.MODE_PRIVATE);
        int times = sharedPreferences.getInt("times", 0);
        Log.i("TEST_TIMES", "times is : " + times);
        timesView = (TextView) view.findViewById(R.id.text_times);
        timesView.setText(Integer.toString(times));

        SpannableStringBuilder word = new SpannableStringBuilder();
        final String one = String.valueOf(time_hour);
        final String two = "H";
        final String three = String.valueOf(time_minute);
        final String four = "Min";
        word.append(one);
        int start = 0;
        int end = one.length();
        word.setSpan(new AbsoluteSizeSpan(200), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        word.append(two);
        start = end;
        end += two.length();
        word.setSpan(new AbsoluteSizeSpan(100), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        word.append(three);
        start = end;
        end += three.length();
        word.setSpan(new AbsoluteSizeSpan(200), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        word.append(four);
        start = end;
        end += four.length();
        word.setSpan(new AbsoluteSizeSpan(100), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView_time.setText(word);
        if (page == 1) {
            textView_tip.setText("今日使用时间");
        }
        if (page == 2) {
            textView_tip.setText("本周使用时间");
        }
        if (page == 3) {
            textView_tip.setText("本月使用时间");
        }
    }

    private void initBarChart(View view, ArrayList<BarEntry> yVals) {
        BarChart barchart = (BarChart) view.findViewById(R.id.spread_bar_chart);
//        ArrayList<BarEntry> yVals = new ArrayList<>();//Y轴方向第一组数组
        ArrayList<String> xVals = new ArrayList<>();//X轴数据

        for (int i = 1; i < 25; i++) {//添加数据源
            if (i < 10)
                xVals.add("0" + i + "h");
            else
                xVals.add(i + "h");
        }

        BarDataSet barDataSet = new BarDataSet(yVals, "发现");

        barDataSet.setColor(Color.rgb(20,185,214));//设置第一组数据颜色

        ArrayList<IBarDataSet> onebardata = new ArrayList<>();//IBarDataSet 接口很关键，是添加多组数据的关键结构，LineChart也是可以采用对应的接口类，也可以添加多组数据
        onebardata.add(barDataSet);

        BarData bardata = new BarData(xVals, onebardata);
        barchart.setData(bardata);
        barchart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);//设置注解的位置在左上方
        barchart.getLegend().setForm(Legend.LegendForm.CIRCLE);//这是左边显示小图标的形状
        barchart.getLegend().setTextColor(Color.BLACK);
        barchart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        barchart.getXAxis().setDrawGridLines(false);//不显示网格

        barchart.getAxisRight().setEnabled(false);//右侧不显示Y轴
        barchart.getAxisLeft().setAxisMinValue(0.0f);//设置Y轴显示最小值，不然0下面会有空隙
        barchart.getAxisLeft().setDrawGridLines(false);//不设置Y轴网格

        barchart.setDescription("");//设置描述
        barchart.setDescriptionTextSize(20.f);//设置描述字体
//        barchart.animateXY(1000, 2000);//设置动画

    }

}
