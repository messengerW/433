package com.example.f433;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    private static final String db_name = "league";     //  数据库名
    private static final int db_version = 1;    //  当前版本

    public DBhelper(Context context) {
        super(context,db_name,null,db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String  sql ="CREATE TABLE `league` (" +
                "`rank` int(2) NOT NULL," +
                "`name` varchar(255) DEFAULT NULL," +
                "`turn` int(2) DEFAULT NULL," +
                "`num1` int(2) DEFAULT NULL," +
                "`num2` int(2) DEFAULT NULL," +
                "`num3` int(2) DEFAULT NULL," +
                "`rate` varchar(255) DEFAULT NULL," +
                "`points` int(3) DEFAULT NULL," +
                "PRIMARY KEY (`rank`)" +
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}



}
