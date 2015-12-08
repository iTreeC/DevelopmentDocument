package com.itree.dao;

import java.util.List;

import com.itree.entity.TDuty;

public interface RoleDao extends BaseDao {
	public void add(TDuty d,int [] permission);//增加角色。
	public List<TDuty> getAll();//获取角色列表

}
