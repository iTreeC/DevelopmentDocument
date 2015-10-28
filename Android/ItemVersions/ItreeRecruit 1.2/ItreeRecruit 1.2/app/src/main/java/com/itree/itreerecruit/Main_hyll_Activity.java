package com.itree.itreerecruit;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.itree.itreerecruit.dao.DataBaseAdapter;
import com.itree.itreerecruit.dao.DataBaseHelper_Hyll;
import com.itree.itreerecruit.entity.Hyll;
import com.itree.itreerecruit.gzddUtil.MyAdapter_hyll;

import java.util.ArrayList;


public class Main_hyll_Activity extends AppCompatActivity {
    private ListView listView;
    private DataBaseHelper_Hyll sqlHelper;
    private DataBaseAdapter dataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hyll);
        //往数据库添加数据
        //实现第一次进入此页面，添加数据库

        if(getParmHyll().equals("false")){
            addToDb();
        }

        dataBaseAdapter =new DataBaseAdapter(Main_hyll_Activity.this);
        //读取数据
        ArrayList<Hyll> hylls= dataBaseAdapter.findAll_hyll();

        //将arraylist转换成数组
        String[] s = new String[hylls.size()];

        for(int i= 0;i<hylls.size();i++){
            s[i]=hylls.get(i).getHyll();
        }
        for(int i= 0;i<s.length;i++){
            System.out.println(s[i]);
        }

        //设置列表
        listView = (ListView)findViewById(R.id.listView_hyll);
        listView.setAdapter(new MyAdapter_hyll(Main_hyll_Activity.this,s));

    }
    /**
     * 改变从主页面中传过来的false为ture
     */
    public void addPramTrueHyll(){
        SharedPreferences.Editor editor = getSharedPreferences("Pram",MODE_PRIVATE).edit();
        editor.putString("q","true");
        Log.d("TEST", "更改成功");
        editor.commit();
    }

    /**
     * 来得到全项目变量的值
     * @return
     */
    public String getParmHyll(){
        String q=null;
        SharedPreferences sharedPreferences = getSharedPreferences("Pram", MODE_PRIVATE);
        q = sharedPreferences.getString("q","");
        Log.d("Test", q);
        return q;
    }

    /**
     * 添加数据到行业类别中
     */
    private void addToDb(){
        //添加数据到数据库

        System.out.println("添加数据");
        //建立新表,插入数据
        sqlHelper =new DataBaseHelper_Hyll(Main_hyll_Activity.this);
        System.out.println("准备进入hyll建表");
//        sqlHelper.getWritableDatabase();
        //插入数据
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        sqlHelper.onCreate(db);
        ContentValues values = new ContentValues();
        //插入数据

        values.put("hyll","互联网/电子商务");
        db.insert("hyll_table", null, values);
        values.put("hyll","计算机软件");
        db.insert("hyll_table", null, values);
        values.put("hyll","IT服务（系统/数据/维护）");
        db.insert("hyll_table", null, values);
        values.put("hyll","电子技术/半导体/集成电路");
        db.insert("hyll_table", null, values);
        values.put("hyll","计算机硬件");
        db.insert("hyll_table", null, values);
        //改变全局变量为true;
        addPramTrueHyll();
        System.out.println("添加成功");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_hyll, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
