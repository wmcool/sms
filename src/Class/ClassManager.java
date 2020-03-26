package Class;

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

public class ClassManager extends JFrame {
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
    DBConnection conn = DBConnection.getDBConnection();
    Object[][] arrData = {};
    String[] arrField = {"班级编号", "学院名称", "专业名称"};
    JOptionPane jOptionPane1 = new JOptionPane();
    DefaultTableModel model = new DefaultTableModel();
    String sql;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClassManager window = new ClassManager();
                    window.setSize(600, 600);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClassManager() {
        try {
            init();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void init() throws Exception {
        getContentPane().setLayout(xyLayout1);
        xyLayout1.setWidth(530);
        xyLayout1.setHeight(540);
        jLabel1.setFont(new java.awt.Font("黑体", Font.PLAIN, 20));
        jLabel1.setText("班 级 信 息 管 理");
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel2.setText("请选择学院：");
        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel3.setText("请选择学院：");
        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel4.setText("请输入班号：");
        jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox2.setEditable(false);
        jComboBox2.addActionListener(new ClassManager_jComboBox2_actionAdapter(this));
        jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton1.setText("按学院查询：");
        jRadioButton1.addItemListener(new ClassManager_jRadioButton1_itemAdapter(this));
        jRadioButton1.addActionListener(new ClassManager_jRadioButton1_actionAdapter(this));
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setLayout(xYLayout2);
        jRadioButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton2.setText("按专业查询：");
        jRadioButton2.addItemListener(new ClassManager_jRadioButton2_itemAdapter(this));
        jRadioButton2.addActionListener(new ClassManager_jRadioButton2_actionAdapter(this));
        jRadioButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jRadioButton3.setText("按班号查询：");
        jRadioButton3.addItemListener(new ClassManager_jRadioButton3_itemAdapter(this));
        jRadioButton3.addActionListener(new ClassManager_jRadioButton3_actionAdapter(this));
        jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel5.setText("请选择专业：");
        jComboBox3.setEnabled(false);
        jComboBox3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton1.setText("查  询");
        jButton1.addActionListener(new ClassManager_jButton1_actionAdapter(this));
        jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton2.setText("查  询");
        jButton2.addActionListener(new ClassManager_jButton2_actionAdapter(this));
        jButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton3.setText("查  询");
        jButton3.addActionListener(new ClassManager_jButton3_actionAdapter(this));
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
        jButton4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jButton4.setText("修  改");
        jButton4.addActionListener(new ClassManager_jButton4_actionAdapter(this));
        jButton5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jButton5.setText("删  除");
        jButton5.addActionListener(new ClassManager_jButton5_actionAdapter(this));
        jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jButton6.setText("返  回");
        jButton6.addActionListener(new ClassManager_jButton6_actionAdapter(this));
        jPanel1.add(jRadioButton2, new XYConstraints(20, 50, 125, 25));
        jPanel1.add(jRadioButton3, new XYConstraints(20, 130, 125, 25));
        jPanel1.add(jRadioButton1, new XYConstraints(20, 10, 125, 25));
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        jPanel1.add(jLabel3, new XYConstraints(150, 50, 100, 25));
        jPanel1.add(jLabel4, new XYConstraints(150, 130, 100, 25));
        jPanel1.add(jLabel2, new XYConstraints(150, 10, 100, 25));
        jPanel1.add(jComboBox1, new XYConstraints(260, 10, 130, 25));
        jPanel1.add(jComboBox2, new XYConstraints(260, 50, 130, 25));
        jPanel1.add(jTextField1, new XYConstraints(260, 130, 130, 25));
        jPanel1.add(jLabel5, new XYConstraints(150, 90, 100, 25));
        jPanel1.add(jComboBox3, new XYConstraints(260, 90, 130, 25));
        jPanel1.add(jButton1, new XYConstraints(410, 10, 80, 25));
        jPanel1.add(jButton2, new XYConstraints(410, 90, 80, 25));
        jPanel1.add(jButton3, new XYConstraints(410, 130, 80, 25));
        jScrollPane1.getViewport().add(jTable1);
        this.getContentPane().add(jLabel1, new XYConstraints(177, 14, 177, 39));
        this.getContentPane().add(jPanel1, new XYConstraints(10, 59, 510, 170));
        this.getContentPane().add(jScrollPane1, new XYConstraints(10, 239, 510, 225));
        this.getContentPane().add(jButton4, new XYConstraints(100, 480, 90, 35));
        this.getContentPane().add(jButton5, new XYConstraints(220, 480, 90, 35));
        this.getContentPane().add(jButton6, new XYConstraints(340, 480, 90, 35));
        jComboBox1.addItem("请选择学院");
        jComboBox2.addItem("请选择学院");

        ResultSet rs = conn.query("select * from school;");
        while (rs.next()) {
            String school = rs.getString("schoolName");
            jComboBox1.addItem(school);
            jComboBox2.addItem(school);
        }

        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
        jTextField1.setEditable(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);

        sql = "select * from class;";
        UpdateRecord();
    }

    public void UpdateRecord(){
        Object[][] arrTmp = {};
        Vector vec = new Vector(1, 1);
        model = new DefaultTableModel(arrTmp, arrField);
        jTable1 = new JTable(model);
        jScrollPane1.getViewport().add(jTable1, null);
        try {
            ResultSet rs3 = DBConnection.getDBConnection().query(sql);
            while (rs3.next()) {
                vec = new Vector();
                vec.add(String.valueOf(rs3.getInt("classId")));
                vec.add(rs3.getString("schoolName").trim());
                vec.add(rs3.getString("departName"));
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

    public void jRadioButton1_actionPerformed(ActionEvent e){
        jComboBox1.setEnabled(true);
        jButton1.setEnabled(true);
    }

    public void jRadioButton2_actionPerformed(ActionEvent e){
        jComboBox2.setEnabled(true);
        jButton2.setEnabled(true);
    }

    public void jRadioButton3_actionPerformed(ActionEvent e) {
        jTextField1.setEditable(true);
        jButton3.setEnabled(true);
    }

    public void jRadioButton1_itemStateChanged(ItemEvent e) {
        jComboBox1.setEnabled(false);
        jButton1.setEnabled(false);
    }

    public void jRadioButton2_itemStateChanged(ItemEvent e) {
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
        jButton2.setEnabled(false);
    }

    public void jRadioButton3_itemStateChanged(ItemEvent e) {
        jTextField1.setEditable(false);
        jButton3.setEnabled(false);
    }

    public void jComboBox2_actionPerformed(ActionEvent e) {
        jComboBox3.setEnabled(true);
        jComboBox3.removeAllItems();
        jComboBox3.addItem("请选择专业");
        ResultSet rs = DBConnection.getDBConnection().query("select * from department where schoolName='" + jComboBox2.getSelectedItem().toString() + "';");
        try {
            while(rs.next()){
                String depart = rs.getString("departName");
                jComboBox3.addItem(depart);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void jButton1_actionPerformed(ActionEvent e){
        sql = "select * from class where schoolName='" + jComboBox1.getSelectedItem().toString() + "';";
        UpdateRecord();
    }

    public void jButton2_actionPerformed(ActionEvent e){
        sql = "select * from class where departName='" + jComboBox2.getSelectedItem().toString() + "';";
        UpdateRecord();
    }

    public void jButton3_actionPerformed(ActionEvent e){
        sql = "select * from class where classId='" + jComboBox3.getSelectedItem().toString() + "';";
        UpdateRecord();
    }

    public void jButton4_actionPerformed(ActionEvent e) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            jOptionPane1.showMessageDialog(this, "请选择要修改的班级！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        }
        String now = model.getValueAt(row, 0).toString().trim();
        ClassChange siadd = new ClassChange(now);
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
            jOptionPane1.showMessageDialog(this, "请选择要删除的班级！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        }
        String now = model.getValueAt(row, 0).toString().trim();
        DBConnection.getDBConnection().Update("delete from class where classId=" + Integer.valueOf(now) + ";");
        UpdateRecord();
    }

    // 退出
    public void jButton6_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    class ClassManager_jButton6_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jButton6_actionAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton6_actionPerformed(e);
        }
    }

    class ClassManager_jButton5_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jButton5_actionAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton5_actionPerformed(e);
        }
    }

    class ClassManager_jButton4_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jButton4_actionAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {

            adaptee.jButton4_actionPerformed(e);
        }
    }

    class ClassManager_jButton3_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jButton3_actionAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton3_actionPerformed(e);
        }
    }

    class ClassManager_jButton2_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jButton2_actionAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }

    class ClassManager_jButton1_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jButton1_actionAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton1_actionPerformed(e);
        }
    }

    class ClassManager_jRadioButton1_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jRadioButton1_actionAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jRadioButton1_actionPerformed(e);
        }
    }

    class ClassManager_jRadioButton1_itemAdapter implements ItemListener {
        private ClassManager adaptee;

        ClassManager_jRadioButton1_itemAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void itemStateChanged(ItemEvent e) {
            adaptee.jRadioButton1_itemStateChanged(e);
        }
    }

    class ClassManager_jRadioButton2_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jRadioButton2_actionAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jRadioButton2_actionPerformed(e);
        }
    }

    class ClassManager_jRadioButton2_itemAdapter implements ItemListener {
        private ClassManager adaptee;

        ClassManager_jRadioButton2_itemAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void itemStateChanged(ItemEvent e) {
            adaptee.jRadioButton2_itemStateChanged(e);
        }
    }

    class ClassManager_jRadioButton3_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jRadioButton3_actionAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jRadioButton3_actionPerformed(e);
        }
    }

    class ClassManager_jRadioButton3_itemAdapter implements ItemListener {
        private ClassManager adaptee;

        ClassManager_jRadioButton3_itemAdapter(ClassManager adaptee) {
            this.adaptee = adaptee;
        }

        public void itemStateChanged(ItemEvent e) {
            adaptee.jRadioButton3_itemStateChanged(e);
        }
    }

    class ClassManager_jComboBox2_actionAdapter implements ActionListener {
        private ClassManager adaptee;

        ClassManager_jComboBox2_actionAdapter(ClassManager adaptee) {

            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jComboBox2_actionPerformed(e);
        }
    }
}
