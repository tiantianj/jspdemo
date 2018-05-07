package servlet;

import com.alibaba.fastjson.JSON;
import dao.SubjectDao;
import dao.dao.impl.SubjectDaoImpl;
import entity.Subject;
import entity.User;
import service.SubjectService;
import service.service.impl.SubjectServiceImpl;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListSubjectServlet",urlPatterns = "/subject/list")
public class ListSubjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取分页数据
        int pageNo = 1;
        int pageSize = 10;
        String strPageNo = request.getParameter("pageNo");
        String strPageSize = request.getParameter("pageSize");
        if (strPageNo != null && !"".equals(strPageNo)) {
            pageNo = Integer.parseInt(strPageNo);
        }
        if (strPageSize != null && !"".equals(strPageSize)) {
            pageSize = Integer.parseInt(strPageSize);
        }
        //获取,如果前端没有subjectName参数，会直接将null作为字符串传递给数据库
        String likeName = request.getParameter("subjectName");


        //获取分页数据
        //在控制层只能调用业务层的方法，不能跨层调用持久层代码
        SubjectService service = new SubjectServiceImpl();
        List<Subject> subs = service.findPage(likeName, pageNo, pageSize,true);
        int totalCount = (int) service.getCount(likeName);

        //构建响应给列表页的分页对象
        Page<Subject> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalCount(totalCount);
        //当设置完每页数量和总记录数后，调用方法计算总页数
        page.setPageCount();
        page.setRows(subs);

        //将数据放入request转发给页面
        request.setAttribute("pager", page);
        request.setAttribute("subjectName", likeName);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
