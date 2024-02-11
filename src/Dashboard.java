import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import java.sql.*;
import javax.swing.ImageIcon;

public class Dashboard {

    public JFrame frame;
    private JLabel totalcars;
    private JLabel totalbikes;
    private JLabel totalvans;
	private JLabel availablecars;
	private JLabel availablevans;
	private JLabel availablebikes;
    private static String username;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dashboard window = new Dashboard(username);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Dashboard(String username) {
        this.username = username;
        initialize();
    }
 
    // Helper method to retrieve count from a database table
    private int getCountFromTable(Connection connection, String tableName) throws SQLException {
        int count = 0;

        String query = "SELECT COUNT(Regno) FROM " + tableName;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }

        resultSet.close();
        statement.close();

        return count;
    }
    
    // Overloaded helper method to retrieve count based on vehicle type
    private int getCountFromTable(Connection connection, String tableName, String vehicleType) throws SQLException {
        int count = 0;

        String query = "SELECT COUNT(Regno) FROM " + tableName + " WHERE VehicleType = '" + vehicleType + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }

        resultSet.close();
        statement.close();

        return count;
    }

    private void initialize() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
         
            // Retrieve counts for various vehicle types and rentals
            int carCount = getCountFromTable(connection, "cars");
            int vanCount = getCountFromTable(connection, "vans");
            int bikeCount = getCountFromTable(connection, "bikes");
            
            int carRentCount = getCountFromTable(connection, "rental", "Car");
            int vanRentCount = getCountFromTable(connection, "rental", "Van");
            int bikeRentCount = getCountFromTable(connection, "rental", "Bike");
       
            // Calculate available vehicle counts
            int availableCarCount = carCount - carRentCount;
            int availableVanCount = vanCount - vanRentCount;
            int availableBikeCount = bikeCount - bikeRentCount;

            frame = new JFrame();
            frame.getContentPane().setForeground(new Color(0, 0, 0));
            frame.getContentPane().setBackground(new Color(255, 255, 255));
            frame.setBounds(100, 100, 823, 488);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(null);
         
            // Create and configure Swing components for displaying vehicle counts
            JLabel lblNewLabel_1 = new JLabel("Cars");
            lblNewLabel_1.setBackground(new Color(224, 255, 255));
            lblNewLabel_1.setOpaque(true);
            lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblNewLabel_1.setBounds(248, 77, 155, 49);
            frame.getContentPane().add(lblNewLabel_1);

            JLabel lblNewLabel_1_1 = new JLabel("Available Vans");
            lblNewLabel_1_1.setOpaque(true);
            lblNewLabel_1_1.setBackground(new Color(224, 255, 255));
            lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblNewLabel_1_1.setBounds(431, 231, 155, 49);
            frame.getContentPane().add(lblNewLabel_1_1);

            totalcars = new JLabel(String.valueOf(carCount));
            totalcars.setOpaque(true);
            totalcars.setHorizontalAlignment(SwingConstants.CENTER);
            totalcars.setForeground(new Color(30, 144, 255));
            totalcars.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
            totalcars.setBackground(new Color(154, 205, 50));
            totalcars.setBounds(248, 130, 155, 68);
            frame.getContentPane().add(totalcars);

            totalvans = new JLabel(String.valueOf(vanCount));
            totalvans.setOpaque(true);
            totalvans.setHorizontalAlignment(SwingConstants.CENTER);
            totalvans.setForeground(new Color(30, 144, 255));
            totalvans.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
            totalvans.setBackground(new Color(154, 205, 50));
            totalvans.setBounds(431, 130, 156, 68);
            frame.getContentPane().add(totalvans);

            totalbikes = new JLabel(String.valueOf(bikeCount));
            totalbikes.setOpaque(true);
            totalbikes.setHorizontalAlignment(SwingConstants.CENTER);
            totalbikes.setForeground(new Color(30, 144, 255));
            totalbikes.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
            totalbikes.setBackground(new Color(154, 205, 50));
            totalbikes.setBounds(614, 130, 155, 68);
            frame.getContentPane().add(totalbikes);
            
            availablecars = new JLabel();
            availablecars.setOpaque(true);
    		availablecars.setHorizontalAlignment(SwingConstants.CENTER);
    		availablecars.setBackground(new Color(154, 205, 50));
    		availablecars.setForeground(new Color(178, 34, 34));
    		availablecars.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
    		availablecars.setBounds(248, 284, 155, 68);
    		frame.getContentPane().add(availablecars);
    		
    		availablebikes = new JLabel();
    		availablebikes.setOpaque(true);
    		availablebikes.setHorizontalAlignment(SwingConstants.CENTER);
    		availablebikes.setForeground(new Color(178, 34, 34));
    		availablebikes.setBackground(new Color(154, 205, 50));
    		availablebikes.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
    		availablebikes.setBounds(614, 284, 155, 68);
    		frame.getContentPane().add(availablebikes);

            availablevans = new JLabel();
            availablevans.setOpaque(true);
            availablevans.setBackground(new Color(154, 205, 50));
    		availablevans.setHorizontalAlignment(SwingConstants.CENTER);
    		availablevans.setForeground(new Color(178, 34, 34));
    		availablevans.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
    		availablevans.setBounds(432, 284, 155, 68);
    		frame.getContentPane().add(availablevans);
    		
    		availablecars.setText(String.valueOf(availableCarCount));
            availablevans.setText(String.valueOf(availableVanCount));
            availablebikes.setText(String.valueOf(availableBikeCount));
         
            // Create and configure buttons for various actions
    		JButton btnNewButton = new JButton("Vehicle Register");
    		btnNewButton.setForeground(new Color(0, 0, 0));
    		btnNewButton.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    		btnNewButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				RegTopUp rto = new RegTopUp(username);
    				frame.setVisible(false);
    				rto.frame.setVisible(true);
    			}
    		});
    		btnNewButton.setBounds(0, 189, 202, 51);
    		frame.getContentPane().add(btnNewButton);
    		
    		JButton btnNewButton_1 = new JButton("Customer Register");
    		btnNewButton_1.setForeground(new Color(0, 0, 0));
    		btnNewButton_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    		btnNewButton_1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				Customer_Registration cr = new Customer_Registration(username);
    				frame.setVisible(false);
    				cr.frame.setVisible(true);
    			}
    		});
    		btnNewButton_1.setBounds(0, 136, 202, 51);
    		frame.getContentPane().add(btnNewButton_1);
    		
    		JButton btnNewButton_1_1 = new JButton("Vehicle Rent");
    		btnNewButton_1_1.setForeground(new Color(0, 0, 0));
    		btnNewButton_1_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    		btnNewButton_1_1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				RentTopUp rtp=new RentTopUp(username);
    				frame.setVisible(false);
    				rtp.frame.setVisible(true);
    			}
    		});
    		btnNewButton_1_1.setBounds(0, 242, 202, 51);
    		frame.getContentPane().add(btnNewButton_1_1);
    		
    		JButton btnNewButton_1_1_1 = new JButton("Vehicle Return");
    		btnNewButton_1_1_1.setForeground(new Color(0, 0, 0));
    		btnNewButton_1_1_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 13));
    		btnNewButton_1_1_1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				Return rt= new Return(username);
    				frame.setVisible(false);
    				rt.frame.setVisible(true);
    			}
    		});
    		btnNewButton_1_1_1.setBounds(0, 295, 202, 51);
    		frame.getContentPane().add(btnNewButton_1_1_1);
    		
    		JButton btnNewButton_2 = new JButton("Log Out");
    		btnNewButton_2.setBackground(new Color(0, 128, 0));
    		btnNewButton_2.setFont(new Font("Unispace", Font.BOLD, 13));
    		btnNewButton_2.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				try {
    					Login lg=new Login();
    					frame.setVisible(false);
    					lg.frame.setVisible(true);
    					
    				}catch(Exception e1) {System.out.print(e1);}
    			}
    		});
    		btnNewButton_2.setBounds(674, 407, 112, 34);
    		frame.getContentPane().add(btnNewButton_2);
    		
    		
    		
    		
    		JLabel lblNewLabel = new JLabel(username);
            lblNewLabel.setForeground(new Color(105, 105, 105));
            lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblNewLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    Profile pr = new Profile(username);
                    frame.setVisible(false);
                    pr.frame.setVisible(true);
                }
            });
    		lblNewLabel.setBounds(674, 32, 112, 20);
    		frame.getContentPane().add(lblNewLabel);
    		
    		JLabel lblNewLabel_1_2 = new JLabel("Vans");
    		lblNewLabel_1_2.setBackground(new Color(224, 255, 255));
    		lblNewLabel_1_2.setOpaque(true);
    		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
    		lblNewLabel_1_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
    		lblNewLabel_1_2.setBounds(431, 77, 155, 49);
    		frame.getContentPane().add(lblNewLabel_1_2);
    		
    		JLabel lblNewLabel_1_3 = new JLabel("Bikes");
    		lblNewLabel_1_3.setBackground(new Color(224, 255, 255));
    		lblNewLabel_1_3.setOpaque(true);
    		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
    		lblNewLabel_1_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
    		lblNewLabel_1_3.setBounds(614, 77, 155, 49);
    		frame.getContentPane().add(lblNewLabel_1_3);
    		
    		JLabel lblNewLabel_1_1_1 = new JLabel("Available Bikes");
    		lblNewLabel_1_1_1.setOpaque(true);
    		lblNewLabel_1_1_1.setBackground(new Color(224, 255, 255));
    		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
    		lblNewLabel_1_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
    		lblNewLabel_1_1_1.setBounds(614, 231, 155, 49);
    		frame.getContentPane().add(lblNewLabel_1_1_1);
    		
    		JLabel lblNewLabel_1_1_2 = new JLabel("Available Cars");
    		lblNewLabel_1_1_2.setOpaque(true);
    		lblNewLabel_1_1_2.setBackground(new Color(224, 255, 255));
    		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
    		lblNewLabel_1_1_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
    		lblNewLabel_1_1_2.setBounds(248, 231, 155, 49);
    		frame.getContentPane().add(lblNewLabel_1_1_2);
    		
    		JLabel lblNewLabel_7 = new JLabel("");
    		lblNewLabel_7.setOpaque(true);
    		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
    		lblNewLabel_7.setForeground(Color.YELLOW);
    		lblNewLabel_7.setFont(new Font("Unispace", Font.BOLD, 30));
    		lblNewLabel_7.setBackground(new Color(0, 128, 0));
    		lblNewLabel_7.setBounds(0, 0, 202, 134);
    		frame.getContentPane().add(lblNewLabel_7);
    		
    		JLabel lblNewLabel_2 = new JLabel("");
    		lblNewLabel_2.setIcon(new ImageIcon("D:\\Vehicle Rental System\\Vehicle_Rental_System\\images\\profile.jpg"));
    		lblNewLabel_2.setBounds(614, 20, 45, 45);
    		frame.getContentPane().add(lblNewLabel_2);
    		
    		JLabel lblNewLabel_7_1 = new JLabel("");
    		lblNewLabel_7_1.setOpaque(true);
    		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
    		lblNewLabel_7_1.setForeground(Color.YELLOW);
    		lblNewLabel_7_1.setFont(new Font("Dialog", Font.BOLD, 30));
    		lblNewLabel_7_1.setBackground(new Color(0, 128, 0));
    		lblNewLabel_7_1.setBounds(0, 348, 202, 101);
    		frame.getContentPane().add(lblNewLabel_7_1);
    		
    		

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
