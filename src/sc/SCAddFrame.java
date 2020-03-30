package sc;

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

import Department.DepartmentAddFrame;
import db.DBConnection;

public class SCAddFrame extends JFrame {

	JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();
    JTextField jTextField2 = new JTextField();
    JTextField jTextField3 = new JTextField();
    
    public SCAddFrame() {
    	try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private void jbInit() {
		// TODO Auto-generated method stub
		contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(465, 320));
        setTitle("成绩录入");
        
        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
        jLabel1.setText("成  绩 录 入");
        jLabel1.setBounds(new Rectangle(136, 20, 212, 25));
        
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel2.setText("学号：");
        jLabel2.setBounds(new Rectangle(80, 80, 90, 20));
        
        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel3.setText("课程：");
        jLabel3.setBounds(new Rectangle(80, 120, 90, 20));
        
        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel4.setText("成绩：");
        jLabel4.setBounds(new Rectangle(80, 160, 90, 20));
	
        jTextField1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(180, 80, 180, 25));
	
        jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField2.setBounds(new Rectangle(180, 120, 180, 25));
        
        jTextField3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField3.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField3.setBounds(new Rectangle(180, 160, 180, 25));
        
        
        jButton1.setBounds(new Rectangle(102, 223, 96, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("提  交");
        jButton1.addActionListener(new SCAddFrame_jButton1_actionAdapter(this));

        jButton2.setBounds(new Rectangle(265, 221, 96, 31));
        jButton2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setToolTipText("");
        jButton2.setText("退  出");
        jButton2.addActionListener(new SCAddFrame_jButton2_actionAdapter(this));
        
        contentPane.add(jLabel1);
        contentPane.add(jOptionPane1);
        contentPane.add(jTextField2);
        contentPane.add(jTextField3);
        contentPane.add(jLabel3);
        contentPane.add(jLabel4);
        contentPane.add(jLabel2);
        contentPane.add(jTextField1);
        contentPane.add(jButton2);
        contentPane.add(jButton1);
	}
	
	class SCAddFrame_jButton1_actionAdapter implements ActionListener {
        private SCAddFrame adaptee;

        SCAddFrame_jButton1_actionAdapter(SCAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
        	if (adaptee.jTextField1.getText().trim().length() == 0) {
        		jOptionPane1.showMessageDialog(adaptee, "学号不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
			} else if (adaptee.jTextField2.getText().trim().length() == 0) {
				jOptionPane1.showMessageDialog(adaptee, "课程名不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
			} else {
				boolean exit = false;
				ResultSet rs = DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).query("select * from student");
				try {
	                while (rs.next()) {
	                    if (jTextField1.getText().trim().equals(rs.getString("stuNumber").trim())) {
	                    	exit = true;
	                    }
	                }
	            } catch (SQLException ec) {
	                ec.printStackTrace();
	            }
				if (!exit) {
					jOptionPane1.showMessageDialog(adaptee, "学号不存在!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
					return;
				}
				boolean overlap = false;
	            rs = DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).query("select courceName from sc where stuNumber='"+jTextField1.getText().trim()+"';");
	            try {
	                while (rs.next()) {
	                    if (jTextField2.getText().trim().equals(rs.getString("courceName").trim())) {
	                        overlap = true;
	                    }
	                }
	            } catch (SQLException ec) {
	                ec.printStackTrace();
	            }
	            if (overlap) {
                    jOptionPane1.showMessageDialog(adaptee, "该学生该课程成绩已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                	
					DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).Update("insert into sc values ('" + jTextField1.getText().trim() + "','" + jTextField2.getText().trim() + "','" + jTextField3.getText().trim() + "')");        	
                    jOptionPane1.showMessageDialog(adaptee, "恭喜您成绩录入成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
                }
			}
        }
    }
	
	class SCAddFrame_jButton2_actionAdapter implements ActionListener {
        private SCAddFrame adaptee;

        SCAddFrame_jButton2_actionAdapter(SCAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
        	adaptee.dispose();
			
        }
    }
	
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	SCAddFrame window = new SCAddFrame();
	                    window.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
}
