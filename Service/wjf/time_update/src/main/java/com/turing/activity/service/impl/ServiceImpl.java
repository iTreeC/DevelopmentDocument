package com.turing.activity.service.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.turing.activity.service.api.Match;
import com.turing.activity.service.api.Service;
import com.turing.activity.service.api.Status;
import com.turing.activity.service.dao.api.Engine;
import com.turing.activity.service.dao.impl.BaseEngine;
import com.turing.activity.service.dao.impl.HourMinEngine;
import com.turing.activity.service.dao.impl.WeekEngine;
import com.turing.activity.service.dao.impl.YearMonDayEngine;

/**
 * 通过该服务调取相应引擎，对规则进行业务上的增删改查以及匹配
 * 
 * @author 王俊飞
 * @date 2015-10-15
 * 
 */
@Path("/Service")
public class ServiceImpl implements Service {
	Status status = new StatusImpl();
	// 调用相应引擎
	Engine baseEngine = BaseEngine.getSingleton();
	Engine yearMonDayEngine = YearMonDayEngine.getSingleton();// “年月日”引擎
	Engine hourMinEngine = HourMinEngine.getSingleton();// “时分”引擎
	Engine weekEngine = WeekEngine.getSingleton();// “周”引擎

	@Override
	public List<Integer> matchYW(){
		List<Integer> shopList;

		Match match = new MatchImpl(baseEngine, yearMonDayEngine,
				hourMinEngine, weekEngine);
		shopList = match.matchYW(status);

		return shopList;
	}
	@GET
	@Path("/match")
	@Produces(MediaType.APPLICATION_XML)
	@Override
	public List<Integer> matchYH() {
		// TODO Auto-generated method stub
		List<Integer> shopList;

		Match match = new MatchImpl(baseEngine, yearMonDayEngine,
				hourMinEngine, weekEngine);
		shopList = match.matchYH(status);

		return shopList;
	}

}
