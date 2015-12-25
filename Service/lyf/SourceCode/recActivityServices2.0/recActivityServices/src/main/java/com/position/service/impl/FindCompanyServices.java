package com.position.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.position.dao.CompanyDao;
import com.position.dao.PositionDao;
import com.position.pojo.Company;
import com.position.pojo.CompanyPosition;
import com.position.service.FindCompany;

/**
 * 查找招聘公司的服务端口
 * 
 * @author Fei
 * 
 */
@Path("/findcompany")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Service
public class FindCompanyServices implements FindCompany {

	private static Logger logger = Logger.getLogger(FindCompanyServices.class);

	private List<CompanyPosition> list;
	private List<Company> listcom;
	private Company com;
	private List<List<Company>> listcoms;

	@Resource
	private PositionDao positionDaoImpl;
	@Resource
	private CompanyDao companyDaoImpl;

	/**
	 * 通过城市id查找对应公司
	 * 
	 * @param cityid
	 *            城市id
	 * @return listcom 有数据则返回公司实体集合，否则返回null
	 * @author Fei
	 */
	@GET
	@Path("/bycityid")
	public List<Company> FindCompanyByCityId(@QueryParam("cityid") int cityid) {

		// 边界值验证
		if (cityid < 0 || cityid > 65535) {
			logger.error("传入参数超出范围");
			return null;
		}
		try {
			System.out.println("服务*********************************" + positionDaoImpl);
			list = positionDaoImpl.getByCity(cityid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		listcom = new ArrayList<Company>();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				int a = list.get(i).getCompany().getId();
				com = companyDaoImpl.getById(a);
				listcom.add(i, com);
			}
			logger.info("查找成功");
		} else {
			logger.error("查找失败,不存在相应的数据");
		}
		return listcom;
	}

	/**
	 * 通过城市id集合，查找与之一一对应的公司集合
	 * 
	 * @param citiesid
	 *            城市id集合
	 * @return listcoms 与每个城市相对应的公司信息集合，没有数据则返回NULL
	 * @author Fei
	 */
	@GET
	@Path("/bycityidforString")
	public List<List<Company>> FindCompanyForString(@QueryParam("citiesid") String citiesid) {
		int trans;
		// 边界值验证
		if (citiesid == null || citiesid.trim().length() == 0) {
			logger.error("传入参数不能为空");
			return null;
		}
		// 解析字符串1(英文逗号)
		String[] result = citiesid.split(",");
		if (result.equals(citiesid)) {
			// 解析字符串2(中文逗号)
			result = citiesid.split("，");
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
			trans = Integer.parseInt(result[i]);
			listcom = this.FindCompanyByCityId(trans);
			listcoms.add(listcom);
		}
		return listcoms;
	}

	/**
	 * 通过部分地址，查找地址中包含其名字的公司集合
	 * 
	 * @param address
	 *            部分地址信息
	 * @param address
	 * @return listcom 有数据则返回公司实体集合，否则返回null
	 */
	@GET
	@Path("/byaddress")
	public List<Company> FindCompanyByaddress(@QueryParam("address") String address) {

		// 边界值验证
		if (address == null || address.trim().length() == 0) {
			logger.error("传入参数不能为空");
			return null;
		}
		list = positionDaoImpl.getByAddress(address);
		listcom = new ArrayList<Company>();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				int a = list.get(i).getCompany().getId();
				com = companyDaoImpl.getById(a);
				listcom.add(i, com);
			}
			logger.info("查找成功");
		} else {
			logger.error("查找失败,不存在相应的数据");
		}
		return listcom;
	}
	
	/***********************get/set方法******************************/
	public PositionDao getPositionDaoImpl() {
		return positionDaoImpl;
	}

	public void setPositionDaoImpl(PositionDao positionDaoImpl) {
		this.positionDaoImpl = positionDaoImpl;
	}

	public CompanyDao getCompanyDaoImpl() {
		return companyDaoImpl;
	}

	public void setCompanyDaoImpl(CompanyDao companyDaoImpl) {
		this.companyDaoImpl = companyDaoImpl;
	}
}
