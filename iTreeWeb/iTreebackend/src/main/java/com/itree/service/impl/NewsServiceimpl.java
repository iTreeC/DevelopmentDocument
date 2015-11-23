package com.itree.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.itree.dao.NewsDao;
import com.itree.entity.TNews;
import com.itree.service.NewsService;

public class NewsServiceimpl implements NewsService {

	private NewsDao newsdao;

	public NewsDao getNewsdao() {
		return newsdao;
	}

	public void setNewsdao(NewsDao newsdao) {
		this.newsdao = newsdao;
		System.out.println(this.newsdao);
	}

	public List<TNews> getAll() {
		List<TNews> news = newsdao.getAll();
		// employees.clear();
		return news;
	}

	public void saveOrUpdate(TNews news) {
		newsdao.saveOrUpdate(news);

	}

	public TNews get(Integer id) {
		return newsdao.get(id);
	}

	public void stop(Integer id) {
		newsdao.stop(id);
		
	}

	public void start(Integer id) {
		newsdao.start(id);
		
		
	}

	public void del(Integer id) {
		try{
			newsdao.del(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
//批量删除
	public void delmore(String[] delIds) {
		for(String id : delIds){
			del(Integer.valueOf(id));
		}
		System.out.println("进入dao");
		
	}

	
}

/*
 * @Resource private NewsDao newsDao;
 * 
 * public void addNews(TNews news) { newsDao.save(news); }
 * 
 * public void addNews(TNews news, int id) { newsDao.save(news); }
 * 
 * public void delNews(String id) { newsDao.delete(id); } public void
 * delNews(int id) { newsDao.delete(id); }
 * 
 * public List<TNews> getAllNews() { List<TNews> news = newsDao.findAll(null);
 * return news; }
 * 
 * public int getTotal(String title, String id, Date createTimeStart, Date
 * createTimeEnd, Date updateTimeStart, Date updateTimeEnd) { return 0; }
 * 
 * public TNews getTnewsById(String id) { // TODO Auto-generated method stub
 * return newsDao.findById(TNews.class, id); }
 * 
 * public TNews getTnewsById(int id) { // TODO Auto-generated method stub return
 * newsDao.findById(TNews.class, id); } //����ɾ�� public void
 * delBatchTNews(int[] delIds) { for(int id : delIds){ delNews(id); }
 * 
 * }
 * 
 * public void delBatchTNews(String[] delIds) { // TODO Auto-generated method
 * stub for(String id : delIds){ delNews(id); }
 * 
 * }
 */
