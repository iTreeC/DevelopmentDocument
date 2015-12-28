package com.position.service;

import java.util.List;
import java.util.Map;

import com.position.pojo.CityNumber;
import com.position.pojo.ProvincialNumber;

/**
 * 城市表管理接口
 * 
 * @author Fei
 *
 */
public interface CityManage {
	// 根据id
	CityNumber getById(int cityid);

	// 根据城市名
	CityNumber getByName(String name);

	// 根据父类名字
	List<CityNumber> getByParentName(String parentCity);

	// 根据父类id
	List<CityNumber> getByParentId(int parentCity);

	// 查找所有
	List<CityNumber> getAll();

	// 增加(单一参数)
	//Map<String, Boolean> add(CityNumber city);

	// 增加(两个参数)
	//Map<String, Boolean> addTwo(CityNumber city, ProvincialNumber pro);

	// 删除(隐藏式)
	Map<String, Boolean> deleteByIdHid(int cityID);

	// 删除（直接删除）
	Map<String, Boolean> deleteById(int cityID);

	// 修改
	//Map<String, Boolean> postUpdate(CityNumber city);

	// 恢复（隐藏式删除的反向）
	Map<String, Boolean> regainByDelete(int cityid);
}
