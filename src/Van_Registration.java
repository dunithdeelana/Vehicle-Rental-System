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
import javax.swing.SwingConstants;

public class Van_Registration {

    public JFrame frame;
    private JTextField vanregno;
    private JTextField vanfeatures;
    private JTextField vancolor;
    private JTextField vanmodel;
    private JTable vantable;
    private JTextField vanprice;
	private JComboBox vanbrand;
	private JComboBox vanftype;
	private JSpinner vanpassengers;
    private static String username;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Van_Registration window = new Van_Registration(username);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Van_Registration(String username) {
        this.username = username;
        initialize();
        tableload();
        autoID();
    }

    public void tableload() {
        try {
            Statement s = DB.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM vans");

            while (rs.next()) {
                String vregno = rs.getString("Regno");
                String vbrand = rs.getString("Van_Brand");
                String vmodel = rs.getString("Van_Model");
                String vftype = rs.getString("Van_Ftype");
                String vcolor = rs.getString("Van_Color");
                String vanfeatures = rs.getString("Van_Features");
                String vpassengers = rs.getString("Van_Passengers");
                String vanprice = rs.getString("Van_Price");

                String[] tbData3 = { vregno, vbrand, vmodel, vftype, vcolor, vanfeatures, vpassengers, vanprice };
                DefaultTableModel dt = (DefaultTableModel) vantable.getModel();
                dt.addRow(tbData3);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void autoID() {

    	try {
            Statement s = DB.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(Regno) FROM vans");
            
            rs.next();
            String maxID = rs.getString(1);

            if (maxID == null) {
                vanregno.setText("V0001");
            } else {
                int id = Integer.parseInt(maxID.substring(1));
                id++;
                String nextID = String.format("V%04d", id);
                vanregno.setText(nextID);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    private void clearFields() {
        vanbrand.setSelectedItem("");
        vanmodel.setText("");
        vanftype.setSelectedItem("");
        vancolor.setText("");
        vanfeatures.setText("");
        vanpassengers.setValue(0);
        vanprice.setText("");
    }
    

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

        vanregno = new JTextField();
        vanregno.setEditable(false);
        vanregno.setBounds(116, 107, 110, 19);
        frame.getContentPane().add(vanregno);
        vanregno.setColumns(10);

        vanfeatures = new JTextField();
        vanfeatures.setBounds(116, 258, 110, 19);
        frame.getContentPane().add(vanfeatures);
        vanfeatures.setColumns(10);

        vancolor = new JTextField();
        vancolor.setColumns(10);
        vancolor.setBounds(116, 229, 110, 19);
        frame.getContentPane().add(vancolor);

        vanmodel = new JTextField();
        vanmodel.setColumns(10);
        vanmodel.setBounds(116, 170, 110, 19);
        frame.getContentPane().add(vanmodel);

        vanbrand = new JComboBox();
        vanbrand.setModel(new DefaultComboBoxModel(new String[] { "", "Mercedes-Benz", "Toyota", "Nissan", "Suzuki", "Mitsubishi", "Ram", "GMC", "Tesla", 
        		"TATA", "Ford", "Volkswagen", "Renault", "Chevrolet", "Peugeot", "Dodge", "Fiat" }));
        vanbrand.setBounds(116, 136, 110, 21);
        frame.getContentPane().add(vanbrand);

        JLabel lblNewLabel = new JLabel("Van Reg No :");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 107, 96, 17);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Brand :");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 136, 96, 17);
        frame.getContentPane().add(lblNewLabel_1);

        vanftype = new JComboBox();
        vanftype.setModel(new DefaultComboBoxModel(new String[] { "", "Petrol", "Diesel", "Electric" }));
        vanftype.setBounds(116, 198, 110, 21);
        frame.getContentPane().add(vanftype);

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

        vanpassengers = new JSpinner();
        vanpassengers.setBounds(116, 287, 110, 20);
        frame.getContentPane().add(vanpassengers);

        JLabel lblNewLabel_6 = new JLabel("Passengers :");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(10, 287, 96, 17);
        frame.getContentPane().add(lblNewLabel_6);

        JButton btnNewButton = new JButton("Add Van");
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Dialog", Font.BOLD, 11));
        btnNewButton.setBackground(new Color(0, 128, 0));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String vvregno, vvbrand, vvmodel, vvftype, vvcolor, vvfeatures, vvpassenger;
                int vvprice;

                vvregno = vanregno.getText();
                vvbrand = (String) vanbrand.getSelectedItem();
                vvmodel = vanmodel.getText();
                vvftype = (String) vanftype.getSelectedItem();
                vvcolor = vancolor.getText();
                vvfeatures = vanfeatures.getText();
                vvpassenger = vanpassengers.getValue().toString();
                vvprice = Integer.parseInt(vanprice.getText());

                try {

                    Statement s = DB.mycon().createStatement();
                    String sql2 = "insert into vans(Regno, Van_Brand, Van_Model, Van_Ftype, Van_Color, Van_Features, Van_Passengers, Van_Price) values ('" + vvregno + "', '" + vvbrand + "', '" + vvmodel + "', '" + vvftype + "',  '" + vvcolor + "',  '" + vvfeatures + "', '" + vvpassenger + "', " + vvprice + ");";
                    int rowsAffected = s.executeUpdate(sql2);

                    if (rowsAffected > 0) {
                        DefaultTableModel model1 = (DefaultTableModel) vantable.getModel();
                        model1.setRowCount(0);
                        tableload();

                        JOptionPane.showMessageDialog(null, "Van Details Add Successfully");
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
        btnNewButton.setBounds(20, 347, 97, 27);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Edit Van");
        btnNewButton_1.setForeground(new Color(0, 0, 0));
        btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 11));
        btnNewButton_1.setBackground(new Color(0, 128, 0));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String vvregno, vvbrand, vvmodel, vvftype, vvcolor, vvfeatures, vvpassenger;
                int vvprice;

                vvregno = vanregno.getText();
                vvbrand = (String) vanbrand.getSelectedItem();
                vvmodel = vanmodel.getText();
                vvftype = (String) vanftype.getSelectedItem();
                vvcolor = vancolor.getText();
                vvfeatures = vanfeatures.getText();
                vvpassenger = vanpassengers.getValue().toString();
                vvprice = Integer.parseInt(vanprice.getText());

                try {

                    Statement s = DB.mycon().createStatement();
                    String sqledit2 = "UPDATE vans SET Regno = '" + vvregno + "', Van_Brand = '" + vvbrand + "', Van_Model = '" + vvmodel + "', Van_Ftype = '" + vvftype + "', Van_Color = '" + vvcolor + "', Van_Features = '" + vvfeatures + "', Van_Passengers = '" + vvpassenger + "', Van_Price = " + vvprice + " WHERE Regno = '" + vvregno + "'";

                    int rowsAffected = s.executeUpdate(sqledit2);

                    if (rowsAffected > 0) {
                        DefaultTableModel model1 = (DefaultTableModel) vantable.getModel();
                        model1.setRowCount(0);
                        tableload();

                        JOptionPane.showMessageDialog(null, "Van Details Edit Successfully");
                        clearFields();

                    } else {

                        JOptionPane.showMessageDialog(null, "Failed");
                    }
                } catch (Exception e1) {
                    System.out.print(e1);
                }

            }
        });
        btnNewButton_1.setBounds(129, 347, 97, 27);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Delete Van");
        btnNewButton_2.setForeground(new Color(0, 0, 0));
        btnNewButton_2.setBackground(new Color(0, 128, 0));
        btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 11));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection connection = DB.mycon();
                    String sql4 = "DELETE FROM vans WHERE Regno=?";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql4);
                    preparedStatement.setString(1, vanregno.getText());

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        DefaultTableModel model = (DefaultTableModel) vantable.getModel();
                        model.setRowCount(0);
                        tableload();
                        JOptionPane.showMessageDialog(null, "Customer deleted successfully");
                        clearFields();
                        
                        autoID();

                    } else {
                        JOptionPane.showMessageDialog(null, "Customer not found or deletion failed");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton_2.setBounds(20, 380, 97, 27);
        frame.getContentPane().add(btnNewButton_2);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        scrollPane.setBounds(236, 103, 579, 310);
        frame.getContentPane().add(scrollPane);

        vantable = new JTable();
        vantable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "Reg No", "Brand", "Model", "Fuel Type", "Color", "Features", "Passengers", "Price" }

        ) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        scrollPane.setViewportView(vantable);

        JLabel lblNewLabel_6_1 = new JLabel("Price/Day :");
        lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_6_1.setBounds(10, 319, 96, 17);
        frame.getContentPane().add(lblNewLabel_6_1);

        vanprice = new JTextField();
        vanprice.setColumns(10);
        vanprice.setBounds(116, 318, 110, 19);
        frame.getContentPane().add(vanprice);

        JLabel lblNewLabel_7 = new JLabel("Van Registration");
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
        		clearFields();
        		autoID();
        	}
        });
        btnNewButton_2_1.setFont(new Font("Dialog", Font.BOLD, 11));
        btnNewButton_2_1.setBackground(new Color(0, 128, 0));
        btnNewButton_2_1.setBounds(129, 380, 97, 27);
        frame.getContentPane().add(btnNewButton_2_1);
        vantable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel d2 = (DefaultTableModel) vantable.getModel();
                int selectIndex = vantable.getSelectedRow();

                if (selectIndex != -1) {
                    vanregno.setText(d2.getValueAt(selectIndex, 0).toString());
                    vanbrand.setSelectedItem(d2.getValueAt(selectIndex, 1).toString());
                    vanmodel.setText(d2.getValueAt(selectIndex, 2).toString());
                    vanftype.setSelectedItem(d2.getValueAt(selectIndex, 3).toString());
                    vancolor.setText(d2.getValueAt(selectIndex, 4).toString());
                    vanfeatures.setText(d2.getValueAt(selectIndex, 5).toString());
                    vanpassengers.setValue(Integer.parseInt(d2.getValueAt(selectIndex, 6).toString()));
                    vanprice.setText(d2.getValueAt(selectIndex, 7).toString());
                }
            }
        });
    }
}
