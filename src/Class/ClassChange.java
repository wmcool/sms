package Class;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassChange extends JFrame {
    private String classId;

    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel4 = new JLabel();
    JComboBox jComboBox2 = new JComboBox();

    public ClassChange(String classId) throws HeadlessException {
        this.classId = classId;
        try {
            init();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void init() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(465, 280));
        setTitle("班级修改");

        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
        jLabel1.setText("班 级 信 息 修 改");
        jLabel1.setBounds(new Rectangle(136, 20, 212, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel2.setText("班号：");
        jLabel2.setBounds(new Rectangle(80, 80, 90, 20));

        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel3.setText("所属学院：");
        jLabel3.setBounds(new Rectangle(80, 130, 90, 20));

        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel4.setText("所属专业：");
        jLabel4.setBounds(new Rectangle(80, 180, 90, 20));

        jTextField1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(180, 80, 180, 25));

        jButton1.setBounds(new Rectangle(102, 223, 96, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("修  改");
        jButton1.addActionListener(new ClassChange_jButton1_actionAdapter(this));

        jButton2.setBounds(new Rectangle(265, 221, 96, 31));
        jButton2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setToolTipText("");
        jButton2.setText("退  出");
        jButton2.addActionListener(new ClassChange_jButton2_actionAdapter(this));

        jOptionPane1.setBounds(new Rectangle(106, 258, 262, 90));
        jOptionPane1.setLayout(null);

        jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox1.setEditable(true);
        jComboBox1.setBounds(new Rectangle(180, 130, 180, 25));
        jComboBox1.addActionListener(new ClassChange_jComboBox1_actionAdapter(this));

        jComboBox2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox2.setEditable(true);
        jComboBox2.setBounds(new Rectangle(180, 180, 180, 25));

        contentPane.add(jLabel1);
        contentPane.add(jOptionPane1);
        contentPane.add(jComboBox1);
        contentPane.add(jLabel3);
        contentPane.add(jLabel2);
        contentPane.add(jTextField1);
        contentPane.add(jLabel4);
        contentPane.add(jComboBox2);
        contentPane.add(jButton2);
        contentPane.add(jButton1);
        jComboBox1.addItem("请选择学院");

        //将学院的信息展示到复选框中
        ResultSet rs = DBConnection.getDBConnection().query("select * from school");
        while (rs.next()) {
            String school = rs.getString("schoolName");
            jComboBox1.addItem(school);
        }
        jComboBox2.setEnabled(false);
    }

    //选择学院后，填充专业
    public void fillDepart() {
        jComboBox2.removeAllItems();
        jComboBox2.addItem("请选择专业");
        ResultSet rs = DBConnection.getDBConnection().query("select *  from department where schoolName='" +
                String.valueOf(jComboBox1.getSelectedItem()) + "'");
        try {
            while (rs.next()) {
                String depart = rs.getString("departName");
                jComboBox2.addItem(depart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void jComboBox1_actionPerformed(ActionEvent e) {
        fillDepart();
        jComboBox2.setEnabled(true);
    }

    //提交
    public void jButton1_actionPerformed(ActionEvent e) {
        //异常判断
        if (this.jTextField1.getText().trim().length() == 0) {
            jOptionPane1.showMessageDialog(this, "班号不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
        } else if (jComboBox1.getSelectedIndex() == 0) {
            jOptionPane1.showMessageDialog(this, "请选择学院!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
        } else if (jComboBox2.getSelectedIndex() == 0) {
            jOptionPane1.showMessageDialog(this, "请选择专业!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
        } else {
            String school = jComboBox1.getSelectedItem().toString();
            String department = jComboBox2.getSelectedItem().toString();

            boolean overlap = false;
            ResultSet rs = DBConnection.getDBConnection().query("select classId from class;");
            try {
                while (rs.next()) {
                    if (jTextField1.getText().trim().equals(rs.getString("classId").trim())) {
                        overlap = true;
                    }
                }
            } catch (SQLException ec) {
                ec.printStackTrace();
            }
            if (overlap) {
                jOptionPane1.showMessageDialog(this, "该班号已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                DBConnection.getDBConnection().Update("update class set classId='" + jTextField1.getText().trim() + "',schoolName='" + jComboBox1.getSelectedItem().toString()
                        + "',departName='" + jComboBox2.getSelectedItem().toString() + "' where classId='" + classId + "';");
                jOptionPane1.showMessageDialog(this, "恭喜您班级信息修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
            }

        }
    }

    //退出
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    class ClassChange_jComboBox1_actionAdapter implements ActionListener {
        private ClassChange adaptee;

        ClassChange_jComboBox1_actionAdapter(ClassChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jComboBox1_actionPerformed(e);
        }
    }

    class ClassChange_jButton1_actionAdapter implements ActionListener {
        private ClassChange adaptee;

        ClassChange_jButton1_actionAdapter(ClassChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton1_actionPerformed(e);
        }
    }

    class ClassChange_jButton2_actionAdapter implements ActionListener {
        private ClassChange adaptee;

        ClassChange_jButton2_actionAdapter(ClassChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }
}
