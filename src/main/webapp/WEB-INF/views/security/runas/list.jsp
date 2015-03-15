<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>切换身份_SMS</title>
<%@include file="/common/taglibs.jsp" %>
<%@include file="/common/common-header.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<script src="${ctx }/resources/js/admin.js" type="text/javascript"></script>
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
        
    <c:if test="${needRefresh}">
        <script type="text/javascript">
            top.document.getElementById("username").innerText = "${user.username}";
        </script>
    </c:if>
    <div>${msg}</div>
    <h2>切换身份</h2>

    <c:if test="${isRunas}">
        上一个身份：${previousUsername}
        |
        <a href="${pageContext.request.contextPath}/security/runas/switchBack">切换回该身份</a>
    </c:if>

    <h3>切换到其他身份：</h3>

    <c:choose>
        <c:when test="${empty fromUserIds}">无</c:when>
        <c:otherwise>
            <table class="table">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${fromUserIds}" var="id">
                    <tr>
                        <td>${id}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/security/runas/switchTo/${id}">切换到该身份</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    <h3>授予身份给其他人：</h3>
    <table class="table">
        <thead>
        <tr>
            <th>用户名</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allUsers}" var="u">
            <tr>
                <td>${u.username}</td>
                <td>
                    <c:choose>
                        <c:when test="${toUserIds[u.id] != null }">
                            <a href="${pageContext.request.contextPath}/security/runas/revoke/${u.id}">回收身份</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/security/runas/grant/${u.id}">授予身份</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

            </div>
        </div>
        <!-- Container End -->
        <!-- Footer Start -->
        <%@include file="/common/footer.jsp" %>
        <!-- Footer End -->
        
</body>
</html>