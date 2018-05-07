package servlet;

import dao.SubjectDao;
import dao.dao.impl.SubjectDaoImpl;
import entity.Grade;
import entity.Subject;
import service.GradeService;
import service.service.impl.GradeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AddSubjectServlet",urlPatterns = "/subject/add")
public class AddSubjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //接收用户提交的数据(根据参数名称获取参数的值)
        //如果请求的参数不存在，返回null
        String subjectName = request.getParameter("subjectName");
        String strClassHour = request.getParameter("classHour");
        int classHour = 0;
        if(strClassHour!=null&&!"".equals(strClassHour)){
            classHour = Integer.parseInt(strClassHour);
        }
        String strGradeId = request.getParameter("gradeId");
        int gradeId = 0;
        if(strGradeId!=null&&!"".equals(strGradeId)){
            gradeId = Integer.parseInt(strGradeId);
        }

        //将数据封装成subject对象，
        Subject sub = new Subject();
        sub.setName(subjectName);
        sub.setClassHour(classHour);
        sub.setGradeId(gradeId);

        //调用数据库相关方法，保存
        SubjectDao dao = new SubjectDaoImpl();
        dao.save(sub);

        //执行完成以后，给予响应
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('学员添加成功');location.href='list';</script>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有的年级信息
        GradeService service = new GradeServiceImpl();
        List<Grade> grades = service.findAll();
        request.setAttribute("grades",grades);

        request.getRequestDispatcher("add.jsp").forward(request,response);
    }
}
