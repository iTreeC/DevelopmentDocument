
package com.turing.activity.service.api;

import java.util.List;
import java.util.Map;

import com.turing.activity.service.dao.api.Engine;
import com.turing.activity.service.entity.Shop;
/**
 * 用于当前时间和规则的匹配
 * @author 王俊飞
 * @date 2015-10-15
 * @version 2.0
 *
 */
public interface Match{

	/**
	 * 根据年月日时分匹配，判断是否符合规则。
	 * 比如：当前时间是否在2015-01-01到2015-02-02之间的每天8：00到12:00之间
	 * 
	 * @param status 传入的当前时间。包括年月日、时分、周
	 * @return 返回值若为空，表示没有匹配成功；否则，返回匹配成功的商家id
	 */
	public List<Integer> match(Status status);
	/**
	 * 根据年月日匹配，判断是否符合规则。
	 * 比如：当前时间是否在2015-01-01到2015-02-02之间
	 * 
	 * @param status 传入的当前时间。包括年月日时分周
	 * @return 返回值若为空，表示没有匹配成功；否则，返回匹配成功的商家id
	 */
	public List<Integer> matchYearMonDay(Status status);
	/**
	 * 根据时分匹配，判断是否符合规则。
	 * 比如：当前时间是否8:00到12:00之间
	 * 
	 * @param status 传入的当前时间。包括年月日时分周
	 * @return 返回值若为空，表示没有匹配成功；否则，返回匹配成功的商家id
	 */
	public List<Integer> matchHourMin(Status status);
	/**
	 * 根据周匹配，判断当前这一天是否在一周之内。
	 * 比如：当前时间是否在周一和周五之间
	 * 
	 * @param status 传入的当前时间。包括年月日时分周
	 * @return 返回值若为空，表示没有匹配成功；否则，返回匹配成功的商家id
	 */
	public List<Integer> matchWeekBetween(Status status);
	/**
	 * 根据周匹配，判断当前这一天是否为一周内的某一天。
	 * 比如：当前时间是否是周三
	 * 
	 * @param status 传入的当前时间。包括年月日时分周
	 * @return 返回值若为空，表示没有匹配成功；否则，返回匹配成功的商家id
	 */
	public List<Integer> matchWeekEquip(Status status);
}
