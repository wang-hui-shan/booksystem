package booksystem.dao;

import java.sql.*;

public class UserRegister {
    public boolean complete = false;
    private String username,password;
    public UserRegister(String username, String password) {
        this.username = username;
        this.password = password;
        validate();
    }
    private void validate() {
        //遍历查询结果集
        //加载驱动程序
        //Class.forName(driver);
        //1.getConnection()方法，连接MySQL数据库！！
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            statement.execute("begin;");
            //要执行的SQL语句
            String sql = "select username from user where username=\"" + username + "\"";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            if(!rs.next()){ //用户名可用
                complete = true;
                sql = "insert into user(username,userpass) values (" + "\"" + username + "\"" + "," + "\"" + password + "\"" + ")";
                statement.executeUpdate(sql);
            }
            statement.execute("commit;");
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
