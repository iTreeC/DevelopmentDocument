package com.itree.test;
/**
 * 测试：获取某用户的权限列表
 * @author macong
 * 时间：2015年12月12日 21:06:11
 * 版本：1.0
 */
import org.junit.Test;

import com.itree.permissionService.GetUserPermission;
import com.itree.permissionService.impl.GetUserPermissionimpl;

public class GetUserPermissionTest {

	//未通过，原因不详
	//错误代码： 204 
	@Test
	public void test() {
		GetUserPermission u = new GetUserPermissionimpl();
		u.GetUid(1);
	}

}
