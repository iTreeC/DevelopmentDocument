package com.itree.dao.impl;

import java.util.List;
/**
 * 与角色相关的实现类
 * @Author mxc
 * 日期：2015年12月2日 19:49:39
 * 版本：1.0
 */
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itree.dao.RoleDao;
import com.itree.entity.TDuty;
import com.itree.permissionService.AddRoleAndPermission;
import com.itree.permissionService.impl.AddRoleAndPermissionimpl;
import com.itree.utils.SessionUtils;

public class RoleDaoimpl extends BaseDaoimpl implements RoleDao {
	Session session;
	Transaction transaction;
	
	/*
	 *该方法用于将新增加的角色存入TDuty表，并将该角色对应的具体权限id 及该角色的id
	 *与权限服务对接，存入权限表中
	 */
	public void add(TDuty d,int [] permission){
		try{
			System.out.println("RoleDaoImpi的add方法");
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			//存储数据
			System.out.println(d);
			//System.out.println(permission);
			//int rid = (Integer) session.save(d);//返回该角色在数据库中的ID
			//System.out.println(rid);
			session.save(d);//将角色信息存入对应的数据表
			//与权限对接
			String name = d.getDutyName();
			//将数字转换成String类型，不同的权限用“，”隔开，方便url传值。
			//int pid = 1;
			
			AddRoleAndPermission c = new AddRoleAndPermissionimpl();
			c.PostNamePid(name, permission); 
			
			transaction.commit();
			System.out.println("SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
	}
	//获取角色列表
		@SuppressWarnings("unchecked")
		public List<TDuty> getAll(){
			System.out.println("RoleDaoimpl的getAll（）方法"); 
			try{
		    	 session = SessionUtils.getInstance().getSession();
					transaction = session.beginTransaction();
					List<TDuty> duty = session.createQuery("from TDuty").list();
					transaction.commit();
					//System.out.println(duty);
					return duty;
					
			 }catch (Exception e){
		    		 e.printStackTrace(); 
		    		 System.out.println(e.getMessage()); 
		    		 return null;
		    		 }
		    	 }
}
