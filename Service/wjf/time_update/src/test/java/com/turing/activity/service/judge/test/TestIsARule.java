package com.turing.activity.service.judge.test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.judge.IsARule;
import com.turing.activity.service.judge.YearMonDayJudge;

public class TestIsARule {
	IsARule isARule = new IsARule();
	@Test
	public void testTimeSplit(){
		//String time = "1990-01-27 09:20";
		String time = "1990-01-27 09:20 1";
		Map<String, String> str = isARule.timeSplit(time);
		System.out.println(str.get("yearMD"));
		System.out.println(str.get("hourM"));
		System.out.println(str.get("week"));
	}
	@Test
	public void testGetARule() {
		String startTime = "1990-01-27 09:20 1";
		String endTime = "2013-01-23 18:20 1";
		
		Map<String, Object> ruleMap = isARule.getARule("02", startTime, endTime);
		
		System.out.println(ruleMap.get("shopId"));
		Rule rule = (Rule) ruleMap.get("rule");
		
		rule.getYearMonDay().getsYearMonDay();
		
		System.out.println(rule.getYearMonDay().getsYearMonDay()+" "+rule.getYearMonDay().geteYearMonDay());
		
	}

}
