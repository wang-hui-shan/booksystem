package booksystem.dao;

import booksystem.bean.Book;

import java.sql.*;

public class AddBook {
    public boolean complete = false;
    private Book book;

    public AddBook(Book book) {
        this.book = book;
        execute();
    }
    private void execute() {
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //创建statement类对象
            Statement statement = con.createStatement();
            statement.execute("begin;");
            String sql = "insert into bookinfo(bookname,bookauthor,booktheme) values" + "(\"" + book.getBookname() +"\",\""+ book.getBookauthor()+ "\",\"" +book.getBooktheme()+ "\")";
            if(statement.executeUpdate(sql) == 1)
                complete = true;
            statement.execute("commit;");
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
