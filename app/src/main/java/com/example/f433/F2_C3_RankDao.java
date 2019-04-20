package com.example.f433;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

/*
 * 这是一个Dao类，在JavaWeb中Dao就是一个专门用来连接数据库的类，但是Android和JavaWeb有点不一样，
 * Android如果想连接外部已经创建好的数据库的话，需要先copy再paste，我这里copy paste的代码放在了
 * DBHelper类中，也就是说“连接”数据库的任务已经在DBHelper中完成了，这里的Dao实际上就是对数据库的
 * 一些具体操作，比如我们的项目主要就是select。
 * */
public class F2_C3_RankDao {

    private DBHelper helper;
    private SQLiteDatabase db;

    public F2_C3_RankDao(Context context) {
        helper = new DBHelper(context);
        //update();
    }

    /*public void saveImage(Context context) {

        db = helper.getWritableDatabase();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo_liv);
        bmp.compress(Bitmap.CompressFormat.PNG, 1, out);
        Object[] args = new Object[]{out.toByteArray()};
        db.execSQL("UPDATE league SET logo = ? where rank = 2", args);

        ContentValues cv = new ContentValues();
        cv.put("rank", 1);
        cv.put("logo", out.toByteArray());
        cv.put("name", "test");
        cv.put("turn",33);
        cv.put("num1",3);
        cv.put("num2",3);
        cv.put("num3",3);
        cv.put("rate",3);
        cv.put("point","test");
        db.replace("league", null, cv);
        //db.delete("league","rank=1",null);
    }

    public void update() {
        db = helper.getWritableDatabase();
        db.execSQL("update league set name='曼城', turn=13,num1=11,num2=2,num3=0,rate='40/5',point=35");
    }*/

    /**
     * 从本地数据库获取数据
     *
     * @return
     */
    public ArrayList<F2_C3_RankBean> getRankList() {

        ArrayList<F2_C3_RankBean> rankList = new ArrayList<F2_C3_RankBean>();

        try {

            //  通过调用getWritableDatabase方法获取一个可以读写数据库的对象
            db = helper.getWritableDatabase();
            //  游标，类似JavaWeb中的resultset
            Cursor cursor = db.rawQuery("select * from league", null);

            //  查询之前要先判断数据库中是否有数据
            if (cursor.moveToFirst()) {
                do {

                    //  实例化一个bean，也就是VO
                    F2_C3_RankBean bean = new F2_C3_RankBean();

                    String rank = cursor.getString(cursor.getColumnIndex("rank"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String turn = cursor.getString(cursor.getColumnIndex("turn"));
                    String num1 = cursor.getString(cursor.getColumnIndex("num1"));
                    String num2 = cursor.getString(cursor.getColumnIndex("num2"));
                    String num3 = cursor.getString(cursor.getColumnIndex("num3"));
                    String rate = cursor.getString(cursor.getColumnIndex("rate"));
                    String point = cursor.getString(cursor.getColumnIndex("point"));

                    bean.setRank(rank);
                    // logo
                    byte[] img = cursor.getBlob(cursor.getColumnIndex("logo"));
                    ByteArrayInputStream bin = new ByteArrayInputStream(img);
                    bean.setLogo(Drawable.createFromStream(bin, "img"));
                    bean.setName(name);
                    bean.setTurn(turn);
                    bean.setNum1(num1);
                    bean.setNum2(num2);
                    bean.setNum3(num3);
                    bean.setRate(rate);
                    bean.setPoints(point);

                    rankList.add(bean);

                    //这句话是调试用的，把select到的信息输出到Logcat
                    //System.out.println(rank + "-" + name);
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != db) {
                db.close();
            }
        }
        return rankList;
    }
}