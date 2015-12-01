package com.itree.test;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.itree.utils.ConnService;

import junit.framework.TestCase;

public class ConnServiceTest extends TestCase {
	@Test
	public void test() throws IOException {
		 String targetURL = "http://localhost:8080/iTreePermissionService/rolepermission/post";
		 URL url = new URL(targetURL);
		 String method = "POST";
		 String input = "{\"rid\":1,\"pid\":2}";
		 ConnService c = new ConnService();
		 c.Conntce(url, method, input);
	}
}