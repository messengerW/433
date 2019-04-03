package com.example.f433;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GameActivity extends AppCompatActivity {

    private PieChart mp_pieChart;
    private ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*
         * 返回按钮
         */
        ImageView imageView = (ImageView) findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        /*
         * 积分情况对照图
         */
        //主队积分
        final GameActivity_ScoreBoardView myView = (GameActivity_ScoreBoardView) findViewById(R.id.custom_view);
        //客队积分
        final GameActivity_ScoreBoardView myView2 = (GameActivity_ScoreBoardView) findViewById(R.id.custom_view2);
        //主客队联赛中排名
        final GameActivity_RankBar bar = (GameActivity_RankBar) findViewById(R.id.rank_bar);
        bar.setRanks(1, 1);
        //点击事件
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setColor(Color.rgb(0, 204, 0));
                myView.setScore(34);
                myView.setWinDrawLose(10, 4, 5);

                myView2.setColor(Color.rgb(102, 204, 255));
                myView2.setScore(31);
                myView2.setWinDrawLose(8, 7, 4);

                bar.setRanks(6, 10);
                bar.setColor(Color.rgb(0, 204, 0), Color.rgb(104, 204, 255));
            }
        });
        myView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setColor(Color.rgb(0, 204, 0));
                myView.setScore(34);
                myView.setWinDrawLose(10, 4, 5);

                myView2.setColor(Color.rgb(104, 204, 255));
                myView2.setScore(31);
                myView2.setWinDrawLose(8, 7, 4);

                bar.setRanks(6, 10);
                bar.setColor(Color.rgb(0, 204, 0), Color.rgb(104, 204, 255));
            }
        });


        /**
         * 饼图绘制
         */
        //模拟数据
        HashMap dataMap = new HashMap();
        dataMap.put("主队胜", "40");
        dataMap.put("客队胜", "50");
        dataMap.put("平局", "10");

        mp_pieChart = (PieChart) findViewById(R.id.pie_chart);
        setPieChart(mp_pieChart, dataMap, "Data Analysis", true);

    }

    //设置各区块的颜色
    public static final int[] PIE_COLORS = {
            Color.rgb(130, 231, 255), Color.rgb(80, 222, 49),
            Color.rgb(252, 200, 87)
    };

    //设置饼形图属性
    public void setPieChart(PieChart pieChart, Map<String, Float> pieValues, String title, boolean showLegend) {
        pieChart.setUsePercentValues(true);//设置使用百分比（后续有详细介绍）
        //pieChart.setExtraOffsets(5, 5, 5, 5); //设置边距
        pieChart.setHoleRadius(50);//将饼图中心的孔的半径设置为最大半径的百分比
        pieChart.setDragDecelerationFrictionCoef(0.95f);//设置摩擦系数（值越小摩擦系数越大）
        pieChart.setCenterText(title);//设置环中的文字
        pieChart.setRotationEnabled(true);//是否可以旋转
        pieChart.setHighlightPerTapEnabled(true);//点击是否放大
        pieChart.setCenterTextSize(20f);//设置环中文字的大小
        pieChart.setCenterTextColor(Color.rgb(51, 51,255));//设置环中文字的颜色
        pieChart.setDrawCenterText(true);//设置绘制环中文字
        pieChart.setRotationAngle(120f);//设置旋转角度
        pieChart.setTransparentCircleRadius(55);//设置半透明圆环的半径,看着就有一种立体的感觉
        //这个方法为true就是环形图，为false就是饼图
        pieChart.setDrawHoleEnabled(true);
        //设置环形中间空白颜色
        pieChart.setHoleColor(Color.argb(230,255,255,255));
        //设置半透明圆环的颜色
        pieChart.setTransparentCircleColor(Color.WHITE);
        //设置半透明圆环的透明度
        pieChart.setTransparentCircleAlpha(130);
        //设置图标背景
        pieChart.setBackgroundColor(Color.rgb(204, 204, 204)); //cw_0
        //设置默认右下角的描述
        pieChart.setDescription(null);

        /*图例设置----此处不需要
        Legend legend = pieChart.getLegend();
        if (showLegend) {
            legend.setEnabled(true);//是否显示图例
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);//图例相对于图表横向的位置
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例相对于图表纵向的位置
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//图例显示的方向
            legend.setDrawInside(false);
            legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        } else {
            legend.setEnabled(false);
        }*/

        //设置饼图数据
        setPieChartData(pieChart, pieValues);

        pieChart.animateX(1500, Easing.EasingOption.EaseInOutQuad);//数据显示动画

    }

    //设置饼图数据
    private void setPieChartData(PieChart pieChart, Map<String, Float> pieValues) {

        Set set = pieValues.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entries.add(new PieEntry(Float.valueOf(entry.getValue().toString()), entry.getKey().toString()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);//设置饼块之间的间隔
        dataSet.setSelectionShift(5f);//设置饼块选中时偏离饼图中心的距离
        dataSet.setColors(PIE_COLORS);//设置饼块的颜色

        //设置数据显示方式有见图
        //dataSet.setValueLinePart1OffsetPercentage(80f);//数据连接线距图形片内部边界的距离，为百分数
        //dataSet.setValueLinePart1Length(0.3f);
        //dataSet.setValueLinePart2Length(0.4f);
        //dataSet.setValueLineColor(Color.YELLOW);//设置连接线的颜色
        dataSet.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(16f);
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }

}