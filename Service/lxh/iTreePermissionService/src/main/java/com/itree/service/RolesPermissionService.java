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
import com.itree.engine.api.RolesPermissionEngineAPI;
import com.itree.entity.Perm;

@Path("/rolepermission")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RolesPermissionService {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	RolesPermissionEngineAPI rpengine = (RolesPermissionEngineAPI) ctx.getBean("rpengine");
	PermissionEngineAPI pengine = (PermissionEngineAPI) ctx.getBean("pengine");

	/**
	 * 功能：增加角色权限信息
	 * @param rid 角色id
	 * @param pid 权限id
	 * @return true/false
	 */
	@POST
	@Path("/post")
	public Boolean postPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		return rpengine.add(rid, pid);
	}

	/**
	 * 功能：删除角色权限
	 * @param rid 角色id
	 * @return true/false
	 */
	@DELETE
	@Path("/delete")
	public Boolean deletePermission(@QueryParam("rid") int rid) {
		return rpengine.delete(rid);
	}

	/**
	 * 功能：更新角色信息
	 * @param rid 角色id
	 * @param pid 权限id
	 * @return true/false
	 */
	@PUT
	@Path("/put")
	public Boolean putPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		return rpengine.update(rid, pid);
	}

	/**
	 * 功能：查看某角色权限id
	 * @param rid 角色id
	 * @return 权限id列表
	 */
	@GET
	@Path("/get/one/pids")
	public List<Integer> getPids(@QueryParam("rid") int rid) {
		return rpengine.getPermissionID(rid);
	}

	/**
	 * 功能：查看某角色权限值
	 * @param rid
	 * @return 权限值列表
	 */
	@GET
	@Path("/get/one/pnames")
	public List<String> getPermissionName(@QueryParam("rid") int rid) {
		
		List<Integer> pids = rpengine.getPermissionID(rid);
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
	 * 功能：查看某角色的权限列表
	 * @param rid 角色id
	 * @return 权限列表
	 */
	@GET
	@Path("/get/one/permissions")
	public List<Perm> getPermission(@QueryParam("rid") int rid) {
		
		List<Integer> pids = rpengine.getPermissionID(rid);
		List<Perm> perm = new ArrayList<Perm>();;
		Perm tempt;
		if (pids != null) {
			for (int i = 0; i < pids.size(); i++) {
				tempt=pengine.getOneByID(pids.get(i));
				if (tempt != null) {
					perm.add(tempt);//有错！
				}
			}
		}
		return perm;
	}

	/**
	 * 功能：角色-权限匹配 
	 * @param rid 角色id 
	 * @param pid 权限id
	 * @return true/false
	 */
	@GET
	@Path("/cando")
	public Boolean cando(@QueryParam("rid") int rid, @QueryParam("pid") int pid) {
		return rpengine.cando(rid, pid);
	}

}
