<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>文章管理</title>
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
			
				<form:form method="post" modelAttribute="article" id="form">
				<input type="hidden" name="_method" value="${_method }" />

				<h3 class="page-title">文章管理</h3>
				<div class="title-line"></div>
				<div class="databox">
					<div class="databox-title">文章管理&nbsp;（所有带有<span class="red" style="color: red;" >*</span>为必填项）</div>
					<div class="databox-body">
						<div class="changepwd formbox">
							<div class="form-group">
								<span class="form-title">标题：</span>
								<form:input path="title" cssClass="required ipt ipt-size1" />
							</div>
							
							<div class="form-group">
								<span class="form-title">所属分类：</span>
<select name = "categoryId">
	<c:forEach items="${categories }" var="category" varStatus="status">
		<c:choose>
			<c:when test="${category.id eq article.categoryId }">
				<option value="${category.id}" selected="selected"> ${category.name }</option>
			</c:when>
			<c:otherwise>
				<option value="${category.id}"> ${category.name }</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select> 
							</div>
							
							
							<%-- <div class="form-group">
								<span class="form-title">作者：</span>
								<form:input path="author" cssClass="required ipt ipt-size1" />
							</div> --%>
							<div class="form-group">
								<span class="form-title">封面：</span>
								<form:input path="cover" cssClass="ipt ipt-size1" />
								<button id="uploadBut" name="uploadBut" value="上传封面">上传封面</button>
							</div>
							
							<div class="form-group">
								<span class="form-title">内容：</span>
								<form:textarea path="content" rows="30" cols="120" cssClass="required" />
								<!--<span class="red" style="color: red;" >*</span>-->
							</div>
							<%-- <div class="form-group">
								<span class="form-title">状态：</span>
								<form:input path="status" cssClass="required ipt ipt-size1" />
								<!--<span class="red" style="color: red;" >*</span>-->
							</div> --%>
							
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
		<script src="${ctx}/resources/js/jquery.form.js" type="text/javascript"></script>
		
		<div id="elemDiv">
		 <form  id="fileuploadID" action="file-echo2.php" method="post" enctype="multipart/form-data">
	        <input type="file" name="fileToUpload" id="fileToUpload" /><br/>
	     </form>
		</div>
		<script type="text/javascript">
			$(function(){
				$("#form").validate(); 
				$("#back").click(function(){
					location.href = "${ctx}/cms/article/list/";
				});
				//点击上传弹出上传html
				$("#uploadBut").click(function(){
					var elemDiv = document.getElementById('elemDiv');
					var d = dialog({
					    title: '上传图片',
					    content: elemDiv,
					    width: 300,
					    okValue: '上传',
					    ok: function () {
					        //this.title('提交中…');
					        uploaduserphoto();
					        return false;
					    },
					    cancelValue: '取消',
					    cancel: function () {}
					});
					d.show();
				});
				//上传图片
				function uploaduserphoto(){
					 var img=null;
					 var imggeshi=fileuploadID.fileToUpload.value;
					 if(imggeshi==""){
						 alert("请选择图片文件!");
						 return false;
					 }
					 var point=imggeshi.lastIndexOf(".");
					 var imgtype=imggeshi.substr(point);
					 if(imgtype==".jpg" || imgtype==".gif" || imgtype==".png" 
						|| imgtype==".jpeg" || imgtype==".bmp"
						|| imgtype==".JPEG" || imgtype==".BMP" || imgtype==".JPG"
						|| imgtype==".GIF" || imgtype==".PNG"){
						 img=document.createElement("img");
						 if(img.fileSize>2048*1536){
							 alert("图片大小不能超过3M!");
							 return false;
						 }
					 }else{
						 alert("图片格式有误!支持上传格式为jpg,gif,png,jpeg,bmp");
						 return false;
					 }
					 //进行上传图片
					 alert("进行上传图片");
					
				 }
				
			});
		</script>
</body>
</html>
