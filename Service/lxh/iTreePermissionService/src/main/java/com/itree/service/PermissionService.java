/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.service;

import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.PermissionEngineAPI;

@Path("/permission")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PermissionService extends Service {

	/*ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	PermissionEngineAPI pengine = (PermissionEngineAPI) ctx.getBean("pengine");*/

	/*MapUtils maputils = new MapUtils();
	ValidateUtils validate = new ValidateUtils();*/

	/**
	 * 功能：增加一条权限信息
	 * 
	 * @param name
	 *            权限值
	 * @return Map<String, Boolean>
	 */
	@POST
	@Path("/post")
	public Map<String, Boolean> add(@QueryParam("cid") final int cid) {

		if (validate.limit(cid))
			return maputils.map(pengine.add(cid));
		return maputils.map(false);

	}

	/**
	 * 功能：通过id删除一条权限
	 * 
	 * @param id
	 *            Permission ID
	 * @return Map<String, Boolean>
	 */
	@DELETE
	@Path("/delete")
	public Map<String, Boolean> delete(@QueryParam("cid") int cid) {

		if (validate.limit(cid))
			return maputils.map(pengine.delete(cid));
		return maputils.map(false);

	}

	/**
	 * 功能：更新一条权限信息
	 * 
	 * @param id
	 *            Permission ID
	 * @param cid
	 *            ClientPermissionID
	 * @return Map<String, Boolean>
	 */
	/*
	 * @PUT
	 * 
	 * @Path("/put") public Map<String, Boolean> update(@QueryParam("id") int
	 * id,
	 * 
	 * @QueryParam("cid") int cid) {
	 * 
	 * if (validate.limit(id) && validate.limit(cid)) {
	 * 
	 * Perm permission = new Perm(); permission.setId(id);
	 * permission.setClientPermissionID(cid); return
	 * maputils.map(pengine.update(permission)); } return maputils.map(false); }
	 */

	/**
	 * 功能：查看所有权限列表
	 * 
	 * @return 权限列表
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/all") public List<Perm> getAll() {
	 * 
	 * return pengine.getAll(); }
	 */

	/**
	 * 功能：通过id查看一条权限
	 * 
	 * @param id
	 *            Permission ID
	 * @return 一条权限
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one") public Perm getOneByID(@QueryParam("id") int id) {
	 * 
	 * if (validate.limit(id)) return pengine.getOneByID(id); return null; }
	 */

	/**
	 * 功能：通过id查看一条权限值
	 * 
	 * @param id
	 *            Permission ID
	 * @return 一个权限值
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one/cid") public Map<String, Integer>
	 * getClientPermissionIDByID(@QueryParam("id") int id) {
	 * 
	 * if (validate.limit(id)) {
	 * 
	 * Perm p = pengine.getOneByID(id);
	 * 
	 * if (p != null) return maputils.map("cid",p.getClientPermissionID()); }
	 * return null; }
	 */

}
