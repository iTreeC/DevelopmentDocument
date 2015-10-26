/*package com.itree.utils;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;


public class JSONUtil{
	*//**
	 * 将某个对象转换为JSON格式的字符串，并将其直接写入HttpResponse对象
	 * @param obj
	 *//*
	public static void toJSON(Object obj){
		try {
			String json = "";
			
			JsonConfig config = new JsonConfig();
			config.setIgnoreDefaultExcludes(false);
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 
			config.setExcludes(new String[]{"handler","hibernateLazyInitializer"});  
			if(obj instanceof Collection<?>) {
				JSONArray ja = JSONArray.fromObject(obj, config);
				json = ja.toString();
			} else {
				JSONObject jo = JSONObject.fromObject(obj, config);
				json = jo.toString();
			}
			
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().println(
					json
			);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("在将对象"+obj+"转换为JSON格式字符串的时�?发生异常�?");
		}
	}
}
*/