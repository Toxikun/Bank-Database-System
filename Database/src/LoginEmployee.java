	import java.awt.Color;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JButton;
	import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
	
public class LoginEmployee {
	



	
		JFrame frame = new JFrame();
		JTextField textField =new JTextField();
		JButton loginButton = new JButton();
		JLabel loginJLabel = new JLabel();
		JButton backButton = new JButton();
		String aString;
		
		public LoginEmployee() {
			
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 500, 500);
			frame.getContentPane().setBackground(Color.RED);
			frame.setLayout(null);
			frame.setVisible(true);		
			frame.setLocationRelativeTo(null);
			//////////////////
			textField.setBounds(0, 0, 90, 20);
			textField.setBackground(Color.white);
			textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
			frame.add(textField);		
				textField.setBounds(150,100 , 200,100);
				
				
			loginButton.setBounds(0, 0, 89, 23);
			loginButton.setBackground(Color.white);
			loginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			loginButton.setText("Login");
			frame.add(loginButton);		
				loginButton.setBounds(150,250 , 200,50);
				loginButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					aString = textField.getText();
					EmployeeAccount employeeAccount = new EmployeeAccount();
					System.out.println(aString);
					frame.dispose();
						
					}
				});

				loginJLabel.setForeground(Color.WHITE);
				loginJLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
				loginJLabel.setVerticalAlignment(SwingConstants.TOP);
				loginJLabel.setHorizontalAlignment(SwingConstants.CENTER);
				loginJLabel.setBounds(69, 11, 298, 46);
				loginJLabel.setText("Employee ID:");
			frame.add(loginJLabel);
			
			
				

				backButton.setBackground(Color.white);
				backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				backButton.setText("Back");
				frame.add(backButton);		
					backButton.setBounds(0,0 , 100,20);
					backButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
						aString = textField.getText();
						StartPage startPage = new StartPage();
						System.out.println(aString);
						frame.dispose();
							
						}
					});
				
			
			
			
		}
}