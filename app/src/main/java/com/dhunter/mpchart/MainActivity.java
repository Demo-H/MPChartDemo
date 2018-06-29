package com.dhunter.mpchart;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mTargetPieChart)
    PieChart mTarPieChart;
    @BindView(R.id.line_chart_demo)
    Button mLineChartDemo;
    @BindView(R.id.bar_chart_demo)
    Button mBarChartDemo;
    @BindView(R.id.horizontal_bar_chart_demo)
    Button mHBarChartDemo;
    @BindView(R.id.pie_chart_demo)
    Button mPieChartDemo;
    @BindView(R.id.line_chart)
    Button mLineChart;
    @BindView(R.id.bar_chart)
    Button mBarChart;
    @BindView(R.id.horizontal_bar_chart)
    Button mHBarChart;
    @BindView(R.id.pie_chart)
    Button mPieChart;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initLayout() {
        mTarPieChart.setTouchEnabled(false);
        mTarPieChart.setDrawHoleEnabled(true); //显示中间的洞
        mTarPieChart.setHoleColor(0xffffffff); //洞的颜色
        mTarPieChart.setTransparentCircleColor(R.color.color_ff7000);
        mTarPieChart.setDrawSliceText(false);//不显示切片里面的字体，就是pie 块里面的字体
        mTarPieChart.setDescription(null); //不显示描述
        mTarPieChart.setHoleRadius(70f); //洞的大小
        mTarPieChart.setTransparentCircleRadius(20f); //效果的大小
        mTarPieChart.setDrawCenterText(false);//中心的文字也不要写了
        mTarPieChart.setRotationAngle(108); //显示的角度 90+ X% * 360
        Legend l = mTarPieChart.getLegend();
        l.setEnabled(false);//对pie 块的描述也不要显示
    }

    @Override
    protected void requestData() {
        mTarPieChart.setData(getPieData(70, 30, getResources().getColor(R.color.color_ff7000)));
        mTarPieChart.highlightValues(null);
        mTarPieChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
        mTarPieChart.invalidate();
    }

    /**
     * 组合pie chart 的数据
     *
     * @param completeNum   完成的
     * @param remainNum     剩下的
     * @param completeColor 颜色
     * @return
     */
    private PieData getPieData(int completeNum, int remainNum, int completeColor) {
        //X轴数据
        ArrayList<String> xValues = new ArrayList<String>();
        xValues.add("1");
        xValues.add("2");
        xValues.add("3");

        //Y轴数据 -- start ************************************* //
        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();
        yValues.add(new PieEntry(completeNum, 0));
        yValues.add(new PieEntry(remainNum, 1));
        yValues.add(new PieEntry((completeNum + remainNum) / 9f, 2));
        PieDataSet dataSet = new PieDataSet(yValues, "Election Results");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(5f);
        dataSet.setDrawValues(false);
        //Y轴数据 -- end ************************************* //

        //颜色值
        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(completeColor);
        colors.add(0xffe9e9e9);
        colors.add(0xffffffff);
        dataSet.setColors(colors);

        //设置数据开始画
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        return data;
    }

    @OnClick({R.id.line_chart_demo, R.id.bar_chart_demo, R.id.horizontal_bar_chart_demo, R.id.pie_chart_demo,
            R.id.line_chart, R.id.bar_chart, R.id.horizontal_bar_chart, R.id.pie_chart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line_chart_demo:
                jumptoActivity(LineChartDemoActivity.class);
                break;
            case R.id.bar_chart_demo:
                jumptoActivity(BarChartDemoActivity.class);
                break;
            case R.id.horizontal_bar_chart_demo:
                jumptoActivity(HorBarChartDemoActivity.class);
                break;
            case R.id.pie_chart_demo:
                jumptoActivity(PieChartDemoActivity.class);
                break;
            case R.id.line_chart:
                break;
            case R.id.bar_chart:
                break;
            case R.id.horizontal_bar_chart:
                break;
            case R.id.pie_chart:
                break;
        }
    }

    private void jumptoActivity(Class<?> _cls) {
        Intent intent = new Intent();
        intent.setClass(this, _cls);
        startActivity(intent);
    }
}
