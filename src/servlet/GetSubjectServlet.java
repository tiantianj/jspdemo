package servlet;

import com.alibaba.fastjson.JSON;
import dao.SubjectDao;
import dao.dao.impl.SubjectDaoImpl;
import entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetSubjectServlet",urlPatterns = "/subject/getById")
public class GetSubjectServlet extends HttpServlet {
    private int i = 1;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String strNo = request.getParameter("no");
        int no = 0;
        if(strNo!=null&&!"".equals(strNo)){
            no = Integer.parseInt(strNo);
        }

        //调用业务方法
        SubjectDao dao = new SubjectDaoImpl();
        Subject subject = dao.getById(no);

        //将数据封装给request(键值对形式)
        request.setAttribute("subject",subject);

        request.setAttribute("i",++i);

        //将request内容转发给显示的页面
        request.getRequestDispatcher("update.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
