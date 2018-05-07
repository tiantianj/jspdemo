package servlet;

import dao.SubjectDao;
import dao.dao.impl.SubjectDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteSubjectServlet",urlPatterns = "/subject/delete")
public class DeleteSubjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String strNo = request.getParameter("no");
        int no = 0;
        if(strNo!=null&&!"".equals(strNo)){
            no = Integer.parseInt(strNo);
        }

        //调用业务方法
        SubjectDao dao = new SubjectDaoImpl();
        dao.deleteById(no);

        //给予响应
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('课程删除成功');location.href='list.jsp';</script>");
    }
}
