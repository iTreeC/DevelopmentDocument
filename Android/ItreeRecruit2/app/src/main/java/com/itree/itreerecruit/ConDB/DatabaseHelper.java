package com.itree.itreerecruit.ConDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell1 on 2015/10/10.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //定义数据库的名字
    private static  final String DB_NAME="iTreeResume.db";
    private static final int VERSION=1;
    /**
     * 定义sql语句
     */
    private static final String CREATE_TABLE="create table resume_table(_id integer primary key autoincrement,resume_name text," +
            "name varchar(20),sex Bit,birthday TEXT,telphone TEXT,email TEXT,school_Name text,start_Time text,end_Time text)";
    //删除数据表
    private static final String DROP_TABLE="drop table if exists resume_table";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /**
     * 在创建之前可以主动检查是否有数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }
    /**
     * 升级更新方法
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //先删除再创建
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);

    }
}
