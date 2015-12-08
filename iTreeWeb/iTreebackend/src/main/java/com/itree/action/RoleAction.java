package com.itree.action;

import java.util.List;
/**
 * 关于角色的操作。
 * @Author mxc
 * 时间：2015-12-1 20:38:34
 * 版本：1.0
 */
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.itree.entity.TDuty;
import com.itree.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class RoleAction extends ActionSupport implements ModelDriven<TDuty>, RequestAware, Preparable {

	private static final long serialVersionUID = 1L;
	HttpServletRequest re = ServletActionContext.getRequest();// 获取request作用于，接受前台ajax传来的值。

	private TDuty tduty;
	List<TDuty> d;
	//private String permission;
	int[] permission = new int[50];// 权限种类
	private RoleService roleService;

	public List<TDuty> getD() {
		return d;
	}

	public void setD(List<TDuty> d) {
		this.d = d;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public TDuty getTduty() {
		return tduty;
	}

	public void setTduty(TDuty tduty) {
		this.tduty = tduty;
	}
	public int[] getPermission() {
		return permission;
	}

	public void setPermission(int[] permission) {
		this.permission = permission;
	}

	
	
	
	// 获取角色列表
	public String list() {
		// request.put("role", roleService.getAll());
		d = roleService.getAll();

		System.out.println();
		return "list";
	}

	// 从admin-role-add页面获取数据并进行处理
	public void add() {
		System.out.println(".............");
		System.out.println(tduty);
		for(int i=0;i<permission.length;i++){
		
			System.out.println(permission[i]);
		}
		roleService.add(tduty, permission);
	}

	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public TDuty getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
