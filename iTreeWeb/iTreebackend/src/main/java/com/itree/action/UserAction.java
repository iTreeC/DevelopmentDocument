package com.itree.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.itree.entity.AddUserInfo;
import com.itree.entity.TLogin;
import com.itree.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class UserAction extends ActionSupport implements ModelDriven<TLogin>,RequestAware,Preparable{
	
	private static final long serialVersionUID = 1L;

	HttpServletRequest re =ServletActionContext.getRequest();//获取request作用于，接受前台ajax传来的值。
	
	/*因为TLogin中包含TUser表，而TUser表中含有user的全部信息（除密码），考虑到级联查询，故
	 *定义一个TLogin类型的user
    */
	public AddUserInfo aui;
	
	private UserService userService;
	private Integer id;
	private String uid;
	TLogin info ;
	private String name;
	private String pwd;
	
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public TLogin getInfo() {
		return info;
	}
	public void setInfo(TLogin info) {
		this.info = info;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AddUserInfo getAui() {
		return aui;
	}
	public void setAui(AddUserInfo aui) {
		this.aui = aui;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//管理员登录
	public  String login(){
		System.out.println("管理员登录");
		TLogin loginInfo = userService.login(name);
		if(loginInfo!=null){
			if(loginInfo.getPassword().equals(pwd)){
				return "success";
			}else{
				System.out.println("密码不正确！！");
			}
		return "404";
		}
		return "404";
	}
    //调用service的getAll()方法。用于获取用户列表集合。
	public String list(){
		request.put("user",userService.getAll());
		return "list";
	}
	//删除操作。根据选中行的ID,删除该行。
	public void deleteById(){
		System.out.println("删除操作");
		int d = Integer.parseInt(re.getParameter("delete"));
		userService.delete(d);
		System.out.println(d);
	}
	//更改用户表示，停用
	public void stop(){
		System.out.println("用户停用");
		int d = Integer.parseInt(re.getParameter("stop"));
		System.out.println("aaa");
		userService.stop(d);
		System.out.println("bbb");
		System.out.println(d);
	}
	//更改用户表示，启用
	public void start(){
		System.out.println("用户启用");
		int d = Integer.parseInt(re.getParameter("start"));
		userService.start(d);
		System.out.println(d);
	}
	//增加用户
	public String add(){
		System.out.println("增加用户");
		System.out.println(aui);
		userService.save(aui);
		
//		this.list();
		return "update";
	}
	//编辑操作一，根据id返回给实体的值，显示到jsp页面。
	public String edit(){
		if(id != null){
			
			info= userService.get(id);
			System.out.println(info.getId());
			//re.setAttribute("uid", info.getId());
			
			System.out.println(info.getPassword());
			//System.out.println(re.getAttribute("uid"));
		}
//		System.out.println(id);
		return "edit";
	}
	//编辑操作二，将更改后的值进行更新。
	public String doUpdate(){
		//此处可判断aui是否发生改变，若改变，则进行更新操作。亦或直接将aui进行更新操作。
		//考虑到用户意图，个人认为直接进行更新操作更为恰当。
		System.out.println("-----------------------------------------------");
		System.out.println(id);
		//System.out.println(re.getAttribute(uid));
		userService.doUpdate(aui,id);
		System.out.println("===============================================");
		//userService.doUpdate(aui);
		return "update";
	}
	
	private Map<String, Object> request;
	public void setRequest(Map<String, Object> arg0) {

		this.request = arg0;

	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public TLogin getModel() {
		// TODO Auto-generated method stub
		return null;
	}


	}

