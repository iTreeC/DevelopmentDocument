/**
 * @info 
 * @author 李晓欢
 * @time 2015.10.28
 */
package com.itree.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.PermissionEngineAPI;
import com.itree.engine.api.RolesPermissionEngineAPI;
import com.itree.engine.api.UserRoleEngineAPI;
import com.itree.entity.Perm;

@Path("/userrole")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRoleService {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	PermissionEngineAPI pengine = (PermissionEngineAPI) ctx.getBean("pengine");
	UserRoleEngineAPI urengine = (UserRoleEngineAPI) ctx.getBean("urengine");
	RolesPermissionEngineAPI rpengine = (RolesPermissionEngineAPI) ctx
			.getBean("rpengine");

	List<Integer> rid;
	List<Integer> pid; 
	/**
	 * 功能：增加用户角色
	 * @param uid 用户id
	 * @param rid 角色id
	 * @return true/false
	 */
	@POST
	@Path("/post")
	public Boolean postPermission(@QueryParam("uid") int uid,
			@QueryParam("rid") List<Integer> rid) {
		return urengine.add(uid, rid);
	}

	/**
	 * 功能：删除用户角色
	 * @param uid 用户id
	 * @return true/false
	 */
	@DELETE
	@Path("/delete")
	public Boolean deletePermission(@QueryParam("uid") int uid) {
		return urengine.delete(uid);
	}

	/**
	 * 功能：更新用户角色
	 * @param uid 用户id
	 * @param rid 角色id
	 * @return true/false
	 */
	@PUT
	@Path("/put")
	public Boolean putPermission(@QueryParam("uid") int uid,
			@QueryParam("uid") List<Integer> rid) {
		return urengine.update(uid, rid);
	}

	/**
	 * 功能：查看某用户的权限id
	 * @param uid 用户id
	 * @return 权限id列表
	 */
	@GET
	@Path("/get/one/rids")
	public List<Integer> getRIds(@QueryParam("uid") int uid) {
		return urengine.getRIds(uid);
	}

	/**
	 * 功能：查看某用户权限列表
	 * @param uid
	 * @return 权限列表
	 */
	@GET
	@Path("/get/one/permissions")
	public List<Perm> getPermission(@QueryParam("uid") int uid) {
		rid = urengine.getRIds(uid);

		if (rid != null) {

			pid = new ArrayList<Integer>();
			List<Perm> userpermissions = new ArrayList<Perm>();

			for (int i = 0; i < rid.size(); i++) {
				pid.addAll(rpengine.getPermissionID(rid.get(i)));
			}

			// 去除重复数据
			HashSet<Integer> id = new HashSet<Integer>(pid);
			pid.clear();
			pid.addAll(id);
			Perm tempt;
			for (int i = 0; i < pid.size(); i++) {
				tempt = pengine.getOneByID(pid.get(i));
				if (tempt != null) {
					userpermissions.add(tempt);
				}
			}
			return userpermissions;
		} else {
			return null;
		}
	}

	/**
	 * 功能：查看某用户权限值
	 * @param uid 用户id
	 * @return 权限值列表
	 */
	@GET
	@Path("/get/one/pnames")
	public List<String> getPermissionName(@QueryParam("uid") int uid) {
		
		rid = urengine.getRIds(uid);

		if (rid != null) {

			pid = new ArrayList<Integer>();
			List<String> pname = new ArrayList<String>();

			for (int i = 0; i < rid.size(); i++) {
				pid.addAll(rpengine.getPermissionID(rid.get(i)));
			}

			// 去除重复数据
			HashSet<Integer> id = new HashSet<Integer>(pid);
			pid.clear();
			pid.addAll(id);
			String tempt;
			for (int i = 0; i < pid.size(); i++) {
				tempt = pengine.getNameByID(pid.get(i));
				if (tempt != null) {
					pname.add(tempt);
				}
			}
			return pname;
		} else {
			return null;
		}
	}

	/**
	 * 功能 用户-角色匹配
	 * @param uid 用户id
	 * @param rid 角色id
	 * @return true/false
	 */
	@GET
	@Path("/cando")
	public Boolean cando(@QueryParam("uid")int uid, @QueryParam("rid")int rid) {
		return urengine.cando(uid, rid);
	}

}
