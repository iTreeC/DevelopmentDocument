/**
 * @info 
 * @author ÀîÏþ»¶
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

	@POST
	@Path("/post")
	public Boolean add(@QueryParam("name") String name) {
		return pengine.add(name);
	}

	@DELETE
	@Path("/delete")
	public Boolean delete(@QueryParam("id") int id) {
		return pengine.delete(id);
	}

	@PUT
	@Path("/put")
	public Boolean update(@QueryParam("id") int id,
			@QueryParam("name") String name) {
		Perm permission = new Perm();
		permission.setId(id);
		permission.setName(name);
		return pengine.update(permission);
	}

	@GET
	@Path("/get/all")
	public List<Perm> getAll() {
		return pengine.getAll();
	}

	@GET
	@Path("/get/all/pnames")
	public List<String> getAllName() {
		return pengine.getAllName();
	}

	@GET
	@Path("/get/one")
	public Perm getOneByID(@QueryParam("id") int id) {
		return pengine.getOneByID(id);
	}

	@GET
	@Path("/get/one/pname")
	public String getPermissionByID(@QueryParam("id") int id) {
		return pengine.getOneByID(id).getName();
	}

}
