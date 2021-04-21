package booksystem.dao;

import java.sql.*;

public class ReturnBook {
    public boolean complete = false;
    private String bookId;
    private int userId;

    public ReturnBook(String bookId,int userId) {
        this.bookId = bookId;
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
            ResultSet rs = statement.executeQuery("select * from userbookinfo where bookid=" + this.bookId + " and userid=" + this.userId);
            if(rs.next()){
                statement.addBatch("delete from userbookinfo where bookid=" + this.bookId);
                statement.addBatch("update bookinfo set bookstatus=0 where bookid=" + this.bookId);
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
