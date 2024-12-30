import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class KumTorbası {
	JFrame frame = new JFrame();
	JButton backButton = new JButton();
	public KumTorbası() {
		// TODO Auto-generated constructor stub
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.getContentPane().setBackground(Color.RED);
		frame.setLayout(null);
		frame.setVisible(true);		
		frame.setLocationRelativeTo(null);
		backButton.setBackground(Color.white);
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backButton.setText("Back");
		frame.add(backButton);		
			backButton.setBounds(0,0 , 100,20);
			backButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
				StartPage startPage = new StartPage();
			
				frame.dispose();
					
				}
			});
	}
	
}
