/**
 * @info 
 * @author ÀîÏþ»¶
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

@Path("/userpermions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersPermissionService {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	UsersPermissionEngineAPI upengine = (UsersPermissionEngineAPI) ctx.getBean("upengine");
	PermissionEngineAPI pengine = (PermissionEngineAPI) ctx.getBean("pengine");
	UserRoleEngineAPI urengine =  (UserRoleEngineAPI) ctx.getBean("urengine");

	@POST
	@Path("/post")
	public Boolean postPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {
		return upengine.add(uid, pid);

	}

	@DELETE
	@Path("/delete")
	public Boolean deletePermission(@QueryParam("uid") int uid) {
		return upengine.delete(uid);
	}

	@PUT
	@Path("/put")
	public Boolean putPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {
		return upengine.update(uid, pid);
	}

	@GET
	@Path("/get/one/pids")
	public List<Integer> getPermissionID(@QueryParam("uid") int uid) {
		return upengine.getPermissionID(uid);
	}

	@GET
	@Path("/get/one/pnames")
	public List<String> getPermission(@QueryParam("uid") int uid) {

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

	@GET
	@Path("/cando")
	public Boolean cando(@QueryParam("uid") int uid, @QueryParam("pid") int pid) {
		return upengine.cando(uid, pid);
	}

}
