package com.turing.activity.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.turing.activity.service.api.Status;
/**
 * 获取当前时间状态
 * 比如：获取当前时间为1994-02-16（年月日） 8:00（时分） 2（周）
 * @author 王俊飞
 * @date 2015-10-15
 * 
 */
public class StatusImpl implements Status {

	@Override
	public Object get() {
		Map statusMap = new HashMap();
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

		//获取当前年月日、时分、周
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; //系统获取到的0-11月，分别对应实际的1-12月。所以+1
		int day = c.get(Calendar.DATE); 
		int week = c.get(Calendar.DAY_OF_WEEK)-1;//系统获取到的 周1-周7，分别对应实际的周日、周1-周6
		if(week==1){
			week=7;
		}
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE);
		
		//将当前时间存到Map中
		statusMap.put("YEAR", year);
		statusMap.put("MONTH", month);
		statusMap.put("DAY", day);
		statusMap.put("WEEK", week);
		statusMap.put("HOUR", hour);
		statusMap.put("MINUTE", minute);
//		statusMap.put("YEAR", 2015);
//		statusMap.put("MONTH", 03);
//		statusMap.put("DAY", 21);
//		statusMap.put("WEEK", 4);
//		statusMap.put("HOUR", 15);
//		statusMap.put("MINUTE", 00);
		return statusMap;
	}

}
