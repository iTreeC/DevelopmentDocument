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
<title>æ·»å äº§ååç±»</title>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="form-user-add">
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>åç±»åç§°ï¼</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="" placeholder=""
						id="user-name" name="product-category-name">
				</div>
				<div class="col-5"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">å¤æ³¨ï¼</label>
				<div class="formControls col-5">
					<textarea name="" cols="" rows="" class="textarea"
						placeholder="è¯´ç¹ä»ä¹...æå°è¾å¥10ä¸ªå­ç¬¦"
						datatype="*10-100" dragonfly="true"
						nullmsg="å¤æ³¨ä¸è½ä¸ºç©ºï¼" onKeyUp="textarealength(this,100)"></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/100
					</p>
				</div>
				<div class="col-5"></div>
			</div>
			<div class="row cl">
				<div class="col-9 col-offset-2">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;æäº¤&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</div>
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
	
	$("#form-user-add").Validform({
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