package com.example.david.dontouch.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class JournalFragment extends Fragment {
//public class JournalFragment extends Fragment implements View.OnClickListener {

    private ViewPager vp;
    private View view1, view2, view3;
    private List<View> viewList;
    private PagerAdapter pageradapter;
//    private TextView btn_tv1, btn_tv2, btn_tv3, btn_tv4;


    public JournalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_journal, container, false);
        init(view);
        Random random = new Random();
        ArrayList<BarEntry> yVals = new ArrayList<>();//Y轴方向第一组数组
        for (int i = 0; i < 7; i++) {//添加数据源
            yVals.add(new BarEntry(random.nextInt(60), i));

        }

        initBarChart_duration(view, getWeek_xValues(), yVals);
        initBarChart_times(view, getWeek_xValues(), yVals);
        return view;
    }

    private void init(View view) {

        //viewpager的设置
        vp = (ViewPager) view.findViewById(R.id.vp);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view1 = inflater.inflate(R.layout.vpitem1, null);
        view2 = inflater.inflate(R.layout.vpitem2, null);
        view3 = inflater.inflate(R.layout.vpitem3, null);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        pageradapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

        };
        vp.setAdapter(pageradapter);


        //tv1
//        btn_tv1 = (TextView) view.findViewById(R.id.btn_tv1);
//        btn_tv2 = (TextView) view.findViewById(R.id.btn_tv2);
//        btn_tv3 = (TextView) view.findViewById(R.id.btn_tv3);
//        btn_tv4 = (TextView) view.findViewById(R.id.btn_tv4);
//        btn_tv1.setOnClickListener(this);
//        btn_tv2.setOnClickListener(this);
//        btn_tv3.setOnClickListener(this);
//        btn_tv4.setOnClickListener(this);
    }

    //清空按钮颜色
//    private void clearSelect() {
//
//        btn_tv1.setTextColor(Color.rgb(100, 149, 237));
//        btn_tv2.setTextColor(Color.rgb(100, 149, 237));
//        btn_tv3.setTextColor(Color.rgb(100, 149, 237));
//        btn_tv4.setTextColor(Color.rgb(100, 149, 237));
//        btn_tv1.setBackgroundResource(R.drawable.leftbtnshapefalse);
//        btn_tv2.setBackgroundColor(Color.WHITE);
//        btn_tv3.setBackgroundColor(Color.WHITE);
//        btn_tv4.setBackgroundResource(R.drawable.rightbtnshapefalse);
//
//
//    }

//    @Override
//    public void onClick(View v) {
//        // TODO Auto-generated method stub
//        switch (v.getId()) {
//            case R.id.btn_tv1:
//                clearSelect();
//                btn_tv1.setTextColor(Color.WHITE);
//                btn_tv1.setBackgroundResource(R.drawable.leftbtnshapetrue);
//                break;
//            case R.id.btn_tv2:
//                clearSelect();
//                btn_tv2.setTextColor(Color.WHITE);
//                btn_tv2.setBackgroundColor(Color.rgb(100, 149, 237));
//
////                Random random = new Random();
////                ArrayList<BarEntry> yVals = new ArrayList<>();//Y轴方向第一组数组
////                for (int i = 1; i < 8; i++) {//添加数据源
////                    yVals.add(new BarEntry(random.nextInt(60), i));
////
////                }
////
////                initBarChart_duration(v, getWeek_xValues(), yVals);
////                initBarChart_times(v, getWeek_xValues(), yVals);
//                break;
//            case R.id.btn_tv3:
//                clearSelect();
//                btn_tv3.setTextColor(Color.WHITE);
//                btn_tv3.setBackgroundColor(Color.rgb(100, 149, 237));
//                break;
//            case R.id.btn_tv4:
//                clearSelect();
//                btn_tv4.setTextColor(Color.WHITE);
//                btn_tv4.setBackgroundResource(R.drawable.rightbtnshapetrue);
//                break;
//        }
//    }

    private void initBarChart_duration(View view, ArrayList<String> xVals, ArrayList<BarEntry> yVals) {
        BarChart barchart = (BarChart) view.findViewById(R.id.barchart_total_duration);
//        ArrayList<BarEntry> yVals = new ArrayList<>();//Y轴方向第一组数组

        BarDataSet barDataSet = new BarDataSet(yVals, "总时长");

        barDataSet.setColor(Color.rgb(20, 185, 214));//设置第一组数据颜色

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

    private void initBarChart_times(View view, ArrayList<String> xVals, ArrayList<BarEntry> yVals) {
        BarChart barchart = (BarChart) view.findViewById(R.id.barchart_total_times);
//        ArrayList<BarEntry> yVals = new ArrayList<>();//Y轴方向第一组数组

        BarDataSet barDataSet = new BarDataSet(yVals, "总次数");

        barDataSet.setColor(Color.rgb(20, 185, 214));//设置第一组数据颜色

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

    private ArrayList<String> getDay_xValues() {

        ArrayList<String> xValues = new ArrayList<>();//X轴数据
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date curDate = new Date(System.currentTimeMillis());
        String day = formatter.format(curDate);// 获取当前月份的日期号码
        formatter = new SimpleDateFormat("E");
        String week = formatter.format(curDate);// 获取当前月份的日期号码

        for (int i = 1; i < 25; i++) {//添加数据源
            if (i < 10)
                xValues.add("0" + i + "h");
            else
                xValues.add(i + "h");
        }
//        xValues.add(day);
        return xValues;
    }


    private ArrayList<String> getWeek_xValues() {

        ArrayList<String> xValues = new ArrayList<>();//X轴数据
        ArrayList<String> weeks = new ArrayList<>();
        weeks.add("周日");
        weeks.add("周一");
        weeks.add("周二");
        weeks.add("周三");
        weeks.add("周四");
        weeks.add("周五");
        weeks.add("周六");


        SimpleDateFormat formatter = new SimpleDateFormat("E");
        Date curDate = new Date(System.currentTimeMillis());
        String week = formatter.format(curDate);// 获取当前月份的日期号码
        int current = weeks.indexOf(week);

        for (int  i=6;i>current;i--){
            xValues.add(weeks.get(i));
        }
        for (int  i=0;i<current+1;i++){
            xValues.add(weeks.get(i));
        }
        return xValues;
    }
}
