package com.itree.itreerecruit.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by lenovo on 2015/10/18.
 * 创建职位类别的groups组的表
 */
public class DataBaseHelper_Zwllg extends SQLiteOpenHelper {
    private static final String BD_NAME = "job.db";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE_USERINFO = "create table if not exists zwll_table(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "zwll TEXT"+"type TEXT)";

    private static final String DROP_TABLE_USERINFO ="drop table if exists zwll_table";

    public DataBaseHelper_Zwllg(Context context) {
        super(context, BD_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERINFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USERINFO);
        db.execSQL(CREATE_TABLE_USERINFO);
    }
}
