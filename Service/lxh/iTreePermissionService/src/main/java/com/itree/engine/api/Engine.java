/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

public interface Engine {



	/**
	 * 功能：去除重复数据
	 * 
	 * @param ids
	 *            待去重的数组List<Integer>
	 * @return List<Integer>
	 */
	public List<Integer> dereplication(List<Integer> ids);
}
