package booksystem.dao;

import booksystem.bean.Book;

import java.sql.*;

public class BorrowBook {
    public boolean complete = false;
    private Book book;
    private int userId;

    public BorrowBook(Book book, int userId) {
        this.book = book;
        this.userId = userId;
        execute();
    }
    private void execute() {
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //2.创建statement类对象
            Statement statement = con.createStatement();
            statement.execute("begin;");
            //将用户借阅信息中为bookid的记录删除
            // 将图书的借阅状态修改为0(未被借阅)
            ResultSet rs = statement.executeQuery("select bookname from bookinfo where bookid=" + this.book.getBookid() + " and bookstatus=0");
            if(rs.next()){
                statement.addBatch("update bookinfo set bookstatus=1 where bookid=" + this.book.getBookid());
                statement.addBatch("insert into userbookinfo values (" + this.userId + "," + this.book.getBookid() + ",\"" + rs.getString("bookname") + "\")");
                statement.executeBatch();
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
