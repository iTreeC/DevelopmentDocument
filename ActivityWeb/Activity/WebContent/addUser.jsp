<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ page import="java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
		//这里面写方法
			//前台检查是否输入了信息
			function checkData(){
				if(document.getElementById("telphone-register").value.length < 1) {
					alert("请输入手机号！");
					//document.getElementById("msg").innerHTML="<div style=''><img border='0' src='<c:url value='/images/login/warning.gif'/>'/>&nbsp;请输入您的用户名！</div>";
			     document.getElementById("telphone-register").focus();
					return false;
				}
				if( document.getElementById("validateCode").value.length < 1) {
					alert("请输入验证码！");
					//document.getElementById("msg").innerHTML="<div style=''><img border='0' src='<c:url value='/images/login/warning.gif'/>'/>&nbsp;请输入您的密码！</div>";
			     document.getElementById("validateCode").focus() ;
					return false;
				}	
				if( document.getElementById("passwd1").value.length < 1) {
					alert("请输入密码！");
					//document.getElementById("msg").innerHTML="<div style=''><img border='0' src='<c:url value='/images/login/warning.gif'/>'/>&nbsp;请输入您的密码！</div>";
			     document.getElementById("passwd1").focus() ;
					return false;
				}	
				if( document.getElementById("passwd-register").value.length < 1) {
					alert("请输入确认密码！");
					//document.getElementById("msg").innerHTML="<div style=''><img border='0' src='<c:url value='/images/login/warning.gif'/>'/>&nbsp;请输入您的密码！</div>";
			     document.getElementById("passwd-register").focus() ;
					return false;
				}
				
			}
		
			//点击图片重新生成验证码
			function reloadcheckcode(){
				var img = document.getElementById("showvalidateCode");
				img.src = "checkCode.action?"+Math.random();
			}
		
			
			/* 限制只能输手机号 */
			function yz(){
				var tel = document.getElementById("telphone-register").value;
				
				var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
				if(!myreg.test(tel)&&tel!=""){ 
					/* alert('请输入有效的手机号码！');  */
					document.getElementById("error").innerHTML="请输入有效的手机号码！";	
					document.getElementById("telphone-register").focus();
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
<form action="addUser.action" method="post">
	<div><font color="red">${adderror}</font></div>
	手机号码：<input type="text" id="telphone-register" name="telphone-register" onblur="yz()"><br/>	
	登陆密码：<input type="password" id="passwd1" name="passwd1" ><br/>
	确认密码：<input type="password" id="passwd-register" name="passwd-register"><br/>
	图片验证码：<input type="text" id="validateCode" name="validateCode"> 
	
	验证码：<img src="checkCode.action" id="showvalidateCode" name="showvalidateCode" onclick ="reloadcheckcode()" title="看不清，请点击图片，换一张"/><br/>
	<input type="submit" value="注册" onclick="checkData()" >
	
	
</form>
			
</body>

</html>