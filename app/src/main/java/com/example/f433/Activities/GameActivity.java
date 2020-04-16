package com.example.f433.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.f433.R;
import com.example.f433.Util.StatusBarUtil;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameActivity extends AppCompatActivity {

    private PieChart pie_chart;
    private RadarChart radar_chart;
    private LineChart line_chart;
    private TextView score_bar_text;
    private GameActivity_ScoreBar score_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initView();

    }

    private void initView() {
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
        // 主队积分
        final GameActivity_ScoreBoardView myView = (GameActivity_ScoreBoardView) findViewById(R.id.custom_view);
        myView.setColor(Color.rgb(0, 204, 0));
        myView.setScore(34);
        myView.setWinDrawLose(10, 4, 5);
        // 客队积分
        final GameActivity_ScoreBoardView myView2 = (GameActivity_ScoreBoardView) findViewById(R.id.custom_view2);
        myView2.setColor(Color.rgb(102, 204, 255));
        myView2.setScore(31);
        myView2.setWinDrawLose(8, 7, 4);
        // 主客队联赛中排名
        final GameActivity_RankBar bar = (GameActivity_RankBar) findViewById(R.id.rank_bar);
        bar.setRanks(1, 1);
        bar.setRanks(6, 10);
        bar.setColor(Color.rgb(0, 204, 0), Color.rgb(104, 204, 255));

        // 点击动画
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


        /*
         * 饼图绘制
         */
        //模拟数据
        HashMap dataMap = new HashMap();
        dataMap.put("主胜", "40");
        dataMap.put("客胜", "50");
        dataMap.put("平局", "10");

        pie_chart = (PieChart) findViewById(R.id.pie_chart);
        setPieChart(pie_chart, dataMap, "Predict", true);


        /*
         * 雷达图绘制
         */
        radar_chart = (RadarChart) findViewById(R.id.radar_chart);
        setRadarChart(radar_chart);


        /*
         * 折线图绘制
         */
        line_chart = (LineChart) findViewById(R.id.line_chart);
        setLineChart(line_chart);


        /*
         * 对比条文本
         */
        score_bar_text = (TextView) findViewById(R.id.score_bar_text);
        score_bar_text.setText("射正数");
        score_bar_text.setTextColor(Color.WHITE);


        /*
         * 对比条绘制
         */
        score_bar = (GameActivity_ScoreBar) findViewById(R.id.score_bar);
        score_bar.setScores(5, 8); //设置数据
        score_bar.setBarColor(Color.rgb(0, 204, 0), Color.rgb(187, 255, 250));

    }

    //设置饼图属性
    public void setPieChart(PieChart pieChart, Map<String, Float> pieValues, String title, boolean showLegend) {
        pieChart.setUsePercentValues(true);//设置使用百分比（后续有详细介绍）
        //pieChart.setExtraOffsets(5, 5, 5, 5); //设置边距
        pieChart.setHoleRadius(55);//将饼图中心的孔的半径设置为最大半径的百分比
        pieChart.setDragDecelerationFrictionCoef(0.95f);//设置摩擦系数（值越小摩擦系数越大）
        pieChart.setCenterText(title);//设置环中的文字
        pieChart.setRotationEnabled(true);//是否可以旋转
        pieChart.setHighlightPerTapEnabled(true);//点击是否放大
        pieChart.setCenterTextSize(18f);//设置环中文字的大小
        pieChart.setCenterTextColor(Color.WHITE);//设置环中文字的颜色
        pieChart.setDrawCenterText(true);//设置绘制环中文字
        pieChart.setRotationAngle(120f);//设置旋转角度
        pieChart.setTransparentCircleRadius(63);//设置半透明圆环的半径,看着就有一种立体的感觉
        //这个方法为true就是环形图，为false就是饼图
        pieChart.setDrawHoleEnabled(true);
        //设置环形中间空白颜色
        pieChart.setHoleColor(Color.argb(85, 255, 255, 255));
        //设置半透明圆环的颜色
        pieChart.setTransparentCircleColor(Color.WHITE);
        //设置半透明圆环的透明度
        pieChart.setTransparentCircleAlpha(130);
        //设置图标背景
        //pieChart.setBackgroundColor(Color.rgb(0,0,0));
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

        ArrayList<PieEntry> pie_entries = new ArrayList<PieEntry>();

        //设置饼图各区块的颜色
        final int[] PIE_COLORS = {
                Color.rgb(169, 255, 169),
                Color.rgb(255, 229, 172),
                Color.rgb(187, 255, 255)
        };

        Set set = pieValues.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            pie_entries.add(new PieEntry(Float.valueOf(entry.getValue().toString()), entry.getKey().toString()));
        }

        PieDataSet dataSet = new PieDataSet(pie_entries, "");
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
        pieData.setValueTextSize(13f);
        pieData.setValueTextColor(Color.rgb(238, 90, 255));
        pieChart.setData(pieData);
        pieChart.setEntryLabelColor(Color.rgb(238, 90, 255));
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }

    //设置雷达图属性
    public void setRadarChart(RadarChart chart) {

        //设置描述
        chart.setDescription(null);

        //网格设置
        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        //动画设置
        chart.animateXY(1400, 1400, Easing.EasingOption.EaseInOutQuad, Easing.EasingOption.EaseInOutQuad);

        //x轴--外围文字设置
        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(12f);
        //xAxis.setYOffset(10f);
        //xAxis.setXOffset(10f);
        xAxis.setValueFormatter(new AxisValueFormatter() {
            private final String[] mActivities = new String[]{"进攻", "技巧", "体能", "防守", "对抗", "速度"};

            @Override
            public String getFormattedValue(float value, AxisBase axisBase) {
                return mActivities[(int) value % mActivities.length];
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        xAxis.setTextColor(Color.WHITE);

        //y轴--内部标签设置
        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(10f);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setStartAtZero(true); // Y坐标值是否从0开始
        yAxis.setDrawLabels(false); // 是否显示y值在图表上

        //图例设置
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(10f); // 图例X间距
        l.setYEntrySpace(5f); // 图例Y间距
        l.setTextSize(14f);
        l.setTextColor(Color.WHITE);

        setRadarChartData(chart);
    }

    //设置雷达图数据
    private void setRadarChartData(RadarChart chart) {

        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        //主队数据
        entries1.add(new RadarEntry(80));
        entries1.add(new RadarEntry(90));
        entries1.add(new RadarEntry(60));
        entries1.add(new RadarEntry(70));
        entries1.add(new RadarEntry(50));
        entries1.add(new RadarEntry(50));
        //客队数据
        entries2.add(new RadarEntry(90));
        entries2.add(new RadarEntry(70));
        entries2.add(new RadarEntry(50));
        entries2.add(new RadarEntry(60));
        entries2.add(new RadarEntry(80));
        entries2.add(new RadarEntry(80));
        //主队图案绘制
        RadarDataSet set1 = new RadarDataSet(entries1, "主队");
        set1.setColor(Color.rgb(187, 255, 250));
        set1.setFillColor(Color.rgb(187, 255, 250));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        //客队图案绘制
        RadarDataSet set2 = new RadarDataSet(entries2, "客队");
        set2.setColor(Color.rgb(255, 155, 205));
        set2.setFillColor(Color.rgb(255, 155, 205));
        set2.setDrawFilled(true);
        set2.setFillAlpha(120);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        //图案应用
        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        //雷达图数据集
        RadarData data = new RadarData(sets);
        //不显示数据
        data.setDrawValues(false);
        data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.valueOf((int) value);
            }
        });
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();
    }

    //设置折线图属性
    public void setLineChart(LineChart chart) {

        //设置描述
        chart.setDescription(null);
        //设置图例开关
        chart.getLegend().setEnabled(true);
        //设置显示范围
        chart.setVisibleXRangeMaximum(3);
        chart.setVisibleYRangeMinimum(10f, YAxis.AxisDependency.LEFT);
        //设置透明度
        chart.setAlpha(1.0f);
        //设置背景色
        //chart.setBackgroundColor(Color.WHITE);
        //设置边框
        //chart.setBorderColor(Color.rgb(0, 0, 0));
        //chart.setGridBackgroundColor(R.color.colorPrimary);
        //设置触摸(关闭影响下面3个属性)
        chart.setTouchEnabled(true);
        //设置是否可以拖拽
        chart.setDragEnabled(true);
        //设置是否可以缩放
        chart.setScaleEnabled(true);
        //设置是否能扩大扩小
        chart.setPinchZoom(true);

        //设置XY轴进入动画
        chart.animateXY(800, 800);
        //设置最小的缩放
        chart.setScaleMinima(1f, 1f);

        //设置图例属性
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(10f); // 图例X间距
        l.setYEntrySpace(5f); // 图例Y间距
        l.setTextSize(14f);
        l.setTextColor(Color.WHITE);

        //获取X轴
        XAxis xl = chart.getXAxis();
        //设置x轴参数
        xl.setValueFormatter(new AxisValueFormatter() {
            private final String[] mAxis = new String[]{"0-15", "16-30", "31-45", "46-60", "61-75", "76-90+"};

            @Override
            public String getFormattedValue(float value, AxisBase axisBase) {
                return mAxis[(int) value % mAxis.length];
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        //启用X轴
        xl.setEnabled(true);
        //设置X轴避免图表或屏幕的边缘的第一个和最后一个轴中的标签条目被裁剪
        xl.setAvoidFirstLastClipping(true);
        //设置X轴底部显示
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置竖网格
        xl.setDrawGridLines(true);
        //设置X轴文字大小
        xl.setTextSize(12f);
        //设置X轴文字颜色
        xl.setTextColor(Color.WHITE);
        //设置X轴单位间隔
        xl.setGranularity(1f);


        //获取Y轴(左)
        YAxis yl = chart.getAxisLeft();
        //此表不需要Y轴
        yl.setEnabled(false);
        //设置Y轴文字在外部显示
        yl.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置横网格
        yl.setDrawGridLines(true);
        //Y轴字体
        yl.setTextSize(12f);
        //Y轴字体颜色
        yl.setTextColor(Color.WHITE);
        //设置Y轴最大值
        yl.setAxisMaxValue(100f);
        //设置Y轴起始值
        yl.setAxisMinValue(0f);

        //获取Y轴(右)
        YAxis ylR = chart.getAxisRight();
        //禁用右侧Y轴
        ylR.setEnabled(false);

        setLineChartData(chart);
    }

    //设置折线图数据
    private void setLineChartData(LineChart chart) {

        //主队数据
        ArrayList<Entry> list_home = new ArrayList<>();
        list_home.add(new Entry(0, 70));
        list_home.add(new Entry(1, 40));
        list_home.add(new Entry(2, 55));
        list_home.add(new Entry(3, 12));
        list_home.add(new Entry(4, 65));
        list_home.add(new Entry(5, 75));

        LineDataSet lHome = new LineDataSet(list_home, "主队");
        lHome.setAxisDependency(YAxis.AxisDependency.LEFT);
        //设置包括的范围区域填充颜色
        lHome.setDrawFilled(false);
        //设置线的宽度
        lHome.setLineWidth(2f);
        //设置曲线的颜色
        lHome.setColor(Color.rgb(140, 210, 118));
        //设置曲率,0.05f-1f  1为折线
        lHome.setCubicIntensity(1f);
        //设置有圆点
        lHome.setDrawCircles(true);
        //设置小圆点的大小
        lHome.setCircleRadius(4f);
        //设置圆圈颜色
        lHome.setCircleColor(Color.rgb(140, 210, 118));
        //填充圆圈内颜色
        //lHome.setCircleColorHole(Color.rgb(140, 210, 118));

        //客队数据
        ArrayList<Entry> list_away = new ArrayList<>();
        list_away.add(new Entry(0, 40));
        list_away.add(new Entry(1, 70));
        list_away.add(new Entry(2, 65));
        list_away.add(new Entry(3, 80));
        list_away.add(new Entry(4, 75));
        list_away.add(new Entry(5, 85));

        LineDataSet lAway = new LineDataSet(list_away, "客队");
        lAway.setAxisDependency(YAxis.AxisDependency.LEFT);
        //设置包括的范围区域填充颜色
        lAway.setDrawFilled(false);
        //设置线的宽度
        lAway.setLineWidth(2f);
        //设置曲线的颜色
        lAway.setColor(Color.rgb(159, 143, 186));
        //设置曲率,0.05f-1f  1为折线
        lAway.setCubicIntensity(1f);
        //设置有圆点
        lAway.setDrawCircles(true);
        //设置小圆点的大小
        lAway.setCircleRadius(4f);
        //设置圆圈颜色
        lAway.setCircleColor(Color.rgb(159, 143, 186));
        //填充圆圈内颜色
        //lAway.setCircleColorHole(Color.rgb(159, 143, 186));

        //添加数据进入数据集合
        List<ILineDataSet> lineDataSetArrayList = new ArrayList<>();
        lineDataSetArrayList.add(lHome);
        lineDataSetArrayList.add(lAway);

        LineData lineData = new LineData(lineDataSetArrayList);
        //设置显示数值
        lineData.setDrawValues(true);
        //设置数值格式
        lineData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value + "%";
            }
        });
        lineData.setValueTextColor(Color.WHITE);
        lineData.setValueTextSize(10f);

        chart.setData(lineData);

        //刷新图表
        chart.invalidate();//List<Model> newData = new ArrayList<>();
    }

}