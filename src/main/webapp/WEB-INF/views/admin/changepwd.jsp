<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改账户密码_SMS</title>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common-header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<script src="${ctx }/resources/js/admin.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<!-- Header Start -->
		<%@include file="/common/header.jsp" %>
		<!-- Header End -->
		<!-- Container Start -->
		<div id="container">
			<%@include file="/common/left.jsp" %>
			<div id="main-content">
				<h3 class="page-title">修改账户密码</h3>
				<div class="title-line"></div>
				<form id="form" name="form" method="post" action="${ctx}/admin/changepwd/">
				<div class="databox">
					<div class="databox-title">修改账户密码&nbsp;（所有带有<span class="red" style="color: red;" >*</span>为必填项）</div>
					<div class="databox-body">
						<div class="changepwd formbox">
							<div class="form-group">
								<span class="form-title">原密码：</span>
								<input id="oldPassword" name="oldPassword" type="password" class="ipt ipt-size1"/>
								<span class="red" style="color: red;" >*</span>
							</div>
							<div class="form-group">
								<span class="form-title">新密码：</span>
								<input id="password" name="password" type="password" class="ipt ipt-size1"/>
								<span class="red" style="color: red;" >*</span>
							</div>
							<div class="form-group">
								<span class="form-title">确认密码：</span>
								<input id="confirmPassword" name="confirmPassword" type="password" class="ipt ipt-size1"/>
								<span class="red" style="color: red;" >*</span>
							</div>
							<input id="ok" type="submit" value="修改" class="btn" />&nbsp;
						</div>
					</div>
				</div>
				</form>
				<!-- <div class="adduser">
					<div class="databox">
						<div class="databox-title">子用户列表</div>
						<div class="databox-body">
							<table class="table">
								<thead>
									<tr><th>用户名</th><th>性别</th><th>手机号码</th><td>操作</td></tr>	
								</thead>
								<tbody>
									<tr><td>小林</td><td>女</td><td>136365366</td><td class="operate-td"><a href="#" class="do-btn edit-btn">修改</a><a href="#" class="do-btn del-btn">删除</a></td></tr>
								</tbody>
							</table>
						</div>
					</div>
				</div> -->
			</div>	
			</div>	
		</div>
		<!-- Container End -->
		<!-- Footer Start -->
		<%@include file="/common/footer.jsp" %>
		<!-- Footer End -->
		<script src="${ctx }/resources/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
		<script src="${ctx }/resources/js/jquery-validation/localization/messages_zh.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			$(function(){
				//提示信息：
				var type = "${message.type}";
				var text = "${message.text}";
				if(type && text){
					if(type == "error"){
						var d = dialog({
							title: '错误提示：',
						    content: text
						});
						d.show();
						setTimeout(function () {
						    d.close().remove();
						}, 2000);
						
					}else{
						var d = dialog({
							title: '温馨提示：',
						    content: text
						});
						d.show();
						setTimeout(function () {
						    d.close().remove();
						}, 2000);
						
					}
				}
				
				//验证
				$("#form").validate({
					rules: {
						oldPassword: {
							required: true,
							minlength: 5
						},
						password: {
							required: true,
							minlength: 5
						},
						confirmPassword: {
							required: true,
							minlength: 5,
							equalTo: "#password"
						}
					},
					messages: {
					}
				});

				
			});
		</script>

</body>
</html>
