package booksystem.ui;

import booksystem.bean.Book;
import booksystem.dao.GetBorrowBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 查看用户借阅信息
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

        //this.setBounds(400,200,420, 520); /** 设置窗体大小 */
        //this.setResizable(false); /** 不可放大 */
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void prepareUI() {
        GetBorrowBook userBooks = new GetBorrowBook(Login.user.getUserId());
        ArrayList<Book> books = userBooks.getBookList();
        String[] colsName = {"图书编号","书名"};
        int Rows = books.size();
        int Cols = colsName.length;
        Object[][] table = new Object[Rows][Cols];
        for (int i = 0; i < Rows; i++) {
            table[i][0] = books.get(i).getBookid();
            table[i][1] = books.get(i).getBookname();
        }

        bookList = new JTable(new DefaultTableModel(table, colsName){
            @Override
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
