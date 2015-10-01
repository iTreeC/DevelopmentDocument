package com.itree.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.dao.UserDao;
import com.itree.entity.User;

@Path("/user")
public class UsersPermissionService {

	private static Logger logger = Logger
			.getLogger(UsersPermissionService.class);

	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	UserDao userdao = (UserDao) ctx.getBean("userdao");

	@POST
	@Path("/post")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean postPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {

		if (uid == 0 || pid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}
		List<Integer> pid2 = userdao.findUserPIds(uid);
		if (pid2 != null)
			pid.removeAll(pid2);

		if (pid.size() != 0) {
			userdao.add(uid, pid);
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
	public Boolean deletePermission(@QueryParam("uid") int uid) {
		if (uid == 0){
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		logger.info("ɾ��Ȩ�޳ɹ�");
		userdao.deleteByUId(uid);
		return true;
	}

	@PUT
	@Path("/put")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean putPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {
		if (uid == 0 || pid.equals(null)) {
			logger.error("��ɫ��Ȩ��ID����Ϊ��ֵ������");
			return false;
		}

		HashSet<Integer> pid2 = new HashSet<Integer>(pid);
		pid.clear();
		pid.addAll(pid2);

		userdao.updatePermission(uid, pid);
		logger.info("����Ȩ�޳ɹ�");
		return true;
	}

	@GET
	@Path("/get")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<User> getPermission(@QueryParam("uid") int uid) {
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return null;
		}
		userdao.findUserPIds(uid);
		userdao.findAll();
		List<User> user = userdao.findByUId(uid);

		if (user != null) {
			logger.info("�����û�Ȩ�޳ɹ�");
			return user;
		} else {
			logger.error("���޴��û�Ȩ�ޣ���ȷ���û�ID�Ƿ���ȷ");
			return null;
		}
	}

	@GET
	@Path("/cando")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Boolean cando(@QueryParam("uid") int uid, @QueryParam("pid") int pid) {
		List<User> user = new ArrayList<User>();
		Boolean flag = false;
		if (uid == 0) {
			logger.error("�û���Ϊ�գ�����");
			return flag;
		}
		user = userdao.findByUId(uid);
		for (int i = 0; i < user.size(); i++) {
			if (user.get(i).getPid() == pid) {
				flag = true;
				logger.info("Ȩ��ƥ��ɹ�");
				return flag;
			}
		}
		logger.info("Ȩ��ƥ��ʧ��");
		return flag;
	}

}
