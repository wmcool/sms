package school;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Class.ClassAddFrame;
import db.DBConnection;

public class SchoolAddFrame extends JFrame {
	
	JPanel contentPane;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();
    
    public SchoolAddFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
    	contentPane = (JPanel) getContentPane();
    	contentPane.setLayout(null);
    	setSize(new Dimension(465, 300));
        setTitle("学院录入");
        
        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
        jLabel1.setText("学  院 信 息 录 入");
        jLabel1.setBounds(new Rectangle(136, 20, 212, 25));
        
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
        jLabel2.setText("学院名：");
        jLabel2.setBounds(new Rectangle(80, 80, 90, 20));
        
        jTextField1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
        jTextField1.setBounds(new Rectangle(180, 80, 180, 25));
        
        jButton1.setBounds(new Rectangle(102, 180, 96, 29));
        jButton1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton1.setText("提  交");
        jButton1.addActionListener(new AddSchoolFrame_jButton1_actionAdapter(this));

        jButton2.setBounds(new Rectangle(265, 180, 96, 31));
        jButton2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
        jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton2.setToolTipText("");
        jButton2.setText("退  出");
        jButton2.addActionListener(new AddSchoolFrame_jButton2_actionAdapter(this));
        
        jOptionPane1.setBounds(new Rectangle(106, 258, 262, 90));
        jOptionPane1.setLayout(null);
        
        contentPane.add(jLabel1);
        contentPane.add(jLabel2);
        contentPane.add(jTextField1);
        contentPane.add(jButton1);
        contentPane.add(jButton2);
        contentPane.add(jOptionPane1);
        
    }
    
    public void jButton1_actionPerformed(ActionEvent e) {
    	 if (this.jTextField1.getText().trim().length() == 0) {
    		 jOptionPane1.showMessageDialog(this, "添加学院不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
    	 }else {
    		 boolean overlap = false;
             ResultSet rs = DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).query("select schoolName from school");
             try {
                 while (rs.next()) {
                     if (jTextField1.getText().trim().equals(rs.getString("schoolName").trim())) {
                         overlap = true;
                     }
                 }
             } catch (SQLException ec) {
                 ec.printStackTrace();
             }
             if (overlap) {
                 jOptionPane1.showMessageDialog(this, "该学院已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
             } else {
                 DBConnection.getDBConnection(DBConnection.userString, DBConnection.passwordString).Update("insert into school values ('"+jTextField1.getText().trim()+"')");
                 jOptionPane1.showMessageDialog(this, "恭喜您学院信息录入成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
             }
    	 }
    }

    
  //退出
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }
    
    class AddSchoolFrame_jButton1_actionAdapter implements ActionListener {
        private SchoolAddFrame adaptee;

        AddSchoolFrame_jButton1_actionAdapter(SchoolAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {

            adaptee.jButton1_actionPerformed(e);
        }
    }

    
    class AddSchoolFrame_jButton2_actionAdapter implements ActionListener {
        private SchoolAddFrame adaptee;

        AddSchoolFrame_jButton2_actionAdapter(SchoolAddFrame adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }

    
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SchoolAddFrame window = new SchoolAddFrame();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
