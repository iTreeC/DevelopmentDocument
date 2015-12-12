package com.itree.permissionService.impl;
/**
 * 功能：增加用户。
 * @author macong
 * 时间：2015年12月12日 20:26:15
 * 版本1.0
 *
 */
import java.net.URI;
import java.net.URISyntaxException;

import com.itree.permissionService.AddUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class AddUserimpl implements AddUser {

	public String PostCid(int cid) {
		// TODO Auto-generated method stub
		Client client = Client.create();
		URI u;
		WebResource resource;
		String result;

		try {
			u = new URI("http://localhost:8080/iTreePermissionService/user/post");
			System.out.println(u);
			resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			params.add("cid", cid);
			result = resource.queryParams(params).post(String.class);
			System.out.println(result);
			return result;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
