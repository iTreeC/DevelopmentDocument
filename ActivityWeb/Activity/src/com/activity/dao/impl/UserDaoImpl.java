package com.activity.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.activity.actions.MD5;
import com.activity.dao.UserDao;
import com.activity.model.User;

@Component("userDao")
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;
	private Session session=null;
	
	MD5 md5 = new MD5();
	/**
	 * ע���û���������������û��Ƿ�ע��������ж�
	 * @return ����false��˵��δ��ע��ɹ�������true��˵��ע��ɹ�
	 */
	@Override
	public Boolean addUser(User user) {
		// TODO Auto-generated method stub
		Boolean adduserreturn = null;
		if (logined(user)==true) {
			System.out.println("�û���ע�ᣬ���ܽ��ж���ע��");
			adduserreturn=false;
		}else{
			System.out.println("�û�δע�ᣬ����ע��");
			sessionFactory.openSession().save(user);
			adduserreturn = true;
		}
		return adduserreturn;
				
	}
	/**
	 * ���ܣ� ����û��Ƿ���ע��
	 * @param user
	 * @return ����falseʱ��˵��Ϊ���ֻ���δע�ᣬ����trueʱ��˵�����ֻ�����ע��
	 */
	public Boolean logined(User user){
		Boolean logined = null;
		try {
			session = sessionFactory.getCurrentSession();
			String hql = "select u from User u where u.name like ?";
			Query query =session.createQuery(hql);
			query.setParameter(0, user.getName());
			User user2 = (User)query.uniqueResult();
			
			//��ѯ�����Ϊ�գ�˵�����ֻ�����ע��
			if(user2.equals(null)==false){
				System.out.println("�û���ע��");
				logined=true;
			}
		} catch (Exception e) {
			//���״�ʱ����δ�鵽���ֻ��ţ�˵��δע��
			System.out.println("�û�δע��");
			logined=false;
		}
		return logined; 
	}
	
	/**
	 * �û���¼����
	 * @return ����false˵����¼ʧ�ܣ�����true˵����½�ɹ�
	 */
	@Override
	public Boolean userLogin(User user) {
		// TODO Auto-generated method stub
		Boolean userloginreturn = null;
		if(logined(user)==false){
			System.out.println("�û�δע��");
			userloginreturn = false;
		}else{
			System.out.println("�û���ע��");
			//��ѯ�û������� 
			session = sessionFactory.getCurrentSession();
			String hql = "select u from User u where u.name like ?";
			Query query =session.createQuery(hql);
			query.setParameter(0, user.getName());
			User user2 = (User)query.uniqueResult();
			
			if(user2.getPasswd().equals(user.getPasswd())){
				System.out.println("����������ȷ");
				userloginreturn = true;
			}else {
				System.out.println("�������벻��ȷ");
				userloginreturn = false;
			}
		}
		return userloginreturn;
	}
	/**
	 * ���ܣ��ڵ�¼�Ժ���и�������
	 * @param ����ǰ��¼�е��û����������� ���Ѿ������Ҫ���ĵ�����
	 * @return �����ĳɹ���ʱ�򷵻�true �����Ĵ��󷵻�false
	 */
	@Override
	public Boolean updatepwd(User user,String updatepwd) {
		// TODO Auto-generated method stub
		Boolean updatemsg = null;
		//��ѯ�û������� 
		session = sessionFactory.getCurrentSession();
		String hql = "select u from User u where u.name like ?";
		Query query =session.createQuery(hql);
		query.setParameter(0, user.getName());
		User user2 = (User)query.uniqueResult();
		if(user2.getPasswd().equals(user.getPasswd())){
			System.out.println("����������ȷ");
			//�����������
			user2.setPasswd(updatepwd);
			session.save(user2);
			System.out.println("��������ɹ�");
			updatemsg = true;
		}else {
			System.out.println("�������벻��ȷ");
			updatemsg = false;
		}
		return updatemsg;
	}

}
