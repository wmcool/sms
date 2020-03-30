package Department;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartChange extends JFrame {
    private String deptId;

    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JComboBox jComboBox1 = new JComboBox();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();

    public DepartChange(String deptId) throws HeadlessException {
        deptId = deptId;
        try {
            init();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void init() throws SQLException {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(465, 280));
        setTitle("涓撲笟淇敼");

        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
        jLabel1.setText("涓� 涓� 淇� 鎭� 淇� 鏀�");
        jLabel1.setBounds(new Rectangle(136, 20, 212, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel2.setText("涓撲笟缂栧彿锛�");
        jLabel2.setBounds(new Rectangle(80, 80, 90, 20));

        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel3.setText("鎵�灞炲闄細");
        jLabel3.setBounds(new Rectangle(80, 130, 90, 20));

        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel4.setText("涓撲笟鍚嶇О锛�");
        jLabel4.setBounds(new Rectangle(80, 180, 90, 20));

        jTextField1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(180, 80, 180, 25));

        jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox1.setEditable(true);
        jComboBox1.setBounds(new Rectangle(180, 130, 180, 25));

        jTextField2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setBounds(new Rectangle(180, 180, 180, 25));

        jButton1.setBounds(new Rectangle(102, 223, 96, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("淇�  鏀�");
        jButton1.addActionListener(new DepartChange_jButton1_actionAdapter(this));

        jButton2.setBounds(new Rectangle(265, 221, 96, 31));
        jButton2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setToolTipText("");
        jButton2.setText("閫�  鍑�");
        jButton2.addActionListener(new DepartChange_jButton2_actionAdapter(this));

        contentPane.add(jLabel1);
        contentPane.add(jOptionPane1);
        contentPane.add(jComboBox1);
        contentPane.add(jLabel3);
        contentPane.add(jLabel2);
        contentPane.add(jTextField1);
        contentPane.add(jLabel4);
        contentPane.add(jTextField2);
        contentPane.add(jButton2);
        contentPane.add(jButton1);
        jComboBox1.addItem("璇烽�夋嫨瀛﹂櫌");

        //灏嗗闄㈢殑淇℃伅灞曠ず鍒板閫夋涓�
        ResultSet rs = DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).query("select * from school");
        while (rs.next()) {
            String school = rs.getString("schoolName");
            jComboBox1.addItem(school);
        }
    }

    //鎻愪氦
    public void jButton1_actionPerformed(ActionEvent e) {
        //寮傚父鍒ゆ柇
        if (this.jTextField1.getText().trim().length() == 0) {
            jOptionPane1.showMessageDialog(this, "涓撲笟缂栧彿涓嶈兘涓虹┖!", "鎻愮ず", jOptionPane1.INFORMATION_MESSAGE, null);
        } else if (jComboBox1.getSelectedIndex() == 0) {
            jOptionPane1.showMessageDialog(this, "璇烽�夋嫨瀛﹂櫌!", "鎻愮ず", jOptionPane1.INFORMATION_MESSAGE, null);
        } else if (jTextField2.getText().trim().length() == 0) {
            jOptionPane1.showMessageDialog(this, "璇峰～鍐欎笓涓氬悕绉�!", "鎻愮ず", jOptionPane1.INFORMATION_MESSAGE, null);
        } else {
            String departId = jTextField1.getText().trim();
            String departName = jTextField2.getText().trim();

            boolean overlap = false;
            ResultSet rs = DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).query("select deptId, departName from department;");
            try {
                while (rs.next()) {
                    if (departId.equals(rs.getString("deptId").trim()) ||
                            departName.equals(rs.getString("departName").trim())) {
                        overlap = true;
                    }
                }
            } catch (SQLException ec) {
                ec.printStackTrace();
            }
            if (overlap) {
                jOptionPane1.showMessageDialog(this, "涓撲笟缂栧彿鎴栧悕绉板凡缁忓瓨鍦紒", "鎻愮ず", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).Update("update department set deptId='" + departId + "',schoolName='" + jComboBox1.getSelectedItem().toString() +
                        "',departName='" + departName + "' where deptId='" + deptId + "';");
                jOptionPane1.showMessageDialog(this, "鎭枩鎮ㄤ笓涓氫俊鎭綍鍏ユ垚鍔燂紒", "鎻愮ず", JOptionPane.INFORMATION_MESSAGE, null);
            }

        }
    }

    //閫�鍑�
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    class DepartChange_jButton1_actionAdapter implements ActionListener {
        private DepartChange adaptee;

        DepartChange_jButton1_actionAdapter(DepartChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton1_actionPerformed(e);
        }
    }

    class DepartChange_jButton2_actionAdapter implements ActionListener {
        private DepartChange adaptee;

        DepartChange_jButton2_actionAdapter(DepartChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }

}
