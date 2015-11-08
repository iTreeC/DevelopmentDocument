/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.service;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.PermissionEngineAPI;
import com.itree.entity.Perm;

@Path("/permission")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PermissionService {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	PermissionEngineAPI pengine = (PermissionEngineAPI) ctx.getBean("pengine");

	/**
	 * 功能：增加一条权限信息
	 * 
	 * @param name
	 *            权限值
	 * @return true/false
	 */
	@POST
	@Path("/post")
	public Boolean add(@QueryParam("name") String name) {
		return pengine.add(name);
	}

	/**
	 * 功能：通过id删除一条权限
	 * 
	 * @param id
	 *            权限id
	 * @return true/false
	 */
	@DELETE
	@Path("/delete")
	public Boolean delete(@QueryParam("id") int id) {
		return pengine.delete(id);
	}

	/**
	 * 功能：更新一条权限信息
	 * 
	 * @param id
	 *            权限id
	 * @param name
	 *            权限值
	 * @return true/false
	 */
	@PUT
	@Path("/put")
	public Boolean update(@QueryParam("id") int id,
			@QueryParam("name") String name) {
		Perm permission = new Perm();
		permission.setId(id);
		permission.setName(name);
		return pengine.update(permission);
	}

	/**
	 * 功能：查看所有权限列表
	 * 
	 * @return 权限列表
	 */
	@GET
	@Path("/get/all")
	public List<Perm> getAll() {
		return pengine.getAll();
	}

	/**
	 * 功能：查看所有权限值
	 * 
	 * @return 权限值列表
	 */
	@GET
	@Path("/get/all/pnames")
	public List<String> getAllName() {
		return pengine.getAllName();
	}

	/**
	 * 功能：通过id查看一条权限
	 * 
	 * @param id
	 *            权限id
	 * @return 一条权限
	 */
	@GET
	@Path("/get/one")
	public Perm getOneByID(@QueryParam("id") int id) {
		return pengine.getOneByID(id);
	}

	/**
	 * 功能：通过id查看一条权限值
	 * 
	 * @param id
	 *            权限id
	 * @return 一个权限值
	 */
	@GET
	@Path("/get/one/pname")
	public String getPermissionByID(@QueryParam("id") int id) {
		return pengine.getOneByID(id).getName();
	}

}
