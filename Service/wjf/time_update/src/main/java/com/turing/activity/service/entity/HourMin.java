package com.turing.activity.service.entity;
/**
 * 用于存放开始和结束的小时分钟的实体类
 * @author 王俊飞
 * @date 2015-10-15
 */
public class HourMin {
	private String sHourMin;	//起始“时分”
	private String eHourMin;	//结束“时分”
	
	private Rule rule;

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public String getsHourMin() {
		return sHourMin;
	}

	public void setsHourMin(String sHourMin) {
		this.sHourMin = sHourMin;
	}

	public String geteHourMin() {
		return eHourMin;
	}

	public void seteHourMin(String eHourMin) {
		this.eHourMin = eHourMin;
	}

}
