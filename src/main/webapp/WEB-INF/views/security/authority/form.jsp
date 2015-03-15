<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户权限管理_SMS</title>
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
			
				<form:form method="post" modelAttribute="authority" id="form">
				<input type="hidden" name="_method" value="${_method }" />

				<h3 class="page-title">用户权限管理</h3>
				<div class="title-line"></div>
				<div class="databox">
					<div class="databox-title">用户权限管理&nbsp;（所有带有<span class="red" style="color: red;" >*</span>为必填项）</div>
					<div class="databox-body">
						<div class="changepwd formbox">
							<div class="form-group">
								<span class="form-title">名称：</span>
								<form:input path="name" cssClass="required ipt ipt-size1" />
								<span class="red" style="color: red;" >*</span>
							</div>
							<div class="form-group">
								<span class="form-title">权限：</span>
								<form:input path="permission" cssClass="required ipt ipt-size1" />
								<span class="red" style="color: red;" >*</span>
								&nbsp;（如：channel:list）
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
		<script src="${ctx }/resources/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
		<script src="${ctx }/resources/js/jquery-validation/localization/messages_zh.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			$(function(){
				$("#form").validate(); 
				$("#back").click(function(){
					location.href = "${ctx}/security/authority/list/";
				});
			});
		</script>

</body>
</html>
