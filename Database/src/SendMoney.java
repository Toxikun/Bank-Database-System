import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.border.EmptyBorder;

public class SendMoney {
	JFrame frame = new JFrame();
	JTextField accountNoField,amountField;
public static String additionalBalanceString;
    JButton transferButton = new JButton("Transfer");
	public SendMoney() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
	
		frame.getContentPane().setBackground(Color.red);
		

		
		frame.setLayout(null);
		frame.setVisible(true);
		JButton btnNewButton_2 = new JButton("Send");
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton_2.setBounds(114, 203, 191, 50);
		frame.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transactions transactions = new Transactions();
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(0, 0, 85, 21);
		frame.add(btnNewButton_1);
		
		JLabel lblUserId = new JLabel("Amount:");
		lblUserId.setForeground(Color.WHITE);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserId.setBounds(279, 49, 108, 42);
		frame.add(lblUserId);
		
		JLabel lblNewLabel_1_1 = new JLabel("Account ID:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(10, 49, 126, 42);
		frame.add(lblNewLabel_1_1);
		
		accountNoField = new JTextField();
		accountNoField.setBounds(10, 87, 126, 30);
		frame.add(accountNoField);
		accountNoField.setColumns(10);
		
		amountField = new JTextField();
		amountField.setColumns(10);
		amountField.setBounds(274, 92, 126, 30);
		frame.add(amountField);
		
		
		String gönderilecekAccountString= accountNoField.getText();
		String amountString = amountField.getText();
	
	 
		
		
		btnNewButton_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String gönderilecekAccountString = accountNoField.getText();
		        String amountString = amountField.getText();
		        

		        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:AAAA/project", "root", "password")) {//To use this you need to change pathway, root and password
		            String query = "UPDATE Account SET Balance = Balance -"+ amountString+" WHERE Account_No = "+LoginUser.accountNo;
		            PreparedStatement pstmt = con.prepareStatement(query);
		            

		            int rowsUpdated = pstmt.executeUpdate();

		            if (rowsUpdated > 0) {
		                JOptionPane.showMessageDialog(frame, "Money sent successfully.");
		            } else {
		                JOptionPane.showMessageDialog(frame, "Error occurred while sending money.");
		            }

		            pstmt.close();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Veritabanı2 hatası: " + ex.getMessage());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Invalid amount of money");
		        }

		        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:AAAA/project", "root", "password")) {//To use this you need to change pathway, root and password
		            String query = "UPDATE project.Account SET Balance = Balance +"  +amountString	 + " WHERE Account_No = " + gönderilecekAccountString;
		           
		            System.out.println(query);
		            
		            
		           con.createStatement().executeUpdate(query);
		          

		         
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Veritaban3 hatası: " + ex.getMessage());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Invalid amount of money");
		        }
		    }
		});

	}}
