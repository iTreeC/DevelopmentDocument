package com.itree.xh.client;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class ClientTest {

	public static void main(String[] args) throws URISyntaxException {
/*		Client client = Client.create();
		URI u;
		WebResource resource;
		String result;
		u = new URI("http://localhost:8080/iTreePermissionService/role/get");
		System.out.println(u);
		resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		params.add("rid", "1");
		result = resource.queryParams(params).get(String.class);
		System.out.println(result);*/
		
	/*	Client client = Client.create();
		URI u;
		WebResource resource;
		String result;
		u = new URI("http://localhost:8080/iTreePermissionService/role/post");
		System.out.println(u);
		resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		params.add("rid", "422");
		params.add("pid", "22");
		params.add("pid", "2");
		result = resource.queryParams(params).post(String.class);
		System.out.println(result);*/
		
	/*	Client client = Client.create();
		URI u;
		WebResource resource;
		String result;
		u = new URI("http://localhost:8080/iTreePermissionService/user/get");
		System.out.println(u);
		resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		params.add("uid", "1");
		result = resource.queryParams(params).get(String.class);
		System.out.println(result);*/
		
		
		Client client = Client.create();
		URI u;
		WebResource resource;
		String result;
		u = new URI("http://localhost:8080/iTreePermissionService/role/post");
		System.out.println(u);
		resource = client.resource(u);
		MultivaluedMapImpl params = new MultivaluedMapImpl();
		params.add("uid", "1");
		params.add("pid", "2");
		result = resource.queryParams(params).post(String.class);
		System.out.println(result);

	}
}
