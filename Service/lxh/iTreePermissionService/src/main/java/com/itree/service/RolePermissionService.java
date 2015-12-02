/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.RolePermissionEngineAPI;

@Path("/rolepermission")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RolePermissionService extends Service {

	/*ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	RolePermissionEngineAPI rpengine = (RolePermissionEngineAPI) ctx
			.getBean("rpengine");
*/
/*	MapUtils maputils = new MapUtils();
	ValidateUtils validate = new ValidateUtils();*/

	List<Integer> cids = new ArrayList<Integer>();

	/**
	 * ���ܣ����ӽ�ɫȨ����Ϣ
	 * 
	 * @param rid
	 *            ��ɫid
	 * @param pid
	 *            Ȩ��id
	 * @return Map<String, Boolean>
	 */
	@POST
	@Path("/post")
	public Map<String, Boolean> postPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {

		if (validate.limit(rid) & validate.limit(pid))
			return maputils.map(rpengine.add(rid, pid));
		return maputils.map(false);
	}

	/**
	 * ���ܣ�ɾ����ɫȨ��
	 * 
	 * @param rid
	 *            ��ɫid
	 * @return Map<String, Boolean>
	 */
	@DELETE
	@Path("/delete")
	public Map<String, Boolean> deletePermission(@QueryParam("rid") int rid) {
		if (validate.limit(rid))
			return maputils.map(rpengine.delete(rid));
		return maputils.map(false);
	}

	/**
	 * ���ܣ����½�ɫ��Ϣ
	 * 
	 * @param rid
	 *            ��ɫid
	 * @param pid
	 *            Ȩ��id
	 * @return Map<String, Boolean>
	 */
	@PUT
	@Path("/put")
	public Map<String, Boolean> putPermission(@QueryParam("rid") int rid,
			@QueryParam("pid") List<Integer> pid) {
		if (validate.limit(rid) & validate.limit(pid))
			return maputils.map(rpengine.update(rid, pid));
		return null;
	}

	/**
	 * ���ܣ��鿴ĳ��ɫȨ��ID
	 * 
	 * @param rid
	 * @return Ȩ��ֵ�б�
	 */
	@GET
	@Path("/get/permissionid")
	public List<Map<String, Integer>> getPermissionName(
			@QueryParam("rid") int rid) {
		if (!validate.limit(rid))
			return null;

		cids = rpengine.getRolePermissionID(rid);
		if (cids != null)
			return maputils.idmap("id", cids);
		return null;
	}

	/**
	 * ���ܣ���ɫ-Ȩ��ƥ��
	 * 
	 * @param rid
	 *            ��ɫid
	 * @param pid
	 *            Ȩ��id
	 * @return Map<String, Boolean>
	 */
	@GET
	@Path("/cando")
	public Map<String, Boolean> cando(@QueryParam("rid") int rid,
			@QueryParam("pid") int pid) {
		if (validate.limit(rid) & validate.limit(pid))
			return maputils.map(rpengine.cando(rid, pid));
		return null;
	}

	/**
	 * ���ܣ��鿴ĳ��ɫ��Ȩ���б�
	 * 
	 * @param rid
	 *            ��ɫid
	 * @return Ȩ���б�
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one/permissions") public List<Perm>
	 * getPermission(@QueryParam("rid") int rid) {
	 * 
	 * List<Perm> perm = new ArrayList<Perm>(); if (validate.limit(rid)) {
	 * 
	 * List<Integer> pids = rpengine.getPermissionID(rid);
	 * 
	 * Perm tempt; if (pids != null) { for (int i = 0; i < pids.size(); i++) {
	 * tempt = pengine.getOneByID(pids.get(i)); if (tempt != null)
	 * perm.add(tempt); } } } return perm; }
	 */

	/**
	 * ���ܣ��鿴ĳ��ɫȨ��id
	 * 
	 * @param rid
	 *            ��ɫid
	 * @return Ȩ��id�б�
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one/pids") public List<Map<String, Integer>>
	 * getPids(@QueryParam("rid") int rid) {
	 * 
	 * if (validate.limit(rid)) { List<Integer> rids = new ArrayList<Integer>();
	 * if (validate.limit(rid)) rids = rpengine.getPermissionID(rid); if (rids
	 * != null) return maputils.idmap(rids); } return null;
	 * 
	 * }
	 */
}
