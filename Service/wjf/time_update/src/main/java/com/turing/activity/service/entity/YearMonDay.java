package com.turing.activity.service.entity;
/**
 * 用于存放开始和结束的年月日的实体类
 * @author 王俊飞
 * @date 2015-10-15
 */
public class YearMonDay {
	private String sYearMonDay; // 起始“年月日”
	private String eYearMonDay; // 结束“年月日”
	
	private Rule rule;

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public String getsYearMonDay() {
		return sYearMonDay;
	}

	public void setsYearMonDay(String sYearMonDay) {
		this.sYearMonDay = sYearMonDay;
	}

	public String geteYearMonDay() {
		return eYearMonDay;
	}

	public void seteYearMonDay(String eYearMonDay) {
		this.eYearMonDay = eYearMonDay;
	}

}
