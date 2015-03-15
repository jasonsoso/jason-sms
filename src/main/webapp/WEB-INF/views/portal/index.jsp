<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>SMS</title>
		<meta name="keywords" content="SMS" />
		<meta name="description" content="SMS" />
		<%@include file="/common/common-header.jsp" %>
		<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/index.css"/>
	</head>
	<body>
		<!-- Header Start -->
		<div id="header" class="bluebg">
			<div class="wrap">
				<h1 class="logo fl">SMS</h1>
			</div>
		</div>
		<!-- Header end -->
		<div class="cl"></div>
		<!-- Main Start -->
		<div id="main">
			<div class="dlblock">
				
					<!-- 全屏轮播图 Start-->
					<div id="kinMaxShow">
				    	<div><a><img src="${ctx }/resources/images/tc1.jpg"/></a></div>
				        <div><a><img src="${ctx }/resources/images/tc2.jpg"/></a></div>
				        <div><a><img src="${ctx }/resources/images/tc4.jpg"/></a></div>   	   		            
				    </div>
				    <!-- 全屏轮播图 End-->
				
			</div>
			<div class="navbar wrap">
				<ul>
					<li>
						<a href="${ctx }/admin/login">
							<div class="tu tu1"><b></b></div>
							<h2>管理系统</h2>
						</a>
					</li>
					<!-- <li>
						<a href="javascript:void(0);">
							<div class="tu tu2"><b></b></div>
							<h2>学校统计分析</h2>
						</a>
					</li>
					<li>
						<a href="javascript:void(0);">
							<div class="tu tu3"><b></b></div>
							<h2>家长报告查询</h2>
						</a>
					</li> -->
				</ul>
			</div>

		</div>
		<!-- Main end -->
		<div class="cl"></div>
		<!-- Footer Start -->
		<div id="footer">
			<div class="wrap">
				<!-- <p><img src="${ctx }/resources/images/jllogo.png" alt="家联" title="家联"></p> -->
			  	<p>Copyright © 2015 Jason 版权所有.</p>
			  	<!-- <p>粤ICP备14050083号</p> -->
			</div>
		</div>
		<!-- Footer end -->
		<!-- 首屏轮播js start -->
		<script type="text/javascript" src="${ctx }/resources/js/jquery.kinMaxShow-1.0.min.js"></script>
		<script type="text/javascript">
			$(function(){	
				$("#kinMaxShow").kinMaxShow();
			});
		</script>
		<!-- 首屏轮播js  end -->
	</body>
</html>