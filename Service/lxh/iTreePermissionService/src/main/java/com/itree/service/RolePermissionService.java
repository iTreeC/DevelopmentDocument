/**
 * @info 
 * @author 李晓欢
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

import com.itree.engine.api.RolePermissionEngineAPI;

@Path("/rolepermission")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RolePermissionService extends Service {

	/*ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	RolePermissionEngineAPI rpengine = (RolePermissionEngineAPI) ctx
			.getBean("rpengine");
*/
/*	MapUtils maputils = new MapUtils();
	ValidateUtils validate = new ValidateUtils();*/

	List<Integer> cids = new ArrayList<Integer>();

	/**
	 * 功能：增加角色权限信息
	 * 
	 * @param rid
	 *            角色id
	 * @param pid
	 *            权限id
	 * @return Map<String, Boolean>
	 */
	@POST
	@Path("/post")
	public Map<String, Boolean> postPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {

		if (validate.limit(rid) & validate.limit(pid))
			return maputils.map(rpengine.add(rid, pid));
		return maputils.map(false);
	}

	/**
	 * 功能：删除角色权限
	 * 
	 * @param rid
	 *            角色id
	 * @return Map<String, Boolean>
	 */
	@DELETE
	@Path("/delete")
	public Map<String, Boolean> deletePermission(@QueryParam("rid") int rid) {
		if (validate.limit(rid))
			return maputils.map(rpengine.delete(rid));
		return maputils.map(false);
	}

	/**
	 * 功能：更新角色信息
	 * 
	 * @param rid
	 *            角色id
	 * @param pid
	 *            权限id
	 * @return Map<String, Boolean>
	 */
	@PUT
	@Path("/put")
	public Map<String, Boolean> putPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		if (validate.limit(rid) & validate.limit(pid))
			return maputils.map(rpengine.update(rid, pid));
		return null;
	}

	/**
	 * 功能：查看某角色权限ID
	 * 
	 * @param rid
	 * @return 权限值列表
	 */
	@GET
	@Path("/get/permissionid")
	public List<Map<String, Integer>> getPermissionName(
			@QueryParam("rid") int rid) {
		if (!validate.limit(rid))
			return null;

		cids = rpengine.getRolePermissionID(rid);
		if (cids != null)
			return maputils.idmap("id", cids);
		return null;
	}

	/**
	 * 功能：角色-权限匹配
	 * 
	 * @param rid
	 *            角色id
	 * @param pid
	 *            权限id
	 * @return Map<String, Boolean>
	 */
	@GET
	@Path("/cando")
	public Map<String, Boolean> cando(@QueryParam("rid") int rid,
			@QueryParam("pid") int pid) {
		if (validate.limit(rid) & validate.limit(pid))
			return maputils.map(rpengine.cando(rid, pid));
		return null;
	}

	/**
	 * 功能：查看某角色的权限列表
	 * 
	 * @param rid
	 *            角色id
	 * @return 权限列表
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one/permissions") public List<Perm>
	 * getPermission(@QueryParam("rid") int rid) {
	 * 
	 * List<Perm> perm = new ArrayList<Perm>(); if (validate.limit(rid)) {
	 * 
	 * List<Integer> pids = rpengine.getPermissionID(rid);
	 * 
	 * Perm tempt; if (pids != null) { for (int i = 0; i < pids.size(); i++) {
	 * tempt = pengine.getOneByID(pids.get(i)); if (tempt != null)
	 * perm.add(tempt); } } } return perm; }
	 */

	/**
	 * 功能：查看某角色权限id
	 * 
	 * @param rid
	 *            角色id
	 * @return 权限id列表
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one/pids") public List<Map<String, Integer>>
	 * getPids(@QueryParam("rid") int rid) {
	 * 
	 * if (validate.limit(rid)) { List<Integer> rids = new ArrayList<Integer>();
	 * if (validate.limit(rid)) rids = rpengine.getPermissionID(rid); if (rids
	 * != null) return maputils.idmap(rids); } return null;
	 * 
	 * }
	 */
}
