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

import com.itree.engine.api.UserRoleEngineAPI;
import com.itree.entity.Role;

@Path("/userrole")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRoleService extends Service {

	
	/*  ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	  
	  UserRoleEngineAPI urengine = (UserRoleEngineAPI) ctx.getBean("urengine");*/
	  
	/*  MapUtils maputils = new MapUtils(); ValidateUtils validate = new
	  ValidateUtils();*/
	 

	List<Integer> uids = new ArrayList<Integer>();
	List<Integer> cids = new ArrayList<Integer>();

	@POST
	@Path("/post")
	public Map<String, Boolean> postPermission(@QueryParam("uid") int uid,
			@QueryParam("rid") List<Integer> rid) {

		if (validate.limit(rid) & validate.limit(uid))
			return maputils.map(urengine.add(uid, rid));
		return maputils.map(false);
	}

	@DELETE
	@Path("/delete")
	public Map<String, Boolean> deletePermission(@QueryParam("uid") int uid) {

		if (validate.limit(uid))
			return maputils.map(urengine.delete(uid));
		return maputils.map(false);

	}

	@PUT
	@Path("/put")
	public Map<String, Boolean> putPermission(@QueryParam("uid") int uid,
			@QueryParam("rid") List<Integer> rid) {

		if (validate.limit(rid) & validate.limit(uid))
			return maputils.map(urengine.update(uid, rid));
		return maputils.map(false);

	}

	@GET
	@Path("/get/roleid")
	public List<Map<String, Integer>> getRIds(@QueryParam("uid") int uid) {

		if (validate.limit(uid))
			uids = urengine.getRIds(uid);

		if (uids != null)
			return maputils.idmap("id", uids);

		return null;

	}

	/**
	 * ���ܣ��鿴ĳ�û�Ȩ��ID
	 * 
	 * @param uid
	 *            User ID
	 * @return Map<String, Integer>
	 */
	@GET
	@Path("/get/permissionid")
	public List<Map<String, Integer>> getPermissionIDS(
			@QueryParam("uid") int uid) {

		if (validate.limit(uid))
			cids = urengine.getUserRolePermissionID(uid);

		if (cids != null)
			return maputils.idmap("cid", cids);

		return null;
	}

	/**
	 * ���ܣ��鿴ĳ�û���ɫ
	 * 
	 * @param uid
	 *            User ID
	 * @return Map<String, Integer>
	 */
	@GET
	@Path("/get/role")
	public List<Role> getRole(@QueryParam("uid") int uid) {
		if (validate.limit(uid))
			return null;
		return urengine.getUserRole(uid);

	}

	/**
	 * ���� �û�-��ɫƥ��
	 * 
	 * @param uid
	 *            User ID
	 * @param rid
	 *            RoleID
	 * @return Map<String, Boolean>
	 */
	@GET
	@Path("/cando/role")
	public Map<String, Boolean> candoRole(@QueryParam("uid") int uid,
			@QueryParam("rid") int rid) {

		if (validate.limit(rid) & validate.limit(uid))
			return maputils.map(urengine.cando(uid, rid));
		return null;
	}

	/**
	 * ���� �û�-Ȩ��ƥ��
	 * 
	 * @param uid
	 *            User ID
	 * @param rid
	 *            RoleID
	 * @return Map<String, Boolean>
	 */
	@GET
	@Path("/cando/permission")
	public Map<String, Boolean> candoPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") int pid) {

		if (validate.limit(pid) & validate.limit(uid))
			return maputils.map(urengine.cando(uid, pid));
		return null;
	}

	/**
	 * ���ܣ��鿴ĳ�û�Ȩ���б�
	 * 
	 * @param uid
	 *            User ID
	 * @return Map<String, Boolean>
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one/permission/cid") public List<Perm>
	 * getPermission(@QueryParam("uid") int uid) {
	 * 
	 * List<Perm> userpermissions = new ArrayList<Perm>();
	 * 
	 * if (validate.limit(uid)) {
	 * 
	 * rid = urengine.getRIds(uid);
	 * 
	 * if (rid != null) {
	 * 
	 * List<Integer> pid = new ArrayList<Integer>();
	 * 
	 * for (int i = 0; i < rid.size(); i++)
	 * pid.addAll(rpengine.getPermissionID(rid.get(i)));
	 * 
	 * // ȥ���ظ����� HashSet<Integer> id = new HashSet<Integer>(pid); pid.clear();
	 * pid.addAll(id);
	 * 
	 * // ��ѯȨ�� Perm tempt; for (int i = 0; i < pid.size(); i++) { tempt =
	 * pengine.getOneByID(pid.get(i)); if (tempt != null)
	 * userpermissions.add(tempt); } } } return userpermissions;
	 * 
	 * }
	 */

}
