package department;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Class.ClassAddFrame;
import db.DBConnection;

public class DepartmentAddFrame extends JFrame{

	JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();
    JComboBox jComboBox1 = new JComboBox();
    String school;
    
    public DepartmentAddFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private void jbInit() throws Exception {
		// TODO Auto-generated method stub
		contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
//        setSize(new Dimension(465, 280));
        setSize(new Dimension(465, 320));
        setTitle("专业录入");
        
        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
        jLabel1.setText("专  业 信 息 录 入");
        jLabel1.setBounds(new Rectangle(136, 20, 212, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel2.setText("系号：");
        jLabel2.setBounds(new Rectangle(80, 80, 90, 20));

        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel3.setText("专业名：");
        jLabel3.setBounds(new Rectangle(80, 130, 90, 20));
        
        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel4.setText("所属学院：");
        jLabel4.setBounds(new Rectangle(80, 180, 90, 20));
        
        jTextField1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(180, 80, 180, 25));
        
        jTextField2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setBounds(new Rectangle(180, 130, 180, 25));
        
        jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox1.setEditable(true);
        jComboBox1.setBounds(new Rectangle(180, 180, 180, 25));
        jComboBox1.addActionListener(new DepartmentAddFrame_jComboBox1_actionAdapter(this));
        ResultSet rs = DBConnection.getDBConnection().query("select * from department");
        jComboBox1.addItem("请选择学院");
        while (rs.next()) {
        	String schoolName = rs.getString("schoolName");
            jComboBox1.addItem(schoolName);
        }
        
        jButton1.setBounds(new Rectangle(102, 223, 96, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("提  交");
        jButton1.addActionListener(new DepartmentAddFrame_jButton1_actionAdapter(this));

        jButton2.setBounds(new Rectangle(265, 221, 96, 31));
        jButton2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setToolTipText("");
        jButton2.setText("退  出");
        jButton2.addActionListener(new DepartmentAddFrame_jButton2_actionAdapter(this));
        
        contentPane.add(jLabel1);
        contentPane.add(jOptionPane1);
        contentPane.add(jComboBox1);
        contentPane.add(jLabel3);
        contentPane.add(jLabel2);
        contentPane.add(jTextField1);
        contentPane.add(jTextField2);
        contentPane.add(jLabel4);
        contentPane.add(jButton2);
        contentPane.add(jButton1);
        
	}
	
	public void jComboBox1_actionPerformed(ActionEvent e) {
		school = String.valueOf(jComboBox1.getSelectedItem());
    }

    class DepartmentAddFrame_jComboBox1_actionAdapter implements ActionListener {
        private DepartmentAddFrame adaptee;

        DepartmentAddFrame_jComboBox1_actionAdapter(DepartmentAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jComboBox1_actionPerformed(e);
        }
    }
    
  //退出
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }
    
    class DepartmentAddFrame_jButton2_actionAdapter implements ActionListener {
        private DepartmentAddFrame adaptee;

        DepartmentAddFrame_jButton2_actionAdapter(DepartmentAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }
    
  //提交
    public void jButton1_actionPerformed(ActionEvent e) {
    	if (this.jTextField1.getText().trim().length() == 0) {
            jOptionPane1.showMessageDialog(this, "系号不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
        } else if (this.jTextField2.getText().trim().length() == 0) {
        	jOptionPane1.showMessageDialog(this, "系名不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
		} else if (jComboBox1.getSelectedIndex() == 0) {
			jOptionPane1.showMessageDialog(this, "请选择学院!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
		} else {
			boolean overlap = false;
            ResultSet rs = DBConnection.getDBConnection().query("select deptId from department");
            try {
                while (rs.next()) {
                    if (jTextField1.getText().trim().equals(rs.getString("deptId").trim())) {
                        overlap = true;
                    }
                }
            } catch (SQLException ec) {
                ec.printStackTrace();
            }
            if (overlap) {
                jOptionPane1.showMessageDialog(this, "该系号已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
            	overlap = false;
            	rs = DBConnection.getDBConnection().query("select departName from department where schoolName='" + school + "'");
                try {
                    while (rs.next()) {
                        if (jTextField1.getText().trim().equals(rs.getString("departName").trim())) {
                            overlap = true;
                        }
                    }
                } catch (SQLException ec) {
                    ec.printStackTrace();
                }
                if (overlap) {
                    jOptionPane1.showMessageDialog(this, "该系名已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                	DBConnection.getDBConnection().Update("insert into department values ('" + jTextField1.getText().trim() + "','" + school + "','" + jTextField2.getText().trim() + "')");
                    jOptionPane1.showMessageDialog(this, "恭喜您专业信息录入成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                }
            }
		}
    }
    
    class DepartmentAddFrame_jButton1_actionAdapter implements ActionListener {
        private DepartmentAddFrame adaptee;

        DepartmentAddFrame_jButton1_actionAdapter(DepartmentAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {

            adaptee.jButton1_actionPerformed(e);
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	DepartmentAddFrame window = new DepartmentAddFrame();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
