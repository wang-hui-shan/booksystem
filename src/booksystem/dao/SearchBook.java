package booksystem.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Vector;

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
        getBook();
    }

    public Vector getBook() {
        Vector bookInfo = new Vector();
        //驱动程序名
        //String driver = "com.mysql.jc.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        //遍历查询结果集
        //加载驱动程序
        //Class.forName(driver);
        //1.getConnection()方法，连接MySQL数据库！！
        try(Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booksystem?serverTimezone=GMT%2B8","root","200425")) {
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from bookinfo where " + typeToCol.get(this.searchType) +"=" + "\"" + this.searchInfo+"\"";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            Vector temp = new Vector();
            while(rs.next()){
                temp.add(rs.getInt("bookid") + "");
                temp.add(rs.getString("bookname"));
                temp.add(rs.getString("bookauthor"));
                temp.add(rs.getString("booktheme"));
                temp.add(rs.getInt("bookstatus") + "");
                bookInfo.add(temp.clone()); //不可以直接添加，否则clear()之后，bookInfo中存储的也会clear
                temp.clear();
            }
        } catch(SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return bookInfo;
    }
}
