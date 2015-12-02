package com.itree.service.impl;

import com.itree.dao.RoleDao;
import com.itree.entity.TDuty;
import com.itree.service.RoleService;

public class RoleServiceimpl implements RoleService {
	private RoleDao roledao;
	
	public void add(TDuty tduty,String permission){
		roledao.add(tduty, permission);
	}

	
	
	
	public RoleDao getRoledao() {
		return roledao;
	}

	public void setRoledao(RoleDao roledao) {
		this.roledao = roledao;
	}
	
	
	
	
}
