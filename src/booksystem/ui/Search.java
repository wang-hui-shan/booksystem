package booksystem.ui;

import booksystem.dao.SearchBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * 显示图书查询的界面
 */
public class Search extends JFrame{
    private JComboBox<String> searchType;
    private JTextField searchArea;
    private JButton search;
    private JTable bookTable;
    private JPanel searchAreaPanel;
    private JScrollPane bookTablePanel;
    private DefaultTableModel tableModel;
    public Search() {
        this.setTitle("查询");
        //setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        prepareUI();
        searchAreaPanel = new JPanel(new FlowLayout());
    }
    void showUi() {
        searchAreaPanel.add(searchType);
        searchAreaPanel.add(searchArea);
        searchAreaPanel.add(search);

        bookTablePanel = new JScrollPane(bookTable);


        this.add(searchAreaPanel,BorderLayout.NORTH);
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


        tableModel = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        bookTable = new JTable(tableModel);
        bookTable.setFont(new Font("宋体", 0, 20));
        bookTable.setAutoscrolls(true);
        bookTable.setRowHeight(24);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonActionPerformed(e);
            }
        });
    }

    private void searchButtonActionPerformed(ActionEvent e) {
        String type = (String)searchType.getSelectedItem();
        Vector bookInfo = new Vector();

        //Object[][] books = new Object[][];
        SearchBook sb = new SearchBook(searchArea.getText(), type);
        bookInfo = sb.getBook();

        //int numRows = bookInfo.size();
        Vector colsName = new Vector();
        colsName.add("图书编号");
        colsName.add("书名");
        colsName.add("作者");
        colsName.add("类型");
        colsName.add("借阅状态");

        tableModel.setDataVector(bookInfo, colsName);
        //bookList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// 设置表格列宽
    }
}
