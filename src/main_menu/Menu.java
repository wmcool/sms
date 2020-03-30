package main_menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Class.ClassAddFrame;
import Class.ClassManager;
import Course.CourseAddFrame;
import Course.CourseManager;
import Department.DepartmentAddFrame;
import Department.DepartmentManager;
import Student.StudentAddFrame;
import Student.StudentManager;
import db.DBConnection;
import sc.SCAddFrame;
import sc.SCManager;
import school.SchoolAddFrame;
import school.SchoolManager;

public class Menu extends JFrame {
	JLayeredPane jLayeredPane=new JLayeredPane();
	JPanel jPanel;
	ImageIcon imageIcon;
	JLabel jLabel1;
	JTextField jTextField1 = new JTextField();
	JPasswordField jPasswordField = new JPasswordField();
	JButton jButton = new JButton();
	JTextField jTextField2 = new JTextField();
	JTextField jTextField3 = new JTextField();
	
	public Menu() {
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
		
		JPanel bgp=new BackgroundPanel((new ImageIcon("src/main_menu/2.jpg")).getImage());
		bgp.setBounds(0,0,500,333);
		
		jPanel.add(bgp);
//		jPanel.setOpaque(true);

//		jPanel.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
		
		jTextField1.setBounds(200,80,120,30);
		jPanel.add(jTextField1);
		
		jPasswordField.setBounds(200, 190,120,30);
		jPanel.add(jPasswordField);
		
		jButton.setText("登录");
		jButton.setBounds(200, 250, 60, 30);
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBConnection.userString = jTextField1.getText();
				DBConnection.passwordString = jPasswordField.getPassword().toString();
				String sql = "select * from class;";
		        
				try {
					Statement stmt = DBConnection.getDBConnection("root", "").getConnection().createStatement();
			        ResultSet set;
					set = stmt.executeQuery(sql);
					 while (set.next()) {
				            String mclass = set.getString("schoolName");
				            System.out.println(mclass);
				        }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       
			}
		});
		jPanel.add(jButton);
		
		
//		getLayeredPane().setLayout(null);
//		getLayeredPane().add(jLabel1, new Integer(Integer.MIN_VALUE));
//		setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500,333);
		
//		jTextField.setBounds(new Rectangle(180, 80, 180, 25));
//		jPanel.add(jTextField);
		
		JMenuBar jMenuBar1 = new JMenuBar();
		JMenu jMenu1 = new JMenu();
		jMenu1.setText("class");
		JMenuItem jMenuItem11 = new JMenuItem();
		jMenuItem11.setText("add");
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
		jMenuItem12.setText("manage");
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
		jMenu2.setText("cource");
		JMenuItem jMenuItem21 = new JMenuItem();
		jMenuItem21.setText("add");
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
		jMenuItem22.setText("manage");
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
		jMenu3.setText("depart");
		JMenuItem jMenuItem31 = new JMenuItem();
		jMenuItem31.setText("add");
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
		jMenuItem32.setText("manage");
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
		jMenu4.setText("sc");
		JMenuItem jMenuItem41 = new JMenuItem();
		jMenuItem41.setText("add");
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
		jMenuItem42.setText("manage");
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
		jMenu5.setText("school");
		JMenuItem jMenuItem51 = new JMenuItem();
		jMenuItem51.setText("add");
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
		jMenuItem52.setText("manage");
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
		jMenu6.setText("student");
		JMenuItem jMenuItem61 = new JMenuItem();
		jMenuItem61.setText("add");
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
		jMenuItem62.setText("manage");
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
		
		jMenuBar1.add(jMenu1);
		jMenuBar1.add(jMenu2);
		jMenuBar1.add(jMenu3);
		jMenuBar1.add(jMenu4);
		jMenuBar1.add(jMenu5);
		jMenuBar1.add(jMenu6);
		setJMenuBar(jMenuBar1);
	}
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Menu window = new Menu();
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
