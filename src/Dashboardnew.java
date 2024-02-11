import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;

public class Dashboardnew {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboardnew window = new Dashboardnew();
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
	public Dashboardnew() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 823, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Available Cars");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 11, 155, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Count of Customers");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(163, 11, 155, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Count of Booked Cars");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(340, 11, 155, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("10");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(30, 144, 255));
		lblNewLabel_2.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 56, 140, 68);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("10");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(178, 34, 34));
		lblNewLabel_3.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(173, 56, 140, 68);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("10");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(new Color(124, 252, 0));
		lblNewLabel_3_1.setFont(new Font("Modern No. 20", Font.PLAIN, 40));
		lblNewLabel_3_1.setBounds(352, 56, 140, 68);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		JButton btnNewButton = new JButton("Car Register");
		btnNewButton.setBounds(33, 135, 215, 122);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Customer Register");
		btnNewButton_1.setBounds(258, 135, 215, 122);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Car Rent");
		btnNewButton_1_1.setBounds(33, 268, 215, 122);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Car Return");
		btnNewButton_1_1_1.setBounds(258, 268, 215, 122);
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Logout");
		btnNewButton_1_1_1_1.setBackground(new Color(169, 169, 169));
		btnNewButton_1_1_1_1.setBounds(371, 401, 102, 37);
		frame.getContentPane().add(btnNewButton_1_1_1_1);
	}
}
