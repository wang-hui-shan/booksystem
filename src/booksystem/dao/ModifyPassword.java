package booksystem.dao;

import java.sql.*;

public class ModifyPassword {
    public boolean complete = false;
    private String password, role;
    private int userId;
    public ModifyPassword(int userId, String password, String role) {
        this.userId = userId;
        this.role = role;
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
            String sql = "update "+ this.role + " set userpass = " + "\""+ password + "\"" + " where userid=\"" + this.userId + "\"";
            if(statement.executeUpdate(sql) == 1) {
                complete = true;
            }
            statement.execute("commit;");
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
