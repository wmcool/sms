package Course;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CourseChange extends JFrame {
    private String courseId;

    JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JTextField jTextField3 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();

    public CourseChange(String courseId) throws HeadlessException {
        this.courseId = courseId;
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void jbInit() {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(465, 320));
        setTitle("课程修改");

        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
        jLabel1.setText("课 程 信 息 修 改");
        jLabel1.setBounds(new Rectangle(136, 20, 212, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel2.setText("课程代码：");
        jLabel2.setBounds(new Rectangle(80, 80, 90, 20));

        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel3.setText("课程名称：");
        jLabel3.setBounds(new Rectangle(80, 130, 90, 20));

        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel4.setText("学分：");
        jLabel4.setBounds(new Rectangle(80, 180, 90, 20));

        jTextField1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(180, 80, 180, 25));

        jTextField2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setBounds(new Rectangle(180, 130, 180, 25));

        jTextField3.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField3.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField3.setBounds(new Rectangle(180, 180, 180, 25));

        jButton1.setBounds(new Rectangle(102, 223, 96, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("提  交");
        jButton1.addActionListener(new CourseChange_jButton1_actionAdapter(this));

        jButton2.setBounds(new Rectangle(265, 221, 96, 31));
        jButton2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setToolTipText("");
        jButton2.setText("退  出");
        jButton2.addActionListener(new CourseChange_jButton2_actionAdapter(this));

        contentPane.add(jLabel1);
        contentPane.add(jOptionPane1);
        contentPane.add(jLabel3);
        contentPane.add(jLabel2);
        contentPane.add(jLabel4);
        contentPane.add(jTextField1);
        contentPane.add(jTextField2);
        contentPane.add(jTextField3);
        contentPane.add(jButton1);
        contentPane.add(jButton2);
    }

    //退出
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    public void jButton1_actionPerformed(ActionEvent e) {
        if (jTextField1.getText().length() == 0) {
            jOptionPane1.showMessageDialog(this, "课程代码不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        } else if (jTextField2.getText().length() == 0) {
            jOptionPane1.showMessageDialog(this, "课程名称不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        } else if (jTextField3.getText().length() == 0) {
            jOptionPane1.showMessageDialog(this, "学分不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        } else {
            try {
                boolean overlap = false;
                ResultSet rs = DBConnection.getDBConnection().query("select courseId from course;");
                while (rs.next()) {
                    if (jTextField1.getText().trim().equals(rs.getString("courseId").trim())) {
                        overlap = true;
                    }
                }
                if (overlap) {
                    jOptionPane1.showMessageDialog(this, "课程代码已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    DBConnection.getDBConnection().Update("update course set courseId='" + jTextField1.getText().trim() + "',courseName='" + jTextField2.getText().trim()
                            + "',credit='" + Float.valueOf(jTextField3.getText().trim()) + "'where courseId='" + courseId + "';");
                    jOptionPane1.showMessageDialog(this, "课程信息修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                }
                rs.close();

            } catch (Exception ec) {
                ec.printStackTrace();
            }
        }
    }

    class CourseChange_jButton1_actionAdapter implements ActionListener {
        private CourseChange adaptee;

        CourseChange_jButton1_actionAdapter(CourseChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton1_actionPerformed(e);
        }
    }

    class CourseChange_jButton2_actionAdapter implements ActionListener {
        private CourseChange adaptee;

        CourseChange_jButton2_actionAdapter(CourseChange adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }

}
