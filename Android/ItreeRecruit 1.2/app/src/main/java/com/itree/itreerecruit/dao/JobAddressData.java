package com.itree.itreerecruit.dao;

import android.provider.BaseColumns;

/**
 * Created by lenovo on 2015/10/14.
 * 工作地点中的数据
 */
public class JobAddressData {
    public static abstract class JobAddress implements BaseColumns {
        //表名
        public static final String TABLE_NAME = "jobaddress_table";
        //字段名
        public static final String ID = "id";
        public static final String ADDRESS = "address";
        public static final String TYPE = "type";
    }

}
