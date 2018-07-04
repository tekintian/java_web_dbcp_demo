package cn.tekin.dbcp.listener;

import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

@WebListener
public class DBCPListener implements ServletContextListener{

    /**
     * 应用启动时，该方法被调用
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("设置数据库连接池");
            ServletContext application = sce.getServletContext();
            Properties properties=new Properties();
            properties.load(application.getClassLoader().getResourceAsStream("dbcp.properties"));
            DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
            application.setAttribute("dataSource", dataSource);
        }
        catch(Exception ex) {
            System.err.println("数据库连接池设置出现异常：" + ex.getMessage());
        }
    }

    /**
     * 应用关闭时，该方法被调用
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}