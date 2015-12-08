package com.itree.service;

import java.util.List;
import com.itree.entity.TDuty;

public interface RoleService {
	public List<TDuty> getAll();//获取角色列表
	public void add(TDuty tduty,int[] permission);
	public void delete(int d);//根据id删除角色
	public TDuty get(int id);//根据id返回角色实体
	public void doUpdate(TDuty tduty,String permission);//更新角色信息
	
}
