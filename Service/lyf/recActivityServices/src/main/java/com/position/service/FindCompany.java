package com.position.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.position.dao.CompanyDao;
import com.position.dao.PositionDao;
import com.position.dao.impl.CompanyDaoImpl;
import com.position.dao.impl.PositionDaoImpl;
import com.position.pojo.Business_Position;
import com.position.pojo.Company;
/**
 * 查找招聘公司服务端口
 * @author Fei
 *
 */
@Path("/findcompany")
public class FindCompany {

	private static Logger logger = Logger
			.getLogger(FindCompany.class);
	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	CompanyDao comp =  (CompanyDao) ctx.getBean("companydao");
	
	private List<Business_Position> list;
	private List<Company> listcom;
	//CompanyDaoImpl comp;
	private Company com;

	/**
	 * 通过城市id查找对应公司
	 * @param cityId
	 * @return 有数据则返回公司实体集合，否则返回null
	 */
	@GET
	@Path("/bycityid")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Company> FindCompanyByCityId(@QueryParam("cityid") int cityid) {

		//边界值验证
		if(cityid<0 && cityid>65535){
			logger.error("传入参数超出范围");
			return null;
		}
		PositionDao pos = new PositionDaoImpl();
		list = pos.getByCity(cityid);
		comp = new CompanyDaoImpl();
		listcom = new ArrayList<Company>();
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				//com= new Company();
				int a = list.get(i).getCompanyID();
				//System.out.println(a);			
				com = comp.getById(a);
				//System.out.println(a);
				listcom.add(i, com);
				//System.out.println(listcom.get(i).getCompanyName());
			}
			logger.info("查找成功");
		} else {
			logger.error("查找失败,不存在相应的数据");
			//System.out.println("查找失败");
		}
		return listcom;
	}
	
	@GET
	@Path("/bycityidforString")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<List<Company>> FindCompanyForString(@QueryParam("citiesid") String citiesid) {

		List<List<Company>> listcoms = new ArrayList<List<Company>>();
		int trans;
		//边界值验证
		if(citiesid == null){
			logger.error("传入参数不能为空");
			logger.error("传入参数为空");
			return null;
		}
		//解析字符串(中文逗号)
		String[] result=citiesid.split("，");
	    for(int i=0;i<result.length;i++) {
	        System.out.println(result[i]);
	        trans =Integer.parseInt(result[i]) ;
	        listcom = this.FindCompanyByCityId(trans);
	        listcoms.add(listcom);
	    }
		return listcoms;
	}
}
