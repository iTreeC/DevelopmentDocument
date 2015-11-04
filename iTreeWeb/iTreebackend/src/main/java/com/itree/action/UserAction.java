package com.itree.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.itree.entity.TLogin;
import com.itree.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class UserAction extends ActionSupport implements ModelDriven<TLogin>,RequestAware,Preparable{
	
	private static final long serialVersionUID = 1L;

	HttpServletRequest re = ServletActionContext.getRequest();//获取request作用于，接受前台ajax传来的值。
	
	/*因为TLogin中包含TUser表，而TUser表中含有user的全部信息（除密码），考虑到级联查询，故
	 *定义一个TLogin类型的user
    */
	public TLogin user;
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    //调用service的getAll()方法。用于获取用户列表集合。
	public String list(){
		request.put("user",userService.getAll());
		System.out.println(request.get("news"));
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
		System.out.println("aaaaaa");
		userService.stop(d);
		System.out.println("bbb");
		System.out.println(d);
	}
	//更改用户表示，启用
	public void start(){
		System.out.println("用户停用");
		int d = Integer.parseInt(re.getParameter("start"));
		userService.start(d);
		System.out.println(d);
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

