package Student;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;
import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentManager extends JFrame {
    XYLayout xyLayout1 = new XYLayout();
    JLabel jLabel1 = new JLabel();
    JPanel jPanel1 = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JRadioButton jRadioButton1 = new JRadioButton();
    JRadioButton jRadioButton2 = new JRadioButton();
    JRadioButton jRadioButton3 = new JRadioButton();
    ButtonGroup buttonGroup1 = new ButtonGroup();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JComboBox jComboBox2 = new JComboBox();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JComboBox jComboBox3 = new JComboBox();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    JButton jButton4 = new JButton();
    JButton jButton5 = new JButton();
    JButton jButton6 = new JButton();
    DBConnection conn = DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString);
    Object[][] arrData = {};
    String[] arrField = {"学号", "姓名", "学院名称", "专业名称", "班号"};
    JOptionPane jOptionPane1 = new JOptionPane();
    DefaultTableModel model = new DefaultTableModel();
    String sql;

    private StudentManager_jComboBox2_actionAdapter actionAdapter = new StudentManager_jComboBox2_actionAdapter(this);

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentManager window = new StudentManager();
                    window.setSize(600, 600);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentManager() {
        try {
            init();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void init() throws SQLException {
        getContentPane().setLayout(xyLayout1);
        xyLayout1.setWidth(530);
//        xyLayout1.setHeight(540);
        xyLayout1.setHeight(700);
        jLabel1.setFont(new java.awt.Font("黑体", Font.PLAIN, 20));
        jLabel1.setText("学 生 信 息 管 理");
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel2.setText("请选择学院：");
        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel3.setText("请选择专业：");
        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel4.setText("请选择班级：");
        jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel5.setText("请输入学号：");
        jLabel6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel6.setText("请输入姓名：");

        jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox1.addActionListener(new StudentManager_jComboBox1_actionAdapter(this));

        jComboBox2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox2.addActionListener(actionAdapter);

        jComboBox3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));

        jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton1.setText("按学号查询：");
        jRadioButton1.addItemListener(new ItemListener() {//取消选中时
            @Override
            public void itemStateChanged(ItemEvent e) {
                jTextField1.setEditable(false);
                jButton1.setEnabled(false);
            }
        });
        jRadioButton1.addActionListener(new ActionListener() {//选中时
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1.setEditable(true);
                jButton1.setEnabled(true);
            }
        });
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
//        xYLayout2.setHeight(700);
        jPanel1.setLayout(xYLayout2);
        jRadioButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton2.setText("按姓名查询：");
        jRadioButton2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jTextField2.setEditable(false);
                jButton2.setEnabled(false);
            }
        });
        jRadioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField2.setEditable(true);
                jButton2.setEnabled(true);
            }
        });
        jRadioButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton3.setText("按班级查询：");
        jRadioButton3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                jComboBox3.setEnabled(false);
                jButton3.setEnabled(false);
            }
        });
        jRadioButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jComboBox1.setEnabled(true);
                jButton3.setEnabled(true);
            }
        });
        jComboBox3.setEnabled(false);
        jComboBox3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton1.setText("查  询");
        jButton1.addActionListener(new StudentManager_jButton1_actionAdapter(this));
        jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton2.setText("查  询");
        jButton2.addActionListener(new StudentManager_jButton2_actionAdapter(this));
        jButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton3.setText("查  询");
        jButton3.addActionListener(new StudentManager_jButton3_actionAdapter(this));
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
        jButton4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jButton4.setText("修  改");
        jButton4.addActionListener(new StudentManager_jButton4_actionAdapter(this));
        jButton5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jButton5.setText("删  除");
        jButton5.addActionListener(new StudentManager_jButton5_actionAdapter(this));
        jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jButton6.setText("返  回");
        jButton6.addActionListener(new StudentManager_jButton6_actionAdapter(this));

        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);

        jPanel1.add(jLabel5, new XYConstraints(150, 10, 100, 25));
        jPanel1.add(jLabel6, new XYConstraints(150, 50, 100, 25));
        jPanel1.add(jLabel2, new XYConstraints(150, 90, 100, 25));
        jPanel1.add(jLabel3, new XYConstraints(150, 130, 100, 25));
        jPanel1.add(jLabel4, new XYConstraints(150, 170, 100, 25));
        jPanel1.add(jComboBox1, new XYConstraints(260, 90, 130, 25));
        jPanel1.add(jComboBox2, new XYConstraints(260, 130, 130, 25));
        jPanel1.add(jComboBox3, new XYConstraints(260, 170, 130, 25));
        jPanel1.add(jTextField1, new XYConstraints(260, 10, 130, 25));
        jPanel1.add(jTextField2, new XYConstraints(260, 50, 130, 25));
        jPanel1.add(jButton1, new XYConstraints(410, 10, 80, 25));
        jPanel1.add(jButton2, new XYConstraints(410, 50, 80, 25));
        jPanel1.add(jButton3, new XYConstraints(410, 170, 80, 25));
        jPanel1.add(jRadioButton2, new XYConstraints(20, 50, 125, 25));
        jPanel1.add(jRadioButton1, new XYConstraints(20, 10, 125, 25));
        jPanel1.add(jRadioButton3, new XYConstraints(20, 90, 125, 25));
        jScrollPane1.getViewport().add(jTable1);
        this.getContentPane().add(jLabel1, new XYConstraints(177, 14, 177, 39));
        this.getContentPane().add(jPanel1, new XYConstraints(10, 59, 510, 210));
        this.getContentPane().add(jScrollPane1, new XYConstraints(10, 270, 510, 225));
        this.getContentPane().add(jButton4, new XYConstraints(100, 500, 90, 35));
        this.getContentPane().add(jButton5, new XYConstraints(220, 500, 90, 35));
        this.getContentPane().add(jButton6, new XYConstraints(340, 500, 90, 35));

        jComboBox1.addItem("请选择学院");
        ResultSet rs = conn.query("select * from school;");
        while (rs.next()) {
            String school = rs.getString("schoolName");
            jComboBox1.addItem(school);
        }

        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);

        sql = "select * from student;";
        UpdateRecord();
    }

    public void UpdateRecord() {
        Object[][] arrTmp = {};
        Vector vec = new Vector(1, 1);
        model = new DefaultTableModel(arrTmp, arrField);
        jTable1 = new JTable(model);
        jScrollPane1.getViewport().add(jTable1, null);
        try {
            ResultSet rs3 = DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).query(sql);
            while (rs3.next()) {
                vec = new Vector();
                vec.add(String.valueOf(rs3.getInt("stuNumber")));
                vec.add(rs3.getString("stuName").trim());
                vec.add(rs3.getString("stuSchool"));
                vec.add(rs3.getString("stuDept"));
                vec.add(rs3.getString("stuClass"));
                model.addRow(vec);
            }
            rs3.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        jScrollPane1.getHorizontalScrollBar();
        jTable1.setGridColor(Color.blue);
        jTable1.setDragEnabled(true);
        jTable1.setSelectionForeground(Color.red);
        jTable1.setSelectionBackground(Color.green);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setRowSelectionAllowed(true);
        jTable1.setShowVerticalLines(true);
    }

    public void jButton1_actionPerformed(ActionEvent e) {
        sql = "select * from student where stuNumber='" + jTextField1.getText().trim() + "';";
        UpdateRecord();
    }

    public void jButton2_actionPerformed(ActionEvent e) {
        sql = "select * from student where stuName='" + jTextField2.getText().trim() + "';";
        UpdateRecord();
    }

    public void jButton3_actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        boolean and = false;
        sb.append("select * from student where ");
//        sql = "select * from student where stuSchool='" + jComboBox1.getSelectedItem().toString() + "' and stuDept='" + jComboBox2.getSelectedItem().toString() +
//                "' and stuClass='" + jComboBox3.getSelectedItem().toString() + "';";

        if(jComboBox1.getSelectedIndex() != 0){
            sb.append("stuSchool='" + jComboBox1.getSelectedItem().toString() + "'");
            and = true;
        }
        if(jComboBox2.getSelectedIndex() != 0){
            if(and) sb.append(" and ");
            sb.append("stuDept='" + jComboBox2.getSelectedItem().toString() + "'");
        }
        if(jComboBox3.getSelectedIndex() != 0){
            if(and) sb.append(" and ");
            sb.append("stuClass='" + jComboBox3.getSelectedItem().toString() + "';");
        }
        sql = sb.toString();
        UpdateRecord();
    }

    public void jComboBox1_actionPerformed(ActionEvent e) {
        jComboBox2.setEnabled(true);
        jComboBox2.removeActionListener(actionAdapter);
        jComboBox2.removeAllItems();
        jComboBox2.addActionListener(actionAdapter);
        jComboBox2.addItem("请选择专业");
        ResultSet rs = DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).query("select * from department where schoolName='" + jComboBox1.getSelectedItem().toString() + "';");
        try {
            while (rs.next()) {
                String depart = rs.getString("departName");
                jComboBox2.addItem(depart);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        jComboBox3.setEnabled(false);
    }

    public void jComboBox2_actionPerformed(ActionEvent e) {
        jComboBox3.setEnabled(true);
        jComboBox3.removeAllItems();
        jComboBox3.addItem("请选择班级");
        ResultSet rs = DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).query("select * from class where schoolName='" + jComboBox1.getSelectedItem().toString() + "' and departName='" +
                jComboBox2.getSelectedItem().toString() + "';");
        try {
            while (rs.next()) {
                String depart = rs.getString("classId");
                jComboBox3.addItem(depart);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void jButton4_actionPerformed(ActionEvent e) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            jOptionPane1.showMessageDialog(this, "请选择要修改的学生！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        }
        String now = model.getValueAt(row, 0).toString().trim();
        StudentChange siadd = new StudentChange(now);
        siadd.setLocation(400, 200);
        siadd.setSize(465, 400);
        siadd.setVisible(true);
        siadd.setResizable(false);
        siadd.validate();
        this.dispose();
    }

    public void jButton5_actionPerformed(ActionEvent e) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            jOptionPane1.showMessageDialog(this, "请选择要删除的学生！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        }
        String now = model.getValueAt(row, 0).toString().trim();
        DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).Update("delete from student where stuNumber=" + Integer.valueOf(now) + ";");
        UpdateRecord();
    }

    // 退出
    public void jButton6_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    class StudentManager_jButton1_actionAdapter implements ActionListener {
        private StudentManager adaptee;

        StudentManager_jButton1_actionAdapter(StudentManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton1_actionPerformed(e);
        }
    }

    class StudentManager_jButton2_actionAdapter implements ActionListener {
        private StudentManager adaptee;

        StudentManager_jButton2_actionAdapter(StudentManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }

    class StudentManager_jButton3_actionAdapter implements ActionListener {
        private StudentManager adaptee;

        StudentManager_jButton3_actionAdapter(StudentManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton3_actionPerformed(e);
        }
    }

    class StudentManager_jButton4_actionAdapter implements ActionListener {
        private StudentManager adaptee;

        StudentManager_jButton4_actionAdapter(StudentManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton4_actionPerformed(e);
        }
    }

    class StudentManager_jButton5_actionAdapter implements ActionListener {
        private StudentManager adaptee;

        StudentManager_jButton5_actionAdapter(StudentManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton5_actionPerformed(e);
        }
    }

    class StudentManager_jButton6_actionAdapter implements ActionListener {
        private StudentManager adaptee;

        StudentManager_jButton6_actionAdapter(StudentManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton6_actionPerformed(e);
        }
    }

    class StudentManager_jComboBox1_actionAdapter implements ActionListener {
        private StudentManager adaptee;

        StudentManager_jComboBox1_actionAdapter(StudentManager adaptee) {

            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jComboBox1_actionPerformed(e);
        }
    }

    class StudentManager_jComboBox2_actionAdapter implements ActionListener {
        private StudentManager adaptee;

        StudentManager_jComboBox2_actionAdapter(StudentManager adaptee) {

            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jComboBox2_actionPerformed(e);
        }
    }
}
