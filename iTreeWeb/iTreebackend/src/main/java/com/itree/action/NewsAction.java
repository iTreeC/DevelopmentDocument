package com.itree.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import com.itree.entity.TNews;
import com.itree.service.NewsService;
/*import com.itree.utils.JSONUtil;*/
import com.itree.utils.Json;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NewsAction extends ActionSupport implements ModelDriven<TNews>, RequestAware, Preparable {

	private static final long serialVersionUID = 1L;

	private NewsService newsService;

	public NewsService getNewsService() {
		return newsService;
	}
	private String idList;
	
	
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
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
		return "list";
	}

	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}
	
    public TNews getModel() {
		//System.out.println(model);
		return news;
	}
    
    
	public String edit(){
		if(id != null){
			news = newsService.get(id);		
		}
		System.out.println(id);
		return INPUT;
	}
	
	
	

	public void prepareSave() {
		
		
		  System.out.println(id);
          System.out.println("prepare");
		if(id == null){
			news = new TNews();
		}else{
			news = newsService.get(id);
	}
		
	}
	
	public String save() {
		if(id == null){
			news.setNewsDate(new Date());			
			}
		System.out.println(news);
		newsService.saveOrUpdate(news);
		System.out.println(news);
		this.list();
		return SUCCESS;
		
		

	}
	//更改用户状态
	public String stop(){
		//HttpServletRequest rq = ServletActionContext.getRequest();
		//id= rq.getParameter("id");
		HttpServletRequest rq = ServletActionContext.getRequest();
		//id= rq.getParameter("id");
		id=Integer.parseInt(rq.getParameter("id"));
		System.out.println(id);
		newsService.stop(id);
		//ut.stop(user1);
		
		return "list";
	}
	public String start(){
		HttpServletRequest rq = ServletActionContext.getRequest();
		//id= rq.getParameter("id");
		id=Integer.parseInt(rq.getParameter("id"));
		System.out.println(id);
		newsService.start(id);
		System.out.println("1");
		return "list";
	}
	//删除文章
	public String  del(){
		HttpServletRequest rq = ServletActionContext.getRequest();
		//id= rq.getParameter("id");
		id=Integer.parseInt(rq.getParameter("id"));
		System.out.println(id);
		newsService.del(id);
		System.out.println("1");
		return "list";
	}
	//批量删除
	public String datadel(){
		System.out.println(idList);
		Json j = new Json();
		try {
			String[] delIds = idList.split(",");
			System.out.println(delIds);
			
			newsService.delmore(delIds);
			System.out.println("进入service");
			/*j.setSuccess(true);
			j.setMsg("成功删除【"+delIds.length+"】个用户！");*/
		} catch (Exception e) {
			
			
			e.getMessage();
			e.getStackTrace();
			/*j.setMsg("删除用户失败！");*/
		}
	 
		//JSONUtil.toJSON(j);
		return "list";
		
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
