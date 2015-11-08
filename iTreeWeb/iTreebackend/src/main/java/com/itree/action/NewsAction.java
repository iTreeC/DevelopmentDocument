package com.itree.action;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.itree.entity.TNews;
import com.itree.service.NewsService;
/*import com.itree.utils.JSONUtil;*/
import com.itree.utils.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NewsAction extends ActionSupport implements ModelDriven<TNews>, RequestAware, Preparable {

	private static final long serialVersionUID = 1L;

	private NewsService newsService;

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public TNews news;

	public TNews getNews() {
		return news;
	}

	public void setNews(TNews news) {
		this.news = news;
	}

	public String list() {
		request.put("news", newsService.getAll());

		System.out.println(request.get("news"));
		return "list";

	}

	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public void prepareSave() {
		if(id == null){
			model = new TNews();
		}else{
			model = newsService.get(id);
	}
	}

	private TNews model;

	public TNews getModel() {
		// TODO Auto-generated method stub
		System.out.println(model);
		return model;
	}
	
	
	public String save() {
		if(id == null){
			model.setNewsDate(new Date());			
		}
		newsService.saveOrUpdate(model);
		System.out.println(model);
		return SUCCESS;

	}

	/*
	 * private TNews news;
	 * 
	 * @Resource private NewsService newsService; private String ids; private
	 * int page; private int rows; private String sort; private String order;
	 * private String title; private Date createTimeStart; private Date
	 * createTimeEnd; private Date updateTimeStart; private Date updateTimeEnd;
	 * //private String channelId;
	 * 
	 * public TNews getModel() { // TODO Auto-generated method stub return null;
	 * }
	 * 
	 * public String addInput(){ return "add_input"; }
	 * 
	 * public void addArticle(){ Json j = new Json(); try {
	 * newsService.addNews(news); j.setSuccess(true);
	 * j.setMsg("���¡�"+news.getNewsTittle()+"���ѳɹ���ӣ�"); } catch (Exception
	 * e) { j.setMsg("���¡�"+news.getNewsTittle()+"�����ʧ�ܣ�"); }
	 * JSONUtil.toJSON(j); }
	 * 
	 * public String updateInput(){ Integer id = news.getId(); news =
	 * newsService.getTnewsById(id); ActionContext.getContext().put("article",
	 * news); return "update_input"; }
	 * 
	 * 
	 * 
	 * public void delTNews(){ Json j = new Json();
	 * 
	 * try { String[] delIds = ids.split(",");
	 * newsService.delBatchTNews(delIds); j.setSuccess(true);
	 * j.setMsg("�ɹ�ɾ��"+delIds.length+"��ƪ����!"); } catch (Exception e) {
	 * j.setMsg("ɾ������ʧ�ܣ�"); } JSONUtil.toJSON(j); }
	 */
	private Map<String, Object> request;

	public void setRequest(Map<String, Object> arg0) {

		this.request = arg0;

	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

}
