import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Loginnew {

	private JFrame frame;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginnew window = new Loginnew();
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
	public Loginnew() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setFont(new Font("Footlight MT Light", Font.BOLD, 45));
		lblNewLabel.setBounds(262, 11, 110, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel user1 = new JLabel("User Name");
		user1.setVerticalAlignment(SwingConstants.TOP);
		user1.setForeground(Color.DARK_GRAY);
		user1.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		user1.setBounds(239, 67, 167, 23);
		frame.getContentPane().add(user1);
		
		JLabel pass1 = new JLabel("Password");
		pass1.setVerticalAlignment(SwingConstants.TOP);
		pass1.setForeground(Color.DARK_GRAY);
		pass1.setFont(new Font("Sitka Small", Font.PLAIN, 16));
		pass1.setBounds(239, 135, 150, 23);
		frame.getContentPane().add(pass1);
		
		user = new JTextField();
		user.setToolTipText("Enter user name");
		user.setFont(new Font("Tahoma", Font.PLAIN, 13));
		user.setColumns(10);
		user.setBounds(239, 101, 185, 23);
		frame.getContentPane().add(user);
		
		pass = new JPasswordField();
		pass.setToolTipText("Enter password");
		pass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pass.setBounds(239, 165, 185, 23);
		frame.getContentPane().add(pass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
					Statement stmt=con.createStatement();
					String sql="Select * from login where Username='"+user.getText()+"' and Password='"+pass.getText().toString()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next())
						JOptionPane.showMessageDialog(null, "Login successfully...");
					else
						JOptionPane.showMessageDialog(null, "Incorrect credentials...");
					con.close();
					
				}catch(Exception e1) {System.out.print(e1);}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBackground(new Color(0, 255, 127));
		btnLogin.setBounds(239, 227, 85, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReset.setBackground(new Color(178, 34, 34));
		btnReset.setBounds(339, 227, 85, 23);
		frame.getContentPane().add(btnReset);
	}
}
