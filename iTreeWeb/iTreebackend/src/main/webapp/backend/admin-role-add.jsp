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
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
	type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>新建网站角色</title>
</head>
<body>
	<div class="pd-20">
		<form action="role-add.action" method="post"
			class="form form-horizontal" id="form-user-character-add">
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>角色名称：</label>
				<div class="formControls col-10">
					<input type="text" class="input-text" placeholder="" id=""
						name="tduty.dutyName" datatype="*4-16" nullmsg="角色名不能为空">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">备注：</label>
				<div class="formControls col-10">
					<input type="text" class="input-text" placeholder="" id=""
						name="tduty.profile">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">网站角色：</label>
				<div class="formControls col-10">
					<dl class="permission-list">
						<dt>
							<label> <input type="checkbox" name="permission"
								id="user-Character-0" value="1"> 资讯管理
							</label>
						</dt>
						<dd>
							<dl class="cl permission-list2">
								<dt>
									<label class=""> <input type="checkbox" value="6"
										name="permission" id="user-Character-0-0"> 栏目管理
									</label>
								</dt>
								<dd>
									<label class=""> <input type="checkbox" value="14"
										name="permission" id="14"> 添加
									</label> <label class=""> <input type="checkbox" value="15"
										name="permission" id="15"> 修改
									</label> <label class=""> <input type="checkbox" value="16"
										name="permission" id="16"> 删除
									</label> <label class=""> <input type="checkbox" value="17"
										name="permission" id="17"> 查看
									</label> <label class=""> <input type="checkbox" value="18"
										name="permission" id="18"> 审核
									</label> <label class="c-orange"><input type="checkbox"
										value="19" name="permission" id="19"> 只能操作自己发布的</label>
								</dd>
							</dl>
						</dd>
					</dl>
					<dl class="permission-list">
						<dt>
							<label> <input type="checkbox" name="permission" value="2"
								id="user-Character-1"> 图片管理
							</label>
						</dt>
						<dd>
							<dl class="cl permission-list2">
								<dt>
									<label class=""> <input type="checkbox" value="7"
										name="permission" id="user-Character-1-0"> 图片管理
									</label>
								</dt>
								<dd>
									<label class=""> <input type="checkbox" value="20"
										name="permission" id="20"> 添加
									</label> <label class=""> <input type="checkbox" value="21"
										name="permission" id="21"> 修改
									</label> <label class=""> <input type="checkbox" value="22"
										name="permission" id="22"> 删除
									</label> <label class=""> <input type="checkbox" value="23"
										name="permission" id="23"> 查看
									</label> <label class=""> <input type="checkbox" value="24"
										name="permission" id="24"> 审核
									</label><label class="c-orange"><input type="checkbox"
										value="25" name="permission" id="25"> 只能操作自己发布的</label>
								</dd>
							</dl>
						</dd>
					</dl>
					<dl class="permission-list">
						<dt>
							<label> <input type="checkbox" name="permission" value="3"
								id="user-Character-1"> 评论管理
							</label>
						</dt>
						<dd>
							<dl class="cl permission-list2">
								<dt>
									<label class=""> <input type="checkbox" value="8"
										name="permission" id="user-Character-1-0"> 评论列表
									</label>
								</dt>
								<dd>
									<label class=""> <input type="checkbox" value="26"
										name="permission" id="26"> 添加
									</label> <label class=""> <input type="checkbox" value="27"
										name="permission" id="27"> 修改
									</label> <label class=""> <input type="checkbox" value="28"
										name="permission" id="28"> 删除
									</label> <label class=""> <input type="checkbox" value="29"
										name="permission" id="29"> 查看
									</label> <label class=""> <input type="checkbox" value="30"
										name="permission" id="30"> 审核
									</label>
								</dd>
							</dl>
							<dl class="cl permission-list2">
								<dt>
									<label class=""> <input type="checkbox" value="9"
										name="permission" id="user-Character-1-0"> 意见反馈
									</label>
								</dt>
								<dd>
									<label class=""> <input type="checkbox" value="31"
										name="permission" id="31"> 修改
									</label> <label class=""> <input type="checkbox" value="32"
										name="permission" id="32"> 删除
									</label> <label class=""> <input type="checkbox" value="33"
										name="permission" id="33"> 查看
									</label>
								</dd>
							</dl>
						</dd>
					</dl>
					<dl class="permission-list">
						<dt>
							<label> <input type="checkbox" name="permission" value="4"
								id="user-Character-1"> 管理员管理
							</label>
						</dt>
						<dd>
							<dl class="cl permission-list2">
								<dt>
									<label class=""> <input type="checkbox" value="10"
										name="permission" id="user-Character-1-0"> 角色管理
									</label>
								</dt>
								<dd>
									<label class=""> <input type="checkbox" value="34"
										name="permission" id="34"> 添加
									</label> <label class=""> <input type="checkbox" value="35"
										name="permission" id="35"> 修改
									</label> <label class=""> <input type="checkbox" value="36"
										name="permission" id="36"> 删除
									</label> <label class=""> <input type="checkbox" value="37"
										name="permission" id="37"> 查看
									</label>
								</dd>
							</dl>
							<dl class="cl permission-list2">
								<dt>
									<label class=""> <input type="checkbox" value="11"
										name="permission" id="user-Character-1-0"> 权限管理
									</label>
								</dt>
								<dd>
									<label class=""> <input type="checkbox" value="38"
										name="permission" id="38"> 添加
									</label> <label class=""> <input type="checkbox" value="39"
										name="permission" id="39"> 修改
									</label> <label class=""> <input type="checkbox" value="40"
										name="permission" id="40"> 删除
									</label> <label class=""> <input type="checkbox" value="41"
										name="permission" id="41"> 查看
									</label>
								</dd>
							</dl>
							<dl class="cl permission-list2">
								<dt>
									<label class=""> <input type="checkbox" value="12"
										name="permission" id="user-Character-1-0"> 管理员管理
									</label>
								</dt>
								<dd>
									<label class=""> <input type="checkbox" value="42"
										name="permission" id="42"> 添加
									</label> <label class=""> <input type="checkbox" value="43"
										name="permission" id="43"> 修改
									</label> <label class=""> <input type="checkbox" value="44"
										name="permission" id="44"> 删除
									</label> <label class=""> <input type="checkbox" value="45"
										name="permission" id="45"> 查看
									</label><label class="c-orange"><input type="checkbox"
										value="46" name="permission" id="46"> 只能操作自己发布的</label>
								</dd>
							</dl>
						</dd>
					</dl>
					<dl class="permission-list">
						<dt>
							<label> <input type="checkbox" name="permission" value="13"
								id="user-Character-1"> 系统管理
							</label>
						</dt>
						<dd>
							<dl class="cl permission-list2">
								<dt>
									<label class=""> <input type="checkbox" value="47"
										name="permission" id="47"> 系统管理
									</label>
								</dt>
							</dl>
						</dd>
					</dl>
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<button type="submit" onclick="checked()" class="btn btn-success radius"
						id="admin-role-save" name="admin-role-save">
						<i class="icon-ok"></i> 确定
					</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/H-ui.js"></script>
	<script type="text/javascript" src="js/H-ui.admin.js"></script>
	<script>
		$(function() {
			$(".permission-list dt input:checkbox").click(
					function() {
						$(this).closest("dl").find("dd input:checkbox").prop(
								"checked", $(this).prop("checked"));
					});
			$(".permission-list2 dd input:checkbox")
					.click(
							function() {
								var l = $(this).parent().parent().find(
										"input:checked").length;
								var l2 = $(this).parents(".permission-list")
										.find(".permission-list2 dd").find(
												"input:checked").length;
								if ($(this).prop("checked")) {
									$(this).closest("dl").find(
											"dt input:checkbox").prop(
											"checked", true);
									$(this).parents(".permission-list").find(
											"dt").first()
											.find("input:checkbox").prop(
													"checked", true);
								} else {
									if (l == 0) {
										$(this).closest("dl").find(
												"dt input:checkbox").prop(
												"checked", false);
									}
									if (l2 == 0) {
										$(this).parents(".permission-list")
												.find("dt").first().find(
														"input:checkbox").prop(
														"checked", false);
									}
								}

							});
		});

		/* //获取选中的checkbox的值
		function checked() {
			//js获取复选框值    
			var obj = document.getElementsByName("permission");//选择所有name="permission"的对象，返回数组    
			var s = '';//如果这样定义var s;变量s中会默认被赋个null值
			for (var i = 0; i < obj.length; i++) {
				if (obj[i].checked) //取到对象数组后，我们来循环检测它是不是被选中
					s += obj[i].value + ','; //如果选中，将value添加到变量s中    
			}
			alert("执行完毕！！");
			return s;
		} */
	</script>
</body>
</html>