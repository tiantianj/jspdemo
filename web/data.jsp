<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/3/003
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Context context = new InitialContext();
        //通过名称获取对应的数据源对象
        //java:comp/env/->JavaEE环境默认的地址前缀
        DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/myschool");
        Connection conn = ds.getConnection();
        out.print(conn);
    %>
</body>
</html>
