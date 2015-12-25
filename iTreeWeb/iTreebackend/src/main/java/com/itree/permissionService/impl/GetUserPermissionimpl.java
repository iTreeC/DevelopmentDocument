package com.itree.permissionService.impl;

/**
 * 功能：获得某用户的权限列表
 * @author macong
 * 时间：2015年12月12日 20:54:42
 * 版本1.0
 *
 */
import java.net.URI;
import java.net.URISyntaxException;
import com.itree.permissionService.GetUserPermission;
import com.itree.utils.JSONUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class GetUserPermissionimpl implements GetUserPermission {

	public String GetUid(int uid) {
		// TODO Auto-generated method stub
		Client client = Client.create();
		URI u;
		WebResource resource;
		//Map<String, Object> result = new HashMap<String, Object>();
		String result;
		try {
			u = new URI("http://localhost:8080/iTreePermissionService/userpermission/get/permissionid");
			System.out.println(u);
			resource = client.resource(u);
			MultivaluedMapImpl params = new MultivaluedMapImpl();
			params.add("uid", uid);
			result = resource.queryParams(params).get(String.class);
			
			
//			JSONUtil l = new JSONUtil();
//			System.out.println(l.jsonListToObjectList(result));
			
			
			System.out.println(result);
			return result;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
