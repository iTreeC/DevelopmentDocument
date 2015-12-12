package com.itree.permissionService.impl;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UserRoleMappingimpl implements com.itree.permissionService.UserRoleMapping {

	public String GetUidPid(int uid, int rid) {
		// TODO Auto-generated method stub
		Client client = Client.create();
		URI u;
		WebResource resource;
		String result;

		try {
			u = new URI("http://localhost:8080/iTreePermissionService/userrole/cando/role");
			System.out.println(u);
			resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			params.add("uid", uid);
			params.add("rid", rid);
			result = resource.queryParams(params).get(String.class);
			System.out.println(result);
			return result;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
