package com.itree.itreerecruit.dao;

import android.provider.BaseColumns;

/**
 * Created by lenovo on 2015/10/17.
 * 行业类别中的信息
 */
public class HyllData {
    public static abstract class Hyll implements BaseColumns {
        //表名
        public static final String TABLE_NAME = "hyll_table";
        //字段名
        public static final String ID = "id";
        public static final String HYLL = "hyll";
        public static final String TYPE = "type";
    }
}
