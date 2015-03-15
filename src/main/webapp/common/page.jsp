<%@ page language="java"  pageEncoding="utf-8"%><%@include file="/common/taglibs.jsp" %>
<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="order" id="order" value="${page.order}" />

<div class="page">
	<div class="pagination pagination-large fr">
		<div class="totalnum fl">共${page.totalCount }条记录，第${page.pageNo }/${page.totalPages}页</div>
		<ul class="fl">
			<li><a href="javascript:health.jumpPage(1)">首页</a></li>
			<c:if test="${page.isHasPre}"><li><a href="javascript:health.jumpPage(${page.prePage })">« 上一页</a></li></c:if>
			<c:if test="${page.isHasNext}"><li><a href="javascript:health.jumpPage(${page.nextPage })">下一页 »</a></li></c:if>
			<li><a href="javascript:health.jumpPage(${page.totalPages })">末页</a></li>
		</ul>
		<div class="numgo fl">
			<span>第：<input type="text"  id="targetPage" name="targetPage" value="${page.pageNo}" class="numpage" onkeydown="if (event.keyCode === 13) {health.jumpTarget(document.getElementById('targetPage').value,${page.totalPages});}"> 页</span>
			<a href="javascript:void(0)" onClick="javascript:health.jumpTarget(document.getElementById('targetPage').value,${page.totalPages})" class="btn gobtn">GO</a>
		</div>
	</div>
</div>
