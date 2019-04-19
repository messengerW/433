package com.example.f433;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/*
* 这是一个用来连接sqlite数据库的类，基本思路是把已经手动添加到assets目录下的.db文件拷贝到一个指定
* 文件夹（我只测试了模拟器的路径 /data/data/com.example.f433/，暂时还没实验真机），这个文件夹
* 可以在侧边工具栏Device File Explorer中找到，SQLiteOpenHelper 是一个专门来连接sqlite数据库的类。
*
* */
public class DBHelper extends SQLiteOpenHelper {
    private static final String db_name = "ftt.db";     //  数据库名
    private static String db_path = "/data/data/com.example.f433/databases/";    //  要拷贝到的路径
    private static final int db_version = 1;    //  当前数据库版本


    //  狗崽方法
    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
        CopyDB(context);
    }

    public void CopyDB(Context context) {

        // 检查 SQLite 数据库文件是否存在
        if (!(new File(db_path + db_name)).exists()) {
            // 如果 SQLite 数据库文件不存在，再检查一下 databases 目录是否存在
            File f = new File(db_path);
            // 如果 databases 目录不存在，新建该目录
            if (!f.exists()) {
                f.mkdir();
            }

            try {

                // 得到 assets 目录下已手动添加的.db文件作为输入流
                InputStream is = context.getAssets().open("ftt.db");

                // 输出流,在指定路径下生成数据库文件
                FileOutputStream os = new FileOutputStream(db_path + db_name);

                // 文件写入
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }

                // 关闭文件流
                os.flush();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        /*String sql = "create table league" + "(" +
                "rank TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "turn TEXT NOT NULL," +
                "num1 TEXT NOT NULL," +
                "num2 TEXT NOT NULL," +
                "num3 TEXT NOT NULL," +
                "rate TEXT NOT NULL" +
                ")";
        db.execSQL(sql);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            onCreate(db);
        }
    }

   /* public Cursor query(String sql, String... selectionArgs) {
        return db.rawQuery(sql, selectionArgs);

    }
*/

}
