package com.turing.activity.service.entity;

import java.util.HashSet;
import java.util.Set;
/**
 * 用于存放规则的实体类
 * @author 王俊飞
 * @date 2015-10-15
 */
public class Rule {
	private int ruleId;
	
	private YearMonDay yearMonDay;
	private Week week;
	private HourMin hourMin;
	
	public YearMonDay getYearMonDay() {
		return yearMonDay;
	}

	public void setYearMonDay(YearMonDay yearMonDay) {
		this.yearMonDay = yearMonDay;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public HourMin getHourMin() {
		return hourMin;
	}

	public void setHourMin(HourMin hourMin) {
		this.hourMin = hourMin;
	}

	private Set<Shop> shops = new HashSet<Shop>();

	public Set<Shop> getShops() {
		return shops;
	}

	public void setShops(Set<Shop> shops) {
		this.shops = shops;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	
	
}
