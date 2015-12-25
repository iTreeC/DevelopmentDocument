package com.position.service;

import java.util.List;

import com.position.pojo.Company;

/**
 * 查找招聘公司的服务端口
 * 
 * @author Fei
 * 
 */

public interface FindCompany {
	/**
	 * 通过城市id查找对应公司
	 * 
	 * @param cityid
	 *            城市id
	 * @return listcom 有数据则返回公司实体集合，否则返回null
	 * @author Fei
	 */
	public List<Company> FindCompanyByCityId(int cityid);

	/**
	 * 通过城市id集合，查找与之一一对应的公司集合
	 * 
	 * @param citiesid
	 *            城市id集合
	 * @return listcoms 与每个城市相对应的公司信息集合，没有数据则返回NULL
	 * @author Fei
	 */
	public List<List<Company>> FindCompanyForString(String citiesid);

	/**
	 *	通过部分地址，查找地址中包含其名字的公司集合
	 * 
	 * @param address
	 *            部分地址信息
	 * @param address
	 * @return listcom 有数据则返回公司实体集合，否则返回null
	 */
	public List<Company> FindCompanyByaddress(String address);
}
