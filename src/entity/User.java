package entity;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 用户信息
 * @author
 */
public class User implements HttpSessionBindingListener {
    private int id;
    private String name;
    private String password;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 当User对象被添加到Session作用域时触发
     * @param httpSessionBindingEvent
     */
    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        ServletContext application = httpSessionBindingEvent.getSession().getServletContext();
        if(application.getAttribute("loginNum")!=null){
            int num = (int)application.getAttribute("loginNum");
            application.setAttribute("loginNum",++num);
        }else{
            application.setAttribute("loginNum",1);
        }
    }

    /**
     * 当User对象被移出Session作用域时触发
     * @param httpSessionBindingEvent
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        ServletContext application = httpSessionBindingEvent.getSession().getServletContext();
        int num = (int)application.getAttribute("loginNum");
        application.setAttribute("loginNum",--num);

    }
}
