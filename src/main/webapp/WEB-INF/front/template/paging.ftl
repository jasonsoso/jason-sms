<#assign totalPages="${(page.totalPages gt 2000)?string('2000',page.totalPages)}" >
<div class="global-page">
<#if totalPages?number gt 1>
	<#if (page.pageNo?number) gt 1>
		<a class="previous-page" href="${pagingUrl}/page/${((page.pageNo?number-1) gt 0)?string(page.pageNo?number-1,page.pageNo?number)}/${page.orderBy}/${page.order}">上一页</a>
	</#if>
	<#if page.pageNo?number == 1>
		<a class="previous-page" href="${pagingUrl}/page/1/${page.orderBy}/${page.order}">1</a>&nbsp;
	<#else>
		<a href="${pagingUrl }/page/1/${page.orderBy}/${page.order}">1</a>&nbsp;
	</#if>
	<#if totalPages?number gt 0>
		<#if page.pageNo?number lt 10>
			<#if page.pageNo?number gte 2>
			<#list 2..page.pageNo?number as i>
				<#if page.pageNo?number==i>
					<a class="previous-page" href="${pagingUrl }/page/${i}/${page.orderBy}/${page.order}">${i}</a>&nbsp;
				<#else>
					<a href="${pagingUrl }/page/${i}/${page.orderBy}/${page.order}">${i}</a>&nbsp;  
				</#if>
			</#list>
			</#if>
		<#else>
			  ...&nbsp;  
			  <#list page.pageNo?number-4..page.pageNo?number as i>
				<#if page.pageNo?number==i>
					<a class="previous-page" href="${pagingUrl }/page/${i}/${page.orderBy}/${page.order}">${i}</a>&nbsp;
				<#else>
					<a href="${pagingUrl }/page/${i}/${page.orderBy}/${page.order}">${i}</a>&nbsp;  
				</#if>
			  </#list>
		</#if>
		<#if page.pageNo?number gte totalPages?number-4 || totalPages?number-4 lte 0>
			<#if page.pageNo?number+1 lte totalPages?number>
			<#list page.pageNo?number+1..totalPages?number as i>
				<a href="${pagingUrl }/page/${i}/${page.orderBy}/${page.order}">${i}</a>&nbsp;  
				<#if page.pageNo?number == i>
					<a class="previous-page" href="${pagingUrl }/page/${i}/${page.orderBy}/${page.order}">${i}</a>&nbsp;
				</#if>
			</#list>
			</#if>
		<#else>
			<#list page.nextPage..page.pageNo?number+4 as i>
				<#if page.pageNo?number == i>
					<a class="previous-page"  href="${pagingUrl }/page/${i}/${page.orderBy}/${page.order}">${i}</a>&nbsp;
				<#else>
					<a href="${pagingUrl }/page/${i}/${page.orderBy}/${page.order}">${i}</a>&nbsp;  
				</#if>
			</#list>
			  ...&nbsp;  
			  <a href="${pagingUrl }/page/${totalPages}/${page.orderBy}/${page.order}">${totalPages}</a>&nbsp;  
		</#if>
		<#if page.pageNo?number lt totalPages?number>
			<a class="next-page" href="${pagingUrl }/page/${((page.nextPage) lt totalPages?number)?string(page.nextPage,totalPages?number)}/${page.orderBy}/${page.order}">下一页</a>
		</#if>
	</#if>
	
</#if>
</div>
