import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;


public class Customer_Registration {

	public JFrame frame;
	private JTextField cid;
	private JTextField cname;
	private JTextField cnic;
	private JTextField cmobile;
	private JTable ctable;
	private JDateChooser cdob;
	private static String username;

	
	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_Registration window = new Customer_Registration(username);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//Create the application. 
	public Customer_Registration(String username) {
		this.username=username;
		initialize();
		tbload();
		autoID();
		clearFields();
	}
	
	public void autoID() {
        try {
            Statement s = DB.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(Cus_Id) FROM customer");
            rs.next();
            String maxID = rs.getString(1);

            if (maxID == null) {
            	cid.setText("00001");
            } else {
                int id = Integer.parseInt(maxID.substring(1)) + 1;
                String nextID = String.format("%05d", id);
                cid.setText(nextID);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
	
	 public void clearFields() {
		 cname.setText("");
		 cdob.setDate(null);
         cnic.setText("");
         cmobile.setText("");
	    }
	
	PreparedStatement pst;
	
    //method of importing data from database to table
	public void tbload() {
        try {
            Statement s = DB.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customer");

            while (rs.next()) {
                String id = rs.getString("Cus_Id");
                String name = rs.getString("Cus_Name");
                String dob = rs.getString("Cus_DOB");
                String nic = rs.getString("Cus_NIC");
                String mobile = rs.getString("Cus_Mobile");

                String[] tbData = { id, name, dob, nic, mobile };
                DefaultTableModel dt = (DefaultTableModel) ctable.getModel();
                dt.addRow(tbData);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
	
	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 837, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_3_1 = new JButton("<");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(username == null) {
					Admin ad = new Admin();
					frame.setVisible(false);
					ad.frame.setVisible(true);
				}
				else {
					Dashboard db = new Dashboard(username);
					frame.setVisible(false);
					db.frame.setVisible(true);
				}
				
			}
		});
		btnNewButton_3_1.setBounds(0, 0, 45, 23);
		frame.getContentPane().add(btnNewButton_3_1);
		
		cid = new JTextField();
		cid.setEditable(false);
		cid.setBounds(174, 123, 107, 19);
		frame.getContentPane().add(cid);
		cid.setColumns(10);
		
		cname = new JTextField();
		cname.setColumns(10);
		cname.setBounds(174, 155, 107, 19);
		frame.getContentPane().add(cname);
		
		cdob = new JDateChooser();
		cdob.setBounds(174, 185, 107, 19);
		frame.getContentPane().add(cdob);
		
		cnic = new JTextField();
		cnic.setColumns(10);
		cnic.setBounds(174, 213, 107, 19);
		frame.getContentPane().add(cnic);
		
		cmobile = new JTextField();
		cmobile.setColumns(10);
		cmobile.setBounds(174, 244, 107, 19);
		frame.getContentPane().add(cmobile);
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Unispace", Font.BOLD, 15));
		lblNewLabel.setBounds(29, 124, 107, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCname = new JLabel("Name :");
		lblCname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCname.setFont(new Font("Unispace", Font.BOLD, 15));
		lblCname.setBounds(29, 156, 107, 14);
		frame.getContentPane().add(lblCname);
		
		JLabel lblCdob = new JLabel("Date Of Birth :");
		lblCdob.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCdob.setFont(new Font("Unispace", Font.BOLD, 15));
		lblCdob.setBounds(29, 184, 107, 16);
		frame.getContentPane().add(lblCdob);
		
		JLabel lblNic = new JLabel("NIC :");
		lblNic.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNic.setFont(new Font("Unispace", Font.BOLD, 15));
		lblNic.setBounds(29, 213, 107, 16);
		frame.getContentPane().add(lblNic);
		
		JLabel lblMobile = new JLabel("Mobile :");
		lblMobile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMobile.setFont(new Font("Unispace", Font.BOLD, 15));
		lblMobile.setBounds(29, 241, 107, 21);
		frame.getContentPane().add(lblMobile);
		
		JButton btnadd = new JButton("Add");
		btnadd.setForeground(new Color(0, 0, 0));
		btnadd.setBackground(new Color(0, 128, 0));
		btnadd.setFont(new Font("Dialog", Font.BOLD, 13));
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cusid, cusname, cusdob, cusnic, cusmobile;
				
				cusid = cid.getText();
				cusname = cname.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        cusdob = sdf.format(cdob.getDate());
				cusnic = cnic.getText();
				cusmobile = cmobile.getText();
				
				try {
					
					Statement s = DB.mycon().createStatement();
					String sql1="insert into customer(Cus_Id, Cus_Name, Cus_DOB, Cus_NIC, Cus_Mobile) values ('"+cusid+"', '"+cusname+"', '"+cusdob+"', '"+cusnic+"', '"+cusmobile+"');";
					int rowsAffected = s.executeUpdate(sql1);
		            
		            if (rowsAffected > 0) {
		            	DefaultTableModel model=(DefaultTableModel)ctable.getModel();
		            	model.setRowCount(0);
		            	tbload();
		                JOptionPane.showMessageDialog(null, "Customer Add Successfully");
		                clearFields();
		                autoID();
		                
		            } else {
		            	
		                JOptionPane.showMessageDialog(null, "Failed");
		            }
				}catch(Exception e1) {
					System.out.print(e1);
				}
				
			}
		});
		btnadd.setBounds(67, 290, 98, 34);
		frame.getContentPane().add(btnadd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setForeground(new Color(0, 0, 0));
		btnEdit.setBackground(new Color(0, 128, 0));
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 13));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cusid, cusname, cusdob, cusnic, cusmobile;
				
				cusid = cid.getText();
				cusname = cname.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        cusdob = sdf.format(cdob.getDate());
				cusnic = cnic.getText();
				cusmobile = cmobile.getText();
				
				try {
					
					Statement s = DB.mycon().createStatement();
					String sqledit="UPDATE customer SET Cus_Id = '"+cusid+"', Cus_Name = '"+cusname+"', Cus_DOB = '"+cusdob+"', Cus_NIC = '"+cusnic+"', Cus_Mobile = '"+cusmobile+"' WHERE Cus_Id = '"+cusid+"'";
						
					int rowsAffected = s.executeUpdate(sqledit);
		            
		            if (rowsAffected > 0) {
		            	DefaultTableModel model=(DefaultTableModel)ctable.getModel();
		            	model.setRowCount(0);
		            	tbload();
		                JOptionPane.showMessageDialog(null, "Customer Edit Successfully");
		                clearFields();
		                autoID();
		                
		            } else {
		            	
		                JOptionPane.showMessageDialog(null, "Failed");
		            }
				}catch(Exception e1) {
					System.out.print(e1);
				}
				
			}
		});
		btnEdit.setBounds(172, 290, 98, 34);
		frame.getContentPane().add(btnEdit);
		
		JButton btnNewButton_3 = new JButton("Clear");
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setBackground(new Color(0, 128, 0));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
				autoID();
			}
		});
		btnNewButton_3.setFont(new Font("Dialog", Font.BOLD, 13));
		btnNewButton_3.setBounds(172, 335, 98, 34);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setForeground(new Color(0, 0, 0));
		btnNewButton_4.setBackground(new Color(0, 128, 0));
		btnNewButton_4.setFont(new Font("Dialog", Font.BOLD, 13));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				try {
				    Connection connection = DB.mycon();
				    String sql = "DELETE FROM Customer WHERE Cus_Id=?";
				    
				    PreparedStatement preparedStatement = connection.prepareStatement(sql);
				    preparedStatement.setString(1, cid.getText());
				    
				    int rowsAffected = preparedStatement.executeUpdate();
				    
				    if (rowsAffected > 0) {
				    	DefaultTableModel model=(DefaultTableModel)ctable.getModel();
		            	model.setRowCount(0);
				    	tbload();
				        JOptionPane.showMessageDialog(null, "Customer deleted successfully");
				        clearFields();
		                autoID();
				      
				    } else {
				        JOptionPane.showMessageDialog(null, "Customer not found or deletion failed");
				    }
				} catch (SQLException e1) {
				    e1.printStackTrace();
				}

				
				
			}
		});
		btnNewButton_4.setBounds(67, 334, 98, 34);
		frame.getContentPane().add(btnNewButton_4);
		
		JScrollPane ctable1 = new JScrollPane();
		ctable1.setBounds(291, 104, 522, 308);
		frame.getContentPane().add(ctable1);
		
		ctable = new JTable();
		ctable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1 = (DefaultTableModel) ctable.getModel();
		        int selectIndex = ctable.getSelectedRow();

		        
		        if (selectIndex != -1) {
		            cid.setText(d1.getValueAt(selectIndex, 0).toString());
		            cname.setText(d1.getValueAt(selectIndex, 1).toString());
		            cdob.setDateFormatString(d1.getValueAt(selectIndex, 2).toString());
		            cnic.setText(d1.getValueAt(selectIndex, 3).toString());
		            cmobile.setText(d1.getValueAt(selectIndex, 4).toString());
		        }
			}
		});
		ctable1.setViewportView(ctable);
		ctable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Date Of Birth", "NIC", "Mobile Number"
			}
		) {
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row,int column) {return false;}}
				);
		
		JLabel lblNewLabel_7 = new JLabel("Customer Registration");
		lblNewLabel_7.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setForeground(Color.YELLOW);
		lblNewLabel_7.setBackground(new Color(0, 128, 0));
		lblNewLabel_7.setBounds(0, 0, 823, 97);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("");
		lblNewLabel_7_1.setOpaque(true);
		lblNewLabel_7_1.setForeground(Color.YELLOW);
		lblNewLabel_7_1.setBackground(new Color(0, 128, 0));
		lblNewLabel_7_1.setBounds(0, 417, 823, 21);
		frame.getContentPane().add(lblNewLabel_7_1);
		
		
	}
}
