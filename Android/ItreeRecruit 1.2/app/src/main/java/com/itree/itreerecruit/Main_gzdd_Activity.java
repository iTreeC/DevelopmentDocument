package com.itree.itreerecruit;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;


import com.itree.itreerecruit.dao.DataBaseAdapter;
import com.itree.itreerecruit.dao.DataBaseHelper;
import com.itree.itreerecruit.entity.JobAddress;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main_gzdd_Activity extends AppCompatActivity {

    //这是自动检测搜索条变量
    private AutoCompleteTextView autoCompleteTextView_main_gzdd;
    //这是列表的变量
    private ListView listview;
    private DataBaseHelper sqlHelper;
    private DataBaseAdapter dataBaseAdapter;
    //全局变量来接受被点击的按钮的id
    Set<Integer> positionSet1  = new HashSet<Integer>();
    //存储全项目变量
    private String checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gzdd);

        //这是为自动 检索条设置数据


//        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(Main_gzdd_Activity.this, R.array.city,android.R.layout.simple_dropdown_item_1line);
//        autoCompleteTextView_main_gzdd = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1_main_gzdd);
//        autoCompleteTextView_main_gzdd.setAdapter(arrayAdapter);
        //这是为列表设置数据
        listview = (ListView)findViewById(R.id.listView1_gzdd);
        //实现第一次进入此页面，添加数据库
        if(getParmGzdd().equals("false")){
            addToDB();
        }
        dataBaseAdapter =new DataBaseAdapter(Main_gzdd_Activity.this);
        //读取数据
        ArrayList<JobAddress> jobAddresses= dataBaseAdapter.findAll_gzdd();
        //将arraylist转换成数组
        String[] s = new String[jobAddresses.size()];

        for(int i= 0;i<jobAddresses.size();i++){
            s[i]=jobAddresses.get(i).getAddress();
        }
        /*for(int i= 0;i<s.length;i++){
            System.out.println(s[i]);
        }*/

        listview.setAdapter(new MyAdapter_gzdd(this, s, positionSet1));

    }


    /**
     * 把数据传出来
     */
    public void addPramchecked(){
        SharedPreferences.Editor editor = getSharedPreferences("Pram",MODE_PRIVATE).edit();
        editor.putString("checked",checked);
//        Log.d("TEST", "添加数据成功");
        editor.commit();
    }

    /**
     * 改变从主页面中传过来的false为ture
     */
    public void addPramTrueGzdd(){
        SharedPreferences.Editor editor = getSharedPreferences("Pram",MODE_PRIVATE).edit();
        editor.putString("P","true");
//        Log.d("TEST","更改成功");
        editor.commit();
    }

    /**
     * 来得到全项目变量的值
     * @return
     */
    public String getParmGzdd(){
        String p=null;
        SharedPreferences sharedPreferences = getSharedPreferences("Pram", MODE_PRIVATE);
        p = sharedPreferences.getString("P","");
//        Log.d("Test", p);
        return p;
    }

    /**
     * 添加数据到数据库
     */
    public  void  addToDB(){
        //添加数据到数据库

            System.out.println("添加数据");
            //建立新表,插入数据
            sqlHelper =new DataBaseHelper(Main_gzdd_Activity.this);
            System.out.println("11111");
//            sqlHelper.getWritableDatabase();
            //插入数据
            SQLiteDatabase db = sqlHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            //插入数据
            values.put("address","北京");
            db.insert("jobaddress_table", null, values);
            values.put("address", "北京");
            db.insert("jobaddress_table", null, values);
            values.put("address", "天津");
            db.insert("jobaddress_table", null, values);
            values.put("address", "上海");
            db.insert("jobaddress_table", null, values);
            values.put("address", "重庆");
            db.insert("jobaddress_table", null, values);
            values.put("address", "石家庄");
            db.insert("jobaddress_table", null, values);
            values.put("address", "唐山");
            db.insert("jobaddress_table", null, values);
            values.put("address", "秦皇岛");
            db.insert("jobaddress_table", null, values);
            values.put("address", "邯郸");
            db.insert("jobaddress_table", null, values);
            values.put("address", "邢台");
            db.insert("jobaddress_table", null, values);
            values.put("address", "保定");
            db.insert("jobaddress_table", null, values);
            values.put("address", "张家口");
            db.insert("jobaddress_table", null, values);
            values.put("address", "承德");
            db.insert("jobaddress_table", null, values);
            values.put("address", "沧州");
            db.insert("jobaddress_table", null, values);
            values.put("address", "廊坊");
            db.insert("jobaddress_table", null, values);
            values.put("address", "衡水");
            db.insert("jobaddress_table", null, values);
            values.put("address", "太原");
            db.insert("jobaddress_table", null, values);
            values.put("address", "大同");
            db.insert("jobaddress_table", null, values);
            values.put("address", "阳泉");
            db.insert("jobaddress_table", null, values);
            values.put("address", "长治");
            db.insert("jobaddress_table", null, values);
            values.put("address", "晋城");
            db.insert("jobaddress_table", null, values);
            values.put("address", "朔州");
            db.insert("jobaddress_table", null, values);
            values.put("address", "晋中");
            db.insert("jobaddress_table", null, values);
            values.put("address", "运城");
            db.insert("jobaddress_table", null, values);
            values.put("address", "忻州");
            db.insert("jobaddress_table", null, values);
            values.put("address", "临汾");
            db.insert("jobaddress_table", null, values);
            values.put("address", "吕梁");
            db.insert("jobaddress_table", null, values);
            values.put("address", "台北");
            db.insert("jobaddress_table", null, values);
            values.put("address", "高雄");
            db.insert("jobaddress_table", null, values);
            //改变全项目变量为true
            addPramTrueGzdd();
            System.out.println("添加成功");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_gzdd, menu);
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


    /**
     * Created by lenovo on 2015/10/18.
     * 实现工作地点列表的相关工作
     */
    class MyAdapter_gzdd extends BaseAdapter {
        private Set<Integer> positionSet  = new HashSet<Integer>();
        private Context context;
        private String[] addresses;
        private boolean[] checks;//用于保存checkbox的选中状态


        public MyAdapter_gzdd(Context context, String[] s,Set positionSet11) {
            this.context=context;
            //将数据传给addresses
            this.addresses = s;
            checks = new boolean[s.length];
        }



        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return addresses.length;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return addresses[arg0];
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View converView, ViewGroup arg2) {
            // TODO Auto-generated method stub

            ViewHolder vh;

            //可利用view为空，就是没有可用的，再去创建新的,减少了创建新的
            if(converView==null){
                LayoutInflater inflater = LayoutInflater.from(context);
                //实例化一个布局文件
                converView  = inflater.inflate(R.layout.list_item_gzdd, null);
                //搜索ID
                vh = new ViewHolder();
                vh.cb_text= (CheckBox) converView.findViewById(R.id.checkBox_gzdd);
                converView.setTag(vh);
            }else{
                //如果有则直接取得
                vh = (ViewHolder)converView.getTag();
            }
            //往多选中写入从数据库中得到的值
            vh.cb_text.setText(addresses[arg0]);

            vh.cb_text.setId(arg0);
            //解决多选框乱动
            final int pos  = arg0; //pos必须声明为final
            vh.cb_text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //这是解决读出被选择的多选框
                    if (isChecked) {        //记录或删除勾选的状态
                        positionSet.add(buttonView.getId());
                    } else {
                        positionSet.remove(buttonView.getId());
                    }
                    //将被选出的按钮的id传给全局变量
                    positionSet1 = positionSet;

                    System.out.println("全局变量");
                    System.out.println(positionSet1);
                    //将全局变量传给全项目变量
                    checked = positionSet1.toString();
                    System.out.println("进入addchecked方法");
                    addPramchecked();
                    //这是解决乱动
                    checks[pos] = isChecked;
                }
            });

            vh.cb_text.setChecked(checks[pos]);

            return converView;
        }


        //能不封装就不封装了 ，为了性能，保存ID
          class ViewHolder{
            CheckBox cb_text;
        }
    }


}
