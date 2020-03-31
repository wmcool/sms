package Course;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;
import db.DBConnection;
import Main.MainFrame;

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

public class CourseManager extends JFrame {
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
    JComboBox jComboBox1 = new JComboBox();
    JComboBox jComboBox2 = new JComboBox();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel5 = new JLabel();
    JComboBox jComboBox3 = new JComboBox();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    JButton jButton4 = new JButton();
    JButton jButton5 = new JButton();
    JButton jButton6 = new JButton();
    Object[][] arrData = {};
    String[] arrField = {"课程编号", "课程名称", "学分"};
    JOptionPane jOptionPane1 = new JOptionPane();
    DefaultTableModel model = new DefaultTableModel();
    String sql;

    public CourseManager() throws HeadlessException {
        try{
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CourseManager window = new CourseManager();
                window.setVisible(true);
            }
        });
    }

    public void init() throws SQLException {
        getContentPane().setLayout(xyLayout1);
        setSize(600, 600);
        xyLayout1.setWidth(530);
        xyLayout1.setHeight(540);
        jLabel1.setFont(new java.awt.Font("黑体", Font.PLAIN, 20));
        jLabel1.setText("课 程 信 息 管 理");
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel2.setText("请选择课程：");
        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel3.setText("请输入课程编号：");
        jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));

        jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton1.setText("查  询");
        jButton1.addActionListener(new ActionListener() {//按课程名查询
            @Override
            public void actionPerformed(ActionEvent e) {
                sql = "select * from course where courseName='" + jComboBox1.getSelectedItem().toString() +"';";
                updateRecord();
            }
        });

        jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton2.setText("查  询");
        jButton2.addActionListener(new ActionListener() {//按课程编号查询
            @Override
            public void actionPerformed(ActionEvent e) {
                sql = "select * from course where courseId='" + jTextField1.getText().trim() + "';";
                updateRecord();
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton1.setText("按课程名称查询：");
        jRadioButton1.addItemListener(new ItemListener() {//取消选中时
            @Override
            public void itemStateChanged(ItemEvent e) {
                jComboBox1.setEnabled(false);
                jButton1.setEnabled(false);
            }
        });
        jRadioButton1.addActionListener(new ActionListener() {//选中时
            @Override
            public void actionPerformed(ActionEvent e) {
                jComboBox1.setEnabled(true);
                jButton1.setEnabled(true);
            }
        });
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setLayout(xYLayout2);
        jRadioButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton2.setText("按课程编号查询：");
        jRadioButton2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jTextField1.setEditable(false);
                jButton2.setEnabled(false);
            }
        });
        jRadioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1.setEditable(true);
                jButton2.setEnabled(true);
            }
        });
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder());

        jButton4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jButton4.setText("修  改");
        jButton4.addActionListener(new CourseManager_jButton4_actionAdapter(this));
        jButton5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jButton5.setText("删  除");
        jButton5.addActionListener(new CourseManager_jButton5_actionAdapter(this));
        jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jButton6.setText("返  回");
        jButton6.addActionListener(new CourseManager_jButton6_actionAdapter(this));

        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        jPanel1.add(jLabel2, new XYConstraints(150, 10, 100, 25));
        jPanel1.add(jLabel3, new XYConstraints(150, 50, 100, 25));
        jPanel1.add(jComboBox1, new XYConstraints(260, 10, 130, 25));
        jPanel1.add(jTextField1, new XYConstraints(260, 50, 130, 25));
        jPanel1.add(jButton1, new XYConstraints(410, 10, 80, 25));
        jPanel1.add(jButton2, new XYConstraints(410, 50, 80, 25));
        jPanel1.add(jRadioButton2, new XYConstraints(20, 50, 125, 25));
        jPanel1.add(jRadioButton1, new XYConstraints(20, 10, 125, 25));
        jScrollPane1.getViewport().add(jTable1);
        this.getContentPane().add(jLabel1, new XYConstraints(177, 14, 177, 39));
        this.getContentPane().add(jPanel1, new XYConstraints(10, 59, 510, 170));
        this.getContentPane().add(jScrollPane1, new XYConstraints(10, 239, 510, 225));
        this.getContentPane().add(jButton4, new XYConstraints(100, 480, 90, 35));
        this.getContentPane().add(jButton5, new XYConstraints(220, 480, 90, 35));
        this.getContentPane().add(jButton6, new XYConstraints(340, 480, 90, 35));
        jComboBox1.addItem("请选择学院");

        ResultSet rs = DBConnection.getDBConnection().query("select * from course;");
        while (rs.next()) {
            String courseName = rs.getString("courseName");
            jComboBox1.addItem(courseName);
        }

        jComboBox1.setEnabled(false);
        jTextField1.setEditable(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);

        if(MainFrame.user.equals("student")){
            jButton4.setVisible(false);
            jButton5.setVisible(false);
        }

        sql = "select * from course;";
        updateRecord();
    }

    public void updateRecord(){
        Object[][] arrTmp = {};
        Vector vec = new Vector(1, 1);
        model = new DefaultTableModel(arrTmp, arrField);
        jTable1 = new JTable(model);
        jScrollPane1.getViewport().add(jTable1, null);
        try {
            ResultSet rs3 = DBConnection.getDBConnection().query(sql);
            while (rs3.next()) {
                vec = new Vector();
                vec.add(String.valueOf(rs3.getInt("courseId")));
                vec.add(rs3.getString("courseName").trim());
                vec.add(rs3.getString("credit"));
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

    public void jButton4_actionPerformed(ActionEvent e) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            jOptionPane1.showMessageDialog(this, "请选择要修改的课程！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        }
        String now = model.getValueAt(row, 0).toString().trim();
        CourseChange siadd = new CourseChange(now);
        siadd.setLocation(400, 200);
        siadd.setSize(465, 310);
        siadd.setVisible(true);
        siadd.setResizable(false);
        siadd.validate();
        this.dispose();
    }

    public void jButton5_actionPerformed(ActionEvent e){
        int row = jTable1.getSelectedRow();
        if(row == -1){
            jOptionPane1.showMessageDialog(this, "请选择要删除的课程！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        }
        String now = model.getValueAt(row, 0).toString().trim();
        DBConnection.getDBConnection().Update("delete from course where courseId=" + Integer.valueOf(now) + ";");
        updateRecord();
    }

    // 退出
    public void jButton6_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    class CourseManager_jButton6_actionAdapter implements ActionListener {
        private CourseManager adaptee;

        CourseManager_jButton6_actionAdapter(CourseManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton6_actionPerformed(e);
        }
    }

    class CourseManager_jButton5_actionAdapter implements ActionListener {
        private CourseManager adaptee;

        CourseManager_jButton5_actionAdapter(CourseManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton5_actionPerformed(e);
        }
    }

    class CourseManager_jButton4_actionAdapter implements ActionListener {
        private CourseManager adaptee;

        CourseManager_jButton4_actionAdapter(CourseManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {

            adaptee.jButton4_actionPerformed(e);
        }
    }
}

