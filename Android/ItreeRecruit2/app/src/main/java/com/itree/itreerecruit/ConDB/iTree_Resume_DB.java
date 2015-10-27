package com.itree.itreerecruit.ConDB;

import android.provider.BaseColumns;

/**
 * Created by dell1 on 2015/10/10.
 * 元数据的定义
 */
public final class iTree_Resume_DB {
    //定义构造方法
    public iTree_Resume_DB() {
    }
    //定义数据表
    /*public static abstract class EducateBg  implements BaseColumns{
        *//**
     * 表中的一些字段
     *//*
        public static final String TABLE_NAME="EducateBg_Table";
        public static final String SCHOOL_NAME="School_Name";
        public static final String START_TIME="Start_Time";
        public static final String END_TIME="End_Time";*/
//}
    public static abstract class RESUME implements BaseColumns{
        public static final String TABLE_NAME="resume_table";
        public static final String RESUME_NAME="resume_name";
        public static final String USERNAME = "name";
        public static final String SEX = "sex";
        public static final String BIRTHDAY = "birthday";
        public static final String TEL = "telphone";
        public static final String EMAIL = "email";
        public static final String SCHOOL_NAME="school_Name";
        public static final String START_TIME="start_Time";
        public static final String END_TIME="end_Time";
    }


    }

