<p>栏目：<a href="${ctx}/">首页</a>
	 <@cms_channel_list path='man';channels>
	 	 <#list channels as channels>
		 	 <a href="${ctx}${channels.path}">${channels.name}</a>
		 	 <#if channels.children?size gt 0>
		 	 	 [<#list channels.children as channel>
		 	 	 <a href="${ctx}${channel.path}">${channel.name}</a>
		 	 	 </#list>]
		 	 </#if>
		 </#list>
	</@cms_channel_list>
</p>