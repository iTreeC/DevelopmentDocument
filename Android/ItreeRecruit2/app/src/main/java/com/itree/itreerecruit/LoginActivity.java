package com.itree.itreerecruit;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.cndemoz.avalidations.EditTextValidator;
import com.cndemoz.avalidations.ValidationModel;
import com.itree.itreerecruit.entity.User;
import com.itree.itreerecruit.userper_utill.CheckUtil;
import com.itree.itreerecruit.validations.passwordValidation;
import com.itree.itreerecruit.validations.userNameValidation;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.security.Signature;
import java.util.List;

import static com.itree.itreerecruit.userper_utill.CheckUtil.checkEmail;
import static com.itree.itreerecruit.userper_utill.CheckUtil.checkPhoneNum;

public class LoginActivity extends AppCompatActivity {

	private EditText loginUsername;
	private EditText loginPassword;
	private EditText SignUsername;
	private EditText signPassword;
	private Button loginButton;
	private Button signButton;
	private EditTextValidator editTextValidator;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginorsign);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		TabHost tabhost = (TabHost)findViewById(R.id.tabhost1);
		tabhost.setup();  

	    LayoutInflater i=LayoutInflater.from(this);  
	    i.inflate(R.layout.activity_login, tabhost.getTabContentView());
	    i.inflate(R.layout.sign, tabhost.getTabContentView());
	    
	          
	    tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("登录").setContent(R.id.LinearLayout_login));
	    tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("注册").setContent(R.id.LinearLayout_sign));

		loginInit();
		signInit();
	}

	/**
	 * 登录界面的初始化方法
	 * @author 苑雪元
	 */
	public void loginInit(){
		loginUsername = (EditText) findViewById(R.id.user_name);
		loginPassword = (EditText) findViewById(R.id.user_password);
		loginButton = (Button) findViewById(R.id.user_login);

		loginButton.setOnClickListener(loginListener);

		editTextValidator = new EditTextValidator(this)
				.setButton(loginButton)
				.add(new ValidationModel(loginUsername,new userNameValidation()))
				.add(new ValidationModel(loginPassword,new passwordValidation()))
				.execute();
	}

	/**
	 * 注册界面的初始化方法
	 * @author 苑雪元
	 */
	public void signInit(){
		SignUsername = (EditText) findViewById(R.id.user_login_name);
		signPassword = (EditText) findViewById(R.id.user_login_check);
		signButton = (Button) findViewById(R.id.user_login_login);

		signButton.setOnClickListener(loginListener);

		editTextValidator = new EditTextValidator(this)
				.setButton(signButton)
				.add(new ValidationModel(SignUsername, new userNameValidation()))
				.add(new ValidationModel(signPassword, new passwordValidation()))
				.execute();
	}

	/**
	 * @author guanjiwei
	 * @test 测试连接数据库
	 */
	public void storedUser(){

		//连接数据库
		DbUtils db = DbUtils.create(this);
		//
		int p=0;
		int g=0;
		//创建实例

		User user = new User();
		//设置变量

		//获取数据
		TextView userName = (TextView)findViewById(R.id.user_login_name);
		TextView userPwd = (TextView)findViewById(R.id.user_login_check);
		String user_Name = userName.getText().toString().toString();
		String user_Pwd= userPwd.getText().toString().toString().trim();

		//查重

		//解析数据
		if(checkPhoneNum(user_Name)){
			if(iscz(user_Name)){
				Toast.makeText(this,"用户名已存在",Toast.LENGTH_SHORT).show();
			}else {
				Log.d("yundong","开始");
				user.setTel(user_Name);
				p=1;
			}
		}else{
			Toast.makeText(this,"用户名有误",Toast.LENGTH_SHORT).show();
		}

		//解析密码
		if(user_Pwd.equals("")){
			Toast.makeText(this,"密码不可为空",Toast.LENGTH_SHORT).show();
		}else{
			user.setPassword(user_Pwd);
			Log.d("yundong", "开始储存");
			g=1;
		}

		if(g==1&&p==1){
			try {
				db.save(user);
				Toast.makeText(this,user_Name+"注册成功",Toast.LENGTH_SHORT).show();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}

		//存入数据库


	}

	//判断存在,cunzai 存在问题
	public boolean iscz(String userName){
		List<User> list = null;
		DbUtils db = DbUtils.create(this);
		try {
			list = db.findAll(User.class);
		} catch (DbException e) {
			e.printStackTrace();
		}

		if(list==null){

		}else{
			for(User u:list){
				if(u.getTel().equals(userName)){
					return true;
				}
			}
		}
		return false;
	}
		View.OnClickListener loginListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch(v.getId()){
					case R.id.user_login:
						if(checkLogin()){
							if (editTextValidator.validate()) {
								Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
								goClass(LoginActivity.this,MainActivity.class);
								finish();
							}

						}else{
							Toast.makeText(LoginActivity.this,"用户账号或密码不正确",Toast.LENGTH_SHORT).show();
						}
					break;
					case R.id.user_login_login:
						storedUser();
						break;
				}

			}
		};


	/**
	 * @author guanjiwei
	 * @ecffec 从数据库进行取值
	 */
	public String getValue(String user_Name){
		DbUtils dbUtils = DbUtils.create(this);
		User user =new User();
		//获取数据
//		TextView userName = (TextView) findViewById(R.id.user_login);
//		String user_Name = userName.getText().toString();
		if(checkPhoneNum(user_Name)){
			try {
				user =	dbUtils.findFirst(Selector.from(User.class).where("tel", "=", user_Name));
				Log.d("VALUE",user.getPassword());
			} catch (DbException e) {
				e.printStackTrace();
			}
		}else if(checkEmail(user_Name)){
			try {
				user = dbUtils.findFirst(Selector.from(User.class).where("email", "=", user_Name));
				Log.d("VALUE",user.getPassword());
			} catch (DbException e) {
				e.printStackTrace();
			}
		}else{
			return "4567898797";
		}
		return user.getPassword();
	}
	/**
	 * @author chenmeng
	 * @effect 监听textview双重限定
	 */
	/*public void textViewJudge(){
		//获取三个对象
		final EditText userName = (EditText) findViewById(R.id.user_name);
		final EditText userPwd = (EditText) findViewById(R.id.user_password);
		final Button userlogin = (Button) findViewById(R.id.user_login);

		final String user_Name =userName.getText().toString().trim();
		final String user_Pwd =userPwd.getText().toString().trim();

		//初始化判断
		if (TextUtils.isEmpty(userPwd.getText())) {
			userlogin.setEnabled(false);
		} else {
			userlogin.setEnabled(true);
		}

		userName.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				 String user_Name =userName.getText().toString().trim();
				 String user_Pwd =userPwd.getText().toString().trim();
				if(hasFocus){
					if(user_Name.isEmpty()||user_Pwd.isEmpty()){
						userlogin.setEnabled(false);
					}else {
						userlogin.setEnabled(true);
					}
				}else{

				}

			}
		});

		userPwd.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String user_Name =userName.getText().toString().trim();
				String user_Pwd =userPwd.getText().toString().trim();
				if(hasFocus){
					if(user_Name.isEmpty()||user_Pwd.isEmpty()){
						userlogin.setEnabled(false);
					}else {
						userlogin.setEnabled(true);
					}
				}else{

				}

			}
		});



	}*/

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

	/**
	 * 权限服务
	 * @return
	 */
	public boolean checkLogin(){


		//获取数据
		EditText user_name = (EditText)findViewById(R.id.user_name);
		EditText user_password = (EditText)findViewById(R.id.user_password);
		//获取储存位置
		SharedPreferences.Editor editor =  getSharedPreferences("login",MODE_PRIVATE).edit();
		//转换数据
		String userName = user_name.getText().toString().trim();
		String userPassword = user_password.getText().toString().trim();
		//验证用户
		if(userName.equals("15703216801") && userPassword.equals("123456")){
//			方式一
//			getSharedPreferences("login", MODE_PRIVATE).edit().putString("user",userName);
//			getSharedPreferences("login",MODE_PRIVATE).edit().putString("per","1" ).commit();
// 			方式二
			editor.putString("user", userName);
			editor.putString("per", "3");
			editor.commit();

			return true;
		}else if (userName.equals("13933718941") && userPassword.equals("123456")){
			editor.putString("user", userName);
			editor.putString("per", "2");
			editor.commit();
			return true;
		}else if (userName.equals("15175466687") && userPassword.equals("123456")){
			editor.putString("user", userName);
			editor.putString("per", "1");
			editor.commit();
			return true;
		}else if(getValue(userName).equals(userPassword)){
			editor.putString("user", userName);
			editor.putString("per", "1");
			editor.commit();
			return true;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
