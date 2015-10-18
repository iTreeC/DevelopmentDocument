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

import com.turing.activity.service.entity.HourMin;
import com.turing.activity.service.entity.YearMonDay;

/**
 * 判断是否符合“时分”规则
 * @author 刘洋
 * @time 2015-05-13
 * @param StartTime 用户传入的开始时间，EndTime 用户传入的结束时间，ShopID 获取的商家ID
 * 
 * 修改：王俊飞
 * @date 2015-10-15
 */
public class HourMinJudge {
	private static Logger logger = Logger.getLogger(HourMinJudge.class);
	
	final static String REXP = "^([0-1]?[0-9]|2[0-3]):([0-5][0-9])$";
	Pattern pat = Pattern.compile(REXP);
	
	Map<String, Object> hourMinMap = new HashMap<String, Object>();
	String Stime = null;// 开始时间
	String Etime = null;// 结束时间
	
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
	 * 判断是否是一条“时分”规则
	 * @param shopId 此规则所对应的商家id
	 * @param startTime 开始的时间
	 * @param endTime 结束的时间
	 * @return 若符合“时分”规则，返回包含时分规则和商家id的map；否则，返回值为空
	 */
	public  Map<String, Object> judge(String shopId, String startTime, String endTime) {

		//Pattern pat = Pattern.compile(rexp);
		Matcher Emat = pat.matcher(endTime);
		Matcher Smat = pat.matcher(startTime);

		/*
		 * 判断输入的数据是否符合日期规则
		 */
		if (Smat.find() && Emat.find()) {
			/*
			 * 判断输入的数据正否符合要求：结束时间大于等于开始时间
			 */
			DateFormat hm = new SimpleDateFormat("HH:mm");
			try {
				Date St = hm.parse(startTime);
				Date Et = hm.parse(endTime);
				if (St.getTime() <= Et.getTime()) {
					Stime = Smat.group();
					Etime = Emat.group();
					// 将正确的数据添加到Map
					HourMin hourMin = new HourMin();
					hourMin.setsHourMin(Stime);
					hourMin.seteHourMin(Etime);
	   			 	
	   			 	hourMinMap.put("shopId", shopId);
	   			 	hourMinMap.put("hourMinRule", hourMin);
				}else{
					logger.debug("对不起，您输入的结束时间不能比开始时间小");
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else {
			logger.debug("对不起，你输入的不正确");
		}
		return hourMinMap;
	}
}
