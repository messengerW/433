package com.example.f433;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
* 这是一个Dao类，在JavaWeb中Dao就是一个专门用来连接数据库的类，但是Android和JavaWeb有点不一样，
* Android如果想连接外部已经创建好的数据库的话，需要先copy再paste，我这里copy paste的代码放在了
* DBHelper类中，也就是说“连接”数据库的任务已经在DBHelper中完成了，这里的Dao实际上就是对数据库的
* 一些具体操作，比如我们的项目主要就是select。
* */
public class F2_C3_RankDao {

    private DBHelper helper;    //  实例化一个helper
    private SQLiteDatabase db;  //  实例化一个数据库类

    public F2_C3_RankDao(Context context) {
        helper = new DBHelper(context);
    }

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