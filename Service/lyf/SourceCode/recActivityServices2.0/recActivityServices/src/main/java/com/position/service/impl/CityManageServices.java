package com.position.service.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.position.dao.CityNumberDao;
import com.position.dao.impl.CityNumberDaoImpl;
import com.position.pojo.CityNumber;
import com.position.pojo.ProvincialNumber;
import com.position.service.CityManage;

/**
 * 城市信息管理的服务端口
 * @author Fei
 *
 */
@Path("/citymanage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CityManageServices implements CityManage {

	//private static Logger logger = Logger.getLogger(CityManageServices.class);
	private CityNumberDao cityDao = new CityNumberDaoImpl();

	// 根据id
	@GET
	@Path("/getbyid")
	public CityNumber getById(@QueryParam("cityid") int cityid) {
		return cityDao.getById(cityid);
	}

	// 根据城市名
	@GET
	@Path("/getbyname")
	public CityNumber getByName(@QueryParam("name") String name) {
		return cityDao.getByName(name);
	}

	// 根据父类名字
	@GET
	@Path("/getbyparentname")
	public List<CityNumber> getByParentName(@QueryParam("parentCity") String parentCity) {
		return cityDao.getByParentName(parentCity);
	}

	// 根据父类id
	@GET
	@Path("/getbyparentid")
	public List<CityNumber> getByParentId(@QueryParam("parentCity") int parentCity) {
		return cityDao.getByParentId(parentCity);
	}

	// 查找所有
	@GET
	@Path("/getall")
	public List<CityNumber> getAll() {
		return cityDao.getAll();
	}

	// 增加(一个参数)
	@GET
	@Path("/add")
	public void add(@QueryParam("city") CityNumber city) {
		cityDao.add(city);
	}

	// 增加(两个参数)
	@GET
	@Path("/addtwo")
	public void addtwo(@QueryParam("city") CityNumber city,@QueryParam("pro")  ProvincialNumber pro) {
		cityDao.addtwo(city, pro);
	}

	// 删除(隐藏式)
	@GET
	@Path("/deletebyidhib")
	public void deleteByIdHid(@QueryParam("cityID") int cityID) {
		cityDao.deleteByIdHid(cityID);
	}

	// 删除（直接删除）
	@GET
	@Path("/deletebyid")
	public void deleteById(@QueryParam("cityID") int cityID) {
		cityDao.deleteById(cityID);
	}

	// 修改
	@GET
	@Path("/upodate")
	public void update(@QueryParam("city") CityNumber city) {
		cityDao.update(city);
	}

	// 恢复（隐藏式删除的反向）
	@GET
	@Path("/regainbydelete")
	public void regainByDelete(@QueryParam("cityid") int cityid) {
		cityDao.regainByDelete(cityid);
	}
}
