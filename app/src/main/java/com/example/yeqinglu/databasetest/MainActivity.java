package com.example.yeqinglu.databasetest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    public Button createDatabase;
    public Button addData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,3);
        createDatabase = (Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        addData = (Button)findViewById(R.id.add_data);
//        assert addData != null;
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                /*SQLiteDatabase 中提供了一个 insert()方法，这个方法就是专门用于添加数据的。它接收三个
                参数，第一个参数是表名，我们希望向哪张表里添加数据，这里就传入该表的名字。第二个
                参数用于在未指定添加数据的情况下给某些可为空的列自动赋值 NULL，一般我们用不到这
                个功能，直接传入 null 即可。第三个参数是一个 ContentValues 对象，它提供了一系列的 put()
                方法重载，用于向 ContentValues 中添加数据，只需要将表中的每个列名以及相应的待添加
                数据传入即可。*/
                //开始组装第一条数据
                values.put("name","The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("page",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();// 插入第一条数据
                // 开始组装第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values); // 插入第二条数据
            }
        });


        /*第一个参数和 insert()方法一样，也是表名，在这里指定去更新哪张表里的数
        据。第二个参数是 ContentValues 对象，要把更新数据在这里组装进去。第三
        、第四个参数
        用于去约束更新某一行或某几行中的数据，不指定的话默认就是更新所有行。*/
        Button updataData = (Button)findViewById(R.id.updata_data);
        updataData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name=?",new String[]{"The" +
                        "Da Vinci Code"

                });
            }
        });

        Button delteButton = (Button)findViewById(R.id.delete_data);
        delteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book","pages>?",new String[] {"500"});
            }
        });



    }
}
