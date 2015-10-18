package com.turing.activity.service.judge.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.turing.activity.service.entity.YearMonDay;
import com.turing.activity.service.judge.YearMonDayJudge;

public class TestYearMonDay {
	YearMonDayJudge y = new YearMonDayJudge();
	
	@Test
	public void testIsRule(){
		//String time = "1931-02-28";
		String time = "1931 02-28";
		System.out.println(y.isRule(time));
	}
	
	@Test
	public void testJudge() {
		
		String Stime = "1931-02-28";
		String Etime = "2004-02-29";
		String ShopID = "02";
		
		Map<String, Object> map = y.judge(ShopID, Stime,Etime);
		System.out.println(map.get("shopId"));
		YearMonDay yearMonDay = (YearMonDay) map.get("yearMonDayRule");
		System.out.println(yearMonDay.getsYearMonDay()+" "+ yearMonDay.geteYearMonDay());
	}

}
