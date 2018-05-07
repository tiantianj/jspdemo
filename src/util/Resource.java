package util;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取数据库配置文件的工具类
 *
 * 使用单例模式——设计模式
 * 1)构造方法私有化
 * 2)私有的静态的当前类的对象
 * 3)公有的静态的获取当前类的唯一对象的方法
 */
public class Resource {
    private static Resource resource;
    private Properties prop = new Properties();

    private Resource(){
        try {

            prop.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Resource getIntance(){
        if(resource==null){
            resource = new Resource();
        }
        return resource;
    }

    public String getValue(String key){
        return prop.getProperty(key);
    }

}
