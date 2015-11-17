package com.position.dao;

import java.util.List;

import com.position.pojo.City_Number;
/**
 * 城市表增删改查操作
 * @author Fei
 *
 */

public interface CityNumberDao{
	
	//根据id
	City_Number getById(int cityid);
	//根据城市名
	City_Number getByName(String name);
//	//根据父类名字
//	List<City_Number> getByParentName(String parentCity);
	//根据父类id
	List<City_Number> getByParentId(int parentCity);
	//查找所有
	List<City_Number> getAll();
	//增加
	void add(City_Number city);
	//删除(隐藏式)
	void deleteByIdHid(int cityID);
	//删除（直接删除）
	void deleteById(int cityID);
	//修改
	void update(City_Number city);
	//恢复（隐藏式删除的反向）
	void regainByDelete(int cityid);
}
