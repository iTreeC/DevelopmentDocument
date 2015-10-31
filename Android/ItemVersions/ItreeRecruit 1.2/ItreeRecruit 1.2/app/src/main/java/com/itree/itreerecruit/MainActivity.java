package com.itree.itreerecruit;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itree.itreerecruit.ConDB.DataBaseAdapter;


public class MainActivity extends AppCompatActivity {

    private Button btn4  = null;
    private Button btn5  = null;
    private Button btnLogin  = null;

    private Button bt_gzdd = null;
    private Button bt_hyll = null;
    private Button bt_ss=null;
    //建立消息队列
    private static RequestQueue requestQueue;

    private TextView resume;
    private DataBaseAdapter dataBaseAdapter;
    ViewFlipper viewFlipper = null;
    float startX;
    private int perNum;//权限值
    //测试其他

   /* private TabHost.TabSpec tabSpec;
    private TabHost tabHost;
    private Intent intent;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;*/

    int currentView = 0;
    private static int maxTabIndex = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //获取volley消息队列
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        TabHost tabhost = (TabHost)findViewById(R.id.tabhost);
        tabhost.setup();
     /* //  UtilVar.activities.add(MainActivity.this);
       // this.initTableHost();

        gestureDetector = new GestureDetector(new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        };*/

        //动态载入XML，而不需要Activity
        LayoutInflater i= LayoutInflater.from(this);
        i.inflate(R.layout.searchjob, tabhost.getTabContentView());
        i.inflate(R.layout.myzhilian, tabhost.getTabContentView());
        i.inflate(R.layout.myresume, tabhost.getTabContentView());

        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("职位搜索").setContent(R.id.LinearLayout_SerachJob));
        tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("我的智联").setContent(R.id.LinearLayout_MyZhiLian));
        tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("我的简历").setContent(R.id.LinearLayout_MyResume));


//      获取查看简历、添加简历、删除简历、更新简历按钮
        btn4 = (Button)findViewById(R.id.button_location);
        //采用隐藏而非创建的方式进行 功能有无的控制 针对我的智联

        perNum =getGroupPer();
        if(perNum==0||perNum==1){
            hiddenGroup(1);
            hiddenGroup(2);
        }else if(perNum==2){
            hiddenGroup(2);
        }


        //增加、修改、查询简历
        setAdd();
        setRefresh();
        setSelresume();

        //

//      跳转按钮
        btn5 = (Button)findViewById(R.id.toIp);

        btnLogin = (Button)findViewById(R.id.toLogin);


        btn4.setOnClickListener(listener4);
        btn5.setOnClickListener(listener5);


//        btnLogin.setOnClickListener(listenerLogin);

        //职位搜索页面中的按钮点击事件
        bt_gzdd = (Button)findViewById(R.id.button_location);
        bt_gzdd.setOnClickListener(listener_gzdd);
        bt_hyll = (Button)findViewById(R.id.button_hangye);
        bt_hyll.setOnClickListener(listener_hyll);
        bt_ss = (Button) findViewById(R.id.button_select);
        bt_ss.setOnClickListener(listener_ss);
        init();
    }
   /* 左右滑动刚好页面也有滑动效果
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
           // TabHost tabHost = getTabHost();
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.i("test", "right");
                    if (currentView == maxTabIndex) {
                        currentView = 0;
                    } else {
                        currentView++;
                    }
                    tabHost.setCurrentTab(currentView);
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.i("test", "left");
                    if (currentView == 0) {
                        currentView = maxTabIndex;
                    } else {
                        currentView--;
                    }
                    tabHost.setCurrentTab(currentView);
                }
            } catch (Exception e) {
            }
            return false;
        }*//*
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)) {
            event.setAction(MotionEvent.ACTION_CANCEL);
        }
        return super.dispatchTouchEvent(event);
    }*/


    /**
 *获取权限值
 */
    public int getGroupPer(){
        int per=0;
        SharedPreferences editor =  getSharedPreferences("login",MODE_PRIVATE);
        String ed_userPassword = editor.getString("per", "");
        if(ed_userPassword.equals("")||ed_userPassword.isEmpty()){
            per=0;
        }else{
            per = Integer.parseInt(ed_userPassword);
        }
        return per;
    }

    /**
     * @author guanjiwei
     * @effec 控制显示等级
     */
    public void showFun(int grade,int id){
        //获取所有需要隐藏的button
        switch(grade){
            case 1:
                hiddenGroup(id);
                break;
            case 2:
                hiddenObject(id);
                break;

        }
    }
    /**
     * @author guanjiwei
     * @effec group的隐藏
     */
    public void hiddenGroup(int id){
        LinearLayout group;
        switch(id){
            case 1:
                group = (LinearLayout)findViewById(R.id.Group_Show1);
                group.setVisibility(View.GONE);
                break;
            case 2:
                group = (LinearLayout)findViewById(R.id.Group_Show2);
                group.setVisibility(View.GONE);
                break;
        }

    }
    public void hiddenObject(int id){
        Button button ;
        switch (id){
            case 2:
                button =(Button)findViewById(R.id.testShow2);
                button.setVisibility(View.GONE);
                break;
            case 3:
                button =(Button)findViewById(R.id.testShow3);
                button.setVisibility(View.GONE);
                break;
            case 4:
                button =(Button)findViewById(R.id.testShow4);
                button.setVisibility(View.GONE);
                break;
            case 5:
                button =(Button)findViewById(R.id.testShow5);
                button.setVisibility(View.GONE);
                break;
            case 6:
                button =(Button)findViewById(R.id.testShow6);
                button.setVisibility(View.GONE);
                break;
        }
    }
    //职位搜索的相关点击事件
    /**
     * volley 的get方法
     * 用于与网络服务连接使用
     */
    public void volley_get(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("Tag", s);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag",volleyError.getMessage());
            }
        });
        //插入请求队列
        requestQueue.add(stringRequest);
    }

    /**
     * 得到全项目变量
     * @return
     */
    public String getPramcheckbox(){
        SharedPreferences editor = getSharedPreferences("Pram",MODE_PRIVATE);
        String checked = editor.getString("checked","");
        System.out.println("得到全项目变量");
        System.out.println(checked);
        return checked;
    }
    /**
     * 搜索按钮的点击事件
     */
    protected View.OnClickListener listener_ss = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            //获取被选的多选框的id
            getPramcheckbox();

        }
    };
    /**
     * 增加控制变元 全项目变量
     */

    public void addPramGzdd(){
        SharedPreferences.Editor editor = getSharedPreferences("Pram",MODE_PRIVATE).edit();
        editor.putString("P","false");
        editor.commit();
    }

    /**
     * 得到全项目变量-来看是否第一次获取了地点列表
     * @return
     */
    public String getPramGzdd(){
        SharedPreferences editor = getSharedPreferences("Pram",MODE_PRIVATE);
        String p = editor.getString("P","");
        return p;
    }

    /**
     * 工作地点的跳转事件
     */
    protected View.OnClickListener listener_gzdd = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            Intent intent = new Intent();
            //来实现第一次进入工作地点，添加数据
            if(getPramGzdd().equals("") || getPramGzdd().equals("false")){
                //使p成为false
                addPramGzdd();
                intent.setClass(MainActivity.this, Main_gzdd_Activity.class);
                startActivity(intent);
            }else{
//                Toast.makeText(MainActivity.this,"已经加入",Toast.LENGTH_SHORT).show();
                intent.setClass(MainActivity.this, Main_gzdd_Activity.class);
            }   startActivity(intent);


        }
    };

    /**
     * 职业类别的跳转事件
     */
    protected View.OnClickListener listener_zyll = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Main_zwll_Activity.class);
            startActivity(intent);
        }
    };
    /**
     * 增加控制变元 全项目变量
     */

    public void addPramHyll(){
        SharedPreferences.Editor editor = getSharedPreferences("Pram",MODE_PRIVATE).edit();
        editor.putString("q","false");
        editor.commit();
    }

    /**
     * 得到全项目变量
     * @return
     */
    public String getPramHyll(){
        SharedPreferences editor = getSharedPreferences("Pram",MODE_PRIVATE);
        String p = editor.getString("q","");
        return p;
    }
    /**
     * 行业类别的跳转事件
     */
    protected View.OnClickListener listener_hyll = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            //来实现第一次进入工作地点，添加数据
            if(getPramHyll().equals("") || getPramHyll().equals("false")){
                //使p成为false
                addPramHyll();
                intent.setClass(MainActivity.this, Main_hyll_Activity.class);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this,"已经加入",Toast.LENGTH_SHORT).show();
                intent.setClass(MainActivity.this, Main_hyll_Activity.class);
            }   startActivity(intent);
        }
    };
    private void init() {
        viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper );
    }

    /**
     * @author guanjiwei
     * @effec 增加 增加简历功能
     */
    public void setAdd(){
        ImageButton btn_add = (ImageButton)findViewById(R.id.btn_createResume);
        btn_add.setOnClickListener(listener);
    }
    /**
     * @author guanjiwei
     * @effec 增加 查询简历功能
     */
    public void setSelresume(){
        ImageButton btn_selresume = (ImageButton)findViewById(R.id.btn_ResumeView);
        btn_selresume.setOnClickListener(listener1);
    }
    /**
     * @author guanjiwei
     * @effec 增加 修改功能
     */
    public void setRefresh(){
        ImageButton btn_refresh = (ImageButton)findViewById(R.id.btn_RefreshResume);
        btn_refresh.setOnClickListener(listener6);
    }
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_UP:

                if (event.getX() > startX) { // 向右滑动
                    viewFlipper.setInAnimation(this, R.anim.push_right_in);
                    viewFlipper.setOutAnimation(this, R.anim.push_right_out);
                    viewFlipper.showNext();
                } else if (event.getX() < startX) { // 向左滑动
                    viewFlipper.setInAnimation(this, R.anim.push_left_in);
                    viewFlipper.setOutAnimation(this, R.anim.push_left_out);
                    viewFlipper.showPrevious();
                }
                break;
        }

        return super.onTouchEvent(event);
    }


    /**
     *@author：guanjiwei
     * 用于进入登入页面
     */
    public void goLogin(){

        goClass(MainActivity.this, LoginActivity.class);

    }
    /**
     * @author guanjiwei
     * @effect 判断转向登录的所有条件
     */
    public boolean checkLogin(){
        //获取SharedPreferences域中的数据
        SharedPreferences login = getSharedPreferences("login",MODE_PRIVATE);
        String user = login.getString("user","").trim();
        //比对用户是否为空，空则转向登录
        if(user.equals("") || user.isEmpty()){
            return false;
        }else{
            return true;
        }

    }
    /**
     * @author：guanjiwei
     * @param context
     * @param cla
     * @effect 用于专项其他界面
     */
    public void goClass(Context context,Class cla){
        Intent intent = new Intent();
        intent.setClass(context,cla);
        startActivity(intent);
    }





    protected View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            if(checkLogin()){
                SharedPreferences.Editor editor =  getSharedPreferences("update_resume",MODE_PRIVATE).edit();
                editor.putString("is_updateresume","addresume");
                editor.commit();


                Intent intent = new Intent();
                intent.setClass(MainActivity.this,CreateresumeActivity.class);
                startActivity(intent);
            }else{
                goLogin();
            }

        }
    };
    protected View.OnClickListener listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            if(checkLogin()){
                SharedPreferences.Editor editor =  getSharedPreferences("resume",MODE_PRIVATE).edit();
                editor.putString("check","select");
                editor.commit();
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, ResumeActivity.class);
                startActivity(intent);
            }else{
                goLogin();
            }

        }
    };
    protected View.OnClickListener listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, PersonalInfoActivity.class);
            startActivity(intent);
        }
    };

    protected View.OnClickListener listener4 = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Log.d("ADD","进入增加按钮");
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Main_gzdd_Activity.class);
            startActivity(intent);
        }
    };
    protected View.OnClickListener listener5 = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            if(checkLogin()){
                goClass(MainActivity.this,UserPermissions.class);
                /*Intent intent = new Intent();
                intent.setClass(MainActivity.this,UserPermissions.class);
                startActivity(intent);*/
            }else{
                goLogin();
            }
        }
    };
    protected View.OnClickListener listener6 = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            if(checkLogin()){
                SharedPreferences.Editor editor =  getSharedPreferences("resume",MODE_PRIVATE).edit();
                editor.putString("check","update");
                editor.commit();
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, ResumeActivity.class);
                startActivity(intent);
            }else{
                goLogin();
            }

        }
    };
    protected View.OnClickListener listenerLogin = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            goLogin();
            showGroup(1);
        }
    };
    /**
     * @author guanjiwei
     * @effec group的显示
     */
    public void showGroup(int id){
        LinearLayout group;
        switch(id){
            case 1:
                group = (LinearLayout)findViewById(R.id.Group_Show1);
                group.setVisibility(View.VISIBLE);
                break;
            case 2:
                group = (LinearLayout)findViewById(R.id.Group_Show2);
                group.setVisibility(View.VISIBLE);
                break;
        }

    }
    //当个显示
    public void showObject(int id){
        switch (id){
            case 2:
                Button button =(Button)findViewById(R.id.toIp);
                button.setVisibility(View.GONE);
                break;
            case 3:
                button =(Button)findViewById(R.id.testShow3);
                button.setVisibility(View.VISIBLE);
                break;
            case 4:
                button =(Button)findViewById(R.id.testShow4);
                button.setVisibility(View.VISIBLE);
                break;
            case 5:
                button =(Button)findViewById(R.id.testShow5);
                button.setVisibility(View.VISIBLE);
                break;
            case 6:
                button =(Button)findViewById(R.id.testShow6);
                button.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(checkLogin()) {
            menu.getItem(0).setTitle("注销");
        }else{
            menu.getItem(0).setTitle("登录");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        int id = item.getItemId();
//        Intent intent = new Intent();
        switch (id)
        {
            case R.id.action_login:
                if(item.getTitle().equals("登录")){
                    goLogin();
                }else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.tishi_xiaoxi)
                            .setMessage(R.string.tishi_massage)
                            .setPositiveButton(R.string.tishi_ok, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    goLogout();
                                    goClass(MainActivity.this, MainActivity.class);
                                    finish();
                                }
                            })
                            .setNegativeButton(R.string.tishi_cancle, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    Log.d("Test","quxiao");

                                }
                            }).create().show();

                }

                break;
            case R.id.action_advices:
                Toast.makeText(MainActivity.this,"对不起，该功能未开发",Toast.LENGTH_SHORT).show();
                break;
//            case R.id.action_message:
//                Toast.makeText(MainActivity.this,"对不起，该功能未开发",Toast.LENGTH_SHORT).show();
//                break;
            case R.id.action_select:
                Toast.makeText(MainActivity.this,"对不起，该功能未开发",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_money:
                Toast.makeText(MainActivity.this,"对不起，该功能未开发",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_recommend:
                Toast.makeText(MainActivity.this,"对不起，该功能未开发",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goLogout() {
        SharedPreferences.Editor editor = getSharedPreferences("login",MODE_PRIVATE).edit();
        editor.putString("user","");
        editor.putString("per","0");
        editor.commit();
    }
}
