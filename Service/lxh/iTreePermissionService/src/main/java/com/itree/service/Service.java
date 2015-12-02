package com.itree.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.PermissionEngineAPI;
import com.itree.engine.api.RoleEngineAPI;
import com.itree.engine.api.RolePermissionEngineAPI;
import com.itree.engine.api.UserEngineAPI;
import com.itree.engine.api.UserRoleEngineAPI;
import com.itree.engine.api.UserPermissionEngineAPI;
import com.itree.utils.MapUtils;
import com.itree.utils.ValidateUtils;

public class Service {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	PermissionEngineAPI pengine = (PermissionEngineAPI) ctx.getBean("pengine");

	RoleEngineAPI rengine = (RoleEngineAPI) ctx.getBean("rengine");

	UserRoleEngineAPI urengine = (UserRoleEngineAPI) ctx.getBean("urengine");

	RolePermissionEngineAPI rpengine = (RolePermissionEngineAPI) ctx
			.getBean("rpengine");

	UserEngineAPI uengine = (UserEngineAPI) ctx.getBean("uengine");

	UserPermissionEngineAPI upengine = (UserPermissionEngineAPI) ctx
			.getBean("upengine");

	MapUtils maputils = new MapUtils();
	ValidateUtils validate = new ValidateUtils();
}
