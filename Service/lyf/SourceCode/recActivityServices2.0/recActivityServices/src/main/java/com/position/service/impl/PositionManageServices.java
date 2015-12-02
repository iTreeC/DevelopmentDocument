package com.position.service.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.position.dao.PositionDao;
import com.position.dao.impl.PositionDaoImpl;
import com.position.pojo.CompanyPosition;
import com.position.service.PositionManage;
/**
 * 位置表管理服务端口
 * @author Fei
 *
 */
@Path("/positionmanage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class PositionManageServices implements PositionManage {
	private PositionDao posDao = new PositionDaoImpl();
	
	//根据位置id
	@GET
	@Path("/getbyid")
	public CompanyPosition getById(@QueryParam("id") int id) {
		return posDao.getById(id);
	}

	//根据公司id
	@GET
	@Path("/getbyposid")
	public CompanyPosition getByPosId(@QueryParam("id") int id) {
		return posDao.getByPosId(id);
	}

	//查找所有
	@GET
	@Path("/getall")
	public List<CompanyPosition> getAll() {
		return posDao.getAll();
	}

	//根据详尽地址查找（模糊查询）
	@GET
	@Path("/getbyaddress")
	public List<CompanyPosition> getByAddress(@QueryParam("str") String str) {
		return posDao.getByAddress(str);
	}

	//根据省查找
	@GET
	@Path("/getbypro")
	public List<CompanyPosition> getByPro(@QueryParam("id") int id) {
		return posDao.getByPro(id);
	}

	//城市id
	@GET
	@Path("/getbycity")
	public List<CompanyPosition> getByCity(@QueryParam("id") int id) {
		return posDao.getByCity(id);
	}

	//根据县查找
	@GET
	@Path("/getbycount")
	public List<CompanyPosition> getByCount(@QueryParam("id") int id) {
		return posDao.getByCount(id);
	}

	//增加
	@GET
	@Path("/add")
	public void add(@QueryParam("bus") CompanyPosition Bus) {
		posDao.add(Bus);
	}

	//删除(隐藏式)
	@GET
	@Path("/deletebyidhib")
	public void deleteByIdHid(@QueryParam("id") int ID) {
		posDao.deleteByIdHid(ID);
	}

	// 删除（直接删除）。注意该表不能直接删除，此方法仅为测试
	@GET
	@Path("/deletebyid")
	public void deleteById(@QueryParam("id") int ID) {
		posDao.deleteById(ID);
	}

	//修改
	@GET
	@Path("/update")
	public void update(@QueryParam("bus") CompanyPosition Bus) {
		posDao.update(Bus);
	}
	
	
	//恢复（隐藏式删除的反向）
	@GET
	@Path("/regainbydelete")
	public void regainByDelete(@QueryParam("id") int id){
		posDao.regainByDelete(id);
	}
}
