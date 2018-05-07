<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增学员</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/subject/add" method="post">
        <label for="subjectName">课程名称</label>
        <input type="text" name="subjectName" id="subjectName"><br>
        <label for="classHour">总课时</label>
        <input type="text" name="classHour" id="classHour"><br>
        <label for="gradeId">年级编号</label>
        <select name="gradeId" id="gradeId">
            <c:forEach items="${grades}" var="grade">
                <option value="${grade.id}">${grade.name}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="添加">
    </form>
</body>
</html>