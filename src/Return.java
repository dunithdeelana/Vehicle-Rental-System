import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Return {
    public JFrame frame;
    private JComboBox<String> regNoComboBox;
    private JTable rentedVehicleTable;
    private String selectedVehicleType;
	protected static String username;

    public static void main(String[] args) {
        // Initialize the Swing application
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create an instance of the Return class
                    Return window = new Return(username);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Return(String username) {
    	this.username = username;
        initialize();
        populateTable();
    }

    private void initialize() {
        // Create the main application frame
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Create and set up the label for selecting a vehicle type
        JLabel lblVehicleType = new JLabel("Select Vehicle Type:");
        lblVehicleType.setBounds(44, 82, 150, 20);
        frame.getContentPane().add(lblVehicleType);

        // Create radio buttons for vehicle type selection
        JRadioButton carRadioButton = new JRadioButton("Car");
        carRadioButton.setBounds(200, 82, 60, 20);
        frame.getContentPane().add(carRadioButton);

        JRadioButton vanRadioButton = new JRadioButton("Van");
        vanRadioButton.setBounds(260, 82, 60, 20);
        frame.getContentPane().add(vanRadioButton);

        JRadioButton bikeRadioButton = new JRadioButton("Bike");
        bikeRadioButton.setBounds(320, 82, 60, 20);
        frame.getContentPane().add(bikeRadioButton);

        // Create a button group to ensure only one radio button is selected at a time
        ButtonGroup vehicleTypeButtonGroup = new ButtonGroup();
        vehicleTypeButtonGroup.add(carRadioButton);
        vehicleTypeButtonGroup.add(vanRadioButton);
        vehicleTypeButtonGroup.add(bikeRadioButton);

        // Add an action listener to handle radio button selections
        carRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedVehicleType = "Car";
                populateTable();
            }
        });

        vanRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedVehicleType = "Van";
                populateTable();
            }
        });

        bikeRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedVehicleType = "Bike";
                populateTable();
            }
        });

        // Create the combo box for vehicle registration numbers
        regNoComboBox = new JComboBox<String>();
        regNoComboBox.setBounds(200, 110, 180, 20);
        frame.getContentPane().add(regNoComboBox);

        // Create and set up the "Return" button
        JButton returnButton = new JButton("Return");
        returnButton.setFont(new Font("Unispace", Font.BOLD, 12));
        returnButton.setBounds(212, 285, 100, 30);
        frame.getContentPane().add(returnButton);

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(443, 62, 333, 267);
        frame.getContentPane().add(scrollPane);
        
        // Create a date chooser for selecting the return date
        JDateChooser tdate = new JDateChooser();
        tdate.getCalendarButton().setEnabled(false);
        tdate.setDate(new Date());
        tdate.setBounds(200, 144, 180, 20);
        frame.getContentPane().add(tdate);

        // Create the table for displaying rented vehicles
        rentedVehicleTable = new JTable();
        scrollPane.setViewportView(rentedVehicleTable);
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Vehicle Registration No");
        tableModel.addColumn("Customer ID");
        rentedVehicleTable.setModel(tableModel);
        
        // Create labels for vehicle registration number and date
        JLabel lblNewLabel = new JLabel("Vehicle Reg No : ");
        lblNewLabel.setBounds(44, 110, 150, 20);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Date :");
        lblNewLabel_1.setBounds(44, 144, 150, 20);
        frame.getContentPane().add(lblNewLabel_1);
        
        // Create and set up the "Cancel" button
        JButton btnNewButton = new JButton("Cancel");
        btnNewButton.setFont(new Font("Unispace", Font.BOLD, 12));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Handle the "Cancel" button action
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
        btnNewButton.setBounds(320, 285, 100, 30);
        frame.getContentPane().add(btnNewButton);
        
        // Create and set up labels for the application's title and footer
        JLabel lblNewLabel_7_1 = new JLabel("Return Vehicle");
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
        lblNewLabel_7_1_1.setBounds(0, 333, 794, 30);
        frame.getContentPane().add(lblNewLabel_7_1_1);

        // Add an action listener to the "Return" button for returning a vehicle
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the selected registration number from the combo box
                String regNo = regNoComboBox.getSelectedItem().toString();
                // Attempt to return the selected vehicle and show a success or failure message
                if (returnVehicle(regNo)) {
                    JOptionPane.showMessageDialog(frame, "Vehicle returned successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Vehicle return failed. Check the registration number.");
                }
                populateTable(); // After returning, refresh the table
            }
        });
    }

    // Method to populate the table with rented vehicles
    private void populateTable() {
        DefaultTableModel tableModel = (DefaultTableModel) rentedVehicleTable.getModel();
        tableModel.setRowCount(0);
        regNoComboBox.removeAllItems(); // Clear the combo box

        if (selectedVehicleType != null) {
            try {
                // Establish a database connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
                // SQL query to select rented vehicles of the specified type
                String sql = "SELECT DISTINCT Regno, Cus_Id, VehicleType FROM rental WHERE VehicleType = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, selectedVehicleType);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String vehRegNo = resultSet.getString("Regno");
                    String customerId = resultSet.getString("Cus_Id");

                    // Add the item in the format: "RegistrationNumber,VehicleType"
                    regNoComboBox.addItem(vehRegNo);

                    Object[] row = {vehRegNo, customerId};
                    tableModel.addRow(row);
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to return a vehicle based on its registration number
    private boolean returnVehicle(String regNo) {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
            // SQL query to delete the rental record for the selected vehicle
            String sql = "DELETE FROM rental WHERE Regno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, regNo);
            int rowsAffected = preparedStatement.executeUpdate();
            connection.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to set the visibility of the frame
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
