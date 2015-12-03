/**
 * @info 
 * @author ������
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
	 * ���ܣ����ӽ�ɫȨ����Ϣ
	 * @param rid ��ɫid
	 * @param pid Ȩ��id
	 * @return true/false
	 */
	@POST
	@Path("/post")
	public Boolean postPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		return rpengine.add(rid, pid);
	}

	/**
	 * ���ܣ�ɾ����ɫȨ��
	 * @param rid ��ɫid
	 * @return true/false
	 */
	@DELETE
	@Path("/delete")
	public Boolean deletePermission(@QueryParam("rid") int rid) {
		return rpengine.delete(rid);
	}

	/**
	 * ���ܣ����½�ɫ��Ϣ
	 * @param rid ��ɫid
	 * @param pid Ȩ��id
	 * @return true/false
	 */
	@PUT
	@Path("/put")
	public Boolean putPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		return rpengine.update(rid, pid);
	}

	/**
	 * ���ܣ��鿴ĳ��ɫȨ��id
	 * @param rid ��ɫid
	 * @return Ȩ��id�б�
	 */
	@GET
	@Path("/get/one/pids")
	public List<Integer> getPids(@QueryParam("rid") int rid) {
		return rpengine.getPermissionID(rid);
	}

	/**
	 * ���ܣ��鿴ĳ��ɫȨ��ֵ
	 * @param rid
	 * @return Ȩ��ֵ�б�
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
	 * ���ܣ��鿴ĳ��ɫ��Ȩ���б�
	 * @param rid ��ɫid
	 * @return Ȩ���б�
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
					perm.add(tempt);//�д�
				}
			}
		}
		return perm;
	}

	/**
	 * ���ܣ���ɫ-Ȩ��ƥ�� 
	 * @param rid ��ɫid 
	 * @param pid Ȩ��id
	 * @return true/false
	 */
	@GET
	@Path("/cando")
	public Boolean cando(@QueryParam("rid") int rid, @QueryParam("pid") int pid) {
		return rpengine.cando(rid, pid);
	}

}
