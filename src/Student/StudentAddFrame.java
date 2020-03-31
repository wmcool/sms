package Student;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAddFrame extends JFrame {
    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();

    JComboBox jComboBox1 = new JComboBox();
    JComboBox jComboBox2 = new JComboBox();
    JComboBox jComboBox3 = new JComboBox();

    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();

    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentAddFrame window = new StudentAddFrame();
                window.setVisible(true);
            }
        });
    }

    public StudentAddFrame() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws SQLException {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(465, 400));
        setTitle("学生录入");

        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
        jLabel1.setText("学 生 信 息 录 入");
        jLabel1.setBounds(new Rectangle(136, 20, 212, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel2.setText("学号：");
        jLabel2.setBounds(new Rectangle(80, 80, 90, 20));

        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel3.setText("姓名：");
        jLabel3.setBounds(new Rectangle(80, 130, 90, 20));

        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel4.setText("学院：");
        jLabel4.setBounds(new Rectangle(80, 180, 90, 20));

        jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel5.setText("专业：");
        jLabel5.setBounds(new Rectangle(80, 230, 90, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel6.setText("班级：");
        jLabel6.setBounds(new Rectangle(80, 280, 90, 20));

        jTextField1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(180, 80, 180, 25));

        jTextField2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setBounds(new Rectangle(180, 130, 180, 25));

        jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox1.setEditable(true);
        jComboBox1.setBounds(new Rectangle(180, 180, 180, 25));
        jComboBox1.addActionListener(new StudentAddFrame_jComboBox1_actionAdapter(this));

        jComboBox2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox2.setEditable(true);
        jComboBox2.setBounds(new Rectangle(180, 230, 180, 25));
        jComboBox2.addActionListener(new StudentAddFrame_jComboBox2_actionAdapter(this));

        jComboBox3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox3.setEditable(true);
        jComboBox3.setBounds(new Rectangle(180, 280, 180, 25));

        jButton1.setBounds(new Rectangle(102, 320, 96, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("提  交");
        jButton1.addActionListener(new StudentAddFrame_jButton1_actionAdapter(this));

        jButton2.setBounds(new Rectangle(265, 320, 96, 31));
        jButton2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setToolTipText("");
        jButton2.setText("退  出");
        jButton2.addActionListener(new StudentAddFrame_jButton2_actionAdapter(this));

        contentPane.add(jLabel1);
        contentPane.add(jLabel2);
        contentPane.add(jLabel3);
        contentPane.add(jLabel4);
        contentPane.add(jLabel5);
        contentPane.add(jLabel6);
        contentPane.add(jTextField1);
        contentPane.add(jTextField2);
        contentPane.add(jComboBox1);
        contentPane.add(jComboBox2);
        contentPane.add(jComboBox3);
        contentPane.add(jButton1);
        contentPane.add(jButton2);

        jComboBox1.addItem("请选择学院");
        //将学院的信息展示到复选框中
        ResultSet rs1 = DBConnection.getDBConnection().query("select * from school;");
        while (rs1.next()) {
            String school = rs1.getString("schoolName");
            jComboBox1.addItem(school);
        }
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
    }

    //选择学院后，填充专业
    public void fillDepart() {
        jComboBox2.removeAllItems();
        jComboBox2.addItem("请选择专业");
        ResultSet rs = DBConnection.getDBConnection().query("select *  from department where schoolName='" +
                String.valueOf(jComboBox1.getSelectedItem()) + "';");
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

    public void jComboBox2_actionPerformed(ActionEvent e) {
        jComboBox3.removeAllItems();
        jComboBox3.addItem("请选择班级");
        ResultSet rs = DBConnection.getDBConnection().query("select *  from class where schoolName='" +
                String.valueOf(jComboBox1.getSelectedItem()) + "' and departName='" + String.valueOf(jComboBox2.getSelectedItem()) + "';");
        try {
            while (rs.next()) {
                String depart = rs.getString("classId");
                jComboBox3.addItem(depart);
            }
        } catch (SQLException ec) {
            ec.printStackTrace();
        }
        jComboBox3.setEnabled(true);
    }

    //退出
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    //提交
    public void jButton1_actionPerformed(ActionEvent e) {
        //异常判断
        if (this.jTextField1.getText().trim().length() == 0) {
            jOptionPane1.showMessageDialog(this, "学号不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
        } else if (jTextField2.getText().trim().length() == 0) {
            jOptionPane1.showMessageDialog(this, "姓名不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
        } else if (jComboBox1.getSelectedIndex() == 0) {
            jOptionPane1.showMessageDialog(this, "请选择学院!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
        } else if (jComboBox2.getSelectedIndex() == 0) {
            jOptionPane1.showMessageDialog(this, "请选择专业!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
        } else if (jComboBox3.getSelectedIndex() == 0) {
            jOptionPane1.showMessageDialog(this, "请选择班级!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
        } else {
            String stuId = jTextField1.getText().trim();

            boolean overlap = false;
            ResultSet rs = DBConnection.getDBConnection().query("select stuNumber from student;");
            try {
                while (rs.next()) {
                    if (jTextField1.getText().trim().equals(rs.getString("stuNumber").trim())) {
                        overlap = true;
                    }
                }
            } catch (SQLException ec) {
                ec.printStackTrace();
            }
            if (overlap) {
                jOptionPane1.showMessageDialog(this, "该学号已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                DBConnection.getDBConnection().Update("insert into student values ('" + jTextField1.getText().trim() + "','" + jTextField2.getText().trim() +
                        "','" + jComboBox1.getSelectedItem().toString() + "','" + jComboBox2.getSelectedItem().toString() + "','" + jComboBox3.getSelectedItem().toString() + "');");
                jOptionPane1.showMessageDialog(this, "恭喜您学生信息录入成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
            }

        }
    }

    class StudentAddFrame_jComboBox1_actionAdapter implements ActionListener {
        private StudentAddFrame adaptee;

        StudentAddFrame_jComboBox1_actionAdapter(StudentAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jComboBox1_actionPerformed(e);
        }
    }

    class StudentAddFrame_jComboBox2_actionAdapter implements ActionListener {
        private StudentAddFrame adaptee;

        StudentAddFrame_jComboBox2_actionAdapter(StudentAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jComboBox2_actionPerformed(e);
        }
    }


    class StudentAddFrame_jButton1_actionAdapter implements ActionListener {
        private StudentAddFrame adaptee;

        StudentAddFrame_jButton1_actionAdapter(StudentAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton1_actionPerformed(e);
        }
    }

    class StudentAddFrame_jButton2_actionAdapter implements ActionListener {
        private StudentAddFrame adaptee;

        StudentAddFrame_jButton2_actionAdapter(StudentAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }
}
