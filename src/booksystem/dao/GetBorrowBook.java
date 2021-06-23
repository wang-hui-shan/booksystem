package booksystem.dao;

import booksystem.bean.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GetBorrowBook {
    private int userId;

    public GetBorrowBook(int userId) {
        // 获取用户输入的用户名和密码
        this.userId = userId;
    }

    public ArrayList<Book> getBookList() {
        ArrayList<Book> books = new ArrayList<>();
        //1.getConnection()方法，连接MySQL数据库！！
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select bookid,bookname from userbookinfo where userid=" + userId;
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Book book = new Book();
                book.setBookid(rs.getInt("bookid"));
                book.setBookname(rs.getString("bookname"));
                books.add(book);
            }
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return books;
    }
}
