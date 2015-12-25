package com.position.service;

import java.util.List;

import com.position.pojo.ProvincialNumber;
/**
 * 省表管理接口
 * 
 * @author Fei
 *
 */
public interface ProvincialManage {
	// 根据id
	ProvincialNumber getById(int id);

	// 根据名
	ProvincialNumber getByName(String name);

	// 查找所有
	List<ProvincialNumber> getAll();

	// 增加
	void add(ProvincialNumber Pro);

	// 删除(隐藏式)
	void deleteByIdHid(int ID);

	// 删除（直接删除）
	void deleteById(int ID);

	// 修改
	void update(ProvincialNumber Pro);

	// 恢复（隐藏式删除的反向）
	void regainByDelete(int id);
}
