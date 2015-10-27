package com.itree.itreerecruit.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.itree.itreerecruit.entity.Hyll;
import com.itree.itreerecruit.entity.JobAddress;
import com.itree.itreerecruit.entity.Zwll;
import com.itree.itreerecruit.entity.Zwll_c;

import java.util.ArrayList;

/**
 * Created by dell1 on 2015/10/10.
 */
public class DataBaseAdapter {

    private DataBaseHelper dataBaseHelper;

    public DataBaseAdapter(Context context){
        dataBaseHelper = new DataBaseHelper(context);
    }

    /**
     * 添加数据
     * @param jobAddress  实体类
     */
    public void add(JobAddress jobAddress){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JobAddressData.JobAddress.ADDRESS,jobAddress.getAddress());
        values.put(JobAddressData.JobAddress.ID,jobAddress.getId());

        db.insert(JobAddressData.JobAddress.TABLE_NAME,null,values);
        db.close();
    }

    /**
     * 删除数据
     * @param id 要删除的数据的id
     */
    public void delete(int id){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String whereClause = JobAddressData.JobAddress._ID+"=?";
        String [] whereArgs = {String.valueOf(id)};
        db.delete(JobAddressData.JobAddress.TABLE_NAME, whereClause, whereArgs);
        db.close();
    }

    /**
     * 更新数据
     * @param jobAddress 实体类
     */
    public void update(JobAddress jobAddress){
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JobAddressData.JobAddress.ADDRESS,jobAddress.getAddress());
        values.put(JobAddressData.JobAddress.ID,jobAddress.getId());
        String whereClause = JobAddressData.JobAddress._ID+"=?";
        String [] whereArgs = {String.valueOf(jobAddress.getId())};
        db.update(JobAddressData.JobAddress.TABLE_NAME, values, whereClause, whereArgs);
        db.close();
    }
    /**
     * 根据type查询被选择的数据
     * @param type 根据数据被选中的的状态，来查询查询出被选中的数据
     * @return 实体类的全部数据
     */
    public JobAddress findById(int type){

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c = db.query(true, JobAddressData.JobAddress.TABLE_NAME, null, JobAddressData.JobAddress.TYPE + "=?", new String[]{String.valueOf(type)}, null, null, null, null);
        JobAddress jobAddress = null;
        if(c.moveToNext()){
            jobAddress = new JobAddress();
            jobAddress.setId(c.getInt(c.getColumnIndexOrThrow(JobAddressData.JobAddress.TYPE)));
            jobAddress.setAddress(c.getString(c.getColumnIndexOrThrow(JobAddressData.JobAddress.ADDRESS)));

        }
        c.close();
        db.close();
        return jobAddress;

    }

    /**
     * 查询所有的地点数据
     * @return
     */
    public ArrayList<JobAddress> findAll_gzdd(){

        String sql = "select _id,address from jobaddress_table";
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c =db.rawQuery(sql, null);
        ArrayList<JobAddress> jobAddresses = new ArrayList<>();
        JobAddress jobAddress = null;
        while (c.moveToNext()){
            jobAddress = new JobAddress();
            jobAddress.setId(c.getInt(c.getColumnIndexOrThrow(JobAddressData.JobAddress._ID)));
            jobAddress.setAddress(c.getString(c.getColumnIndexOrThrow(JobAddressData.JobAddress.ADDRESS)));
            //jobAddress.setType(c.getInt(c.getColumnIndexOrThrow(JobAddressData.JobAddress.TYPE)));
            jobAddresses.add(jobAddress);

        }
        c.close();
        db.close();
        return jobAddresses;

    }
    /**
     * 查询所有的行业类别数据
     * @return
     */
    public ArrayList<Hyll> findAll_hyll(){

        String sql = "select _id,hyll from hyll_table";
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c =db.rawQuery(sql, null);
        ArrayList<Hyll> hylls = new ArrayList<>();
        Hyll hyll = null;
        while (c.moveToNext()){
            hyll = new Hyll();
            hyll.setId(c.getInt(c.getColumnIndexOrThrow(HyllData.Hyll._ID)));
            hyll.setHyll(c.getString(c.getColumnIndexOrThrow(HyllData.Hyll.HYLL)));
           // hyll.setType(c.getInt(c.getColumnIndexOrThrow(HyllData.Hyll.TYPE)));
            hylls.add(hyll);

        }
        c.close();
        db.close();
        return hylls;

    }
    /**
     * 查询所有的行业类别中组的数据
     * @return
     */
    public ArrayList<Zwll> findAll_zwll(){

        String sql = "select _id,zwll from zwll_table";
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c =db.rawQuery(sql, null);
        ArrayList<Zwll> zwlls = new ArrayList<>();
        Zwll zwll = null;
        while (c.moveToNext()){
            zwll = new Zwll();
            zwll.setId(c.getInt(c.getColumnIndexOrThrow(ZwllData.Zwll._ID)));
            zwll.setZwll(c.getString(c.getColumnIndexOrThrow(ZwllData.Zwll.ZWLL)));
            //zwll.setType(c.getInt(c.getColumnIndexOrThrow(ZwllData.Zwll.TYPE)));
            zwlls.add(zwll);

        }
        c.close();
        db.close();
        return zwlls;

    }

    /**
     * 查询所有的行业类别中组的数据
     * @return
     */
    public ArrayList<Zwll_c> findAll_zwll_c(){

        String sql = "select _id,zwll_c from zwll_c_table";
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor c =db.rawQuery(sql, null);
        ArrayList<Zwll_c> zwll_cs = new ArrayList<>();
        Zwll_c zwll_c = null;
        while (c.moveToNext()){
            zwll_c = new Zwll_c();
            zwll_c.setId(c.getInt(c.getColumnIndexOrThrow(Zwll_cData.Zwll_c._ID)));
            zwll_c.setZwll_C(c.getString(c.getColumnIndexOrThrow(Zwll_cData.Zwll_c.ZWLL_C)));
            //zwll.setType(c.getInt(c.getColumnIndexOrThrow(ZwllData.Zwll.TYPE)));
            zwll_cs.add(zwll_c);

        }
        c.close();
        db.close();
        return zwll_cs;

    }

//    /**
//     * 获取总共个数
//     * @return 总个数
//     */
//    public int getCount(){
//        int count = 0;
//        String sql = "select count(_id) from userinfo_table ";
//        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
//        Cursor c =db.rawQuery(sql, null);
//        c.moveToFirst();
//        count = c.getInt(0);
//        c.close();
//        db.close();
//        return count;
//
//    }
}
