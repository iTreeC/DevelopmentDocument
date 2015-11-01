package com.turing.activity.service.dao.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import com.turing.activity.service.dao.impl.BaseEngine;
import com.turing.activity.service.dao.impl.YearMonDayEngine;
import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.entity.YearMonDay;

public class TestYearMonDayEngine {
	BaseEngine yearMonDayEngine = YearMonDayEngine.getSingleton();

	@Test
	public void testUpdate(){
		Rule rule = new Rule();
		rule.setRuleId(3);
		
		YearMonDay y = new YearMonDay();
		y.seteYearMonDay("2015-02-24");
		y.setsYearMonDay("2010-01-02");
		
		rule.setYearMonDay(y);
		
		yearMonDayEngine.updata(rule);
	}
	
	@Test
	public void testFindByShopId() {
		// 通过shopid查询相应“年月日”规则
		List rules = yearMonDayEngine.findByShopId(1);
		for (int i = 0; i < rules.size(); i++) {
			System.out.println(((Rule) rules.get(i)).getYearMonDay()
					.geteYearMonDay());
		}

	}

	@Test
	public void testFind() {
		// 查询所有“年月日”规则
		List rules = yearMonDayEngine.find();
		for (int i = 0; i < rules.size(); i++) {
			System.out.println(((Rule) rules.get(i)).getYearMonDay()
					.geteYearMonDay());
		}
	}

}
