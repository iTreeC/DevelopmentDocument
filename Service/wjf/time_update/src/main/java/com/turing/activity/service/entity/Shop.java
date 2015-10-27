package com.turing.activity.service.entity;

import java.util.HashSet;
import java.util.Set;
/**
 * 用于存放商家的实体类
 * @author 王俊飞
 * @date 2015-10-15
 */
public class Shop {
	private int shopId;
	private String shopName;
	
	private Set<Rule> rules = new HashSet<Rule>();

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Set<Rule> getRules() {
		return rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}
	
	
}
