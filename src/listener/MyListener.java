package listener;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class MyListener implements HttpSessionListener {
    /**
     * 构建Session对象的时候触发
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        String id = session.getId();
        //如果这个session是新创建的，
        if(session.isNew()){
            String name = "游客";
            if(session.getAttribute("user")!=null){
                name = ((User)session.getAttribute("user")).getName();
            }
            //将sessionid和用户名加入application作用域
            ServletContext application = session.getServletContext();
            Map<String,String> online = null;
            if(application.getAttribute("online")!=null){
                online = (Map<String,String>)application.getAttribute("online");
            }else{
                online = new HashMap<>();
                application.setAttribute("online",online);
            }
            online.put(id,name);
        }
    }

    /**
     * 销毁Session对象的时候触发
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //当Session销毁时，从作用域中移除当前的访客信息
        ServletContext application = httpSessionEvent.getSession().getServletContext();
        Map<String,String> online = (Map<String,String>)application.getAttribute("online");
        online.remove(httpSessionEvent.getSession().getId());
    }
}
