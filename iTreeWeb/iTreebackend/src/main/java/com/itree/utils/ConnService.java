package com.itree.utils;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class ConnService {
	/**
	 * 尚未成功。该方法可以将所有变量以参数形式传递，后续进行改进时参考。
	 * 
	 * @return
	 */
	/*
	 * public String Conntce(URL url, String method, String input) throws
	 * IOException { URL targetUrl = url; HttpURLConnection httpConnection =
	 * (HttpURLConnection) targetUrl.openConnection();
	 * httpConnection.setDoOutput(true);
	 * httpConnection.setRequestMethod(method);
	 * httpConnection.setRequestProperty("Content-Type", "application/json");
	 * 
	 * // String input = "{\"rid\":1,\"pid\":2}"; OutputStream outputStream =
	 * httpConnection.getOutputStream(); outputStream.write(input.getBytes());
	 * outputStream.flush();
	 * 
	 * if (httpConnection.getResponseCode() != 200) { throw new
	 * RuntimeException("Failed : HTTP error code : " +
	 * httpConnection.getResponseCode()); }
	 * 
	 * BufferedReader responseBuffer = new BufferedReader(new
	 * InputStreamReader((httpConnection.getInputStream())));
	 * 
	 * String output; System.out.println("Output from Server:\n"); while
	 * ((output = responseBuffer.readLine()) != null) {
	 * System.out.println(output); return output; }
	 * 
	 * httpConnection.disconnect();
	 * 
	 * return null; }
	 */

	/**
	 * 目前采用该方式，url及POST/GET方法写死。
	 * 
	 * @param cid
	 * @throws URISyntaxException
	 */

	// 增加一条权限
	public void PermissionPost(int cid) throws URISyntaxException {
		Client client = Client.create();
		URI u;
		WebResource resource;
		String result;
		u = new URI("http://localhost:8080/iTreePermissionService/permission/post");
		System.out.println(u);
		resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		params.add("cid", cid);
		result = resource.queryParams(params).post(String.class);
		System.out.println(result);
	}

	// 增加一个角色并赋值
	public void PostNamePid(String name, int pid) throws URISyntaxException {
		Client client = Client.create();
		URI u;
		WebResource resource;
		String result;
		u = new URI("http://localhost:8080/iTreePermissionService/role/post/name/pid");
		System.out.println(u);
		resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		params.add("name", name);
		params.add("pid", pid);
		result = resource.queryParams(params).post(String.class);
		System.out.println(result);
	}

	// 给某一用户赋予角色（用户--角色匹配）
	public void UserRolePost(int uid, int rid) throws URISyntaxException {
		Client client = Client.create();
		URI u;
		WebResource resource;
		String result;
		u = new URI("http://localhost:8080/iTreePermissionService/userrole/cando/role");
		System.out.println(u);
		resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		params.add("uid", uid);
		params.add("rid", rid);
		result = resource.queryParams(params).get(String.class);
		System.out.println(result);
	}

}
