package booksystem.dao;

import java.sql.*;

public class BorrowBook {
    public boolean complete = false;
    private String bookId;
    private int userId;

    public BorrowBook(String bookId, int userId) {
        this.bookId = bookId;
        this.userId = userId;
        execute();
    }
    private void execute() {
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //2.创建statement类对象
            Statement statement = con.createStatement();
            //将用户借阅信息中为bookid的记录删除
            // 将图书的借阅状态修改为0(未被借阅)
            ResultSet rs = statement.executeQuery("select bookname from bookinfo where bookid=" + this.bookId + " and bookstatus=0");
            if(rs.next()){
                statement.addBatch("update bookinfo set bookstatus=1 where bookid=" + this.bookId);
                statement.addBatch("insert into userbookinfo values (" + this.userId + "," + this.bookId + ",\"" + rs.getString("bookname") + "\")");
                statement.executeBatch();
                complete = true;
            }
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
