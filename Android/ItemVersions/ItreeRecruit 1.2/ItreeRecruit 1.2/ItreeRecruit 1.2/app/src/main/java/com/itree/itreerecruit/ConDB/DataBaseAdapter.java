package com.itree.itreerecruit.ConDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itree.itreerecruit.entity.Resume;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库的适配器，把前端和后台联系起来
 * Created by dell1 on 2015/10/10.
 */
public class DataBaseAdapter {
    private DatabaseHelper databasehelper;
    public DbUtils dbUtils ;
    public Context context;
    public Resume resume;

    //创建表
    public DataBaseAdapter(Context context) {
        databasehelper = new DatabaseHelper(context);
        this.context = context;
        dbUtils = DbUtils.create(this.context,"iTreeResume.db");
    }
    /*
    public void updateResumename(Resume resume){
        SQLiteDatabase DBwrite = databasehelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("resume_name",resume.getResume_name());
        String whereClause="_id"+"=?";
        String []whereArgs={String.valueOf(resume.getResume_id())}  ;
        DBwrite.update("resume_table", values, whereClause, whereArgs);
        DBwrite.close();
    }
    *//*public void addResumeNmae(Resume resume) {
        //创建contextvalues的集合
        SQLiteDatabase DBwrite = databasehelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("resume_name", resume.getResume_name());
        DBwrite.insert("resume_table", null, values);
        System.out.println("修改简历名成功");
        DBwrite.close();
    }*//*
    public Resume findid(String name){
        Resume resume1=new Resume();
        SQLiteDatabase dbreader=databasehelper.getReadableDatabase();
        String []columns={iTree_Resume_DB.RESUME._ID};
        //参数值：是否去除重复列，表名，查询条件，查询条件的值，分组，分组条件的值，排序，分页条件
        //cursor：结果集
        Cursor c=dbreader.query(true,"resume_table",columns, iTree_Resume_DB.RESUME.RESUME_NAME+"=?", new String[]{name}, null, null, null, null);
        if (c.moveToNext()){
            resume1.setResume_id(c.getInt(c.getColumnIndexOrThrow(iTree_Resume_DB.RESUME._ID)));
        }
        c.close();
        return resume1;
    }
    public void updateEducate(Resume resume){
        //创建contextvalues的集合
        SQLiteDatabase DBwrite = databasehelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("school_Name",resume.getSchool_name());
        values.put("start_Time",resume.getStime());
        values.put("end_Time",resume.getEtime());
        String whereClause="_id"+"=?";
        String []whereArgs={String.valueOf(resume.getResume_id())}  ;
        DBwrite.update("resume_table", values, whereClause, whereArgs);
        DBwrite.close();
    }
    public void updateInfo(Resume resume){
        //创建contextvalues的集合
        SQLiteDatabase DBwrite = databasehelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",resume.getName());
        values.put("birthday",resume.getBirthday());
        values.put("email",resume.getEmail());
        values.put("telphone", resume.getTel());
        values.put("sex",resume.getSex());
        String whereClause="_id"+"=?";
        String []whereArgs={String.valueOf(resume.getResume_id())}  ;
        DBwrite.update("resume_table", values, whereClause, whereArgs);
        DBwrite.close();
    }
    *//*public void delete(int id){
        SQLiteDatabase DBwrite = databasehelper.getWritableDatabase();
        String whereClause= iTree_Resume_DB.RESUME._ID+"=?";
        String[] whereargs={String.valueOf(id)};
        DBwrite.delete(iTree_Resume_DB.RESUME.TABLE_NAME, whereClause, whereargs);
    }*//*
    public Cursor find(){
        SQLiteDatabase DBwrite = databasehelper.getWritableDatabase();
        //游标查询每条数据
        Cursor cursor =DBwrite.query(true, iTree_Resume_DB.RESUME.TABLE_NAME,null,null, null, null, null, null, null);
        return cursor;
    }

    *//*public Resume findbyid(int id){
        Resume resume=new Resume();
        SQLiteDatabase dbreader=databasehelper.getReadableDatabase();
        //要查询的列
        //cursor：结果集
        Cursor c=dbreader.query(true,"resume_table",null, iTree_Resume_DB.RESUME._ID+"=?",new String[]{String.valueOf(id)}, null, null, null, null);
        if (c.moveToNext()){
            resume.setResume_id(c.getInt(c.getColumnIndexOrThrow(iTree_Resume_DB.RESUME._ID)));
            resume.setResume_name(c.getString(c.getColumnIndexOrThrow("resume_name")));
            resume.setName(c.getString(c.getColumnIndexOrThrow("name")));
            resume.setSex(c.getString(c.getColumnIndexOrThrow("sex")));
            resume.setBirthday(c.getString(c.getColumnIndexOrThrow("birthday")));
            resume.setTel(c.getString(c.getColumnIndexOrThrow("telphone")));
            resume.setEmail(c.getString(c.getColumnIndexOrThrow("email")));
            resume.setSchool_name(c.getString(c.getColumnIndexOrThrow("school_Name")));
            resume.setStime(c.getString(c.getColumnIndexOrThrow("start_Time")));
            resume.setEtime(c.getString(c.getColumnIndexOrThrow("end_Time")));
        }
        c.close();
        dbreader.close();
        return resume;
    }*//*
     public ArrayList<Resume> findall(){
        SQLiteDatabase db=databasehelper.getReadableDatabase();
//        //要查询的列
//        String []columns={iTree_Resume_DB.EducateBg._ID,iTree_Resume_DB.EducateBg.SCHOOL_NAME,iTree_Resume_DB.EducateBg.START_TIME};
        //参数值：是否去除重复列，表名，查询条件，查询条件的值，分组，分组条件的值，排序，分页条件
        //cursor：结果集
        Cursor c=db.query(true, iTree_Resume_DB.RESUME.TABLE_NAME,null,null, null, null, null, null, null);
        ArrayList<Resume> list=new ArrayList<Resume>();
         while (c.moveToNext()){
             Resume resume=new Resume();
             resume.setResume_id(c.getInt(c.getColumnIndexOrThrow(iTree_Resume_DB.RESUME._ID)));
             resume.setResume_name(c.getString(c.getColumnIndexOrThrow("resume_name")));
             resume.setName(c.getString(c.getColumnIndexOrThrow("name")));
             resume.setSex(c.getString(c.getColumnIndexOrThrow("sex")));
             resume.setBirthday(c.getString(c.getColumnIndexOrThrow("birthday")));
             resume.setTel(c.getString(c.getColumnIndexOrThrow("telphone")));
             resume.setEmail(c.getString(c.getColumnIndexOrThrow("email")));
             resume.setSchool_name(c.getString(c.getColumnIndexOrThrow("school_Name")));
             resume.setStime(c.getString(c.getColumnIndexOrThrow("start_Time")));
             resume.setEtime(c.getString(c.getColumnIndexOrThrow("end_Time")));
         }
        c.close();
        db.close();
        return  list;
    }*/



    public void addResumeName(Resume resume) {
    }


    /**
     * 插入单个对象
     *
     * @param entity 实体类的对象
     * @return true:插入成功 false:插入失败
     */
    public synchronized boolean save(Resume entity) {
        try {
            dbUtils.save(entity);
        } catch (DbException e) {
            if (e != null)
                e.printStackTrace();
            return false;
        }
        return true;
    }




    /**
     * 更新这张表中的所有数据
     *
     * @param entity 实体类的对象
     * @return true:更新成功 false:更新失败
     */
    public synchronized boolean update(Object entity) {
        try {
            dbUtils.saveOrUpdate(entity);//先去查这个条数据 根据id来判断是存储还是更新 如果存在更新
        } catch (Exception e) {
            if (e != null)
                e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据id更新这张表中的数据
     *
     * @param entity 实体类的对象, id 需要更新的id
     * @return true:更新成功 false:更新失败
     */
    public synchronized boolean updatePersonalInfoById(int id) {
        Resume entity = null;
        try {
            //根据id查找
            entity = dbUtils.findById(Resume.class,id);
//          //调用update方法，参数：实体类，更改的数据，字段名
            dbUtils.update(resume,entity.getName(),entity.getEmail(),entity.getTel(),entity.getBirthday(),entity.getSex(), "name","email","tel","birthday","sex");
            //测试
        } catch (Exception e) {
            if (e != null)
                e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 根据id更新这张表中的数据
     *
     * @return Cursor
     */
    public synchronized Cursor findAll() {
        Cursor c;
        try {

            String sql = "select resume_id,resume_name,name,sex,birthday,tel,email,school_name,stime,etime from resume_table";
            c = dbUtils.execQuery(sql);
        } catch (Exception e) {
            if (e != null)
                e.printStackTrace();
            return null;
        }
        return c;
    }


    /**
     * 根据id更新这张表中的数据
     *
     * @param  id 需要更新的id
     * @return true:更新成功 false:更新失败
     */
    public synchronized boolean delete(int id) {
        try {

            dbUtils.deleteById(Resume.class, id);
        } catch (Exception e) {
            if (e != null)
                e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据ID进行查找
     * @param id
     * @return 实体类
     */
    public synchronized Resume findById(int id) {
        Resume r;
        try {
            r = dbUtils.findFirst(Selector.from(Resume.class).where("resume_id", "=", id));
//             r = mDBClient.findById(Resume.class, id);
        } catch (Exception e) {
            if (e != null)
                e.printStackTrace();
            return null;
        }
        return r;
    }





}