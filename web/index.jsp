<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <base href="${pageContext.request.contextPath}/">
  </head>
  <body>
    <p>欢迎你，${user.name} <a href="logout">注销</a></p>
    <p>
      <a href="subject/list">课程信息</a>
    </p>
    <%=application.getAttribute("loginNum")%>
  </body>
</html>
