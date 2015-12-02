package com.itree.dao.impl;
import java.net.URL;

/*
 * 与角色相关的实现类
 * @Author mxc
 * 日期：2015年12月2日 19:49:39
 * 版本：1.0
 */
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itree.dao.RoleDao;
import com.itree.entity.TDuty;
import com.itree.utils.ConnService;
import com.itree.utils.SessionUtils;

public class RoleDaoimpl extends BaseDaoimpl implements RoleDao {
	Session session;
	Transaction transaction;
	
	/*
	 *该方法用于将新增加的角色存入TDuty表，并将该角色对应的具体权限id 及该角色的id
	 *与权限服务对接，存入权限表中
	 */
	public void add(TDuty d,String permission){
		try{
			System.out.println("RoleDaoImpi的add方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			//存储数据
			System.out.println(d);
			System.out.println(permission);
			int rid = (Integer) session.save(d);//返回该角色在数据库中的ID
			System.out.println(rid);
			
			//与权限对接
			 String targetURL = "http://localhost:8080/iTreePermissionService/rolepermission/post";
			 URL url = new URL(targetURL);
			 String method = "POST";
			 String input = "{\"rid\":1,\"pid\":2}";
			 ConnService c = new ConnService();
			 c.Conntce(url, method, input);
			
			
			transaction.commit();
			System.out.println("SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
	}
}
