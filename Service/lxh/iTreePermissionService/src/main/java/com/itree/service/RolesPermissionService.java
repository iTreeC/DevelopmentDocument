package com.itree.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.PermissionEngineAPI;
import com.itree.engine.api.RolesPermissionEngineAPI;

@Path("/rolepermission")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RolesPermissionService {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	RolesPermissionEngineAPI rpengine = (RolesPermissionEngineAPI) ctx.getBean("rpengine");
	PermissionEngineAPI pengine = (PermissionEngineAPI) ctx.getBean("pengine");

	@POST
	@Path("/post")
	public Boolean postPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		return rpengine.add(rid, pid);
	}

	@DELETE
	@Path("/delete")
	public Boolean deletePermission(@QueryParam("rid") int rid) {
		return rpengine.delete(rid);
	}

	@PUT
	@Path("/put")
	public Boolean putPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		return rpengine.update(rid, pid);
	}

	@GET
	@Path("/get/one/pids")
	public List<Integer> getPids(@QueryParam("rid") int rid) {
		return rpengine.getPermissionID(rid);
	}

	@GET
	@Path("/get/one/pnames")
	public List<String> getPermission(@QueryParam("rid") int rid) {
		
		List<Integer> pids = rpengine.getPermissionID(rid);
		List<String> names = new ArrayList<String>();
		String tempt;
		if (pids != null) {
			for (int i = 0; i < pids.size(); i++) {
				tempt=pengine.getNameByID(rid);
				if (tempt != null) {
					names.add(tempt);
				}
			}
		}
		return names;
	}

	@GET
	@Path("/cando")
	public Boolean cando(@QueryParam("rid") int rid, @QueryParam("pid") int pid) {
		return rpengine.cando(rid, pid);
	}

}
