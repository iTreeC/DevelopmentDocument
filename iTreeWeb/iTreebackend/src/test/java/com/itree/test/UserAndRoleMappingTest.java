package com.itree.test;
/**
 * 增加用户，进行用户--角色的匹配
 * @author macong
 * 时间：2015年12月12日 20:31:29
 * 版本：1.0
 */

import org.junit.Test;
import com.itree.permissionService.AddUser;
import com.itree.permissionService.UserRoleMapping;
import com.itree.permissionService.impl.AddUserimpl;
import com.itree.permissionService.impl.UserRoleMappingimpl;

public class UserAndRoleMappingTest {

	@Test
	public void test() {
		AddUser adduser = new AddUserimpl();
		adduser.PostCid(111);
		
		UserRoleMapping urm = new UserRoleMappingimpl();
		urm.GetUidPid(111, 1);
	}

}
