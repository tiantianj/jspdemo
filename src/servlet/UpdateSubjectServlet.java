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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UpdateSubjectServlet",urlPatterns = "/subject/update")
public class UpdateSubjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //接收用户提交的数据(根据参数名称获取参数的值)
        //如果请求的参数不存在，返回null
        String strSubjectNo = request.getParameter("subjectNo");
        int subjectNo = 0;
        if(strSubjectNo!=null&&!"".equals(strSubjectNo)){
            subjectNo = Integer.parseInt(strSubjectNo);
        }
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
        sub.setNo(subjectNo);
        sub.setName(subjectName);
        sub.setClassHour(classHour);
        sub.setGradeId(gradeId);

        //调用数据库相关方法，保存
        SubjectDao dao = new SubjectDaoImpl();
        dao.update(sub);

        //执行完成直接跳转到列表页，因为不需要传递请求数据，所以可以直接使用重定向
        response.sendRedirect(request.getContextPath()+"/subject/list");
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
        Subject subject = dao.getById(no);

        GradeService gradeService = new GradeServiceImpl();
        List<Grade> grades = gradeService.findAll();


        //将数据封装给request(键值对形式)
        request.setAttribute("subject",subject);
        request.setAttribute("grades",grades);


        //Session
        Subject subject1 = dao.getById(1);
        HttpSession session = request.getSession();
        session.setAttribute("subject",subject1);

        //将request内容转发给显示的页面
        request.getRequestDispatcher("update.jsp").forward(request,response);
        //response.sendRedirect("/subject/update.jsp");
    }
}
