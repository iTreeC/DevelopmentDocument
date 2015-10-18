package com.turing.activity.service.judge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.turing.activity.service.entity.HourMin;
import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.entity.Week;
import com.turing.activity.service.entity.YearMonDay;

/**
 * 通过调用约束类中的约束方法，来进行格式的统一判断
 * @author 苑雪元
 * @time 2015-05-13
 * 
 * 修改：王俊飞
 * @date 2015-10-15
 */
public class IsARule {
	
	private static Logger logger = Logger.getLogger(HourMinJudge.class);
	//一条完整规则的对象
	Rule rule = new Rule();
	//用于存放shopId和一条完整的规则
	Map<String, Object> ruleMap = new HashMap<String, Object>();
		
	//分别判断“年月日”、“时分”、“周”的对象及返回值的引用
	YearMonDayJudge yearMonDay = new YearMonDayJudge();
	HourMinJudge hourMin = new HourMinJudge();
	WeekJudge week = new WeekJudge();
	
	Map<String, Object> yearMonDayRule;
	Map<String, Object> hourMinRule;
	Map<String, Object> weekRule;
	
	/**
	 * 将一整条时间分割成“年月日”、“时分”、“周”
	 * @param time 一整条时间。比如：1994-02-16 8:00 2
	 * @return 返回年月日、时分、周三部分的一个map
	 */
	public Map<String, String> timeSplit(String time){
		String[] timeStr = time.split(" ");
		
		Map<String, String> timeMap = new HashMap<String, String>();
		//“年月日、时分、 周”
		if(timeStr.length == 3){
			timeMap.put("yearMD", timeStr[0]);
			timeMap.put("hourM", timeStr[1]);
			timeMap.put("week", timeStr[2]);
		}else if(timeStr.length == 2){    //“年月日、时分 ”或者“时分、周”
			if(timeStr[0].length() == 10){
				timeMap.put("yearMD", timeStr[0]);
				timeMap.put("hourM", timeStr[1]);
			}else if(timeStr[0].length() == 5){
				timeMap.put("hourM", timeStr[0]);
				timeMap.put("week", timeStr[1]);
			}else{
				logger.debug("输入的时间格式有误！");
			}
		}else if(timeStr.length == 1){    //“年月日” 或 “时分” 或 “周”
			if(timeStr[0].length() == 10){
				timeMap.put("yearMD", timeStr[0]);
			}else if(timeStr[0].length() == 5){
				timeMap.put("hourM", timeStr[0]);
			}else if(timeStr[0].length() == 1){
				timeMap.put("week", timeStr[0]);
			}
		}else{
			logger.debug("输入的时间格式有误！");
		}
		return timeMap;
	}
	
	/**
	 * 产生一条完整的规则
	 *  @param shopId 此规则所对应的商家id
	 * @param startTime 开始的时间
	 * @param endTime 结束的时间
	 * @return 若符合规则，返回整条规则和商家id的map；否则，返回值为空
	 */
	public Map<String, Object> getARule(String shopId, String startTime, String endTime){
		Map<String, String> startStr = this.timeSplit(startTime);
		Map<String, String> endStr = this.timeSplit(endTime);
		//在map中添加商家ID
		ruleMap.put("shopId", shopId);
		
		//“年月日”规则的判断
		if(startStr.get("yearMD") != null && endStr.get("yearMD") != null){
			if(yearMonDay.isRule(startStr.get("yearMD")) && yearMonDay.isRule(endStr.get("yearMD"))){
				yearMonDayRule = yearMonDay.judge(shopId, startStr.get("yearMD"), endStr.get("yearMD"));
				rule.setYearMonDay((YearMonDay) yearMonDayRule.get("yearMonDayRule"));
			}
		}
		
		//“时分”规则的判断
		if(startStr.get("hourM") != null && endStr.get("hourM") != null){
			if(hourMin.isRule(startStr.get("hourM")) && hourMin.isRule(endStr.get("hourM"))){
				hourMinRule = hourMin.judge(shopId, startStr.get("hourM"), endStr.get("hourM"));
				rule.setHourMin((HourMin) hourMinRule.get("hourMinRule"));
			}
		}
		
		//“周”规则的判断
		if(startStr.get("week") != null && endStr.get("week") != null){
			if(week.isRule(startStr.get("week")) && week.isRule(endStr.get("week"))){
				weekRule = week.judge(shopId, startStr.get("week"), endStr.get("week"));
				rule.setWeek((Week) weekRule.get("weekRule"));
			}
		}
	
		ruleMap.put("rule", rule);
		
		return ruleMap;
	}
}
