package com.turing.activity.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.transform.Transformers;

import com.turing.activity.service.entity.HourMin;
import com.turing.activity.service.entity.Rule;
import com.turing.activity.service.entity.YearMonDay;

/**
 * “时分”引擎，主要针对“时分”规则的操作。
 * 
 * @author 王俊飞
 * @date 2015-10-15
 *
 */
public class HourMinEngine extends BaseEngine {

	private static Logger logger = Logger.getLogger(BaseEngine.class);

	// 单例
	private static final HourMinEngine hourMinEngine = new HourMinEngine();

	private HourMinEngine() {
	}

	// 通过该方法获得实例对象
	public static HourMinEngine getSingleton() {
		return hourMinEngine;
	}

	@Override
	public List<Rule> findByShopId(int shopId) {
		try {
			// 打开session,开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			List<Rule> listRule = new ArrayList<Rule>();
			if (shopId > 0) {
				String sql = "SELECT r.rule_Id, r.s_hourmin, r.e_hourmin "
						+ "FROM t_rule r LEFT JOIN t_rule_shop rs ON r.rule_Id = rs.rule_Id where shop_Id = "
						+ shopId;
				Query query = session.createSQLQuery(sql);
				// .setResultTransformer(Transformers.TO_LIST);
				List list = query.list();
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Object[] object = (Object[]) list.get(i);

						Rule rule = new Rule();
						rule.setRuleId((int) object[0]);

						HourMin hourMin = new HourMin();
						hourMin.setsHourMin((String) object[1]);
						hourMin.seteHourMin((String) object[2]);
						rule.setHourMin(hourMin);

						listRule.add(rule);

					}
					transaction.commit();
					logger.info("查询规则的信息成功！");
				} else {
					logger.info("没有“小时：分钟”规则的信息！");
				}

			} else {
				logger.debug("shopId必须大于0！");
			}
			return listRule;
		} catch (Exception e) {
			logger.error("查询失败！");
			return null;
		}
	}

	@Override
	public List<Rule> find() {
		try {
			// 打开session,开启事物
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			List<Rule> listRule = new ArrayList<Rule>();
			String sql = "SELECT r.rule_Id, r.s_hourmin, r.e_hourmin "
					+ "FROM t_rule r";
			Query query = session.createSQLQuery(sql);
			// .setResultTransformer(Transformers.TO_LIST);
			List list = query.list();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Object[] object = (Object[]) list.get(i);

					Rule rule = new Rule();
					rule.setRuleId((int) object[0]);

					HourMin hourMin = new HourMin();
					hourMin.setsHourMin((String) object[1]);
					hourMin.seteHourMin((String) object[2]);
					rule.setHourMin(hourMin);

					listRule.add(rule);

				}
				transaction.commit();
				logger.info("查询规则的信息成功！");
			} else {
				logger.info("没有“小时：分钟”规则的信息！");
			}
			return listRule;
		} catch (Exception e) {
			logger.error("查询失败！");
			return null;
		}
	}
}