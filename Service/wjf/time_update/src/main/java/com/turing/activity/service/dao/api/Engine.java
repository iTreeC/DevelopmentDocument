package com.turing.activity.service.dao.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.entity.Shop;
import com.turing.activity.service.judge.IsARule;

/**
 * 一个引擎实例对应多条规则，具有对规则的增、删、改、查的能力。
 * @author 王俊飞
 * @date   2015-10-15
 * @version 2.0
 *
 */
public interface Engine {
	/********对Rule的相关操作*********/
	public void save(Rule o);//保存某个对象
	public void delete(int id);//删除某对象
	public void updata(Rule o);//更新
	
	public Rule findById(int id);//通过ID查询
	public List<Rule> find(String hql);//hql语句查询
	public List<Rule> find();//查询对应引擎的所有规则
	
	/********对Shop的相关操作*********/
	public List<Shop> findShopByShopId(int shopId);//通过shopId查询shop
	
	public void saveShop(Shop o);//保存某个对象
	public void deleteShop(int id);//删除某对象
	public void updataShop(Shop o);//更新
	
	/********其他的相关操作*********/
	public List findByShopId(int shopId); //通过shopId查询rule
	public List<Shop> findShopByRuleId(int ruleId); //通过ruleId查询shop
	public List<Integer> findRuleIdByShopId(int shopId);//通过shopId查询ruleId
	public List<Integer> findShopIdByRuleId(int ruleId);//通过ruleId查询shopId
	
	public Long count(String hql);//查询某表的全部信息条数
	public Long count(String hql,Map<String,Object> params);//查询某表的某条件下的信息条数
	public void executeHql(String hql);//执行hql语句
	
}

