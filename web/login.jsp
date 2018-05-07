<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>${msg}</p>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="username">用户名</label><input id="username" type="text" name="username" value="${username}"><br>
        <label for="password">密码</label><input id="password" type="password" name="password"><br>
        <input type="submit" value="登录">
    </form>

    <%
        Map<String,String> online = (Map<String,String>)application.getAttribute("online");
        for (String id:
             online.keySet()) {
            out.print(id+"-"+online.get(id)+"<br/>");
        }
    %>
</body>
</html>
