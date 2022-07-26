package ksrtcproject;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class technical{
	String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,database="Charan@2001";
	String data[][];
	int d,c,b,t;
	java.sql.Date selectedDate;
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ResultSet rcheck = null;
	JFrame techframe = new JFrame();
	JPanel thppanel = new JPanel();
	JPanel viewdatapanel = new JPanel();
	JPanel assignbuspanel = new JPanel();           
	JPanel assignpanel = new JPanel();
	JPanel infopanel = new JPanel();
	
	JMenu menu = new JMenu("Menu");
	JMenuBar mb = new JMenuBar();	
	JMenuItem viewdata = new JMenuItem("View Data");
	JMenuItem assignbus = new JMenuItem("Assign Trip");
	JMenuItem assign = new JMenuItem("Assign ");
	
	JMenuItem travelinfo = new JMenuItem("Traveling Info");
	JMenuItem logout = new JMenuItem("Log Out");
	
	void addmenu() {
		menu.add(viewdata);
		menu.add(assignbus);
		menu.add(assign);
		//menu.add(assignconductor);
		menu.add(travelinfo);
		menu.add(logout);
		mb.add(menu);
		techframe.setJMenuBar(mb);
		assign.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				assign();
			
			}
			}
			);
		/*assignconductor.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
		
				assign("Assign Conductor","Conductor id");
	
			}
			}
			);*/
		assignbus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				assigntrip();
			
			}
			}
			);
		viewdata.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				viewdata();
			
			}
			}
			);
		travelinfo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				travellinginfo();
			
			}
			}
			);
		logout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				logout();
			
			}
			}
			);
		}
	void logout() {
		techframe.setVisible(false);
		ksrtc obj=new ksrtc();
		obj.loginpage();
	}
	void tech_homepage(){
		viewdatapanel.setVisible(false);
		assignbuspanel.setVisible(false);
		assignpanel.setVisible(false);  
		infopanel.setVisible(false);
		thppanel.setVisible(true);
		techframe.setTitle("Technical Employee");
		thppanel.setBounds(50,50,490,475);
		thppanel.setBackground(Color.gray);
		JButton vdButton = new JButton("View Data");
		vdButton.setBounds(180, 80, 150, 25);
		JButton abButton = new JButton("Assign");
		abButton.setBounds(180, 115, 150, 25);
		JButton adButton = new JButton("Assign Trip");
		adButton.setBounds(180, 150, 150, 25);
		//JButton acButton = new JButton("Assign Conductor");
		//acButton.setBounds(180, 185, 150, 25);
		JButton tiButton = new JButton("Travelled Info");
		tiButton.setBounds(180, 185, 150, 25);
		JButton lgButton = new JButton("Logout");
		lgButton.setBounds(180, 220, 150, 25);
		
		addmenu();
		thppanel.add(vdButton);
		thppanel.add(abButton);
		thppanel.add(abButton);
		thppanel.add(adButton);
		//thppanel.add(acButton);
		thppanel.add(tiButton);
		thppanel.add(lgButton);
		
		thppanel.setLayout(null);
		techframe.add(thppanel);
		techframe.setSize(605, 615);
		techframe.setLayout(null);
		techframe.setVisible(true);
		adButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				assign();
			
			}
			}
			);
		/*acButton.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
		
				assign("Assign Conductor","Conductor id");
	
			}
			}
			);*/
		abButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				assigntrip();
			
			}
			}
			);
		vdButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				viewdata();
			
			}
			}
			);
		tiButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				travellinginfo();
			
			}
			}
			);
		lgButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				logout();
			
			}
			}
			);
	}
	DefaultListModel<String> l1 = new DefaultListModel<>();
	DefaultListModel<String> l2 = new DefaultListModel<>();  
	DefaultListModel<String> l3 = new DefaultListModel<>();  
	DefaultListModel<String> l4 = new DefaultListModel<>();  
    
	void getviewdata() throws Exception {
try {
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                    "postgres", database);
            // PreparedStatements can use variables and are more efficient
        	statement = connect.createStatement();
        	resultSet = statement
                    .executeQuery("select count(*) from driver");
        	resultSet.next();
        	d=resultSet.getInt(1);
        	
        	resultSet = statement
                    .executeQuery("select * from driver inner join person on driver.person_id=person.person_id");
        	while(resultSet.next()) {
        		
        			l1.addElement(resultSet.getString("driver_id"));}
        	
        	resultSet = statement
                    .executeQuery("select count(*) from conductor");
        	resultSet.next();
        	c=resultSet.getInt(1);
        	resultSet = statement
                    .executeQuery("select * from conductor");
        	while(resultSet.next()) 
        	l2.addElement(resultSet.getString("conductor_id"));
        	
        	resultSet = statement
                    .executeQuery("select count(*) from bus");
        	resultSet.next();
        	b=resultSet.getInt(1);
        	resultSet = statement
                    .executeQuery("select * from bus");
        	while(resultSet.next()) 
        	l3.addElement(resultSet.getString("bus_id"));
        	
        	resultSet = statement
                    .executeQuery("select count(*) from trip");
        	resultSet.next();
        	t=resultSet.getInt(1);
        	resultSet = statement
                    .executeQuery("select * from trip");
        	while(resultSet.next()) 
        	l4.addElement(resultSet.getString("trip_id"));
        	
        	
            
            
            
        } catch (Exception e) {
            throw e;
        }
	
    
	}
	int flag=0;
	void viewdata(){
		thppanel.setVisible(false);
		assignbuspanel.setVisible(false); 
		assignpanel.setVisible(false);
		infopanel.setVisible(false);
		viewdatapanel.setVisible(true);
		techframe.setTitle("View Data");
		viewdatapanel.setBounds(50,50,650,475);
		viewdatapanel.setBackground(Color.gray);
		try {
			if(flag==0) {
			getviewdata();
			flag=1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel driversLable = new JLabel("Drivers");
		driversLable.setBounds(55, 75, 125, 25);
		driversLable.setVerticalAlignment(JLabel.TOP);
		driversLable.setFont(new Font("Verdana", Font.PLAIN, 18));
		driversLable.setForeground(Color.white);
		JList<String> list = new JList<>(l1);
        list.setBounds(25,100, 125,20*d);
        
        JLabel conductorsLable = new JLabel("Conductors");
        conductorsLable.setBounds(185, 75, 125, 25);
        conductorsLable.setVerticalAlignment(JLabel.TOP);
        conductorsLable.setFont(new Font("Verdana", Font.PLAIN, 18));
        conductorsLable.setForeground(Color.white);
        JList<String> list2 = new JList<>(l2);
        list2.setBounds(175,100, 125,20*c);
        
        JLabel busesLable = new JLabel("Buses");
        busesLable.setBounds(360, 75, 125, 25);
        busesLable.setVerticalAlignment(JLabel.TOP);
        busesLable.setFont(new Font("Verdana", Font.PLAIN, 18));
        busesLable.setForeground(Color.white);
        JList<String> list3 = new JList<>(l3);
        list3.setBounds(325,100, 125,20*b);
        
        JLabel tripLable = new JLabel("Trips");
        tripLable.setBounds(510, 75, 125, 25);
        tripLable.setVerticalAlignment(JLabel.TOP);
        tripLable.setFont(new Font("Verdana", Font.PLAIN, 18));
        tripLable.setForeground(Color.white);        
        JList<String> list4 = new JList<>(l4);
        list4.setBounds(475,100, 125,20*t);
        //addmenu();
        viewdatapanel.add(driversLable);
        viewdatapanel.add(conductorsLable);
        viewdatapanel.add(busesLable);
        viewdatapanel.add(tripLable);
        viewdatapanel.add(list);
        viewdatapanel.add(list2);
        viewdatapanel.add(list3);
        viewdatapanel.add(list4);
		viewdatapanel.setLayout(null);
		techframe.add(viewdatapanel);
		techframe.setSize(755, 615);
		techframe.setLayout(null);
		techframe.setVisible(true);
		
	}
	void writetrip() throws Exception {
		try {
		        	Class.forName("org.postgresql.Driver");
		            // Setup the connection with the DB
		        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
		                    "postgres", database);
		            // PreparedStatements can use variables and are more efficient
		        	statement = connect.createStatement();
		        	resultSet = statement
		                    .executeQuery("select count(*) from trip");
		        	resultSet.next();
		        	int ss1=resultSet.getInt(1);
		        	
		        	preparedStatement = connect
		                    .prepareStatement("insert into  trip values (?, ?, ?, ?,?,?)");
		            preparedStatement.setString(1, "trip-"+ss1);
		            preparedStatement.setString(2, s1);
		            preparedStatement.setString(3, s2);
		            preparedStatement.setDate(4, selectedDate);
		            preparedStatement.setString(5, s3);
		            preparedStatement.setString(6, s4);
		            preparedStatement.executeUpdate();
		            
		                //return true;
		            
		            
		        	//return false;
		            
		            
		            
		        } catch (Exception e) {
		            throw e;
		        }
			}
	void assigntrip(){
		thppanel.setVisible(false);
		viewdatapanel.setVisible(false);
		assignpanel.setVisible(false);
		infopanel.setVisible(false);
		assignbuspanel.setVisible(true);
		techframe.setTitle("Assign Trip");
		assignbuspanel.setBounds(50,50,490,475);
		assignbuspanel.setBackground(Color.gray);
		
		
		JLabel DepartureLable = new JLabel("Departure");
		DepartureLable.setBounds(100, 90, 80, 25);
		JTextField DepartureText = new JTextField(20);
		DepartureText.setBounds(190, 90, 160, 25);
		JLabel ArrivalLable = new JLabel("Arrival");
		ArrivalLable.setBounds(100, 120, 80, 25);
		JTextField ArrivalText = new JTextField(20);
		ArrivalText.setBounds(190, 120, 160, 25);
		JLabel DepTime_Lable = new JLabel("Departure Time");
		DepTime_Lable.setBounds(100, 150, 105, 25);
		JTextField DepTime_Text = new JTextField(20);
		DepTime_Text.setBounds(190, 150, 160, 25);		
		JLabel arriTime_Lable = new JLabel("Arrival Time");
		arriTime_Lable.setBounds(100, 180, 80, 25);
		JTextField arriTime_Text = new JTextField(20);
		arriTime_Text.setBounds(190, 180, 160, 25);
		JLabel datejouLable = new JLabel("Date of jouney");
		datejouLable.setBounds(100, 210, 80, 25);
		SqlDateModel model = new SqlDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model,new Properties());
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateComponentFormatter());
		datePicker.setBounds(190, 210, 160, 25);
		
		JButton assignButton = new JButton("Assign Trip");
		assignButton.setBounds(220, 260, 80, 25);
		//addmenu();
		//assignbuspanel.add(busidLabel);
		//assignbuspanel.add(busidText);
		assignbuspanel.add(DepartureLable);
		assignbuspanel.add(DepartureText);
		assignbuspanel.add(ArrivalLable);
		assignbuspanel.add(ArrivalText);
		assignbuspanel.add(DepTime_Lable);
		assignbuspanel.add(DepTime_Text);
		assignbuspanel.add(arriTime_Lable);
		assignbuspanel.add(arriTime_Text);
		assignbuspanel.add(datejouLable);
		assignbuspanel.add(datePicker);
		assignbuspanel.add(assignButton);
		assignbuspanel.setLayout(null);
		techframe.add(assignbuspanel);
		techframe.setSize(605, 615);
		techframe.setLayout(null);
		techframe.setVisible(true);
		assignButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				s1=DepartureText.getText();
				s2=ArrivalText.getText();
				s3=DepTime_Text.getText();
				s4=arriTime_Text.getText();
				selectedDate=(java.sql.Date) datePicker.getModel().getValue();
				
				try {
					writetrip();
					tech_homepage();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 //deleteemployee();
			
			}
			}
			);
	}
		
		
	
	void scheduletrip() throws Exception {
try {
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                    "postgres", database);
            // PreparedStatements can use variables and are more efficient
        	preparedStatement = connect
                    .prepareStatement("insert into  scheduletrip values (?, ?, ?, ?)");
            preparedStatement.setString(1, s1);
            preparedStatement.setString(2, s2);
            preparedStatement.setString(3, s3);
            preparedStatement.setString(4, s4);
            preparedStatement.executeUpdate();
            
                //return true;
            
            
        	//return false;
            
            
            
        } catch (Exception e) {
            throw e;
        }
	}
	void assign(){
	     
		thppanel.setVisible(false);
		viewdatapanel.setVisible(false); 
		assignbuspanel.setVisible(false);
		infopanel.setVisible(false);
		assignpanel.setVisible(true);
		techframe.setTitle("assign");
	     assignpanel.setBounds(50,50,490,475);
	     assignpanel.setBackground(Color.gray);
	     JLabel busidLable =new JLabel("Bus Id");
	     busidLable.setBounds(100,60,80,25);
	     JTextField busidText =new JTextField(20);
	     busidText.setBounds(190,60,160,25);
	     JLabel drividLable =new JLabel("Driver Id");
	     drividLable.setBounds(100,90,80,25);
	     JTextField drividText =new JTextField(20);
	     drividText.setBounds(190,90,160,25);
	     JLabel condidLable =new JLabel("Conductor Id");
	     condidLable.setBounds(100,120,80,25);
	     JTextField condidText =new JTextField(20);
	     condidText.setBounds(190,120,160,25);
	     JLabel tripidLable =new JLabel("Trip Id");
	     tripidLable.setBounds(100,150,80,25);
	     JTextField tripidText =new JTextField(20);
	     tripidText.setBounds(190,150,160,25);
	     JButton assign = new JButton("Assign");
	     assign.setBounds(220,200,80,25);
	     assignpanel.add(busidLable);
	     assignpanel.add(busidText);
	     assignpanel.add(drividLable);
	     assignpanel.add(drividText);
	     assignpanel.add(condidLable);
	     assignpanel.add(condidText);
	     assignpanel.add(tripidLable);
	     assignpanel.add(tripidText);
	     assignpanel.add(assign);
	     techframe.add(assignpanel);
	     assignpanel.setLayout(null);
	     techframe.setSize(605, 615);
	     techframe.setLayout(null);
	     techframe.setVisible(true);
	     assign.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					s1=busidText.getText();
					s2=drividText.getText();
					s3=condidText.getText();
					s4=tripidText.getText();
					try {
						scheduletrip();
						tech_homepage();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 //deleteemployee();
				
				}
				}
				);
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
	   techframe.setTitle("Booked Information");
	    infopanel.setBounds(50,50,990,675);
		infopanel.setBackground(Color.gray);
		thppanel.setVisible(false);
		viewdatapanel.setVisible(false); 
		assignbuspanel.setVisible(false);
		assignpanel.setVisible(false);
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
		techframe.setLayout(null);
		techframe.add(infopanel);          
		techframe.setSize(1105,915);    
		techframe.setVisible(true); 
}

}

