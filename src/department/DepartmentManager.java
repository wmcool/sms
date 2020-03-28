package department;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.borland.jbcl.layout.XYLayout;
import com.mysql.cj.xdevapi.Result;

import Class.ClassManager;
import db.DBConnection;

public class DepartmentManager extends JFrame {
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	Border etchedBorder1 = BorderFactory.createEtchedBorder();
	JPanel jPanel1 = new JPanel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JComboBox jComboBox1 = new JComboBox();
	JComboBox jComboBox2 = new JComboBox();
	JButton button1 = new JButton();
	DBConnection conn = DBConnection.getDBConnection();
	JPanel jPanel2 = new JPanel();
	Border etchedBorder2 = BorderFactory.createEtchedBorder();
	JScrollPane jScrollPane1 = new JScrollPane();
    Object[][] arrTmp = {};
    String[] arrField = {"系号", "学院名称", "专业名称"};
    DefaultTableModel model = new DefaultTableModel(arrTmp, arrField);
    JTable jTable1 = new JTable(model);
    String schoolString;
    String departString;
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	DepartmentManager window = new DepartmentManager();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public DepartmentManager() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

	private void jbInit() throws SQLException {
		// TODO Auto-generated method stub
		contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(600, 520));
        jLabel1.setFont(new java.awt.Font("黑体", Font.PLAIN, 20));
        jLabel1.setText("专 业 信 息 管 理");
        jLabel1.setBounds(new Rectangle(190, 20, 212, 25));
        
        jPanel1.setBounds(new Rectangle(20, 50, 540, 80));
        jPanel1.setLayout(null);
        jPanel1.setBorder(etchedBorder1);
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel2.setText("请选择学院：");
        jLabel2.setBounds(new Rectangle(10, 10, 120, 25));
        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel3.setText("请选择专业：");
        jLabel3.setBounds(new Rectangle(150, 10, 120, 25));
        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel4.setText("请输入系号：");
        jLabel4.setBounds(new Rectangle(290, 10, 120, 25));
        jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox1.setEditable(false);
        jComboBox1.setBounds(new Rectangle(10, 40, 120, 25));
        jComboBox2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jComboBox2.setEditable(false);
        jComboBox2.setBounds(new Rectangle(150, 40, 120, 25));
        jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField1.setBounds(new Rectangle(290, 40, 120, 25));
        button1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        button1.setText("查询");
        button1.setBounds(new Rectangle(430, 40, 80, 25));
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel3);
        jPanel1.add(jLabel4);
        jPanel1.add(jComboBox1);
        jPanel1.add(jComboBox2);
        jPanel1.add(jTextField1);
        jPanel1.add(button1);
        jComboBox1.addItem("");
        jComboBox2.addItem("");
        ResultSet rs = DBConnection.getDBConnection().query("select * from department");
        while (rs.next()) {
        	String departName = rs.getString("departName");
        	jComboBox2.addItem(departName);
        }
        rs = DBConnection.getDBConnection().query("select * from school");
        while (rs.next()) {
        	String schoolName = rs.getString("schoolName");
        	jComboBox1.addItem(schoolName);
        }
        
        jPanel2.setBounds(new Rectangle(20, 150, 540, 200));
        jPanel2.setLayout(null);
        jPanel2.setBorder(etchedBorder2);
        jScrollPane1.getViewport().add(jTable1);
        jPanel2.add(jScrollPane1);
        jScrollPane1.getHorizontalScrollBar();
        jTable1.setGridColor(Color.blue);
        jTable1.setDragEnabled(true);
        jTable1.setSelectionForeground(Color.red);
        jTable1.setSelectionBackground(Color.green);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.setRowSelectionAllowed(true);
        jTable1.setShowVerticalLines(true);
        jScrollPane1.setBounds(new Rectangle(5, 5, 530, 190));
        button1.addActionListener(new DepartmentManager_jButton1_actionAdapter(this));
        
        button2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        button2.setText("删除");
        button2.setBounds(new Rectangle(50, 400, 80, 25));
        button2.addActionListener(new DepartmentManager_jButton2_actionAdapter(this));
        button3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        button3.setText("修改");
        button3.setBounds(new Rectangle(250, 400, 80, 25));
        button3.addActionListener(new DepartmentManager_jButton3_actionAdapter(this));
        button4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        button4.setText("退出");
        button4.setBounds(new Rectangle(450, 400, 80, 25));
        button4.addActionListener(new DepartmentManager_jButton4_actionAdapter(this));
        
        contentPane.add(jLabel1);
        contentPane.add(jPanel1);
        contentPane.add(jPanel2);
        contentPane.add(button2);
        contentPane.add(button3);
        contentPane.add(button4);
	}
	
	class DepartmentManager_jButton1_actionAdapter implements ActionListener {
		private DepartmentManager adaptee;
		
		public DepartmentManager_jButton1_actionAdapter(DepartmentManager adaptee) {
			// TODO Auto-generated constructor stub
			this.adaptee = adaptee;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			model = new DefaultTableModel(arrTmp, arrField);
			jTable1 = new JTable(model);
	        jScrollPane1.getViewport().add(jTable1, null);
			String query = "";
			String deptId;
			if (adaptee.jTextField1.getText().trim().length() != 0) {
				deptId = adaptee.jTextField1.getText().trim();
				query += "deptId='"+deptId+"'";
			}
			if (jComboBox1.getSelectedItem().toString().trim().length()!=0) {
				schoolString = jComboBox1.getSelectedItem().toString().trim();
				if (query.length()!=0) {
					query += " and ";
				}
				query += "schoolName='"+schoolString+"'";
			}
			if (jComboBox2.getSelectedItem().toString().trim().length()!=0) {
				departString = jComboBox2.getSelectedItem().toString().trim();
				if (query.length()!=0) {
					query += " and ";
				}
				query += "departName='"+departString+"'";
			}
			if (query.length()!=0) {
				query = " where "+query;
			}
			query = "select * from department"+query;
			Vector vec = new Vector(1, 1);
			ResultSet rs = conn.query(query);
			try {
                while (rs.next()) {
                	vec = new Vector();
                    vec.add(String.valueOf(rs.getInt("deptId")));
                    vec.add(rs.getString("schoolName").trim());
                    vec.add(rs.getString("departName"));
                    adaptee.model.addRow(vec);
                }
                rs.close();
            } catch (SQLException ec) {
                ec.printStackTrace();
            }
			jScrollPane1.getHorizontalScrollBar();
	        jTable1.setGridColor(Color.blue);
	        jTable1.setDragEnabled(true);
	        jTable1.setSelectionForeground(Color.red);
	        jTable1.setSelectionBackground(Color.green);
	        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        jTable1.setRowSelectionAllowed(true);
	        jTable1.setShowVerticalLines(true);
	        jScrollPane1.setBounds(new Rectangle(5, 5, 530, 190));
		}
	}

	class DepartmentManager_jButton2_actionAdapter implements ActionListener{
		DepartmentManager adaptee;
		public DepartmentManager_jButton2_actionAdapter(DepartmentManager adaptee) {
			// TODO Auto-generated constructor stub
			this.adaptee = adaptee;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int row = jTable1.getSelectedRow();
	        if(row == -1){
	            jOptionPane1.showMessageDialog(adaptee, "请选择要删除的专业！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	        }
	        String now = model.getValueAt(row, 0).toString().trim();
	        DBConnection.getDBConnection().Update("delete from department where deptId='" + now + "'");
	        jOptionPane1.showMessageDialog(adaptee, "恭喜您删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}	
	}
	
	class DepartmentManager_jButton3_actionAdapter implements ActionListener{
		DepartmentManager adaptee;
		public DepartmentManager_jButton3_actionAdapter(DepartmentManager adaptee) {
			// TODO Auto-generated constructor stub
			this.adaptee = adaptee;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int row = jTable1.getSelectedRow();
	        if(row == -1){
	            jOptionPane1.showMessageDialog(adaptee, "请选择要修改的专业！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	        }else {
	        	ArrayList<String> ops = new ArrayList<String>();
		        ResultSet rs = conn.query("select * from school");
		        try {
					while (rs.next()) {
						ops.add(rs.getString("schoolName").trim());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        String[] obj = new String[ops.size()];
		        for (int i = 0; i < obj.length; i++) {
					obj[i] = ops.get(i);
				}        
		        String newSchool = (String) JOptionPane.showInputDialog(adaptee, "该专业所属学院:\n", "专业", JOptionPane.PLAIN_MESSAGE, null, obj, model.getValueAt(row, 1).toString().trim());
		        if (newSchool==null) {
					return;
				}
		        String newdept = JOptionPane.showInputDialog(adaptee,"请输入：(不改变为空)\n","新系名",JOptionPane.PLAIN_MESSAGE);
		        if (newdept==null) {
					return;
				}
		        if (newdept.length()==0) {
					newdept = model.getValueAt(row, 2).toString().trim();
				}
		        conn.Update("update department set schoolName='"+newSchool+"' where deptId='"+model.getValueAt(row, 0).toString().trim()+"';");
		        conn.Update("update department set departName='"+newdept+"' where deptId='"+model.getValueAt(row, 0).toString().trim()+"';");
		        jOptionPane1.showMessageDialog(adaptee, "恭喜您修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	        }
	        
		}	
	}
	
	class DepartmentManager_jButton4_actionAdapter implements ActionListener{
		DepartmentManager adaptee;
		public DepartmentManager_jButton4_actionAdapter(DepartmentManager adaptee) {
			// TODO Auto-generated constructor stub
			this.adaptee = adaptee;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			adaptee.dispose();
		}	
	}
	
	
}
