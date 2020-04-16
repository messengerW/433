package com.example.f433.Util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.view.WindowManager;

/*
 * File: StatusBarUtil.java
 * Date: 2019/10/5-0:04
 * Author: msw.
 * PS 比葫芦画瓢，我的第一个轮子，用来设置顶部状态栏颜色，根据背景色深浅调换字体颜色。不断完善
 *    中。。。
 */
public class StatusBarUtil {


    /*  一个参数的方法，适用于带有 toolbar 的 page
     * 根据顶部toolbar颜色，为状态栏设置渐变色
     *
     */
    public static void setStatusBar(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


    /* 两个参数的方法，适用于顶部没有 toolbar 的 page
     * 根据顶部颜色设置状态栏底色、文字颜色。
     * @param color
     * @param activity
     * 有待改进，传过来的这个color默认是白色，据此设置的状态栏颜色只能是几种固定的颜色，纯白、纯黑、纯黄色等
     */
    public static void setStatusBar(Activity activity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 设置状态栏底色颜色
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(color);

            // 如果亮色，设置状态栏文字为黑色
            if (isLightColor(color)) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }

    }

    /**
     * 判断颜色是不是亮色
     *
     * @param color
     * @return
     * @from https://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
     */
    public static boolean isLightColor(@ColorInt int color) {
        return ColorUtils.calculateLuminance(color) >= 0.5;
    }
}

