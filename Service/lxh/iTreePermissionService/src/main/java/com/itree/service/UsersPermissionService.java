/**
 * @info 
 * @author 李晓欢
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
	 * 功能：增加用户权限
	 * @param uid 用户id
	 * @param pid 权限id
	 * @return true/false
	 */
	@POST
	@Path("/post")
	public Boolean postPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {
		return upengine.add(uid, pid);

	}

	/**
	 * 功能：删除用户权限
	 * @param uid 用户id
	 * @return true/false
	 */
	@DELETE
	@Path("/delete")
	public Boolean deletePermission(@QueryParam("uid") int uid) {
		return upengine.delete(uid);
	}

	/**
	 * 功能：修改用户权限
	 * @param uid 用户id
	 * @param pid 权限id
	 * @return
	 */
	@PUT
	@Path("/put")
	public Boolean putPermission(@QueryParam("uid") int uid,
			@QueryParam("pid") List<Integer> pid) {
		return upengine.update(uid, pid);
	}

	/**
	 * 功能：查询用户权限id
	 * @param uid
	 * @return List<Integer> id列表
	 */
	@GET
	@Path("/get/one/pids")
	public List<Integer> getPermissionID(@QueryParam("uid") int uid) {
		return upengine.getPermissionID(uid);
	}

	/**
	 * 功能：查询用户权限值
	 * @param uid 用户id
	 * @return List<Integer> 用户权限值
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
	 * 功能：查询用户权限列表
	 * @param uid 用户id
	 * @return List<Perm> 用户权限列表
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
	 * 功能：根据id匹配用户权限
	 * @param uid 用户id
	 * @param pid 权限id
	 * @return true/false
	 */
	
	@GET
	@Path("/cando")
	public Boolean cando(@QueryParam("uid") int uid, @QueryParam("pid") int pid) {
		return upengine.cando(uid, pid);
	}

}
