package booksystem.dao;

import java.sql.*;

public class LoginVerify {
    private String username,password;
    private static int userId;
    private static String role;
    public boolean in = false;

    public LoginVerify(String username, String password, String role) {
        // 获取用户输入的用户名和密码
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public int validate() {
        // 到数据库去验证
        //驱动程序名
        //String driver = "com.mysql.jc.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        //遍历查询结果集
        //加载驱动程序
        //
        //1.getConnection()方法，连接MySQL数据库！！
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            statement.execute("begin;");
            //要执行的SQL语句
            String sql = "select userpass from "+ role +" where username=\"" + username + "\"";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String truePass = rs.getString("userpass");
                if(this.password.equals(truePass)){
                    in = true;
                }
            }
            // 查询userId
            sql = "select userid from "+ role +" where username=\"" + username + "\"";
            rs = statement.executeQuery(sql);
            while(rs.next()){
                int userid = rs.getInt("userid");
                this.userId = userid;
            }
            statement.execute("commit;");
        } catch(SQLException e1) {
            //数据库连接失败异常处理
            e1.printStackTrace();
        } catch (Exception e2) {
            // TODO: handle exception
            e2.printStackTrace();
        }
        return this.userId;
    }
}
