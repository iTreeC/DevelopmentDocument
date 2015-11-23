package com.itree.dao;

import java.util.List;
import com.itree.entity.TNews;


public interface NewsDao extends BaseDao {
	public List<TNews> getAll();
	public void saveOrUpdate(TNews news);
	public TNews get(Integer id);
	
	public void del(Integer id);//删除
	public void stop(Integer id);//停用
	public void start(Integer id);//启用
	}
