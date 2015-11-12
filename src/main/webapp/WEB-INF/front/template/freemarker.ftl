<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>

	<@cms_channel_list path='man';channels>
		<#list channels as channels>
			${channels.name}
			<#list channels.children as channel>
				${channel.name}
			</#list>
		</#list>
	</@cms_channel_list>
	
	
	<#assign x="test assign">
	定义变量使用：assign:<br>${x}
	<hr>

	处理日期时间数据：<br>
	${date?string("yyyy年MM月dd日 hh:mm")} <br>
	${date?datetime}
	
	<hr>
	处理布尔类型：<br>
	<#assign foo=false/>
	${foo?string("Yes","No")}
	<hr>
	freemarker if..elseif标签
	<br>
	<#if flag=="2">
		${flag} 是2
	<#elseif flag="3">
		${flag} 是3
	</#if>
	
<#list ["Joe", "Fred"] + ["Julia", "Kate"] as user>
- ${user}
</#list> 
	
	
	<hr>
	freemarker if..else标签
	<br>
	<#if flag=="2">
		${flag} 是2
	<#else>
		flag=${flag}
	</#if>
	<hr>用switch处理数据。flag是action传递过来的参数<br>
	
	<#switch flag>
		<#case "A">
        		<select>
        			<option value="1">胖子</option>
					<option value="2">胖子2</option>
					<option value="3">IT3</option>
					<option value="4">IT4</option>
        		</select>
      		<#break>
		<#case "B">
       		请输入<input type="text" id="" name=""/>
      		<#break>
		<#case "C">
      		C
      		<#break>
		<#case "2">
      		2
      		<#break>
		<#default>
     		 default      
	</#switch>
	<hr>
	处理List<br>
	<#list testList as t1>
		当前元素的下标：${t1_index}---内容是:${t1}<br>
	</#list>
	<hr>
	结合List和select下拉框<br>
	<select>
		<#list testList as t1>
			<option value="${t1_index}">${t1}</option>
		</#list>
	</select>
	
  </body>
</html>
