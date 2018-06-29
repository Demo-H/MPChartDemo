package com.dhunter.mpchart;

import android.graphics.Color;
import android.view.ViewGroup;

import com.dhunter.mpchart.utils.ScreenUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by dhunter on 2018/6/29.
 */

public class BarChartDemoActivity extends BaseActivity {

    @BindView(R.id.mBarChart)
    BarChart mBarChart;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_barchart_demo;
    }

    @Override
    protected void initLayout() {
// 条形图初始化
        ViewGroup.LayoutParams lp = mBarChart.getLayoutParams();
        lp.width = ScreenUtil.getWidth(this);
        lp.height = ScreenUtil.getWidth(this);
        mBarChart.setLayoutParams(lp);

        //设置表格上的点，被点击的时候，的回调函数
//        mBarChart.setOnChartValueSelectedListener(this);
        mBarChart.setDrawBarShadow(false);
        //设置所有的数值在图形的上面,而不是图形上
        mBarChart.setDrawValueAboveBar(true);
        //描述设置显示在y轴上的单位
        mBarChart.getDescription().setEnabled(true);
        mBarChart.getDescription().setText("(万元)");
        mBarChart.getDescription().setPosition(65,60);
        mBarChart.getDescription().setTextColor(Color.BLACK);

        // 如果60多个条目显示在图表,drawn没有值
//        mBarChart.setMaxVisibleValueCount(60);
        // 扩展现在只能分别在x轴和y轴
        mBarChart.setPinchZoom(false);
        //是否显示表格颜色
        mBarChart.setDrawGridBackground(false);
        mBarChart.setNoDataText("暂无数据");
        mBarChart.setHighlightFullBarEnabled(false);

        mBarChart.animateY(2500);


        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        // 1个月的时间间隔
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(12);
        xAxis.setAxisMinimum(0f);
//        xAxis.setAxisMaximum(12f);

        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //这个替换setStartAtZero(true)
        leftAxis.setAxisMinimum(0f);
//        leftAxis.setAxisMaximum(600f);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(false);


        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setEnabled(false);

        // 设置标示，就是那个一组y的value的
        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //样式
        l.setForm(Legend.LegendForm.SQUARE);
        //字体
        l.setFormSize(9f);
        //大小
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
    }

    @Override
    protected void requestData() {
        //模拟数据
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        yVals1.add(new BarEntry(1, 200));
        yVals1.add(new BarEntry(2, 300));
        yVals1.add(new BarEntry(3, 400));
        yVals1.add(new BarEntry(4, 530));
        yVals1.add(new BarEntry(5, 100));
        yVals1.add(new BarEntry(6, 200));
        yVals1.add(new BarEntry(7, 300));
        yVals1.add(new BarEntry(8, 400));
        yVals1.add(new BarEntry(9, 400));
        yVals1.add(new BarEntry(10, 440));
        yVals1.add(new BarEntry(11, 400));
        yVals1.add(new BarEntry(12, 405));
        setData(yVals1);
    }
    //设置数据
    private void setData(ArrayList yVals1) {
        float start = 1f;
        BarDataSet set1;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "销售量");
            //设置有四种颜色
            set1.setColors(0xffff7700);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            //设置数据
            mBarChart.setData(data);
        }
    }
}
