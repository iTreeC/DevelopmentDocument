package com.position.service.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.position.dao.ProNumberDao;
import com.position.dao.impl.ProNumberDaoImpl;
import com.position.pojo.ProvincialNumber;
import com.position.service.ProvincialManage;
/**
 * 省信息管理的服务端口
 * 
 * @author Fei
 *
 */
@Path("/provincialmanage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ProvincialManageServices implements ProvincialManage {
	private ProNumberDao proDao = new ProNumberDaoImpl();
	
	// 根据id
	@GET
	@Path("/getbyid")
	public ProvincialNumber getById(@QueryParam("id") int id) {
		return proDao.getById(id);
	}

	// 根据名
	@GET
	@Path("/getbyname")
	public ProvincialNumber getByName(@QueryParam("name") String name) {
		return proDao.getByName(name);
	}

	// 查找所有
	@GET
	@Path("/getall")
	public List<ProvincialNumber> getAll() {
		return proDao.getAll();
	}

	// 增加
	@GET
	@Path("/add")
	public void add(@QueryParam("pro") ProvincialNumber Pro) {
		proDao.add(Pro);
	}

	// 删除(隐藏式)
	@GET
	@Path("/deletebyidhib")
	public void deleteByIdHid(@QueryParam("id") int ID) {
		proDao.deleteByIdHid(ID);
	}

	// 删除（直接删除）
	@GET
	@Path("/deletebyid")
	public void deleteById(@QueryParam("id") int ID) {
		proDao.deleteById(ID);
	}

	// 修改
	@GET
	@Path("/update")
	public void update(@QueryParam("pro") ProvincialNumber Pro) {
		proDao.update(Pro);
	}

	// 恢复（隐藏式删除的反向）
	@GET
	@Path("/regainbydelete")
	public void regainByDelete(@QueryParam("id")  int id) {
		proDao.regainByDelete(id);
	}
}
