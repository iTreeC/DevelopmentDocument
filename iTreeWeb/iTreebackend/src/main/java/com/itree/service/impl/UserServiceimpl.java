package com.itree.service.impl;

import java.util.List;

import com.itree.dao.UserDao;
import com.itree.entity.AddUserInfo;
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
	

	
	public TLogin login(String name){
		TLogin loginInfo = userdao.login(name);
		return loginInfo;
	}
	public List<TLogin> getAll(){
		List<TLogin> user = userdao.getAll();
		return user;
	}
	
	public void delete(int id){
		
		userdao.delete(id);
	}

	public void stop(int id){
		userdao.stop(id);
	}
	
	public void start(int id){
		userdao.start(id);
	}
	public void save(AddUserInfo aui){
		userdao.save(aui);
	}
	public TLogin get(int id){
		return userdao.get(id);
	}
	public void doUpdate(AddUserInfo aui,int id){
		userdao.doUpdate(aui,id);
	}
}
