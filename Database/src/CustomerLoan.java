import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CustomerLoan {
	JFrame frame = new JFrame();
	
	private JTextField amountField;
	public static String amountString;

	public CustomerLoan() {
		// TODO Auto-generated constructor stub
		
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
	
		frame.getContentPane().setBackground(Color.red);
		frame.setLayout(null);
		frame.setVisible(true);
		
		JLabel amountLabel = new JLabel("Amount:");
		amountLabel.setForeground(Color.WHITE);
		amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		amountLabel.setBounds(27, 10, 140, 73);
		frame.add(amountLabel);
		
		
		
		amountField = new JTextField();
		amountField.setBounds(23, 64, 140, 36);
		frame.add(amountField);
		amountField.setColumns(10);
		
	
		
		JButton btnNewButton = new JButton("Request Loan");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amountString = amountField.getText();
				
				try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:AAAA/project", "root", "password")) {//To use this you need to change pathway, root and password
					String query="Insert into project.Transaction values("+ amountString +","+LoginUser.accountNo+");";
					System.out.println(query);
					con.createStatement().executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Your request has been send.");
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error:You already send a request for this account.");
				}
			}
		});
		
		JButton backButton = new JButton("Back");
		btnNewButton.setBounds(90, 152, 258, 47);
		frame.add(btnNewButton);
		   backButton.setBackground(Color.WHITE);
	        backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        frame.add(backButton);
	        backButton.setBounds(0, 0, 100, 20);
	        backButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Transactions transactions = new Transactions();
	                frame.dispose();
	            }
	        });
	}
	}

