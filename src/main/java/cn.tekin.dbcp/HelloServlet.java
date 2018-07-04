package cn.tekin.dbcp;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Tekin
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/hello.do"})
public class HelloServlet extends HttpServlet {
    /**
     * 从ServletContext中获取并初始化datasource
     */
    private  DataSource dataSource = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* 定义数据库资源对象 */
        Connection conn =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        try {
            this.dataSource = (DataSource) getServletContext().getAttribute("dataSource");
            conn = dataSource.getConnection();
            String sql = "select id,name,email,age,now() as nowtime from user;";
            pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();

            /*循环读取并输出数据*/
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                String nowtime = rs.getString("nowtime");

                out.println("id: "+ id +"  Name:" + name +"  Email:"+ email +"  Age:"+ age + " 数据库时间："+ nowtime
                        +"</br>");
            }
        } catch (Exception ex) {
            out.println(ex.getMessage());
        }finally {
            /*在finally里面确保资源使用后及时关闭*/
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

}