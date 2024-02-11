import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class RentTopUp {

	public JFrame frame;
	protected static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentTopUp window = new RentTopUp(username);
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
	public RentTopUp(String username) {
		this.username=username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_1_1 = new JButton("Car Rent");
		btnNewButton_1_1.setBackground(new Color(0, 128, 0));
		btnNewButton_1_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1_1.setFont(new Font("Unispace", Font.BOLD, 12));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarRent cr=new CarRent(username);
				frame.setVisible(false);
				cr.frame.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(131, 73, 155, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_2 = new JButton("Van Rent");
		btnNewButton_1_1_2.setBackground(new Color(0, 128, 0));
		btnNewButton_1_1_2.setForeground(new Color(0, 0, 0));
		btnNewButton_1_1_2.setFont(new Font("Unispace", Font.BOLD, 12));
		btnNewButton_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VanRent vr=new VanRent(username);
				frame.setVisible(false);
				vr.frame.setVisible(true);
			}
		});
		btnNewButton_1_1_2.setBounds(131, 107, 155, 23);
		frame.getContentPane().add(btnNewButton_1_1_2);
		
		JButton btnNewButton_1_1_3 = new JButton("Bike Rent");
		btnNewButton_1_1_3.setBackground(new Color(0, 128, 0));
		btnNewButton_1_1_3.setForeground(new Color(0, 0, 0));
		btnNewButton_1_1_3.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton_1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BikeRent br=new BikeRent(username);
				frame.setVisible(false);
				br.frame.setVisible(true);
			}
		});
		btnNewButton_1_1_3.setBounds(131, 141, 155, 23);
		frame.getContentPane().add(btnNewButton_1_1_3);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setFont(new Font("Unispace", Font.BOLD, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard db = new Dashboard(username);
                frame.setVisible(false);
                db.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(347, 230, 79, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("D:\\Vehicle Rental System\\Vehicle_Rental_System\\images\\car.png"));
		lblNewLabel.setBounds(0, 38, 436, 225);
		frame.getContentPane().add(lblNewLabel);
	}

}
