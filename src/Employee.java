import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.toedter.calendar.JDateChooser;


public class Employee {

	public JFrame frame;
	private JTextField uname;
	private JTextField fname;
	private JTextField lname;
	private JTextField add;
	private JTextField ciity;
	private JTextField pcode;
	private JTextField nicc;
	private JTextField pnum;
	private JTextField mail;
	private JTextField pass;
	private JTextField bsalary;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private Connection connection;
    private JRadioButton male;
    private JRadioButton female;
    private JComboBox<String> jobtitle;
    private JComboBox<String> empstatus;
    private JComboBox<String> payfreque;
    private JComboBox<String> workshedule;
    private JButton deleteButton;
    private JButton btnNewButton;
	private JDateChooser dobb;
	private JLabel lblNewLabel_7_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Employee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 804, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add New Employees");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 128, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
		lblNewLabel.setBounds(0, 0, 790, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(42, 55, 95, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("First Name :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(42, 85, 93, 19);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Last Name :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(42, 115, 93, 19);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Address :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setBounds(42, 145, 93, 19);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("City :");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setBounds(42, 175, 93, 19);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Postal Code :");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1.setBounds(42, 205, 93, 19);
		frame.getContentPane().add(lblNewLabel_1_4_1);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("NIC :");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_2.setBounds(42, 235, 93, 19);
		frame.getContentPane().add(lblNewLabel_1_4_2);
		
		JLabel lblNewLabel_1_4_3 = new JLabel("Gender :");
		lblNewLabel_1_4_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_3.setBounds(42, 265, 93, 19);
		frame.getContentPane().add(lblNewLabel_1_4_3);
		
		JLabel lblNewLabel_1_4_4 = new JLabel("DOB :");
		lblNewLabel_1_4_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_4.setBounds(42, 295, 93, 19);
		frame.getContentPane().add(lblNewLabel_1_4_4);
		
		JLabel lblNewLabel_1_4_5 = new JLabel("Phone Number :");
		lblNewLabel_1_4_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_5.setBounds(42, 325, 93, 19);
		frame.getContentPane().add(lblNewLabel_1_4_5);
		
		JLabel lblNewLabel_1_4_6 = new JLabel("Email :");
		lblNewLabel_1_4_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_6.setBounds(42, 355, 95, 19);
		frame.getContentPane().add(lblNewLabel_1_4_6);
		
		JLabel lblNewLabel_1_4_7 = new JLabel("Job Title :");
		lblNewLabel_1_4_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_7.setBounds(436, 55, 132, 19);
		frame.getContentPane().add(lblNewLabel_1_4_7);
		
		JLabel lblNewLabel_1_5 = new JLabel("Employment Status :");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_5.setBounds(436, 85, 132, 19);
		frame.getContentPane().add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Pay Frequency :");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_6.setBounds(436, 115, 132, 19);
		frame.getContentPane().add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("Work Shedule :");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_7.setBounds(436, 145, 132, 19);
		frame.getContentPane().add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_8 = new JLabel("Salary Basic :");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_8.setBounds(436, 175, 132, 19);
		frame.getContentPane().add(lblNewLabel_1_8);
		
		JButton cancel = new JButton("Cancel");
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin ad= new Admin();
				frame.setVisible(false);
				ad.frame.setVisible(true);
			}
		});
		cancel.setBounds(691, 220, 75, 23);
		frame.getContentPane().add(cancel);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(42, 385, 95, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		 // Text field for username
		uname = new JTextField();
		uname.setBounds(145, 54, 200, 20);
		frame.getContentPane().add(uname);
		uname.setColumns(10);
		
		
		fname = new JTextField();
		fname.setColumns(10);
		fname.setBounds(145, 84, 200, 20);
		frame.getContentPane().add(fname);
		
		lname = new JTextField();
		lname.setColumns(10);
		lname.setBounds(145, 114, 200, 20);
		frame.getContentPane().add(lname);
		
		add = new JTextField();
		add.setColumns(10);
		add.setBounds(145, 144, 200, 20);
		frame.getContentPane().add(add);
		
		ciity = new JTextField();
		ciity.setColumns(10);
		ciity.setBounds(145, 174, 200, 20);
		frame.getContentPane().add(ciity);
		
		pcode = new JTextField();
		pcode.setColumns(10);
		pcode.setBounds(145, 204, 200, 20);
		frame.getContentPane().add(pcode);
		
		nicc = new JTextField();
		nicc.setToolTipText("XXXXXXXXXV");
		nicc.setColumns(10);
		nicc.setBounds(145, 234, 200, 20);
		frame.getContentPane().add(nicc);
		
		pnum = new JTextField();
		pnum.setToolTipText("07XXXXXXXX");
		pnum.setColumns(10);
		pnum.setBounds(145, 324, 200, 20);
		frame.getContentPane().add(pnum);
		
		mail = new JTextField();
		mail.setToolTipText("Example@gmail.com");
		mail.setColumns(10);
		mail.setBounds(145, 354, 200, 20);
		frame.getContentPane().add(mail);
		
		pass = new JTextField();
		pass.setToolTipText("Not more than 20 Characters");
		pass.setColumns(10);
		pass.setBounds(145, 382, 200, 20);
		frame.getContentPane().add(pass);
		
		bsalary = new JTextField();
		bsalary.setColumns(10);
		bsalary.setBounds(578, 174, 200, 20);
		frame.getContentPane().add(bsalary);
		
		// Radio buttons for gender
		male = new JRadioButton("Male");
        male.setBounds(145, 263, 75, 23);
        frame.getContentPane().add(male);

        female = new JRadioButton("Female");
        female.setBounds(270, 263, 75, 23);
        frame.getContentPane().add(female);
		
        jobtitle = new JComboBox<>();
		jobtitle.setModel(new DefaultComboBoxModel(new String[] {"", "Rental Agent", "Cashier"}));
		jobtitle.setBounds(578, 53, 200, 22);
		frame.getContentPane().add(jobtitle);
		
		empstatus = new JComboBox<>();
		empstatus.setModel(new DefaultComboBoxModel(new String[] {"", "Full-Time", "Part-Time"}));
		empstatus.setBounds(578, 83, 200, 22);
		frame.getContentPane().add(empstatus);
		
		payfreque = new JComboBox<>();
		payfreque.setModel(new DefaultComboBoxModel(new String[] {"", "Weekly", "Monthly"}));
		payfreque.setBounds(578, 113, 200, 22);
		frame.getContentPane().add(payfreque);
		
		workshedule = new JComboBox<>();
		workshedule.setModel(new DefaultComboBoxModel(new String[] {"", "Mon-Fri", "Sat-Sun"}));
		workshedule.setBounds(578, 143, 200, 22);
		frame.getContentPane().add(workshedule);
		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
		        try {
		            
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");

		            
		            String username = uname.getText();
		            String firstName = fname.getText();
		            String lastName = lname.getText();
		            String address = add.getText();
		            String city = ciity.getText();
		            String postalCode = pcode.getText();
		            String nic = nicc.getText();
		            String gender = male.isSelected() ? "Male" : "Female";
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            String dob = sdf.format(dobb.getDate());
		            String phoneNumber = pnum.getText();
		            String email = mail.getText();
		            String password = pass.getText();
		            String jobTitle = jobtitle.getSelectedItem().toString();
		            String employmentStatus = empstatus.getSelectedItem().toString();
		            String payFrequency = payfreque.getSelectedItem().toString();
		            String workSchedule = workshedule.getSelectedItem().toString();
		            String salaryBasic = bsalary.getText();
		            

		            
		            String sql = "INSERT INTO employee (`Username`, `First Name`, `Last Name`, `Address`, `City`, `Postal Code`, `NIC`, `Gender`, `DOB`, `Phone Number`, `Email`, `Password`, `Job Title`, `Employment Status`, `Pay Frequency`, `Work Shedule`, `Salary Basic`) " +
		                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		           
		            PreparedStatement preparedStatement = connection.prepareStatement(sql);
		            preparedStatement.setString(1, username);
		            preparedStatement.setString(2, firstName);
		            preparedStatement.setString(3, lastName);
		            preparedStatement.setString(4, address);
		            preparedStatement.setString(5, city);
		            preparedStatement.setString(6, postalCode);
		            preparedStatement.setString(7, nic);
		            preparedStatement.setString(8, gender);
		            preparedStatement.setString(9, dob);
		            preparedStatement.setString(10, phoneNumber);
		            preparedStatement.setString(11, email);
		            preparedStatement.setString(12, password);
		            preparedStatement.setString(13, jobTitle);
		            preparedStatement.setString(14, employmentStatus);
		            preparedStatement.setString(15, payFrequency);
		            preparedStatement.setString(16, workSchedule);
		            preparedStatement.setString(17, salaryBasic);

		            
		            int rowsAffected = preparedStatement.executeUpdate();

		            if (rowsAffected > 0) {
		            	
		            	JOptionPane.showMessageDialog(null, "New employee added successfully");
		            	refreshTable();
		                clearInputFields();
		            } else {
		                
		            	JOptionPane.showMessageDialog(null, "Incorrect data check again");
		            }

		            
		            connection.close();

		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            
		        }
		    }
		});
		submit.setBounds(436, 220, 75, 23);
		frame.getContentPane().add(submit);
		
		 // A "Delete" button and its action
		deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		deleteButton.addActionListener(new ActionListener() {
	        @Override
	        
	        // Logic for deleting a user
	        public void actionPerformed(ActionEvent e) {
	            int selectedRow = userTable.getSelectedRow();
	            if (selectedRow >= 0) {
	                String selectedUsername = (String) tableModel.getValueAt(selectedRow, 0);
	                if (deleteUser(selectedUsername)) {
	                    JOptionPane.showMessageDialog(null, "User deleted successfully");
	                    tableModel.removeRow(selectedRow);
	                    clearInputFields();
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Please select a user to delete.");
	            }
	        }
	    });
		deleteButton.setBounds(606, 220, 75, 23);
		frame.getContentPane().add(deleteButton);
		
		try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		tableModel = new DefaultTableModel(new Object[] { "Username" }, 0);
        userTable = new JTable(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setBounds(436, 267, 342, 132);
        userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String selectedUsername = (String) tableModel.getValueAt(selectedRow, 0);
                    loadUserData(selectedUsername);
                }
            }
        });
        frame.getContentPane().add(userTable);
        
        btnNewButton = new JButton("Clear");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearInputFields();
        	}
        });
        btnNewButton.setBounds(521, 220, 75, 23);
        frame.getContentPane().add(btnNewButton);
        
        dobb = new JDateChooser();
        dobb.setBounds(145, 294, 200, 20);
        frame.getContentPane().add(dobb);
        
        lblNewLabel_7_1 = new JLabel("");
        lblNewLabel_7_1.setOpaque(true);
        lblNewLabel_7_1.setForeground(Color.YELLOW);
        lblNewLabel_7_1.setBackground(new Color(0, 128, 0));
        lblNewLabel_7_1.setBounds(0, 414, 794, 35);
        frame.getContentPane().add(lblNewLabel_7_1);
        
        loadUsernames();
		
	}
	// Logic for refreshing the user table
	private void refreshTable() {
	    tableModel.setRowCount(0);
	    loadUsernames();
	}
	
	private boolean deleteUser(String username) {
	    try {
	        String query = "DELETE FROM employee WHERE Username = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, username);
	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}
	
	// Logic for refreshing the user table
	private void clearInputFields() {
	    uname.setText("");
	    fname.setText("");
	    lname.setText("");
	    add.setText("");
	    ciity.setText("");
	    pcode.setText("");
	    nicc.setText("");
	    dobb.setDate(null);
	    pnum.setText("");
	    mail.setText("");
	    pass.setText("");
	    bsalary.setText("");
	    male.setSelected(false);
	    female.setSelected(false);
	    jobtitle.setSelectedIndex(0);
	    empstatus.setSelectedIndex(0);
	    payfreque.setSelectedIndex(0);
	    workshedule.setSelectedIndex(0);
	}
	
	// Logic for loading usernames into the user table
	private void loadUsernames() {
        SwingWorker<Void, Vector<Object>> worker = new SwingWorker<Void, Vector<Object>>() {
            @Override
            protected Void doInBackground() {
                try {
                    String query = "SELECT Username FROM employee";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(resultSet.getString("Username"));
                        publish(row);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Vector<Object>> chunks) {
                for (Vector<Object> row : chunks) {
                    tableModel.addRow(row);
                }
            }
        };
        worker.execute();
    }

	// Logic for loading user data based on username
	private void loadUserData(String username) {
	    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() {
	            try {
	                String query = "SELECT * FROM employee WHERE Username = ?";
	                PreparedStatement preparedStatement = connection.prepareStatement(query);
	                preparedStatement.setString(1, username);
	                ResultSet resultSet = preparedStatement.executeQuery();

	                if (resultSet.next()) {
	                    uname.setText(resultSet.getString("Username"));
	                    fname.setText(resultSet.getString("First Name"));
	                    lname.setText(resultSet.getString("Last Name"));
	                    add.setText(resultSet.getString("Address"));
	                    ciity.setText(resultSet.getString("City"));
	                    pcode.setText(resultSet.getString("Postal Code"));
	                    nicc.setText(resultSet.getString("NIC"));
	                    java.util.Date dob = resultSet.getDate("DOB");
	                    dobb.setDate(dob);
	                    pnum.setText(resultSet.getString("Phone Number"));
	                    mail.setText(resultSet.getString("Email"));
	                    bsalary.setText(resultSet.getString("Salary Basic"));
	                    
	                    String gender = resultSet.getString("Gender");
                        if ("Male".equals(gender)) {
                            male.setSelected(true);
                            female.setSelected(false);
                        } else if ("Female".equals(gender)) {
                            male.setSelected(false);
                            female.setSelected(true);
                        }
                        
                        String jobTitle = resultSet.getString("Job Title");
                        String employmentStatus = resultSet.getString("Employment Status");
                        String payFrequency = resultSet.getString("Pay Frequency");
                        String workSchedule = resultSet.getString("Work Shedule");
                        
                        jobtitle.setSelectedItem(jobTitle);
                        empstatus.setSelectedItem(employmentStatus);
                        payfreque.setSelectedItem(payFrequency);
                        workshedule.setSelectedItem(workSchedule);
                        
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            return null;
	        }
	    };
	    worker.execute();
	}
}
