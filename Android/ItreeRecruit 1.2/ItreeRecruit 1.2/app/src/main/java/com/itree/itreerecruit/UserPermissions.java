package com.itree.itreerecruit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.itree.itreerecruit.userper_utill.CheckUtil;
import com.itree.itreerecruit.userper_utill.RwStringRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPermissions extends AppCompatActivity {
    //建立消息队列
    private static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_permissions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //获取volley消息队列
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        //设置按钮的可视性
        Button user_per_city = (Button)findViewById(R.id.user_per_city_select);
        user_per_city.setVisibility(View.GONE);

        //创建按钮监听事件
        buttonOnClickListen();

    }

    /**
     * volley 的get方法
     * 用于与网络服务连接使用
     */
    public void volley_get(String url){
        RwStringRequest rwStringRequest = new RwStringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Toast.makeText(UserPermissions.this,s,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag",volleyError.getMessage());
            }
        });
        //插入数据头
        rwStringRequest.setHeaders("apikey", "6b2090e1e7e974f79b1e3ba40461f3dc");
        //插入请求队列
        requestQueue.add(rwStringRequest);
    }

    /**
     *
     */
    public void buttonOnClickListen() {
        //先点击按钮，然后获取手机号
        //注册按钮
        Button user_per_phone_select = (Button) findViewById(R.id.user_per_phone_select);
//        Button user_per_cood_select = (Button) findViewById(R.id.user_per_cood_select);

        //创建按钮监听事件
        Button.OnClickListener user_per_btn_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.user_per_phone_select:
                        //间接赋值可能导致出错
                        EditText phone_num = (EditText)findViewById(R.id.user_per_phone_num);
                        String phoneNum = phone_num.getText().toString();
                        //使用地址
                        String url = "http://apis.baidu.com/showapi_open_bus/mobile/find?num="+phoneNum;
                        if(phoneNum.trim().length()==0){
                            Toast.makeText(UserPermissions.this,"手机号不能为空",Toast.LENGTH_LONG).show();
                            phone_num.setText("");
                        }else{
                            if(phoneNum.trim().length()!=11){
                                Toast.makeText(UserPermissions.this,"请输入11位手机号",Toast.LENGTH_LONG).show();
                            }else{
                                if(CheckUtil.checkPhoneNum(phoneNum)!=true){
                                    Toast.makeText(UserPermissions.this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(UserPermissions.this,"请稍等",Toast.LENGTH_SHORT).show();
                                    volley_get(url);
                                }
                            }
                        }

                        break;
                }
            }
        };
        user_per_phone_select.setOnClickListener(user_per_btn_listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_permissions, menu);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
