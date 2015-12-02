/**
 * @info 
 * @author ������
 * @time 2015.10.28
 */
package com.itree.service;

import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itree.engine.api.UserEngineAPI;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService extends Service {

	/*ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	UserEngineAPI uengine = (UserEngineAPI) ctx.getBean("uengine");*/

	/*
	 * MapUtils maputils = new MapUtils(); ValidateUtils validate = new
	 * ValidateUtils();
	 */

	/**
	 * ���ܣ�����һ���û���Ϣ
	 * 
	 * @param name
	 *            �û���Client��ID
	 * @return Map<String, Boolean>
	 */
	@POST
	@Path("/post")
	public Map<String, Boolean> add(@QueryParam("cid") int cid) {

		if (validate.limit(cid))
			return maputils.map(uengine.add(cid));
		return maputils.map(false);

	}

	/**
	 * ���ܣ�ͨ��idɾ��һ���û�
	 * 
	 * @param id
	 *            User ID
	 * @return Map<String, Boolean>
	 */
	@DELETE
	@Path("/delete")
	public Map<String, Boolean> delete(@QueryParam("cid") int cid) {

		if (validate.limit(cid))
			return maputils.map(uengine.delete(cid));
		return maputils.map(false);

	}

	/**
	 * ���ܣ�����һ���û���Ϣ
	 * 
	 * @param id
	 *            User ID
	 * @param cid
	 *            ClientUserID
	 * @return Map<String, Boolean>
	 */
	/*
	 * @PUT
	 * 
	 * @Path("/put") public Map<String, Boolean> update(@QueryParam("id") int
	 * id,
	 * 
	 * @QueryParam("cid") int cid) {
	 * 
	 * if (validate.limit(id) && validate.limit(cid)) {
	 * 
	 * User User = new User(); User.setId(id); User.setClientUserID(cid); return
	 * maputils.map(uengine.update(User)); } return maputils.map(false); }
	 */

	/**
	 * ���ܣ��鿴�����û��б�
	 * 
	 * @return �û��б�
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/all") public List<User> getAll() {
	 * 
	 * return uengine.getAll(); }
	 */

	/**
	 * ���ܣ�ͨ��id�鿴һ���û�
	 * 
	 * @param id
	 *            User ID
	 * @return һ���û�
	 */
	/*
	 * @GET
	 * 
	 * @Path("/get/one") public User getOneByID(@QueryParam("cid") int cid) {
	 * 
	 * if (validate.limit(cid)) return uengine.getOneByClientUserID(cid); return
	 * null; }
	 */

}
