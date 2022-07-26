package ksrtcproject;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.UtilDateModel;
import java.sql.Date;



import java.awt.event.*;
//import java.util.Date;
import java.util.Properties;
//import org.jdatepicker.Util;
//import JDatePanelImpl;








public class ksrtc{
	//private static final GraphicsConfiguration GraphicsConfiguration = null;
	String username,password,database="Charan@2001";
	int flag;
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ResultSet resultSet1 = null;
    void authentication() throws Exception{
    try {
    	Class.forName("org.postgresql.Driver");
        connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC","postgres", database);
        
        preparedStatement = connect.prepareStatement("select * from customer where username = ?");
        //statement = connect.createStatement();
        //preparedStatement.setString(1, "");
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()) {
        if(resultSet.getString("password").equals(password)) {
        	flag=1;
        }}
        preparedStatement = connect.prepareStatement("select * from technical where emp_id = ?");
        //preparedStatement.setString(1, "");
        preparedStatement.setString(1, username);
        resultSet1 = preparedStatement.executeQuery();
        
        while(resultSet1.next()) {
        	
        	if(password.equals((resultSet1.getString("password")).trim())) {
            	
        		flag=2;
            }}
        if(username.equals("Admin")) {
        	if(password.equals("Admin")) {
        		flag=3;
        	}
        }
        
        
        
        
    	
    	
    } catch (Exception e) {
        throw e;
    }
    }
    
	void loginpage(){
	JFrame  frame = new JFrame("Login");
	JPanel panel = new JPanel();
	panel.setBounds(400,150,290,275);
	panel.setOpaque(false);
	JLabel userLabel = new JLabel("username");
	userLabel.setBounds(10, 10, 80, 25);
	JTextField userText = new JTextField(20);
	userText.setBounds(100, 10, 160, 25);
	JLabel passwordLabel = new JLabel("Password") ;
	passwordLabel.setBounds(10, 40, 80, 25);
	JPasswordField passwordText = new JPasswordField(20);
	passwordText.setBounds(100, 40, 160, 25);
	JButton loginButton = new JButton("login");
	loginButton.setBounds(150, 80, 80, 25);
	JLabel newuser = new JLabel("newuser");
	newuser.setBounds(100, 120, 50, 25);
	JButton registerButton = new JButton("Sign up");
	registerButton.setBounds(150, 125, 90, 15);
	JLabel background=new JLabel();
	background.setIcon(new ImageIcon("D:\\photo7.jpg"));
    background.setBounds(0, 0, 1420, 650);
	//background.setBounds(0, 0,1600, 1200);
	
	
	panel.add(userLabel);
	panel.add (userText);
	panel.add (passwordLabel);
	panel.add (passwordText);
	panel.add (loginButton);
	panel.add(newuser);
	panel.add (registerButton);
	
	panel.setLayout(null);
	frame.add(panel);
	frame.add(background);
	frame.setSize(1000, 680);
	frame.setLayout(null);
	frame.setVisible(true);
	loginButton.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			
			username=userText.getText();
			char pass[]=passwordText.getPassword();
			password= new String(pass);
			
			try {
				authentication();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(flag==1) {
				frame.setVisible(false);
			Customer obj = new Customer(username);
			obj.homepage();}
			else if(flag==2) {
				frame.setVisible(false);
				technical obj1 = new technical();
				obj1.tech_homepage();
			
			}
			else if(flag==3){
				frame.setVisible(false);
			admin obj2 = new admin();
			obj2.admin_homepage();
			}
			
			//login b = new login(); 
			//frame.setVisible(false);
			/*JButton source = (JButton) e.getSource();
			
		JOptionPane.showMessageDialog(source, source. getText()
		+ " button has been pressed");*/
		}
		}
		);

		registerButton.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			
			signup obj = new signup(" ");
			obj.profile("Sign Up","Sign Up");
		}
		}
		);
	
	}
	
	
	
public static void main(String[] args) {

ksrtc obj=new ksrtc();
obj.loginpage();
}

}




