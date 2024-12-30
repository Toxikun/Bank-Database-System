import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StartPage {
	JFrame frame = new JFrame();
	JButton userLoginButton =new JButton();
	JButton userRegisterButton = new JButton();
	JButton empLoginButton = new JButton();
	JButton empRegisterButton = new JButton();
	public StartPage() {
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.getContentPane().setBackground(Color.RED);
		frame.setLayout(null);
		frame.setVisible(true);		
		frame.setLocationRelativeTo(null);
		//////////////////
		
		userLoginButton.setBackground(Color.white);
		userLoginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userLoginButton.setText("User Login");
		frame.add(userLoginButton);		
			userLoginButton.setBounds(150,100 , 200,50);
			userLoginButton.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					LoginUser loginUser = new LoginUser();
					frame.dispose();
				}
			});
			
			userRegisterButton.setBackground(Color.white);
			userRegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			userRegisterButton.setText("User Register");
			frame.add(userRegisterButton);		
				userRegisterButton.setBounds(150,160 , 200,50);
				userRegisterButton.addActionListener( new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						RegisterCustomer registerCustomer=new RegisterCustomer();
						frame.dispose();
					}
				});
			
			
		
		empLoginButton.setBackground(Color.white);
		empLoginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		empLoginButton.setText("Employee Login");
		frame.add(empLoginButton);		
			empLoginButton.setBounds(150,250 , 200,50);
			
			empLoginButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					LoginEmployee loginEmployee = new LoginEmployee();
					frame.dispose();
				}
			});
			
			empRegisterButton.setBackground(Color.white);
			empRegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			empRegisterButton.setText("Employee Register");
			frame.add(empRegisterButton);		
				empRegisterButton.setBounds(150,310 , 200,50);
				
				empRegisterButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						RegisterEmployee registerEmployee = new RegisterEmployee();
						frame.dispose();
					}
				});
			
							
		
	}}


