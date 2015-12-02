/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.UserPermissionEngineAPI;

@Path("/userpermission")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserPermissionService extends Service {

	/*
	  ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	  
	  UserPermissionEngineAPI upengine = (UserPermissionEngineAPI) ctx
	  .getBean("upengine");*/
	  
	 /* MapUtils maputils = new MapUtils(); ValidateUtils validate = new
	  ValidateUtils();*/
	 

	private List<Integer> cids = new ArrayList<Integer>();

	/**
	 * ���ܣ������û�Ȩ��
	 * 
	 * @param uid
	 *            �û�id
	 * @param pid
	 *            Ȩ��id
	 * @return Map<String, Boolean>
	 */
	@POST
	@Path("/post")
	public Map<String, Boolean> postPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {

		if (validate.limit(pid) & validate.limit(pid))
			return maputils.map(upengine.add(uid, pid));
		return maputils.map(false);
	}

	/**
	 * ���ܣ�ɾ���û�Ȩ��
	 * 
	 * @param uid
	 *            User ID
	 * @return Map<String, Boolean>
	 */
	@DELETE
	@Path("/delete")
	public Map<String, Boolean> deletePermission(@QueryParam("uid") int uid) {

		if (validate.limit(uid))
			return maputils.map(upengine.delete(uid));
		return maputils.map(false);
	}

	/**
	 * ���ܣ��޸��û�Ȩ��
	 * 
	 * @param uid
	 *            User ID
	 * @param pid
	 *            Ȩ��id
	 * @return Map<String, Boolean>
	 */
	@PUT
	@Path("/put")
	public Map<String, Boolean> putPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {

		if (validate.limit(uid) & validate.limit(pid))
			return maputils.map(upengine.update(uid, pid));
		return maputils.map(false);
	}

	/**
	 * ���ܣ���ѯ�û�Ȩ��ֵ
	 * 
	 * @param uid
	 *            User ID
	 * @return List<Integer> �û�Ȩ��ֵ
	 */
	@GET
	@Path("/get/permissionid")
	public List<Map<String, Integer>> getPermissionName(
			@QueryParam("uid") int uid) {

		if (!validate.limit(uid))
			return null;

		cids = upengine.getUserPermissionID(uid);

		if (cids != null)
			return maputils.idmap("id", cids);
		return null;
	}

	/**
	 * ���ܣ�����idƥ���û�Ȩ��
	 * 
	 * @param uid
	 *            User ID
	 * @param pid
	 *            Permission ID
	 * @return Map<String, Boolean>
	 */

	@GET
	@Path("/cando")
	public Map<String, Boolean> cando(@QueryParam("uid") int uid,
			@QueryParam("pid") int pid) {

		if (validate.limit(uid) & validate.limit(pid))
			return maputils.map(upengine.cando(uid, pid));
		return null;
	}

}
