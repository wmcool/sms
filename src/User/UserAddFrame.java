package User;

import Department.DepartmentAddFrame;
import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddFrame extends JFrame {
    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserAddFrame window = new UserAddFrame();
                window.setVisible(true);
            }
        });
    }
    public UserAddFrame() throws HeadlessException {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(){
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
//        setSize(new Dimension(465, 280));
        setSize(new Dimension(465, 320));
        setTitle("用户注册");

        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
        jLabel1.setText("用 户 注 册");
        jLabel1.setBounds(new Rectangle(136, 20, 212, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel2.setText("用户名：");
        jLabel2.setBounds(new Rectangle(80, 80, 90, 20));

        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel3.setText("密码：");
        jLabel3.setBounds(new Rectangle(80, 130, 90, 20));

        jTextField1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(180, 80, 180, 25));

        jTextField2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setBounds(new Rectangle(180, 130, 180, 25));

        jButton1.setBounds(new Rectangle(102, 223, 96, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("确  定");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextField1.getText().trim().length() == 0) {
                    jOptionPane1.showMessageDialog(null, "用户名不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
                } else if (jTextField2.getText().trim().length() == 0) {
                    jOptionPane1.showMessageDialog(null, "密码不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
                } else {
                    boolean overlap = false;
                    ResultSet rs = DBConnection.getDBConnection().query("select userName from user;");
                    try {
                        while (rs.next()) {
                            if (jTextField1.getText().trim().equals(rs.getString("userName").trim())) {
                                overlap = true;
                            }
                        }
                    } catch (SQLException ec) {
                        ec.printStackTrace();
                    }
                    if (overlap) {
                        jOptionPane1.showMessageDialog(null, "用户名已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                    } else {
                        DBConnection.getDBConnection().Update("insert into user values ('" + jTextField1.getText().trim() + "','" + jTextField2.getText() + "',\"student\")");
                        jOptionPane1.showMessageDialog(null, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                    }
                }
            }
        });

        jButton2.setBounds(new Rectangle(265, 221, 96, 31));
        jButton2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setToolTipText("");
        jButton2.setText("退  出");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        contentPane.add(jLabel1);
        contentPane.add(jOptionPane1);
        contentPane.add(jLabel3);
        contentPane.add(jLabel2);
        contentPane.add(jTextField1);
        contentPane.add(jTextField2);
        contentPane.add(jButton2);
        contentPane.add(jButton1);
    }
}
