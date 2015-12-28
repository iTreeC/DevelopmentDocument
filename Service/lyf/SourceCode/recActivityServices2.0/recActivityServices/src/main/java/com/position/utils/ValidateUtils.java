package com.position.utils;

import java.util.List;

/**
 * 传入参数有效性检查
 * @copyby lixiaohuan
 * @Modify lyf
 *	编写限制条件需注意：在需要考虑到多个条件判断时，先后顺序很重要，影响效率请谨慎对待
 */
public class ValidateUtils {

	/**
	 * String类型的数据的 长度限制不大于自定义长度
	 * 
	 * @param name
	 * @param limit
	 * @return
	 */
	public Boolean length(String name, int limit) {
		if (name.length() < limit && name != null && name.trim().length() != 0 ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * String 类型的数据的 长度不大于50
	 * 
	 * @param name
	 * @param limit
	 * @return
	 */
	public Boolean length(String name) {
		if (name != null && name.trim().length() != 0 && name.length() < 50  ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Integer类型的数据的 长度大于0小于自定义长度
	 * 
	 * @param i
	 * @param limit
	 * @return
	 */
	public Boolean length(int i, int limit) {
		String a = i + "";
		if (i > 0 && i<2147483647 &&  a.length() < limit) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Integer类型的数据 大小限制(0-2147483647)
	 * 防止整型变量溢出
	 */
	public Boolean limit(int i) {

		if(i <= 2147483647 && i > 0){
			return true;
		}
		return false;
	}

	/**
	 * Integer类型的数据 大小限制(0-limit)
	 * 防止溢出于自定义长度
	 */
	public Boolean limit(int i, int limit) {

		if(i <= 2147483647 &&  i > 0  && i <= limit){
			return true;
		}
		return false;

	}

	/**
	 * List<Integer>类型的数据 大小限制(0-2147483647)
	 * 防止整型变量溢出
	 */
	public Boolean limit(List<Integer> i) {
		for (int j = 0; j < i.size(); j++) {
			if (i.get(j) > 2147483647 || i.get(j) < 0) {
				return false;
			}
		}
		return true;

	}

	/**
	 * List<Integer>类型的数据 大小限制(0-limit)
	 * 防止溢出于自定义长度
	 */
	public Boolean limit(List<Integer> i, int limit) {
		for (int j = 0; j < i.size(); j = j++)
			if (i.get(j) > 2147483647 || i.get(j) < 0 || i.get(j) > limit) {
				return false;
			}
		return true;
	}
}
