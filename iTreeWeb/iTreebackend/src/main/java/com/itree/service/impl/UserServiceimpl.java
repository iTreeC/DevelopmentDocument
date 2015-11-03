package com.itree.service.impl;

import java.util.List;

import com.itree.dao.UserDao;
import com.itree.entity.TLogin;
import com.itree.service.UserService;

public class UserServiceimpl implements UserService {
	private UserDao userdao;
	
	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	
	
	public List<TLogin> getAll(){
		List<TLogin> user = userdao.getAll();
		return user;
	}

}
