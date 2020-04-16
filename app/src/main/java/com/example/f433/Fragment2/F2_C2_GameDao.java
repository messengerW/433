package com.example.f433.Fragment2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import com.example.f433.Util.DBHelper;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class F2_C2_GameDao {

    private DBHelper helper;
    private SQLiteDatabase db;

    public F2_C2_GameDao(Context context) {
        helper = new DBHelper(context);
    }
    /**
     * 从本地数据库获取数据
     *
     * @return
     */
    public ArrayList<F2_C2_GameBean> getGameList() {

        ArrayList<F2_C2_GameBean> rankList = new ArrayList<F2_C2_GameBean>();

        try {

            //  通过调用getWritableDatabase方法获取一个可以读写数据库的对象
            db = helper.getWritableDatabase();
            //  游标，类似JavaWeb中的resultset
            Cursor cursor = db.rawQuery("select * from game2", null);

            //  查询之前要先判断数据库中是否有数据
            if (cursor.moveToFirst()) {
                do {

                    //  实例化一个bean，也就是VO
                    F2_C2_GameBean bean = new F2_C2_GameBean();

                    String name1 = cursor.getString(cursor.getColumnIndex("name1"));
                    String name2 = cursor.getString(cursor.getColumnIndex("name2"));
                    String date = cursor.getString(cursor.getColumnIndex("date"));
                    String time = cursor.getString(cursor.getColumnIndex("time"));

                    // logo
                    byte[] img1 = cursor.getBlob(cursor.getColumnIndex("logo1"));
                    ByteArrayInputStream bin1 = new ByteArrayInputStream(img1);
                    bean.setLogo1(Drawable.createFromStream(bin1, "img1"));

                    byte[] img2 = cursor.getBlob(cursor.getColumnIndex("logo2"));
                    ByteArrayInputStream bin2 = new ByteArrayInputStream(img2);
                    bean.setLogo2(Drawable.createFromStream(bin2, "img2"));

                    bean.setCentertext("VS");
                    bean.setDate(date);
                    bean.setTime(time);
                    bean.setTeam1(name1);
                    bean.setTeam2(name2);

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
