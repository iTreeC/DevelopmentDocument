package com.turing.activity.service.dao.impl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.turing.activity.service.dao.impl.BaseEngine;
import com.turing.activity.service.entity.HourMin;
import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.entity.Shop;
import com.turing.activity.service.entity.Week;
import com.turing.activity.service.entity.YearMonDay;

public class TestBaseEngine {
	BaseEngine baseEngine = BaseEngine.getSingleton();
	
	@Test
	public void testSave(){
		YearMonDay y = new YearMonDay();
		HourMin h = new HourMin();
		Week w = new Week();
		
		y.setsYearMonDay("2013-01-01");
		y.seteYearMonDay("2015-02-03");
		h.setsHourMin("08:00");
		h.seteHourMin("20:00");
		w.setsWeek("1");
		w.seteWeek("1");
		
		Rule rule = new Rule();
		rule.setYearMonDay(y);
		rule.setHourMin(h);
		rule.setWeek(w);
		rule.setRuleId(2);
		//添加
		baseEngine.save(rule);
	}
	
	@Test
	public void testDelete(){
		//删除超过下限
		baseEngine.delete(-1);
//		//若有数据，可以删除
//		baseEngine.delete(1);
//		//删除超过上限
//		baseEngine.delete(1000000);
	}
	
	@Test
	public void testUpdate(){
		//更新 有点小问题
		Rule rule = new Rule();
		YearMonDay y = new YearMonDay();
		
		rule.setRuleId(2);
		y.seteYearMonDay("2015-02-24");
		baseEngine.updata(rule);
	}
	
	@Test
	public void testFindById() {
		//查询小于1的Id
//		Rule rule1 = baseEngine.findById(-1);
		//查询在范围中的id
		Rule rule1 = baseEngine.findById(1);
		//查询大于最大id的id
//		Rule rule1 = baseEngine.findById(10000000);
		System.out.println(rule1);
	}
	
	@Test
	public void testFin() {
		//按hql语句查询
		String hql = "from Rule as r where r.ruleId = 2";
		List rules = baseEngine.find(hql);
		System.out.println(((Rule)rules.get(0)).getYearMonDay().getsYearMonDay());
	}
	
	@Test
	public void testCount() {
		//查询规则个数
		String hql = "select count(*) from Rule r";
		Long count = baseEngine.count(hql);
		System.out.println(count);
	}
	
	@Test
	public void testFindByShopId() {
		//通过shopID查询Rule
		List rules = baseEngine.findByShopId(1);
		for(int i=0; i<rules.size(); i++){
			System.out.println(((Rule)rules.get(i)).getRuleId());
		}
	}

	
	@Test
	public void testSaveShop(){
		Shop shop = new Shop();
		shop.setShopName("哈哈");
		
		//保存
		baseEngine.saveShop(shop);
	}
	
	@Test
	public void testUpdateShop(){
		//更新
		Shop shop = new Shop();
		shop.setShopId(2);
		shop.setShopName("商家二");
		baseEngine.updataShop(shop);
	}
	
	@Test
	public void testDeleteShop(){
		//删除
		baseEngine.deleteShop(2);
	}
	
	@Test	
	public void testSaveShopAndRule(){
		Shop shop = new Shop();
		//规则和shop一块插入
		YearMonDay y = new YearMonDay();
		HourMin h = new HourMin();
		Week w = new Week();
		
		y.setsYearMonDay("2015-01-01");
		y.seteYearMonDay("2015-02-03");
		h.setsHourMin("09:00");
		h.seteHourMin("20:00");
		w.setsWeek("2");
		w.seteWeek("2");
		
		Rule rule = new Rule();
		rule.setYearMonDay(y);
		rule.setHourMin(h);
		rule.setWeek(w);
		
		shop.setShopName("heihei");
		shop.getRules().add(rule);
		
		baseEngine.save(rule);
		baseEngine.saveShop(shop);
		
	}
	
	@Test
	public void testShopByRuleId(){
		List<Shop> shops = baseEngine.findShopByRuleId(1);
		for (int i = 0; i < shops.size(); i++) {
			String shopName = ((Shop) shops.get(i)).getShopName();
			System.out.println(shopName);
		}
	}
	
	@Test
	public void testRuleIdByShopId(){
		List<Integer> list = baseEngine.findRuleIdByShopId(1);
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testShopIdByRuleId(){
		List<Integer> list = baseEngine.findShopIdByRuleId(2);
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testShopByShopId(){
		List<Shop> list = baseEngine.findShopByShopId(1);
		
		System.out.println(list.get(0));
	}
}
