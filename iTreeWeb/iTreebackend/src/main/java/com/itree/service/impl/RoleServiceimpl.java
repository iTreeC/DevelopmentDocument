package com.itree.service.impl;

import java.util.List;

import com.itree.dao.RoleDao;
import com.itree.entity.TDuty;
import com.itree.service.RoleService;

public class RoleServiceimpl implements RoleService {
	private RoleDao roledao;
	
	public void add(TDuty tduty,int [] permission){
		roledao.add(tduty, permission);
	}
	public List<TDuty> getAll(){
		List<TDuty> tduty = roledao.getAll();
		return tduty;
		
	}
	public RoleDao getRoledao() {
		return roledao;
	}

	public void setRoledao(RoleDao roledao) {
		this.roledao = roledao;
	}
	public void delete(int d) {
		// TODO Auto-generated method stub
		
	}
	public TDuty get(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public void doUpdate(TDuty tduty, String permission) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
