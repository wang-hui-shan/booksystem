package booksystem.ui;

import booksystem.dao.GetBorrowBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

/**
 * 查看用户信息的界面
 */

public class BorrowRecords extends JFrame {
    private JTable bookList;
    private JScrollPane table;
    public BorrowRecords() {
        this.setTitle("借阅信息");
        //setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prepareUI();
    }
    void showUI() {
        table = new JScrollPane(bookList); // 要在定义好table之后再构造
        this.add(table);

        //this.setBounds(400,200,420, 5200); /** 设置窗体大小 */
        //this.setResizable(false); /** 不可放大 */
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void prepareUI() {
        GetBorrowBook ub = new GetBorrowBook(Login.user.getUserId());
        HashMap<Integer,String> books = ub.getBookList();
        int numRows = books.size();
        Object[][] table = new Object[numRows][2];
        for(Integer bookid : books.keySet()) {
            table[numRows-1][0] = bookid;
            table[(numRows--)-1][1] = books.get(bookid);
        }
        String[] colsName = {"图书编号","书名"};
        bookList = new JTable(new DefaultTableModel(table, colsName){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });//设置表格不可更改
        //bookList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// 设置表格列宽
        bookList.setFont(new Font("宋体", 0, 20));
        bookList.setAutoscrolls(true);
        bookList.setRowHeight(24);
    }
}
