package booksystem.dao;

import java.sql.*;

public class AddBook {
    public boolean complete = false;
    private String[] bookInfo;

    public AddBook(String[] bookInfo) {
        this.bookInfo = bookInfo;
        execute();
    }
    private void execute() {
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //创建statement类对象
            Statement statement = con.createStatement();
            String sql = "insert into bookinfo(bookname,bookauthor,booktheme) values" + "(\"" + bookInfo[0]+"\",\""+ bookInfo[1]+ "\",\"" +bookInfo[2]+ "\")";
            System.out.println(sql);
            if(statement.executeUpdate(sql) == 1)
                complete = true;
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
