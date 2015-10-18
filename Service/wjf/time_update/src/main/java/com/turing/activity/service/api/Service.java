
package com.turing.activity.service.api;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.turing.activity.service.dao.api.Engine;

/**
 * 通过该服务调取相应引擎，对规则进行业务上的增删改查以及匹配
 * @author 王俊飞
 * @date 2015-10-15
 * @version 2.0
 * 
 */
@Path("/Service")
public interface Service {
	
	/**
	 * 根据年月日时分匹配，判断是否符合规则。
	 * 比如：当前时间是否在2015-01-01到2015-02-02之间的每天8：00到12:00之间
	 * 
	 * @return 返回值若为空，表示没有匹配成功；否则，返回匹配成功的商家id
	 */
	@GET
	@Path("/match")
	@Produces(MediaType.APPLICATION_XML)
	public List<Integer> match();
	
	/**
	 * 对规则的增删改，有待完善
	 */
}
