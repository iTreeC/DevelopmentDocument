package com.position.service.impl;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.position.dao.PositionDao;
import com.position.pojo.CompanyPosition;
import com.position.service.PositionManage;
import com.position.utils.MapUtils;
import com.position.utils.ValidateUtils;

/**
 * 位置表管理服务端口
 * 
 * @author Fei
 *
 */
@Path("/positionmanage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Service
public class PositionManageServices implements PositionManage {
	// private static Logger logger =
	// Logger.getLogger(PositionManageServices.class);
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	PositionDao positionDaoImpl = (PositionDao) ctx.getBean("positionDaoImpl");
	ValidateUtils validate = new ValidateUtils();// 判断传入参数是否有效
	MapUtils maputils = new MapUtils();//返回值自定义map格式
	
	// 根据位置id
	@GET
	@Path("/getbyid")
	public CompanyPosition getById(@QueryParam("id") int id) {
		return positionDaoImpl.getById(id);
	}

	// 根据公司id
	@GET
	@Path("/getbyposid")
	public CompanyPosition getByPosId(@QueryParam("id") int id) {
		if (validate.limit(id))
			return positionDaoImpl.getByPosId(id);
		return null;
	}

	// 查找所有
	@GET
	@Path("/getall")
	public List<CompanyPosition> getAll() {
		return positionDaoImpl.getAll();
	}

	// 根据详尽地址查找（模糊查询）
	@GET
	@Path("/getbyaddress")
	public List<CompanyPosition> getByAddress(@QueryParam("str") String str) {
		if (validate.length(str))
			return positionDaoImpl.getByAddress(str);
		return null;
	}

	// 根据省查找
	@GET
	@Path("/getbypro")
	public List<CompanyPosition> getByPro(@QueryParam("id") int id) {
		if (validate.limit(id))
			return positionDaoImpl.getByPro(id);
		return null;
	}

	// 城市id
	@GET
	@Path("/getbycity")
	public List<CompanyPosition> getByCity(@QueryParam("id") int id) {
		if (validate.limit(id))
			return positionDaoImpl.getByCity(id);
		return null;
	}

	// 根据县查找
	@GET
	@Path("/getbycount")
	public List<CompanyPosition> getByCount(@QueryParam("id") int id) {
		if (validate.limit(id))
			return positionDaoImpl.getByCount(id);
		return null;
	}

	// 增加
	/*@PUT
	@Path("/add")
	public Map<String, Boolean> add(@QueryParam("bus") CompanyPosition Bus) {
		try {
			positionDaoImpl.add(Bus);
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}*/

	// 删除(隐藏式)
	@PUT
	@Path("/deletebyidhib")
	public Map<String, Boolean> deleteByIdHid(@QueryParam("id") int ID) {
		try {
			if (validate.limit(ID))
				positionDaoImpl.deleteByIdHid(ID);
			else {
				return maputils.map(false);
			}
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}

	// 删除（直接删除）。注意该表不能直接删除，此方法仅为测试
	@DELETE
	@Path("/deletebyid")
	public Map<String, Boolean> deleteById(@QueryParam("id") int ID) {
		try {
			if (validate.limit(ID))
				positionDaoImpl.deleteById(ID);
			else {
				return maputils.map(false);
			}
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}

	// 修改
	/*@PUT
	@Path("/update")
	public Map<String, Boolean> update(@QueryParam("bus") CompanyPosition Bus) {
		try {
			positionDaoImpl.update(Bus);
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}*/

	// 恢复（隐藏式删除的反向）
	@PUT
	@Path("/regainbydelete")
	public Map<String, Boolean> regainByDelete(@QueryParam("id") int id) {
		try {
			if (validate.limit(id))
				positionDaoImpl.regainByDelete(id);
			else {
				return maputils.map(false);
			}
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}
}
