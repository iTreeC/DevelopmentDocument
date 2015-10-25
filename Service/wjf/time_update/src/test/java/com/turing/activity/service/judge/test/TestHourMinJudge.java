package com.turing.activity.service.judge.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.turing.activity.service.entity.HourMin;
import com.turing.activity.service.entity.YearMonDay;
import com.turing.activity.service.judge.HourMinJudge;

public class TestHourMinJudge {
	HourMinJudge hm = new HourMinJudge();
	
	@Test
	public void testIsRule(){
		//String time = "03 20";
		String time = "03:20";
		System.out.println(hm.isRule(time));
		
	}
	@Test
	public void testJude() {
		
		String Stime = "03:20";
		String Etime = "20:20";
		String ShopID = "02";
		
		Map<String, Object> map = hm.judge(ShopID, Stime,Etime);
		System.out.println(map.get("shopId"));
		HourMin hourMin = (HourMin) map.get("hourMinRule");
		System.out.println(hourMin.getsHourMin()+" "+ hourMin.geteHourMin());
	}

}
