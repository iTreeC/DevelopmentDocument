package com.turing.activity.service.judge.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.turing.activity.service.entity.Week;
import com.turing.activity.service.entity.YearMonDay;
import com.turing.activity.service.judge.WeekJudge;

public class TestWeekJudge {
	WeekJudge w=new WeekJudge();
	
	@Test
	public void testIsRule(){
		//String time = "1";
		String time = "10";
		System.out.println(w.isRule(time));
	}
	@Test
	public void testJude() {
		Map<String, Object> map = w.judge("02", "1","1");
		System.out.println(map.get("shopId"));
		Week week = (Week) map.get("weekRule");
		System.out.println(week.getsWeek()+" "+ week.geteWeek());
	}

}
