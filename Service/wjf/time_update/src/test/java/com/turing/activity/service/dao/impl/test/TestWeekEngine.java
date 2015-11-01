package com.turing.activity.service.dao.impl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.turing.activity.service.dao.impl.BaseEngine;
import com.turing.activity.service.dao.impl.WeekEngine;
import com.turing.activity.service.dao.impl.YearMonDayEngine;
import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.entity.Week;
import com.turing.activity.service.entity.YearMonDay;

public class TestWeekEngine {
	BaseEngine weekEngine = WeekEngine.getSingleton();

	@Test
	public void testUpdate(){
		Rule rule = new Rule();

		rule.setRuleId(3);
		
		Week week = new Week();
		week.setsWeek("3");
		week.seteWeek("5");
		
		rule.setWeek(week);
		
		weekEngine.updata(rule);
	}
	
	@Test
	public void testFindByShopId() {
		// 通过shopid查询所有“周”规则
		List rules = weekEngine.findByShopId(1);
		for (int i = 0; i < rules.size(); i++) {
			System.out.println(((Rule) rules.get(i)).getWeek().geteWeek());
		}
	}

	@Test
	public void testFind() {
		// 查询所有“周”规则
		List rules = weekEngine.find();
		for (int i = 0; i < rules.size(); i++) {
			System.out.println(((Rule) rules.get(i)).getWeek().getsWeek());
			System.out.println(((Rule) rules.get(i)).getWeek().geteWeek());
		}
	}

}
