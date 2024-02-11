import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JSeparator;

public class Admin {

	public JFrame frame;
	private JLabel totalcars;
	private JLabel availablecars;
	private JLabel totalbikes;
	private JLabel availablebikes;
	private JLabel totalvans;
	private JLabel availablevans;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
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

	
	public Admin() {
		initialize();
	}

	
	private void initialize() {
		
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
			
			int carCount = getCountFromTable(connection, "cars");
	        int vanCount = getCountFromTable(connection, "vans");
	        int bikeCount = getCountFromTable(connection, "bikes");
	        
	        int carRentCount = getCountFromTable(connection, "rental", "Car");
	        int vanRentCount = getCountFromTable(connection, "rental", "Van");
	        int bikeRentCount = getCountFromTable(connection, "rental", "Bike");

	        int availableCarCount = carCount - carRentCount;
	        int availableVanCount = vanCount - vanRentCount;
	        int availableBikeCount = bikeCount - bikeRentCount;
			
			frame = new JFrame();
			frame.setBounds(100, 100, 754, 449);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JButton btnNewButton = new JButton("Log Out");
			btnNewButton.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Login lg=new Login();
						frame.setVisible(false);
						lg.frame.setVisible(true);
						
					}catch(Exception e1) {System.out.print(e1);}
				}
			});
			btnNewButton.setBounds(639, 352, 89, 29);
			frame.getContentPane().add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Manage Cars");
			btnNewButton_1.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Car_Registration cr = new Car_Registration(null);
					frame.setVisible(false);
					cr.frame.setVisible(true);
				}
			});
			btnNewButton_1.setBounds(170, 143, 150, 23);
			frame.getContentPane().add(btnNewButton_1);
			
			JButton btnNewButton_4 = new JButton("Manage Customers");
			btnNewButton_4.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Customer_Registration cr = new Customer_Registration(null);
					frame.setVisible(false);
					cr.frame.setVisible(true);
				}
			});
			btnNewButton_4.setBounds(10, 143, 150, 23);
			frame.getContentPane().add(btnNewButton_4);
			
			JButton btnNewButton_5 = new JButton("Manage Employees");
			btnNewButton_5.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Employee emp=new Employee();
					frame.setVisible(false);
					emp.frame.setVisible(true);
				}
			});
			btnNewButton_5.setBounds(10, 178, 150, 23);
			frame.getContentPane().add(btnNewButton_5);
			
			JButton btnNewButton_6 = new JButton("Financial Summerry");
			btnNewButton_6.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Financial fin = new Financial();
					frame.setVisible(false);
					fin.frame.setVisible(true);
				}
			});
			btnNewButton_6.setBounds(578, 178, 150, 23);
			frame.getContentPane().add(btnNewButton_6);
			
			JButton btnNewButton_7 = new JButton("Manage Car Rents");
			btnNewButton_7.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CarRent cr=new CarRent(null);
					frame.setVisible(false);
					cr.frame.setVisible(true);
				}
			});
			btnNewButton_7.setBounds(418, 143, 150, 23);
			frame.getContentPane().add(btnNewButton_7);
			
			JButton btnNewButton_8 = new JButton("Manage Returns");
			btnNewButton_8.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Return rt= new Return(null);
					frame.setVisible(false);
					rt.frame.setVisible(true);
				}
			});
			btnNewButton_8.setBounds(578, 143, 150, 23);
			frame.getContentPane().add(btnNewButton_8);
			
			JLabel lblNewLabel_1 = new JLabel("Total Cars");
			lblNewLabel_1.setBounds(10, 58, 89, 14);
			frame.getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewLabel_1_1 = new JLabel("Available Cars");
			lblNewLabel_1_1.setBounds(109, 58, 89, 14);
			frame.getContentPane().add(lblNewLabel_1_1);
			
			JLabel lblNewLabel_1_2 = new JLabel("Total Bikes");
			lblNewLabel_1_2.setBounds(282, 58, 89, 14);
			frame.getContentPane().add(lblNewLabel_1_2);
			
			JLabel lblNewLabel_1_3 = new JLabel("Available Bikes");
			lblNewLabel_1_3.setBounds(381, 58, 89, 14);
			frame.getContentPane().add(lblNewLabel_1_3);
			
			JLabel lblNewLabel_1_4 = new JLabel("Total Vans");
			lblNewLabel_1_4.setBounds(540, 58, 89, 14);
			frame.getContentPane().add(lblNewLabel_1_4);
			
			JLabel lblNewLabel_1_5 = new JLabel("Available Vans");
			lblNewLabel_1_5.setBounds(639, 58, 89, 14);
			frame.getContentPane().add(lblNewLabel_1_5);
			
			totalcars = new JLabel(String.valueOf(carCount));
			totalcars.setHorizontalAlignment(SwingConstants.CENTER);
			totalcars.setBounds(10, 83, 51, 51);
			frame.getContentPane().add(totalcars);
			
			availablecars = new JLabel(String.valueOf(availableCarCount));
			availablecars.setHorizontalAlignment(SwingConstants.CENTER);
			availablecars.setBounds(109, 83, 51, 51);
			frame.getContentPane().add(availablecars);
			
			totalbikes = new JLabel(String.valueOf(bikeCount));
			totalbikes.setHorizontalAlignment(SwingConstants.CENTER);
			totalbikes.setBounds(282, 83, 51, 51);
			frame.getContentPane().add(totalbikes);
			
			availablebikes = new JLabel(String.valueOf(availableBikeCount));
			availablebikes.setHorizontalAlignment(SwingConstants.CENTER);
			availablebikes.setBounds(381, 83, 51, 51);
			frame.getContentPane().add(availablebikes);
			
			totalvans = new JLabel(String.valueOf(vanCount));
			totalvans.setHorizontalAlignment(SwingConstants.CENTER);
			totalvans.setBounds(540, 81, 51, 51);
			frame.getContentPane().add(totalvans);
			
			availablevans = new JLabel(String.valueOf(availableVanCount));
			availablevans.setHorizontalAlignment(SwingConstants.CENTER);
			availablevans.setBounds(639, 81, 51, 51);
			frame.getContentPane().add(availablevans);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 58, 204, 76);
			frame.getContentPane().add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(268, 58, 204, 76);
			frame.getContentPane().add(separator_1);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(524, 58, 204, 76);
			frame.getContentPane().add(separator_2);
			
			JButton btnNewButton_1_1 = new JButton("Manage Vans");
			btnNewButton_1_1.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Van_Registration vreg = new Van_Registration(null);
					frame.setVisible(false);
					vreg.frame.setVisible(true);
				}
			});
			btnNewButton_1_1.setBounds(170, 178, 150, 23);
			frame.getContentPane().add(btnNewButton_1_1);
			
			JButton btnNewButton_1_2 = new JButton("Manage Bikes");
			btnNewButton_1_2.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_1_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Bike_Registration breg = new Bike_Registration(null);
					frame.setVisible(false);
					breg.frame.setVisible(true);
				}
			});
			btnNewButton_1_2.setBounds(170, 212, 150, 23);
			frame.getContentPane().add(btnNewButton_1_2);
			
			JButton btnNewButton_7_1 = new JButton("Manage Van Rents");
			btnNewButton_7_1.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_7_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VanRent vr=new VanRent(null);
					frame.setVisible(false);
					vr.frame.setVisible(true);
				}
			});
			btnNewButton_7_1.setBounds(418, 178, 150, 23);
			frame.getContentPane().add(btnNewButton_7_1);
			
			JButton btnNewButton_7_2 = new JButton("Manage Bike Rents");
			btnNewButton_7_2.setFont(new Font("Unispace", Font.BOLD, 10));
			btnNewButton_7_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BikeRent br=new BikeRent(null);
					frame.setVisible(false);
					br.frame.setVisible(true);
				}
			});
			btnNewButton_7_2.setBounds(418, 212, 150, 23);
			frame.getContentPane().add(btnNewButton_7_2);
			
			JLabel lblNewLabel_7_1 = new JLabel("Administrator Panel");
			lblNewLabel_7_1.setOpaque(true);
			lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_7_1.setForeground(Color.YELLOW);
			lblNewLabel_7_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
			lblNewLabel_7_1.setBackground(new Color(0, 128, 0));
			lblNewLabel_7_1.setBounds(0, 0, 740, 55);
			frame.getContentPane().add(lblNewLabel_7_1);
			
			JLabel lblNewLabel_7_1_1 = new JLabel("");
			lblNewLabel_7_1_1.setOpaque(true);
			lblNewLabel_7_1_1.setForeground(Color.YELLOW);
			lblNewLabel_7_1_1.setBackground(new Color(0, 128, 0));
			lblNewLabel_7_1_1.setBounds(0, 391, 740, 21);
			frame.getContentPane().add(lblNewLabel_7_1_1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
