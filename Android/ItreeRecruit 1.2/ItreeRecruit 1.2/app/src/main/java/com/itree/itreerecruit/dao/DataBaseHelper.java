package com.itree.itreerecruit.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell1 on 2015/10/10.
 * 工作地点的表的创建
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String BD_NAME = "job.db";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE_USERINFO = "create table if not exists jobaddress_table(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "address TEXT"+"type TEXT)";

    private static final String DROP_TABLE_USERINFO ="drop table if exists jobaddress_table";

    public DataBaseHelper(Context context) {
        super(context, BD_NAME, null, VERSION);
    }

    /**
     * 创建
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USERINFO);
    }

    /**
     * 更新
     * @param sqLiteDatabase
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE_USERINFO);
        sqLiteDatabase.execSQL(CREATE_TABLE_USERINFO);
    }
}
