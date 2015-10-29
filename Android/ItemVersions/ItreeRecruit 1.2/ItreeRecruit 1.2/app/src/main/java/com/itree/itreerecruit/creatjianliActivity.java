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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.itree.itreerecruit.ConDB.DataBaseAdapter;
import com.itree.itreerecruit.entity.Resume;


public class creatjianliActivity extends AppCompatActivity {
    private Button ed_button, weimingming_button, userinfo;
    private DataBaseAdapter dbAdapter;
    private EditText Missname_edit;
    private String str=null;
    private int id;
    private int _id;
    private Boolean click = false;
    Intent intent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatjianli);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        dbAdapter = new DataBaseAdapter(this);
        ed_button = (Button) findViewById(R.id.edu_button);
        weimingming_button= (Button) findViewById(R.id.missname_button);
        userinfo = (Button) findViewById(R.id.Userinfo_button);
        /*Resume resume=new Resume();
        resume.setResume_name(weimingming_button.getText().toString());
        dbAdapter.addResumeNmae(resume);
        Resume r = dbAdapter.findid(weimingming_button.getText().toString());
        _id = r.getResume_id();
        System.out.println(_id);*/
        ed_button.setEnabled(false);
        userinfo.setEnabled(false);

        init();

        ed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                if (str.equals("update")) {
                    SharedPreferences.Editor editor = getSharedPreferences("up", MODE_PRIVATE).edit();
                    editor.putInt("id1", id);
                    editor.putString("is_updateedu", "update1");
                    editor.commit();

                    intent.setClass(creatjianliActivity.this, addedubgActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(creatjianliActivity.this, addedubgActivity.class);
                    startActivity(intent);
                }

            }
        });
        userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                if(str.equals("update")) {
                    intent.setClass(creatjianliActivity.this, PersonalInfoActivity.class);
                    startActivity(intent);
                }else{
                    intent.setClass(creatjianliActivity.this, PersonalInfoActivity.class);
                    startActivity(intent);
                }
            }
        });

        weimingming_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        //获取自定义布局的layout
            final RelativeLayout relativeLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.missnamelayout, null);
            //创建builder
            AlertDialog.Builder builder = new AlertDialog.Builder(creatjianliActivity.this);
            builder.setTitle("修改简历名称");
            builder.setView(relativeLayout);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
             builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {



                     Resume resume = new Resume();
                     System.out.println(str + "asf sdf sfg fdg f hg ");
                     Missname_edit = (EditText) relativeLayout.findViewById(R.id.Miss_resumename);
                     if (str.equals("update")) {
                         weimingming_button.setText(Missname_edit.getText().toString());
                         resume.setResume_name(Missname_edit.getText().toString());
                         resume.setResume_id(id);
                         dbAdapter.updateResumename(resume);
                         Toast.makeText(creatjianliActivity.this, "更新成功", Toast.LENGTH_SHORT).show();

                     } else {
                         String name = Missname_edit.getText().toString();
                         if (name.trim().isEmpty()) {
                             Toast.makeText(creatjianliActivity.this, "请输入简历名称", Toast.LENGTH_SHORT).show();
                             return;
                         }
                         weimingming_button.setText(name);
                         resume.setResume_name(name);
                         dbAdapter.addResumeNmae(resume);
                         Resume r = dbAdapter.findid(name);
                         _id = r.getResume_id();
                         System.out.println(_id);

                         //设置按钮可点否
                         click =true;
                         ed_button.setEnabled(true);
                         userinfo.setEnabled(true);
                         weimingming_button.setText(Missname_edit.getText().toString());
                         resume.setResume_name(Missname_edit.getText().toString());
                         resume.setResume_id(_id);
                         dbAdapter.updateResumename(resume);
                         //获取SharedPreferences对象
                         SharedPreferences.Editor editor = getSharedPreferences("educate", MODE_PRIVATE).edit();
                         editor.putInt("educateid", _id);
                         editor.putString("res_name", name);
                         editor.commit();
                         System.out.println(_id);
                         Toast.makeText(creatjianliActivity.this, "简历名称保存成功", Toast.LENGTH_SHORT).show();
                         dialog.dismiss();

                     }

                 }
             });
             builder.create().show();
            }
        });

    }

    public void init(){
        SharedPreferences editor = getSharedPreferences("update_resume", MODE_PRIVATE);
        str = editor.getString("is_updateresume", null);
        id = editor.getInt("updateresumeid", 0);
        if (str.equals("update")) {
            Resume resume = dbAdapter.findbyid(id);
            System.out.print(resume);
            weimingming_button.setText(resume.getResume_name());
            click =true;
            ed_button.setEnabled(true);
            userinfo.setEnabled(true);
//            Intent intent = new Intent();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_creatjianli, menu);
        return true;
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

