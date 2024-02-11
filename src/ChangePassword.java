import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ChangePassword {

	public JFrame frame;
	private JTextField oldpass;
	private JTextField newpass;
	private JTextField conpass;
	private static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword window = new ChangePassword(username);
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
	public ChangePassword(String username) {
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
		
		JLabel lblNewLabel = new JLabel("Old Password :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(50, 92, 120, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewPassword_1 = new JLabel("New Password :");
		lblNewPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewPassword_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPassword_1.setBounds(50, 123, 120, 14);
		frame.getContentPane().add(lblNewPassword_1);
		
		JLabel lblNewPassword = new JLabel("Confirm Password :");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPassword.setBounds(50, 154, 120, 14);
		frame.getContentPane().add(lblNewPassword);
		
		oldpass = new JTextField();
		oldpass.setBounds(179, 89, 175, 20);
		frame.getContentPane().add(oldpass);
		oldpass.setColumns(10);
		
		newpass = new JTextField();
		newpass.setColumns(10);
		newpass.setBounds(179, 120, 175, 20);
		frame.getContentPane().add(newpass);
		
		conpass = new JTextField();
		conpass.setColumns(10);
		conpass.setBounds(179, 151, 175, 20);
		frame.getContentPane().add(conpass);
		
		JLabel lblNewLabel_1 = new JLabel("Change the Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(0, 128, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		lblNewLabel_1.setBounds(0, 0, 436, 44);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opass = oldpass.getText();
		        String npass = newpass.getText();
		        String cpass = conpass.getText();

		        try {
		            String selectQuery = "SELECT * FROM employee WHERE Username = ? AND Password = ?";
		            PreparedStatement selectStmt = DB.mycon().prepareStatement(selectQuery);
		            selectStmt.setString(1, username);
		            selectStmt.setString(2, opass);
		            ResultSet rs = selectStmt.executeQuery();

		            if (rs.next()) {
		                if (npass.equals(cpass)) {
		                    
		                    String updateQuery = "UPDATE employee SET Password = ? WHERE Username = ?";
		                    PreparedStatement updateStmt = DB.mycon().prepareStatement(updateQuery);
		                    updateStmt.setString(1, npass);
		                    updateStmt.setString(2, username);
		                    int rowsUpdated = updateStmt.executeUpdate();

		                    if (rowsUpdated > 0) {
		                        JOptionPane.showMessageDialog(null, "Password Changed Successfully");
		                        
		                        Profile prf = new Profile(username);
		                        frame.setVisible(false);
		                        prf.frame.setVisible(true);
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Password update failed.");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "New passwords do not match.");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Incorrect Old Password");
		            }

		            DB.mycon().close();
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		btnNewButton.setBounds(141, 191, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile prf=new Profile(username);
				frame.setVisible(false);
				prf.frame.setVisible(true);
			}
		});
		btnCancel.setBounds(235, 191, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Poor Richard", Font.BOLD, 20));
		lblNewLabel_1_1.setBackground(new Color(0, 128, 0));
		lblNewLabel_1_1.setBounds(0, 230, 436, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
	}
}
