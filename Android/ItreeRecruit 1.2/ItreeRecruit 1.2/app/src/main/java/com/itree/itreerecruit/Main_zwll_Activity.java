package com.itree.itreerecruit;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;


import com.itree.itreerecruit.dao.DataBaseAdapter;
import com.itree.itreerecruit.dao.DataBaseHelper_Zwllc;
import com.itree.itreerecruit.dao.DataBaseHelper_Zwllg;
import com.itree.itreerecruit.entity.Zwll;
import com.itree.itreerecruit.entity.Zwll_c;
import com.itree.itreerecruit.gzddUtil.MyExpandableAdapter_zwll;

import java.util.ArrayList;

public class Main_zwll_Activity extends AppCompatActivity {
    private ExpandableListView exlistview;
    private DataBaseHelper_Zwllg sqlHelper_zwllg;
    private DataBaseHelper_Zwllc sqlHelper_zwllc;
    private DataBaseAdapter dataBaseAdapter;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zwll);
        //添加groups组的数据
        addToDbG();

        dataBaseAdapter =new DataBaseAdapter(Main_zwll_Activity.this);
        //读取数据
        ArrayList<Zwll> zwlls= dataBaseAdapter.findAll_zwll();

        //将arraylist转换成数组
        String[] g = new String[zwlls.size()];

        for(int i= 0;i<zwlls.size();i++){
            g[i]=zwlls.get(i).getZwll();
        }
        for(int i= 0;i<g.length;i++){
            System.out.println(g[i]);
        }
        //添加childs组的数据
        addToDbC();

        dataBaseAdapter =new DataBaseAdapter(Main_zwll_Activity.this);
        //读取数据
        ArrayList<Zwll_c> zwll_cs= dataBaseAdapter.findAll_zwll_c();

        //将arraylist转换成数组
        String[][] c = new String[zwlls.size()][];

        for(int i= 0;i<zwlls.size();i++){
            for (int j=0;i<2;j++){
                c[i][j]=zwll_cs.get(i).getZwll_C();
            }

        }
//        for(int i= 0;i<s.length;i++){
//            System.out.println(s[i]);
//        }

        exlistview = (ExpandableListView)findViewById(R.id.expandableListView_zwll);
        exlistview.setAdapter(new MyExpandableAdapter_zwll(Main_zwll_Activity.this,g,c));
        //遍历所有group,将所有项设置成默认展开
        int groupCount = exlistview.getCount();
        for (int i=0; i<groupCount; i++)
        {
            exlistview.expandGroup(i);
        };
    }

    /**
     * 增加groups组的数据
     */
    private void addToDbG(){
        //添加职位grourps组数据到数据库

            System.out.println("添加groups数据");
            //建立新表,插入数据
            sqlHelper_zwllg =new DataBaseHelper_Zwllg(Main_zwll_Activity.this);
            sqlHelper_zwllg.getWritableDatabase();
            //插入数据
            SQLiteDatabase db = sqlHelper_zwllg.getWritableDatabase();
            ContentValues values = new ContentValues();
            //插入数据
            values.put("zwll","销售业务");
            db.insert("zwll_table", null, values);
            values.put("zwll","销售管理");
            db.insert("zwll_table", null, values);
            values.put("zwll","销售行政/商务");
            db.insert("zwll_table", null, values);
            values.put("zwll","客服/售前/售后技术支持");
            db.insert("zwll_table", null, values);
            values.put("zwll","市场");
            db.insert("zwll_table", null, values);
            values.put("zwll", "公关/媒体");
            db.insert("zwll_table", null, values);


            System.out.println("添加成功");

    }

    /**
     * 添加childs组的数据
     *
     */
    private void addToDbC(){
        //添加职位childs组数据到数据库

            System.out.println("添加childs数据");
            //建立新表,插入数据
            sqlHelper_zwllc =new DataBaseHelper_Zwllc(Main_zwll_Activity.this);
            sqlHelper_zwllc.getWritableDatabase();
            //插入数据
            SQLiteDatabase db = sqlHelper_zwllc.getWritableDatabase();
            ContentValues values = new ContentValues();
            //插入数据

            values.put("zwll_c", "销售总监");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售经理");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售总监");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售经理");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售总监");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售经理");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售总监");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售经理");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售总监");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售经理");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售总监");
            db.insert("zwll_c_table", null, values);
            values.put("zwll_c", "销售经理");
            db.insert("zwll_c_table", null, values);


            System.out.println("添加成功");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_zwll, menu);
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
