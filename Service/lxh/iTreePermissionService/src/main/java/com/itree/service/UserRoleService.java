/**
 * @info 
 * @author ������
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
	 * ���ܣ������û���ɫ
	 * @param uid �û�id
	 * @param rid ��ɫid
	 * @return true/false
	 */
	@POST
	@Path("/post")
	public Boolean postPermission(@QueryParam("uid") int uid,
			@QueryParam("rid") List<Integer> rid) {
		return urengine.add(uid, rid);
	}

	/**
	 * ���ܣ�ɾ���û���ɫ
	 * @param uid �û�id
	 * @return true/false
	 */
	@DELETE
	@Path("/delete")
	public Boolean deletePermission(@QueryParam("uid") int uid) {
		return urengine.delete(uid);
	}

	/**
	 * ���ܣ������û���ɫ
	 * @param uid �û�id
	 * @param rid ��ɫid
	 * @return true/false
	 */
	@PUT
	@Path("/put")
	public Boolean putPermission(@QueryParam("uid") int uid,
			@QueryParam("uid") List<Integer> rid) {
		return urengine.update(uid, rid);
	}

	/**
	 * ���ܣ��鿴ĳ�û���Ȩ��id
	 * @param uid �û�id
	 * @return Ȩ��id�б�
	 */
	@GET
	@Path("/get/one/rids")
	public List<Integer> getRIds(@QueryParam("uid") int uid) {
		return urengine.getRIds(uid);
	}

	/**
	 * ���ܣ��鿴ĳ�û�Ȩ���б�
	 * @param uid
	 * @return Ȩ���б�
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

			// ȥ���ظ�����
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
	 * ���ܣ��鿴ĳ�û�Ȩ��ֵ
	 * @param uid �û�id
	 * @return Ȩ��ֵ�б�
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

			// ȥ���ظ�����
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
	 * ���� �û�-��ɫƥ��
	 * @param uid �û�id
	 * @param rid ��ɫid
	 * @return true/false
	 */
	@GET
	@Path("/cando")
	public Boolean cando(@QueryParam("uid")int uid, @QueryParam("rid")int rid) {
		return urengine.cando(uid, rid);
	}

}
