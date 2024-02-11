import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JCheckBoxMenuItem;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import javax.swing.SwingConstants;


public class VanRent {

    public JFrame frame;
    private JTextField customerNameField;
    private JTextField custid;
    private JTextField phnum;
    private JTextField textField_2;
    private JTextField t_bill;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField ppd;
	private JComboBox carid;
	private JComboBox comboBox_1;
	private JSpinner rentalDurationSpinner;
	private JDateChooser returnDateChooser;
	private JDateChooser tdate;
	private JTextField t_disc;
	private JTextField reid;
	private JTable rentalTable;
	private Connection connection;
	protected static String username;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	VanRent window = new VanRent(username);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VanRent(String username) {
    	this.username=username;
        initialize();
        populateVanRegNoComboBox();
        populateRentalTable();
        autoId();
        
    }
    
    private void populateVanRegNoComboBox() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");

            String sql = "SELECT Regno FROM vans";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            
            ArrayList<String> availableVanRegNos = new ArrayList<>();

            while (resultSet.next()) {
                String vanRegNo = resultSet.getString("Regno");

                
                if (!isVanRented(vanRegNo)) {
                    availableVanRegNos.add(vanRegNo);
                }
            }

            
            carid.setModel(new DefaultComboBoxModel(availableVanRegNos.toArray()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    
    private boolean isVanRented(String vanRegNo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");

            String sql = "SELECT Regno FROM rental WHERE Regno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vanRegNo);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    

    public void populateRentalTable() {
        DefaultTableModel model = (DefaultTableModel) rentalTable.getModel();
        model.setRowCount(0);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Rent_Id, Regno, Cus_Id, Return_Date FROM rental");

            while (resultSet.next()) {
                String rentId = resultSet.getString("Rent_Id");
                String RegNo = resultSet.getString("Regno");
                String cusId = resultSet.getString("Cus_Id");
                String returnDate = resultSet.getString("Return_Date");

                model.addRow(new Object[] { rentId, RegNo, cusId, returnDate });
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void autoId() {
    	
		try {
			Statement s = DB.mycon().createStatement();
			ResultSet rs = s.executeQuery("SELECT MAX(Rent_Id) FROM rental");
	        rs.next();
	        String maxID = rs.getString(1);

            if (maxID == null) {
            	reid.setText("R0001");
            } else {
                int id = Integer.parseInt(maxID.substring(1)) + 1;
                String nextID = String.format("R%04d", id);
                reid.setText(nextID);
                
            }
	        
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
    	
    }
    

    
    private void populateVanDetails(String RegNo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");

            String sql = "SELECT Van_Brand, Van_Model, Van_Color, Van_Price FROM vans WHERE Regno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, RegNo);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                textField_4.setText(resultSet.getString("Van_Brand"));
                textField_5.setText(resultSet.getString("Van_Model"));
                textField_6.setText(resultSet.getString("Van_Color"));
                ppd.setText(resultSet.getString("Van_Price"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void populateCustomerInfo(String customerIDOrPhoneNumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");

            String sql = "SELECT Cus_Id, Cus_Name, Cus_NIC, Cus_Mobile FROM customer WHERE Cus_Id = ? OR Cus_Mobile = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerIDOrPhoneNumber);
            preparedStatement.setString(2, customerIDOrPhoneNumber);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String customerID = resultSet.getString("Cus_Id");
                String customerName = resultSet.getString("Cus_Name");
                String customerNIC = resultSet.getString("Cus_NIC");
                String customerMobile = resultSet.getString("Cus_Mobile");

                custid.setText(customerID);
                customerNameField.setText(customerName);
                textField_2.setText(customerNIC);
                phnum.setText(customerMobile);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void updateReturnDate() {
        Date startDate = tdate.getDate();
        int rentalDuration = (int) rentalDurationSpinner.getValue();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, rentalDuration);

        Date returnDate = calendar.getTime();
        returnDateChooser.setDate(returnDate);
    }
    
    public void clearInputFields(){
    	carid.setSelectedIndex(0);
    	textField_4.setText("");
    	textField_5.setText("");
    	textField_6.setText("");
    	
        //textField_7.setText("");
        
    	custid.setText("");
    	phnum.setText("");
    	customerNameField.setText("");
    	textField_2.setText("");
    	comboBox_1.setSelectedIndex(0);
    	t_disc.setText("");
    	t_bill.setText("");
    	returnDateChooser.setDate(null);
    	rentalDurationSpinner.setValue(0);
    	
    }
    
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 808, 490);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCarRegNo = new JLabel("Vehicle Reg No:");
        lblCarRegNo.setBounds(53, 71, 126, 20);
        frame.getContentPane().add(lblCarRegNo);

        JLabel lblCustomerName = new JLabel("Customer Name :");
        lblCustomerName.setBounds(408, 162, 100, 14);
        frame.getContentPane().add(lblCustomerName);

        customerNameField = new JTextField();
        customerNameField.setEditable(false);
        customerNameField.setBounds(506, 159, 168, 20);
        frame.getContentPane().add(customerNameField);
        customerNameField.setColumns(10);

        JLabel lblRentalDuration = new JLabel("Rental Duration (days) :");
        lblRentalDuration.setBounds(53, 265, 133, 20);
        frame.getContentPane().add(lblRentalDuration);

        
        carid = new JComboBox();
        carid.setModel(new DefaultComboBoxModel(new String[] {""}));
        carid.setBounds(186, 71, 162, 20);
        populateVanRegNoComboBox();
        carid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCarRegNo = carid.getSelectedItem().toString();
                populateVanDetails(selectedCarRegNo);
            }
        });
        frame.getContentPane().add(carid);
        
        custid = new JTextField();
        custid.setBounds(506, 97, 168, 20);
        custid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerIDOrPhoneNumber = custid.getText();
                populateCustomerInfo(customerIDOrPhoneNumber);
            }
        });



        frame.getContentPane().add(custid);
        custid.setColumns(10);
        
        phnum = new JTextField();
        phnum.setBounds(506, 128, 168, 20);
        phnum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerIDOrPhoneNumber = phnum.getText();
                populateCustomerInfo(customerIDOrPhoneNumber);
            }
        });

        frame.getContentPane().add(phnum);
        phnum.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Customer ID :");
        lblNewLabel.setBounds(408, 100, 86, 14);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Phone Number :");
        lblNewLabel_1.setBounds(408, 131, 94, 14);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Enter Customer_ID or the Phone number");
        lblNewLabel_2.setBounds(408, 54, 266, 35);
        frame.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("NIC :");
        lblNewLabel_3.setBounds(408, 193, 86, 14);
        frame.getContentPane().add(lblNewLabel_3);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBounds(506, 190, 168, 20);
        frame.getContentPane().add(textField_2);
        
        
        JLabel lblNewLabel_4 = new JLabel("Add Discount :");
        lblNewLabel_4.setBounds(53, 317, 123, 22);
        frame.getContentPane().add(lblNewLabel_4);
        
        JLabel lblNewLabel_4_1 = new JLabel("Total Bill :");
        lblNewLabel_4_1.setBounds(53, 368, 123, 20);
        frame.getContentPane().add(lblNewLabel_4_1);
        
        comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "5%", "10%"}));
        comboBox_1.setBounds(186, 317, 162, 22);
        comboBox_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDiscountOption = comboBox_1.getSelectedItem().toString();
                if ("10%".equals(selectedDiscountOption)) {
                    
                    int rentalDuration = (int) rentalDurationSpinner.getValue();

                    double pricePerDay = Double.parseDouble(ppd.getText());
                    double totalBill = pricePerDay * (double)rentalDuration;

                    double discount = totalBill * 10.0/100;
                    double discountedTotal = totalBill - discount;

                    t_disc.setText("Rs. " + discount);
                    t_bill.setText("Rs. " + discountedTotal);
                }
                else if("5%".equals(selectedDiscountOption)) {
                	int rentalDuration = (int) rentalDurationSpinner.getValue();

                    double pricePerDay = Double.parseDouble(ppd.getText());
                    double totalBill = pricePerDay * (double)rentalDuration;

                    double discount = totalBill * 5.0/100;
                    double discountedTotal = totalBill - discount;

                    t_disc.setText("Rs. " + discount);
                    t_bill.setText("Rs. " + discountedTotal);
                }
                else {
                	int rentalDuration = (int) rentalDurationSpinner.getValue();
                	
                	double pricePerDay = Double.parseDouble(ppd.getText());
                    double totalBill = pricePerDay * (double)rentalDuration;
                    
                    double discount = 0;
                    double discountedTotal = totalBill - discount;
                	
                    t_disc.setText("Rs. " + discount);
                    t_bill.setText("Rs. " + discountedTotal);
                }
                
            }
        });
        frame.getContentPane().add(comboBox_1);
        
        t_bill = new JTextField();
        t_bill.setEditable(false);
        t_bill.setBounds(186, 368, 162, 20);
        frame.getContentPane().add(t_bill);
        t_bill.setColumns(10);
        
        textField_4 = new JTextField();
        textField_4.setEditable(false);
        textField_4.setBounds(186, 105, 162, 20);
        frame.getContentPane().add(textField_4);
        textField_4.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Brand :");
        lblNewLabel_5.setBounds(53, 105, 126, 20);
        frame.getContentPane().add(lblNewLabel_5);
        
        textField_5 = new JTextField();
        textField_5.setEditable(false);
        textField_5.setBounds(186, 136, 162, 20);
        frame.getContentPane().add(textField_5);
        textField_5.setColumns(10);
        
        textField_6 = new JTextField();
        textField_6.setEditable(false);
        textField_6.setBounds(186, 167, 162, 20);
        frame.getContentPane().add(textField_6);
        textField_6.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Model :");
        lblNewLabel_6.setBounds(53, 136, 126, 20);
        frame.getContentPane().add(lblNewLabel_6);
        
        ppd = new JTextField();
        ppd.setEditable(false);
        ppd.setColumns(10);
        ppd.setBounds(186, 198, 162, 20);
        frame.getContentPane().add(ppd);
        
        JLabel lblNewLabel_7 = new JLabel("Colour :");
        lblNewLabel_7.setBounds(53, 167, 126, 20);
        frame.getContentPane().add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Price per Day :");
        lblNewLabel_8.setBounds(53, 198, 126, 20);
        frame.getContentPane().add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Date :");
        lblNewLabel_9.setBounds(53, 240, 126, 20);
        frame.getContentPane().add(lblNewLabel_9);
        
        tdate = new JDateChooser();
        tdate.getCalendarButton().setEnabled(false);
        tdate.setDate(new Date());
        tdate.setBounds(186, 240, 162, 20);
        frame.getContentPane().add(tdate);
        
        JLabel lblNewLabel_10 = new JLabel("Return Date :");
        lblNewLabel_10.setBounds(53, 290, 123, 20);
        frame.getContentPane().add(lblNewLabel_10);
        
        rentalDurationSpinner = new JSpinner();
        rentalDurationSpinner.setBounds(186, 265, 162, 20);
        rentalDurationSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int rentalDuration = (int) rentalDurationSpinner.getValue();
                double pricePerDay = Double.parseDouble(ppd.getText());
                
                updateReturnDate();

                String selectedDiscountOption = comboBox_1.getSelectedItem().toString();
                if ("10%".equals(selectedDiscountOption)) {
                    double totalBill = pricePerDay * (double) rentalDuration;
                    double discount = totalBill * 10.0 / 100;
                    double discountedTotal = totalBill - discount;
                    t_bill.setText("Rs. " + discountedTotal);
                    t_disc.setText("Rs. " + discount);
                } else if ("5%".equals(selectedDiscountOption)) {
                    double totalBill = pricePerDay * (double) rentalDuration;
                    double discount = totalBill * 5.0 / 100;
                    double discountedTotal = totalBill - discount;
                    t_bill.setText("Rs. " + discountedTotal);
                    t_disc.setText("Rs. " + discount);
                } else {
                    
                    double totalBill = pricePerDay * (double) rentalDuration;
                    double discount = 0;
                    double discountedTotal = totalBill - discount;
                    t_bill.setText("Rs. " + discountedTotal);
                    t_disc.setText("Rs. " + discount);
                }
            }
        });

        frame.getContentPane().add(rentalDurationSpinner);

        returnDateChooser = new JDateChooser();
        returnDateChooser.getCalendarButton().setEnabled(false);
        returnDateChooser.setBounds(186, 290, 162, 20);
        frame.getContentPane().add(returnDateChooser);
        
        JLabel lblNewLabel_11 = new JLabel("Total Discount :");
        lblNewLabel_11.setBounds(53, 343, 126, 20);
        frame.getContentPane().add(lblNewLabel_11);
        
        t_disc = new JTextField();
        t_disc.setEditable(false);
        t_disc.setBounds(186, 343, 162, 20);
        frame.getContentPane().add(t_disc);
        t_disc.setColumns(10);
     
        rentalTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Rental ID");
        model.addColumn("Vehicle Registration No");
        model.addColumn("Customer ID");
        model.addColumn("Return Date");

        rentalTable.setModel(model);

        rentalTable.setBounds(394, 236, 360, 175);
        frame.getContentPane().add(rentalTable);

        
        JButton btnRentCar = new JButton("Rent");
        btnRentCar.setFont(new Font("Unispace", Font.BOLD, 10));
        btnRentCar.setBounds(53, 398, 75, 23);
        btnRentCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	financialCal();
                
            	try {
            		
            		 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
					
					 String rid = reid.getText();
			         String cusid = custid.getText();
			         String carregno = carid.getSelectedItem().toString();
			         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			         String date = sdf.format(tdate.getDate());
			         String duration = rentalDurationSpinner.getValue().toString();
			         String retdate = sdf.format(returnDateChooser.getDate());
			         
			         String sql = "INSERT INTO rental (`Rent_Id`, `Cus_Id`, `Regno`, `Date`, `Duration`, `Return_Date`, `VehicleType`) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
			         
			         PreparedStatement preparedStatement = connection.prepareStatement(sql);
			         
			         preparedStatement.setString(1, rid);
			         preparedStatement.setString(2, cusid);
			         preparedStatement.setString(3, carregno);
			         preparedStatement.setString(4, date);
			         preparedStatement.setString(5, duration);
			         preparedStatement.setString(6, retdate);
			         preparedStatement.setString(7, "Van");
			         
			         int rowsAffected = preparedStatement.executeUpdate();

			         if (rowsAffected > 0) {
			        	 JOptionPane.showMessageDialog(null, "New rental added successfully");
			        	 
			        	 populateRentalTable();
			        	 clearInputFields();
			        	 
			         } else {
			        	 JOptionPane.showMessageDialog(null, "Incorrect data check again");
			        	 
			        	 }
			         
			         connection.close();
			         
			         clearInputFields();
		             populateRentalTable();
		             autoId();
		             populateVanRegNoComboBox();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
            }
        });
        frame.getContentPane().add(btnRentCar);
        
        JButton btnRentCar_1 = new JButton("Clear");
        btnRentCar_1.setFont(new Font("Unispace", Font.BOLD, 10));
        btnRentCar_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		populateRentalTable();
        		clearInputFields();
        	}
        });
        btnRentCar_1.setBounds(138, 398, 75, 23);
        frame.getContentPane().add(btnRentCar_1);
        
        reid = new JTextField();
        reid.setEditable(false);
        reid.setBounds(109, 16, 86, 20);
        frame.getContentPane().add(reid);
        reid.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Rent ID");
        lblNewLabel_12.setFont(new Font("Unispace", Font.BOLD, 10));
        lblNewLabel_12.setBounds(53, 19, 46, 14);
        frame.getContentPane().add(lblNewLabel_12);
        
        JLabel lblNewLabel_13 = new JLabel("Rent ID");
        lblNewLabel_13.setBounds(394, 220, 81, 14);
        frame.getContentPane().add(lblNewLabel_13);
        
        JLabel lblNewLabel_13_1 = new JLabel("Car Reg NO");
        lblNewLabel_13_1.setBounds(485, 220, 81, 14);
        frame.getContentPane().add(lblNewLabel_13_1);
        
        JLabel lblNewLabel_13_2 = new JLabel("Customer ID");
        lblNewLabel_13_2.setBounds(576, 220, 81, 14);
        frame.getContentPane().add(lblNewLabel_13_2);
        
        JLabel lblNewLabel_13_3 = new JLabel("Return Date");
        lblNewLabel_13_3.setBounds(667, 220, 81, 14);
        frame.getContentPane().add(lblNewLabel_13_3);
        
        JButton btnRentCar_1_1 = new JButton("Delete");
        btnRentCar_1_1.setFont(new Font("Unispace", Font.BOLD, 10));
        btnRentCar_1_1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int selectedRow = rentalTable.getSelectedRow();
	            if (selectedRow >= 0) {
	                String selectedRentid = (String) model.getValueAt(selectedRow, 0);
	                if (deleteUser(selectedRentid)) {
	                    JOptionPane.showMessageDialog(null, "Deleted successfully");
	                    model.removeRow(selectedRow);
	                    autoId();
	                    populateRentalTable();
	                    populateVanRegNoComboBox();
	                    clearInputFields();
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Please select a data to delete.");
	            }
	        }
	    });
        btnRentCar_1_1.setBounds(223, 399, 75, 23);
        frame.getContentPane().add(btnRentCar_1_1);
        
        JButton btnNewButton_3_1 = new JButton("<");
        btnNewButton_3_1.setFont(new Font("Unispace", Font.BOLD, 10));
        btnNewButton_3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(username == null) {
					Admin ad = new Admin();
					frame.setVisible(false);
					ad.frame.setVisible(true);
				}
            	else {
            		Dashboard db = new Dashboard(username);
                    frame.setVisible(false);
                    db.frame.setVisible(true);
            	}
        	}
        });
        btnNewButton_3_1.setBounds(308, 398, 45, 23);
        frame.getContentPane().add(btnNewButton_3_1);
        
        JLabel lblNewLabel_7_1 = new JLabel("Van Rent");
        lblNewLabel_7_1.setOpaque(true);
        lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7_1.setForeground(Color.YELLOW);
        lblNewLabel_7_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
        lblNewLabel_7_1.setBackground(new Color(0, 128, 0));
        lblNewLabel_7_1.setBounds(0, 0, 794, 55);
        frame.getContentPane().add(lblNewLabel_7_1);
        
        JLabel lblNewLabel_7_1_1 = new JLabel("");
        lblNewLabel_7_1_1.setOpaque(true);
        lblNewLabel_7_1_1.setForeground(Color.YELLOW);
        lblNewLabel_7_1_1.setBackground(new Color(0, 128, 0));
        lblNewLabel_7_1_1.setBounds(0, 432, 794, 21);
        frame.getContentPane().add(lblNewLabel_7_1_1);
        
    }
    
    private void financialCal() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");

            double disc = 0.0;
            double total = 0.0;
            
            String discountText = t_disc.getText();
            String totalText = t_bill.getText();
            
            
            if (discountText.startsWith("Rs. ")) {
                discountText = discountText.substring(4);
                disc = Double.parseDouble(discountText);
            }
            
            if (totalText.startsWith("Rs. ")) {
                totalText = totalText.substring(4);
                total = Double.parseDouble(totalText);
            }

            double stotal = total + disc;

            String sql = "INSERT INTO financial (`Sub_Total`, `Discount`, `Total`) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, stotal);
            preparedStatement.setDouble(2, disc);
            preparedStatement.setDouble(3, total);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Financial Table Updated");


            } else {
                JOptionPane.showMessageDialog(null, "Error Updating Financial Table");

            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private boolean deleteUser(String rentid) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");

            String query = "DELETE FROM rental WHERE Rent_Id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, rentid);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
