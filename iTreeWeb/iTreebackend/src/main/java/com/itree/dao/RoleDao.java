package com.itree.dao;

import com.itree.entity.TDuty;

public interface RoleDao extends BaseDao {
	public void add(TDuty d,String permission);//增加角色。
}
