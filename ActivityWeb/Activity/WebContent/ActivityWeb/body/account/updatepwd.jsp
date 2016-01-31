<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/Activity/updatePwd.action" method="post">
用户名：<input type="text" name="updatetelphone">
原始密码：<input type="text" name="updatepwdold">
新密码：<input type="text" name="updatepwdnew1">
确认新密码：<input type="text" name="updatepwdnew2">
<input type="submit" value="提交">
</form>
</body>
</html>