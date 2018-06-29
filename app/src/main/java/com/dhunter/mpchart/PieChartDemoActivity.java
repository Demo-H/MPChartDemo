package com.dhunter.mpchart;

import android.graphics.Color;
import android.view.ViewGroup;

import com.dhunter.mpchart.MpAndroidChart.MvPieChart;
import com.dhunter.mpchart.utils.ScreenUtil;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by dhunter on 2018/6/28.
 */

public class PieChartDemoActivity extends BaseActivity {

    @BindView(R.id.pieChart)
    MvPieChart mPieChart;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_piechart_demo;
    }

    @Override
    protected void initLayout() {
        // 饼状图初始化
        ViewGroup.LayoutParams lp = mPieChart.getLayoutParams();
        lp.width = ScreenUtil.getWidth(this);
        lp.height = ScreenUtil.getWidth(this);
        mPieChart.setLayoutParams(lp);

        mPieChart.setUsePercentValues(true);
        mPieChart.setBackgroundColor(Color.WHITE);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(30, 30, 30, 30);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);

        mPieChart.setDrawHoleEnabled(false);
        mPieChart.setDrawCenterText(false);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

    }

    @Override
    protected void requestData() {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(new Random().nextInt(100), "粉丝"));
        entries.add(new PieEntry(new Random().nextInt(100), "普通会员"));
        entries.add(new PieEntry(new Random().nextInt(100), "人民币玩家"));
        entries.add(new PieEntry(new Random().nextInt(100), "高级会员"));
        entries.add(new PieEntry(new Random().nextInt(100), "土豪"));
        entries.add(new PieEntry(new Random().nextInt(100), "特级VIP"));
        //设置数据
        setData(entries);

        mPieChart.animateX(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setEnabled(false);

        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.BLACK);
        mPieChart.setEntryLabelTextSize(12f);
    }

    //设置数据
    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "SingleVipPrecent");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setValueLinePart1Length(0.7f);
        dataSet.setValueLinePart2Length(0.9f);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColors(colors);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }

}
