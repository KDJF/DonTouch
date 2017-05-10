package com.example.david.dontouch.Fragment;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.view.chart.BarChart;
import com.ab.view.chart.CategorySeries;
import com.ab.view.chart.ChartFactory;
import com.ab.view.chart.PointStyle;
import com.ab.view.chart.XYMultipleSeriesDataset;
import com.ab.view.chart.XYMultipleSeriesRenderer;
import com.ab.view.chart.XYSeriesRenderer;
import com.example.david.dontouch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobFragment extends Fragment  implements View.OnClickListener {
    private ViewPager vp;
    private View view1, view2, view3;
    private List<View> viewList;
    private PagerAdapter pageradapter;
    private TextView btn_tv1,btn_tv2,btn_tv3,btn_tv4;
    private RelativeLayout alltimes,viewingtimes;

    public MobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mob, container, false);

        init(view);
        xybarChart();
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
        btn_tv1=(TextView) view.findViewById(R.id.btn_tv1);
        btn_tv2=(TextView) view.findViewById(R.id.btn_tv2);
        btn_tv3=(TextView) view.findViewById(R.id.btn_tv3);
        btn_tv4=(TextView) view.findViewById(R.id.btn_tv4);
        btn_tv1.setOnClickListener(this);
        btn_tv2.setOnClickListener(this);
        btn_tv3.setOnClickListener(this);
        btn_tv4.setOnClickListener(this);
        alltimes=(RelativeLayout) view.findViewById(R.id.alltimes);
        viewingtimes=(RelativeLayout) view.findViewById(R.id.viewingtimes);
    }

    //清空按钮颜色
    private void clearSelect() {

        btn_tv1.setTextColor(Color.rgb(100, 149, 237));
        btn_tv2.setTextColor(Color.rgb(100, 149, 237));
        btn_tv3.setTextColor(Color.rgb(100, 149, 237));
        btn_tv4.setTextColor(Color.rgb(100, 149, 237));
        btn_tv1.setBackgroundResource(R.drawable.leftbtnshapefalse);
        btn_tv2.setBackgroundColor(Color.WHITE);
        btn_tv3.setBackgroundColor(Color.WHITE);
        btn_tv4.setBackgroundResource(R.drawable.rightbtnshapefalse);


    }

    private void xybarChart(){

//		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.chart01);
        //说明文字
        String[] titles = new String[] { "第一组", "第二组" };
        //数据
        List<double[]> values = new ArrayList<double[]>();
        //每个数据点的颜色
        List<int[]> colors = new ArrayList<int[]>();
        //每个数据点的简要 说明
        List<String[]> explains = new ArrayList<String[]>();

        values.add(new double[] { 14230, 0, 0, 0, 15900, 17200, 12030});
        values.add(new double[] { 5230, 0, 0, 0, 7900, 9200, 13030});

        colors.add(new int[] { Color.RED, 0, 0, 0, 0, 0, 0});
        colors.add(new int[] { 0, 0, Color.BLUE, 0, Color.GREEN, 0, 0});

        explains.add(new String[] { "红色", "点2", "点3", "点4", "", "点6", ""});
        explains.add(new String[] { "没有颜色", "没有颜色", "蓝色的点\n第二行的文字\n第三行的文字", "没有颜色\n第二行的文字\n第三行的文字\n第四行的文字\n第五行的文字", "没有颜色", "没有颜色", ""});

        //柱体或者线条颜色
        int[] mSeriescolors = new int[] { Color.rgb(153, 204, 0),Color.rgb(51, 181, 229) };
        //创建渲染器
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        int length = mSeriescolors.length;
        for (int i = 1; i < length; i++) {
            //创建SimpleSeriesRenderer单一渲染器
            XYSeriesRenderer r = new XYSeriesRenderer();
            //SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            //设置渲染器颜色
            r.setColor(mSeriescolors[i]);
            r.setFillPoints(true);
            r.setPointStyle(PointStyle.CIRCLE);
            r.setLineWidth(1);
            r.setChartValuesTextSize(16);
            //加入到集合中
            renderer.addSeriesRenderer(r);
        }
        //点的大小
        renderer.setPointSize(2f);
        //坐标轴标题文字大小
        renderer.setAxisTitleTextSize(16);
        //图形标题文字大小
        renderer.setChartTitleTextSize(25);
        //轴线上标签文字大小
        renderer.setLabelsTextSize(15);
        //说明文字大小
        renderer.setLegendTextSize(15);
        //图表标题
//	    renderer.setChartTitle("我是图表的标题");
        //X轴标题
        renderer.setXTitle("X轴");
        //Y轴标题
        renderer.setYTitle("Y轴");
        //X轴最小坐标点
        renderer.setXAxisMin(0.5);
        //X轴最大坐标点
        renderer.setXAxisMax(7.5);
        //Y轴最小坐标点
        renderer.setYAxisMin(0);
        //Y轴最大坐标点
        renderer.setYAxisMax(24000);
        //坐标轴颜色
        renderer.setAxesColor(Color.rgb(51, 181, 229));
        renderer.setXLabelsColor(Color.rgb(51, 181, 229));
        renderer.setYLabelsColor(0,Color.rgb(51, 181, 229));
        //设置图表上标题与X轴与Y轴的说明文字颜色
        renderer.setLabelsColor(Color.GRAY);
        //renderer.setGridColor(Color.GRAY);
        //设置字体加粗
        renderer.setTextTypeface("sans_serif", Typeface.BOLD);
        //设置在图表上是否显示值标签
        renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
//	    renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
        //显示屏幕可见取区的XY分割数
        renderer.setXLabels(7);
        renderer.setYLabels(10);
        //X刻度标签相对X轴位置
        renderer.setXLabelsAlign(Paint.Align.CENTER);
        //Y刻度标签相对Y轴位置
        renderer.setYLabelsAlign(Paint.Align.LEFT);
        renderer.setPanEnabled(true, false);
        renderer.setZoomEnabled(true);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomRate(1.1f);
        renderer.setBarSpacing(0.5f);

        //标尺开启
        renderer.setScaleLineEnabled(false);
        //设置标尺提示框高
        renderer.setScaleRectHeight(60);
        //设置标尺提示框宽
        renderer.setScaleRectWidth(150);
        //设置标尺提示框背景色
        renderer.setScaleRectColor(Color.argb(150, 52, 182, 232));
        renderer.setScaleLineColor(Color.argb(175, 150, 150, 150));
        renderer.setScaleCircleRadius(35);
        //第一行文字的大小
        renderer.setExplainTextSize1(20);
        //第二行文字的大小
        renderer.setExplainTextSize2(20);

        //临界线
        double[] limit = new double[]{15000,12000,4000,9000};
	    renderer.setmYLimitsLine(limit);
        int[] colorsLimit = new int[] { Color.rgb(100, 255,255),Color.rgb(100, 255,255),Color.rgb(0, 255, 255),Color.rgb(0, 255, 255) };
        renderer.setmYLimitsLineColor(colorsLimit);

        //显示表格线
        renderer.setShowGrid(true);

        //如果值是0是否要显示
        renderer.setDisplayValue0(true);
        //创建渲染器数据填充器
        XYMultipleSeriesDataset mXYMultipleSeriesDataset = new XYMultipleSeriesDataset();
        for (int i = 1; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int[] c = colors.get(i);
            String[] e = explains.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                //设置每个点的颜色
                series.add(v[k],c[k],e[k]);
            }
            mXYMultipleSeriesDataset.addSeries(series.toXYSeries());
        }
        //背景
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setMarginsColor(Color.WHITE);

        //线图
        View chart = ChartFactory.getBarChartView(getContext(),mXYMultipleSeriesDataset,renderer, BarChart.Type.DEFAULT);
        View chart2 = ChartFactory.getBarChartView(getContext(),mXYMultipleSeriesDataset,renderer,BarChart.Type.DEFAULT);
        alltimes.addView(chart);
        viewingtimes.addView(chart2);

    }



    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_tv1:
                clearSelect();
                btn_tv1.setTextColor(Color.WHITE);
                btn_tv1.setBackgroundResource(R.drawable.leftbtnshapetrue);
                break;
            case R.id.btn_tv2:
                clearSelect();
                btn_tv2.setTextColor(Color.WHITE);
                btn_tv2.setBackgroundColor(Color.rgb(100, 149, 237));

                break;
            case R.id.btn_tv3:
                clearSelect();
                btn_tv3.setTextColor(Color.WHITE);
                btn_tv3.setBackgroundColor(Color.rgb(100, 149, 237));
                break;
            case R.id.btn_tv4:
                clearSelect();
                btn_tv4.setTextColor(Color.WHITE);
                btn_tv4.setBackgroundResource(R.drawable.rightbtnshapetrue);
                break;
        }
    }

}
