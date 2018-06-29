package com.dhunter.mpchart;

import android.graphics.Color;
import android.view.ViewGroup;

import com.dhunter.mpchart.utils.ScreenUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by dhunter on 2018/6/29.
 */

public class LineChartDemoActivity extends BaseActivity {

    @BindView(R.id.mLineChar)
    LineChart mLineChart;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_linechart_demo;
    }

    @Override
    protected void initLayout() {

        // 线形图初始化
        ViewGroup.LayoutParams lp = mLineChart.getLayoutParams();
        lp.width = ScreenUtil.getWidth(this);
        lp.height = ScreenUtil.getWidth(this);
        mLineChart.setLayoutParams(lp);

        // 没有描述的文本
        mLineChart.getDescription().setEnabled(false);
        // 支持触控手势
        mLineChart.setTouchEnabled(true);
        mLineChart.setDragDecelerationFrictionCoef(0.9f);
        // 支持缩放和拖动
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setHighlightPerDragEnabled(true);
        // 如果禁用,扩展可以在x轴和y轴分别完成
        mLineChart.setPinchZoom(true);
        // 设置背景颜色(白色)
        mLineChart.setBackgroundColor(Color.WHITE);

        //默认x动画
        mLineChart.animateX(2500);
//        mLineChart.setNoDataText("没有数据呢(⊙o⊙)");   //没有数据时显示在中央的字符串，参数是String对象
        mLineChart.setHighlightPerDragEnabled(false);

        //获得数据
        Legend l = mLineChart.getLegend();

        //修改
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(0xff43484b);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        //x轴
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(12f);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        //设置x轴显示的标签个数
        xAxis.setLabelCount(12);

        //左边y轴
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(5000);
        leftAxis.setAxisMinimum(-3000);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularityEnabled(false);

        mLineChart.getAxisRight().setEnabled(false);
    }

    @Override
    protected void requestData() {
        //设置数据
        setData(12, 1000);
    }

    //设置数据
    private void setData(int count, float range) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (new Random().nextInt(1500)) + 3000;
            yVals1.add(new Entry(i + 1, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float mult = range-3000;
            float val = (float) (new Random().nextInt(1500));
            yVals2.add(new Entry(i + 1, val));
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float mult = range-3000;
            float val = (float) (new Random().nextInt(1500)) - 3000;
            yVals3.add(new Entry(i + 1, val));
        }

        LineDataSet set1, set2, set3;

        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mLineChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mLineChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = new LineDataSet(yVals1, "新增会员");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.isDrawValuesEnabled();
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.BLUE);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
//            set1.setHighLightColor(Color.rgb(244, 117, 117));  // 设置点击某个点时，横竖两条线的颜色
            set1.setDrawCircleHole(false);

            //创建一个数据集,并给它一个类型
            set2 = new LineDataSet(yVals2, "活跃会员");
            set2.isDrawValuesEnabled();
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(Color.GREEN);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
//            set2.setHighLightColor(Color.rgb(244, 117, 117));

            set3 = new LineDataSet(yVals3, "流失会员");
            set3.setAxisDependency(YAxis.AxisDependency.LEFT);
            set3.setColor(Color.YELLOW);
            set1.isDrawValuesEnabled();
            set3.setCircleColor(Color.RED);
            set3.setLineWidth(2f);
            set3.setCircleRadius(3f);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
//            set3.setHighLightColor(Color.rgb(244, 117, 117));

            // 创建一个数据集的数据对象
            LineData data = new LineData(set1, set2, set3);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            //设置数据
            mLineChart.setData(data);
        }
    }
}
