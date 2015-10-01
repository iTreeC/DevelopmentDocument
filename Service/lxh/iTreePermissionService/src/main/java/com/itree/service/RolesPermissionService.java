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
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		List<Integer> pid2 = roledao.findRolePIds(rid);
		if (pid2 != null)
			pid.removeAll(pid2);

		if (pid.size() != 0) {
			roledao.add(rid, pid);
			logger.info("Ȩ����ӳɹ�");
			return true;
		} else {
			logger.info("��Ȩ���Ѿ����ڣ������ظ����");
			return false;
		}
	}

	@DELETE
	@Path("/delete")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean deletePermission(@QueryParam("rid") int rid) {
		if (rid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return false;
		}
		roledao.deleteByRId(rid);
		logger.info("ɾ��Ȩ�޳ɹ�");
		return true;
	}

	@PUT
	@Path("/put")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean putPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		if (rid == 0 || pid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		roledao.updatePermission(rid, pid);
		logger.info("����Ȩ�޳ɹ�");
		return true;
	}

	@GET
	@Path("/get")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Role> getPermission(@QueryParam("rid") int rid) {
		if (rid == 0) {
			logger.error("��ɫΪ��ֵ������");
			return null;
		}
		List<Role> role = roledao.findByRId(rid);
		if (role != null) {
			logger.info("���ҽ�ɫȨ�޳ɹ�");
			return role;
		} else {
			logger.error("���޴˽�ɫ��Ȩ�ޣ���ȷ�Ͻ�ɫID�Ƿ���ȷ");
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
			logger.error("��ɫΪ��ֵ������");
			return false;
		}
		List<Role> role = roledao.findByRId(rid);
		for (int i = 0; i < role.size(); i++) {
			if (role.get(i).getPid() == pid) {
				flag = true;
				logger.info("Ȩ��ƥ��ɹ�");
				break;
			}
		}
		logger.info("Ȩ��ƥ��ʧ��");
		return flag;
	}

}
