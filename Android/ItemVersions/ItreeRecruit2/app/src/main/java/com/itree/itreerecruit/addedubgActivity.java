package com.itree.itreerecruit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itree.itreerecruit.ConDB.DataBaseAdapter;
import com.itree.itreerecruit.entity.Resume;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addedubgActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_ruxue,button_biye,b4,b5,b6;
    private EditText  schoolname_edit;
    private DataBaseAdapter dataBaseAdapter;
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
        dataBaseAdapter=new DataBaseAdapter(this);

        //获取按钮id
        button_ruxue= (Button) findViewById(R.id.ruxue);
        button_biye= (Button) findViewById(R.id.biye);
        schoolname_edit= (EditText) findViewById(R.id.schoolname_edit);
        button_ruxue.setOnClickListener(this);
        button_biye.setOnClickListener(this);

        SharedPreferences editor =  getSharedPreferences("update_resume", MODE_PRIVATE);
        String str=editor.getString("is_updateresume","");
        if(str.equals("update")){
            int id=editor.getInt("updateresumeid", 0);
            Resume resume=dataBaseAdapter.findbyid(id);
            System.out.print(resume);
            schoolname_edit.setText(resume.getSchool_name());
            button_ruxue.setText(resume.getStime());
            button_biye.setText(resume.getEtime());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addedubg, menu);
        return true;
    }
    /**
     * 判定输入汉字
     * @param c
     * @return
     */
    public  boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 检测String是否全是中文
     * @param name
     * @return
     */
    /*public  boolean checkNameChese(String name)
    {
        boolean res=true;
        char [] cTemp = name.toCharArray();
        for(int i=0;i<name.length();i++)
        {
            if(!isChinese(cTemp[i]))
            {
                res=false;
                break;
            }
        }
        return res;
    }*/



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
                finish();
                return true;
        }

        if (id == R.id.addedubg) {
            Boolean tag=checkNameChese(schoolname_edit.getText().toString());
            Resume resume=new Resume();
            resume.setSchool_name(schoolname_edit.getText().toString());
            resume.setStime(button_ruxue.getText().toString());
            resume.setEtime(button_biye.getText().toString());
            if(schoolname_edit.getText().toString().trim().isEmpty()&&!isDateStringValid(button_ruxue.getText().toString())&&
                    !isDateStringValid(button_biye.getText().toString())){
                Toast.makeText(this,"请前去补全信息",Toast.LENGTH_SHORT).show();
            }
            if(schoolname_edit.getText().toString().trim()!=null&&!isDateStringValid(button_ruxue.getText().toString())&&
                    !isDateStringValid(button_biye.getText().toString())){
                Toast.makeText(this,"请选择时间",Toast.LENGTH_SHORT).show();
            }
            if(schoolname_edit.getText().toString().trim().isEmpty()){
                Toast.makeText(this,"请输入院校名称",Toast.LENGTH_SHORT).show();
            }else if(tag==false){
                Toast.makeText(this,"输入内容必须为汉字,且学校名称不能超过15个字",Toast.LENGTH_SHORT).show();
            } else if(!isDateStringValid(button_ruxue.getText().toString())){
                Toast.makeText(addedubgActivity.this, "请选择入学时间", Toast.LENGTH_SHORT).show();
            }else if(!isDateStringValid(button_biye.getText().toString())){
                Toast.makeText(addedubgActivity.this, "请选择毕业时间", Toast.LENGTH_SHORT).show();
            }else {
                SharedPreferences editor =  getSharedPreferences("educate",MODE_PRIVATE);
                int _id1 = editor.getInt("educateid",0);
                System.out.println(_id1);
                resume.setResume_id(_id1);
                dataBaseAdapter.updateEducate(resume);
                ArrayList<Resume> list=dataBaseAdapter.findall();
                for(Resume s:list){
                    System.out.println(s);
                }
                Intent intent = new Intent();
                intent.setClass(addedubgActivity.this, creatjianliActivity.class);
                startActivity(intent);
                Toast.makeText(addedubgActivity.this, "数据插入成功", Toast.LENGTH_SHORT).show();
                return true;
                }
            }
        return super.onOptionsItemSelected(item);
    }
    public void setdatevalue_ryxue(int year,int month,int date){
        button_ruxue= (Button) findViewById(R.id.ruxue);
        button_ruxue.setText(year+"年"+(month+1)+"月"+date+"日");
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.ruxue:
               DatepickerFragment datepickerFragment=new DatepickerFragment();
                datepickerFragment.show(getFragmentManager(),"datepicker");
                break;
            case R.id.biye:
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
                        Button button_data = (Button) findViewById(R.id.biye);
                        button_data.setText(sb);
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
                    }
                }
        }
