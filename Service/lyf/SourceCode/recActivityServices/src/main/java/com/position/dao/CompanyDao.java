package com.position.dao;

import java.util.List;

import com.position.pojo.Company;
/**
 * 公司表增删改查
 * @author Fei
 *
 */
public interface CompanyDao {
	//根据id
	Company getById(int id);
	//根据公司名
	Company getByName(String name);
	//查找所有
	List<Company> getAll();
	//增加
	void add(Company company);
	//删除(隐藏式)
	void deleteByIdHid(int ID);
	//删除（直接删除）
	void deleteById(int ID);
	//修改
	void update(Company company);
	//恢复（隐藏式删除的反向）
	void regainByDelete(int id);
}
