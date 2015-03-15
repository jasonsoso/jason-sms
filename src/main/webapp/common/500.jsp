<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%response.setStatus(200);%>

<%
    Throwable ex = null;
    if (exception != null)
        ex = exception;
    if (request.getAttribute("javax.servlet.error.exception") != null)
        ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

    //记录日志
    Logger logger = LoggerFactory.getLogger("500.jsp");
    logger.error(ex.getMessage(), ex);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/taglibs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>500  亲，程序错误啦</title>
</head>

<body>
<p>500  亲，程序错误啦 </p>
<%= exception %>
</body>
</html>
