package com.position.dao;

import java.util.List;

import com.position.pojo.CityNumber;
/**
 * 城市表增删改查操作
 * @author Fei
 *
 */

public interface CityNumberDao{
	
	//根据id
	CityNumber getById(int cityid);
	//根据城市名
	CityNumber getByName(String name);
//	//根据父类名字
//	List<City_Number> getByParentName(String parentCity);
	//根据父类id
	List<CityNumber> getByParentId(int parentCity);
	//查找所有
	List<CityNumber> getAll();
	//增加
	void add(CityNumber city);
	//删除(隐藏式)
	void deleteByIdHid(int cityID);
	//删除（直接删除）
	void deleteById(int cityID);
	//修改
	void update(CityNumber city);
	//恢复（隐藏式删除的反向）
	void regainByDelete(int cityid);
}
