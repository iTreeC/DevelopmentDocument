package com.itree.itreerecruit.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2015/10/17.
 * 建立行业类别的表
 */
public class DataBaseHelper_Hyll extends SQLiteOpenHelper {
    private static final String BD_NAME = "job.db";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE_USERINFO = "create table if not exists hyll_table(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "hyll TEXT"+"type TEXT)";

    private static final String DROP_TABLE_USERINFO ="drop table if exists hyll_table";

    public DataBaseHelper_Hyll(Context context) {
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
