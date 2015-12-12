package com.itree.permissionService.impl;
/**
 * 功能：增加角色的同时赋予权限。
 * @author macong
 * 时间：2015年12月12日 19:44:52
 * 版本1.0
 *
 */
import java.net.URI;
import java.net.URISyntaxException;

import com.itree.permissionService.AddRoleAndPermission;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class AddRoleAndPermissionimpl implements AddRoleAndPermission {

	public String PostNamePid(String name, int[] permission) {
		// TODO Auto-generated method stub
		Client client = Client.create();
		URI u;
		WebResource resource;
		String result;
		
		try {
			u = new URI("http://localhost:8080/iTreePermissionService/role/post/name/pid");
			System.out.println(u);
			resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			params.add("name", name);
			for (int i = 0; i < permission.length; i++) {
				params.add("pid", permission[i]);

			}
			result = resource.queryParams(params).post(String.class);
			System.out.println(result);
			return result;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
