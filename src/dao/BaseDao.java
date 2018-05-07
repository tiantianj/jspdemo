package dao;

import util.Resource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * 数据库操作的基类
 * 主要包含了三个对象，四个方法
 * @author
 */
public class BaseDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;


    private String driverClass= Resource.getIntance().getValue("driverClass");
    private String url = Resource.getIntance().getValue("url");
    private String user = Resource.getIntance().getValue("user");
    private String password = Resource.getIntance().getValue("password");


    /**
     * 抽象增删改的方法
     * @param sql 包含占位符的SQL
     * @param paras 参数数组
     * @return
     *
     * 类型后面加上"..."表示在该位置可以是0个到任意个对应类型的参数
     * 相当于以下方法的合并写法
     * 注意：这种写法只能在方法声明中的最后一个参数才行
     * public int executeUpdate(String sql)
     * public int executeUpdate(String sql,Object para1)
     * public int executeUpdate(String sql,Object para1,Object para2)
     * public int executeUpdate(String sql,Object para1,Object para2,Object para3)
     * ....
     */
    public int executeUpdate(String sql,Object... paras){
        int flag = -1;
        getConn();
        try {
            setParas(sql,paras);
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }

    /**
     * 抽象查询方法
     * @param sql
     * @param paras
     * @return
     */
    public ResultSet executeQuery(String sql,Object... paras){
        getConn();
        try {
            setParas(sql,paras);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * 抽象获取唯一结果值的方法
     * @param sql
     * @param paras
     * @return
     */
    public Object executeUnique(String sql,Object... paras){
        Object result = null;
        getConn();
        try {
            setParas(sql,paras);
            rs = ps.executeQuery();
            while (rs.next()){
                result = rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }


//    /**
//     * 传统的JDBC获取数据库连接
//     */
//    public void getConn2(){
//        try {
//            //1.加载驱动
//            Class.forName(driverClass);
//            //2.获取数据库连接
//            conn = DriverManager.getConnection(url,user,password);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 使用数据源获取数据库连接
     */
    public void getConn(){
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/myschool");
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭所有资源的操作
     */
    public void closeAll(){
        if(rs!=null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 提取ps绑定参数的操作
     */
    private void setParas(String sql, Object... paras) throws SQLException {
        ps = conn.prepareStatement(sql);
        if(paras!=null){
            for (int i = 0; i < paras.length; i++) {
                ps.setObject(i+1,paras[i]);
            }
        }
    }

}
