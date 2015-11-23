package com.itree.service;

import java.util.Date;
import java.util.List;

import com.itree.entity.TNews;


public interface NewsService {
	/*public void addNews(TNews news);
	public void addNews(TNews news, int id);
	public void delNews(int id);
	public List<TNews>getAllNews();
	public int getTotal(String title, String id, Date createTimeStart, Date createTimeEnd, Date updateTimeStart, Date updateTimeEnd);
	public TNews getTnewsById(int id);
	public void delBatchTNews(String[] delIds);
*/
	public List<TNews> getAll();
	public void saveOrUpdate(TNews news);
	public TNews get(Integer id);
	public void stop(Integer id);//设置用户标识为停用
	public void start(Integer id);//设置用户表示为可用
	public void del(Integer id);//删除列表信息
	public void delmore(String[] delIds);//批量删除

}
