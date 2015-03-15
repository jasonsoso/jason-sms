<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理_SMS</title>
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
			
				<form:form method="post" modelAttribute="user" id="form">
				<input type="hidden" name="_method" value="${_method }" />

				<h3 class="page-title">用户管理</h3>
				<div class="title-line"></div>
				<div class="databox">
					<div class="databox-title">用户管理&nbsp;（所有带有<span class="red" style="color: red;" >*</span>为必填项）</div>
					<div class="databox-body">
						<div class="changepwd formbox">
							<div class="form-group">
								<span class="form-title">用户名：</span>
								<form:input path="username" id="username" cssClass="required ipt ipt-size1" />
								<span class="red" style="color: red;" >*</span>
							</div>
							<div class="form-group">
								<span class="form-title">密码：</span>
								<input type="password" id="password" name="password" class="required ipt ipt-size1" />
								<span id="password-tip"><span class="red" style="color: red;" >*</span></span>
							</div>
							<div class="form-group">
								<span class="form-title">角色：</span>
								<select class="slt" id="roleId" name="roleId">
								<c:forEach var="role" items="${roles }">
									<c:choose>
										<c:when test="${user.roleId eq role.id}">
											<option selected="selected" value="${role.id }">${role.name }</option>
										</c:when>
										<c:otherwise>
											<option value="${role.id }">${role.name }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</select>
								<span class="red" style="color: red;" >*</span>
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
					location.href = "${ctx}/security/user/list/";
				});
				var currentUrl = document.URL;
				if(currentUrl && currentUrl.indexOf("edit") !== -1){
					$("#username").attr("readonly","readonly");
					$("#password").removeClass("required");
					//$("#password").rules("remove", { required: false});
					$("#password-tip").empty().append('<span class="red" style="color: red;" >（如不修改，请留空）</span>');
				}
			});
		</script>

</body>
</html>

