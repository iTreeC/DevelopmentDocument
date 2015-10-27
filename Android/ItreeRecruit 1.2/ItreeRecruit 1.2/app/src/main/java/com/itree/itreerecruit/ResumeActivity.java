package com.itree.itreerecruit;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.itree.itreerecruit.ConDB.DataBaseAdapter;
import com.itree.itreerecruit.ConDB.DatabaseHelper;
import com.itree.itreerecruit.entity.Resume;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

/**
 * 查询简历
 */
public class ResumeActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper databasehelper;
    private DataBaseAdapter dataBaseAdapter;

    private Resume resume;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        SharedPreferences editor =  getSharedPreferences("resume",MODE_PRIVATE);
        String str=editor.getString("check", "");
        System.out.println(str);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //初始化
        dataBaseAdapter=new DataBaseAdapter(this);
        databasehelper =new DatabaseHelper(this);
        listView=new ListView(this);
        listView = (ListView) findViewById(R.id.listview1);
        //注册上下文
        if(!str.equals("select")){
            registerForContextMenu(listView);
        }
        try {
         changed();
        } catch (Exception e) {
            Log.i("exception", e.toString());
        }
    }
    public void changed(){
        Cursor cursor=dataBaseAdapter.find();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(ResumeActivity.this, R.layout.intension,cursor,
                new String[]{"_id", "resume_name","name", "sex","telphone", "email","birthday","school_Name","start_Time","end_Time"},
                new int[]{R.id.resume_id, R.id.resume_name, R.id.stu_name, R.id.stu_sex, R.id.stu_tel, R.id.stu_height, R.id.text_bir, R.id.school_name, R.id.start_time, R.id.end_time});
        listView.setAdapter(adapter);
    }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);

                menu.setHeaderTitle("请选择操作类型");
            getMenuInflater().inflate(R.menu.menu_resume, menu);
        }
        @Override
        public boolean onContextItemSelected(MenuItem item) {
            switch (item.getItemId()){

                case R.id.delete_item:
                    AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    TextView textView_id= (TextView) info.targetView.findViewById(R.id.resume_id);
                    int id=Integer.parseInt(textView_id.getText().toString());
                    System.out.println(id);
                    dataBaseAdapter.delete(id);
                    Toast.makeText(ResumeActivity.this,"删除数据成功",Toast.LENGTH_SHORT).show();
                    changed();
                    break;
                case R.id.update_item:
                    info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    textView_id= (TextView) info.targetView.findViewById(R.id.resume_id);
                    id=Integer.parseInt(textView_id.getText().toString());
                    System.out.println(id + "sdsfdfdfgsddffgffg");

                    SharedPreferences.Editor editor =  getSharedPreferences("update_resume",MODE_PRIVATE).edit();
                    editor.putInt("updateresumeid",id);
                    editor.putString("is_updateresume","update");
                    editor.commit();

////                    Resume resume=dataBaseAdapter.findbyid(id);
//                    System.out.println(resume);
                    Intent intent=new Intent();
                    intent.setClass(ResumeActivity.this,creatjianliActivity.class);
                    startActivity(intent);
                    break;
                case R.id.load_item:
                    info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    textView_id= (TextView) info.targetView.findViewById(R.id.resume_id);
                    id=Integer.parseInt(textView_id.getText().toString());
                    resume = dataBaseAdapter.findbyid(id);
                    //根据id获取所有数据
                    String birthday = resume.getBirthday();
                    String schoolName = resume.getSchool_name();
                    String name = resume.getName();
                    String eTime = resume.getEtime();
                    String sTime = resume.getStime();
                    String email = resume.getEmail();
                    String resumeName = resume.getResume_name();
                    String sex = resume.getSex();
                    String tel = resume.getTel();
                    System.out.println(schoolName);
//                    if(tel.isEmpty()||birthday.isEmpty()||schoolName.isEmpty()||name.isEmpty()||eTime.isEmpty()||sTime.isEmpty()||resumeName.isEmpty()||email.isEmpty()||sex.isEmpty()){
//                        Toast.makeText(ResumeActivity.this, "简历不完整哦，请将简历进行完善", Toast.LENGTH_LONG).show();
//                        break;
//                    }else{
                        String url = "http://1.crazyteam5.sinaapp.com";
                        AsyncHttpClient client = new AsyncHttpClient();
                        RequestParams params = new RequestParams();

                        //将数据传入parame
                        params.put("birthday",birthday);
                        params.put("schoolName",schoolName);
                        params.put("name",name);
                        params.put("eTime",eTime);
                        params.put("sTime",sTime);
                        params.put("email",email);
                        params.put("resumeName",resumeName);
                        params.put("sex",sex);
                        params.put("tel", tel);

                        client.post(url, params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                Toast.makeText(ResumeActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onFailure(int statusCode, Header[] headers,
                                                  byte[] responseBody, Throwable error) {
                                Toast.makeText(ResumeActivity.this, "失败", Toast.LENGTH_LONG).show();
                            }
                        });
                        break;

//                    }
            }
            return super.onContextItemSelected(item);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resume, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent();

        if (id == R.id.action_settings) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        switch (item.getItemId()){
            case R.id.bar_chuangjian:
                intent.setClass(this,creatjianliActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
