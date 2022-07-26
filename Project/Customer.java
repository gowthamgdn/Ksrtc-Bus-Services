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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class Customer{
	String username,database="Charan@2001";
	Customer(String s){
		username=s;
	}
	int n;
	String from[];
	String to[];
	String seats[];
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    int ss8,ss1;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,fromselected,toselected,selectedseat;
    java.sql.Date selectedDate;
    String data[][]={        };
	
	JFrame frame = new JFrame();
	JPanel homepanel = new JPanel();    
	JPanel bookingpanel = new JPanel();
	JPanel bookpanel = new JPanel();
	JMenu menu = new JMenu("Menu");
	JMenuBar mb = new JMenuBar();	
	JMenuItem m_editprofile = new JMenuItem("Edit Profile");
	JMenuItem m_bookticket = new JMenuItem("Book Ticket");
	JMenuItem m_mybooking = new JMenuItem("My Booking");
	JMenuItem m_logout = new JMenuItem("Log Out");
	//JLabel background=new JLabel(new ImageIcon("C:\\Users\\Jagan\\Desktop\\Charan\\Sem 3\\Project\\photo.jpg"));
	
	void addmenu() {
		menu.add(m_editprofile);
		menu.add(m_bookticket);
		menu.add(m_mybooking);
		menu.add(m_logout);
		mb.add(menu);
		frame.setJMenuBar(mb);
		m_editprofile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				signup obj = new signup(username);
				obj.profile("Edit Pofile","Save");
			//System.out.print("tyuu");
			}
			}
			);
		m_bookticket.addActionListener(new ActionListener() {
	
	public void actionPerformed(ActionEvent e) {
		booking();}});
	
		
	m_mybooking.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			mybooking();}});
	m_logout.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			logout();}});
		}
	
	void logout() {
		frame.setVisible(false);
		ksrtc obj=new ksrtc();
		obj.loginpage();
	}
	void homepage(){
		//frame.setLayout(new BorderLayout());
		//frame.add(background);
		//background.setLayout(new FlowLayout());
		homepanel.setVisible(true);
		bookingpanel.setVisible(false);
		bookpanel.setVisible(false);
		frame.setTitle("Home Page");
		homepanel.setBounds(50,50,290,275);
		homepanel.setBackground(Color.gray);
		JButton editprofileButton = new JButton("Edit Profile");
		editprofileButton.setBounds(75, 40, 140, 25);
		JButton bookticketButton = new JButton("Book Ticket");
		bookticketButton.setBounds(75, 75, 140, 25);
		JButton mybookingButton = new JButton("My Booking");
		mybookingButton.setBounds(75, 110, 140, 25);
		JButton logoutButton = new JButton("Log Out");
		logoutButton.setBounds(75, 145, 140, 25);
		//frame.add(background);
		//background.setLayout(new FlowLayout());
		
		addmenu();
		
		homepanel.add (editprofileButton);
		homepanel.add (bookticketButton);
		homepanel.add (mybookingButton);
		homepanel.add (logoutButton);
		homepanel.setLayout(null);
		//frame.add(background);
		frame.add(homepanel);
		frame.setSize(405, 415);
		frame.setLayout(null);
		frame.setVisible(true);
		
		editprofileButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				signup obj = new signup(username);
				
				obj.profile("Edit Pofile","Save");
			
			}
			}
			);
		
		
		bookticketButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				booking();
				
			}
			}
			);
		mybookingButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				mybooking();
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
	int amount;
	String bus;
	void getbusdata() throws Exception{
		try {
		        	
		        	
		            // This will load the MySQL driver, each DB has its own driver
		        	Class.forName("org.postgresql.Driver");
		            // Setup the connection with the DB
		        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
		                    "postgres", database);
		            // PreparedStatements can use variables and are more efficient
		        	statement = connect.createStatement();
		        	resultSet = statement
		                    .executeQuery("select count(*) from trip inner join scheduletrip on trip.trip_id=scheduletrip.trip_id");
		        	resultSet.next();
		        	ss1=resultSet.getInt(1);
		        	n=ss1;
		        	from = new String[n];
		        	to = new String[n];
		        	statement = connect.createStatement();
		        	resultSet = statement
		                    .executeQuery("select * from trip inner join scheduletrip on trip.trip_id=scheduletrip.trip_id");
		        	int i=0;
		            while(resultSet.next()) {
		            	from[i]=resultSet.getString("source");
		            	to[i]=resultSet.getString("destination");
		            	i++;		            	
		            }
		            
		        } catch (Exception e) {
		            throw e;
		        } 
		    }
	void writeticket() throws Exception{
		try {
			Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                    "postgres", database);
           	statement = connect.createStatement();
           	resultSet = statement
                    .executeQuery("select count(*) from ticket");
        	resultSet.next();
        	ss1=resultSet.getInt(1);
        	preparedStatement = connect
                    .prepareStatement("select * from bus where bus_id = ?");
            preparedStatement.setString(1,bus);
            resultSet = preparedStatement.executeQuery();
        	
        	//amount=resultSet.getInt("amount");
        	while(resultSet.next()) {
        	amount=resultSet.getInt("amount_for_person");
        	//System.out.println(resultSet.getString("amount"));
        	}
        	preparedStatement = connect
                    .prepareStatement("insert into Ticket values (?,?,?,?,?,?,?,?)");
        	preparedStatement.setString(1,"tic-"+ss1);
        	preparedStatement.setString(2,selectedseat);
        	preparedStatement.setDate(3,selectedDate);
        	preparedStatement.setInt(4,amount);
        	preparedStatement.setString(5,username);
        	preparedStatement.setString(6,fromselected);
        	preparedStatement.setString(7,toselected);
        	preparedStatement.setString(8,bus);
        	
        	preparedStatement.executeUpdate();
        	ticket_pass();
            
        } catch (Exception e) {
            throw e;
        }
	}
	boolean checktripdata()throws Exception{
		try {
			Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                    "postgres", database);
            // PreparedStatements can use variables and are more efficient
        	statement = connect.createStatement();
        	resultSet = statement
                    .executeQuery("select * from trip inner join scheduletrip on trip.trip_id=scheduletrip.trip_id");
        	while(resultSet.next()) {
        		//System.out.println(resultSet.getDate("dateofjourney").toString());
            	if((resultSet.getString("source").equals(fromselected)) && 
            			(resultSet.getString("destination").equals(toselected))
            			&&((selectedDate.toString()).equals(resultSet.getDate("dateofjourney").toString()))) {
            		
            		
            	    bus=resultSet.getString("bus_id");
            	    setseats(bus);
            		
            	 return true;
            	}
            }
            return false;
            
        } catch (Exception e) {
            throw e;
        } 
    }
	void setseats(String bus)throws Exception{
		try {
			
			//String query="select * from Ticket where bus_id = busid-0";
			Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                    "postgres", database);
            // PreparedStatements can use variables and are more efficient
        	statement = connect.createStatement();
        	
        	//resultSet.next();
        	//ss1=resultSet.getInt(1);
        	
        	seats = new String[18];
        	
        	
        		for(int i=1;i<=18;i++) {
        			preparedStatement = connect
                            .prepareStatement("select * from ticket where bus_id = ? and dateofjourney = ?");
                	preparedStatement.setString(1,bus);
                	preparedStatement.setDate(2,selectedDate);
                	resultSet = preparedStatement
                            .executeQuery();
                	
    					seats[i-1]=""+i;
        			while(resultSet.next()) {
        				if(resultSet.getString("seat").equals(""+i)) {
        					seats[i-1]="filled"+i;
        					continue;
        				}
            	
        			}
        	}
            //return false;
            
        } catch (Exception e) {
            throw e;
        } 
    }
	void ticket_pass() throws Exception{
		try {
			Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC",
                    "postgres", database);
           
        	preparedStatement = connect
                    .prepareStatement("insert into ticket_passengers values (?,?,?,?,?,?)");
        	preparedStatement.setString(1,"tic-"+ss1);
        	preparedStatement.setString(2,s1);
        	preparedStatement.setString(3,s2);
        	preparedStatement.setString(4,s3);
        	preparedStatement.setString(5,s4);
        	preparedStatement.setDate(6,selectedDate);
        	preparedStatement.executeUpdate();
        	
        } catch (Exception e) {
            throw e;
        }
	}
	
	JFrame f;    
	void passengerticket(){    
	    f=new JFrame();
	    JPanel p1 = new JPanel();
	    JPanel p2 = new JPanel();
	    JPanel p3 = new JPanel();
	    JPanel p5 = new JPanel();
	    JPanel p4 = new JPanel();
	    p4.setBounds(50,50,490,475);
	    p4.setBackground(Color.gray); 
	    JLabel custLabel = new JLabel ("Passenger's Details:");
	    custLabel .setBounds(150,0,250,50);
	    custLabel.setVerticalAlignment(JLabel.TOP);
	    custLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		custLabel.setForeground(Color.white);
	    JLabel custnameLabel = new JLabel ("Name:");
	    custnameLabel .setBounds(100, 60, 80, 25);
	    JTextField custnameText = new JTextField(20);
	    custnameText.setBounds(190, 60, 160, 25);
	    JLabel custphLabel = new JLabel ("ph no:");
	    custphLabel .setBounds(100,90,80,25);
	    JTextField custphText = new JTextField(10);
	    custphText.setBounds(190, 90, 160, 25);
	    JLabel custmailLabel = new JLabel ("Email.id:");
	    custmailLabel .setBounds(100,120,80,25);
	    JTextField custmailText = new JTextField(50);
	    custmailText.setBounds(190, 120, 160, 25);
	    JLabel gender = new JLabel("gender");
		gender.setBounds(100, 150, 80, 25);
		JRadioButton male=new JRadioButton("Male");
		male.setBounds(190,150,60,25);
		male.setBackground(Color.gray);
		JRadioButton female=new JRadioButton("Female");
		female.setBounds(260,150,80,25);
		female.setBackground(Color.gray);
		ButtonGroup forgender=new ButtonGroup();
		forgender.add(male);
		forgender.add(female);
		JLabel selectLabel = new JLabel ("Avaliable seats");
		selectLabel .setBounds(100,180,80,25);
		
		JComboBox seatscombo = new JComboBox(seats);
		seatscombo.setBounds(190, 180, 150, 25);
		JButton submit = new JButton("Submit");
		submit.setBounds(230, 230, 80, 25);
		p4.add(custLabel);
		
	    p4.add(custnameLabel);
	    p4.add(custnameText);
	    p4.add(custphLabel);
	    p4.add(custphText);
	    p4.add(custmailLabel);
	    p4.add(custmailText);
	    p4.add(gender);
	    p4.add(male);
	    p4.add(female);
	    p4.add(selectLabel);
	    p4.add(seatscombo);
	    p4.add(submit);
	    p4.setLayout(null);
	    f.add(p4);//f.add(p5);//f.add(p6);
	    // setting grid layout of 3 rows and 3 columns    
	    f.setLayout(null);    
	    f.setSize(605, 615);    
	    f.setVisible(true);
submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				s1=custnameText.getText();
				s2=custphText.getText();
				s3=custmailText.getText();
				if (male.isSelected()) {
					  
                    s4 = "male";
                }
  
                else if (female.isSelected()) {
  
                    s4 = "female";
                }
				
				selectedseat=seatscombo.getSelectedItem().toString();
				
				try {
					writeticket();
					f.setVisible(false);
					homepage();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
			}
			);
	}    

void booking(){
		
	homepanel.setVisible(false);
	bookingpanel.setVisible(true);
	bookpanel.setVisible(false);
		//JFrame bookingframe = new JFrame("Book Ticket");
		frame.setTitle("Book Ticket");
		//JPanel bookingpanel = new JPanel();
		bookingpanel.setBounds(50,50,490,475);
		bookingpanel.setBackground(Color.gray);
		
		JLabel searchLabel = new JLabel("Search for Buses");
		searchLabel.setBounds(140, 20, 210, 90);
		searchLabel.setVerticalAlignment(JLabel.TOP);
		searchLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		//searchLabel.setPreferredSize(new Dimension(1500, 1500));
		JLabel fromLabel = new JLabel("From");
		fromLabel.setBounds(160, 80, 80, 25);
		                                 //from places
		try {
			getbusdata();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JComboBox fc = new JComboBox(from);
		fc.setBounds(220, 80, 150, 25);
		JLabel toLabel = new JLabel("To");
		toLabel.setBounds(160, 140, 80, 25);
		
		JComboBox tc = new JComboBox(to);                            //to places
		tc.setBounds(220, 140, 150, 25);
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setBounds(160, 200, 80, 25);
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(180, 260, 90, 25);
		SqlDateModel model = new SqlDateModel();    
		JDatePanelImpl datePanel = new JDatePanelImpl(model,new Properties());
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateComponentFormatter());
		
		datePicker.setBounds(220, 200, 180, 25);
		//datePicker.setVisible(true);
		//addmenu();
		bookingpanel.add (searchLabel);
		bookingpanel.add (fc);
		bookingpanel.add (tc);
		bookingpanel.add (fromLabel);
		bookingpanel.add (toLabel);
		bookingpanel.add (dateLabel);
		//bookingpanel.add (date);
		bookingpanel.add (searchButton);
		bookingpanel.add(datePicker);
		bookingpanel.setLayout(null);
		frame.add(bookingpanel);
		frame.setSize(605, 615);
		frame.setLayout(null);
		frame.setVisible(true);
		homepanel.setVisible(false);
		searchButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				fromselected = fc.getSelectedItem().toString();
				toselected = tc.getSelectedItem().toString();
				//Date selectedDate = (Date) datePicker.getModel().getValue();                                             //date of journey
				selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				try {
					if(checktripdata())
					passengerticket();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
			}
			);
		
	}
void getmyBooking() throws Exception{
	try {
		Class.forName("org.postgresql.Driver");
		connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KSRTC","postgres", database);
		
		preparedStatement = connect.prepareStatement("select count(*) from ticket where username = ?");
    	preparedStatement.setString(1, username);
		resultSet = preparedStatement.executeQuery();
		resultSet.next();
		ss1=resultSet.getInt(1);
    	data=new String [ss1][6];
		preparedStatement = connect.prepareStatement("select * from ticket where username = ?");
    	preparedStatement.setString(1, username);
		resultSet = preparedStatement.executeQuery();
		
		int k=0;
		while(resultSet.next()) {
			data[k][0]=resultSet.getString("ticket_id");
			data[k][1]=resultSet.getString("bus_id");
			data[k][2]=resultSet.getString("source");
			data[k][3]=resultSet.getString("destination");
			data[k][4]=resultSet.getString("dateofjourney");
			data[k][5]=""+resultSet.getInt("seat");
			k++;
		}
		}
	catch (Exception e) {
        throw e;
    } 


}
	void mybooking(){    
		frame.setTitle("My Booking");  
		homepanel.setVisible(false);
		bookingpanel.setVisible(false);
		bookpanel.setVisible(true);
		bookpanel.setBounds(50,50,690,475);
		bookpanel.setBackground(Color.gray);
		try {
			getmyBooking();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String column[]={"Ticket No","Bus No","From","To","Date","Seat no"};         
		JTable jt=new JTable(data,column);    
		jt.setBounds(50,50,590,375);          
		JScrollPane sp=new JScrollPane(jt);   
		sp.setBounds(50,50,590,375);
		//addmenu();
		bookpanel.add(sp);
		bookpanel.setLayout(null);
		frame.setLayout(null);
		frame.add(bookpanel);          
		frame.setSize(805,615);    
		frame.setVisible(true);    
		
}
}
