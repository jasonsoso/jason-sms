<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>主页_SMS</title>
		<%@include file="/common/taglibs.jsp" %>
		<%@include file="/common/common-header.jsp" %>
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
	</head>
	<body>
		<!-- Header Start -->
		<%@include file="/common/header.jsp" %>
		<!-- Header End -->
		<!-- Container Start -->
		<div id="container">
			<%@include file="/common/left.jsp" %>
			<div id="main-content">
			
			
				<h3 class="page-title">首页</h3>
				<div class="title-line"></div>
				<div class="databox">
					<div class="databox-title">系统信息&nbsp;</div>
					<div class="databox-body">
						<div class="changepwd formbox">
							<div class="form-group">
								<p id="current">当前时间：</p>
							</div>
							
							<li>操作系统：${props['os.name'] }</li>
							<li>操作系统版本：${props['os.version'] }</li>
							<li>JAVA运行时环境版本：${props['java.version'] }</li>
							<li>后台管理系统：health ${version}</li>
							</ul>

						</div>
					</div>
				</div>
				
			
			
			
			
			</div>	
		</div>
		<!-- Container End -->
		<!-- Footer Start -->
		<%@include file="/common/footer.jsp" %>
		<!-- Footer End -->
<%
	long current = System.currentTimeMillis();
%>
<script src="${ctx }/resources/js/jquery-timer.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		var current = <%= current %>;
		$("#current").everyTime("1s", "updateTimer", function(){
			current += 1000;
			var date = new Date();
			date.setTime(current);
			$("#current").empty().append("当前时间：" + date.toLocaleString());
		});
	});
</script>

	</body>
</html>