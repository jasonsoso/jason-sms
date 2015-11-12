<#include "/WEB-INF/front/template/taglibs.ftl" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>SMS</title>
		<meta name="keywords" content="SMS" />
		<meta name="description" content="SMS" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	 	 <#include "/WEB-INF/front/template/common-header.ftl" />
	</head>
	<body>
		<!-- Header Start -->
		<#include "/WEB-INF/front/template/navigation.ftl" />
		<!-- Header end -->
		<div class="cl"></div>
		<!-- Main Start -->
		<div id="main">
			
			<div class="navbar wrap">
				<ul>
					<li>
						<a href="/admin/login">
							<div class="tu tu1"><b></b></div>
							<h2>管理系统</h2>
						</a>
					</li>
					<li>
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
					</li>
				</ul>
			</div>

		</div>
		<!-- Main end -->
		<div class="cl"></div>
		<!-- Footer Start -->
	 	 <#include "/WEB-INF/front/template/footer.ftl" />
		<!-- Footer end -->
	</body>
</html>