package com.example.f433.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/*
 * 这是一个用来连接sqlite数据库的类，基本思路是把已经手动添加到assets目录下的.db文件拷贝到一个指定
 * 文件夹（我只测试了模拟器的路径 /data/data/com.example.f433/，暂时还没实验真机），这个文件夹
 * 可以在侧边工具栏Device File Explorer中找到，SQLiteOpenHelper 是一个专门来连接sqlite数据库的类。
 * */
public class DBHelper extends SQLiteOpenHelper {
    private static final String db_name = "ftt.db";     //  数据库名
    private static String db_path = "/data/data/com.example.f433/databases/";    //  要拷贝到的路径

    /*wdnmd被这个db_version坑了好久，这个参数就是sqlite的版本，比如我用的sqlite3。*/
    private static final int db_version = 3;    //  当前数据库版本

    //  构造方法，调用copy函数
    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
        CopyDB(context);
    }

    /*  这个函数是最重要的一个函数，具体是这样操作的：
    *   （准备）先用数据库管理工具建立一个数据库文件 ftt.db 然后在工程目录下建立一个 assets 文件夹，把 ftt.db 粘贴进去，
    *   然后 copydb() 这个函数的作用就是先检查手机（实体机/模拟器）存储目录中是否有你要的这个 .db 文件，没有的话，
    *   建立目录、文件，然后将 assets 文件夹下的数据库文件写入刚刚创建的目录中。
    *
    */
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

    /*  onCreate函数是第一次创建数据库文件时调用的，SQLiteOpenHelper会自动检测数据库文件是否存在。如果存在，
    会打开这个数据库，在这种情况下就不会调用onCreate()方法。如果数据库文件不存在，SQLiteOpenHelper
    首先会创建一个数据库文件，然后打开这个数据库，最后调用onCreate()方法。因此，onCreate()方法一般用来
    在新创建的数据库中建立表、视图等操作。
        但是我觉得，建数据库这些操作直接用外部图形化工具更方便，比如我用Navicat Premium创建好数据库文件后，直接
    把这个数据库文件（×××.db）复制粘贴到assets目录下就可以了，这样就不用在这里写sql语句创建了。
    */
    @Override
    public void onCreate(SQLiteDatabase db) {

        /*String sql = "create table league" + "(" +
                "rank TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "turn TEXT NOT NULL," +
                "num1 TEXT NOT NULL," +
                "num2 TEXT NOT NULL," +
                "num3 TEXT NOT NULL," +
                "rate TEXT NOT NULL," +
                "PRIMARY KEY (name)" +
                ")";
        db.execSQL(sql);*/
        Log.i("onCreate--->", "首次安装数据库，创建一个表");
    }

    /*  这个函数时更新数据库版本的，一般不触发，只有当创建数据库对象时传入的版本比当前数据库版本更高时才会触发*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion == 1) {
            //String upgrade1 = "";
            //db.execSQL(upgrade1);
            Log.i("------------onUpgrade", "版本：" + oldVersion + " -> " + newVersion);
        }

        if (oldVersion == 2) {
            //String upgrade2 = "";
            //db.execSQL(upgrade2);
            Log.i("------------onUpgrade", "版本：" + oldVersion + " -> " + newVersion);
        }

    }



}
