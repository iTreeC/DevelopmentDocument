package com.turing.activity.service.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.transform.Transformers;

import com.turing.activity.service.dao.api.Engine;
import com.turing.activity.service.entity.HourMin;
import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.entity.Shop;
import com.turing.activity.service.entity.Week;
import com.turing.activity.service.entity.YearMonDay;

/**
 * 基础引擎，具有对所有规则的增、删、改、查的能力。
 * 
 * @author 王俊飞
 * @date 2015-10-15
 *
 */
public class BaseEngine implements Engine {
	protected SessionFactory sessionFactory;
	protected Session session;
	protected Transaction transaction;

	private static Logger logger = Logger.getLogger(BaseEngine.class);

	// 单例
	private static final BaseEngine baseEngine = new BaseEngine();

	protected BaseEngine() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	// 通过该方法获得实例对象
	public static BaseEngine getSingleton() {
		return baseEngine;
	}

	@Override
	public void save(Rule o) {
		// TODO Auto-generated method stub
		try {
			// 打开session,开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			if (o != null) {
				session.save(o);
			} else {
				logger.debug("保存的规则对象不能为空！");
			}

			transaction.commit();
			session.close();
			logger.info("规则保存成功！");
		} catch (Exception e) {
			logger.error("规则" + o + "保存失败！");
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try {
			// 打开session，开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			if (id > 0) {
				Rule r = new Rule();
				r.setRuleId(id);
				session.delete(r);
				
				transaction.commit();
				session.close();
				logger.info("id为" + id + "的规则删除成功！");
			} else {
				logger.debug("删除的规则id不能小于1！");
			}
		} catch (Exception e) {
			logger.error("规则删除失败或者没有该数据！");
		}
	}

	@Override
	public void updata(Rule o) {
		// TODO Auto-generated method stub
		try {
			// 打开session,开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			if (o != null) {
				session.saveOrUpdate(o);
			} else {
				logger.debug("更新的对象不能为空！");
			}
			transaction.commit();
			session.close();
			logger.info("更新成功！");
		} catch (Exception e) {
			logger.error("规则更新失败！");
		}
	}

	@Override
	public Rule findById(int id) {
		// TODO Auto-generated method stub
		try {
			// 打开session，开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Rule rule = null;
			if (id > 0) {
				rule = (Rule) session.load(Rule.class, id);
				transaction.commit();
				logger.info("通过" + id + "查找规则成功！");
			} else {
				logger.debug("查找的规则id必须大于0！");
			}
			return rule;
		} catch (Exception e) {
			logger.error("规则查找失败！");
			return null;
		}
	}

	@Override
	public List<Rule> find(String hql) {
		// TODO Auto-generated method stub
		try {
			// 打开session，开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			List list = null;
			if (hql != null) {
				list = session.createQuery(hql).list();
				transaction.commit();
				logger.info("查询成功！");
			} else {
				logger.debug("规则查找语句为空！");
			}
			return list;
		} catch (Exception e) {
			logger.error("规则查找失败！");
			return null;
		}
	}

	@Override
	public List<Rule> find() {
		return null;
	}

	@Override
	public Long count(String hql) {
		// TODO Auto-generated method stub
		try {
			// 打开session，开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Long c = 0L;
			if (hql != null) {
				Query q = session.createQuery(hql);
				c = (Long) q.uniqueResult();
				transaction.commit();
				logger.info("查询数目成功！");
			} else {
				logger.debug("查找语句不能为空！");
			}
			return c;
		} catch (Exception e) {
			logger.error("数目查找失败！");
			return null;
		}
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		// 打开session，开启事物
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			Long c = 0L;
			if (hql != null) {
				Query q = session.createQuery(hql);
				if (params != null && !params.isEmpty()) {
					for (String key : params.keySet()) {
						q.setParameter(key, params.get(key));
					}
					c = (Long) q.uniqueResult();
					transaction.commit();
					logger.info("查询数目成功！");
				} else {
					logger.debug("查找的参数不能为空！");
				}
			} else {
				logger.debug("查找语句不能为空！");
			}
			return c;
		} catch (Exception e) {
			logger.error("数目查找失败！");
			return null;
		}
	}

	@Override
	public void executeHql(String hql) {
		// 打开session，开启事物
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			if (hql != null) {
				session.createQuery(hql).executeUpdate();
				transaction.commit();
				logger.info("hql语句执行成功！");
			} else {
				logger.error("hql语句不能为空！");
			}
		} catch (Exception e) {
			logger.error("hql语句执行失败！");
		}
	}

	@Override
	public List<Rule> findByShopId(int shopId) {
		try {
			// 打开session，开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			List<Rule> listRule = null;
			if (shopId > 0) {
				String sql = "SELECT * "
						+ "FROM t_rule r LEFT JOIN t_rule_shop rs ON r.rule_Id = rs.rule_Id where shop_Id = "
						+ shopId;
				Query query = session.createSQLQuery(sql).addEntity(Rule.class);
				listRule = query.list();
				transaction.commit();
				logger.info("通过商家号" + shopId + "查询成功!");
			} else {
				logger.debug("shopId必须大于0!");
			}
			return listRule;
		} catch (Exception e) {
			logger.error("查询规则失败！");
			return null;
		}
	}

	@Override
	public List<Shop> findShopByRuleId(int ruleId) {
		// TODO Auto-generated method stub
		try {
			// 打开session，开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			List<Shop> listShop = null;
			if (ruleId > 0) {
				String sql = "SELECT * "
						+ "FROM t_shop s LEFT JOIN t_rule_shop rs ON s.shop_Id = rs.shop_Id where rs.rule_Id = "
						+ ruleId;
				Query query = session.createSQLQuery(sql).addEntity(Shop.class);
				listShop = query.list();
				transaction.commit();
				logger.info("查询成功！");
			} else {
				logger.debug("ruleId必须大于0!");
			}

			return listShop;
		} catch (Exception e) {
			logger.error("查询商家失败！");
			return null;
		}
	}

	@Override
	public void saveShop(Shop o) {
		// TODO Auto-generated method stub
		try {
			// 打开session,开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			if (o != null) {
				session.save(o);
			} else {
				logger.debug("保存的商家对象不能为空！");
			}

			transaction.commit();
			session.close();
			logger.info("商家保存成功！");
		} catch (Exception e) {
			logger.error("商家" + o + "保存失败！");
		}
	}

	@Override
	public void deleteShop(int id) {
		// TODO Auto-generated method stub
		try {
			// 打开session，开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			if (id > 0) {
				Shop shop = new Shop();
				shop.setShopId(id);
				session.delete(shop);

			} else {
				logger.debug("删除的商家id不能小于1！");
			}

			transaction.commit();
			session.close();
			logger.info("id为" + id + "的商家删除成功！");
		} catch (Exception e) {
			logger.error("商家删除失败！");
		}
	}

	@Override
	public void updataShop(Shop o) {
		// TODO Auto-generated method stub
		try {
			// 打开session,开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			if (o != null) {
				session.saveOrUpdate(o);
			} else {
				logger.debug("更新的对象不能为空！");
			}
			transaction.commit();
			session.close();
			logger.info("更新成功！");
		} catch (Exception e) {
			logger.error("商家更新失败！");
		}
	}

	@Override
	public List<Integer> findRuleIdByShopId(int shopId) {
		// TODO Auto-generated method stub
		try {
			// 打开session， 开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			List<Integer> listRule = null;
			if (shopId > 0) {
				String sql = "SELECT rs.rule_Id FROM t_rule_shop rs where rs.shop_Id = "
						+ shopId;
				Query query = session.createSQLQuery(sql);
				listRule = query.list();
				transaction.commit();
				logger.info("查询成功！");
			} else {
				logger.debug("shopId必须大于0!");
			}
			return listRule;
		} catch (Exception e) {
			logger.error("查询规则失败！");
			return null;
		}

	}

	@Override
	public List<Shop> findShopByShopId(int shopId) {
		// TODO Auto-generated method stub
		try {
			// 打开session, 开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			List<Shop> listShop = null;
			if (shopId > 0) {
				String sql = "SELECT * " + "FROM t_shop s where s.shop_Id = "
						+ shopId;
				Query query = session.createSQLQuery(sql).addEntity(Shop.class);
				listShop = query.list();
				transaction.commit();
				logger.info("查询成功！");
			} else {
				logger.debug("shopId必须大于0!");
			}
			return listShop;
		} catch (Exception e) {
			logger.error("查询规则失败！");
			return null;
		}
	}

	@Override
	public List<Integer> findShopIdByRuleId(int ruleId) {
		// TODO Auto-generated method stub
		try {
			// 打开session
			session = sessionFactory.openSession();
			// 开启事物
			transaction = session.beginTransaction();

			List<Integer> listShop = null;
			if (ruleId > 0) {
				String sql = "SELECT rs.shop_Id FROM t_rule_shop rs where rs.rule_Id = "
						+ ruleId;
				Query query = session.createSQLQuery(sql);
				listShop = query.list();
				transaction.commit();
				logger.info("查询成功！");
			} else {
				logger.debug("ruleId必须大于0!");
			}
			return listShop;
		} catch (Exception e) {
			logger.error("查询规则失败！");
			return null;
		}
	}

}
