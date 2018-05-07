package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 所有访问课程相关的操作都必须判断登陆以后才能执行
 */
@WebFilter(filterName = "UserFilter",urlPatterns = "/subject/*")
public class UserFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hsq = (HttpServletRequest)req;
        HttpSession session = hsq.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null) {
            //用户存在的话执行下一步操作
            chain.doFilter(req, resp);
        }else{
            //用户不存在直接跳转到登录页
            String msg = "请登录以后再访问";
            hsq.setAttribute("msg",msg);
            hsq.getRequestDispatcher("/login").forward(hsq,resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
