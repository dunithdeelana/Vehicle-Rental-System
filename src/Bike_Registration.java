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
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class Bike_Registration {

    public JFrame frame;
    private JTextField bikeregno;
    private JTextField bikefeatures;
    private JTextField bikecolor;
    private JTextField bikemodel;
    private JTable biketable;
    private JTextField bikeprice;
    private JTextField biketype;
	private JComboBox bikebrand;
	private JComboBox bikeftype;
    private static String username;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Bike_Registration window = new Bike_Registration(username);
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
    public Bike_Registration(String username) {
        this.username = username;
        initialize();
        tableload();
        autoid();
    }

    
    public void autoid() {
        try {
            Statement s = DB.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(Regno) FROM bikes");
            rs.next();
            String maxID = rs.getString(1);

            if (maxID == null) {
            	bikeregno.setText("B0001");
            } else {
                int id = Integer.parseInt(maxID.substring(1)) + 1;
                String nextID = String.format("B%04d", id);
                bikeregno.setText(nextID);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void clearfields() {
        bikebrand.setSelectedItem("");
        bikemodel.setText("");
        bikeftype.setSelectedItem("");
        bikecolor.setText("");
        bikefeatures.setText("");
        biketype.setText("");
        bikeprice.setText("");
    }

    public void tableload() {
        try {
            Statement s = DB.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM bikes");

            while (rs.next()) {
                String bregno = rs.getString("Regno");
                String bbrand = rs.getString("Bike_Brand");
                String bmodel = rs.getString("Bike_Model");
                String bftype = rs.getString("Bike_FType");
                String bcolor = rs.getString("Bike_Color");
                String bfeatures = rs.getString("Bike_Features");
                String btype = rs.getString("Bike_Type");
                int bprice = rs.getInt("Bike_Price");

                String[] tbData4 = {bregno, bbrand, bmodel, bftype, bcolor, bfeatures, btype, String.valueOf(bprice)};
                DefaultTableModel dt = (DefaultTableModel) biketable.getModel();
                dt.addRow(tbData4);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
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

        bikeregno = new JTextField();
        bikeregno.setEditable(false);
        bikeregno.setBounds(116, 107, 110, 19);
        frame.getContentPane().add(bikeregno);
        bikeregno.setColumns(10);

        bikefeatures = new JTextField();
        bikefeatures.setBounds(116, 258, 110, 19);
        frame.getContentPane().add(bikefeatures);
        bikefeatures.setColumns(10);

        bikecolor = new JTextField();
        bikecolor.setColumns(10);
        bikecolor.setBounds(116, 229, 110, 19);
        frame.getContentPane().add(bikecolor);

        bikemodel = new JTextField();
        bikemodel.setColumns(10);
        bikemodel.setBounds(116, 170, 110, 19);
        frame.getContentPane().add(bikemodel);

        bikebrand = new JComboBox();
        bikebrand.setModel(new DefaultComboBoxModel(new String[] { "", "Benz", "Toyota", "Nissan", "Suzuki", "Mitsubishi", "TATA", "Honda", "Kawasaki", "Ford", "Volkswagen",
        		"Yamaha", "Ducati", "Harley-Davidson", "KTM", "Triumph" }));
        bikebrand.setBounds(116, 136, 110, 21);
        frame.getContentPane().add(bikebrand);

        JLabel lblNewLabel = new JLabel("Bike Reg No :");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 107, 96, 17);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Brand :");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 136, 96, 17);
        frame.getContentPane().add(lblNewLabel_1);

        bikeftype = new JComboBox();
        bikeftype.setModel(new DefaultComboBoxModel(new String[] {"", "Petrol", "Electric"}));
        bikeftype.setBounds(116, 198, 110, 21);
        frame.getContentPane().add(bikeftype);

        JLabel lblNewLabel_2 = new JLabel("Model :");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(10, 170, 96, 17);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Fuel Type :");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(10, 198, 96, 17);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Color :");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_4.setBounds(10, 229, 96, 17);
        frame.getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Features :");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_5.setBounds(10, 258, 96, 17);
        frame.getContentPane().add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Bike Type :");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(10, 287, 96, 17);
        frame.getContentPane().add(lblNewLabel_6);

        JButton addBikeButton = new JButton("Add Bike");
        addBikeButton.setForeground(new Color(0, 0, 0));
        addBikeButton.setFont(new Font("Dialog", Font.BOLD, 11));
        addBikeButton.setBackground(new Color(0, 128, 0));
        addBikeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bbRegNo, bbBrand, bbModel, bbFType, bbColor, bbFeatures, bbType;
                int bbPrice;

                bbRegNo = bikeregno.getText();
                bbBrand = (String) bikebrand.getSelectedItem();
                bbModel = bikemodel.getText();
                bbFType = (String) bikeftype.getSelectedItem();
                bbColor = bikecolor.getText();
                bbFeatures = bikefeatures.getText();
                bbType = biketype.getText();

                try {
                    bbPrice = Integer.parseInt(bikeprice.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Price should be a number.");
                    return;
                }

                try {
                    Statement s = DB.mycon().createStatement();
                    String sql2 = "INSERT INTO bikes(Regno, Bike_Brand, Bike_Model, Bike_FType, Bike_Color, Bike_Features, Bike_Type, Bike_Price) values ('" + bbRegNo + "', '" + bbBrand + "', '" + bbModel + "', '" + bbFType + "',  '" + bbColor + "',  '" + bbFeatures + "', '" + bbType + "', " + bbPrice + ");";
                    int rowsAffected = s.executeUpdate(sql2);

                    if (rowsAffected > 0) {
                        DefaultTableModel model1 = (DefaultTableModel) biketable.getModel();
                        model1.setRowCount(0);
                        tableload();
                        JOptionPane.showMessageDialog(null, "Bike Details Added Successfully");
                        autoid();
                        clearfields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed");
                    }
                } catch (Exception e2) {
                    System.out.print(e2);
                }
            }
        });
        addBikeButton.setBounds(22, 347, 97, 27);
        frame.getContentPane().add(addBikeButton);

        JButton editBikeButton = new JButton("Edit Bike");
        editBikeButton.setForeground(new Color(0, 0, 0));
        editBikeButton.setFont(new Font("Dialog", Font.BOLD, 11));
        editBikeButton.setBackground(new Color(0, 128, 0));
        editBikeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bbRegNo, bbBrand, bbModel, bbFType, bbColor, bbFeatures, bbType;
                int bbPrice;

                bbRegNo = bikeregno.getText();
                bbBrand = (String) bikebrand.getSelectedItem();
                bbModel = bikemodel.getText();
                bbFType = (String) bikeftype.getSelectedItem();
                bbColor = bikecolor.getText();
                bbFeatures = bikefeatures.getText();
                bbType = biketype.getText();

                try {
                    bbPrice = Integer.parseInt(bikeprice.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Price should be a number.");
                    return;
                }

                try {
                    Statement s = DB.mycon().createStatement();
                    String sqledit2 = "UPDATE bikes SET Regno = '" + bbRegNo + "', Bike_Brand = '" + bbBrand + "', Bike_Model = '" + bbModel + "', Bike_FType = '" + bbFType + "', Bike_Color = '" + bbColor + "', Bike_Features = '" + bbFeatures + "', Bike_Type = '" + bbType + "', Bike_Price = " + bbPrice + " WHERE Regno = '" + bbRegNo + "'";

                    int rowsAffected = s.executeUpdate(sqledit2);

                    if (rowsAffected > 0) {
                        DefaultTableModel model1 = (DefaultTableModel) biketable.getModel();
                        model1.setRowCount(0);
                        tableload();
                        JOptionPane.showMessageDialog(null, "Bike Details Edited Successfully");
                        autoid();
                        clearfields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed");
                    }
                } catch (Exception e1) {
                    System.out.print(e1);
                }
            }
        });
        editBikeButton.setBounds(129, 347, 97, 27);
        frame.getContentPane().add(editBikeButton);

        JButton deleteBikeButton = new JButton("Delete Bike");
        deleteBikeButton.setForeground(new Color(0, 0, 0));
        deleteBikeButton.setBackground(new Color(0, 128, 0));
        deleteBikeButton.setFont(new Font("Dialog", Font.BOLD, 11));
        deleteBikeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DB.mycon();
                    String sql4 = "DELETE FROM bikes WHERE Regno=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql4);
                    preparedStatement.setString(1, bikeregno.getText());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        DefaultTableModel model = (DefaultTableModel) biketable.getModel();
                        model.setRowCount(0);
                        tableload();
                        JOptionPane.showMessageDialog(null, "Bike Details deleted successfully");
                        autoid();
                        clearfields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Bike Details not found Or Deletion Failed");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        deleteBikeButton.setBounds(22, 382, 97, 27);
        frame.getContentPane().add(deleteBikeButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        scrollPane.setBounds(236, 103, 579, 310);
        frame.getContentPane().add(scrollPane);

        biketable = new JTable();
        biketable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Reg No", "Brand", "Model", "Fuel Type", "Color", "Features", "Type", "Price/Day"
                }
        ) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        scrollPane.setViewportView(biketable);

        JLabel lblNewLabel_6_1 = new JLabel("Price/Day :");
        lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6_1.setBounds(10, 319, 96, 17);
        frame.getContentPane().add(lblNewLabel_6_1);

        bikeprice = new JTextField();
        bikeprice.setColumns(10);
        bikeprice.setBounds(116, 318, 110, 19);
        frame.getContentPane().add(bikeprice);

        biketype = new JTextField();
        biketype.setColumns(10);
        biketype.setBounds(116, 287, 110, 19);
        frame.getContentPane().add(biketype);

        JLabel lblNewLabel_7 = new JLabel("Bike Registration");
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
        
        JButton btnNewButton_2_1 = new JButton("Clear");
        btnNewButton_2_1.setForeground(new Color(0, 0, 0));
        btnNewButton_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearfields();
        		autoid();
        		
        	}
        });
        btnNewButton_2_1.setFont(new Font("Dialog", Font.BOLD, 11));
        btnNewButton_2_1.setBackground(new Color(0, 128, 0));
        btnNewButton_2_1.setBounds(129, 382, 97, 27);
        frame.getContentPane().add(btnNewButton_2_1);
        biketable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel d2 = (DefaultTableModel) biketable.getModel();
                int selectIndex = biketable.getSelectedRow();

                if (selectIndex != -1) {
                    bikeregno.setText(d2.getValueAt(selectIndex, 0).toString());
                    bikebrand.setSelectedItem(d2.getValueAt(selectIndex, 1).toString());
                    bikemodel.setText(d2.getValueAt(selectIndex, 2).toString());
                    bikeftype.setSelectedItem(d2.getValueAt(selectIndex, 3).toString());
                    bikecolor.setText(d2.getValueAt(selectIndex, 4).toString());
                    bikefeatures.setText(d2.getValueAt(selectIndex, 5).toString());
                    biketype.setText(d2.getValueAt(selectIndex, 6).toString());
                    bikeprice.setText(d2.getValueAt(selectIndex, 7).toString());
                }
            }
        });
    }
}
