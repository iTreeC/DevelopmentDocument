package com.position.service;

import java.util.List;
import java.util.Map;

import com.position.pojo.CompanyPosition;
/**
 * 位置表管理服务接口
 * @author Fei
 *
 */
public interface PositionManage {
	// 根据id
	CompanyPosition getById(int id);

	// 根据公司id
	CompanyPosition getByPosId(int id);

	// 查找所有
	List<CompanyPosition> getAll();

	// 根据详尽地址查找（模糊查询）
	List<CompanyPosition> getByAddress(String str);

	// 根据省查找
	List<CompanyPosition> getByPro(int id);

	// 根据市查找
	List<CompanyPosition> getByCity(int id);

	// 根据县查找
	List<CompanyPosition> getByCount(int id);

	// 增加
	//Map<String, Boolean> add(CompanyPosition Bus);

	// 删除(隐藏式)
	Map<String, Boolean> deleteByIdHid(int ID);

	// 删除（直接删除）
	Map<String, Boolean> deleteById(int ID);

	// 修改
	//Map<String, Boolean> update(CompanyPosition Bus);

	// 恢复（隐藏式删除的反向）
	Map<String, Boolean> regainByDelete(int id);
}
