﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix='s'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" /> 
<LINK rel="Bookmark" href="/favicon.ico">
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
	type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>管理员列表</title>
</head>
<body>
<%-- <%! int identity=1;%> --%>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a
		class="btn btn-success radius r mr-20"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20">
		<!--
		<div class="text-c">
			 日期范围： <input type="text"
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})"
				id="datemin" class="input-text Wdate" style="width: 120px;">
			- <input type="text"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})"
				id="datemax" class="input-text Wdate" style="width: 120px;">
			
			 <form action="" method="post">
			 <input type="text" class="input-text" style="width: 250px"
				placeholder="输入管理员名称" id="" name="">
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜用户
			</button>
			</form>
		</div>
		 -->
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<%-- <span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> --%>
			 <a href="javascript:;"onclick="admin_add('添加成员','admin-add.jsp','800','500')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加成员</a>
			</span> 
			<s:set id="users" value="users"></s:set>
			<span class="r">共有数据：<strong><s:property value="#request.user.size" /></strong> 条</span>
		</div>
		<table class="table table-border table-bordered table-bg">
			<thead>
				<tr>
					<th scope="col" colspan="9">成员列表</th>
				</tr>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="130">成员ID</th>
					<th width="40">姓名</th>
					<th width="40">职务</th>
					<th width="20">性别</th>
					<th width="130">密码</th>
					<th width="100">手机号码</th>
					<th width="40">是否已启用</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
			
			<s:iterator id="users" value="#request.user">
				<tr class="text-c">
					<td><input type="checkbox" value="" name=""></td>
					<td>${TUser.id }</td>
					<td>${TUser.userName }</td>
					<td>${TUser.TDuty.dutyName }</td>
					<%-- <s:if test="TUser.userSex==1">
					<td>男</td>
					</s:if>
					<s:elseif test="TUSer.userSex==0">
					<td>女</td>
					</s:elseif>--%>
					<td>${TUser.userSex }</td>	 
					<td>${password }</td>
					<td>${TUser.userTel }</td>
					
					<!--根据user的identity来选择相应的图标及信息  -->
				    <s:if test="TUser.userStatus==0">
				    <td class="td-status"><span class="label label-default radius">已停用</span></td>
					<td class="td-manage"><a  id = "${id}" style="text-decoration:none" onClick="admin_start(this,$(this).attr('id'))" 
					title="启用"><i class="Hui-iconfont">&#xe615;</i></a>
					
					<a title="编辑" href="javascript:;" 
					onclick="admin_edit('管理员编辑','user-edit?id=${id}',$(this).attr('id'),'800','500')" class="ml-5" style="text-decoration:none">
					<i class="Hui-iconfont">&#xe6df;</i></a> 
					
					<a title="删除" href="javascript:;" id="${id}" onclick="admin_del(this,$(this).attr('id'))" class="ml-5" style="text-decoration:none">
					<i class="Hui-iconfont">&#xe6e2;</i></a>
					</td>
					</s:if>
					<%-- id = "${UserID}" --%>
					<%-- id = "<s:property value="#u.UserID">" --%>
					<s:elseif test="TUser.userStatus==1">
				    <td class="td-status"><span class="label label-success radius">已启用</span></td>
					<td class="td-manage"><a  id = "${id}" style="text-decoration:none" onClick="admin_stop(this,$(this).attr('id'))"
					title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
				    
				    <a title="编辑" href="javascript:;" 
					onclick="admin_edit('管理员编辑','user-edit?id=${id}',$(this).attr('id'),'800','500')" class="ml-5" style="text-decoration:none">
					<i class="Hui-iconfont">&#xe6df;</i></a> 

					<a title="删除" href="javascript:;" id ="${id }" onclick="admin_del(this,$(this).attr('id'))" class="ml-5" style="text-decoration:none">
					<i class="Hui-iconfont">&#xe6e2;</i></a>
					</td>	
					</s:elseif>	
				</tr>
				
				<!-- 此处定义表达，目的是在js中调用相关的action方法。 -->			
				<!-- <form id="stop" action="userAction!stop.action" method="get">
				<input type="hidden" name="user1" value=<s:property value="#u.UserIdentity" /> >
				</form>	
				<form id="start" action="userAction!start.action?user1=u" method="get" ></form>		 -->	
			
			</s:iterator>			
					
				
			</tbody>
		</table>
			<%-- <s:debug></s:debug> --%>
	</div>
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/H-ui.js"></script>
	<script type="text/javascript" src="js/H-ui.admin.js"></script>
	<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$.ajax({
            url:"user-deleteById.action",
            data: {'delete':id},
            type:"get", 
           // success:function(data){//ajax返回的数据
           // } 
       }); 
		//此处请求后台程序，下方是成功后的前台处理……
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	//layer_show(title,url,w,h);
	var index = layer.open({
		type : 2,
		title : title,
		content : url
	});
	layer.full(index);
}
/*管理员-停用*/
function admin_stop(obj,id){
	//alert(id);
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
            url:"user-stop.action",
            data: {'stop':id},
            type:"get", 
           // success:function(data){//ajax返回的数据
           // } 
       }); 
		//此处请求后台程序，下方是成功后的前台处理……
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
		identity = 0;
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	//alert(id);
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
            url:"user-start.action",
            data: {'start':id},
            type:"get", 
           // success:function(data){//ajax返回的数据
           // } 
       }); 
		//此处请求后台程序，下方是成功后的前台处理……
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}
</script>
</body>
</html>