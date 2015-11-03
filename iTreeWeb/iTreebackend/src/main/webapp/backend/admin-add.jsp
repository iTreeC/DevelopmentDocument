<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
	type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加成员</title>
</head>
<body>
	<div class="pd-20">
		<form action="userAction!userAdd.action" method="post" class="form form-horizontal" id="form-admin-add">
			
			
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>成员ID</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" placeholder=""
						id="UserID" name="user.UserID" datatype="*2-16" nullmsg="成员不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>姓名</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" placeholder=""
						id="UserName" name="user.UserName" datatype="*2-6" nullmsg="姓名不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-5 skin-minimal">
				<div class="radio-box">
					<input type="radio" id="1" name="sex" datatype="*" nullmsg="请选择性别！">
					<label for="1">男</label>
				</div>
				<div class="radio-box">
					<input type="radio" id="2" name="sex">
					<label for="0">女</label>
				</div>
			</div>
			<div class="col-4"> </div>
			</div>
			
			
			<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>初始密码：</label>
			<div class="formControls col-5">
				<input type="password" placeholder="密码" autocomplete="off" value="" class="input-text" datatype="*6-20" nullmsg="密码不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		<!-- <div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-5">
				<input type="password" placeholder="确认新密码" autocomplete="off" class="input-text Validform_error" errormsg="您两次输入的新密码不一致！" datatype="*" nullmsg="请再输入一次新密码！" recheck="newpassword" id="newpassword2" name="newpassword2">
			</div>
			<div class="col-4"> </div>
		</div>
			 -->
			
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" placeholder=""
						id="UserTel" name="user.UserTel" datatype="m" nullmsg="手机不能为空">
				</div>
				<div class="col-4"></div>
			</div>
			
			<!-- <div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>邮箱：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" placeholder="@" name="email"
						id="email" datatype="e" nullmsg="请输入邮箱！">
				</div>
				<div class="col-4"></div>
			</div> -->
			<div class="row cl">
				<label class="form-label col-3">座右铭</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" id="UserMotto" name="user.UserMotto" placeholder="20个字符以内" dragonfly="true"
						onKeyUp="textarealength(this,20)">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3">爱好</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" id="UserHoby" name="user.UserHoby">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3">照片</label>
				<div class="formControls col-5">
					<input type="file" value="" id="PhotoID" name="user.PhotoID">
				</div>
				<div class="col-4"></div>
			</div>			
			
			<div class="row cl">
				<label class="form-label col-3">角色：</label>
				<div class="formControls col-5">
					<span class="select-box" style="width: 150px;"> <select
						class="select" name="user.DutyID" size="1">
						    <option value="D4">学生</option>
							<option value="D1">数据库管理员</option>
							<option value="D2">导师</option>
							<option value="D3">助教</option>
							<option value="D5">TEST</option>
					</select>
					</span>
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3">简介：</label>
				<div class="formControls col-5">
					<textarea name="user.UserProfile" cols="" rows="" class="textarea"
						placeholder="说点什么...100个字符以内" dragonfly="true"
						onKeyUp="textarealength(this,100)"></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/100
					</p>
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3">是否可用：</label>
				<div class="formControls col-5">
					<span class="select-box" style="width: 150px;"> <select
						class="select" name="user.UserIdentify" size="1">
							<option value="1">可用</option>
							<option value="0">不可用</option>
					</select>
					</span>
				</div>
			</div>
			
			<div class="row cl">
				<div class="col-9 col-offset-3">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="js/H-ui.js"></script>
	<script type="text/javascript" src="js/H-ui.admin.js"></script>
	<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-add").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});
</script>
</body>
</html>