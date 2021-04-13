package booksystem.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登陆后显示的主界面
 * 包含功能入口：用户个人信息 查询图书 登出 修改密码
 */
public class AdminMain extends JFrame {
    private JPanel searchBook,addBook,deleteBook,modifyPass,logout;
    private JButton searchBookButton,
            addBookButton,deleteBookButton,modifyPassButton,logoutButton;
    public AdminMain() {
        this.setTitle("主界面");
        this.setLayout(new GridLayout(5,1));

        searchBook = new JPanel();

        addBook = new JPanel();
        deleteBook = new JPanel();
        modifyPass = new JPanel();
        logout = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        prepareUI();
    }
    public void showUI(){
        searchBook.add(searchBookButton);
        addBook.add(addBookButton);
        deleteBook.add(deleteBookButton);
        modifyPass.add(modifyPassButton);
        logout.add(logoutButton);

        this.add(searchBook);
        this.add(addBook);
        this.add(deleteBook);
        this.add(modifyPass);
        this.add(logout);

        this.setBounds(400,200,320, 320); /** 设置窗体大小 */
        this.setResizable(false); /** 不可放大 */
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void prepareUI() {
        searchBookButton = new JButton("查询图书");
        searchBookButton.setFont(new Font("宋体", 0, 20));

        modifyPassButton = new JButton("修改密码");
        modifyPassButton.setFont(new Font("宋体", 0, 20));

        addBookButton = new JButton("添加图书");
        addBookButton.setFont(new Font("宋体", 0, 20));

        deleteBookButton = new JButton("删除图书");
        deleteBookButton.setFont(new Font("宋体", 0, 20));

        logoutButton = new JButton("登出");
        logoutButton.setFont(new Font("宋体", 0, 20));

        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBookButtonActionPerformed(e);
            }
        });
        modifyPassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyPassButtonActionPerformed(e);
            }
        });
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookButtonActionPerformed(e);
            }
        });
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBookButtonActionPerformed(e);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutButtonActionPerformed(e);
            }
        });
    }
    // 删除图书
    private void deleteBookButtonActionPerformed(ActionEvent e) {
        Delete d = new Delete();
        d.showUi();
    }
    // 增加图书
    private void addBookButtonActionPerformed(ActionEvent e) {
        Add_ a = new Add_();
        a.showUi();
    }
    //查询图书
    private void searchBookButtonActionPerformed(ActionEvent e) {
        Search s = new Search();
        s.showUi();
    }
    //登出
    private void logoutButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }
    // 修改密码
    private void modifyPassButtonActionPerformed(ActionEvent e) {
        Modify m = new Modify();
        m.showUi();
    }


}
