<%@ page import="entity.Subject" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改课程</title>

</head>
<body>
项目根目录的完整路径
${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/
    <form action="${pageContext.request.contextPath}/subject/update" method="post">
        <label for="subjectNo">课程编号</label>
        <input type="text" name="subjectNo" id="subjectNo" value="${subject.no}" readonly><br>
        <label for="subjectName">课程名称</label>
        <input type="text" name="subjectName" id="subjectName" value="${subject["name"]}"><br>
        <label for="classHour">总课时</label>
        <input type="text" name="classHour" id="classHour" value="${subject.classHour}"><br>
        <label for="gradeId">年级编号</label>
        <select name="gradeId" id="gradeId">
            <c:forEach items="${grades}" var="grade">
                <option value="${grade.id}"  <c:if test="${grade.id==subject.gradeId}">selected</c:if> >${grade.name}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="添加">
    </form>
    ${null}
</body>
</html>