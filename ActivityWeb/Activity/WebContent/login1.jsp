<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="msg" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
		//这里面写方法
			//前台检查是否输入了用户名，密码信息
			function checkData(){
				if(document.getElementById("telphone-login").value.length < 1) {
					alert("请输入您的用户名！");
					//document.getElementById("msg").innerHTML="<div style=''><img border='0' src='<c:url value='/images/login/warning.gif'/>'/>&nbsp;请输入您的用户名！</div>";
			     document.getElementById("telphone-login").focus();
					return false;
				}
				if( document.getElementById("passwd-login").value.length < 1) {
					alert("请输入您的密码！");
					//document.getElementById("msg").innerHTML="<div style=''><img border='0' src='<c:url value='/images/login/warning.gif'/>'/>&nbsp;请输入您的密码！</div>";
			     document.getElementById("passwd-login").focus() ;
					return false;
				}
				
			}
			/* 限制只能输手机号 */
			function yz(){
				var tel = document.getElementById("telphone-login").value;
				
				var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
				 if(!myreg.test(tel)&&tel!=""){ 
					/* alert('请输入有效的手机号码！');  */
					document.getElementById("error").innerHTML="请输入有效的手机号码！";	
					document.getElementById("telphone-login").focus();
					return false; 
				 } else if(myreg.test(tel)&&tel!=""){
					 document.getElementById("error").innerHTML="手机号码格式正确！";
				 }
			} 
</script>

</head>
<body>
	<a href="/Activity/addUser.jsp">用户注册</a>
	<a href="/Activity/login1.jsp">登录</a>
	<h1 align="center">登录页面</h1>
	<font color="red"><div id="error">${loginerror}</div></font>
	<form action="userLogin.action" method="post">
		
		手机号：<input type="text" id="telphone-login" name="telphone-login" onblur="yz()" value="<%=request.getParameter("telphone-login")%>"><br />
		密码：<input type="password" id="passwd-login"  name="passwd-login"><br /> 
		
		<input type="submit" id="loginForm" value="登录" onclick="checkData()">
			
		
		
		
	</form>
	

</body>
</html>