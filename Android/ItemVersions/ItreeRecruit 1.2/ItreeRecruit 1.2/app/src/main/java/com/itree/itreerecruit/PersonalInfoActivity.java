package com.itree.itreerecruit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itree.itreerecruit.ConDB.DataBaseAdapter;
import com.itree.itreerecruit.ConDB.DatabaseHelper;
import com.itree.itreerecruit.entity.Resume;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonalInfoActivity extends AppCompatActivity {

    private Button btn ;
    private RadioGroup maleOrFemale;
    private RadioButton sex;
//    private Button birthday;
    private EditText userName;
    private TextView birthday;
    private EditText email;
    private EditText tel;
    private  String str;
    private String sexs;
     private DataBaseAdapter dataBaseAdapter;
    private DatabaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_personal_info);
        //实例化
        dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseHelper = new DatabaseHelper(this);
        //获取组件的id
        userName = (EditText) findViewById(R.id.EditText_userName);
        maleOrFemale = (RadioGroup) findViewById(R.id.maleOrFemale);
        email = (EditText) findViewById(R.id.btn_email);
        tel = (EditText) findViewById(R.id.btn_tel);
        birthday = (TextView) findViewById(R.id.birthday_text);

        maleOrFemale.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                sex = (RadioButton) findViewById(maleOrFemale.getCheckedRadioButtonId());
                sexs = sex.getText().toString();
            }
        });

        //保存按钮
        btn = (Button) findViewById(R.id.btn_saveAndNext);
        btn.setOnClickListener(listener);

        SharedPreferences editor =  getSharedPreferences("update_resume", MODE_PRIVATE);
        String username= editor.getString("is_updateresume", "");
        if(username.equals("update")){
            int id=editor.getInt("updateresumeid", 0);
            Resume resume = dataBaseAdapter.findbyid(id);
            userName.setText(resume.getName());
            birthday.setText(resume.getBirthday());
            email.setText(resume.getEmail());
            tel.setText(resume.getTel());
        }
    }

    /**
     * 添加简历
     */
    protected View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub


//            UserInfo userInfo = new UserInfo();
            String userNames = userName.getText().toString();
            String emails = email.getText().toString();
            String tels = tel.getText().toString();
            String birthdays = birthday.getText().toString();

            System.out.println(sexs);

            if(userNames.trim().isEmpty()||emails.trim().isEmpty()||tels.trim().isEmpty()||birthdays.trim().isEmpty()||sexs.trim().isEmpty()){
                Toast.makeText(PersonalInfoActivity.this,"对不起，您没有将信息填写全",Toast.LENGTH_SHORT).show();
            }else if( tels.trim().length()!=11){
                Toast.makeText(PersonalInfoActivity.this,"请输入11位手机号",Toast.LENGTH_SHORT).show();
            }else if(checkPhoneNum(tels)!=true){
                Toast.makeText(PersonalInfoActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            }else if(checkEmail(emails)!=true){
                Toast.makeText(PersonalInfoActivity.this,"请输入正确的email地址",Toast.LENGTH_SHORT).show();
            }else {
                SharedPreferences editor =  getSharedPreferences("educate",MODE_PRIVATE);
                int _id = editor.getInt("educateid", 0);
                Resume resume=new Resume();
                resume.setResume_id(_id);
                resume.setName(userNames);
                resume.setEmail(emails);
                resume.setTel(tels);
                resume.setBirthday(birthdays);
                resume.setSex(sexs);
                dataBaseAdapter.updateInfo(resume);
                Toast.makeText(PersonalInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(PersonalInfoActivity.this, addedubgActivity.class);
                startActivity(intent);
            }


        }
    };

    /**
     * 检查手机号
     * @param phoneNum
     * @return
     */
    public boolean checkPhoneNum(String phoneNum){

        //建立审核规范
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(phoneNum);

        String a = ""+m.matches();
        Log.d("Tag", a);
        return m.matches();
    }

    /**
     * 检查是否是正确的email地址
     * @param email
     * @return
     */
    public boolean checkEmail(String email){

        //建立审核规范
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

        Matcher m = p.matcher(email);

        String a = ""+m.matches();
        Log.d("Tag", a);
        return m.matches();
    }

    /**
     * 设置选择日期的对话框
     * @param
     */
    public void selectDate(View v ){
        View view = (LinearLayout)getLayoutInflater().inflate(R.layout.activity_select_date, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        new AlertDialog.Builder(this).setTitle("请选择您的出生日期").setView(view).setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //日期格式
                StringBuffer sb = new StringBuffer();
                sb.append(String.format("%d年%02d月%02d日", datePicker.getYear(),
                        datePicker.getMonth() + 1,
                        datePicker.getDayOfMonth()));
                birthday = (TextView) findViewById(R.id.birthday_text);
                birthday.setText(sb);
            }
        }).create().show();


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_info, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
