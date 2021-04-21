package booksystem.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import booksystem.dao.LoginVerify;
import booksystem.user.AbsUser;
import booksystem.user.Admin;
import booksystem.user.User;

/**
 * 显示登录界面
 * 继承java.awt.JFrame
 */
public class Login extends JFrame {
    /* 入口
    public static void main(String[] args) {
        (new Login()).showUI();
    }
    */
    private JLabel welcome,userNameLabel,userPassLabel;
    private JTextField userName;
    private JPasswordField userPass;
    private JComboBox<String> userType; /**用户类型下拉框*/
    private JButton loginButton, registerButton;
    private JPanel name;
    private JPanel pass;
    private JPanel type;
    private JPanel button;
    static AbsUser user;
    /**
     * 初始化login frame的属性
     */
    public Login() {
        this.setTitle("登陆界面");
        //setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        this.setLayout(new GridLayout(5,1));
        name = new JPanel();

        pass = new JPanel();

        button = new JPanel();

        type = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        prepareUI();
    }
    void showUI() {
        name.add(userNameLabel);
        name.add(userName);

        pass.add(userPassLabel);
        pass.add(userPass);

        button.add(loginButton);
        button.add(registerButton);

        type.add(userType);

        this.add(welcome);
        this.add(name);
        this.add(pass);
        this.add(type);
        this.add(button);

        this.setBounds(400,200,320, 250); /** 设置窗体大小 */
        this.setResizable(false); /** 不可放大 */
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void prepareUI() {
        welcome = new JLabel("图书管理系统",JLabel.CENTER);
        welcome.setFont(new Font("宋体",0,32));
        welcome.setForeground(Color.CYAN);

        userNameLabel = new JLabel();
        userNameLabel.setFont(new Font("宋体",0,20));
        userNameLabel.setText("用户名:");

        userName = new JTextField(20);
        userName.setFont(new Font("宋体",0,20));

        userPassLabel = new JLabel();
        userPassLabel.setFont(new Font("宋体",0,20));
        userPassLabel.setText("密  码:");

        userPass = new JPasswordField(20);
        userPass.setFont(new Font("宋体",0,20));

        userType = new JComboBox<String>(new String[]{"user","admin"});

        loginButton = new JButton("登录");
        loginButton.setFont(new Font("宋体", 0, 20));

        // 登录按钮设置快捷键
        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '\n'){
                    loginButton.doClick();
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButtonActionPerformed(e);
            }
        });

        registerButton = new JButton("注册");
        registerButton.setFont(new Font("宋体", 0, 20));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButtonActionPerformed(e);
            }
        });
    }

    private void registerButtonActionPerformed(ActionEvent e) {
        Register r = new Register();
        r.showUI();
        this.dispose();
    }
    private void loginButtonActionPerformed(ActionEvent e) {
        // 获取用户输入的用户名和密码
        String username = userName.getText();
        String password = new String(userPass.getPassword());
        String role = (String)userType.getSelectedItem();
        LoginVerify v = new LoginVerify(username, password, role);
        int userid = v.validate();
        //登陆成功
        //隐藏LoginForm，显示MainForm
        if(v.in){
            if(role == "user"){
                this.user = new User(userid, role, username);
                this.setVisible(false);
                UserMain mainUi = new UserMain();
                mainUi.showUI();
            } else {
                this.user = new Admin(userid, role, username);
                this.setVisible(false);
                AdminMain mainUi = new AdminMain();
                mainUi.showUI();
            }
        } else{
            //登录失败
            JOptionPane.showMessageDialog(this, "用户名或密码错误！");
        }
    }
}
