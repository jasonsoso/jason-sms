<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>频道管理</title>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common-header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css"/>
<script src="${ctx}/resources/js/admin.js" type="text/javascript"></script>
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
			
				<form:form method="post" modelAttribute="channel" id="form">
				<input type="hidden" name="_method" value="${_method }" />

				<h3 class="page-title">频道管理</h3>
				<div class="title-line"></div>
				<div class="databox">
					<div class="databox-title">频道管理&nbsp;（所有带有<span class="red" style="color: red;" >*</span>为必填项）</div>
					<div class="databox-body">
						<div class="changepwd formbox">
							<div class="form-group">
								<span class="form-title">所属分类：</span>
<select name = "belongChannel">  
	<c:choose>
		<c:when test="${empty channel.father.id }">
			<option value="" selected>一级目录</option>
			<c:forEach items="${channelNames }" var="names">
				<c:if test="${empty names.father.id }">
					<option value="${names.id }">${names.name }</option>
					<c:forEach items="${names.children }" var="child">
						<option value="${child.id}" >- - - -${child.name }</option>
					</c:forEach>
				</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<option value="" >一级目录</option>
			<c:forEach items="${channelNames }" var="names">
				<c:if test="${empty names.father.id }">
					<c:choose>
						<c:when test="${channel.father.id eq names.id }">
							<option value="${names.id }" selected>${names.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${names.id }" >${names.name }</option>
						</c:otherwise>
					</c:choose>
					<c:forEach items="${names.children }" var="child">
						<option value="${child.id}" >- - - -${child.name }</option>
					</c:forEach>
				</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</select>
							</div>
							
							<div class="form-group">
								<span class="form-title">栏目名称：</span>
								<form:input path="name" cssClass="required ipt ipt-size1" />
							</div>
							<div class="form-group">
								<span class="form-title">栏目路径：</span>
								<form:input path="path" cssClass="required ipt ipt-size1" />
							</div>
							<div class="form-group">
								<span class="form-title">栏目排序：</span>
								<form:input path="sort" cssClass="required ipt ipt-size1" />
							</div>
							<div class="form-group">
								<span class="form-title">所属模板：</span>
<select name = "belongTemplate">
	<c:forEach items="${template }" var="templates" varStatus="status">
		<c:choose>
			<c:when test="${templates.id eq channel.template.id }">
				<option value="${templates.id}" selected="selected"> ${templates.name }</option>
			</c:when>
			<c:otherwise>
				<option value="${templates.id}"> ${templates.name }</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select> 
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
		
		<script type="text/javascript">
			$(function(){
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
				$("#back").click(function(){
					location.href = "${ctx}/cms/channel/list/";
				});
			});
		</script>
</body>
</html>
