package com.activity.service;

import com.activity.model.User;

public interface UserService {
	//��֤�û��Ƿ���ע��
	public Boolean logined(User user);
	//�û�ע�Ṧ��
	public Boolean addUser(User user);
	//�û���¼
	public Boolean userLogin(User user);
	//��½����������
	public Boolean updatepwd(User user,String updatepwd);

}
