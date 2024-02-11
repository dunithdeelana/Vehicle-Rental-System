import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Financial {

    public JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Financial window = new Financial();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Financial() {
        initialize();
        populateTable(tableModel);
    }

    private void populateTable(DefaultTableModel tableModel) {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM financial";
            ResultSet resultSet = statement.executeQuery(query);

            // Clear existing data and columns
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add column names to the table model
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }

            // Populate the table with data
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = resultSet.getObject(i + 1);
                }
                tableModel.addRow(rowData);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 776, 478);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(panel);

        panel.setLayout(null);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 67, 760, 291);
        panel.add(scrollPane);

        JButton Sum = new JButton("Monthly Summary");
        Sum.setFont(new Font("Unispace", Font.BOLD, 10));
        Sum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
                    Statement statement = connection.createStatement();
                    String stot = "SELECT SUM(Sub_Total) FROM financial";
                    String dis = "SELECT SUM(Discount) FROM financial";
                    String tot = "SELECT SUM(Total) FROM financial";

                    ResultSet resultSet1 = statement.executeQuery(stot);
                    resultSet1.next();
                    double subTotalSum = resultSet1.getDouble(1);

                    ResultSet resultSet2 = statement.executeQuery(dis);
                    resultSet2.next();
                    double discountSum = resultSet2.getDouble(1);

                    ResultSet resultSet3 = statement.executeQuery(tot);
                    resultSet3.next();
                    double totalSum = resultSet3.getDouble(1);

                    String summaryMessage = "Monthly Summary:\n\n";
                    summaryMessage += "Sub Total: Rs. " + subTotalSum + "\n";
                    summaryMessage += "Discount: Rs. " + discountSum + "\n";
                    summaryMessage += "Total: Rs. " + totalSum;

                    JOptionPane.showMessageDialog(frame, summaryMessage, "Monthly Summary", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        Sum.setBounds(124, 393, 155, 23);
        panel.add(Sum);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Unispace", Font.BOLD, 10));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Admin ad = new Admin();
                frame.setVisible(false);
                ad.frame.setVisible(true);
            }
        });
        btnCancel.setBounds(322, 393, 155, 23);
        panel.add(btnCancel);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Unispace", Font.BOLD, 10));
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclerentalsystem", "root", "");
                    Statement statement = connection.createStatement();
                    String query = "TRUNCATE TABLE financial";
                    int rowsAffected = statement.executeUpdate(query);

                    // Clear the table data and repopulate it
                    populateTable(tableModel);

                    statement.close();
                    connection.close();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnReset.setBounds(522, 393, 155, 23);
        panel.add(btnReset);

        // Add a JLabel to display a message
        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(502, 393, 186, 23);
        panel.add(messageLabel);

        JLabel lblNewLabel_7_1_1 = new JLabel("");
        lblNewLabel_7_1_1.setOpaque(true);
        lblNewLabel_7_1_1.setForeground(Color.YELLOW);
        lblNewLabel_7_1_1.setBackground(new Color(0, 128, 0));
        lblNewLabel_7_1_1.setBounds(-13, 348, 773, 93);
        panel.add(lblNewLabel_7_1_1);

        JLabel lblNewLabel_7_1 = new JLabel("Financial Summary");
        lblNewLabel_7_1.setOpaque (true);
        lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7_1.setForeground(Color.YELLOW);
        lblNewLabel_7_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
        lblNewLabel_7_1.setBackground(new Color(0, 128, 0));
        lblNewLabel_7_1.setBounds(0, 0, 760, 70);
        panel.add(lblNewLabel_7_1);
    }
}
