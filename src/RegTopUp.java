import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class RegTopUp {

	public JFrame frame;
	protected static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegTopUp window = new RegTopUp(username);
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
	public RegTopUp(String username) {
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
		
		JButton btnNewButton = new JButton("Car Register");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Car_Registration cr = new Car_Registration(username);
				frame.setVisible(false);
				cr.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(131, 73, 155, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnVanRegister = new JButton("Van Register");
		btnVanRegister.setForeground(new Color(0, 0, 0));
		btnVanRegister.setBackground(new Color(0, 128, 0));
		btnVanRegister.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVanRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Van_Registration vreg = new Van_Registration(username);
				frame.setVisible(false);
				vreg.frame.setVisible(true);
			}
		});
		btnVanRegister.setBounds(131, 107, 155, 23);
		frame.getContentPane().add(btnVanRegister);
		
		JButton btnNewButton_3_1 = new JButton("Bike Register");
		btnNewButton_3_1.setForeground(new Color(0, 0, 0));
		btnNewButton_3_1.setBackground(new Color(0, 128, 0));
		btnNewButton_3_1.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bike_Registration breg = new Bike_Registration(username);
				frame.setVisible(false);
				breg.frame.setVisible(true);
			}
		});
		btnNewButton_3_1.setBounds(131, 141, 155, 23);
		frame.getContentPane().add(btnNewButton_3_1);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Unispace", Font.PLAIN, 10));
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
