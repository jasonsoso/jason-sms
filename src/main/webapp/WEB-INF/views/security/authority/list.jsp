<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户权限管理_SMS</title>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common-header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css" />
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
			<form id="myForm" action="${ctx }/security/authority/list/" method="get">
				<h3 class="page-title">用户权限管理</h3>
				<div class="title-line"></div>
				<div class="cl"></div>
				<div class="searchbar">
					<div class="search-group fl">
						<span>名称：</span>
						<input class="ipt ipt-size1" name="params[name]" value="${page.params.name }" type="text" />
					</div>
					<div class="search-group fl">
						<input class="btn" type="button" onclick="javascript:health.search();" value="查询" />
					</div>
				</div>

				<div class="class-info">
					<div class="databox">
						<div class="databox-title">用户权限管理&nbsp;
						
						</div>
						<div class="databox-body">
							<div class="searchbar mar2">
								<input class="btn" id="add" type="button" value="新增" />
								<input class="btn" id="del" type="button" value="删除" />
							</div>
							<table class="table table-bordered">
								<thead>
									<tr class="titletr">
										<th class="checktd"><div class="checker"><span><input type="checkbox"  id="selectAndUnselect" class="group-checkable checkboxes" value="1"/></span></div></th>
										<th>名称</th><th>权限</th><th>操作</th></tr>	
								</thead>
								<tbody>
									  <c:choose>
									  	<c:when test="${not empty page.result }">
										  	<c:forEach items="${page.result }" var="authority">
										  	<tr class="even">
											    <td class="checktd">
											    	<div class="checker">
											    	<span><input type="checkbox" name="items" id="checkbox" value="${authority.id}" class="group-checkable" /></span>
											    	</div>
											    </td>
											    <td>${authority.name}</td>
											    <td>${authority.permission }</td>
											    <td>
													<a href="${ctx }/security/authority/${authority.id}/edit/"  class="do-btn edit-btn"><span>编辑</span></a>
													
											    </td>
										  	</tr>
										  	</c:forEach>
									  	</c:when>
									  	<c:otherwise>
											<tr><td colspan="4" align="center"><b>暂无内容</b></td></tr>
									  	</c:otherwise>
									  </c:choose>
									  
								</tbody>
							</table>
							<!-- Page Start-->
							<jsp:include page="/common/page.jsp" />
						</div>
					</div>
				</div>
				</form>
			</div>	
		</div>
		<!-- Container End -->
		<!-- Footer Start -->
		<%@include file="/common/footer.jsp" %>
		<!-- Footer End -->
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
		$("#del").click(function(){
			var items = health.select();
			if(items && items.length <=0){
				alert("请先选择要删除的内容");
				return false;
			}
			if(confirm("你确定要删除这些内容吗?")){
				$('input[name="_method"]').remove();
				$("#myForm").attr("action", "${ctx}/security/authority/delete/")
							.attr("method","post")
							.append('<input type="hidden" name="_method" value="DELETE" />')
							.submit();
				return false;
			}
		});
		$("#add").click(function(){
			location.href = "${ctx}/security/authority/create/";
		});
	});
</script>

</body>
</html>
