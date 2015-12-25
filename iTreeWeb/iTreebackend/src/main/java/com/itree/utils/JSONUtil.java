package com.itree.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;


public class JSONUtil{
	*//**
		 * 将某个对象转换为JSON格式的字符串，并将其直接写入HttpResponse对象
		 * 
		 * @param obj
		 *//*
		 * public static void toJSON(Object obj){ try { String json = "";
		 * 
		 * JsonConfig config = new JsonConfig();
		 * config.setIgnoreDefaultExcludes(false);
		 * config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		 * config.setExcludes(new
		 * String[]{"handler","hibernateLazyInitializer"}); if(obj instanceof
		 * Collection<?>) { JSONArray ja = JSONArray.fromObject(obj, config);
		 * json = ja.toString(); } else { JSONObject jo =
		 * JSONObject.fromObject(obj, config); json = jo.toString(); }
		 * 
		 * ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		 * ServletActionContext.getResponse().getWriter().println( json );
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace(); throw new
		 * RuntimeException("在将对象"+obj+"转换为JSON格式字符串的时�?发生异常�?"); } } }
		 */

// test
public class JSONUtil {
	// [{"username":"7845166313","password":"123456"},{"username":"545434","password":"3455"},
	// {"username":"45354","password":"345454"}] 数组格式的解析
	public List<Map<Object, Object>> jsonListToObjectList(String json) {
		List<Map<Object, Object>> listMap = new ArrayList<Map<Object, Object>>();// 实例化一个map类型的集合对象
		String data = json.replaceAll("\"", "").replace("[{", "").replace("}]", "");// 去掉前后括号、双引号
		String[] arry = data.split("},\\{"); // 根据“},{”分隔字符串，获得对象数组
		for (int i = 0; i < arry.length; i++) {
			String[] arry2 = arry[i].split(",");// 以逗号分隔对象（"username":"7845166313","password":"123456"）
			Map<Object, Object> m = new HashMap<Object, Object>();// 实例化map对象
			for (int j = 0; j < arry2.length; j++) {
				String[] aray3 = arry2[j].split(":");// 根据”:“冒号分隔字符串，获得键-值数组对象
				m.put(aray3[0], aray3[1]);// 将对象以键-值对的方式存入map中
			}
			listMap.add(m);// 将对象存入集合中
			System.out.println("hhhhh");
		}
		return listMap;
	}
	
}