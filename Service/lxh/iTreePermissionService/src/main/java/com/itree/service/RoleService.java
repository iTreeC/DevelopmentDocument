/**
 * @info 
 * @author ������
 * @time 2015.11.09
 */
package com.itree.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.RoleEngineAPI;
import com.itree.entity.Role;

@Path("/role")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoleService extends Service {
/*
	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	RoleEngineAPI rengine = (RoleEngineAPI) ctx.getBean("rengine");*/

/*	MapUtils maputils = new MapUtils();
	ValidateUtils validate = new ValidateUtils();*/

	/**
	 * ���ܣ�����һ����ɫ��Ϣ
	 * 
	 * @param name
	 *            ��ɫֵ
	 * @return Map<String, Boolean>
	 */
	@POST
	@Path("/post")
	public Map<String, Boolean> add(@QueryParam("name") String name) {

		if (validate.length(name))
			return maputils.map(rengine.add(name));
		return maputils.map(false);

	}

	/**
	 * ���ܣ�ͨ��idɾ��һ����ɫ
	 * 
	 * @param id
	 *            Role ID
	 * @return Map<String, Boolean>
	 */
	@DELETE
	@Path("/delete")
	public Map<String, Boolean> delete(@QueryParam("id") int id) {
		if (validate.limit(id))
			return maputils.map(rengine.delete(id));
		return maputils.map(false);
	}

	/**
	 * ���ܣ�����һ����ɫ��Ϣ
	 * 
	 * @param id
	 *            Role ID
	 * @param name
	 *            ��ɫֵ
	 * @return Map<String, Boolean>
	 */
	@PUT
	@Path("/put")
	public Map<String, Boolean> update(@QueryParam("id") int id,
			@QueryParam("name") String name) {

		if (validate.limit(id) && validate.length(name)) {

			return maputils.map(rengine.update(id, name));
		}
		return maputils.map(false);
	}

	/**
	 * ���ܣ��鿴���н�ɫ�б�
	 * 
	 * @return List of Role
	 */
	@GET
	@Path("/get/all")
	public List<Role> getAll() {

		return rengine.getAll();
	}

	/**
	 * ���ܣ�ͨ��id�鿴һ����ɫ
	 * 
	 * @param id
	 *            Role ID
	 * @return һ����ɫ
	 */
	@GET
	@Path("/get/one")
	public Role getOneByID(@QueryParam("id") int id) {
		if (validate.limit(id))
			return rengine.getOneByID(id);
		return null;
	}

	/**
	 * ���ܣ��鿴���н�ɫֵ
	 * 
	 * @return ��ɫֵ�б�
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/all/names") public List<Map<String, String>> getAllName() {
	 * 
	 * List<String> names = rengine.getAllName(); if (names != null) return
	 * maputils.map(names); return null; }
	 */

	/**
	 * ���ܣ�ͨ��id�鿴һ����ɫֵ
	 * 
	 * @param id
	 *            Role ID
	 * @return name һ����ɫֵ
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one/name") public Map<String, String>
	 * getRoleByID(@QueryParam("id") int id) { if (validate.limit(id)) { Role r
	 * = rengine.getOneByID(id); if (r != null) return maputils.map("name",
	 * r.getName()); } return null; }
	 */

}
