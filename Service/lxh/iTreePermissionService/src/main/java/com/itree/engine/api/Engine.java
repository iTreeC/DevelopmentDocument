/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

public interface Engine {



	/**
	 * ���ܣ�ȥ���ظ�����
	 * 
	 * @param ids
	 *            ��ȥ�ص�����List<Integer>
	 * @return List<Integer>
	 */
	public List<Integer> dereplication(List<Integer> ids);
}
