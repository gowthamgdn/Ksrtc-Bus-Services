package ksrtcproject;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class signup {
	String username,database="Charan@2001";
	signup(String s){
		username=s;
	}
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    int ss8,ss1;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    java.sql.Date selectedDate;
    
    
    boolean write() throws Exception {
    	
        try {
        	
        	
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                    "postgres", database);
            // PreparedStatements can use variables and are more efficient
        	statement = connect.createStatement();
        	resultSet = statement
                    .executeQuery("select count(*) from person");
        	resultSet.next();
        	ss1=resultSet.getInt(1);
        	s10=Integer.toString(ss1);
        	if(s2.equals(s3)) {
        	
            
            preparedStatement = connect
                    .prepareStatement("insert into  person values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
            
            
            
            // Parameters start with 1
            preparedStatement.setString(1, s10);
            preparedStatement.setString(2, s4);
            preparedStatement.setString(3, s5);
            preparedStatement.setString(4, s6);
            preparedStatement.setString(5, s7);
            preparedStatement.setDate(6, selectedDate);
            preparedStatement.setInt(7,ss8);
            preparedStatement.setString(8, "customer");
            preparedStatement.setString(9, s9);
            preparedStatement.setString(10, "registred");
            preparedStatement.executeUpdate();
            
            preparedStatement = connect
                    .prepareStatement("insert into  customer values (?, ?, ?)");
        	
        	preparedStatement.setString(1, s10);
            preparedStatement.setString(2, s1);
            preparedStatement.setString(3, s3);
            preparedStatement.executeUpdate();
            return true;
        	}
        	else {
        		return false;
        	}
        } catch (Exception e) {
            throw e;
        } 
        }
boolean writeupdate() throws Exception {
    	
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
        
        }
    
    void getdata() throws Exception{
try {
        	
        	
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                    "postgres", database);
            // PreparedStatements can use variables and are more efficient
        	statement = connect.createStatement();
        	resultSet = statement
                    .executeQuery("select count(*) from person");
        	resultSet.next();
        	ss1=resultSet.getInt(1);
        	s10=Integer.toString(ss1);
        	
        	
            
            preparedStatement = connect
                    .prepareStatement("select * from person INNER JOIN customer on person.person_id = customer.person_id where username = ?");
            
            
            
            // Parameters start with 1
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
            	s10=resultSet.getString("person_id");
            	s2=resultSet.getString("password");
            	s3=resultSet.getString("first_name");
            	s4=resultSet.getString("last_name");
            	s5=resultSet.getString("gender");
            	s6=resultSet.getString("email_id");
            	selectedDate =resultSet.getDate("date_of_birth");
            	ss1=resultSet.getInt("age");
            	s7=resultSet.getString("mobile");
            	
            }
            
            
            
        	
        	
        } catch (Exception e) {
            throw e;
        } 
    }
	void profile(String s,String ss){
		
		JFrame  signupframe = new JFrame(s);
		JPanel signuppanel = new JPanel();
		signuppanel.setBounds(50,50,490,475);
		signuppanel.setBackground(Color.gray);
		JLabel userLabel = new JLabel("username");
		userLabel.setBounds(100, 60, 80, 25);
		JTextField userText = new JTextField(20);
		userText.setBounds(190, 60, 160, 25);
		JLabel passwordLabel = new JLabel("Password") ;
		passwordLabel.setBounds(100, 90, 80, 25);
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(190, 90, 160, 25);
		JLabel confirmpassword = new JLabel("Confirm Password");
		confirmpassword.setBounds(100, 120, 80, 25);
		JTextField confirmpasswordText = new JTextField(20);
		confirmpasswordText.setBounds(190, 120, 160, 25);
		JLabel firstname = new JLabel("firstname");
		firstname.setBounds(100, 150, 80, 25);
		JTextField firstnameText = new JTextField(20);
		firstnameText.setBounds(190, 150, 160, 25);
		JLabel lastname = new JLabel("lastname");
		lastname.setBounds(100, 180, 80, 25);
		JTextField lastnameText = new JTextField(20);
		lastnameText.setBounds(190, 180, 160, 25);
		JLabel gender = new JLabel("gender");
		gender.setBounds(100, 210, 80, 25);
		JRadioButton male=new JRadioButton("Male");
		male.setBounds(190,210,60,25);
		male.setBackground(Color.gray);
		JRadioButton female=new JRadioButton("Female");
		female.setBounds(260,210,80,25);
		female.setBackground(Color.gray);
		ButtonGroup forgender=new ButtonGroup();
		forgender.add(male);
		forgender.add(female);
		
		JLabel mail = new JLabel("E-mail");
		mail.setBounds(100, 240, 80, 25);
		JTextField mailText = new JTextField(20);
		mailText.setBounds(190, 240, 160, 25);
		JLabel dobLabel = new JLabel("Date of Birth");
		dobLabel.setBounds(100, 270, 80, 25);
		SqlDateModel model = new SqlDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model,new Properties());
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateComponentFormatter());
		datePicker.setBounds(190, 270, 160, 25);
		JLabel age = new JLabel("age");
		age.setBounds(100, 300, 80, 25);
		JTextField age_value = new JTextField(20);
		age_value.setBounds(190, 300, 160, 25);
		
		
		JLabel phone = new JLabel("phone no");
		phone.setBounds(100, 330, 80, 25);
		JTextField phoneText = new JTextField(20);
		phoneText.setBounds(190, 330, 160, 25);
		
		JButton SignUpButton = new JButton(ss);
		SignUpButton.setBounds(230, 380, 80, 25);
		signuppanel.add(userLabel);
		signuppanel.add (userText);
		signuppanel.add (passwordLabel);
		signuppanel.add (passwordText);
		signuppanel.add (confirmpassword);
		signuppanel.add (confirmpasswordText);
		signuppanel.add (firstname);
		signuppanel.add (firstnameText);
		signuppanel.add (lastname);
		signuppanel.add (lastnameText);
		signuppanel.add (gender);
		signuppanel.add (male);
		signuppanel.add (female);
		signuppanel.add (mail);
		signuppanel.add (mailText);
		signuppanel.add (dobLabel);
		signuppanel.add (datePicker);
		signuppanel.add (age);
		signuppanel.add (age_value);
		signuppanel.add (phone);
		signuppanel.add (phoneText);
		signuppanel.add (SignUpButton);
		signuppanel.setLayout(null);
		signupframe.add(signuppanel);
		signupframe.setSize(605, 615);
		signupframe.setLayout(null);
		signupframe.setVisible(true);
		if (s.equals("Edit Pofile")){
			try {
				getdata();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			userText.setText(username);
			userText.setEditable(false);
			passwordText.setText(s2);
			confirmpasswordText.setText(s2);
			firstnameText.setText(s3);
			lastnameText.setText(s4);
			if(s5.equals("male")) {
				female.setEnabled(false);
				male.setSelected(true);
			}
			else {
				male.setEnabled(false);
				female.setSelected(true);
			}
			mailText.setText(s6);
			age_value.setText(Integer.toString(ss1));
			phoneText.setText(s7);
			
			
		}
		
		SignUpButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(ss.equals(s)) {
					
					selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				//ButtonModel b1;
				s1=userText.getText();
				char ch [] =passwordText.getPassword();
				s2=new String(ch);
				s3=confirmpasswordText.getText();
				s4=firstnameText.getText();
				s5=lastnameText.getText();
				//b1=forgender.getSelection();
				if (male.isSelected()) {
					  
                    s6 = "male";
                }
  
                else if (female.isSelected()) {
  
                    s6 = "female";
                }
                
				s7=mailText.getText();
				s8=age_value.getText();
				s9=phoneText.getText();
				ss8=Integer.parseInt(s8);
				try {
					signupframe.setVisible(!write());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				ksrtc obj=new ksrtc();
				obj.loginpage();
				}
				
				
				else if(s.equals("Edit Pofile")) {
					char ch [] =passwordText.getPassword();
					s2=new String(ch);
					s8=confirmpasswordText.getText();
					s3=firstnameText.getText();
					s4=lastnameText.getText();
					s6=mailText.getText();
					s7=age_value.getText();
					s9=phoneText.getText();
					ss8=Integer.parseInt(s7);
					try {
						signupframe.setVisible(!writeupdate());
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
	            	
				}
			/*JButton source = (JButton) e.getSource();

			JOptionPane.showMessageDialog(source, source. getText()
			+ " button has been pressed");*/
			}
			}
			);
	
	
	
}}
