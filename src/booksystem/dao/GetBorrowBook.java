package booksystem.dao;

import java.sql.*;
import java.util.HashMap;

public class GetBorrowBook {
    private int userId;

    public GetBorrowBook(int userId) {
        // 获取用户输入的用户名和密码
        this.userId = userId;
    }

    public HashMap getBookList() {
        //书单
        HashMap<Integer,String> bookList = new HashMap<>();
        //驱动程序名
        //String driver = "com.mysql.jc.jdbc.Driver";
        //Class.forName(driver);
        //1.getConnection()方法，连接MySQL数据库！！
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            statement.execute("begin;");
            //要执行的SQL语句
            String sql = "select bookid,bookname from userbookinfo where userid=" + userId;
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int bookId = rs.getInt("bookid");
                String bookName = rs.getString("bookname");
                bookList.put(bookId,bookName);
            }
            statement.execute("commit;");
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return bookList;
    }
}
