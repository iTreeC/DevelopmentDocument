package com.itree.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.itree.dao.NewsDao;
import com.itree.entity.PageModel;
import com.itree.entity.TNews;
import com.itree.utils.SessionUtils;

public class NewsDaoimpl extends BaseDaoimpl implements NewsDao {
	Session session;
	Transaction transaction;

	// private TNews news;

	public List<TNews> getAll() {
		System.out.println("1");
		try {
			session = SessionUtils.getInstance().getSession();
			transaction = session.beginTransaction();
			List<TNews> news = session.createQuery("from TNews").list();
			transaction.commit();
			System.out.println(news);
			return news;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void saveOrUpdate(TNews news) {
		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		session.saveOrUpdate(news);
		System.out.println("1");
		transaction.commit();
	}

	public TNews get(Integer id) {

		session = SessionUtils.getInstance().getSession();
		transaction = session.beginTransaction();
		TNews new1 =  (TNews) session.get(TNews.class, id);
		transaction.commit();
		return new1;
		
	}

	/**
	 * add 更改用户标识，停用。
	 * 
	 * @author mxc
	 * @return
	 */
	public void stop(Integer id) {
		// TODO Auto-generated method stub
		// System.out.println("jinru ");
		if (id != null) {
			System.out.println("jinru2");
			try {

				session = SessionUtils.getInstance().getSession();
				transaction = session.beginTransaction();
				// session.beginTransaction();
				/*
				 * session.update("update TNews u set u.newsStatus = 0");
				 * transaction.commit();
				 */
				String hql = "update TNews u set u.newsStatus = 0  where id=?";
				Query query = session.createQuery(hql).setInteger(0, id);
				query.executeUpdate();
				transaction.commit();
				// session.getTransaction().commit();
				/*
				 * Session session =
				 * HibernateUitl.getSessionFactory().getCurrentSession();
				 * session.beginTransaction(); Query query =
				 * session.createQuery(
				 * "update Teacher t set t.name = 'yangtianb' where id = 3"
				 * );<br> query.executeUpdate();
				 * session.getTransaction().commit();
				 */
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		} else {
			throw new RuntimeException("更新的信息不能为空！");
		}
	}

	public void start(Integer id) {
		if (id != null) {
			try {
				session = SessionUtils.getInstance().getSession();
				transaction = session.beginTransaction();
				// session.beginTransaction();
				String hql = "update TNews u set u.newsStatus = 1  where id=?";
				Query query = session.createQuery(hql).setInteger(0, id);

				query.executeUpdate();
				transaction.commit();

				// session.getTransaction().commit();
				/*
				 * Session session =
				 * HibernateUitl.getSessionFactory().getCurrentSession();
				 * session.beginTransaction(); Query query =
				 * session.createQuery(
				 * "update Teacher t set t.name = 'yangtianb' where id = 3"
				 * );<br> query.executeUpdate();
				 * session.getTransaction().commit();
				 */
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		} else {
			throw new RuntimeException("更新的信息不能为空！");
		}
	}

	public void del(Integer id) {
		if (id != null) {
			try {
				session = SessionUtils.getInstance().getSession();
				transaction = session.beginTransaction();
				// session.beginTransaction();
				String hql = "update TNews u set u.newsStatus =2  where id=?";
				Query query = session.createQuery(hql).setInteger(0, id);
				query.executeUpdate();
				transaction.commit();

				// session.getTransaction().commit();
				/*
				 * Session session =
				 * HibernateUitl.getSessionFactory().getCurrentSession();
				 * session.beginTransaction(); Query query =
				 * session.createQuery(
				 * "update Teacher t set t.name = 'yangtianb' where id = 3"
				 * );<br> query.executeUpdate();
				 * session.getTransaction().commit();
				 */
				System.out.println("shanchu");
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}/* finally {
				session.close();
			}*/
		} else {
			throw new RuntimeException("更新的信息不能为空！");
		}

	}

}

/*
 * public void saveOrUpdate(TNews news) { try{ session
 * =SessionUtils.getInstance().getSession(); transaction =
 * session.beginTransaction(); session.saveOrUpdate(news); transaction.commit();
 * System.out.println(news); }catch (Exception e){ e.printStackTrace();
 * System.out.println(e.getMessage());
 * 
 * } }
 */

