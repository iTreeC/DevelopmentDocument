<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<link rel="icon" href="../../favicon.ico">
    
    <title>注册界面</title>
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
    <div class="container" >
 	 <div class="row account"style="width=1366px;">           
            <div class="col-sm-8">    
                <img src="../../images/banner2.png">
                      
            </div>
            <div class="col-sm-2">      
                <div class="panel panel-success">
                
                		
<div class="panel panel-default">
  <div class="panel-body">  

<form class="form-horizontal" action="/Activity/addUser.action" method="post">
    <fieldset>
      <div id="legend" class="">
        <legend class="titlel">招兼职</legend>
      </div>
 
 
 <table width="300" border="0" cellpadding="15px" class="hang" >
  <tr>
    <td width="100">手机号码:</td>
    <td height="25" colspan="2"><input type="text" class="form-control" placeholder="请输入手机号码" aria-describedby="sizing-addon1" id="telphone-register" name="telphone-register" onblur="yz()"></td>
  </tr>
  <tr>
    <td>图片验证码:</td>
    <td width="150"><input  style="120px!important" type="text" class="form-control" placeholder="请输入验证码" aria-describedby="sizing-addon1" id="validateCode" name="validateCode"></td>
    <td width="100"><div style="margin:auto; text-align:center"><img src="checkCode.action" id="showvalidateCode" name="showvalidateCode" onclick ="reloadcheckcode()" title="看不清，请点击图片，换一张"/></div></td>
  </tr>
  <!-- <tr>
    <td>短信验证码:</td>
    <td><input  style="120px!important;"type="text" class="form-control" placeholder="请输入验证码" aria-describedby="sizing-addon1"></td>
    <td><div style="margin:auto; text-align:center"> <button  style="width:60px"class="btn btn-success">获取</button></div></td>
  </tr> -->
  <tr>
    <td>登录密码:</td>
    <td colspan="2"><input type="password" class="form-control" placeholder="请输入登录密码" aria-describedby="sizing-addon1" id="passwd1" name="passwd1"></td>
  </tr>
  <tr>
    <td>确认密码:</td>
    <td colspan="2"><input type="password" class="form-control" placeholder="请输入确认密码" aria-describedby="sizing-addon1" id="passwd-register" name="passwd-register"></td>
  </tr>
</table>
 
       <div  style="padding-top:15px"class="loginin">
        	<div class="row clearfix">
	                <input type="checkbox" class="rememberBox fl" checked="checked"/>
	                <span class="remember fl">我已阅读并同意</span>
	                <a href="#"> 《商家服务协议》</a>
	        </div>
	
          <!-- Button -->
           <div class="controls">
            <button class="btn btn-success" onclick="checkData()" >注册</button>
           </div>
		</div>
    </fieldset>
  </form>
<div  style="width:300px;text-align: center;">
 	 <span class="rg_row" style=" text-align: center; font-family: Tahoma, Geneva, sans-serif; font-size: 12px"><span>已有账号?</span>  <a class="regist_btn" style="padding-left:45px;" href="#">立即登录</a></span></div>
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