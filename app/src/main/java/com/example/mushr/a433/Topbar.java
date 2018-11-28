package com.example.mushr.a433;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Topbar extends RelativeLayout {

    private Button LeftButton;
    private String LeftText;
    private float LeftTextSize;
    private int LeftTextColor;
    private int LeftBackground;

    private Button RightButton;
    private String RightText;
    private float RightTextSize;
    private int RightTextColor;
    private int RightBackground;

    private TextView Title;
    private String TitleText;
    private float TitleTextSize;
    private int TitleTextColor;

    private LayoutParams LeftBtnParams, RightBtnParams, TitleParams;

    public Topbar(Context context, AttributeSet attrs) {

        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.Topbar);

        LeftText = ta.getString(R.styleable.Topbar_LeftText);
        LeftTextSize = ta.getDimension(R.styleable.Topbar_LeftTextSize,0);
        LeftTextColor = ta.getColor(R.styleable.Topbar_LeftTextColor,0);
        LeftBackground = ta.getColor(R.styleable.Topbar_LeftBackground,0);

        RightText = ta.getString(R.styleable.Topbar_RightText);
        RightTextSize = ta.getDimension(R.styleable.Topbar_RightTextSize,0);
        RightTextColor = ta.getColor(R.styleable.Topbar_RightTextColor,0);
        RightBackground = ta.getColor(R.styleable.Topbar_RightBackground,0);

        TitleText = ta.getString(R.styleable.Topbar_TitleText);
        TitleTextColor = ta.getColor(R.styleable.Topbar_TitleTextColor,0);
        TitleTextSize = ta.getDimension(R.styleable.Topbar_TitleTextSize,0);

        ta.recycle();

        LeftButton =  new Button(context);
        RightButton = new Button(context);
        Title = new TextView(context);

        LeftButton.setText(LeftText);
        LeftButton.setTextSize(LeftTextSize);
        LeftButton.setTextColor(LeftTextColor);
        LeftButton.setBackgroundColor(LeftBackground);

        RightButton.setText(RightText);
        RightButton.setTextSize(RightTextSize);
        RightButton.setTextColor(RightTextColor);
        RightButton.setBackgroundColor(RightBackground);

        Title.setText(TitleText);
        Title.setTextColor(TitleTextColor);
        Title.setTextSize(TitleTextSize);
        Title.setGravity(Gravity.CENTER);

        setBackgroundColor(Color.parseColor("#008000"));

        LeftBtnParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        LeftBtnParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(LeftButton,LeftBtnParams);

        RightBtnParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        RightBtnParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(RightButton,RightBtnParams);

        TitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        TitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(Title,TitleParams);

    }

}
