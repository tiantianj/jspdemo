<%@ page import="util.Page" %>
<%@ page import="entity.Subject" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>课程列表页面</title>
    <style>
        table,td,th{
            border: 1px solid #CCCCCC;
        }
        table{
            border-collapse: collapse;
            margin: 0 auto;
        }
        td{
            padding: 5px 15px;
        }
    </style>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
</head>
<body>
    <p>你好，${user.name}</p>
    <button class="addSubject">新增学员</button>
    <form action="${pageContext.request.contextPath}/subject/list">
        <label for="subjectName">课程名称：</label><input type="text" name="subjectName" value="${subjectName}"
                                                     id="subjectName" >
        <!-- 查询中，依然需要传入分页相关数据，页码和每页显示数量，所以使用隐藏域传递 -->
        <input type="hidden" name="pageSize" value="${pager.pageSize}">
        <input type="hidden" name="pageNo" id="targetNo" value="">
        <input type="submit" value="查询">
    </form>

    <table class="subjectList">
        <thead>
            <tr>
                <th>编号</th>
                <th>课程名称</th>
                <th>总课时</th>
                <th>年级编号</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${!empty pager.rows}">
                    <c:forEach var="sub" items="${pager.rows}" >
                        <tr>
                            <td>${sub.no}</td>
                            <td>${sub.name}</td>
                            <td>${sub.classHour}</td>
                            <td>${sub.grade.name}</td>
                            <td>
                                <button onclick="goUpdate(${sub.no})">修改</button>
                                <button onclick="deleteSubject(${sub.no},'${sub.name}')">修改</button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <td colspan="5" style="height: 5em;text-align: center;vertical-align: center">对不起，没有合适的数据</td>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    <c:import url="/util/pager.jsp">
        <c:param name="totalCount" value="${pager.totalCount}"/>
        <c:param name="pageNo" value="${pager.pageNo}"/>
        <c:param name="pageCount" value="${pager.pageCount}"/>
    </c:import>


    <script>
        $(function () {
            $(".addSubject").click(function () {
                location.href = "add";
            });
        });

        function deleteSubject(no,name){
            if(confirm("确定要删除"+name+"课程吗？")){
                location.href="delete?no="+no;
            }
        }
        
        function goUpdate(no) {
            location.href="update?no="+no;
        }
    </script>
</body>
</html>