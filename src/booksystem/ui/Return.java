package booksystem.ui;

import booksystem.bean.Book;
import booksystem.dao.ReturnBook;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Return extends JFrame{
    private JButton returnBook;
    private JTextField bookId;
    private JLabel sign;

    public Return() {
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
        sign = new JLabel("请输入要归还的图书编号:");
        sign.setFont(new Font("宋体",0,20));

        bookId = new JTextField();
        bookId.setFont(new Font("宋体",0,20));
        bookId.setColumns(20);

        returnBook = new JButton("确认归还");
        returnBook.setFont(new Font("宋体",0,20));

        returnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBookActionPerformed(e);
            }
        });
    }

    private void returnBookActionPerformed(ActionEvent e) {
        Book book = new Book();
        book.setBookid(Integer.parseInt(bookId.getText()));
        ReturnBook rb = new ReturnBook(book, Login.user.getUserId());
        if(rb.complete) {
            JOptionPane.showMessageDialog(this, "成功归还！");
        } else {
            JOptionPane.showMessageDialog(this, "你没有这本书！");
        }
    }
}
