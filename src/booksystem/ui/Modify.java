package booksystem.ui;

import booksystem.dao.ModifyPassword;
import booksystem.dao.LoginVerify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modify extends JFrame {
    private JLabel oldPassLabel, newPassLabel,confirmPassLabel;
    private JPasswordField oldPass,newPass,confirmPass;
    private JPanel oldPassPanel,newPassPanel,confirmPassPanel,confirmPanel;
    private JButton confirm;
    public Modify() {
        this.setTitle("修改密码");
        //setIconImage(new ImageIcon(getClass().getResource("")).getImage());
        this.setLayout(new GridLayout(4,1));

        oldPassPanel = new JPanel();
        oldPassPanel.setLayout(new FlowLayout());

        newPassPanel = new JPanel();
        newPassPanel.setLayout(new FlowLayout());

        confirmPassPanel = new JPanel();
        confirmPassPanel.setLayout(new FlowLayout());

        confirmPanel = new JPanel();

        this.setBounds(400,200,420, 240); /** 设置窗体大小 */
        this.setResizable(false); /** 不可放大 */
        //this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        prepareUI();
    }
    public void showUi() {
        oldPassPanel.add(oldPassLabel);
        oldPassPanel.add(oldPass);

        newPassPanel.add(newPassLabel);
        newPassPanel.add(newPass);

        confirmPassPanel.add(confirmPassLabel);
        confirmPassPanel.add(confirmPass);

        confirmPanel.add(confirm);

        this.add(oldPassPanel);
        this.add(newPassPanel);
        this.add(confirmPassPanel);
        this.add(confirmPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void prepareUI() {
        oldPassLabel = new JLabel("请输入旧密码:");
        oldPassLabel.setFont(new Font("宋体",0,20));

        oldPass = new JPasswordField(20);
        oldPass.setFont(new Font("宋体",0,20));

        newPassLabel = new JLabel("请输入新密码:");
        newPassLabel.setFont(new Font("宋体",0,20));

        newPass = new JPasswordField(20);
        newPass.setFont(new Font("宋体",0,20));

        confirmPassLabel = new JLabel("请再输入一次:");
        confirmPassLabel.setFont(new Font("宋体",0,20));

        confirmPass = new JPasswordField(20);
        confirmPass.setFont(new Font("宋体",0,20));

        confirm = new JButton("确认修改");
        confirm.setFont(new Font("宋体",0,20));

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmActionPeformed(e);
            }
        });
    }

    private void confirmActionPeformed(ActionEvent e) {
        String oldPassword = new String(oldPass.getPassword());
        String password = new String(newPass.getPassword());
        String confirmPassword = new String(confirmPass.getPassword());

        //验证输入密码是否正确
        LoginVerify v = new LoginVerify(Login.user.getUsername(), oldPassword, Login.user.getRole());
        v.validate();
        if(oldPassword.equals("") || password.equals("") || confirmPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "不能为空！");
        } else if(oldPassword.equals(password)){
            JOptionPane.showMessageDialog(this, "新密码和旧密码一致！");
        } else if(!confirmPassword.equals(password)){
            JOptionPane.showMessageDialog(this, "新密码两次输入不一致！");
        } else if(v.in){ //旧密码输入正确
            ModifyPassword mp = new ModifyPassword(Login.user.getUserId(), password, Login.user.getRole());
            if(mp.complete) {
                JOptionPane.showMessageDialog(this, "修改成功！");
            } else {
                JOptionPane.showMessageDialog(this, "系统出错,请再次修改！");
            }
        } else {
            JOptionPane.showMessageDialog(this, "旧密码错误！");
        }
    }
}
