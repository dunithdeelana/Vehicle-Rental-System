import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Profile {

	public JFrame frame;
	private static String username;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile window = new Profile(username);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Profile(String username) {
		this.username = username;
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 804, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(42, 55, 95, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(username);
		lblNewLabel_2.setBounds(147, 55, 195, 19);
		frame.getContentPane().add(lblNewLabel_2);
		
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
		lblNewLabel_1_4_7.setBounds(42, 385, 95, 19);
		frame.getContentPane().add(lblNewLabel_1_4_7);
		
		JLabel lblNewLabel_1_5 = new JLabel("Employment Status :");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_5.setBounds(482, 55, 132, 19);
		frame.getContentPane().add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Pay Frequency :");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_6.setBounds(482, 87, 132, 19);
		frame.getContentPane().add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("Work Shedule :");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_7.setBounds(482, 117, 132, 19);
		frame.getContentPane().add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_8 = new JLabel("Salary Basic :");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_8.setBounds(482, 147, 132, 19);
		frame.getContentPane().add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_3 = new JLabel("Change the Password");
		lblNewLabel_3.setForeground(new Color(0, 0, 255));
		lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ChangePassword chp = new ChangePassword(username);
				frame.setVisible(false);
				chp.frame.setVisible(true);
			}
		});
		lblNewLabel_3.setBounds(540, 388, 132, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.setFont(new Font("Unispace", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard db= new Dashboard(username);
				frame.setVisible(false);
				db.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(678, 384, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(147, 87, 195, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setBounds(147, 117, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("");
		lblNewLabel_5_2.setBounds(147, 147, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("");
		lblNewLabel_5_3.setBounds(147, 177, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_5_4 = new JLabel("");
		lblNewLabel_5_4.setBounds(147, 207, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_4);
		
		JLabel lblNewLabel_5_5 = new JLabel("");
		lblNewLabel_5_5.setBounds(147, 237, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_5);
		
		JLabel lblNewLabel_5_6 = new JLabel("");
		lblNewLabel_5_6.setBounds(147, 267, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_6);
		
		JLabel lblNewLabel_5_7 = new JLabel("");
		lblNewLabel_5_7.setBounds(147, 297, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_7);
		
		JLabel lblNewLabel_5_8 = new JLabel("");
		lblNewLabel_5_8.setBounds(147, 327, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_8);
		
		JLabel lblNewLabel_5_9 = new JLabel("");
		lblNewLabel_5_9.setBounds(147, 357, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_9);
		
		JLabel lblNewLabel_5_10 = new JLabel("");
		lblNewLabel_5_10.setBounds(147, 387, 195, 14);
		frame.getContentPane().add(lblNewLabel_5_10);
		
		JLabel lblNewLabel_5_11 = new JLabel("");
		lblNewLabel_5_11.setBounds(624, 57, 154, 14);
		frame.getContentPane().add(lblNewLabel_5_11);
		
		JLabel lblNewLabel_5_12 = new JLabel("");
		lblNewLabel_5_12.setBounds(624, 87, 154, 14);
		frame.getContentPane().add(lblNewLabel_5_12);
		
		JLabel lblNewLabel_5_13 = new JLabel("");
		lblNewLabel_5_13.setBounds(624, 117, 154, 14);
		frame.getContentPane().add(lblNewLabel_5_13);
		
		JLabel lblNewLabel_5_14 = new JLabel("");
		lblNewLabel_5_14.setBounds(624, 147, 154, 14);
		frame.getContentPane().add(lblNewLabel_5_14);
		
		JLabel lblNewLabel_7_1 = new JLabel("Profile");
		lblNewLabel_7_1.setOpaque(true);
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setForeground(Color.YELLOW);
		lblNewLabel_7_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
		lblNewLabel_7_1.setBackground(new Color(0, 128, 0));
		lblNewLabel_7_1.setBounds(0, 0, 794, 49);
		frame.getContentPane().add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("");
		lblNewLabel_7_1_1.setOpaque(true);
		lblNewLabel_7_1_1.setForeground(Color.YELLOW);
		lblNewLabel_7_1_1.setBackground(new Color(0, 128, 0));
		lblNewLabel_7_1_1.setBounds(0, 414, 794, 35);
		frame.getContentPane().add(lblNewLabel_7_1_1);
		
		String url = "jdbc:mysql://localhost:3306/vehiclerentalsystem";
        String usname = "root";
        String password = "";
        
        try (Connection connection = DriverManager.getConnection(url, usname, password)) {
            
        	String query = "SELECT `First Name`, `Last Name`, `Address`, `City`, `Postal Code`, NIC, Gender, DOB, `Phone Number`, Email, `Job Title`, `Employment Status`, `Pay Frequency`, `Work Shedule`, `Salary Basic` FROM employee WHERE Username = '" + username + "';";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                
            	lblNewLabel_5.setText(resultSet.getString("First Name"));
            	lblNewLabel_5_1.setText(resultSet.getString("Last Name"));
            	lblNewLabel_5_2.setText(resultSet.getString("Address"));
            	lblNewLabel_5_3.setText(resultSet.getString("City"));
            	lblNewLabel_5_4.setText(resultSet.getString("Postal Code"));
            	lblNewLabel_5_5.setText(resultSet.getString("NIC"));
            	lblNewLabel_5_6.setText(resultSet.getString("Gender"));
            	lblNewLabel_5_7.setText(resultSet.getString("DOB"));
            	lblNewLabel_5_8.setText(resultSet.getString("Phone Number"));
            	lblNewLabel_5_9.setText(resultSet.getString("Email"));
            	lblNewLabel_5_10.setText(resultSet.getString("Job Title"));
            	lblNewLabel_5_11.setText(resultSet.getString("Employment Status"));
            	lblNewLabel_5_12.setText(resultSet.getString("Pay Frequency"));
            	lblNewLabel_5_13.setText(resultSet.getString("Work Shedule"));
            	lblNewLabel_5_14.setText(resultSet.getString("Salary Basic"));
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
