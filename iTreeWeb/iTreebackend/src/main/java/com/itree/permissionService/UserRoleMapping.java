package com.itree.permissionService;

/**
 * 定义接口
 * 功能：用户与角色匹配。
 * @author macong
 * 时间：2015年12月12日 19:57:07
 * 版本1.0
 *
 */
public interface UserRoleMapping extends BaseService {
	
	public String GetUidPid(int uid, int rid);

}
