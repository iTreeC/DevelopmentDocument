package com.example.zhilian2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;

public class MainActivity extends Activity {
	
	private Button btn = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TabHost tabhost = (TabHost)findViewById(R.id.tabhost);  
		tabhost.setup();  
	          
		//动态载入XML，而不需要Activity  
	    LayoutInflater i=LayoutInflater.from(this);  
	    i.inflate(R.layout.searchjob, tabhost.getTabContentView()); 
	    i.inflate(R.layout.myzhilian, tabhost.getTabContentView());
	    i.inflate(R.layout.myresume, tabhost.getTabContentView());
	    
	          
	    tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("职位搜索").setContent(R.id.LinearLayout_SerachJob));    
	    tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("我的智联").setContent(R.id.LinearLayout_MyZhiLian));
	    tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("我的简历").setContent(R.id.LinearLayout_MyResume));
	
	    btn =(Button) findViewById(R.id.button_select);
	    btn.setOnClickListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public OnClickListener listener = new OnClickListener() {
		
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, LoginActivity.class);
			startActivity(intent);
		}
	};

}
