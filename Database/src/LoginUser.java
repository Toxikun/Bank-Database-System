import java.awt.Color;
import java.sql.ResultSetMetaData;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.protocol.a.result.ResultsetRowsStatic;
import com.mysql.cj.xdevapi.Table;


public class LoginUser {
	JTable table = new JTable();
    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JButton loginButton = new JButton("Login");
    JLabel loginJLabel = new JLabel("User ID:");
    JButton backButton = new JButton("Back");
    String girdi;
    public static String userID,age , name, surname,mobileNo, email,adress,balance,accountNo;
    
    

    public LoginUser() {
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);
        frame.getContentPane().setBackground(Color.RED);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        textField.setBounds(150, 100, 200, 100);
        textField.setBackground(Color.WHITE);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        frame.add(textField);

        loginButton.setBackground(Color.WHITE);
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        frame.add(loginButton);
        loginButton.setBounds(150, 250, 200, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            girdi = textField.getText();
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "orkun123")) {
                String query = "select * from project.Customer where Cust_ID=" + girdi;
                java.sql.Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) { 
                	userID = resultSet.getString("Cust_ID");
                    name = resultSet.getString("name");
                    surname = resultSet.getString("Surname");
                    age = resultSet.getString("Age");
                    mobileNo = resultSet.getString("mobile_no");
                    email = resultSet.getString("email");
                    adress = resultSet.getString("address");

                
                    String query2 = "select Account_No from Account where Cust_ID =" + userID;
                    ResultSet resultSet2 = statement.executeQuery(query2);
                    
                    
                    if (resultSet2.next()) {
                        accountNo = resultSet2.getString("Account_No");
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR: Account not found for user ID: " + userID);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR:INVALID/UNKNOWN USER ID");
                }
                String query3 = "select Balance from Account where Account_No = " + accountNo; 
                ResultSet resultSet3 = statement.executeQuery(query3);
                if (resultSet3.next()) {
                	balance = resultSet3.getString("Balance");
					
				}

                statement.close();
                con.close();
                UserAccountMenu userAccountMenu = new UserAccountMenu();
                frame.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
            }}
        });

        loginJLabel.setForeground(Color.WHITE);
        loginJLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
        loginJLabel.setVerticalAlignment(SwingConstants.TOP);
        loginJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginJLabel.setBounds(69, 11, 298, 46);
        frame.add(loginJLabel);

        

        backButton.setBackground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        frame.add(backButton);
        backButton.setBounds(0, 0, 100, 20);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	StartPage startPage = new StartPage();
                frame.dispose();
            }
        });
    }}
