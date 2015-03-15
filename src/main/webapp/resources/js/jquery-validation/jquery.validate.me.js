jQuery.validator.addMethod("range", function (value, element) {  
	   return this.optional(element) || ((value>0&&value<1));  
	}, $.validator.format("小数范围为0.00~0.99"));  

jQuery.validator.addMethod("score", function (value, element) {  
	   return this.optional(element) || ((value>=0&&value<=100));  
	}, $.validator.format("数字范围为0~100"));  

jQuery.validator.addMethod("decimal", function (value, element) {  
	   var decimal = /^-?\d+(\.\d{1,2})?$/;  
	   return this.optional(element) || (decimal.test(value));  
	}, $.validator.format("小数位数不能超过两位"));  