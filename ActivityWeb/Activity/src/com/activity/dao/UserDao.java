package com.activity.dao;

import com.activity.model.User;



/**
 * ���ܣ����ݲ����ӿ���
 * @author ����� 
 * @time 2015/12/21
 *
 */
public interface UserDao {
	//��ѯ�û��Ƿ�ע��
	public Boolean logined(User user);
	//�����û���Ϣ���ֻ��ţ����ܺ�����룩
	public Boolean addUser(User user);
	//��¼����
	public Boolean userLogin(User user);
	//����¼����и�������
	public Boolean updatepwd(User user,String updatepwd);
}
