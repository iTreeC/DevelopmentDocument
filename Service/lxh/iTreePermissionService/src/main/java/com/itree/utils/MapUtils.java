package com.itree.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtils {
	String result = "result";

	public Map<String, Boolean> map(String key, Boolean value) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(key, value);
		return map;
	}

	public Map<String, Boolean> map(Boolean value) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(result, value);
		return map;
	}

	public Map<String, String> map(String key, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		return map;
	}

	public Map<String, String> map(String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(result, value);
		return map;
	}
	
	public Map<String, Integer> map(String key, int value) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(key, value);
		return map;
	}

	public Map<String, Integer> map(int value) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(result, value);
		return map;
	}

	public List<Map<String, String>> map(String key, List<String> value) {
		List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		for (int i = 0; i < value.size(); i++) {
			map = new HashMap<String, String>();
			map.put(key, value.get(i));
			maplist.add(map);
		}
		return maplist;
	}

	public List<Map<String, String>> map(List<String> value) {
		List<Map<String, String>> maplist = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		for (int i = 0; i < value.size(); i++) {
			map = new HashMap<String, String>();
			map.put(result, value.get(i));
			maplist.add(map);
		}
		return maplist;
	}
	public List<Map<String, Integer>> idmap(String key, List<Integer> value) {
		List<Map<String, Integer>> maplist = new ArrayList<Map<String, Integer>>();
		Map<String, Integer> map;
		for (int i = 0; i < value.size(); i++) {
			map = new HashMap<String, Integer>();
			map.put(key, value.get(i));
			maplist.add(map);
		}
		return maplist;
	}

	public List<Map<String, Integer>> idmap(List<Integer> value) {
		List<Map<String, Integer>> maplist = new ArrayList<Map<String, Integer>>();
		Map<String, Integer> map;
		for (int i = 0; i < value.size(); i++) {
			 map = new HashMap<String, Integer>();
			map.put(result, value.get(i));
			maplist.add(map);
		}
		return maplist;
	}

}
