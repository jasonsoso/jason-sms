<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统管理员登录_SMS</title>

<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common-header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/loginpage.css">
<script type="text/javascript">
$(function(){
	$("#username").focus();
	
	var showError = function(msg){
		$("#error").empty().append(msg);
	};
	
	var code = "${param.code}";
	if(code){
		try{
			var codeAsInt = parseInt(code , 10);
			switch(codeAsInt){
			case 1:
				showError("无效的用户名密码！");
				break;
			case 2:
				showError("该用户已被锁定！");
				break;
			case 4:
				showError("无效的用户名密码！");
				break;
			case 8:
				showError("验证码错误！");
				break;
			default:
				showError("未知错误！");
				break;
			}
		}catch(e){
			showError("未知错误！");
		}
	};
	$("#captcha").click(function(){
		changeImg()
	});
	$("#captchaBtn").click(function(){
		changeImg();
	});
	function changeImg(){
		var captchaUrl = "${ctx}/captcha/?" + Math.random().toString();
		$("#captcha").attr("src", captchaUrl);
		$("#captchaText").focus();
	}
});
</script>
</head>
<body>

<div id="jg-login" class="login">
	<div class="login-inner">
			<div class="login-user">
					<ul>
						<li><a href="${ctx}/">首页</a></li>
						<li class="usercheck"><a href="javascript:void(0);">系统管理员登录</a></li>
					</ul>
			</div>
			<div class="login-title"><h1 class="logo">SMS</h1></div>
			<div class="loginbox formbox">
			<form action="${ctx }/admin/login" method="post">
				<div class="login-l fl">
					<div id="error" name="error"></div>
					<div class="form-group">
						<span class="form-title">用户名：</span>
						<input id="username" type="text" name="username" maxlength="20" size="3" class="ipt ipt-size1"/>
					</div>
					<div class="form-group">
						<span class="form-title">密&nbsp;&nbsp;&nbsp;码：</span>
						<input type="password" name="password" maxlength="20" size="3" class="ipt ipt-size1" />
					</div>
					<div class="form-group">
						<p><span class="form-title">验证码：</span>
						<input autocomplete = "off" type="text" id="captchaText" name="captcha" maxlength="20" size="3" class="ipt ipt-size1" /></p>
						<p><img id="captcha" src="${ctx }/captcha/" style="cursor:pointer;" /><a id="captchaBtn" class="changecode" title="点击刷新验证码"></a></p>
					</div>
					<input id="sub" class="btn login-btn" type="submit" value="登  录" name="">
				</div>
				<div class="login-r fl">
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
