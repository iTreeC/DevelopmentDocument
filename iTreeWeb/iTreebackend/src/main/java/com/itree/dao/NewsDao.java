package com.itree.dao;

import java.util.List;

import com.itree.entity.PageModel;
import com.itree.entity.TNews;


public interface NewsDao extends BaseDao {
	public List<TNews> getAll();
	public void saveOrUpdate(TNews news);
	public TNews get(Integer id);
	
	public void del(Integer id);//删除列表中的咨询
	public void stop(Integer id);//设置用户标识为停用
	public void start(Integer id);//设置用户表示为可用

}
