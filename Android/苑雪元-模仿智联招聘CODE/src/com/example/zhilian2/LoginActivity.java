package com.example.zhilian2;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.TabHost;

public class LoginActivity extends Activity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginorsign);
		
		TabHost tabhost = (TabHost)findViewById(R.id.tabhost1);  
		tabhost.setup();  
	    
		//��̬����XML��������ҪActivity  
	    LayoutInflater i=LayoutInflater.from(this);  
	    i.inflate(R.layout.login, tabhost.getTabContentView()); 
	    i.inflate(R.layout.sign, tabhost.getTabContentView());
	    
	          
	    tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("��¼").setContent(R.id.LinearLayout_login));    
	    tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("ע��").setContent(R.id.LinearLayout_sign));
	    
	   
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
	

}
