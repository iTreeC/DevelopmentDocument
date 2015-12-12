package com.itree.permissionService;

/**
 * 定义接口
 * 功能：增加角色的同时赋予权限。
 * @author macong
 * 时间：2015年12月12日 19:44:52
 * 版本1.0
 *
 */
public interface AddRoleAndPermission extends BaseService {
	
	public String PostNamePid(String name, int[] permission);

}
