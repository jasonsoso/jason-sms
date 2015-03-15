<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@include file="/common/taglibs.jsp" %>
<div id="header" class="navbar">
	<div class="navbar-inner">
		<h1 class="logo fl">SMS</h1>
		<div class="userinfo fr"><span class="name">欢迎您，<shiro:principal/></span><a href="javascript:void(0);" id="exit" class="exit">退出</a></div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#exit").click(function(){
			if(confirm("你确定要退出系统吗?")){
				location.href = "${ctx}/admin/logout/";
			}
		});
	});
</script>