package booksystem.ui;

import booksystem.dao.DeleteBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete extends JFrame{
    private JButton deleteBook;
    private JTextField bookId;
    private JLabel sign;

    public Delete() {
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prepareUI();
    }

    public void showUI() {
        this.add(sign);
        this.add(bookId);
        this.add(deleteBook);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void prepareUI() {
        sign = new JLabel("请输入要删除的图书编号:");
        sign.setFont(new Font("宋体",0,20));

        bookId = new JTextField();
        bookId.setFont(new Font("宋体",0,20));
        bookId.setColumns(20);

        deleteBook = new JButton("确认删除");
        deleteBook.setFont(new Font("宋体",0,20));

        deleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBookActionPerformed(e);
            }
        });
    }

    private void returnBookActionPerformed(ActionEvent e) {
        DeleteBook rb = new DeleteBook(bookId.getText());
        if(rb.complete)
            JOptionPane.showMessageDialog(this, "成功删除！");
        else
            JOptionPane.showMessageDialog(this, "这本书已被借出！");
    }
}
