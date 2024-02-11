import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class Login {
    public JFrame frame;
    private JTextField user;
    private JPasswordField pass;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create and display the Login window
                    Login window = new Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        // Initialize the application
        initialize();
    }

    private void initialize() {
        // Create the main JFrame
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 450, 322);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Create and configure the "Login" label
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(new Color(0, 128, 0));
        lblNewLabel.setFont(new Font("Footlight MT Light", Font.BOLD, 45));
        lblNewLabel.setBounds(262, 11, 110, 45);
        frame.getContentPane().add(lblNewLabel);

        // Create and configure the "User Name" label
        JLabel user1 = new JLabel("User Name");
        user1.setVerticalAlignment(SwingConstants.TOP);
        user1.setForeground(Color.DARK_GRAY);
        user1.setFont(new Font("Dialog", Font.BOLD, 16));
        user1.setBounds(239, 67, 167, 23);
        frame.getContentPane().add(user1);

        // Create and configure the "Password" label
        JLabel pass1 = new JLabel("Password");
        pass1.setVerticalAlignment(SwingConstants.TOP);
        pass1.setForeground(Color.DARK_GRAY);
        pass1.setFont(new Font("Dialog", Font.BOLD, 16));
        pass1.setBounds(239, 135, 150, 23);
        frame.getContentPane().add(pass1);

        // Create the text field for entering the username
        user = new JTextField();
        user.setToolTipText("Enter user name");
        user.setFont(new Font("Tahoma", Font.PLAIN, 13));
        user.setColumns(10);
        user.setBounds(239, 101, 185, 23);
        frame.getContentPane().add(user);

        // Create the password field for entering the password
        pass = new JPasswordField();
        pass.setToolTipText("Enter password");
        pass.setFont(new Font("Tahoma", Font.PLAIN, 13));
        pass.setBounds(239, 165, 185, 23);
        frame.getContentPane().add(pass);

        // Create a checkbox for logging in as an administrator
        JCheckBox chckbxNewCheckBox = new JCheckBox("Logging as Administrator");
        chckbxNewCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chckbxNewCheckBox.isSelected()) {
                    // If checked, set the username to "Admin" and clear the password field
                    user.setText("Admin");
                    pass.setText("");
                } else {
                    // If unchecked, clear both fields
                    user.setText("");
                    pass.setText("");
                }
            }
        });
        chckbxNewCheckBox.setBounds(239, 223, 167, 23);
        frame.getContentPane().add(chckbxNewCheckBox);

        // Create the "Login" button and configure its functionality
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uname = user.getText();
                String upass = pass.getText();

                if (chckbxNewCheckBox.isSelected()) {
                    // If logging in as an administrator
                    if (upass.equals("password")) {
                        // Check the administrator password
                        JOptionPane.showMessageDialog(null, "Login successfully as Administrator...");
                        Admin ad = new Admin();
                        frame.setVisible(false);
                        ad.frame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Administrator password...");
                    }
                } else {
                    // If logging in as a regular user
                    try {
                        // Establish a database connection and verify user credentials
                        Statement stmt = DB.mycon().createStatement();
                        String sql = "Select * from employee where Username='" + uname + "' and Password='" + upass + "'";
                        ResultSet rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "Login successfully...");
                            Dashboard db = new Dashboard(uname);
                            frame.setVisible(false);
                            db.frame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect credentials...");
                        }
                        DB.mycon().close();
                    } catch (Exception e1) {
                        System.out.print(e1);
                    }
                }
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnLogin.setBackground(new Color(0, 255, 127));
        btnLogin.setBounds(239, 252, 85, 23);
        frame.getContentPane().add(btnLogin);

        // Create a "Help" label with a mailto link
        JLabel helpLabel = new JLabel("Help..!");
        helpLabel.setForeground(new Color(0, 0, 255));
        helpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        helpLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Open the default email client with a support email address
                openEmailClient("mailto:vrssupport@gmail.com");
            }
        });
        helpLabel.setBounds(384, 258, 40, 14);
        frame.getContentPane().add(helpLabel);

        // Create a checkbox to show/hide the password
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                pass.setEchoChar(checkBox.isSelected() ? '\u0000' : '\u2022');
            }
        });
        showPasswordCheckBox.setBounds(239, 197, 122, 23);
        frame.getContentPane().add(showPasswordCheckBox);

        // Create an image icon (assumes login.png is in the specified path)
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("D:\\Vehicle Rental System\\Vehicle_Rental_System\\images\\login.png"));
        lblNewLabel_1.setBounds(0, 0, 224, 285);
        frame.getContentPane().add(lblNewLabel_1);
    }

    // Function to open the default email client with a mailto link
    private void openEmailClient(String mailto) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().mail(new URI(mailto));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to open the email client.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Desktop is not supported, unable to open the email client.");
        }
    }
}