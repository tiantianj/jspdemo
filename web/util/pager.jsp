<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<p>总记录数：<span class="totalCount">${param.totalCount}</span>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <span class="pageNo">${param.pageNo}</span>/<span class="pageCount">${param.pageCount}</span>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <c:if test="${param.pageNo gt 1}">
        <a href="javascript:golist(${param.pageNo-1});" class="previousPage" >上一页</a>
        &nbsp;&nbsp;
    </c:if>
    <c:if test="${param.pageNo lt param.pageCount}">
        <a href="javascript:golist(${param.pageNo+1});" class="nextPage" >下一页</a>
        &nbsp;&nbsp;
    </c:if>
    &nbsp;&nbsp;<input type="text" size="2" id="pageNo">
    <button class="go">GO</button>
</p>

<script>
    $(function () {
        //给跳转按钮绑定操作
        $(".go").click(function () {
            var page = $("#pageNo").val();
            var reg = /^[1-9][0-9]*$/;
            //输入数字格式不能有误
            if(!reg.test(page)){
                alert("请输入正确的数字格式！");
                $("#pageNo").focus();
            }else if(parseInt(page)>parseInt($(".pageCount").text())){
                //不能超出最大页
                alert("超出最大页码！");
                $("#pageNo").focus();
            }else{
                //location.href = "list?pageNo="+parseInt(page)+"&pageSize="+parseInt($("#pageSize").val());
                golist(parseInt(page));
            }
        });
    });

    function golist(pageNo){
        $("#targetNo").val(pageNo);
        //提交表单
        document.forms[0].submit();
    }
</script>

