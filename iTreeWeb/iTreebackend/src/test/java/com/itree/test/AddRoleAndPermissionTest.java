package com.itree.test;
/**
 * 测试：增加角色的同时赋予相应的权限
 * @author macong
 * 时间：2015年12月12日 21:10:18
 * 版本：1.1
 */

import org.junit.Test;

import com.itree.permissionService.AddRoleAndPermission;
import com.itree.permissionService.impl.AddRoleAndPermissionimpl;

public class AddRoleAndPermissionTest {

	@Test
	public void test() {
		int [] permission={1,2,3};
		AddRoleAndPermission a = new AddRoleAndPermissionimpl();
		a.PostNamePid("aa", permission);
	}

}
