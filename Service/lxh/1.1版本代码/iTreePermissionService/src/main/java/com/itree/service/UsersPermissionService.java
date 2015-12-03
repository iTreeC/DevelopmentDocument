/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.PermissionEngineAPI;
import com.itree.engine.api.UserRoleEngineAPI;
import com.itree.engine.api.UsersPermissionEngineAPI;
import com.itree.entity.Perm;

@Path("/userpermission")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersPermissionService {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	UsersPermissionEngineAPI upengine = (UsersPermissionEngineAPI) ctx.getBean("upengine");
	PermissionEngineAPI pengine = (PermissionEngineAPI) ctx.getBean("pengine");
	UserRoleEngineAPI urengine =  (UserRoleEngineAPI) ctx.getBean("urengine");

	/**
	 * ���ܣ������û�Ȩ��
	 * @param uid �û�id
	 * @param pid Ȩ��id
	 * @return true/false
	 */
	@POST
	@Path("/post")
	public Boolean postPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {
		return upengine.add(uid, pid);

	}

	/**
	 * ���ܣ�ɾ���û�Ȩ��
	 * @param uid �û�id
	 * @return true/false
	 */
	@DELETE
	@Path("/delete")
	public Boolean deletePermission(@QueryParam("uid") int uid) {
		return upengine.delete(uid);
	}

	/**
	 * ���ܣ��޸��û�Ȩ��
	 * @param uid �û�id
	 * @param pid Ȩ��id
	 * @return
	 */
	@PUT
	@Path("/put")
	public Boolean putPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {
		return upengine.update(uid, pid);
	}

	/**
	 * ���ܣ���ѯ�û�Ȩ��id
	 * @param uid
	 * @return List<Integer> id�б�
	 */
	@GET
	@Path("/get/one/pids")
	public List<Integer> getPermissionID(@QueryParam("uid") int uid) {
		return upengine.getPermissionID(uid);
	}

	/**
	 * ���ܣ���ѯ�û�Ȩ��ֵ
	 * @param uid �û�id
	 * @return List<Integer> �û�Ȩ��ֵ
	 */
	@GET
	@Path("/get/one/pnames")
	public List<String> getPermissionName(@QueryParam("uid") int uid) {

		List<Integer> pids = upengine.getPermissionID(uid);
		List<String> names = new ArrayList<String>();
		String tempt;
		if (pids != null) {
			for (int i = 0; i < pids.size(); i++) {
				tempt=pengine.getNameByID(pids.get(i));
				if (tempt != null) {
					names.add(tempt);
				}
			}
		}
		return names;
	}
	
	/**
	 * ���ܣ���ѯ�û�Ȩ���б�
	 * @param uid �û�id
	 * @return List<Perm> �û�Ȩ���б�
	 */
	@GET
	@Path("/get/one/permissions")
	public List<Perm> getPermission(@QueryParam("uid") int uid) {

		List<Integer> pids = upengine.getPermissionID(uid);
		List<Perm> perms = new ArrayList<Perm>();;
		Perm tempt;
		if (pids != null) {
			for (int i = 0; i < pids.size(); i++) {
				tempt=pengine.getOneByID(pids.get(i));
				if (tempt != null) {
					perms.add(tempt);
				}
			}
		}
		return perms;
	}

	/**
	 * ���ܣ�����idƥ���û�Ȩ��
	 * @param uid �û�id
	 * @param pid Ȩ��id
	 * @return true/false
	 */
	
	@GET
	@Path("/cando")
	public Boolean cando(@QueryParam("uid") int uid, @QueryParam("pid") int pid) {
		return upengine.cando(uid, pid);
	}

}
