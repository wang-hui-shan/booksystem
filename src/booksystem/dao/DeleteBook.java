package booksystem.dao;

import java.sql.*;

public class DeleteBook {
    public boolean complete = false;
    private String bookId;

    public DeleteBook(String bookId) {
        this.bookId = bookId;
        execute();
    }
    private void execute() {
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //创建statement类对象
            Statement statement = con.createStatement();
            // 确认图书未被借阅
            // 将图书删除
            ResultSet rs = statement.executeQuery("select bookstatus from bookinfo where bookid=" + this.bookId);
            if(rs.next() && rs.getInt("bookstatus") == 0){
                statement.executeUpdate("delete from bookinfo where bookid=" + this.bookId);
                complete = true;
            }
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
