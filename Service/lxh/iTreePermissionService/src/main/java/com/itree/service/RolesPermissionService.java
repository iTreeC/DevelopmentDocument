package com.itree.service;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.itree.dao.RoleDao;
import com.itree.entity.Role;

@Service
@Path("/role")
public class RolesPermissionService {

	private static Logger logger = Logger
			.getLogger(RolesPermissionService.class);

	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	RoleDao roledao = (RoleDao) ctx.getBean("roledao");
	Role role;

	@POST
	@Path("/post")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean postPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		if (rid == 0 || pid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}
		List<Integer> pid2 = roledao.findRolePIds(rid);
		if (pid2 != null)
			pid.removeAll(pid2);

		if (pid.size() != 0) {
			roledao.add(rid, pid);
			logger.info("权限添加成功");
			return true;
		} else {
			logger.info("该权限已经存在，不能重复添加");
			return false;
		}
	}

	@DELETE
	@Path("/delete")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean deletePermission(@QueryParam("rid") int rid) {
		if (rid == 0) {
			logger.error("角色为空值！！！");
			return false;
		}
		roledao.deleteByRId(rid);
		logger.info("删除权限成功");
		return true;
	}

	@PUT
	@Path("/put")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean putPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		if (rid == 0 || pid.equals(null)) {
			logger.error("角色或权限ID不能为空值！！！");
			return false;
		}
		roledao.updatePermission(rid, pid);
		logger.info("更新权限成功");
		return true;
	}

	@GET
	@Path("/get")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Role> getPermission(@QueryParam("rid") int rid) {
		if (rid == 0) {
			logger.error("角色为空值！！！");
			return null;
		}
		List<Role> role = roledao.findByRId(rid);
		if (role != null) {
			logger.info("查找角色权限成功");
			return role;
		} else {
			logger.error("查无此角色的权限，请确认角色ID是否正确");
			return null;
		}
	}

	@GET
	@Path("/cando")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean cando(@QueryParam("rid") int rid, @QueryParam("pid") int pid) {
		Boolean flag = false;
		if (rid == 0) {
			logger.error("角色为空值！！！");
			return false;
		}
		List<Role> role = roledao.findByRId(rid);
		for (int i = 0; i < role.size(); i++) {
			if (role.get(i).getPid() == pid) {
				flag = true;
				logger.info("权限匹配成功");
				break;
			}
		}
		logger.info("权限匹配失败");
		return flag;
	}

}
