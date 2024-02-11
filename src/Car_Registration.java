import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Car_Registration {

    public JFrame frame;
    private JTextField carregno;
    private JTextField carclass;
    private JTextField carcolor;
    private JTextField carmodel;
    private JTextField carprice;
    private JTable cartable;
	private JComboBox carbrand;
	private JComboBox carftype;
	private JSpinner carpassengers;
    private static String username;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Car_Registration window = new Car_Registration(username);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    // Constructor for the Car_Registration class
    public Car_Registration(String username) {
        this.username = username;
        initialize();
        tableload();// Load data into the table
        autoID();// Generate a unique car registration number
    }

    public void tableload() {
        try {
            Statement s = DB.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM cars");

            while (rs.next()) {
            	// Extract car information from the database
                String regno = rs.getString("Regno");
                String brand = rs.getString("Car_Brand");
                String model = rs.getString("Car_Model");
                String ftype = rs.getString("Car_Ftype");
                String color = rs.getString("Car_Color");
                String carclass = rs.getString("Car_Class");
                String passenger = rs.getString("Car_Passengers");
                int price = rs.getInt("Car_Price");
            
                // Create an array of car data to add to the JTable
                String[] tbData1 = { regno, brand, model, ftype, color, carclass, passenger, String.valueOf(price) };
                DefaultTableModel dt = (DefaultTableModel) cartable.getModel();
                dt.addRow(tbData1);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // Generate a unique car registration number
    public void autoID() {
        try {
            Statement s = DB.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(Regno) FROM cars");
            rs.next();
            String maxID = rs.getString(1);

            if (maxID == null) {
                carregno.setText("C0001");
            } else {
                int id = Integer.parseInt(maxID.substring(1)) + 1;
                String nextID = String.format("C%04d", id);
                carregno.setText(nextID);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
 
    // Clear input fields
    public void clearFields() {
        carbrand.setSelectedItem("");
        carmodel.setText("");
        carftype.setSelectedItem("");
        carcolor.setText("");
        carclass.setText("");
        carpassengers.setValue(0);
        carprice.setText("");
    }
 
    // Initialize the graphical user interface
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.getContentPane().setForeground(new Color(0, 128, 0));
        frame.setBounds(100, 100, 837, 475);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton_3 = new JButton("<");
        btnNewButton_3.addActionListener(new ActionListener() {
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
        btnNewButton_3.setBounds(0, 0, 45, 23);
        frame.getContentPane().add(btnNewButton_3);

        carregno = new JTextField();
        carregno.setEditable(false);
        carregno.setBounds(107, 107, 110, 19);
        frame.getContentPane().add(carregno);
        carregno.setColumns(10);

        carclass = new JTextField();
        carclass.setBounds(107, 258, 110, 19);
        frame.getContentPane().add(carclass);
        carclass.setColumns(10);

        carcolor = new JTextField();
        carcolor.setColumns(10);
        carcolor.setBounds(107, 229, 110, 19);
        frame.getContentPane().add(carcolor);

        carmodel = new JTextField();
        carmodel.setColumns(10);
        carmodel.setBounds(107, 170, 110, 19);
        frame.getContentPane().add(carmodel);

        carbrand = new JComboBox();
        carbrand.setModel(new DefaultComboBoxModel(new String[] { "", "Mercedes-Benz", "Toyota", "Nissan", "Suzuki", "Mitsubishi", "BMW", "Honda", "Ford",
        		"Chevrolet", "Volkswagen", "Audi", "Hyundai", "Kia", "Subaru", "Peugeot", "Tesla" }));
        carbrand.setBounds(107, 136, 110, 21);
        frame.getContentPane().add(carbrand);

        JLabel lblNewLabel = new JLabel("Car Reg No :");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 107, 87, 17);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Brand :");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 137, 87, 17);
        frame.getContentPane().add(lblNewLabel_1);

        carftype = new JComboBox();
        carftype.setModel(new DefaultComboBoxModel(new String[] { "", "Petrol", "Diesel", "Electric" }));
        carftype.setBounds(107, 198, 110, 21);
        frame.getContentPane().add(carftype);

        JLabel lblNewLabel_2 = new JLabel("Model :");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(10, 167, 87, 17);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Fuel Type :");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(10, 198, 87, 17);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Color :");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_4.setBounds(10, 230, 87, 17);
        frame.getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Car Class :");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_5.setBounds(10, 259, 87, 17);
        frame.getContentPane().add(lblNewLabel_5);

        carpassengers = new JSpinner();
        carpassengers.setBounds(107, 287, 110, 20);
        frame.getContentPane().add(carpassengers);

        JLabel lblNewLabel_6 = new JLabel("Passengers :");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(10, 287, 87, 17);
        frame.getContentPane().add(lblNewLabel_6);

        JButton btnNewButton = new JButton("Add Car");
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Dialog", Font.BOLD, 11));
        btnNewButton.setBackground(new Color(0, 128, 0));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regno, ccbrand, cmodel, ftype, ccolor, cclass, passenger;
                int price;

                regno = carregno.getText();
                ccbrand = (String) carbrand.getSelectedItem();
                cmodel = carmodel.getText();
                ftype = (String) carftype.getSelectedItem();
                ccolor = carcolor.getText();
                cclass = carclass.getText();
                passenger = carpassengers.getValue().toString();
                price = Integer.parseInt(carprice.getText());

                try {
                    Statement s = DB.mycon().createStatement();
                    String sql2 = "INSERT INTO cars(Regno, Car_Brand, Car_Model, Car_Ftype, Car_Color, Car_Class, Car_Passengers, Car_Price) VALUES ('"
                            + regno + "', '" + ccbrand + "', '" + cmodel + "', '" + ftype + "',  '" + ccolor + "',  '" + cclass + "', '"
                            + passenger + "', " + price + ");";
                    int rowsAffected = s.executeUpdate(sql2);

                    if (rowsAffected > 0) {
                        DefaultTableModel model1 = (DefaultTableModel) cartable.getModel();
                        model1.setRowCount(0);
                        tableload();

                        JOptionPane.showMessageDialog(null, "Car Details Added Successfully");
                        clearFields();
                        autoID();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed");
                    }
                } catch (Exception e2) {
                    System.out.print(e2);
                }
            }
        });
        btnNewButton.setBounds(10, 348, 97, 27);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Edit Car");
        btnNewButton_1.setForeground(new Color(0, 0, 0));
        btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 11));
        btnNewButton_1.setBackground(new Color(0, 128, 0));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regno, ccbrand, cmodel, ftype, ccolor, cclass, passenger;
                int price;

                regno = carregno.getText();
                ccbrand = (String) carbrand.getSelectedItem();
                cmodel = carmodel.getText();
                ftype = (String) carftype.getSelectedItem();
                ccolor = carcolor.getText();
                cclass = carclass.getText();
                passenger = carpassengers.getValue().toString();
                price = Integer.parseInt(carprice.getText());

                try {
                    Statement s = DB.mycon().createStatement();
                    String sqledit2 = "UPDATE cars SET Regno = '" + regno + "', Car_Brand = '" + ccbrand + "', Car_Model = '"
                            + cmodel + "', Car_Ftype = '" + ftype + "', Car_Color = '" + ccolor + "', Car_Class = '" + cclass
                            + "', Car_Passengers = '" + passenger + "', Car_Price = " + price + " WHERE Regno = '" + regno + "';";
                    int rowsAffected = s.executeUpdate(sqledit2);

                    if (rowsAffected > 0) {
                        DefaultTableModel model1 = (DefaultTableModel) cartable.getModel();
                        model1.setRowCount(0);
                        tableload();

                        JOptionPane.showMessageDialog(null, "Car Details Edited Successfully");
                        clearFields();
                        autoID();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed");
                    }
                } catch (Exception e1) {
                    System.out.print(e1);
                }
            }
        });
        btnNewButton_1.setBounds(120, 348, 97, 27);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Delete Car");
        btnNewButton_2.setForeground(new Color(0, 0, 0));
        btnNewButton_2.setBackground(new Color(0, 128, 0));
        btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 11));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DB.mycon();
                    String sql4 = "DELETE FROM cars WHERE Regno = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql4);
                    preparedStatement.setString(1, carregno.getText());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        DefaultTableModel model = (DefaultTableModel) cartable.getModel();
                        model.setRowCount(0);
                        tableload();

                        JOptionPane.showMessageDialog(null, "Car deleted successfully");
                        clearFields();
                        autoID();
                    } else {
                        JOptionPane.showMessageDialog(null, "Car not found or deletion failed");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton_2.setBounds(10, 380, 97, 27);
        frame.getContentPane().add(btnNewButton_2);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        scrollPane.setBounds(227, 103, 588, 295);
        frame.getContentPane().add(scrollPane);

        cartable = new JTable();
        cartable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "Reg No", "Brand", "Model", "Fuel Type", "Color", "Class", "Passengers", "Price/Day" }
        ) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        scrollPane.setViewportView(cartable);

        JLabel lblNewLabel_7 = new JLabel("Car Registration");
        lblNewLabel_7.setOpaque(true);
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7.setForeground(Color.YELLOW);
        lblNewLabel_7.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
        lblNewLabel_7.setBackground(new Color(0, 128, 0));
        lblNewLabel_7.setBounds(0, 0, 823, 97);
        frame.getContentPane().add(lblNewLabel_7);

        JLabel lblNewLabel_7_1 = new JLabel("");
        lblNewLabel_7_1.setOpaque(true);
        lblNewLabel_7_1.setForeground(Color.YELLOW);
        lblNewLabel_7_1.setBackground(new Color(0, 128, 0));
        lblNewLabel_7_1.setBounds(0, 417, 823, 21);
        frame.getContentPane().add(lblNewLabel_7_1);

        JLabel lblNewLabel_6_1 = new JLabel("Price/Day :");
        lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6_1.setBounds(10, 318, 87, 17);
        frame.getContentPane().add(lblNewLabel_6_1);

        carprice = new JTextField();
        carprice.setColumns(10);
        carprice.setBounds(107, 318, 110, 19);
        frame.getContentPane().add(carprice);
        
        JButton btnNewButton_2_1 = new JButton("Clear");
        btnNewButton_2_1.setForeground(new Color(0, 0, 0));
        btnNewButton_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearFields();
        		autoID();
        	}
        });
        btnNewButton_2_1.setFont(new Font("Dialog", Font.BOLD, 11));
        btnNewButton_2_1.setBackground(new Color(0, 128, 0));
        btnNewButton_2_1.setBounds(120, 380, 97, 27);
        frame.getContentPane().add(btnNewButton_2_1);

        // Add a listener to the JTable to capture user clicks and populate input fields
        cartable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel d1 = (DefaultTableModel) cartable.getModel();
                int selectIndex = cartable.getSelectedRow();

                if (selectIndex != -1) {
                    carregno.setText(d1.getValueAt(selectIndex, 0).toString());
                    carbrand.setSelectedItem(d1.getValueAt(selectIndex, 1).toString());
                    carmodel.setText(d1.getValueAt(selectIndex, 2).toString());
                    carftype.setSelectedItem(d1.getValueAt(selectIndex, 3).toString());
                    carcolor.setText(d1.getValueAt(selectIndex, 4).toString());
                    carclass.setText(d1.getValueAt(selectIndex, 5).toString());
                    carpassengers.setValue(Integer.parseInt(d1.getValueAt(selectIndex, 6).toString()));
                    carprice.setText(d1.getValueAt(selectIndex, 7).toString());
                }
            }
        });

    }
}
