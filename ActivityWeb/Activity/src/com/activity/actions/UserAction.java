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

	// ��֤����������,��struts�����ļ��л�ȡ
	private int width;// ����ͼƬ�ĳ���
	private int height;
	private int number;// ��ʾ�����ַ�
	private String codes;// ����Щ�ַ��ɹ�ѡ��

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
	 * ���ܣ�����û������������ݵĻ�ȡ������֤�� ���ߣ������ ʱ�䣺2015/12/22
	 * 
	 * @return
	 */
	public String addUser() {
		// ���洢����struts�� ֵ
		String addreutrn = null;
		// ���洢�Ƿ�ɹ�д������
		Boolean insert = null;

		String md5passwd = null;
		String telphone = ServletActionContext.getRequest().getParameter(
				"telphone-register");// �ֻ���
		String validateCode = ServletActionContext.getRequest().getParameter(
				"validateCode");// �û��������֤��
		String passwd1 = ServletActionContext.getRequest().getParameter(
				"passwd1");// �û��״�����ĵ�½����
		String passwd = ServletActionContext.getRequest().getParameter(
				"passwd-register");// �û������ȷ������
		String showvalidate = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("codes");
		System.out.println(passwd);

		if (validateCode.equalsIgnoreCase(showvalidate)) {
			System.out.println("��֤��������ȷ");
			if (passwd.equals(passwd1)) {
				System.out.println("�������������һ��");
				// ���������MD5����
				md5passwd = md5.GetMD5Code(passwd);
				user.setName(telphone);
				user.setPasswd(md5passwd);
				insert = userService.addUser(user);
				if (insert) {
					addreutrn = "addsuccess";
				} else {
					System.out.println("���û��ѱ�ע��");
					ServletActionContext.getRequest().getSession()
							.setAttribute("adderror", "���û��ѱ�ע��");
					addreutrn = "adderror_telusered";
				}
			} else {
				System.out.println("�����������벻һ��");
				ServletActionContext.getRequest().getSession()
						.setAttribute("adderror", "�����������벻һ��");
				addreutrn = "adderror_passwddif";
			}
		} else {
			if (validateCode == null || validateCode == "") {
				addreutrn = "adderror_validateCode";
			} else {
				System.out.println("��֤�����벻��ȷ");
				ServletActionContext.getRequest().getSession()
						.setAttribute("adderror", "��֤�����벻��ȷ");
				// ��֤�벻��ȷ�Ļ��Ϳ��Բ�������
				if (passwd.equals(passwd1)) {
					System.out.println("�������������һ��");
					addreutrn = "adderror_passwddif";
				} else {
					System.out.println("�����������벻һ��");
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
	 * ��ʵ���û���¼���ܣ�ʵ�������ݵ�У��
	 * 
	 * @return loginreturn ����ֵ���ص�ǰ̨ҳ�棬��ͬ������ز�ֵͬ
	 */
	public String userLogin() {
		// ���ļ��ܺ������
		String md5passwd = null;
		String loginreturn = null;
		String telphone = ServletActionContext.getRequest().getParameter(
				"telphone-login");// �ֻ���
		String passwd = ServletActionContext.getRequest().getParameter(
				"passwd-login");// �û����������
		md5passwd = md5.GetMD5Code(passwd);// �������
		user.setName(telphone);
		user.setPasswd(md5passwd);
		// �ȼ����û��Ƿ�ע��
		if (userService.logined(user)) {
			System.out.println("���û���ע��");
			// ���쳵�����Ƿ���ȷ
			if (userService.userLogin(user)) {
				System.out.println("�û���¼�ɹ�");
				loginreturn = "loginsuccess";
			} else {
				System.out.println("�������");
				ServletActionContext.getRequest().getSession()
						.setAttribute("loginerror", "�������");
				loginreturn = "loginerror";
			}
		} else {
			if (telphone == null || telphone == "") {
				loginreturn = "loginerror";
			} else {
				System.out.println("���û�û��ע��");
				ServletActionContext.getRequest().getSession()
						.setAttribute("loginerror", "���û�δע��");
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
				"updatetelphone");// �ֻ���
		String passwd_old =md5.GetMD5Code(ServletActionContext.getRequest().getParameter(
				"updatepwdold"));// ԭʼ����
		String passwd_new1 = md5.GetMD5Code(ServletActionContext.getRequest().getParameter(
				"updatepwdnew1"));// ������1
		String passwd_new2 = md5.GetMD5Code(ServletActionContext.getRequest().getParameter(
				"updatepwdnew2"));// ȷ��������
		user.setName(telphone);
		user.setPasswd(passwd_old);
		
		if(passwd_new1.equals(passwd_new2)){
			userService.updatepwd(user, passwd_new2);
			updatereturn = "updatesuccess";
		}else{
			System.out.println("�������벻һ��");
			ServletActionContext.getRequest().getSession()
			.setAttribute("updatepwderror", "�������벻һ��");
			updatereturn="error";
		}
		
		return updatereturn;
	}

	/**
	 * ���������С֮�������� ��������֤����ʹ��
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
	 * ������֤��
	 * 
	 * @throws IOException
	 */
	public String checkCode() throws IOException {

		// Ҫ����
		response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		response.setContentType("images/jpeg"); // ������������ݸ�ʽΪͼƬ��ʽ

		// ����һ��ͼƬ
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		// ������ɫ����
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// ���ڱ߿�
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);

		Random random = new Random();
		// �����ַ����
		int x = (width - 1) / number;// �����ƽ��
		int y = height - 4;

		StringBuffer sb = new StringBuffer();
		// ��������ַ�
		for (int i = 0; i < number; i++) {
			String code = String.valueOf(codes.charAt(random.nextInt(codes
					.length())));
			// ������ɫ��ͬ����ĸ�������������������ͬ���һ����ɫ
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);

			// ���������С
			Font font = new Font("Arial", Font.PLAIN,
					random(height / 2, height));// ���壬�����ʽ�������С
			g.setFont(font);
			g.setColor(new Color(red, green, blue));
			g.drawString(code, i * x + 2, y);
			sb.append(code);
		}
		// �����ɵ��ַ����ŵ�session��
		ServletActionContext.getRequest().getSession()
				.setAttribute("codes", sb.toString());
		// �������һЩ��
		for (int i = 0; i < 50; i++) {
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);// �û�Բ���߻���ԭ�㣬�����Ի�

		}
		OutputStream out = response.getOutputStream();
		// ��ͼƬװ����jpeg
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
