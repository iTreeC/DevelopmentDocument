package com.turing.activity.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.turing.activity.service.api.Match;
import com.turing.activity.service.api.Status;
import com.turing.activity.service.dao.api.Engine;
import com.turing.activity.service.dao.impl.BaseEngine;
import com.turing.activity.service.dao.impl.HourMinEngine;
import com.turing.activity.service.dao.impl.WeekEngine;
import com.turing.activity.service.dao.impl.YearMonDayEngine;
import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.entity.Shop;
import com.turing.activity.service.judge.HourMinJudge;
/**
 * 用于当前时间和规则的匹配
 * @author 王俊飞
 * @date 2015-10-15
 *
 */
public class MatchImpl implements Match {
	private static Logger logger = Logger.getLogger(HourMinJudge.class);

	// 调用相应引擎
	Engine baseEngine;
	Engine yearMonDayEngine;// “年月日”引擎
	Engine hourMinEngine;// “时分”引擎
	Engine weekEngine;// “周”引擎
	
	public MatchImpl(){}
	
	public MatchImpl(Engine baseEngine, Engine yearMonDayEngine, Engine hourMinEngine, Engine weekEngine){
		this.baseEngine = baseEngine;
		this.yearMonDayEngine = yearMonDayEngine;
		this.hourMinEngine = hourMinEngine;
		this.weekEngine = weekEngine;
	}
	
	@Override
	public List<Integer> match(Status status) {
		// TODO Auto-generated method stub

		// 1.若年月日匹配成功，记录shop，并进行第2步；否则，匹配失败。
		List<Integer> yearMonDayShopList = this.matchYearMonDay(status);
		// 2.若时分匹配成功，记录shop，并进行第3步；否则，匹配失败。
		List<Integer> hourMinShopList = this.matchHourMin(status);
		// 3.将年月日和时分的shop集合，求交集，即为匹配成功的shop
		
		List<Integer> shopList = getIntersection(yearMonDayShopList, hourMinShopList);
		
		return yearMonDayShopList;
	}

	/*
	 * “年月日”的匹配
	 */
	@Override
	public List<Integer> matchYearMonDay(Status status) {
		Map statusMap = (Map) status.get();
		final int STATUS_YEAR = (int) statusMap.get("YEAR");
		final int STATUS_MONTH = (int) statusMap.get("MONTH");
		final int STATUS_DAY = (int) statusMap.get("DAY");
		
		List<Integer> shopYearMonDay = new ArrayList();
		List<Rule> ruleYearMonDay = new ArrayList();
		// 用年月日引擎拿到年月日规则
		List<Rule> rules = yearMonDayEngine.find();
		// 匹配
		for (int i = 0; i < rules.size(); i++) {
			Rule rule = (Rule) rules.get(i);
			String startTime = rule.getYearMonDay().getsYearMonDay();
			String endTime = rule.getYearMonDay().geteYearMonDay();

			// 将年月日拆分
			String[] startTimes = startTime.split("-");// 索引0为年，1为月，2为日
			String[] endTimes = endTime.split("-");

			// 开始匹配
			// 规则年份不等，当前时间处于两者之间（不包括相等）
			if (Integer.parseInt(startTimes[0]) < STATUS_YEAR
					&& Integer.parseInt(endTimes[0]) > STATUS_YEAR) {
				// 匹配成功
				ruleYearMonDay.add(rule);
				// 规则年份不等，当前时间等于开始时间
			} else if (Integer.parseInt(startTimes[0]) == STATUS_YEAR
					&& Integer.parseInt(endTimes[0]) > STATUS_YEAR) {
				// 如果当前月份大于开始月份，匹配成功
				if (STATUS_MONTH > Integer.parseInt(startTimes[1])) {
					// 匹配成功
					ruleYearMonDay.add(rule);
					// 如果当前月份等于开始月份，比较"日"
				} else if (STATUS_MONTH == Integer.parseInt(startTimes[1])) {
					// 如果当前日大于等于开始日，匹配成功
					if (STATUS_DAY >= Integer.parseInt(startTimes[2])) {
						ruleYearMonDay.add(rule);
					} else {
						logger.info("“年月日”匹配失败：“日”匹配失败！");
					}
				} else {
					logger.info("“年月日”匹配失败：“月”匹配失败！");
				}
				// 规则年份不等，当前时间等于结束时间
			} else if (Integer.parseInt(startTimes[0]) < STATUS_YEAR
					&& Integer.parseInt(endTimes[0]) == STATUS_YEAR) {
				// 如果当前月份小于结束月份，匹配成功
				if (STATUS_MONTH < Integer.parseInt(endTimes[1])) {
					// 匹配成功
					ruleYearMonDay.add(rule);
					// 如果当前月份等于结束月份，比较"日"
				} else if (STATUS_MONTH == Integer.parseInt(endTimes[1])) {
					// 如果当前月份小于等于开始日，匹配成功
					if (STATUS_DAY <= Integer.parseInt(endTimes[2])) {
						ruleYearMonDay.add(rule);
					} else {
						logger.info("“年月日”匹配失败：“日”匹配失败！");
					}
				} else {
					logger.info("“年月日”匹配失败：“月”匹配失败！");
				}
				// 规则年份相等，当前时间等于规则时间
			} else if (Integer.parseInt(startTimes[0]) == STATUS_YEAR
					&& Integer.parseInt(endTimes[0]) == STATUS_YEAR) {
				// 规则月份不等，当前月份在其之间（不包括开始结束时间）
				if (STATUS_MONTH > Integer.parseInt(startTimes[1])
						&& STATUS_MONTH < Integer.parseInt(endTimes[1])) {
					ruleYearMonDay.add(rule);
					// 规则月份不等，当前月等于开始月份
				} else if (STATUS_MONTH == Integer.parseInt(startTimes[1])
						&& STATUS_MONTH < Integer.parseInt(endTimes[1])) {
					// 规则日大于等于开始日
					if (STATUS_DAY >= Integer.parseInt(startTimes[2])) {
						ruleYearMonDay.add(rule);
					} else {
						logger.info("“年月日”匹配失败：“日”匹配失败！");
					}
					// 规则月份不等，当前月等于结束月份
				} else if (STATUS_MONTH > Integer.parseInt(startTimes[1])
						&& STATUS_MONTH == Integer.parseInt(endTimes[1])) {
					// 规则日小于等于开始日
					if (STATUS_DAY <= Integer.parseInt(endTimes[2])) {
						ruleYearMonDay.add(rule);
					} else {
						logger.info("“年月日”匹配失败：“日”匹配失败！");
					}
					// 规则月份相等，当前月等于规则月份
				} else if (STATUS_MONTH == Integer.parseInt(startTimes[1])
						&& STATUS_MONTH == Integer.parseInt(endTimes[1])) {
					// 当前日大于等于开始日，小于等于结束日
					if (STATUS_DAY >= Integer.parseInt(startTimes[2])
							&& STATUS_DAY <= Integer.parseInt(endTimes[2])) {
						ruleYearMonDay.add(rule);
					} else {
						logger.info("“年月日”匹配失败：“日”匹配失败！");
					}
				} else {
					logger.info("“年月日”匹配失败：“月”匹配失败！");
				}
			} else {
				logger.info("“年月日”匹配失败：“年”匹配失败！");
			}
		}
		// 通过规则查相应的shop
		shopYearMonDay = this.rule2ShopId(ruleYearMonDay);
		return shopYearMonDay;
	}

	/*
	 * “时分”的匹配
	 */
	@Override
	public List<Integer> matchHourMin(Status status) {
		Map statusMap = (Map) status.get();
		final int STATUS_HOUR = (int) statusMap.get("HOUR");
		final int STATUS_MINUTE = (int) statusMap.get("MINUTE");
		
		List<Integer> shopHourMin = new ArrayList();
		List<Rule> ruleHourMin = new ArrayList();
		// 用时分引擎拿到时分规则
		List<Rule> rules = hourMinEngine.find();
		// 匹配
		for (int i = 0; i < rules.size(); i++) {
			Rule rule = (Rule) rules.get(i);
			String startTime = rule.getHourMin().getsHourMin();
			String endTime = rule.getHourMin().geteHourMin();

			// 将年月日拆分
			String[] startTimes = startTime.split(":");// 索引0为年，1为月，2为日
			String[] endTimes = endTime.split(":");

			// 两个“时”不等，当前“时”位于之间（不包括边缘值）
			if (STATUS_HOUR > Integer.parseInt(startTimes[0])
					&& STATUS_HOUR < Integer.parseInt(endTimes[0])) {
				ruleHourMin.add(rule);
				// 两个“时”不等，当前“时”等于开始的“时”
			} else if (STATUS_HOUR == Integer.parseInt(startTimes[0])
					&& STATUS_HOUR < Integer.parseInt(endTimes[0])) {
				// 如果分大于等于开始的分，匹配成功
				if (STATUS_MINUTE >= Integer.parseInt(startTimes[1])) {
					ruleHourMin.add(rule);
				} else {
					logger.info("“时分”匹配失败：“分”匹配失败！");
				}
				// 两个“时”不等，当前“时”等于结束的“时”
			} else if (STATUS_HOUR > Integer.parseInt(startTimes[0])
					&& STATUS_HOUR == Integer.parseInt(endTimes[0])) {
				// 如果分小于等于结束的分，匹配成功
				if (STATUS_MINUTE <= Integer.parseInt(endTimes[1])) {
					ruleHourMin.add(rule);
				} else {
					logger.info("“时分”匹配失败：“分”匹配失败！");
				}
				// 两个“时”相等，当前“时”等于该“时”
			} else if (STATUS_HOUR == Integer.parseInt(startTimes[0])
					&& STATUS_HOUR == Integer.parseInt(endTimes[0])) {
				// 如果分在规则分之间（包括边缘值），匹配成功
				if (STATUS_MINUTE >= Integer.parseInt(startTimes[1])
						&& STATUS_MINUTE <= Integer.parseInt(endTimes[1])) {
					ruleHourMin.add(rule);
				} else {
					logger.info("“时分”匹配失败：“分”匹配失败！");
				}
				// 小时匹配失败
			} else {
				logger.info("“时分”匹配失败：“时”匹配失败！");
			}
		}
		// 通过规则转换成shop
		shopHourMin = this.rule2ShopId(ruleHourMin);
		return shopHourMin;
	}

	/*
	 * “周”的匹配（如果当前的周位于起止周之间）
	 */
	@Override
	public List<Integer> matchWeekBetween(Status status) {
		Map statusMap = (Map) status.get();
		final int STATUS_WEEK = (int) statusMap.get("WEEK");
		
		List<Integer> shopWeek = new ArrayList();
		List<Rule> ruleWeek = new ArrayList();
		// 用周引擎拿到周规则
		List<Rule> rules = weekEngine.find();

		for (int i = 0; i < rules.size(); i++) {
			Rule rule = (Rule) rules.get(i);
			String startTime = rule.getWeek().getsWeek();
			String endTime = rule.getWeek().geteWeek();

			// 如果当前的周位于起止周之间，匹配成功
			if (STATUS_WEEK >= Integer.parseInt(startTime)
					&& STATUS_WEEK <= Integer.parseInt(endTime)) {
				ruleWeek.add(rule);
			} else {
				logger.info("“周”匹配失败！");
			}
		}
		// 通过规则转换成shop
		shopWeek = rule2ShopId(ruleWeek);
		return shopWeek;
	}

	/*
	 * “周”的匹配（当前的周等于起止周 ，起止周相等）
	 */
	@Override
	public List<Integer> matchWeekEquip(Status status) {
		Map statusMap = (Map) status.get();
		final int STATUS_WEEK = (int) statusMap.get("WEEK");
		
		List<Integer> shopWeek = new ArrayList();
		List<Rule> ruleWeek = new ArrayList();
		// 用周引擎拿到周规则
		List<Rule> rules = weekEngine.find();

		for (int i = 0; i < rules.size(); i++) {
			Rule rule = (Rule) rules.get(i);
			String startTime = rule.getWeek().getsWeek();
			String endTime = rule.getWeek().geteWeek();

			// 如果当前的周位于起止周之间，匹配成功
			if (STATUS_WEEK == Integer.parseInt(startTime)
					&& STATUS_WEEK == Integer.parseInt(endTime)) {
				ruleWeek.add(rule);
			} else {
				logger.info("“周”匹配失败！");
			}
		}
		// 通过规则转换成shop
		shopWeek = rule2ShopId(ruleWeek);
		return shopWeek;
	}

	/*
	 * 通过rule查询所对应的shopId
	 */
	private List<Integer> rule2ShopId(List<Rule> ruleList) {
		List<Integer> shopIdList = new ArrayList<Integer>();
		for (int i = 0; i < ruleList.size(); i++) {
			List<Integer> shopIdList1 = baseEngine.findShopIdByRuleId(ruleList
					.get(i).getRuleId());
			shopIdList.addAll(shopIdList1);
		}
		// 去重
		HashSet h  =   new  HashSet(shopIdList); 
		shopIdList.clear(); 
		shopIdList.addAll(h); 
		
		return shopIdList;
	}
	
	/*
	 * 通过两种不同引擎查出的商家求交集，即为最终匹配成功的商家ID
	 */
	private List<Integer> getIntersection(List<Integer> list1,
			List<Integer> list2) {
		List<Integer> result = new ArrayList<Integer>();
		for (Integer integer : list2) {//遍历list1
			if (list1.contains(integer)) {//如果存在这个数
				result.add(integer);//放进一个list里面，这个list就是交集
			}
		}
		return result;
	}

}
