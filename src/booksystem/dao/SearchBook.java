package booksystem.dao;

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

    public HashMap<Integer, ArrayList<String>> getBook() {
        HashMap<Integer, ArrayList<String>> booksInfo = new HashMap<>();

        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            statement.execute("begin;");
            //要执行的SQL语句
            String sql = "select * from bookinfo where " + typeToCol.get(this.searchType) +"=" + "\"" + this.searchInfo+"\"";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                ArrayList<String> values = new ArrayList();
                values.add(rs.getString("bookname"));
                values.add(rs.getString("bookauthor"));
                values.add(rs.getString("booktheme"));
                values.add(rs.getString("bookstatus"));
                booksInfo.put(rs.getInt("bookid"),(ArrayList<String>) values.clone());
            }
            statement.execute("commit;");
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return booksInfo;
    }
}
