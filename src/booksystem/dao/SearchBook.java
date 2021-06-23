package booksystem.dao;

import booksystem.bean.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchBook {
    private String searchInfo,searchType;
    private HashMap<String,String> typeToCol = new HashMap<String,String>(){
        {
            put("书名","bookname");
            put("图书编号","bookid");
            put("作者","bookauthor");
        }
    };
    public SearchBook(String searchInfo, String searchType) {
        this.searchInfo = searchInfo;
        this.searchType = searchType;
    }

    public ArrayList<Book> getBook() {
        ArrayList<Book> books = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from bookinfo where " + typeToCol.get(this.searchType) +"=" + "\"" + this.searchInfo+"\"";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Book book = new Book();
                book.setBookid(rs.getInt("bookid"));
                book.setBookname((rs.getString("bookname")));
                book.setBookauthor(rs.getString("bookauthor"));
                book.setBooktheme(rs.getString("booktheme"));
                book.setBookstatus(rs.getInt("bookstatus"));
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
