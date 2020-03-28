package school;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.borland.jbcl.layout.XYLayout;

import Class.ClassAddFrame;
import db.DBConnection;
import Department.DepartmentManager;

public class SchoolManager extends JFrame{

	JLabel jLabel1 = new JLabel();
	JPanel contentPane;
	JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    String[] arrField = {"学院名"};
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();
    DefaultTableModel model;
    
    public SchoolManager() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
    	contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
//        setSize(new Dimension(465, 280));
        setSize(new Dimension(500, 320));
        setTitle("学院信息");
        
        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
        jLabel1.setText("全部学院");
        jLabel1.setBounds(new Rectangle(50, 20, 212, 25));
        
        jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton1.setText("删除");
        jButton1.setBounds(new Rectangle(300, 50, 80, 25));
        jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jButton2.setText("修改");
        jButton2.setBounds(new Rectangle(300, 100, 80, 25));
        jButton1.addActionListener(new SchoolManager_jButton1_actionAdapter(this));
        jButton2.addActionListener(new SchoolManager_jButton2_actionAdapter(this));
        
        Object[][] arrTmp = {};
        model  = new DefaultTableModel(arrTmp, arrField);
        jTable1 = new JTable(model);
        jScrollPane1.getViewport().add(jTable1, null);
        jScrollPane1.setBounds(50, 50, 200, 200);
        ResultSet rs = DBConnection.getDBConnection().query("select schoolName from school");
        Vector vec = new Vector(1, 1);
        while (rs.next()) {
            vec = new Vector();
            vec.add(String.valueOf(rs.getString("schoolName")));
            model.addRow(vec);
        }
        rs.close();
        jScrollPane1.getHorizontalScrollBar();
        jTable1.setGridColor(Color.blue);
        jTable1.setDragEnabled(true);
        jTable1.setSelectionForeground(Color.red);
        jTable1.setSelectionBackground(Color.green);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setRowSelectionAllowed(true);
        jTable1.setShowVerticalLines(true);
        contentPane.add(jLabel1);
        contentPane.add(jScrollPane1);
        contentPane.add(jButton1);
        contentPane.add(jButton2);
    }
    
    public static void main(String [] args) {
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	SchoolManager window = new SchoolManager();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    class SchoolManager_jButton1_actionAdapter implements ActionListener{
    	SchoolManager adaptee;
		public SchoolManager_jButton1_actionAdapter(SchoolManager adaptee) {
			// TODO Auto-generated constructor stub
			this.adaptee = adaptee;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int row = jTable1.getSelectedRow();
			if(row == -1){
	            jOptionPane1.showMessageDialog(adaptee, "请选择要删除的学院！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	            return;
			}
	        String now = model.getValueAt(row, 0).toString().trim();
	        DBConnection.getDBConnection().Update("delete from school where schoolName='" + now + "'");
	        jOptionPane1.showMessageDialog(adaptee, "恭喜您删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	        Object[][] arrTmp = {};
	        model  = new DefaultTableModel(arrTmp, arrField);
	        jTable1 = new JTable(model);
	        jScrollPane1.getViewport().add(jTable1, null);
	        jScrollPane1.setBounds(50, 50, 200, 200);
	        ResultSet rs = DBConnection.getDBConnection().query("select schoolName from school");
	        Vector vec = new Vector(1, 1);
	        try {
				while (rs.next()) {
				    vec = new Vector();
				    vec.add(String.valueOf(rs.getString("schoolName")));
				    model.addRow(vec);
				}
				 rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	}
    
    class SchoolManager_jButton2_actionAdapter implements ActionListener{
    	SchoolManager adaptee;
		public SchoolManager_jButton2_actionAdapter(SchoolManager adaptee) {
			// TODO Auto-generated constructor stub
			this.adaptee = adaptee;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int row = jTable1.getSelectedRow();
	        if(row == -1){
	            jOptionPane1.showMessageDialog(adaptee, "请选择要修改的学院！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	            return;
	        }
	        String newschool = JOptionPane.showInputDialog(adaptee,"请输入：(不改变为空)\n","新院名",JOptionPane.PLAIN_MESSAGE);
	        if (newschool.length()==0) {
				newschool = model.getValueAt(row, 0).toString().trim();
			}
	        DBConnection.getDBConnection().Update("update school set schoolName='"+newschool+"' where schoolName='"+model.getValueAt(row, 0).toString().trim()+"';");
	        jOptionPane1.showMessageDialog(adaptee, "恭喜您修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	        Object[][] arrTmp = {};
	        model  = new DefaultTableModel(arrTmp, arrField);
	        jTable1 = new JTable(model);
	        jScrollPane1.getViewport().add(jTable1, null);
	        jScrollPane1.setBounds(50, 50, 200, 200);
	        ResultSet rs = DBConnection.getDBConnection().query("select schoolName from school");
	        Vector vec = new Vector(1, 1);
	        try {
				while (rs.next()) {
				    vec = new Vector();
				    vec.add(String.valueOf(rs.getString("schoolName")));
				    model.addRow(vec);
				}
				 rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	}
}
