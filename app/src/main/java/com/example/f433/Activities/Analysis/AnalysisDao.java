package com.example.f433.Activities.Analysis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import com.example.f433.Fragment2.F2_C2_GameBean;
import com.example.f433.Util.DBHelper;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class AnalysisDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public AnalysisDao(Context context) {
        helper = new DBHelper(context);
    }

    public ArrayList<AnalysisBean> getItemList() {

        ArrayList<AnalysisBean> itemlist = new ArrayList<AnalysisBean>();

        try {

            //  通过调用getWritableDatabase方法获取一个可以读写数据库的对象
            db = helper.getWritableDatabase();
            //  游标，类似JavaWeb中的resultset
            Cursor cursor = db.rawQuery("select * from newGame", null);

            //  查询之前要先判断数据库中是否有数据
            if (cursor.moveToFirst()) {
                do {

                    //  实例化一个bean，也就是VO
                    AnalysisBean bean = new AnalysisBean();

                    int a = cursor.getInt(cursor.getColumnIndex("aerialDuelScc"));
//                    String name1 = cursor.getString(cursor.getColumnIndex("name1"));
//                    String name2 = cursor.getString(cursor.getColumnIndex("name2"));
//                    String date = cursor.getString(cursor.getColumnIndex("date"));
//                    String time = cursor.getString(cursor.getColumnIndex("time"));



                    bean.setAerialDuelScc(a);


                    itemlist.add(bean);

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
        return itemlist;
    }
}
