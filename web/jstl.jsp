<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setAttribute("str","");
        List list =  new ArrayList();
        list.add(1);
        list.add("xxx");
        list.add("abc");
        request.setAttribute("list",list);

        request.setAttribute("i",10);


        Date today = new Date();
        request.setAttribute("today",today);
    %>
    <fmt:formatDate value="${today}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>

    <c:if test="${empty list}">
        <h1>正确</h1>
    </c:if>

    <c:choose>
        <c:when test="${i gt 20}">大于20</c:when>
        <c:when test="${i gt 10}">大于10</c:when>
        <c:otherwise>小于等于10</c:otherwise>
    </c:choose>

    <c:forEach items="${list}" var="x" begin="0" end="2" step="2" varStatus="status">
        ${x}-count:${status.count}-index:${status.index}-first:${status.first}<br>
    </c:forEach>
</body>
</html>
