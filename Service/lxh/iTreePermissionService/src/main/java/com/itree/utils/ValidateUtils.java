package com.itree.utils;

import java.util.List;

/**
 * ���ݳ��ȼ��
 * 
 * @author ������
 *
 */
public class ValidateUtils {

	/*String regex = "[1-9][0-9]{1,10}";

	public Boolean validate(String rid) {
		if (!rid.matches(regex))
			return false;
		return false;
	}*/

	/**
	 * String���͵����ݵ� ���ȱȽ�
	 * 
	 * @param name
	 * @param limit
	 * @return
	 */
	public Boolean length(String name, int limit) {
		if (name.length() < limit) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * String ���͵����ݵ� ���ȱȽ�
	 * 
	 * @param name
	 * @param limit
	 * @return
	 */
	public Boolean length(String name) {
		if (name.length() < 50) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Integer���͵����ݵ� ���ȱȽ�
	 * 
	 * @param i
	 * @param limit
	 * @return
	 */
	public Boolean length(int i, int limit) {
		String a = i + "";
		if (a.length() < limit & i > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Integer���͵����� ��С����(0-2147483647)
	 */
	public Boolean limit(int i) {

		if(i <= 2147483647 & i > 0){
			return true;
		}
		return false;
	}

	/**
	 * Integer���͵����� ��С����(0-limit)
	 */
	public Boolean limit(int i, int limit) {

		if(i <= limit & i > 0){
			return true;
		}
		return false;

	}

	/**
	 * List<Integer>���͵����� ��С����(0-2147483647)
	 */
	public Boolean limit(List<Integer> i) {
		for (int j = 0; j < i.size(); j++) {
			if (i.get(j) > 2147483647 & i.get(j) > 0) {
				return false;
			}
		}
		return true;

	}

	/**
	 * List<Integer>���͵����� ��С����(0-limit)
	 */
	public Boolean limit(List<Integer> i, int limit) {
		for (int j = 0; j < i.size(); j = j++)
			if (i.get(j) > limit & i.get(j) > 0) {
				return false;
			}
		return true;

	}

}
