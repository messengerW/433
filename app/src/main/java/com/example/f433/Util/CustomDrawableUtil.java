package com.example.f433.Util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.TypedValue;

public class CustomDrawableUtil extends Drawable {

    private Bitmap bitmap;

    private BitmapShader bitmapShader;

    private Paint paint;

    // 圆心
    private float cx, cy;

    // 半径
    private float radius;

    /**
     * Drawable 转 Bitmap，下面好多自定义绘图都要用这个方法。
     * */
    private static Bitmap DrawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    /*
    *                   *** 第一种绘图 ***
    * 这个类的构造方法，绘制圆形头像按钮，应用在 toolbar 的左侧
    * 这个是博客上看来的，然后我比较笨比，不知道怎么改成一个 static 方法，先这样用着8 ^.^ */
    public CustomDrawableUtil(Drawable drawable, Context context, int size) {
        size = dip2px(context, size);
        drawable = zoomDrawable(drawable, dip2px(context, size), dip2px(context, size));
        this.bitmap = DrawableToBitmap(drawable);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);

        cx = size / 2;
        cy = size / 2;
        radius = size / 2;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, paint);
    }

    /**
     * 缩放Drawable
     * */
    private Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = DrawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }

    /**
     * dp to px
     * */
    private int dip2px(Context context, float dipValue) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, resources.getDisplayMetrics());
    }

    public static int dip2px1(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int dip2px2(Context context,float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    /* 第一种绘图结束。鬼知道他为什么要用这么多函数 **/

    /** 第二种绘图，作用也是绘制一个圆形头像按钮，使用的地方是 card 里面的一个图像按钮。
     ** @param context 这个参数是Fragment中getActivity()传给Adapter，然后Adapter在传过来，没有这个
     *                 参数就没办法调用 getResources()
     *  @param drawable 这个参数就是改造前的 drawable，是数据库操作类 Dao 从数据库中读取出的图片文件，
     *                  然后在 adapter 中获取到后传过来
     **/

    public static RoundedBitmapDrawable getRoundedBitmapDrawable(Context context, Drawable drawable){
        Bitmap image = DrawableToBitmap(drawable);
        Bitmap bitmap;
        //将长方形图片裁剪成正方形图片
        if (image.getWidth() == image.getHeight()) {
            bitmap = Bitmap.createBitmap(image, image.getWidth() / 2 - image.getHeight() / 2, 0, image.getHeight(), image.getHeight());
        } else {
            bitmap = Bitmap.createBitmap(image, 0, image.getHeight() / 2 - image.getWidth() / 2, image.getWidth(), image.getWidth());
        }
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        //圆角半径为正方形边长的一半
        roundedBitmapDrawable.setCornerRadius(bitmap.getWidth() / 2);
        //抗锯齿
        roundedBitmapDrawable.setAntiAlias(true);
        return roundedBitmapDrawable;
    }
    /* 第二种绘图结束 **/
}