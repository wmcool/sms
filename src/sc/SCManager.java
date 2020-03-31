package sc;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import db.DBConnection;
import Main.MainFrame;

public class SCManager extends JFrame {
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	Border etchedBorder1 = BorderFactory.createEtchedBorder();
	JPanel jPanel1 = new JPanel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	JTextField jTextField3 = new JTextField();
	JTextField jTextField4 = new JTextField();
	JButton button1 = new JButton();
	DBConnection conn = DBConnection.getDBConnection();
	JPanel jPanel2 = new JPanel();
	Border etchedBorder2 = BorderFactory.createEtchedBorder();
	JScrollPane jScrollPane1 = new JScrollPane();
	Object[][] arrTmp = {};
    String[] arrField = {"学号", "课程名称", "成绩"};
    DefaultTableModel model = new DefaultTableModel(arrTmp, arrField);
    JTable jTable1 = new JTable(model);
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JOptionPane jOptionPane1 = new JOptionPane();


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	SCManager window = new SCManager();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SCManager() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

	private void jbInit() {
		// TODO Auto-generated method stub
		contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(600, 520));
        jLabel1.setFont(new java.awt.Font("黑体", Font.PLAIN, 20));
        jLabel1.setText("成 绩 信 息 管 理");
        jLabel1.setBounds(new Rectangle(190, 20, 212, 25));
        jPanel1.setBounds(new Rectangle(20, 50, 540, 80));
        jPanel1.setLayout(null);
        jPanel1.setBorder(etchedBorder1);
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel2.setText("学号：");
        jLabel2.setBounds(new Rectangle(10, 10, 120, 25));
        jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel3.setText("课程名：");
        jLabel3.setBounds(new Rectangle(150, 10, 120, 25));
        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jLabel4.setText("成绩区间：");
        jLabel4.setBounds(new Rectangle(290, 10, 120, 25));
        jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField1.setBounds(new Rectangle(10, 40, 120, 25));
        jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField2.setBounds(new Rectangle(150, 40, 120, 25));
        jTextField3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField3.setBounds(new Rectangle(290, 40, 40, 25));
        jTextField4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        jTextField4.setBounds(new Rectangle(350, 40, 40, 25));
        button1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        button1.setText("查询");
        button1.setBounds(new Rectangle(410, 40, 80, 25));
        jPanel1.add(jLabel2);
        jPanel1.add(jLabel3);
        jPanel1.add(jLabel4);
        jPanel1.add(jTextField1);
        jPanel1.add(jTextField2);
        jPanel1.add(jTextField3);
        jPanel1.add(jTextField4);
        jPanel1.add(button1);
        button1.addActionListener(new SCManager_jButton1_actionAdapter(this));
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
        button2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        button2.setText("删除");
        button2.setBounds(new Rectangle(50, 400, 80, 25));
        button2.addActionListener(new SCManager_jButton2_actionAdapter(this));
        button3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        button3.setText("修改");
        button3.setBounds(new Rectangle(250, 400, 80, 25));
        button3.addActionListener(new SCManager_jButton3_actionAdapter(this));
        button4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        button4.setText("退出");
        button4.setBounds(new Rectangle(450, 400, 80, 25));
        button4.addActionListener(new SCManager_jButton4_actionAdapter(this));

        contentPane.add(jLabel1);
        contentPane.add(jPanel1);
        contentPane.add(jPanel2);
        contentPane.add(button2);
        contentPane.add(button3);
        contentPane.add(button4);

		if(MainFrame.user.equals("student")){
			button2.setVisible(false);
			button3.setVisible(false);
		}
	}

	class SCManager_jButton1_actionAdapter implements ActionListener {
		private SCManager adaptee;

		public SCManager_jButton1_actionAdapter(SCManager adaptee) {
			// TODO Auto-generated constructor stub
			this.adaptee = adaptee;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			model = new DefaultTableModel(arrTmp, arrField);
			jTable1 = new JTable(model);
	        jScrollPane1.getViewport().add(jTable1, null);
			String query = "";
			if (adaptee.jTextField1.getText().trim().length() != 0) {
				String stuNumber = adaptee.jTextField1.getText().trim();
				query += "stuNumber='"+stuNumber+"'";
			}
			if (adaptee.jTextField2.getText().trim().length() != 0) {
				String courseName = adaptee.jTextField2.getText().trim();
				if (query.length()!=0) {
					query += " and ";
				}
				query += "courseName='"+courseName+"'";
			}
			if (adaptee.jTextField3.getText().trim().length() != 0) {
				String score = adaptee.jTextField3.getText().trim();
				if (query.length()!=0) {
					query += " and ";
				}
				query += "score>="+score+"";
			}
			if (adaptee.jTextField4.getText().trim().length() != 0) {
				String score = adaptee.jTextField4.getText().trim();
				if (query.length()!=0) {
					query += " and ";
				}
				query += "score<="+score+"";
			}
			if (query.length()!=0) {
				query = " where "+query;
			}
			query = "select * from sc"+query;
			Vector vec = new Vector(1, 1);
			ResultSet rs = conn.query(query);
			try {
                while (rs.next()) {
                	vec = new Vector();
                    vec.add(String.valueOf(rs.getInt("stuNumber")));
                    vec.add(rs.getString("courseName").trim());
                    vec.add(rs.getString("score").trim());
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

	class SCManager_jButton2_actionAdapter implements ActionListener{
		SCManager adaptee;
		public SCManager_jButton2_actionAdapter(SCManager adaptee) {
			// TODO Auto-generated constructor stub
			this.adaptee = adaptee;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int row = jTable1.getSelectedRow();
	        if(row == -1){
	            jOptionPane1.showMessageDialog(adaptee, "请选择要删除的元组！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	        }
	        String now = model.getValueAt(row, 0).toString().trim();
	        String now1 = model.getValueAt(row, 1).toString().trim();
	        DBConnection.getDBConnection().Update("delete from sc where stuNumber='" + now + "' and courseName='"+now1+"';");
	        jOptionPane1.showMessageDialog(adaptee, "恭喜您删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}
	}

	class SCManager_jButton3_actionAdapter implements ActionListener{
		SCManager adaptee;
		public SCManager_jButton3_actionAdapter(SCManager adaptee) {
			// TODO Auto-generated constructor stub
			this.adaptee = adaptee;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int row = jTable1.getSelectedRow();
	        if(row == -1){
	            jOptionPane1.showMessageDialog(adaptee, "请选择要修改的成绩！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	            return;
	        }
	        String score = JOptionPane.showInputDialog(adaptee,"请输入：(不改变为空)\n","成绩",JOptionPane.PLAIN_MESSAGE);
	        if (score==null) {
				return;
			}
	        if (score.length()==0) {
				return;
			}
	        conn.Update("update sc set score='"+score+"' where stuNumber='"+model.getValueAt(row, 0).toString().trim()+"' and courseName='"+model.getValueAt(row, 1).toString().trim()+"';");
	        jOptionPane1.showMessageDialog(adaptee, "恭喜您修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}
	}

	class SCManager_jButton4_actionAdapter implements ActionListener{
		SCManager adaptee;
		public SCManager_jButton4_actionAdapter(SCManager adaptee) {
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
