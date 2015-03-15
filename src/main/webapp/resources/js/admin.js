
health = {};

health.select = function() {
	var selectItems = [];
	$(':checkbox[name="items"][checked]').each(function() {
		selectItems.push($(this).val());
	});
	return selectItems;
};

health.selectAll = function() {
	$(':checkbox[name="items"]').attr('checked', true).parent().addClass('checked');

};

health.unselectAll = function() {
	$(':checkbox[name="items"]').attr('checked', false).parent().removeClass('checked');
};
//查询搜索
health.search = function() {
	$("#pageNo").val(1);
	$('#myForm').attr("method", "get").submit();
};

health.jumpPage = function(pageNo){
	$("#pageNo").val(pageNo);
	$("#myForm").attr("method", "get");
	$("#myForm").submit();
};
//跳转页数
health.jumpTarget = function(pageNo,totalCount){
	if (!isNaN(pageNo)) {
		var pageNoInt = parseInt(pageNo);
		var totalCountInt = parseInt(totalCount);
		if (pageNo && pageNo.length!==0 && pageNoInt>0 && pageNoInt<=totalCountInt) {
			if(pageNo.indexOf("/") !== -1 || pageNo.indexOf("<") !== -1 || pageNo.indexOf(">") !== -1){
				return;
			}
			health.jumpPage(pageNo);
		}else{
			return;
		}
	} else {
		return;
	}
};


health.formatDate = function(dateAsLong){
	 var date = new Date(parseInt(dateAsLong, 10));
	 var year = date.getFullYear();
	 var month = date.getMonth() + 1;
	 month = month >= 10 ? month.toString() : ("0" + month);
	 var day = date.getDate() >= 10 ? date.getDate() : ("0" + date.getDate());
	 var hours = date.getHours() >= 10 ? date.getHours().toString() : ("0" + date.getHours());
	 var minutes = date.getMinutes() >= 10 ? date.getMinutes().toString() : ("0" + date.getMinutes());
	 var seconds = date.getSeconds() >= 10 ? date.getSeconds().toString() : ("0" + date.getSeconds());
	 return "".concat(year, "-", month, "-", day, " ", hours, ":", minutes, ":", seconds);
};

var kbytes = 1024;
var mbytes = 1024 * 1024;
var gbytes = 1024 * 1024 * 1024;
health.formatFileSize = function(fileSize){
	if(fileSize > gbytes){
		return health.round((fileSize / gbytes),1) + "G"; 
	}
	
	if(fileSize > mbytes){
		return health.round((fileSize / mbytes),1) + "M"; 
	}
	
	if(fileSize > kbytes){
		return health.round((fileSize / kbytes),1) + "K"; 
	}
	
	return fileSize + "bytes";
};

health.round = function(val, scale){
	var valAsString = val.toString();
	var scaleOnVal = 0;
	try{
		scaleOnVal = valAsString.split(".")[1].length;
	}catch(e){}
	if(scaleOnVal === 0){
		return valAsString;
	}
	
	if(scale >= scaleOnVal){
		return valAsString;
	}
	var length = valAsString.length;
	return valAsString.slice(0, length-(scaleOnVal - scale));
};

$(function() {
	$("#selectAndUnselect").click(function() {
		var selectOr = $("#selectAndUnselect").attr("checked");
		if(selectOr){
			health.selectAll();
		}else{
			health.unselectAll();
		}
	});
	
	
	$("#search").click(function() {
		health.search();
	});
	
	 $(".date").each(function(){
		 var $this = $(this);
		 var lastModified = $this.text();
 		 $this.empty().append(health.formatDate(lastModified));
	  });
	 
	 $(".fileSize").each(function(){
		 var $this = $(this);
		 var fileSize = $this.text();
 		 $this.empty().append(health.formatFileSize(fileSize));
	 });
});