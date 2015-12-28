package com.position.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.PostUpdate;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.position.dao.CityNumberDao;
import com.position.pojo.CityNumber;
import com.position.pojo.ProvincialNumber;
import com.position.service.CityManage;
import com.position.utils.MapUtils;
import com.position.utils.ValidateUtils;

/**
 * 城市信息管理的服务端口
 * 
 * @author Fei
 *
 */
@Path("/citymanage")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
//@Service
public class CityManageServices implements CityManage {

	// private static Logger logger =
	// Logger.getLogger(CityManageServices.class);
	// 启动spring容器，完成注入
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	CityNumberDao cityNumberDaoImpl = (CityNumberDao) ctx.getBean("cityNumberDaoImpl");

	ValidateUtils validate = new ValidateUtils();// 判断传入参数是否有效
	MapUtils maputils = new MapUtils();//返回值自定义map格式

	// 根据id
	@GET
	@Path("/getbyid")
	public CityNumber getById(@QueryParam("cityid") int cityid) {
		if (validate.limit(cityid))
			return cityNumberDaoImpl.getById(cityid);
		return null;
	}

	// 根据城市名
	@GET
	@Path("/getbyname")
	public CityNumber getByName(@QueryParam("name") String name) {
		if (validate.length(name))
			return cityNumberDaoImpl.getByName(name);
		return null;
	}

	// 根据父类名字
	@GET
	@Path("/getbyparentname")
	public List<CityNumber> getByParentName(@QueryParam("parentCity") String parentCity) {
		if (validate.length(parentCity))
			return cityNumberDaoImpl.getByParentName(parentCity);
		return null;
	}

	// 根据父类id
	@GET
	@Path("/getbyparentid")
	public List<CityNumber> getByParentId(@QueryParam("parentCity") int parentCity) {
		if (validate.limit(parentCity))
			return cityNumberDaoImpl.getByParentId(parentCity);
		return null;
	}

	// 查找所有
	@GET
	@Path("/getall")
	public List<CityNumber> getAll() {
		return cityNumberDaoImpl.getAll();
	}

	// 增加(一个参数,城市实体)
/*	@POST
	@Path("/add")
	public Map<String, Boolean> add(@QueryParam("city") CityNumber city) {
		try {
			cityNumberDaoImpl.add(city);
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}*/

	// 增加(两个参数，城市实体与省实体)
	/*@POST
	@Path("/addtwo")
	public Map<String, Boolean> addTwo(@QueryParam("city") CityNumber city, @QueryParam("pro") ProvincialNumber pro) {
		try {
			cityNumberDaoImpl.addtwo(city, pro);
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}*/

	// 删除(隐藏式)
	@DELETE
	@Path("/deletebyidhib")
	public Map<String, Boolean> deleteByIdHid(@QueryParam("cityID") int cityID) {
		try {
			if (validate.limit(cityID)) {
				cityNumberDaoImpl.deleteByIdHid(cityID);
			} else {
				return maputils.map(false);
			}
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}

	// 删除（直接删除）
	@DELETE
	@Path("/deletebyid")
	public Map<String, Boolean> deleteById(@QueryParam("cityID") int cityID) {
		try {
			if (validate.limit(cityID)) {
				cityNumberDaoImpl.deleteById(cityID);
			} else {
				return maputils.map(false);
			}
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}

	// 修改
	/*@POST
	@Path("/upodate")
	public Map<String, Boolean> postUpdate(@QueryParam("city") CityNumber city) {
		try {
			cityNumberDaoImpl.update(city);
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}*/

	// 恢复（隐藏式删除的反向）
	@POST
	@Path("/regainbydelete")
	public Map<String, Boolean> regainByDelete(@QueryParam("cityid") int cityid) {
		try {
			if (validate.limit(cityid)) {
				cityNumberDaoImpl.regainByDelete(cityid);
			} else {
				return maputils.map(false);
			}
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}
}
