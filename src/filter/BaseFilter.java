package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * "/"表示对所有Servlet请求都进行过滤
 */
@WebFilter(filterName = "BaseFilter",urlPatterns = "/*")
public class BaseFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //对所有的请求设置统一的编码格式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        System.out.println("请求时的过滤操作");
        //其上，为请求时进行的过滤操作
        chain.doFilter(req, resp);
        //其下，为响应进行的过滤操作
        System.out.println("响应时的过滤操作");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
