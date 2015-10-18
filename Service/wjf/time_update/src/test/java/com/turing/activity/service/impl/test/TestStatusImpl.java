package com.turing.activity.service.impl.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.turing.activity.service.api.Status;
import com.turing.activity.service.impl.StatusImpl;

public class TestStatusImpl {

	Status status = new StatusImpl();
	@Test
	public void testMatch() {
		
		Map statusMap = (Map) status.get();
		int statusYear = (int) statusMap.get("YEAR");
		int statusMonth = (int) statusMap.get("MONTH");
		int statusDay = (int) statusMap.get("DAY");
		int statusHour = (int) statusMap.get("HOUR");
		int statusMinute = (int) statusMap.get("MINUTE");
		int statusWeek = (int) statusMap.get("WEEK");
		
		System.out.println(statusYear);
		System.out.println(statusMonth);
		System.out.println(statusDay);
		System.out.println(statusHour);
		System.out.println(statusMinute);
		System.out.println(statusWeek);
	}

}
