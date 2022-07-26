package ksrtcproject;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class admin{
	JFrame ahpframe = new JFrame();
	JPanel ahppanel = new JPanel();
	JPanel addemppanel = new JPanel();
	JPanel deleteemppanel = new JPanel();
	JPanel addbuspanel = new JPanel();
	JPanel deletebuspanel = new JPanel();
	JPanel datapanel = new JPanel();
	JPanel infopanel = new JPanel();
	JMenu menu = new JMenu("Menu");
	JMenuBar mb = new JMenuBar();	
	JMenuItem m_add_emp = new JMenuItem("Add Employee");
	JMenuItem m_delete_emp = new JMenuItem("Delete Employee");
	//JMenuItem m_modify_emp = new JMenuItem("Modify Employee");
	JMenuItem m_add_bus = new JMenuItem("Add Bus");
	JMenuItem m_delete_bus = new JMenuItem("Delete Bus");
	//JMenuItem m_modify_bus = new JMenuItem("Modify Bus");
	JMenuItem m_check_sche = new JMenuItem("Check Schedule");
	JMenuItem m_t_info = new JMenuItem("Travelling Info");
	JMenuItem m_logout = new JMenuItem("Logout");
	String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,database="Charan@2001";
	int ss1,home=0;
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ResultSet rcheck = null;
    String data[][]; 
	void addmenu() {
		menu.add(m_add_emp);
		menu.add(m_delete_emp);
		//menu.add(m_modify_emp);
		menu.add(m_add_bus);
		menu.add(m_delete_bus);
		//menu.add(m_modify_bus);
		menu.add(m_check_sche);
		menu.add(m_t_info);
		menu.add(m_logout);
		mb.add(menu);
		ahpframe.setJMenuBar(mb);
		m_add_emp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 addemployee("Add Employee","Submit");
			
			}
			}
			);
		/*m_modify_emp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 addemployee("Modify Employee","Save");
			
			}
			}
			);*/
		m_delete_emp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 deleteemployee();
			
			}
			}
			);
		m_add_bus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 addbus("Add Bus","Submit");
			
			}
			}
			);
		/*m_modify_bus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 addbus("Modify Bus","Save");
			
			}
			}
			);*/
		m_delete_bus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 deletebus();
			
			}
			}
			);
		m_check_sche.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 viewschedule();
			
			}
			}
			);
		m_t_info.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 travellinginfo();
			
			}
			}
			);
		m_logout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 logout();
			
			}
			}
			);
		}
	void logout() {
		ahpframe.setVisible(false);
		ksrtc obj=new ksrtc();
		obj.loginpage();
	}
	void admin_homepage(){
		
		ahppanel.setVisible(true);
		addemppanel.setVisible(false);
		deleteemppanel.setVisible(false);
		addbuspanel.setVisible(false);
		deletebuspanel.setVisible(false);
		datapanel.setVisible(false);
		infopanel.setVisible(false);
		ahpframe.setTitle("Admin");
		ahppanel.setBounds(50,50,490,475);
		ahppanel.setBackground(Color.gray);
		
		JButton aeButton = new JButton("Add Employee");
		aeButton.setBounds(160, 80, 150, 25);
		JButton deButton = new JButton("Delete Employee");
		deButton.setBounds(160, 115, 150, 25);
		/*JButton meButton = new JButton("Modify Employee");
		meButton.setBounds(160, 150, 150, 25);*/
		JButton abButton = new JButton("Add Bus");
		abButton.setBounds(160, 150, 150, 25);
		JButton dbButton = new JButton("Delete Bus");
		dbButton.setBounds(160, 185, 150, 25);
		/*JButton mbButton = new JButton("Modify Bus");
		mbButton.setBounds(160, 255, 150, 25);*/
		JButton Check_scheButton = new JButton("Check Schedule");
		Check_scheButton.setBounds(160, 220, 150, 25);
		JButton travel_infoButton = new JButton("Travel Info");
		travel_infoButton.setBounds(160, 255, 150, 25);
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(160, 290, 150, 25);
		if(home==0) {
		addmenu();
		home=1;}
		ahppanel.add(aeButton);
		ahppanel.add(deButton);
		//ahppanel.add(meButton);
		ahppanel.add(abButton);
		ahppanel.add(dbButton);
		//ahppanel.add(mbButton);
		ahppanel.add(Check_scheButton);
		ahppanel.add(travel_infoButton);
		ahppanel.add(logoutButton);
		ahppanel.setLayout(null);
		ahpframe.add(ahppanel);
		ahpframe.setSize(605, 615);
		ahpframe.setLayout(null);
		ahpframe.setVisible(true);
		
		
		aeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 addemployee("Add Employee","Submit");
			
			}
			}
			);
		/*meButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 addemployee("Modify Employee","Save");
			
			}
			}
			);*/
		deButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 deleteemployee();
			
			}
			}
			);
		abButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 addbus("Add Bus","Submit");
			
			}
			}
			);
		/*mbButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 addbus("Modify Bus","Save");
			
			}
			}
			);*/
		dbButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 deletebus();
			
			}
			}
			);
		Check_scheButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 viewschedule();
			
			}
			}
			);
		travel_infoButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 travellinginfo();
			
			}
			}
			);
		logoutButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 logout();
			
			}
			}
			);
	}
	
	
boolean writetech() throws Exception {
    	
        try {
        	
        	int flag=0;
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                    "postgres", database);
            // PreparedStatements can use variables and are more efficient
        	statement = connect.createStatement();
        	s7="technical";
        	s8="driver";
        	s9="conductor";
        	
        	 
             	if(s7.equals(s6.trim())) {
             		flag=1;
             		s10=s7;
             		
             		
             	}
             	else if(s8.equals(s6.trim())) {
             		flag=2;
             		s10=s8;
             			
             		
             	}
             	else if(s9.equals(s6.trim())) {
             		flag=3;
             		s10=s9;
             		
             		
             		}
        	resultSet = statement
                    .executeQuery("select count(*) from person");
        	resultSet.next();
        	int ss1=resultSet.getInt(1);
        	s1=Integer.toString(ss1);
        	
        	if(flag != 0) {
        	
        	preparedStatement = connect
                    .prepareStatement("insert into  person (person_id,first_name,gender,age,type,mobile,status) values (?, ?, ?, ?, ?, ?,?)");
            preparedStatement.setString(1, s1);
            preparedStatement.setString(2, s2);
            preparedStatement.setString(3, s3);
            preparedStatement.setInt(4, Integer.parseInt(s4));
            preparedStatement.setString(5, s10);
            preparedStatement.setString(6, s5);
            preparedStatement.setString(7, "registred");
            preparedStatement.executeUpdate();}
            if(flag==1) {
            	rcheck =statement
                        .executeQuery("select count(*) from technical");
            	rcheck.next();
            preparedStatement = connect
                    .prepareStatement("insert into  technical values (?, ?, ?)");
        	preparedStatement.setString(1, s1);
            preparedStatement.setString(2, s10.substring(0, 4)+"-"+rcheck.getInt(1));
            preparedStatement.setString(3, "Technical");
            preparedStatement.executeUpdate();
            return true;}
            else if(flag==2) {
            	rcheck =statement
                        .executeQuery("select count(*) from driver");
            	
            	rcheck.next();
            	preparedStatement = connect
                        .prepareStatement("insert into  driver values (?, ?)");
            	preparedStatement.setString(1, s1);
                preparedStatement.setString(2, s10.substring(0, 4)+"-"+rcheck.getInt(1));
                //preparedStatement.setString(3, "Technical");
                preparedStatement.executeUpdate();
                return true;
            }
            else if(flag==3) {
            	rcheck =statement
                        .executeQuery("select count(*) from conductor");
            	
            	rcheck.next();
            	preparedStatement = connect
                        .prepareStatement("insert into  conductor values (?, ?)");
            	preparedStatement.setString(1, s1);
                preparedStatement.setString(2, s10.substring(0, 4)+"-"+rcheck.getInt(1));
                //preparedStatement.setString(3, "Technical");
                preparedStatement.executeUpdate();
                return true;
            }
            
        	return false;
            
            
            
        } catch (Exception e) {
            throw e;
        } 
        }
		
/*boolean writeupdate() throws Exception {
	
    try {
    	
    	Class.forName("org.postgresql.Driver");
    	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                "postgres", database);
    	
    	if(s2.equals(s8.trim())) {
    	
        
        preparedStatement = connect
                .prepareStatement("update customer set password = ? where person_id =?");
        
        
        
        // Parameters start with 1
        preparedStatement.setString(1, s8);
        preparedStatement.setString(2, s10);
        preparedStatement.executeUpdate();
       
        preparedStatement = connect
                .prepareStatement("update person set first_name = ? , last_name = ? , email_id = ? , age = ? , mobile = ? where person_id = ?");
    	
    	preparedStatement.setString(6, s10);
        preparedStatement.setString(1, s3);
        preparedStatement.setString(2, s4);
    	preparedStatement.setString(3, s6);
        preparedStatement.setInt(4, ss8);
        preparedStatement.setString(5, s9);
        preparedStatement.executeUpdate();
        return true;
    	}
    	else {
    		return false;
    	}
    	
    } catch (Exception e) {
        throw e;
    } 
    
    }*/

/*void getempdata() throws Exception{
try {
    	
    	
        // This will load the MySQL driver, each DB has its own driver
    	Class.forName("org.postgresql.Driver");
        // Setup the connection with the DB
    	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                "postgres", database);
        if(s1.substring(0,4).equals("tech")) { 
        preparedStatement = connect
                .prepareStatement("select * from person INNER JOIN technical on person.person_id = technical.person_id where emp_id = ?");
        
        
        
        // Parameters start with 1
        preparedStatement.setString(1, s1);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
        	
        	s2=resultSet.getString("first_name");
        	
        	s3=resultSet.getString("gender");
        	ss1=resultSet.getInt("age");
        	
        	s4=resultSet.getString("mobile");
        	
        }}
        
        
        
    	
    	
    } catch (Exception e) {
        throw e;
    } 

}*/
		
		void addemployee(String s,String ss){
			ahpframe.setTitle(s);
			addemppanel.setBounds(50,50,490,475);
			addemppanel.setBackground(Color.gray);
			ahppanel.setVisible(false);
			addemppanel.setVisible(true);
			deleteemppanel.setVisible(false);
			addbuspanel.setVisible(false);
			deletebuspanel.setVisible(false);
			datapanel.setVisible(false);
			infopanel.setVisible(false);
			JLabel empusernameLabel = new JLabel("Emp type");
			empusernameLabel.setBounds(100, 60, 80, 25);
			JTextField empusernameText = new JTextField(20);
			empusernameText.setBounds(190, 60, 160, 25);
			JLabel emp_nameLable = new JLabel("Name");
			emp_nameLable.setBounds(100, 90, 80, 25);
			JTextField emp_nameText = new JTextField(20);
			emp_nameText.setBounds(190, 90, 160, 25);
			JLabel gender = new JLabel("gender");
			gender.setBounds(100, 120, 80, 25);
			JRadioButton male=new JRadioButton("Male");
			male.setBounds(190,120,60,25);
			male.setBackground(Color.gray);
			JRadioButton female=new JRadioButton("Female");
			female.setBounds(260,120,80,25);
			female.setBackground(Color.gray);
			ButtonGroup forgender=new ButtonGroup();
			forgender.add(male);
			forgender.add(female);
			JLabel ageLable = new JLabel("age");
			ageLable.setBounds(100, 150, 80, 25);
			JTextField age_value = new JTextField(20);
			age_value.setBounds(190, 150, 160, 25);
			JLabel phoneLable = new JLabel("phone no");
			phoneLable.setBounds(100, 180, 80, 25);
			JTextField phoneText = new JTextField(20);
			phoneText.setBounds(190, 180, 160, 25);		
				
			JButton submitButton = new JButton(ss);
			submitButton.setBounds(220, 230, 80, 25);
			//addmenu();
			addemppanel.add(empusernameLabel);
			addemppanel.add(empusernameText);
			addemppanel.add(emp_nameLable);
			addemppanel.add(emp_nameText);
			addemppanel.add(gender);
			addemppanel.add(male);
			addemppanel.add(female);
			addemppanel.add(ageLable);
			addemppanel.add(age_value);
			addemppanel.add(phoneLable);
			addemppanel.add(phoneText);
			
			addemppanel.add(submitButton);
			addemppanel.setLayout(null);
			ahpframe.add(addemppanel);
			ahpframe.setSize(605, 615);
			ahpframe.setLayout(null);
			ahpframe.setVisible(true);
			submitButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
									
					s2=emp_nameText.getText();
					
					
					if (male.isSelected()) {
						  
	                    s3 = "male";
	                }
	  
	                else if (female.isSelected()) {
	  
	                    s3 = "female";
	                }
	                
					
					s4=age_value.getText();
					s5=phoneText.getText();
					
					s6=empusernameText.getText();
					
					try {
						if(writetech()) {
							admin_homepage();
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					
					
				/*JButton source = (JButton) e.getSource();

				JOptionPane.showMessageDialog(source, source. getText()
				+ " button has been pressed");*/
				}
				}
				);
			
		}
		void updatedelemp() throws Exception {
			try {
	        	Class.forName("org.postgresql.Driver");
	            // Setup the connection with the DB
	        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
	                    "postgres", database);
	        	if(s5.substring(0, 4).equals("tech")) {
	        	preparedStatement = connect
	                    .prepareStatement("select * from person inner join technical on person.person_id = technical.person_id where emp_id=?");
	        	preparedStatement.setString(1, s5);
	        	resultSet=preparedStatement.executeQuery();
	        	resultSet.next();
	        	s6=resultSet.getString("person_id");
	            // PreparedStatements can use variables and are more efficient
	        	preparedStatement = connect
	                    .prepareStatement("update person set status ='unregistred' where person_id=?");
	            preparedStatement.setString(1, s6);
	            
	            preparedStatement.executeUpdate();}
	        	
	        	else if(s5.substring(0, 4).equals("driv")) {
		        	preparedStatement = connect
		                    .prepareStatement("select * from person inner join driver on person.person_id = driver.person_id where driver_id=?");
		        	preparedStatement.setString(1, s5);
		        	resultSet=preparedStatement.executeQuery();
		        	resultSet.next();
		        	s6=resultSet.getString("person_id");
		            // PreparedStatements can use variables and are more efficient
		        	preparedStatement = connect
		                    .prepareStatement("update person set status ='unregistred' where person_id=?");
		            preparedStatement.setString(1, s6);
		            
		            preparedStatement.executeUpdate();}
	        	else if(s5.substring(0, 4).equals("cond")) {
		        	preparedStatement = connect
		                    .prepareStatement("select * from person inner join conductor on person.person_id = conductor.person_id where conductor_id=?");
		        	preparedStatement.setString(1, s5);
		        	resultSet=preparedStatement.executeQuery();
		        	resultSet.next();
		        	s6=resultSet.getString("person_id");
		            // PreparedStatements can use variables and are more efficient
		        	preparedStatement = connect
		                    .prepareStatement("update person set status ='unregistred' where person_id=?");
		            preparedStatement.setString(1, s6);
		            
		            preparedStatement.executeUpdate();}
	            } catch (Exception e) {
	            throw e;
	        }
		}
		void deleteemployee(){
			ahpframe.setTitle("Delete Employee"); 
			deleteemppanel.setBounds(50,50,490,475);
			deleteemppanel.setBackground(Color.gray);
			ahppanel.setVisible(false);
			addemppanel.setVisible(false);
			deleteemppanel.setVisible(true);
			addbuspanel.setVisible(false);
			deletebuspanel.setVisible(false);
			datapanel.setVisible(false);
			infopanel.setVisible(false);
			JLabel empidLabel = new JLabel("Emp id");
			empidLabel.setBounds(100, 60, 80, 25);
			JTextField empidText = new JTextField(20);
			empidText.setBounds(190,60,160,25);
			JButton deleteButton = new JButton("Delete");
			deleteButton.setBounds(220, 100, 80, 25);
			//addmenu();
			deleteemppanel.add(empidLabel);
			deleteemppanel.add(empidText);
			deleteemppanel.add(deleteButton);
			deleteemppanel.setLayout(null);
			ahpframe.add(deleteemppanel);
			ahpframe.setSize(605, 615);
			ahpframe.setLayout(null);
			ahpframe.setVisible(true);
			deleteButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					s5=empidText.getText();
					try {
						updatedelemp();
						admin_homepage();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//addemployee("Add Employee","Submit");
				
				}
				}
				);
		}
		void writebus() throws Exception {
			try {
	        	Class.forName("org.postgresql.Driver");
	            // Setup the connection with the DB
	        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
	                    "postgres", database);
	        	statement = connect.createStatement();
	        	resultSet = statement
	                    .executeQuery("select count(*) from bus");
	        	resultSet.next();
	        	
	        	
	            // PreparedStatements can use variables and are more efficient
	        	preparedStatement = connect
	                    .prepareStatement("insert into  bus values (?, ?, ?,?)");
	            preparedStatement.setString(1, "busid-"+resultSet.getInt(1));
	            preparedStatement.setString(2, s2);
	            preparedStatement.setInt(3, Integer.parseInt(s3));
	            preparedStatement.setString(4, "registred");
	            preparedStatement.executeUpdate();
	            } catch (Exception e) {
	            throw e;
	        }
		}
			void addbus(String s,String ss){
				ahpframe.setTitle(s);
				addbuspanel.setBounds(50,50,490,475);
				addbuspanel.setBackground(Color.gray);
				ahppanel.setVisible(false);
				addemppanel.setVisible(false);
				deleteemppanel.setVisible(false);
				addbuspanel.setVisible(true);
				deletebuspanel.setVisible(false);
				datapanel.setVisible(false);
				infopanel.setVisible(false);
				JLabel busidLabel = new JLabel("Bus id");
				busidLabel.setBounds(100, 60, 80, 25);
				JTextField busidText = new JTextField(20);
				busidText.setBounds(190, 60, 160, 25);
				JLabel bus_typeLable = new JLabel("Bus Type");
				bus_typeLable.setBounds(100, 90, 80, 25);
				JTextField bus_typeText = new JTextField(20);
				bus_typeText.setBounds(190, 90, 160, 25);
				JLabel seatsLable = new JLabel("Amount");
				seatsLable.setBounds(100, 120, 80, 25);
				JTextField seatsText = new JTextField(20);
				seatsText.setBounds(190,120,160,25);
				
						
				JButton submitButton = new JButton(ss);
				submitButton.setBounds(220, 160, 80, 25);
				//addmenu();
				addbuspanel.add(busidLabel);
				addbuspanel.add(busidText);
				addbuspanel.add(bus_typeLable);
				addbuspanel.add(bus_typeText);
				addbuspanel.add(seatsLable);
				addbuspanel.add(seatsText);
				addbuspanel.add(submitButton);
				addbuspanel.setLayout(null);
				ahpframe.add(addbuspanel);
				ahpframe.setSize(605, 615);
				ahpframe.setLayout(null);
				ahpframe.setVisible(true);
				submitButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						//s1=busidText.getText();
						s2=bus_typeText.getText();
						s3=seatsText.getText();
						try {
							writebus();
							admin_homepage();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//addemployee("Add Employee","Submit");
					
					}
					}
					);
			}
			void updatedelbus() throws Exception {
				try {
		        	Class.forName("org.postgresql.Driver");
		            // Setup the connection with the DB
		        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
		                    "postgres", database);
		        	
		        	
		        	
		            // PreparedStatements can use variables and are more efficient
		        	preparedStatement = connect
		                    .prepareStatement("update bus set status ='unregistred' where bus_id=?");
		            preparedStatement.setString(1, s5);
		            
		            preparedStatement.executeUpdate();
		            } catch (Exception e) {
		            throw e;
		        }
			}
			void deletebus(){
				ahpframe.setTitle("Delete Bus");
				
				deletebuspanel.setBounds(50,50,490,475);
				deletebuspanel.setBackground(Color.gray);
				ahppanel.setVisible(false);
				addemppanel.setVisible(false);
				deleteemppanel.setVisible(false);
				addbuspanel.setVisible(false);
				deletebuspanel.setVisible(true);
				datapanel.setVisible(false);
				infopanel.setVisible(false);
				JLabel busidLabel = new JLabel("Bus id");
				busidLabel.setBounds(100, 60, 80, 25);
				JTextField busidText = new JTextField(20);
				busidText.setBounds(190,60,160,25);
				JButton deleteButton = new JButton("Delete");
				deleteButton.setBounds(220, 100, 80, 25);
				//addmenu();
				deletebuspanel.add(busidLabel);
				deletebuspanel.add(busidText);
				deletebuspanel.add(deleteButton);
				deletebuspanel.setLayout(null);
				ahpframe.add(deletebuspanel);
				ahpframe.setSize(605, 615);
				ahpframe.setLayout(null);
				ahpframe.setVisible(true);
				deleteButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						s5=busidText.getText();
						try {
							updatedelbus();
							admin_homepage();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//addemployee("Add Employee","Submit");
					
					}
					}
					);
				
			}
			void getscheduletrip() throws Exception{
				try {
					Class.forName("org.postgresql.Driver");
					connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC","postgres", database);
					
					preparedStatement = connect.prepareStatement("select count(*) from scheduletrip inner join trip on scheduletrip.trip_id=trip.trip_id ");
			    	//preparedStatement.setString(1, username);
					resultSet = preparedStatement.executeQuery();
					resultSet.next();
					int ss1=resultSet.getInt(1);
			    	data=new String [ss1][7];
					preparedStatement = connect.prepareStatement("select * from scheduletrip inner join trip on scheduletrip.trip_id=trip.trip_id ");
			    	//preparedStatement.setString(1, username);
					resultSet = preparedStatement.executeQuery();
					
					int k=0;
					while(resultSet.next()) {
						data[k][0]=resultSet.getString("bus_id");
						data[k][1]=resultSet.getString("driver_id");
						data[k][2]=resultSet.getString("conductor_id");
						data[k][3]=resultSet.getString("source");
						data[k][4]=resultSet.getString("destination");
						data[k][5]=resultSet.getString("sourcetime");
						data[k][6]=resultSet.getString("destinationtime");
						//data[k][6]=resultSet.getString("dateofjourney");
						k++;
					}
					}
				catch (Exception e) {
			        throw e;
			    } 


			}
			   void viewschedule(){    
			    ahpframe.setTitle("View Schedule");
			    datapanel.setBounds(50,50,990,675);
				datapanel.setBackground(Color.gray);
				ahppanel.setVisible(false);
				addemppanel.setVisible(false);
				deleteemppanel.setVisible(false);
				addbuspanel.setVisible(false);
				deletebuspanel.setVisible(false);
				datapanel.setVisible(true);
				infopanel.setVisible(false);
				try {
					getscheduletrip();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    String column[]={"Bus No","Driver Id","Conductor Id","From","To","From Time","To Time"};         
			    JTable jt=new JTable(data,column);    
			    jt.setBounds(50,50,690,575);          
			    JScrollPane sp=new JScrollPane(jt);   
				sp.setBounds(50,50,890,575);
				//addmenu();
				datapanel.add(sp);
				datapanel.setLayout(null);
				ahpframe.setLayout(null);
			    ahpframe.add(datapanel);          
			    ahpframe.setSize(1105,915);    
			    ahpframe.setVisible(true);    
			}   
			   void getbooked() throws Exception{
					try {
						Class.forName("org.postgresql.Driver");
						connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC","postgres", database);
						
						preparedStatement = connect.prepareStatement("select count(*) from ticket_passengers inner join ticket on ticket.ticket_id=ticket_passengers.ticket_id ");
				    	//preparedStatement.setString(1, username);
						resultSet = preparedStatement.executeQuery();
						resultSet.next();
						int ss1=resultSet.getInt(1);
				    	data=new String [ss1][6];
						preparedStatement = connect.prepareStatement("select * from ticket_passengers inner join ticket on ticket.ticket_id=ticket_passengers.ticket_id");
				    	//preparedStatement.setString(1, username);
						resultSet = preparedStatement.executeQuery();
						
						int k=0;
						while(resultSet.next()) {
							data[k][0]=resultSet.getString("name");
							data[k][1]=resultSet.getString("mobile_no");
							data[k][2]=resultSet.getString("bus_id");
							data[k][3]=resultSet.getString("source");
							data[k][4]=resultSet.getString("destination");
							//data[k][5]=resultSet.getString("sourcetime");
							//data[k][6]=resultSet.getString("destinationtime");
							data[k][5]=resultSet.getString("dateofjourney");
							k++;
						}
						}
					catch (Exception e) {
				        throw e;
				    } 


				}
			   void travellinginfo(){    
				    ahpframe.setTitle("Booked Information");
				    infopanel.setBounds(50,50,990,675);
					infopanel.setBackground(Color.gray);
					ahppanel.setVisible(false);
					addemppanel.setVisible(false);
					deleteemppanel.setVisible(false);
					addbuspanel.setVisible(false);
					deletebuspanel.setVisible(false);
					datapanel.setVisible(false);
					infopanel.setVisible(true);
				    //String data[][]={        }; 
					try {
						getbooked();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    String column[]={"Passenger Name","Passenger PH No","Bus No","From","To","Date"};         
				    JTable jt=new JTable(data,column);    
				    jt.setBounds(50,50,690,575);          
				    JScrollPane sp=new JScrollPane(jt);   
					sp.setBounds(50,50,890,575);
					//addmenu();
					infopanel.add(sp);
					infopanel.setLayout(null);
					ahpframe.setLayout(null);
				    ahpframe.add(infopanel);          
				    ahpframe.setSize(1105,915);    
				    ahpframe.setVisible(true); 
}
}
