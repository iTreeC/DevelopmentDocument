package com.itree.itreerecruit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itree.itreerecruit.ConDB.DataBaseAdapter;
import com.itree.itreerecruit.ConDB.DatabaseHelper;
import com.itree.itreerecruit.dao.DataBaseHelper;
import com.itree.itreerecruit.entity.Resume;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addedubgActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_ruxue,button_biye,b4,b5,b6;
    private  View ruxue_layout,biye_layout;
    private TextView ruxue_text,biye_text;
    private EditText  schoolname_edit;
    private DataBaseAdapter dataBaseAdapter;
//    private DataBaseHelper dataBaseHelper;
    //定义显示时间控件
    private Calendar calendar; //通过Calendar获取系统时间
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_addedubg);
        //实例化数据库工具类
//        dataBaseAdapter =new DataBaseAdapter(addedubgActivity.this);
//        dataBaseHelper = new DatabaseHelper(addedubgActivity.this);

        //给layout添加点击事件
        ruxue_layout=findViewById(R.id.layout_intoschool);
        biye_layout=findViewById(R.id.layout_outschool);

        ruxue_layout.setOnClickListener(this);
        biye_layout.setOnClickListener(this);

        //获取按钮id
        ruxue_text= (TextView) findViewById(R.id.ruxue_text);
        biye_text= (TextView) findViewById(R.id.biye_text);
        schoolname_edit= (EditText) findViewById(R.id.schoolname_edit);

        SharedPreferences editor =  getSharedPreferences("update_resume", MODE_PRIVATE);
        String str=editor.getString("is_updateresume","");
        if(str.equals("update")){
            int id=editor.getInt("updateresumeid", 0);
            Resume resume=dataBaseAdapter.findById(id);
            System.out.print(resume);
            schoolname_edit.setText(resume.getSchool_name());
            ruxue_text.setText(resume.getStime());
            biye_text.setText(resume.getEtime());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addedubg, menu);
        return true;
    }
    /***
     * 验证中文名字
     * @param name
     * @return
     */
    public static boolean checkNameChese(String name) {
        Pattern pattern = Pattern.compile("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]){1,15}$");
        Matcher matcher = pattern.matcher(name);
        if(matcher.find()){
            return true;
        }
        return false;
    }
    /**
     * 判断是否是时间
     * @param date
     * @return
     */
    public boolean isDateStringValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月DD日");
        try {
            sdf.parse(date);
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }

        if (id == R.id.addedubg) {
            Boolean tag=checkNameChese(schoolname_edit.getText().toString());
            Resume resume=new Resume();
            resume.setSchool_name(schoolname_edit.getText().toString());
            resume.setStime(ruxue_text.getText().toString());
            resume.setEtime(biye_text.getText().toString());
            if(schoolname_edit.getText().toString().trim().isEmpty()&&!isDateStringValid(ruxue_text.getText().toString())&&
                    !isDateStringValid(biye_text.getText().toString())){
                Toast.makeText(this,"请前去补全信息",Toast.LENGTH_SHORT).show();
            }
            if(schoolname_edit.getText().toString().trim()!=null&&!isDateStringValid(ruxue_text.getText().toString())&&
                    !isDateStringValid(biye_text.getText().toString())){
                Toast.makeText(this,"请选择时间",Toast.LENGTH_SHORT).show();
            }
            if(schoolname_edit.getText().toString().trim().isEmpty()){
                Toast.makeText(this,"请输入院校名称",Toast.LENGTH_SHORT).show();
            }else if(tag==false){
                Toast.makeText(this,"输入内容必须为汉字,且学校名称不能超过15个字",Toast.LENGTH_SHORT).show();
            } else if(!isDateStringValid(ruxue_text.getText().toString())){
                Toast.makeText(addedubgActivity.this, "请选择入学时间", Toast.LENGTH_SHORT).show();
            }else if(!isDateStringValid(biye_text.getText().toString())){
                Toast.makeText(addedubgActivity.this, "请选择毕业时间", Toast.LENGTH_SHORT).show();
            }else {
                SharedPreferences editor = getSharedPreferences("educate", MODE_PRIVATE);
                int _id1 = editor.getInt("educateid", 0);
                System.out.println(_id1);
                resume.setResume_id(_id1);

                DbUtils db = DbUtils.create(addedubgActivity.this, "iTreeResume.db");

                //根据id查找
                Resume entity = null;
                try {
                    entity = db.findById(Resume.class, String.valueOf(_id1));
                    db.update(resume,entity.getSchool_name(),entity.getStime(),entity.getEtime(), "school_name","stime","etime");
                    //测试
                    /*entity = db.findById(Resume.class, String.valueOf(_id1));
                    System.out.print(entity.toString());*/
                    finish();
                    Toast.makeText(addedubgActivity.this, "数据插入成功", Toast.LENGTH_SHORT).show();
                } catch (DbException e) {
                    e.printStackTrace();
                    Toast.makeText(addedubgActivity.this, "数据插入失败", Toast.LENGTH_SHORT).show();
                }
            }

            }
        return super.onOptionsItemSelected(item);
    }
    public void setdatevalue_ryxue(int year,int month,int date){
        ruxue_text= (TextView) findViewById(R.id.ruxue_text);
        ruxue_text.setText(year+"年"+(month+1)+"月"+date+"日");
    }
   @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.layout_outschool:
                View view = (LinearLayout)getLayoutInflater().inflate(R.layout.date_dialogelayout,null);
                //创建builder
               AlertDialog.Builder builder=new AlertDialog.Builder(this);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
                //设置日期简略显示 否则详细显示 包括:星期周
                datePicker.setCalendarViewShown(true);
                builder.setTitle("毕业时间");
                builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //日期格式
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d年%02d月%02d日", datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));

                        //判断毕业时间必须大于入学时间

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月DD日");
                        Calendar c1=Calendar.getInstance();
                        Calendar c2=Calendar.getInstance();
                        try {
                            c1.setTime(sdf.parse(ruxue_text.getText().toString()));
                            c2.setTime(sdf.parse(String.valueOf(sb)));
                            int result=c1.compareTo(c2);
                            if(result<0){
                                System.out.println("ahskjahkjdfhdskjfhsjd");
                                Toast.makeText(addedubgActivity.this,"ahskjafadk",Toast.LENGTH_SHORT).show();
                            }else{
                                biye_text = (TextView) findViewById(R.id.biye_text);
                                biye_text.setText(sb);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setView(view).create().show();
                break;
            case R.id.layout_intoschool:
                DatepickerFragment datepickerFragment=new DatepickerFragment();
                datepickerFragment.show(getFragmentManager(), "datepicker");
                break;
                    }
                }
        }
