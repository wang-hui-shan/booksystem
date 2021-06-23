package booksystem.ui;

import booksystem.dao.AddBook;
import booksystem.bean.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add extends JFrame {
    private JButton addButton;
    private JLabel name, author, theme;
    private JTextField bookName, bookAuthor, bookTheme;
    private JPanel namePanel, authorPanel, themePanel, buttonPanel;

    public Add() {
        this.setLayout(new GridLayout(4,1));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        namePanel = new JPanel(new FlowLayout());
        authorPanel = new JPanel(new FlowLayout());
        themePanel = new JPanel(new FlowLayout());
        buttonPanel = new JPanel();

        prepareUi();
    }
    public void showUi() {
        buttonPanel.add(addButton);
        namePanel.add(name);
        namePanel.add(bookName);

        authorPanel.add(author);
        authorPanel.add(bookAuthor);

        themePanel.add(theme);
        themePanel.add(bookTheme);

        this.add(namePanel);
        this.add(authorPanel);
        this.add(themePanel);
        this.add(buttonPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void prepareUi() {
        name = new JLabel("书名:");
        name.setFont(new Font("宋体",0,20));
        bookName = new JTextField();
        bookName.setFont(new Font("宋体",0,20));
        bookName.setColumns(30);

        author = new JLabel("作者:");
        author.setFont(new Font("宋体",0,20));
        bookAuthor = new JTextField();
        bookAuthor.setFont(new Font("宋体",0,20));
        bookAuthor.setColumns(30);

        theme = new JLabel("主题:");
        theme.setFont(new Font("宋体",0,20));
        bookTheme = new JTextField();
        bookTheme.setFont(new Font("宋体",0,20));
        bookTheme.setColumns(30);

        addButton = new JButton("添加");
        addButton.setSize(8,20);
        addButton.setFont(new Font("宋体",0,20));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonActionPerformed(e);
            }
        });
    }

    private void addButtonActionPerformed(ActionEvent e) {
        Book book = new Book();
        book.setBookname(bookName.getText());
        book.setBookauthor(bookAuthor.getText());
        book.setBooktheme(bookTheme.getText());

        AddBook ab = new AddBook(book);
        if(ab.complete)
            JOptionPane.showMessageDialog(this, "添加成功！");
        else
            JOptionPane.showMessageDialog(this, "添加失败！");
    }
}
