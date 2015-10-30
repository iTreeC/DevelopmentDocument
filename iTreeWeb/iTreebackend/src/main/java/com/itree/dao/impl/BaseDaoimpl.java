package com.itree.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itree.dao.BaseDao;



public class BaseDaoimpl implements BaseDao{
	private SessionFactory sessionfaction;

	public SessionFactory getSessionfaction() {
		return sessionfaction;
	}

	public Session getCurrentSession() {// 返回自制函数
		return this.sessionfaction.getCurrentSession();
	}


	public void setSessionfaction(SessionFactory sessionfaction) {
		this.sessionfaction = sessionfaction;
	}

	public Serializable save(Object entity) {
		return this.getCurrentSession().save(entity);
	}

	public void delete(Object entity) {
		this.getCurrentSession().delete(entity);
		
	}

	public void update(Object entity) {
		this.getCurrentSession().update(entity);
		
	}

	public void saveOrUpdata(Object entity) {
		this.getCurrentSession().saveOrUpdate(entity);
		
	}

	public <T> T login(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> list = q.list();
		if (list != null & list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public <T> T login(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null & !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> list = q.list();
		if (list != null & list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public <T> T findById(Class<T> class1, String id) {
		
		return (T) this.getCurrentSession().get(class1, id);
	}

	public <T> T findById(Class<T> class1, int id) {
		return (T) this.getCurrentSession().load(class1, id);
	}

	public <T> List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	public <T> List<T> findAll(String table) {
		String hql = "from " + table;
		Query q = this.getCurrentSession().createQuery(hql);
		// q.setParameter(0, o);
		return q.list();
	}

	public <T> List<T> find(String hql, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> List<T> find(String hql, Map<String, Object> params) {
		
		// TODO Auto-generated method stub
		return null;
	}

	public <T> List<T> find(String hql, Map<String, Object> params, int page,
			int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null & !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}

		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public Long count(String table) {
		String hql = "select count(tb) from " + table + "tb";
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null & !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	public <T> T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null & !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> list = q.list();
		if (list != null & list.size() > 0) {
			return list.get(0);
		}
		return null;

	}
	
	

}
