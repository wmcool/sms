package Main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import Class.ClassAddFrame;
import Class.ClassManager;
import Course.CourseAddFrame;
import Course.CourseManager;
import Department.DepartmentAddFrame;
import Department.DepartmentManager;
import Student.StudentAddFrame;
import Student.StudentManager;
import User.UserAddFrame;
import db.DBConnection;
import sc.SCAddFrame;
import sc.SCManager;
import school.SchoolAddFrame;
import school.SchoolManager;

public class MainFrame extends JFrame {
	public static String user = "student";

	JLayeredPane jLayeredPane=new JLayeredPane();
	JPanel jPanel;
	ImageIcon imageIcon;
	JLabel jLabel1;
	JTextField jTextField1 = new JTextField();
	JPasswordField jPasswordField = new JPasswordField();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JTextField jTextField2 = new JTextField();
	JTextField jTextField3 = new JTextField();
	JOptionPane jOptionPane1 = new JOptionPane();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	
	public MainFrame() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

	private void jbInit() {
		// TODO Auto-generated method stub
		jPanel = (JPanel)getContentPane();
		jPanel.setLayout(null);
		setTitle("教学管理系统");
		JPanel bgp=new BackgroundPanel((new ImageIcon("src/Main/2.jpg")).getImage());
		bgp.setBounds(0,0,500,333);

//		jPanel.add(bgp);
//		jPanel.setOpaque(true);

//		jPanel.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
		
		jTextField1.setBounds(200,80,120,30);
		jPanel.add(jTextField1);
		
		jPasswordField.setBounds(200, 150,120,30);
		jPanel.add(jPasswordField);

		jLabel2.setText("用户名：");
		jLabel2.setBounds(80,80,80,30);
		jPanel.add(jLabel2);
		
		jLabel3.setText("密码：");
		jLabel3.setBounds(80,150,80,30);
		jPanel.add(jLabel3);
//		getLayeredPane().setLayout(null);
//		getLayeredPane().add(jLabel1, new Integer(Integer.MIN_VALUE));
//		setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500,333);
		
//		jTextField.setBounds(new Rectangle(180, 80, 180, 25));
//		jPanel.add(jTextField);
		
		JMenuBar jMenuBar1 = new JMenuBar();
		JMenu jMenu1 = new JMenu();
		jMenu1.setText("班级");
		JMenuItem jMenuItem11 = new JMenuItem();
		jMenuItem11.setText("添加班级");
		jMenuItem11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                	ClassAddFrame window = new ClassAddFrame();
		    				window.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
			}
		});
		jMenu1.add(jMenuItem11);
		JMenuItem jMenuItem12 = new JMenuItem();
		jMenuItem12.setText("班级管理");
		jMenuItem12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                	ClassManager window = new ClassManager();
		                	window.setSize(550,600);
		    				window.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
			}
		});
		jMenu1.add(jMenuItem12);
		
		JMenu jMenu2 = new JMenu();
		jMenu2.setText("课程");
		JMenuItem jMenuItem21 = new JMenuItem();
		jMenuItem21.setText("添加课程");
		jMenuItem21.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                	CourseAddFrame window = new CourseAddFrame();
		    				window.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
			}
		});
		jMenu2.add(jMenuItem21);
		JMenuItem jMenuItem22 = new JMenuItem();
		jMenuItem22.setText("课程管理");
		jMenuItem22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                	CourseManager window = new CourseManager();
//		                	window.setSize(550,600);
		    				window.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
			}
		});
		jMenu1.add(jMenuItem12);
		jMenu2.add(jMenuItem22);
		
		JMenu jMenu3 = new JMenu();
		jMenu3.setText("专业");
		JMenuItem jMenuItem31 = new JMenuItem();
		jMenuItem31.setText("添加专业");
		jMenuItem31.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		});
		jMenu3.add(jMenuItem31);
		JMenuItem jMenuItem32 = new JMenuItem();
		jMenuItem32.setText("专业管理");
		jMenuItem32.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		});
		jMenu3.add(jMenuItem32);
		
		JMenu jMenu4 = new JMenu();
		jMenu4.setText("成绩");
		JMenuItem jMenuItem41 = new JMenuItem();
		jMenuItem41.setText("添加成绩");
		jMenuItem41.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		});
		jMenu4.add(jMenuItem41);
		JMenuItem jMenuItem42 = new JMenuItem();
		jMenuItem42.setText("成绩管理");
		jMenuItem42.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		});
		jMenu4.add(jMenuItem42);
		
		JMenu jMenu5 = new JMenu();
		jMenu5.setText("学院");
		JMenuItem jMenuItem51 = new JMenuItem();
		jMenuItem51.setText("添加学院");
		jMenuItem51.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		});
		jMenu5.add(jMenuItem51);
		JMenuItem jMenuItem52 = new JMenuItem();
		jMenuItem52.setText("学院管理");
		jMenuItem52.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
		});
		jMenu5.add(jMenuItem52);
		
		JMenu jMenu6 = new JMenu();
		jMenu6.setText("学生");
		JMenuItem jMenuItem61 = new JMenuItem();
		jMenuItem61.setText("添加学生");
		jMenuItem61.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                	StudentAddFrame window = new StudentAddFrame();
		    				window.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
			}
		});
		jMenu6.add(jMenuItem61);
		JMenuItem jMenuItem62 = new JMenuItem();
		jMenuItem62.setText("学生管理");
		jMenuItem62.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                	StudentManager window = new StudentManager();
		                	window.setSize(550,600);
		    				window.setVisible(true);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
			}
		});
		jMenu6.add(jMenuItem62);


		jButton1.setText("登录");
		jButton1.setBounds(200, 250, 60, 30);
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jTextField1.getText().trim().length() == 0) {
					jOptionPane1.showMessageDialog(null, "用户名不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
				} 
				else if (jPasswordField.getPassword().length == 0) {
					jOptionPane1.showMessageDialog(null, "密码不能为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
				} 
				else {
					String password = new String(jPasswordField.getPassword());
					ResultSet rs = DBConnection.getDBConnection().query("select authority from user where userName='" + jTextField1.getText() + "' and password='" +
							password + "';");
					try {
						if(!rs.next()){
							jOptionPane1.showMessageDialog(null, "用户名或密码为空!", "提示", jOptionPane1.INFORMATION_MESSAGE, null);
						}else {
							user = rs.getString("authority");
							jOptionPane1.showMessageDialog(null, "登录成功，您的身份为" + user, "提示", jOptionPane1.INFORMATION_MESSAGE, null);
						}
						if(user.equals("student")){
							jMenuItem11.setVisible(false);
							jMenuItem21.setVisible(false);
							jMenuItem31.setVisible(false);
							jMenuItem41.setVisible(false);
							jMenuItem51.setVisible(false);
							jMenuItem61.setVisible(false);
						}else{
							jMenuItem11.setVisible(true);
							jMenuItem21.setVisible(true);
							jMenuItem31.setVisible(true);
							jMenuItem41.setVisible(true);
							jMenuItem51.setVisible(true);
							jMenuItem61.setVisible(true);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		jPanel.add(jButton1);

		jButton2.setText("注册");
		jButton2.setBounds(290, 250, 60,30);
		jButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserAddFrame window = new UserAddFrame();
				window.setVisible(true);
			}
		});
		jPanel.add(jButton2);
		
		jMenuBar1.add(jMenu1);
		jMenuBar1.add(jMenu2);
		jMenuBar1.add(jMenu3);
		jMenuBar1.add(jMenu4);
		jMenuBar1.add(jMenu5);
		jMenuBar1.add(jMenu6);
		setJMenuBar(jMenuBar1);

		if(user.equals("student")){
			jMenuItem11.setVisible(false);
			jMenuItem21.setVisible(false);
			jMenuItem31.setVisible(false);
			jMenuItem41.setVisible(false);
			jMenuItem51.setVisible(false);
			jMenuItem61.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	MainFrame window = new MainFrame();
                	window.setSize(500,400);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

class BackgroundPanel extends JPanel
{
	Image im;
	public BackgroundPanel(Image im)
	{
		this.im=im;
		this.setOpaque(true);
	}
	//Draw the back ground.
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
		
	}
}
