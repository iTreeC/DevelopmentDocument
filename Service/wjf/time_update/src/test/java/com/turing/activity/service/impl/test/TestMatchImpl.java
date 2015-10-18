package com.turing.activity.service.impl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.turing.activity.service.api.Match;
import com.turing.activity.service.api.Status;
import com.turing.activity.service.dao.api.Engine;
import com.turing.activity.service.dao.impl.BaseEngine;
import com.turing.activity.service.dao.impl.HourMinEngine;
import com.turing.activity.service.dao.impl.WeekEngine;
import com.turing.activity.service.dao.impl.YearMonDayEngine;
import com.turing.activity.service.impl.MatchImpl;
import com.turing.activity.service.impl.StatusImpl;

public class TestMatchImpl {
	Status status = new StatusImpl();
	// 调用相应引擎
	Engine baseEngine = BaseEngine.getSingleton();
	Engine yearMonDayEngine = YearMonDayEngine.getSingleton();// “年月日”引擎
	Engine hourMinEngine = HourMinEngine.getSingleton();// “时分”引擎
	Engine weekEngine = WeekEngine.getSingleton();// “周”引擎
	
	Match match = new MatchImpl(baseEngine, yearMonDayEngine, hourMinEngine, weekEngine);
	
	@Test
	public void testMatchYearMonDay() {
		List<Integer> shopList = match.matchYearMonDay(status);
		for(int i=0; i<shopList.size(); i++){
			System.out.println(shopList.get(i));
		}
	}
	
	@Test
	public void testMatchHourMin() {
		List<Integer> shopList = match.matchHourMin(status);
		for(int i=0; i<shopList.size(); i++){
			System.out.println(shopList.get(i));
		}
	}
	
	@Test
	public void testMatch() {
		List<Integer> shopList = match.match(status);
		for(int i=0; i<shopList.size(); i++){
			System.out.println(shopList.get(i));
		}
	}

	@Test
	public void testMatchWeekBetween(){
		List<Integer> shopList = match.matchWeekBetween(status);
		for(int i=0; i<shopList.size(); i++){
			System.out.println(shopList.get(i));
		}
	}
	
	@Test
	public void testMatchWeekEquip(){
		List<Integer> shopList = match.matchWeekEquip(status);
		for(int i=0; i<shopList.size(); i++){
			System.out.println(shopList.get(i));
		}
	}
}
