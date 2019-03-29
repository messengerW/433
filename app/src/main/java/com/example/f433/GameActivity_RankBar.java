package com.example.f433;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class GameActivity_RankBar extends View {
    private Context context;
    /*左右bar的颜色*/
    private int mColorLeft, mColorRight;
    /*左右bar的数值*/
    private int mRankLeft, mRankRight;
    private TypedValue typedValue;
    /*画图形的画笔*/
    private Paint paintDraw = new Paint();
    /*文字画笔*/
    private Paint paintText = new Paint();
    private Path path = new Path();
    /*等分*/
    private final int HEIGHT_DEGREE = 40, WIDTH_DEGREES = 31;

    public GameActivity_RankBar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public GameActivity_RankBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public GameActivity_RankBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init() {
        /*数据初始化，没有设置数据时候的默认数据*/
        mColorLeft = Color.rgb(166, 215, 67);
        mColorRight = Color.rgb(166, 215, 67);
        mRankLeft = 1;
        mRankRight = 1;
        typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.textAppearanceBody1, typedValue, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float totalWidth = getWidth();
        float totalHeight = getHeight();

        paintDraw.reset();
        paintDraw.setAntiAlias(true);
        paintText.reset();
        paintText.setAntiAlias(true);

        /*把总宽度平均分为WIDTH_DEGREES份，中间短横线的长度占一份*/
        float lineLength = totalWidth / WIDTH_DEGREES;
        /*中间一列一共有30个短横线刻度，记录每个短横线刻度的垂直方向的坐标位置，方便绘制*/
        float[] lineYs = new float[30];
        for (int i = HEIGHT_DEGREE / 2 - 14; i <= HEIGHT_DEGREE / 2 + 15; i++) {
            lineYs[i - (HEIGHT_DEGREE / 2 - 14)] = totalHeight * i / HEIGHT_DEGREE;
        }

        /*设置画中间水平线的画笔*/
        paintDraw.setColor(Color.rgb(204, 204, 204));
        paintDraw.setAlpha(50);
        /*设置画笔的宽度*/
        paintDraw.setStrokeWidth(totalHeight / HEIGHT_DEGREE / 3);
        for (int i = 0; i < lineYs.length; i++) {
            /*循环绘制中间的短横线刻度*/
            canvas.drawLine(totalWidth / 2 - lineLength, lineYs[i], totalWidth / 2 + lineLength, lineYs[i], paintDraw);
        }

        /*气泡的半径*/
        float radius = totalHeight * 2.5f / HEIGHT_DEGREE;

        /*绘制左边标签*/
        path.reset();
        /*moveTo：Set the beginning of the next contour to the point (x,y).*/
        /*计算等边三角形右边顶点的坐标rr*/
        float triangleRightVertexX = totalWidth / 2 - lineLength / 2;
        float triangleRightVertexY = lineYs[mRankLeft - 1];
        /*路径起点移动到等边三角形右边顶点rr，以此顶点rr画线*/
        path.moveTo(triangleRightVertexX, triangleRightVertexY);
        /*计算等边三角形的边长，等边三角形上下两个顶点和圆相交于tt,bb,tt、bb与圆心夹角30度*/
        float triangleLength = radius * (float) Math.sin(15 * 2 * Math.PI / 360) * 2;
        /*计算bb坐标，为绘制弧形的起点*/
        float arcStartPointX = triangleRightVertexX - triangleLength * (float) Math.cos(30 * 2 * Math.PI / 360);
        float arcStartPointY = triangleRightVertexY + triangleLength * (float) Math.sin(30 * 2 * Math.PI / 360);
        /*画等边三角形的一条边，rr-bb*/
        path.lineTo(arcStartPointX, arcStartPointY);

        /*arcTo 用于绘制弧线（实际是截取圆或椭圆的一部分）。
        mPath.arcTo(ovalRectF, startAngle, sweepAngle) , ovalRectF为椭圆的矩形，
        startAngle 为开始角度，sweepAngle 为结束角度。*/
        /*利用三角函数计算圆最右边点的坐标，利用该坐标值和半径构建圆的外接正方形然后画圆*/
        float circleRightPointX = arcStartPointX + (radius - radius * (float) Math.cos(15 * 2 * Math.PI / 360));
        float circleRightPointY = triangleRightVertexY;
        /*圆弧路径，起始角度0度在3点钟方向，因此弧形起始角度15度，扫过角度360-30度*/
        path.arcTo(new RectF(circleRightPointX - 2 * radius,
                        circleRightPointY - radius,
                        circleRightPointX,
                        circleRightPointY + radius),
                15, 360 - 30);
        /*闭合路径*/
        path.close();
        paintDraw.setStyle(Paint.Style.FILL);
        paintDraw.setColor(mColorLeft);
        /*将闭合路径绘制在画布上*/
        canvas.drawPath(path, paintDraw);
        /*绘制外边白色边框*/
        paintDraw.setColor(Color.WHITE);
        paintDraw.setAlpha(90);
        paintDraw.setStyle(Paint.Style.STROKE);
        paintDraw.setStrokeWidth(3);
        canvas.drawPath(path, paintDraw);
        /*准备绘制文字*/
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(radius * 3 / 5);
        /*根据数据位数来确定偏移量*/
        float number_offset;
        /*只有一位数字*/
        if (mRankLeft < 10) {
            number_offset = radius / 2;
            /*两位数字*/
        } else {
            number_offset = radius * 3 / 4;
        }
        /*圆心x坐标*/
        float circleX = arcStartPointX - radius;
        /*绘制#号*/
        canvas.drawText("#", circleX - number_offset, lineYs[mRankLeft - 1] + radius / 4, paintText);
        float offset = paintText.measureText("#");
        /*绘制数字*/
        paintText.setTextSize(radius);
        canvas.drawText("" + mRankLeft, circleX - number_offset + offset, lineYs[mRankLeft - 1] + radius / 3, paintText);

        /*绘制右边部分*/
        path.reset();
        /*三角形左边顶点坐标*/
        float triangleLeftVertexX = totalWidth / 2 + lineLength / 2;
        float triangleLeftVertexY = lineYs[mRankRight - 1];
        /*等边三角形左边顶点作为路径起始点*/
        path.moveTo(triangleLeftVertexX, triangleLeftVertexY);
        /*等边三角形上边顶点和圆的焦点作为圆弧路径的起点*/
        arcStartPointX = triangleLeftVertexX + triangleLength * (float) Math.cos(30 * 2 * Math.PI / 360);
        arcStartPointY = triangleLeftVertexY - triangleLength * (float) Math.sin(30 * 2 * Math.PI / 360);
        path.lineTo(arcStartPointX, arcStartPointY);
        /*利用三角函数计算圆最左边点的坐标，利用该坐标值和半径构建圆的外接正方形然后做圆弧路径*/
        float circleLeftPointX = arcStartPointX - (radius - radius * (float) Math.cos(15 * 2 * Math.PI / 360));
        float circleLeftPointY = triangleLeftVertexY;
        /*圆弧路径*/
        path.arcTo(new RectF(circleLeftPointX,
                        circleLeftPointY - radius,
                        circleLeftPointX + 2 * radius,
                        circleLeftPointY + radius),
                180 + 15, 360 - 30);
        /*闭合路径*/
        path.close();
        /*绘制路径*/
        paintDraw.setStyle(Paint.Style.FILL);
        paintDraw.setColor(mColorRight);
        canvas.drawPath(path, paintDraw);
        /*绘制路径外围白色边框*/
        paintDraw.setColor(Color.WHITE);
        paintDraw.setAlpha(90);
        paintDraw.setStyle(Paint.Style.STROKE);
        paintDraw.setStrokeWidth(3);
        canvas.drawPath(path, paintDraw);
        /*开始绘制文字*/
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(radius * 3 / 5);
        if (mRankRight < 10) {
            number_offset = radius / 2;
        } else {
            number_offset = radius * 3 / 4;
        }
        circleX = circleLeftPointX + radius;
        canvas.drawText("#", circleX - number_offset, lineYs[mRankRight - 1] + radius / 4, paintText);
        offset = paintText.measureText("#");
        paintText.setTextSize(radius);
        canvas.drawText("" + mRankRight, circleX - number_offset + offset, lineYs[mRankRight - 1] + radius / 4, paintText);
    }

    public void setRanks(int rank1, int rank2) {
        if (rank1 < 1 || rank1 > 30 || rank2 < 1 || rank2 > 30)
            throw new IllegalArgumentException("排名参数只能设置成1到30的整数");
        this.mRankLeft = rank1;
        this.mRankRight = rank2;
        invalidate();
    }

    public void setColor(int mColorLeft, int mColorRight) {
        this.mColorLeft = mColorLeft;
        this.mColorRight = mColorRight;
        invalidate();
    }

}