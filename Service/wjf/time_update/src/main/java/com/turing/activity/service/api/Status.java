
package com.turing.activity.service.api;

/**
 * 获取当前时间状态
 * 比如：获取当前时间为1994-02-16（年月日） 8:00（时分） 2（周）
 * @author 王俊飞
 * @date 2015-10-15
 * @version 2.0
 * 
 */
public interface Status {
	/**
	 * 获取当前时间状态
	 * @return 返回当前的时间状态，包括年月日、时分、周
	 */
	Object get();
}
