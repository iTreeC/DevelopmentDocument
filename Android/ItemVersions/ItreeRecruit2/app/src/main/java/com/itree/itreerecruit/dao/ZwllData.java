package com.itree.itreerecruit.dao;

import android.provider.BaseColumns;

/**
 * Created by lenovo on 2015/10/17.
 * 职位类别中的数据
 */
public class ZwllData {
    public static abstract class Zwll implements BaseColumns {
        //表名
        public static final String TABLE_NAME = "zwll_table";
        //字段名
        public static final String ID = "id";
        public static final String ZWLL = "zwll";
        public static final String TYPE = "type";
    }
}
