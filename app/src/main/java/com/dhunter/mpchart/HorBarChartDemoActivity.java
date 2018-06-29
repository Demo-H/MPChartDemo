package com.dhunter.mpchart;

import android.view.ViewGroup;

import com.dhunter.mpchart.utils.ScreenUtil;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by dhunter on 2018/6/29.
 */

public class HorBarChartDemoActivity extends BaseActivity {

    @BindView(R.id.mHorizontalBarChart)
    HorizontalBarChart mHorizontalBarChart;
    private final String lable[]={"手机","电视机","笔记本电脑","台式电脑",
            "电冰箱","空调","洗衣机","油烟机","空气净化器","加湿器"};
    private float spaceForBar = 10f;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hor_barchart_demo;
    }

    @Override
    protected void initLayout() {
// 水平条形图初始化
        ViewGroup.LayoutParams lp = mHorizontalBarChart.getLayoutParams();
        lp.width = ScreenUtil.getWidth(this);
        lp.height = ScreenUtil.getWidth(this);
        mHorizontalBarChart.setLayoutParams(lp);

        //设置相关属性
        mHorizontalBarChart.setTouchEnabled(false);
        mHorizontalBarChart.setDrawBarShadow(false);
        mHorizontalBarChart.setDrawValueAboveBar(true);
        mHorizontalBarChart.getDescription().setEnabled(false);
        mHorizontalBarChart.setPinchZoom(false);
        mHorizontalBarChart.setNoDataText("无数据");
        mHorizontalBarChart.setDrawGridBackground(false);

        //x轴
        XAxis xAxis = mHorizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);

        xAxis.setDrawLabels(true);
        xAxis.setGranularity(10f);

        //y轴
        YAxis yAxis = mHorizontalBarChart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMinimum(0f);
        yAxis.setEnabled(false);
        //y轴
        YAxis yr = mHorizontalBarChart.getAxisRight();
        yr.setEnabled(false);

        mHorizontalBarChart.setFitBars(true);
        mHorizontalBarChart.animateY(2500);
        Legend legend = mHorizontalBarChart.getLegend();
        legend.setEnabled(false);
    }

    @Override
    protected void requestData() {
        XAxis xAxis = mHorizontalBarChart.getXAxis();
        xAxis.setLabelCount(10);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return lable[(int) (value / spaceForBar)];
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });

        float barWidth = 8f;
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < 10; i++) {
            float val = new Random().nextInt(1000);
            yVals1.add(new BarEntry(i * spaceForBar, val));
        }
        BarDataSet set1;
        if (mHorizontalBarChart.getData() != null &&
                mHorizontalBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mHorizontalBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mHorizontalBarChart.getData().notifyDataChanged();
            mHorizontalBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet 1");
            set1.setColor(0xffff7700);
            set1.setDrawValues(true);
            //显示为整数
            set1.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    int valuedate = (int) value;
                    return valuedate + "";
                }
            });
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            mHorizontalBarChart.setData(data);
        }
    }

}
