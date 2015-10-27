package com.itree.dao;

import java.util.List;
import com.itree.entity.PageModel;
import com.itree.entity.TNews;


public interface NewsDao extends BaseDao {
	public List<TNews> getAll();
	
	public void saveOrUpdate(TNews news);
	public TNews get(Integer id);
	

}
