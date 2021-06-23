package booksystem.ui;

import booksystem.dao.SearchBook;
import booksystem.bean.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 显示图书查询的界面
 */
public class Search extends JFrame{
    private JComboBox<String> searchType;
    private JTextField searchArea;
    private JButton search;
    private JTable bookList;
    private JPanel searchAreaPanel;
    private JScrollPane bookTablePanel;
    private DefaultTableModel tableModel;

    public Search() {
        this.setTitle("查询");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        searchAreaPanel = new JPanel(new FlowLayout());
        prepareUI();
    }

    public void showUI() {
        searchAreaPanel.add(searchType);
        searchAreaPanel.add(searchArea);
        searchAreaPanel.add(search);

        this.add(searchAreaPanel,BorderLayout.NORTH);
        bookTablePanel = new JScrollPane(bookList);
        this.add(bookTablePanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void prepareUI() {
        searchType = new JComboBox(new String[]{"书名","图书编号","作者"});
        searchType.setFont(new Font("宋体",0,20));

        searchArea = new JTextField();
        searchArea.setFont(new Font("宋体",0,20));
        searchArea.setColumns(20);

        search = new JButton("搜索");
        search.setFont(new Font("宋体",0,20));

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonActionPerformed(e);
            }
        });

        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        bookList = new JTable(tableModel);
        bookList.setFont(new Font("宋体", 0, 20));
        bookList.setAutoscrolls(true);
        bookList.setRowHeight(24);
        bookTablePanel = new JScrollPane(bookList);
    }

    private void searchButtonActionPerformed(ActionEvent e) {
        String type = (String)searchType.getSelectedItem();

        SearchBook sb = new SearchBook(searchArea.getText(), type);
        ArrayList<Book> books = sb.getBook();

        String[] colsName = {"图书编号","书名","作者","类型","借阅状态"};
        int Rows = books.size();
        int Cols = colsName.length;
        Object[][] table = new Object[Rows][Cols];
        for (int i = 0; i < Rows; i++) {
            table[i][0] = books.get(i).getBookid();
            table[i][1] = books.get(i).getBookname();
            table[i][2] = books.get(i).getBookauthor();
            table[i][3] = books.get(i).getBooktheme();
            table[i][4] = books.get(i).getBookstatus();
        }
        tableModel.setDataVector(table,colsName);
    }
}
