package booksystem.ui;

import booksystem.dao.UserRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Register extends JFrame {
    private JLabel userNameLabel,userPassLabel,ensurePassLabel;
    private JTextField userName;
    private JPasswordField userPass,ensurePass;
    private JButton backButton, registerButton;
    private JPanel name;
    private JPanel pass;
    private JPanel ensure;
    private JPanel button;

    public Register() {
        this.setTitle("注册界面");
        //setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        this.setLayout(new GridLayout(4,1));
        name = new JPanel();
        name.setLayout(new FlowLayout());

        pass = new JPanel();
        pass.setLayout(new FlowLayout());

        ensure = new JPanel();
        ensure.setLayout(new FlowLayout());

        button = new JPanel(new FlowLayout());

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prepareUI();
    }

    void showUI() {
        name.add(userNameLabel);
        name.add(userName);

        pass.add(userPassLabel);
        pass.add(userPass);

        ensure.add(ensurePassLabel);
        ensure.add(ensurePass);

        button.add(registerButton);
        button.add(backButton);

        this.add(name);
        this.add(pass);
        this.add(ensure);
        this.add(button);

        this.setBounds(400,200,420, 260); /** 设置窗体大小 */
        this.setResizable(false); /** 不可放大 */
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    void prepareUI() {
        userNameLabel = new JLabel();
        userNameLabel.setFont(new Font("宋体",0,20));
        userNameLabel.setText("请输入用户名:");

        userName = new JTextField(20);
        userName.setFont(new Font("宋体",0,20));

        userPassLabel = new JLabel();
        userPassLabel.setFont(new Font("宋体",0,20));
        userPassLabel.setText("请输入密  码:");

        userPass = new JPasswordField(20);
        userPass.setFont(new Font("宋体",0,20));

        ensurePassLabel = new JLabel();
        ensurePassLabel.setFont(new Font("宋体",0,20));
        ensurePassLabel.setText("再次输入密码:");

        ensurePass = new JPasswordField(20);
        ensurePass.setFont(new Font("宋体",0,20));

        registerButton = new JButton("确认注册");
        registerButton.setFont(new Font("宋体", 0, 20));

        // 登录按钮设置快捷键
        registerButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '\n'){
                    registerButton.doClick();
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButtonActionPerformed(e);
            }
        });

        backButton = new JButton("返回登录页面");
        backButton.setFont(new Font("宋体", 0, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonActionPerformed(e);
            }
        });
    }

    private void backButtonActionPerformed(ActionEvent e) {
        Login l = new Login();
        l.showUI();
        this. dispose();
    }

    private void registerButtonActionPerformed(ActionEvent e) {
        if(!new String(userPass.getPassword()).equals(new String(ensurePass.getPassword()))) {
            JOptionPane.showMessageDialog(this, "两次密码不一致！");
        } else {
            UserRegister ur = new UserRegister(userName.getText(), new String(userPass.getPassword()));
            if(ur.complete) {
                //注册成功
                JOptionPane.showMessageDialog(this, "注册成功！");
            } else {
                JOptionPane.showMessageDialog(this, "用户名已存在！");
            }
        }
    }

}
