package com.example.yeqinglu.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by yeqing.lu on 2016/9/12.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table Book(" +
            "id integer primary key autoincrement,"+
            "author text," +
            "price real," +
            "pages integer,"+
            "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    private Context mContext;

    /*SQLiteOpenHelper 中有两个构造方法可供重写，一般使用参数少一点的那个构造方法即
    可。这个构造方法中接收四个参数，第一个参数是 Context，这个没什么好说的，必须要有
    它才能对数据库进行操作。第二个参数是数据库名，创建数据库时使用的就是这里指定的名
    称。第三个参数允许我们在查询数据的时候返回一个自定义的 Cursor，一般都是传入 null。
    第 四 个 参 数 表 示 当 前 数 据 库 的 版 本 号 ， 可 用 于 对 数 据 库 进 行 升 级 操 作 。*/

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT);

    }


    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);

    }
}
