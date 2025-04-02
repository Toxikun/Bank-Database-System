import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class UserAccountMenu {
	JFrame frame = new JFrame();
	JLabel balanceJLabel = new JLabel();
	JTable table = new JTable();
	JLabel accNoJLabel = new JLabel();
	public static String accNo;
	public UserAccountMenu() {
		// TODO Auto-generated constructor stub
	frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.getContentPane().setBackground(Color.RED);
		frame.setLayout(null);
		frame.setVisible(true);		
		frame.setLocationRelativeTo(null);
		
		////////////////////////////
		JLabel lblNewLabel = new JLabel("BALANCE:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 57, 108, 42);
		frame.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setText("$"+LoginUser.balance);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(128, 57, 197, 42);
		frame.add(lblNewLabel_1);
		///////////////////////////////////
		
		
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setForeground(Color.WHITE);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserId.setBounds(10, 103, 108, 42);
		frame.add(lblUserId);
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setText(LoginUser.userID);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_3.setBounds(103, 103, 108, 42);
		frame.add(lblNewLabel_3);
		////////////////////////////////
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUser loginUser = new LoginUser();
				frame.dispose();
				loginUser.userID ="";
				loginUser.adress="";
				loginUser.age="";
				loginUser.balance="";
				loginUser.email="";
				loginUser.name="";
				loginUser.surname="";
				loginUser.mobileNo="";
			}
		});
		btnNewButton_1.setBounds(0, 0, 85, 21);
		frame.add(btnNewButton_1);
		JButton btnNewButton_3 = new JButton("Account Info");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAccountInfo userAccountInfo = new UserAccountInfo();
				frame.dispose();
			}
		});
		btnNewButton_3.setBounds(334, 10, 150, 21);
		frame.add(btnNewButton_3);
		  try (
              	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:AAAA/project", "root", "password")) {//To use this you need to change pathway, root and password
              	String query ="select Account_No from Account where Cust_ID =" + LoginUser.userID;
              	java.sql.Statement statement = con.createStatement();
              	statement.executeQuery(query);
                    	ResultSet resultSet = statement.executeQuery(query);
              	ResultSetMetaData rsmd = resultSet.getMetaData();
              	DefaultTableModel model = (DefaultTableModel) table.getModel();
              	
              	int cols = rsmd.getColumnCount();
              	String[] colName = new String[cols];
              	for (int i = 0; i < cols; i++) {
              		colName[i]=rsmd.getColumnName(i+1);
					}
              	model.setColumnIdentifiers(colName);
              	
              	while (resultSet.next()) {
              		accNo = resultSet.getString(1);
						
					
						
						String[] rowStrings = {accNo};
						model.addRow(rowStrings);
					}
              	statement.close();
              	con.close();
              	
                

                 
              } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(null, "ERROR:INVALID/UNKNOWN USER ID");
              }
		  accNoJLabel.setForeground(Color.WHITE);
		  accNoJLabel.setForeground(Color.WHITE);
			accNoJLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
			accNoJLabel.setBounds(10, 143, 200, 42);
		
			accNoJLabel.setText("Account NO:" + accNo);
			frame.add(accNoJLabel);
			JButton TransactionButton = new JButton("Transactions");
			TransactionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Transactions transactions = new Transactions();
					frame.dispose();
				}
			});
			TransactionButton.setFont(new Font("Blackadder ITC", Font.PLAIN, 24));
			TransactionButton.setBounds(114, 203, 191, 50);
			frame.add(TransactionButton);

		 
          }
      
		
	
	}

	
