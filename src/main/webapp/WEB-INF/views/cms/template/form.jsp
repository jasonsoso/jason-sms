<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>模板管理</title>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common-header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css"/>
<script src="${ctx}/resources/js/admin.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/markitup/skins/markitup/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/markitup/sets/default/style.css" />

<style type="text/css">
.markItUp  {
	float: left;
}
</style>
</head>
<body>
<!-- Header Start -->
		<%@include file="/common/header.jsp" %>
		<!-- Header End -->
		<!-- Container Start -->
		<div id="container">
			<%@include file="/common/left.jsp" %>
			<div id="main-content">
			
				<form:form method="post" modelAttribute="template" id="form">
				<input type="hidden" name="_method" value="${_method }" />

				<h3 class="page-title">模板管理</h3>
				<div class="title-line"></div>
				<div class="databox">
					<div class="databox-title">模板管理&nbsp;（所有带有<span class="red" style="color: red;" >*</span>为必填项）</div>
					<div class="databox-body">
						<div class="changepwd formbox">
							<div class="form-group">
								<span class="form-title">模板名称：</span>
								<form:input path="name" cssClass="required ipt ipt-size1" maxlength="50"/>
								<!--<span class="red" style="color: red;" >*</span>-->
							</div>
							<div class="form-group">
								<span class="form-title">文件名称：</span>
								<form:input path="fileName" cssClass="required ipt ipt-size1" maxlength="50"/>
								<!--<span class="red" style="color: red;" >*</span>-->
							</div>
							<div class="form-group">
								<span class="form-title">物理路径：</span>
								<form:input path="physicalUrl" cssClass="required ipt ipt-size1" maxlength="255"/>
								<!--<span class="red" style="color: red;" >*</span>-->
							</div>
							<div class="form-group">
								<span class="form-title">排序：</span>
								<form:input path="sort" cssClass="required ipt ipt-size1" maxlength="10" />
								<!--<span class="red" style="color: red;" >*</span>-->
							</div>
							
							<div class="form-group">
								<span class="form-title">内容：</span>
								<form:textarea path="content" rows="30" cols="120" cssClass="required" />
								<!--<span class="red" style="color: red;" >*</span>-->
							</div>
							
							<input id="ok" type="submit" value="提交" class="btn" />&nbsp;
							<input id="back" type="button" value="返回" class="btn"/>
						</div>
					</div>
				</div>
				</form:form>
				
			</div>	
		</div>
		<!-- Container End -->
		<!-- Footer Start -->
		<%@include file="/common/footer.jsp" %>
		<!-- Footer End -->
		<script src="${ctx}/resources/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/jquery-validation/localization/messages_zh.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/markitup/jquery.markitup.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/markitup/sets/default/set.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			$(function(){
				
				$("#content").markItUp(mySettings);//初始化markItUp插件
				//校验
				$("#form").validate({
					rules: {
						sort: {
							required: true,
							digits: true
						}
					},
					messages: {
					}
				});
				//返回
				$("#back").click(function(){
					location.href = "${ctx}/cms/template/list/";
				});
			});
		</script>
</body>
</html>
