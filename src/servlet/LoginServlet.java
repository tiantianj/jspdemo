package servlet;

import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行登录请求");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username!=null&&!"".equals(username)){
            User user = new User();
            user.setName(username);
            user.setPassword(password);

            //省略了去数据库校验用户信息的操作


            //将登录的用户对象放入Session作用域
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            //将用户名放入Cookie中，
            //防止用户名为中文导致的异常，对中文信息进行编码
            System.out.println(URLEncoder.encode(username,"utf-8"));
            Cookie cookie = new Cookie("username", URLEncoder.encode(username,"utf-8"));
            //设定Cookie的有效期,单位为秒
            cookie.setMaxAge(30*24*60*60);
            //将cookie加入响应信息，保存在客户端本地
            response.addCookie(cookie);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            String msg = "用户名不能为空";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先从客户端的Cookie里面查看是否有保存过用户名
        //如果有的话，直接将用户名传递到页面上
        Cookie[] cookies = request.getCookies();
        //遍历所有的cookie
        for (Cookie c:
             cookies) {
            //找到对应存储用户名的cookie
            if("username".equals(c.getName())){
                String name = c.getValue();
                //存放进request,转发到页面上
                //存放从cookie中获取的字符串，需要再进行解码
                request.setAttribute("username", URLDecoder.decode(name,"utf-8"));
            }
        }

        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }
}
