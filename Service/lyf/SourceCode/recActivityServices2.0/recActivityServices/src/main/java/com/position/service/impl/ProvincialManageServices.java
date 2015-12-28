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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.position.dao.CityNumberDao;
import com.position.dao.ProNumberDao;
import com.position.dao.impl.ProNumberDaoImpl;
import com.position.pojo.ProvincialNumber;
import com.position.service.ProvincialManage;
import com.position.utils.MapUtils;
import com.position.utils.ValidateUtils;
/**
 * 省信息管理的服务端口
 * 
 * @author Fei
 *
 */
@Path("/provincialmanage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Service
public class ProvincialManageServices implements ProvincialManage {
	//private static Logger logger = Logger.getLogger(ProvincialManageServices.class);
	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	ProNumberDao proNumberDaoImpl = (ProNumberDao) ctx.getBean("proNumberDaoImpl");
			
	ValidateUtils validate = new ValidateUtils();// 判断传入参数是否有效
	MapUtils maputils = new MapUtils();//返回值自定义map格式
	// 根据id
	@GET
	@Path("/getbyid")
	public ProvincialNumber getById(@QueryParam("id") int id) {
		if (validate.limit(id))
			return proNumberDaoImpl.getById(id);
		return null;
	}

	// 根据名
	@GET
	@Path("/getbyname")
	public ProvincialNumber getByName(@QueryParam("name") String name) {
		if (validate.length(name))
			return proNumberDaoImpl.getByName(name);
		return null;
	}

	// 查找所有
	@GET
	@Path("/getall")
	public List<ProvincialNumber> getAll() {
		return proNumberDaoImpl.getAll();
	}

	// 增加
	/*@PUT
	@Path("/add")
	public Map<String, Boolean> add(@QueryParam("pro") ProvincialNumber Pro) {
		try {
			proNumberDaoImpl.add(Pro);
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
			if (validate.limit(ID)) {
				proNumberDaoImpl.deleteByIdHid(ID);
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
	public Map<String, Boolean> deleteById(@QueryParam("id") int ID) {
		try {
			if (validate.limit(ID)) {
				proNumberDaoImpl.deleteById(ID);
			} else {
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
	public Map<String, Boolean> update(@QueryParam("pro") ProvincialNumber Pro) {
		try {
			proNumberDaoImpl.update(Pro);
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}*/

	// 恢复（隐藏式删除的反向）
	@PUT
	@Path("/regainbydelete")
	public Map<String, Boolean> regainByDelete(@QueryParam("id")  int id) {
		try {
			if (validate.limit(id)) {
				proNumberDaoImpl.regainByDelete(id);
			} else {
				return maputils.map(false);
			}
		} catch (Exception e) {
			return maputils.map(false);
		}
		return maputils.map(true);
	}
}
