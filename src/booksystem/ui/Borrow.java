package booksystem.ui;

import booksystem.dao.BorrowBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Borrow extends JFrame{
    private JButton returnBook;
    private JTextField bookId;
    private JLabel sign;

    public Borrow() {
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prepareUI();
    }

    void showUI() {
        this.add(sign);
        this.add(bookId);
        this.add(returnBook);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void prepareUI() {
        sign = new JLabel("请输入要借阅的图书编号:");
        sign.setFont(new Font("宋体",0,20));

        bookId = new JTextField();
        bookId.setFont(new Font("宋体",0,20));
        bookId.setColumns(20);

        returnBook = new JButton("确认借阅");
        returnBook.setFont(new Font("宋体",0,20));

        returnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBookActionPerformed(e);
            }
        });
    }

    private void returnBookActionPerformed(ActionEvent e) {
        BorrowBook rb = new BorrowBook(bookId.getText(), Login.user.getUserId());
        if(rb.complete)
            JOptionPane.showMessageDialog(this, "借阅成功！");
        else
            JOptionPane.showMessageDialog(this, "当前书已被借阅！");
    }
}
