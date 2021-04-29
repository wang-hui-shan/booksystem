package booksystem.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 普通用户登陆后显示的主界面
 * 包含功能入口：用户借阅信息 查询图书 借阅图书 归还图书 登出 修改密码
 * 按钮触发事件：
 * borrowBookButtonActionPerformed returnBookButtonActionPerformed
 * logoutButtonActionPerformed modifyPassButtonActionPerformed
 * searchBookButtonActionPerformed userInfoButtonActionPerformed
 */
public class UserMain extends JFrame {
    private JPanel borrowInfo,searchBook,borrowBook,
            returnBook,modifyPass,logout;
    private JButton userInfoButton,searchBookButton,borrowBookButton,
            returnBookButton,modifyPassButton,logoutButton;

    public UserMain() {
        this.setTitle("主界面");
        //setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        this.setLayout(new GridLayout(6,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        borrowInfo = new JPanel();
        searchBook = new JPanel();
        borrowBook = new JPanel();
        returnBook = new JPanel();
        modifyPass = new JPanel();
        logout = new JPanel();

        prepareUI();
    }

    public void showUI(){
        borrowInfo.add(userInfoButton);
        searchBook.add(searchBookButton);
        borrowBook.add(borrowBookButton);
        returnBook.add(returnBookButton);
        modifyPass.add(modifyPassButton);
        logout.add(logoutButton);

        this.add(borrowInfo);
        this.add(searchBook);
        this.add(borrowBook);
        this.add(returnBook);
        this.add(modifyPass);
        this.add(logout);

        this.setBounds(400,200,320, 320); /** 设置窗体大小 */
        this.setResizable(false); /** 不可放大 */
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void prepareUI() {
        userInfoButton = new JButton("借阅记录");
        userInfoButton.setFont(new Font("宋体", 0, 20));

        searchBookButton = new JButton("查询图书");
        searchBookButton.setFont(new Font("宋体", 0, 20));

        borrowBookButton = new JButton("借阅图书");
        borrowBookButton.setFont(new Font("宋体", 0, 20));

        returnBookButton = new JButton("归还图书");
        returnBookButton.setFont(new Font("宋体", 0, 20));

        modifyPassButton = new JButton("修改密码");
        modifyPassButton.setFont(new Font("宋体", 0, 20));

        logoutButton = new JButton("登出");
        logoutButton.setFont(new Font("宋体", 0, 20));

        //个人信息
        userInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInfoButtonActionPerformed(e);
            }
        });
        //图书查询
        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBookButtonActionPerformed(e);
            }
        });
        //借阅图书
        borrowBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrowBookButtonActionPerformed(e);
            }
        });
        //归还图书
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBookButtonActionPerformed(e);
            }
        });
        //登出
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutButtonActionPerformed(e);
            }
        });
        //修改密码
        modifyPassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyPassButtonActionPerformed(e);
            }
        });
    }

    private void borrowBookButtonActionPerformed(ActionEvent e) {
        Borrow b = new Borrow();
        b.showUI();
    }

    private void returnBookButtonActionPerformed(ActionEvent e) {
        Return rb = new Return();
        rb.showUI();
    }

    private void logoutButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void modifyPassButtonActionPerformed(ActionEvent e) {
        Modify m = new Modify();
        m.showUi();
    }

    private void searchBookButtonActionPerformed(ActionEvent e) {
        Search s = new Search();
        s.showUI();
    }

    private void userInfoButtonActionPerformed(ActionEvent e) {
        BorrowRecords userinfo = new BorrowRecords();
        userinfo.showUI();
    }
}
