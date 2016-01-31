<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="msg" uri="/struts-tags" %>
	<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<link rel="icon" href="../../favicon.ico">
    
    <title>登录界面</title>
    <!-- Bootstrap -->
     <link href="../../css/bootstrap.css" rel="stylesheet">
    <link href="../../css/main.css" rel="stylesheet" type="text/css">
    <link href="../../css/account.css" rel="stylesheet" type="text/css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
.container {
  width: 1120px;
}
a:link,a:visited{
 text-decoration:none;  /*超链接无下划线*/
}
a:hover{
	text-decoration: underline;
	
}
    </style>
    
    
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
				 if(!myreg.test(tel)){ 
					alert('请输入有效的手机号码！'); 
					return false; 
				 } 
			} 
	</script>
  
  </head>
  <body>
  <!--品牌-->
  <div class="container">
  <nav class="navbar navbar-default">
     
        
          <a class="navbar-brand" href="#">
            <img alt="logo" src="../../images/logo.jpg">
          </a>
  </nav>
  </div>
    <!--品牌end-->

    <!--body-->
   <div class="body">
    <div class="container">
 	 <div class="row account">           
            <div class="col-sm-8">    
                <img src="../../images/banner.png">
                      
            </div>
            <div class="col-sm-2">      
                <div class="panel panel-success">
                
                		
<div class="panel panel-default">
  <div class="panel-body">  

<!-- 马大洲--显示输入错误 -->
<div><font color="red">${loginerror}</font></div>

<form class="form-horizontal" action="/Activity/userLogin.action" method="post">
    <fieldset>
      <div id="legend" class="">
        <legend class="">招兼职</legend>
      </div> 

<div class="input-group">
  <span class="input-group-addon" id="basic-addon1"><img src="../../images/phone.png" ></span>
  <input type="text"  class="form-control" placeholder="请输入手机号码" aria-describedby="basic-addon1" id="telphone-login" name="telphone-login" onblur="yz()">
</div>
  
   <div class="input-group">
  <span class="input-group-addon" id="basic-addon1"><img src="../../images/passwd.png" ></span>
  <input type="password" class="form-control" placeholder="请输入密码" aria-describedby="basic-addon1" id="passwd-login"  name="passwd-login">
</div>
   
   <!--<div class="control-group">
          <div class="controls">
     
      <label class="checkbox">
        <input type="checkbox" value="记住我">
        记住我
      </label>
  </div>
        </div>-->
       <div class="loginin">
        	<div class="row clearfix">
	                <input type="checkbox" class="rememberBox fl" checked="checked"/>
	                <span class="remember fl">记住我</span>
	                <a  class="login" href="#"> 忘记密码</a>
	        </div>
	
          <!-- Button -->
           <div class="controls">
            <button class="btn btn-success" id="loginForm" onclick="checkData()">登录</button>
           </div>
		</div>
    </fieldset>
  </form>
<div  style="width:300px;text-align: center;">
 	 <span class="rg_row" style=" text-align: center; font-family: Tahoma, Geneva, sans-serif; font-size: 12px"><span>没有账号?</span>  <a class="regist_btn" style="padding-left:45px;" href="#">立即注册</a></span></div>
	</div>
</div>
                
                
                	
                </div>
            </div>
      </div>

	</div>
   </div>
    
    
    
    
    <!--bodyend-->
    <!--footer-->
    <footer class="main-footer">
    <div class="container">
        <div class="row">           
            <div class="col-sm-4">    
                <img src="../../images/footer.jpg">
                <p>©2015 iTree 版权所有.冀ICP备14013428号-1</p>       
            </div>
            <div class="col-sm-3">    
                 <h3 class="title" style="font-weight: 700;">客服热线</h3>
       			 <p class="num">4008-065-035</p>
            </div>
            <div class="col-sm-3">    
               <h3 class="title" style="font-weight:700;">关于我们</h3>
        <ul>
          <li>
            <a href="#">用户帮助</a>
          </li>
          <li>
            <a href="#">用户协议</a>
          </li>
          <li>
            <a href="#">关于我们</a>
          </li>
          
          <li>
            <a href="#">加入我们</a>
          </li>
        </ul>
            </div>
            <div class="col-sm-1">    
                <img src="../../images/weixin.jpg">
                 <p>扫码关注微信平台</p>       
            </div>
            
            
        </div>
    </div>
</footer>
    <!--footerend-->
    
    <!-- Bootstrap必须的 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- 和上面一起的 -->
    <script src="../../js/bootstrap.min.js"></script>
  </body>
</html>