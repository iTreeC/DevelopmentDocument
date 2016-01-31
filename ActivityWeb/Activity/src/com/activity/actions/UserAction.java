package com.activity.actions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.activity.model.User;
import com.activity.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {

	// 验证码的相关配置,从struts配置文件中获取
	private int width;// 配置图片的长宽
	private int height;
	private int number;// 显示多少字符
	private String codes;// 有哪些字符可供选择

	private HttpServletResponse response;
	private HttpServletRequest request;

	MD5 md5 = new MD5();
	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	private User user;

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		if (user == null) {
			user = new User();
		}
		return user;
	}

	/**
	 * 功能：添加用户界面的相关数据的获取，与验证。 作者：马大洲 时间：2015/12/22
	 * 
	 * @return
	 */
	public String addUser() {
		// 来存储返回struts的 值
		String addreutrn = null;
		// 来存储是否成功写入数据
		Boolean insert = null;

		String md5passwd = null;
		String telphone = ServletActionContext.getRequest().getParameter(
				"telphone-register");// 手机号
		String validateCode = ServletActionContext.getRequest().getParameter(
				"validateCode");// 用户输入的验证码
		String passwd1 = ServletActionContext.getRequest().getParameter(
				"passwd1");// 用户首次输入的登陆密码
		String passwd = ServletActionContext.getRequest().getParameter(
				"passwd-register");// 用户输入的确认密码
		String showvalidate = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("codes");
		System.out.println(passwd);

		if (validateCode.equalsIgnoreCase(showvalidate)) {
			System.out.println("验证码输入正确");
			if (passwd.equals(passwd1)) {
				System.out.println("两次输入的密码一致");
				// 将密码进行MD5加密
				md5passwd = md5.GetMD5Code(passwd);
				user.setName(telphone);
				user.setPasswd(md5passwd);
				insert = userService.addUser(user);
				if (insert) {
					addreutrn = "addsuccess";
				} else {
					System.out.println("此用户已被注册");
					ServletActionContext.getRequest().getSession()
							.setAttribute("adderror", "此用户已被注册");
					addreutrn = "adderror_telusered";
				}
			} else {
				System.out.println("两次输入密码不一致");
				ServletActionContext.getRequest().getSession()
						.setAttribute("adderror", "两次输入密码不一致");
				addreutrn = "adderror_passwddif";
			}
		} else {
			if (validateCode == null || validateCode == "") {
				addreutrn = "adderror_validateCode";
			} else {
				System.out.println("验证码输入不正确");
				ServletActionContext.getRequest().getSession()
						.setAttribute("adderror", "验证码输入不正确");
				// 验证码不正确的话就可以不走下面
				if (passwd.equals(passwd1)) {
					System.out.println("两次输入的密码一致");
					addreutrn = "adderror_passwddif";
				} else {
					System.out.println("两次输入密码不一致");
					addreutrn = "adderror_passwddif";
				}
				addreutrn = "adderror_validateCode";
			}
		}
		// ServletActionContext.getRequest().getSession().setAttribute("registermsg",
		// addreutrn);
		return addreutrn;
	}

	/**
	 * 来实现用户登录功能，实现了数据的校验
	 * 
	 * @return loginreturn 将此值返回到前台页面，不同结果返回不同值
	 */
	public String userLogin() {
		// 密文加密后的密码
		String md5passwd = null;
		String loginreturn = null;
		String telphone = ServletActionContext.getRequest().getParameter(
				"telphone-login");// 手机号
		String passwd = ServletActionContext.getRequest().getParameter(
				"passwd-login");// 用户输入的密码
		md5passwd = md5.GetMD5Code(passwd);// 密码加密
		user.setName(telphone);
		user.setPasswd(md5passwd);
		// 先检查此用户是否注册
		if (userService.logined(user)) {
			System.out.println("此用户已注册");
			// 来检车密码是否正确
			if (userService.userLogin(user)) {
				System.out.println("用户登录成功");
				loginreturn = "loginsuccess";
			} else {
				System.out.println("密码错误");
				ServletActionContext.getRequest().getSession()
						.setAttribute("loginerror", "密码错误");
				loginreturn = "loginerror";
			}
		} else {
			if (telphone == null || telphone == "") {
				loginreturn = "loginerror";
			} else {
				System.out.println("此用户没有注册");
				ServletActionContext.getRequest().getSession()
						.setAttribute("loginerror", "此用户未注册");
				loginreturn = "loginerror";
			}
		}
		// ServletActionContext.getRequest().getSession().setAttribute("loginmsg",
		// loginreturn);
		return loginreturn;
	}
	
	public String updatepwd(){
		String updatereturn = null;
		String telphone = ServletActionContext.getRequest().getParameter(
				"updatetelphone");// 手机号
		String passwd_old =md5.GetMD5Code(ServletActionContext.getRequest().getParameter(
				"updatepwdold"));// 原始密码
		String passwd_new1 = md5.GetMD5Code(ServletActionContext.getRequest().getParameter(
				"updatepwdnew1"));// 新密码1
		String passwd_new2 = md5.GetMD5Code(ServletActionContext.getRequest().getParameter(
				"updatepwdnew2"));// 确认新密码
		user.setName(telphone);
		user.setPasswd(passwd_old);
		
		if(passwd_new1.equals(passwd_new2)){
			userService.updatepwd(user, passwd_new2);
			updatereturn = "updatesuccess";
		}else{
			System.out.println("两次密码不一致");
			ServletActionContext.getRequest().getSession()
			.setAttribute("updatepwderror", "两次密码不一致");
			updatereturn="error";
		}
		
		return updatereturn;
	}

	/**
	 * 生成最大到最小之间的随机数 ，生成验证码中使用
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	private int random(int min, int max) {
		int m = new Random().nextInt(999999) % (max - min);
		return m + min;
	}

	/**
	 * 生成验证码
	 * 
	 * @throws IOException
	 */
	public String checkCode() throws IOException {

		// 要设置
		response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		response.setContentType("images/jpeg"); // 设置输出流内容格式为图片格式

		// 创建一张图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		// 创建白色背景
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// 画黑边框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);

		Random random = new Random();
		// 设置字符宽度
		int x = (width - 1) / number;// 将宽度平分
		int y = height - 4;

		StringBuffer sb = new StringBuffer();
		// 随机生成字符
		for (int i = 0; i < number; i++) {
			String code = String.valueOf(codes.charAt(random.nextInt(codes
					.length())));
			// 生成颜色不同的字母，这是生成随机数，共同组成一个颜色
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);

			// 设置字体大小
			Font font = new Font("Arial", Font.PLAIN,
					random(height / 2, height));// 字体，字体格式，字体大小
			g.setFont(font);
			g.setColor(new Color(red, green, blue));
			g.drawString(code, i * x + 2, y);
			sb.append(code);
		}
		// 将生成的字符串放到session中
		ServletActionContext.getRequest().getSession()
				.setAttribute("codes", sb.toString());
		// 随机生成一些点
		for (int i = 0; i < 50; i++) {
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);// 用画圆工具画出原点，用于迷惑

		}
		OutputStream out = response.getOutputStream();
		// 将图片装换成jpeg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);
		out.flush();
		out.close();
		return "success";
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

}
