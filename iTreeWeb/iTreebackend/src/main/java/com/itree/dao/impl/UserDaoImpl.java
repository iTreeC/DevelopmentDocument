package com.itree.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itree.dao.UserDao;
import com.itree.entity.AddUserInfo;
import com.itree.entity.TDuty;
import com.itree.entity.TLogin;
import com.itree.entity.TUser;
import com.itree.permissionService.AddUser;
import com.itree.permissionService.UserRoleMapping;
import com.itree.permissionService.impl.AddUserimpl;
import com.itree.permissionService.impl.UserRoleMappingimpl;
import com.itree.utils.SessionUtils;

public class UserDaoimpl implements UserDao {

	Session session;
	Transaction transaction;

	// 获取用户列表
	@SuppressWarnings("unchecked")
	public List<TLogin> getAll() {
		System.out.println("UserDaoimpl的getAll（）方法");
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			List<TLogin> user = session.createQuery("from TLogin").list();
			transaction.commit();
			System.out.println(user);
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	// 登录功能。更加用户名查找密码。
	public TLogin login(String name) {
		try {
			System.out.println("UserDaoImpl的login方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("select u.id from TUser u where u.userName='" + name + "'");
			Integer id = (Integer) query.uniqueResult();
			System.out.println(id);
			Query q = session.createQuery("from TLogin t where t.TUser.id='" + id + "'");
			TLogin loginInfo = (TLogin) q.uniqueResult();
			/* TUser user = (TUser)session.load(TUser.class, id); */
			transaction.commit();
			System.out.println(loginInfo);
			return loginInfo;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("用户名不存在");
			return null;
		}
	}

	// 根据ID，删除用户信息。
	public void delete(int id) {
		try {
			System.out.println("UserDaoImpl的delete方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			TLogin login = (TLogin) session.load(TLogin.class, id);
			/* TUser user = (TUser)session.load(TUser.class, id); */
			session.delete(login);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
	}

	// 更改用户标识，停用
	public void stop(int id) {
		try {
			System.out.println("UserDaoImpl的stop方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			TLogin login = (TLogin) session.get(TLogin.class, id);
			Query query = session.createQuery("update TUser u set u.userStatus = 0 where id ='" + login.getId() + "'");
			query.executeUpdate();
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
	}

	// 更改用户标识，启用
	public void start(int id) {
		try {
			System.out.println("UserDaoImpl的stop方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			TLogin login = (TLogin) session.get(TLogin.class, id);
			Query query = session.createQuery("update TUser u set u.userStatus = 1 where id ='" + login.getId() + "'");
			query.executeUpdate();
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
	}
	
	// 增加用户
	public void save(AddUserInfo aui) {
		try {
			System.out.println("UserDaoImpi的save方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			// 存储数据
			int a = aui.getDuty();
			System.out.println(a);
			TDuty d = (TDuty) session.get(TDuty.class, a);

			TUser u = new TUser();
			TLogin l = new TLogin();

			u.setTDuty(d);
			u.setUserName(aui.getName());
			u.setUserHoby(aui.getHobby());
			u.setUserProfile(aui.getProfile());
			u.setUserTel(aui.getTelephone());
			u.setUserSex(aui.getSex());
			u.setUserStatus(aui.getUserStatus());
			
			int uid = (Integer)session.save(u);
			l.setTUser(u);
			l.setPassword(aui.getPassword());
			int cid = (Integer)session.save(l);
			
			/*与权限服务对接  进行用户角色匹配。此处建议权限服务增加方法：在用户角色匹配的同时，进行
			判断，若该用户id不存在，则进行增加用户操作。
			*/
			//1.增加用户
			AddUser adduser = new AddUserimpl();
			adduser.PostCid(cid);
			
			//2.用户--角色匹配
			int rid = a;	//角色id
			UserRoleMapping m = new UserRoleMappingimpl();
			m.GetUidPid(uid, rid);
			
			transaction.commit();
			//System.out.println("SUCCESS");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// 根据id返回tlogin实体。
	public TLogin get(int id) {
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			TLogin info = (TLogin) session.get(TLogin.class, id);
			transaction.commit();
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	// 更新用户信息。
	public void doUpdate(AddUserInfo aui, int id) {
		try {
			System.out.println("UserDaoImpi的doUpdate方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();

			TLogin l = (TLogin) session.get(TLogin.class, id);
			System.out.println(l);

			l.setPassword(aui.getPassword());
			l.setLoginStatus(aui.getUserStatus());
			TUser u = l.getTUser();
			u.setUserHoby(aui.getHobby());
			u.setUserMotto(aui.getProfile());
			u.setUserName(aui.getName());
			u.setUserSex(aui.getSex());
			u.setUserTel(aui.getTelephone());
			u.setUserStatus(aui.getUserStatus());
			// TDuty d = l.getTUser().getTDuty();
			TDuty d1 = (TDuty) session.get(TDuty.class, aui.getDuty());
			u.setTDuty(d1);
			// d.setDutyName(aui.getDuty());

			// TFile f = (TFile) l.getTUser().getTFiles();
			// f.setFilePath(aui.getPototPath());

			session.save(l);
			transaction.commit();
			System.out.println("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
	}

}