import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegisterEmployee {
	 JFrame frame = new JFrame();  
	    JButton registerButton = new JButton("Register");
	    JButton backButton = new JButton("Back");
	    /////////////////////////////
	    JLabel nameJLabel = new JLabel("Name:");
	    JTextField nameField = new JTextField();
	    ///////////////////////	    
	    JLabel surnameJLabel = new JLabel("Surname:");
	    JTextField surnameField = new JTextField();
	    /////////////////////////////////////
	    JLabel mNoJLabel = new JLabel("Mobile No:");
	    JTextField mNoField = new JTextField();
	    ///////////////////////////////////7
	    JLabel adressJLabel = new JLabel("Adress:");
	    JTextField adressField = new JTextField();
	    //////////////////////////////////
	    JLabel idJLabel = new JLabel("Employee ID:");
	    JTextField idField = new JTextField();
	    //////////////////////////////////
	    JLabel secuJLabel  = new JLabel("Security Key:");
	    JTextField secuField = new JTextField();

	    public RegisterEmployee() {
	        frame.setResizable(false);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setBounds(100, 100, 800, 800);
	        frame.getContentPane().setBackground(Color.RED);
	        frame.setLayout(null);
	        frame.setVisible(true);
	        frame.setLocationRelativeTo(null);
	        ////////////////////////////////7
	        nameJLabel.setForeground(Color.WHITE);
	        nameJLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        nameJLabel.setBounds(150, 70, 200, 20);
	        nameJLabel.setText("Name:");
	        frame.add(nameJLabel);

	        nameField.setBounds(150, 100, 200, 20);
	        nameField.setBackground(Color.WHITE);
	        nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        frame.add(nameField);
	        
	        String nameString;
	        nameString = nameField.getText();
//////////////////////////////////////
	        surnameJLabel.setForeground(Color.WHITE);
	        surnameJLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        surnameJLabel.setBounds(150, 130, 200, 20);
	        surnameJLabel.setText("Surname:");
	        frame.add(surnameJLabel);

	        surnameField.setBounds(150, 160, 200, 20);
	        surnameField.setBackground(Color.WHITE);
	        surnameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        frame.add(surnameField);
	        
	        String surnameString;
	        surnameString = surnameField.getText();

	       
	       
/////////////////////////////////////////
	        mNoJLabel.setForeground(Color.WHITE);
	        mNoJLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        mNoJLabel.setBounds(150, 190, 200, 25);
	        mNoJLabel.setText("Phone Number:");
	        frame.add(mNoJLabel);

	        mNoField.setBounds(150, 220, 200, 20);
	        mNoField.setBackground(Color.WHITE);
	        mNoField.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        frame.add(mNoField);
	        String mNoString;
	        mNoString = mNoField.getText();

	       
 

	       
/////////////////////////////////////////
	        adressJLabel.setForeground(Color.WHITE);
	        adressJLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        adressJLabel.setBounds(150, 250, 200, 20);
	        adressJLabel.setText("Adress:");
	        frame.add(adressJLabel);

	        adressField.setBounds(150, 280, 200, 20);
	        adressField.setBackground(Color.WHITE);
	        adressField.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        frame.add(adressField);
	       
	        String adressString;
	        adressString = adressField.getText();
	       
/////////////////////////////////////////
	        idJLabel.setForeground(Color.WHITE);
	        idJLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        idJLabel.setBounds(150, 310, 200, 20);
	        idJLabel.setText("Employee ID:");
	        frame.add(idJLabel);

	        idField.setBounds(150, 340, 200, 20);
	        idField.setBackground(Color.WHITE);
	        idField.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        frame.add(idField);
	        
	        String idString;
	        idString = idField.getText();

	       
/////////////////////////////////////////
	        secuJLabel.setForeground(Color.WHITE);
	        secuJLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        secuJLabel.setBounds(150, 370, 200, 20);
	        secuJLabel.setText("Security Key:");
	        frame.add(secuJLabel);

	        secuField.setBounds(150, 400, 200, 20);
	        secuField.setBackground(Color.WHITE);
	        secuField.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        frame.add(secuField);
	        

	       
/////////////////////////////////////////
	        registerButton.setBackground(Color.WHITE);
	        registerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        frame.add(registerButton);
	        registerButton.setBounds(300, 700, 200, 50);
	        registerButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	                    try (
	                    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:AAAA/project", "root", "password")) {//To use this you need to change pathway, root and password
	                    	String query = "INSERT INTO project.Employee VALUES (" + idField.getText() + ", '" + nameField.getText() + "', '" + surnameField.getText() + "', '" + mNoField.getText() + "', '" + adressField.getText() + "', '" + secuField.getText() + "')";

	                    	con.createStatement().executeUpdate(query);
	                    	StartPage startPage = new StartPage();
	                        frame.dispose();
	                    } catch (SQLException ex) {
	                    	JOptionPane.showMessageDialog(null, "ERROR:INVALID ACCESS");
	                    }
	              
	                }
	            });
	       
	        
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
	    }

}
