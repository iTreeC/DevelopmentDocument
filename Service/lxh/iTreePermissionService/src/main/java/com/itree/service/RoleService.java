/**
 * @info 
 * @author 李晓欢
 * @time 2015.11.09
 */
package com.itree.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.RoleEngineAPI;
import com.itree.entity.Role;

@Path("/role")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoleService extends Service {
/*
	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	RoleEngineAPI rengine = (RoleEngineAPI) ctx.getBean("rengine");*/

/*	MapUtils maputils = new MapUtils();
	ValidateUtils validate = new ValidateUtils();*/

	/**
	 * 功能：增加一条角色信息
	 * 
	 * @param name
	 *            角色值
	 * @return Map<String, Boolean>
	 */
	@POST
	@Path("/post")
	public Map<String, Boolean> add(@QueryParam("name") String name) {

		if (validate.length(name))
			return maputils.map(rengine.add(name));
		return maputils.map(false);

	}

	/**
	 * 功能：通过id删除一条角色
	 * 
	 * @param id
	 *            Role ID
	 * @return Map<String, Boolean>
	 */
	@DELETE
	@Path("/delete")
	public Map<String, Boolean> delete(@QueryParam("id") int id) {
		if (validate.limit(id))
			return maputils.map(rengine.delete(id));
		return maputils.map(false);
	}

	/**
	 * 功能：更新一条角色信息
	 * 
	 * @param id
	 *            Role ID
	 * @param name
	 *            角色值
	 * @return Map<String, Boolean>
	 */
	@PUT
	@Path("/put")
	public Map<String, Boolean> update(@QueryParam("id") int id,
			@QueryParam("name") String name) {

		if (validate.limit(id) && validate.length(name)) {

			return maputils.map(rengine.update(id, name));
		}
		return maputils.map(false);
	}

	/**
	 * 功能：查看所有角色列表
	 * 
	 * @return List of Role
	 */
	@GET
	@Path("/get/all")
	public List<Role> getAll() {

		return rengine.getAll();
	}

	/**
	 * 功能：通过id查看一条角色
	 * 
	 * @param id
	 *            Role ID
	 * @return 一条角色
	 */
	@GET
	@Path("/get/one")
	public Role getOneByID(@QueryParam("id") int id) {
		if (validate.limit(id))
			return rengine.getOneByID(id);
		return null;
	}

	/**
	 * 功能：查看所有角色值
	 * 
	 * @return 角色值列表
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/all/names") public List<Map<String, String>> getAllName() {
	 * 
	 * List<String> names = rengine.getAllName(); if (names != null) return
	 * maputils.map(names); return null; }
	 */

	/**
	 * 功能：通过id查看一条角色值
	 * 
	 * @param id
	 *            Role ID
	 * @return name 一个角色值
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one/name") public Map<String, String>
	 * getRoleByID(@QueryParam("id") int id) { if (validate.limit(id)) { Role r
	 * = rengine.getOneByID(id); if (r != null) return maputils.map("name",
	 * r.getName()); } return null; }
	 */

}
