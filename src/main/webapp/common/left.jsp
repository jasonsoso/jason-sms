<%@ page language="java" import="java.util.*,com.jason.sms.util.shiro.MybatisShiroRealm.*,com.jason.sms.util.shiro.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp" %>
<div id="sidebar">
	<ul>
	   <li><a href="${ctx}/security/runas/list/"><b class="icon-box icon3"></b>切换身份</a></li>
	     <shiro:hasPermission name="admin:changepwd">
		  <li><a href="${ctx}/admin/changepwd/"><b class="icon-box icon3"></b>修改账户密码</a></li>
		</shiro:hasPermission>
		<li>
			<shiro:hasRole name="admin">
			<a href="javascript:void(0);"><b class="icon-box icon7"></b>系统管理</a>
			<ul class="sub" style="display: block;">
                 <li><a class="" href="${ctx}/security/user/list/">用户管理</a></li>
                 <li><a class="" href="${ctx}/security/role/list/">角色管理</a></li>
                 <li><a class="" href="${ctx}/security/authority/list/">权限管理</a></li>
             </ul>
             <b class="down"></b>
             </shiro:hasRole>
		</li>
					
	</ul>
</div>


<script type="text/javascript">
$(function(){
	alertleft();
	function alertleft(){
		var allwidth=$(window).width();
		var alertwidth=$(".alert").width();
		var leftwith=allwidth-alertwidth;
		$(".alert").css("left",leftwith/2);
	}

	$(window).resize(function(){
		alertleft();
	});
	/*模拟checkbox*/
	$(".checker span").click(function(){
		$(this).toggleClass("checked");
	});
	/*导入弹出框*/
	$(".export-btn").click(function(){
		$("#fullbg").show();
		$("#export-file").show();
	});
	/*模拟上传文件*/
	$("input[type=file]").change(function(){$(this).parents(".uploader").find(".filename").val($(this).val());});
	$("input[type=file]").each(function(){
	if($(this).val()==""){$(this).parents(".uploader").find(".filename").val("您还没有选择文件...");}
	});

});
</script>