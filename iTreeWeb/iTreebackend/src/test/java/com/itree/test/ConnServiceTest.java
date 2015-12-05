package com.itree.test;

import java.net.URISyntaxException;
import org.junit.Test;
import com.itree.utils.ConnService;

import junit.framework.TestCase;

public class ConnServiceTest extends TestCase {
	/*@Test
	public void test() throws IOException {
		 String targetURL = "http://localhost:8080/iTreePermissionService/rolepermission/post";
		 URL url = new URL(targetURL);
		 String method = "POST";
		 String input = "{\"rid\":1,\"pid\":2}";
		 ConnService c = new ConnService();
		 c.Conntce(url, method, input);
	}*/
	@Test
	//增加一条权限
	public void test() throws URISyntaxException {
		int cid=1;
		ConnService c = new ConnService();
		c.PermissionPost(cid);
	}
	
	//增加一个角色并赋予权限
	public void test1() throws URISyntaxException {
		String name="test";
		int pid=1;
		ConnService c = new ConnService();
		c.PostNamePid(name,pid);
	}
	// 给某一用户赋予角色（用户--角色匹配）
	public void test2() throws URISyntaxException {
		int uid=1;
		int rid=1;
		ConnService c = new ConnService();
		c.UserRolePost(uid, rid);
	}
}