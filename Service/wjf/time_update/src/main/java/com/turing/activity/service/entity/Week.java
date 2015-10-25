package com.turing.activity.service.entity;
/**
 * 用于存放开始和结束的周的实体类
 * @author 王俊飞
 * @date 2015-10-15
 */
public class Week {
	private String sWeek;	//起始“周”
	private String eWeek;	//结束“周”
	
	private Rule rule;

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public String getsWeek() {
		return sWeek;
	}

	public void setsWeek(String sWeek) {
		this.sWeek = sWeek;
	}

	public String geteWeek() {
		return eWeek;
	}

	public void seteWeek(String eWeek) {
		this.eWeek = eWeek;
	}

}
