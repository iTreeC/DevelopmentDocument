package com.position.dao;

import java.util.List;

import com.position.pojo.CompanyPosition;
import com.position.pojo.Company;
/**
 * 公司表增删改查
 * @author Fei
 *
 */
public interface PositionDao {
	//根据id
	CompanyPosition getById(int id);
	//根据公司id
	CompanyPosition getByPosId(int id);
	//查找所有
	List<CompanyPosition> getAll();
	//根据详尽地址查找（模糊查询）
	List<CompanyPosition> getByAddress(String str);
	//根据省查找
	List<CompanyPosition> getByPro(int id);
	//根据市查找
	List<CompanyPosition> getByCity(int id);
	//根据县查找
	List<CompanyPosition> getByCount(int id);
	//增加
	void add(CompanyPosition Bus);
	//删除(隐藏式)
	void deleteByIdHid(int ID);
	//删除（直接删除）
	void deleteById(int ID);
	//修改
	void update(CompanyPosition Bus);	
	//恢复（隐藏式删除的反向）
	void regainByDelete(int id);
}
