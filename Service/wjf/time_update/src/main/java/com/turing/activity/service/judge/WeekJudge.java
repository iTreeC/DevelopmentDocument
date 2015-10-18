/**
 * Copyright (2015, ) Turing Ltd, Shi Jiazhuang, Hebei province
 */
package com.turing.activity.service.judge;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.turing.activity.service.entity.Week;
import com.turing.activity.service.entity.YearMonDay;

/**
 * 用于抽象星期规则，对星期的规则判断。
 * 比如：
 * 对于星期规则，星期一到星期五为一条规则；可以选择储存1和5来抽象这条规则。
 * @author 谭亚东
 * @date   2015-05-13
 *
 * 修改：王俊飞
 * @date 2015-10-15
 */
public class WeekJudge {
	private static Logger logger = Logger.getLogger(HourMinJudge.class);
	
	private String ShopID;//定义商家ID
	private String Stime;//定义商家开始星期
	private String Etime;//定义商家结束星期
	
	Map<String, Object> weekMap = new HashMap<String, Object>();
	final static String REXP = "^([1-7])$";
	Pattern p=Pattern.compile(REXP);//用正则表达式限制星期只能是1到7这7个数字。 
	
	/**
	 * 判断是否格式上符合“时分”规则
	 * @param time 时间：时分
	 * @return 若符合，返回true；否则，返回false
	 */
	public boolean isRule(String time){
		Matcher mat = p.matcher(time);
		return mat.find();
	}
	
	/**
	 * 用正则表达式对传入的星期进行格式的验证（只能是1到7这几个数字）
	 * 输入不是1-7范围内的数字直接弹出提示。
	 * @param ShopID:商家ID，类型为String
	 * @param Stime:商家传进来的星期，类型为String类型
	 * @param Etime:商家传进来的星期，类型为String类型
	 * @return Map：将商家ID和星期存入Map
	 */
	public Map<String, Object> judge(String shopId, String startTime,String endtime){
		try{
			
			Matcher m1=p.matcher(startTime);
			Matcher m2=p.matcher(endtime);
			if(m1.find()&&m2.find()){//当开始和结束时间验证都返回true
				if(Integer.parseInt(startTime)<=Integer.parseInt(endtime)){
					Week week = new Week();
					week.setsWeek(startTime);
					week.seteWeek(endtime);
	   			 	
					weekMap.put("shopId", shopId);
					weekMap.put("weekRule", week);
	   			 	
				}
				else{
					logger.debug("开始星期不能大于结束星期");
				}
			}else{//不在，返回0到6以外的数字
				logger.debug("星期格式不正确");
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return weekMap;
	}
}
