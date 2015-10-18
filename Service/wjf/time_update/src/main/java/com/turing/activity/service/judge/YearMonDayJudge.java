package com.turing.activity.service.judge;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.turing.activity.service.entity.YearMonDay;
/**
 * @author 苑雪元
 * @time 2015-05-12
 * @param StartTime 用户传入的开始时间，EndTime 用户传入的结束时间，ShopID 自动获取的商家ID
 * @return  数组类型，其中包括匹配好的开始时间，结束时间 和传入的商家ID
 * 主要功能：匹配用户输入的开始时间，结束时间，并进行一定的限制，包括：正常年月日的规则限制、年份最小值是1930，最大值2050（但不包括）
 *
 * 修改：王俊飞
 * @date 2015-10-15
 */
public class YearMonDayJudge {
	private static Logger logger = Logger.getLogger(HourMinJudge.class);
	
	final static String REXP = "^((((20(([024][048])|([13][26])))|(19(([468][048])|([3579][26]))))-((((0[13578])|(1[02]))-((0[1-9])|([1-2][0-9])|(3[01])))|(((0[469])|(11))-((0[1-9])|([1-2][0-9])|(30)))|(02-((0[1-9])|([1-2][0-9])))))|(((20(([024][1235679])|([13][01345789])))|(19(([468][1235679])|([3579][01345789]))))-((((0[13578])|(1[02]))-((0[1-9])|([1-2][0-9])|(3[01])))|(((0[469])|(11))-((0[1-9])|([1-2][0-9])|(30)))|(02-((0[1-9])|(1[0-9])|(2[0-8]))))))";
	Pattern pat = Pattern.compile(REXP);
	Map<String, Object> yearMonDayMap = new HashMap<String, Object>();
	
	private String Stime =null;//开始时间

	private String Etime =null;//结束时间
	
	public  YearMonDayJudge(){}
	
	/**
	 * 判断是否格式上符合“时分”规则
	 * @param time 时间：时分
	 * @return 若符合，返回true；否则，返回false
	 */
	public boolean isRule(String time){
		Matcher mat = pat.matcher(time);
		return mat.find();
	}
	
	/**
	 * 判断是否是一条“年月日”规则
	 * @param shopId 此规则所对应的商家id
	 * @param startTime 开始的时间
	 * @param endTime 结束的时间
	 * @return 若符合“年月日”规则，返回包含年月日规则和商家id的map；否则，返回值为空
	 */
	public Map<String, Object> judge(String shopId, String startTime,String endTime){
		/*
		 * 利用正则表达式判断日期格式和范围
		 * 将正则表达式中的年份又设定一下限制：1930年以后
		 */
		Matcher Emat = pat.matcher(endTime);
		Matcher Smat = pat.matcher(startTime);
		/*
		 * 判断输入的数据是否符合日期规则
		 */
		if(Smat.find()&&Emat.find()){
			/*
			 * 判断输入的数据是否符合要求：结束时间大于等于开始时间
			 */
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date St = df.parse(startTime);
	            Date Et = df.parse(endTime);
	            if (St.getTime() <= Et.getTime()) {
	            	Stime = Smat.group();
	   			 	Etime = Emat.group();
	   			 	//将正确的数据添加到map
	   			 	YearMonDay yearMonDay = new YearMonDay();
	   			 	yearMonDay.setsYearMonDay(Stime);
	   			 	yearMonDay.seteYearMonDay(Etime);
	   			 	
	   			 	yearMonDayMap.put("shopId", shopId);
	   			 	yearMonDayMap.put("yearMonDayRule", yearMonDay);
	   			 	
	            } else{
	            	logger.debug("对不起，您输入的结束时间不能比开始时间小");
	            }
	        } catch (Exception exception) {
	             exception.printStackTrace();
	        }
		}else{
			logger.debug("对不起，您输入的不正确");
		}
		return yearMonDayMap;
		
	}
}
