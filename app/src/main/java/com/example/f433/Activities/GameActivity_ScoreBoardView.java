package com.example.f433.Activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.f433.R;

public class GameActivity_ScoreBoardView extends View {
    private Context context;
    /*弧的颜色*/
    private int mColor;
    /*积分数，胜场数，平局数，负场数*/
    private int mScore, mWinNumber, mDrawNumber, mLoseNumber;
    /*传入数字的最大值*/
    private final int FULL_SCORE = 38;
    /*动画插值器*/
    DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
    /*动画持续时间（刷新次数）*/
    private int mDuration = 10;
    /*动画刷新过程中的当前值*/
    private int mCurrentTime = 0;
    private TypedValue typedValue;
    private TypedValue typedValue1;
    private Handler mHandler = new Handler();
    private Runnable mAnimation = new Runnable() {
        @Override
        public void run() {
            if (mCurrentTime < mDuration) {
                mCurrentTime++;
                /*导致重绘调用onDraw，onDraw最后再次postDelay执行此动画，直到达到指定的次数*/
                GameActivity_ScoreBoardView.this.invalidate();
            }
        }
    };
    /*绘制图形*/
    private Paint paint = new Paint();
    /*绘制文字*/
    private Paint paintText = new Paint();

    public GameActivity_ScoreBoardView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public GameActivity_ScoreBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public GameActivity_ScoreBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        /*数据初始化，默认属性*/
        mColor = Color.rgb(166, 215, 67);
        mScore = 0;
        mWinNumber = 0;
        mDrawNumber = 0;
        mLoseNumber = 0;
        typedValue = new TypedValue();
        typedValue1 = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.textAppearanceBody1, typedValue, true);
        context.getTheme().resolveAttribute(R.attr.textAppearanceBody2, typedValue1, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*获取控件总的宽高*/
        float totalWidth = getWidth();
        float totalHeight = getHeight();
        /*
         * DecelerateInterpolator：动画从开始到结束，变化率是一个减速的过程。
         * AccelerateInterpolator：动画从开始到结束，变化率是一个加速的过程。
         * CycleInterpolator：动画从开始到结束，变化率是循环给定次数的正弦曲线
         * AccelerateDecelerateInterpolator：动画从开始到结束，变化率是先加速后减速的过程。
         * LinearInterpolator：动画从开始到结束，变化率是线性变化。
         * */
        /*计算当前时刻动画进度值*/
        float AnimCurrentValue = mDecelerateInterpolator.getInterpolation(1.0f * mCurrentTime / mDuration);

        /*图形画笔设置*/
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        /*积分数，上边的大圆*/
        paint.setStrokeWidth(6);
        paint.setColor(mColor);
        /*积分大圆的中心坐标和半径*/
        float score_radius = totalHeight * 1 / 5, score_circle_x = totalWidth / 2, score_circle_y = totalHeight / 3;
        /*绘制圆弧*/
        canvas.drawCircle(score_circle_x, score_circle_y, score_radius, paint);
        /*文字画笔基本设置*/
        paintText.setAntiAlias(true);
        paintText.setStyle(Paint.Style.STROKE);
        /*文字从中间开始绘制*/
        /*Paint.Align.CENTER：The text is drawn centered horizontally on the x,y origin*/
        paintText.setTextAlign(Paint.Align.CENTER);
        /*文字画笔大小和颜色设置*/
        paintText.setTextSize(score_radius * 3 / 4);
        paintText.setColor(Color.rgb(255, 255, 255));
        /*圆心位置绘制积分数值*/
        canvas.drawText("" + mScore, score_circle_x, score_circle_y, paintText);
        /*缩小字体绘制文本信息*/
        paintText.setTextSize(score_radius * 1 / 4);
        paintText.setAlpha(80);
        /*圆心下边绘制文本*/
        canvas.drawText("积分", score_circle_x, score_circle_y + score_radius / 2, paintText);

        /*设置画笔，画下边的三个小圆*/
        paint.setStrokeWidth(6);
        paint.setAlpha(255);
        /*下边三个小圆的半径*/
        float small_radius = totalHeight / 10;
        /*三个小圆的圆心的x坐标*/
        float[] circleXs = new float[]{totalWidth / 2 - score_radius * 3 / 2,
                totalWidth / 2,
                totalWidth / 2 + score_radius * 3 / 2};
        /*三个小圆的圆心的y坐标*/
        float circleY = totalHeight * 3 / 4;
        /*计算三个小圆弧扫过的角度*/
        float[] theta_values = new float[]{360 * mWinNumber / FULL_SCORE * AnimCurrentValue,
                360 * mDrawNumber / FULL_SCORE * AnimCurrentValue,
                360 * mLoseNumber / FULL_SCORE * AnimCurrentValue};
        /*设置画笔颜色，绘制外围圆环*/
        paint.setColor(Color.rgb(0, 0, 0));
        /*分别绘制三个外围圆环*/
        canvas.drawCircle(circleXs[0], circleY, small_radius, paint);//画WIN背景圆
        canvas.drawCircle(circleXs[1], circleY, small_radius, paint);//画DRAW背景圆
        canvas.drawCircle(circleXs[2], circleY, small_radius, paint);//画LOSE背景圆
        /*更改画笔颜色，绘制圆弧进度条*/
        paint.setColor(mColor);
        /*drawArc的起始角度是3点钟方向，因此要从12点钟方向开始绘制，起始角度为270度*/
        canvas.drawArc(new RectF(circleXs[0] - small_radius,
                        circleY - small_radius,
                        circleXs[0] + small_radius,
                        circleY + small_radius),
                270, theta_values[0], false, paint);//画WIN圆形进度条
        canvas.drawArc(new RectF(circleXs[1] - small_radius,
                        circleY - small_radius,
                        circleXs[1] + small_radius,
                        circleY + small_radius),
                270, theta_values[1], false, paint);//画DRAW圆形进度条
        canvas.drawArc(new RectF(circleXs[2] - small_radius,
                        circleY - small_radius,
                        circleXs[2] + small_radius,
                        circleY + small_radius),
                270, theta_values[2], false, paint);//画LOSE圆形进度条
        /*绘制圆弧结束处的小圆点，实心圆*/
        paint.setStyle(Paint.Style.FILL);
        /*已知半径、圆心位置、便宜角度，根据三角函数很方便计算出小实心圆圆心坐标*/
        canvas.drawCircle(circleXs[0] + small_radius * (float) Math.sin(theta_values[0] * Math.PI / 180),
                circleY - small_radius * (float) Math.cos(theta_values[0] * Math.PI / 180), 6, paint);//画WIN末尾小圆点
        canvas.drawCircle(circleXs[1] + small_radius * (float) Math.sin(theta_values[1] * Math.PI / 180),
                circleY - small_radius * (float) Math.cos(theta_values[1] * Math.PI / 180), 6, paint);//画DRAW末尾小圆点
        canvas.drawCircle(circleXs[2] + small_radius * (float) Math.sin(theta_values[2] * Math.PI / 180),
                circleY - small_radius * (float) Math.cos(theta_values[2] * Math.PI / 180), 6, paint);//画LOSE末尾小圆点

        /*绘制文字*/
        paintText.setColor(Color.rgb(255, 255, 255));
        paintText.setTextSize(small_radius * 2 / 3);
        /*测量文字大小，确定绘制文字时垂直方向的位置*/
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float textBaseLineOffset = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        /*在三个小圆的正中心位置绘制相应的数字*/
        canvas.drawText("" + (int) (mWinNumber * AnimCurrentValue), circleXs[0], circleY + textBaseLineOffset, paintText);
        canvas.drawText("" + (int) (mDrawNumber * AnimCurrentValue), circleXs[1], circleY + textBaseLineOffset, paintText);
        canvas.drawText("" + (int) (mLoseNumber * AnimCurrentValue), circleXs[2], circleY + textBaseLineOffset, paintText);
        /*调整字体大小，绘制文本信息*/
        paintText.setTextSize(small_radius * 4 / 9);
        canvas.drawText("胜场", circleXs[0], circleY - small_radius * 4 / 3, paintText);
        canvas.drawText("平局", circleXs[1], circleY - small_radius * 4 / 3, paintText);
        canvas.drawText("负场", circleXs[2], circleY - small_radius * 4 / 3, paintText);
        /*20ms刷新一次数据*/
        mHandler.postDelayed(mAnimation, 20);//启动动画
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
        invalidate();
    }

    public void setScore(int score) {
        this.mScore = score;
        invalidate();
    }

    public void setWinDrawLose(int win, int draw, int lose) {
        this.mWinNumber = win;
        this.mDrawNumber = draw;
        this.mLoseNumber = lose;
        mCurrentTime = 0;
        invalidate();
    }
}