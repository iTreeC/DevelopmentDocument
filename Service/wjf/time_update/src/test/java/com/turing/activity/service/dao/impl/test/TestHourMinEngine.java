package com.turing.activity.service.dao.impl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.turing.activity.service.dao.impl.BaseEngine;
import com.turing.activity.service.dao.impl.HourMinEngine;
import com.turing.activity.service.dao.impl.YearMonDayEngine;
import com.turing.activity.service.entity.HourMin;
import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.entity.YearMonDay;

public class TestHourMinEngine {
	BaseEngine hourMinEngine =HourMinEngine.getSingleton();

	@Test
	public void testUpdate(){
		Rule rule = new Rule();
		rule.setRuleId(3);
		
		HourMin h = new HourMin();
		h.setsHourMin("9:00");
		h.seteHourMin("20:00");
		
		rule.setHourMin(h);
		
		hourMinEngine.updata(rule);
	}
	
	@Test
	public void testFindByShopId() {
		// 通过shopid查询相应“时分”规则
		List rules = hourMinEngine.findByShopId(1);
		for(int i=0; i<rules.size(); i++){
			System.out.println(((Rule) rules.get(i)).getHourMin().geteHourMin());
		}
	}
	
	@Test
	public void testFind() {
		//查询所有“时分”规则
		List rules = hourMinEngine.find();
		for(int i=0; i<rules.size(); i++){
			System.out.println(((Rule)rules.get(i)).getHourMin().geteHourMin());
		}
	}

}
