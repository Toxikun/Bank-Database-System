import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Transactions {
	
	JFrame frame = new JFrame();
public Transactions() {
	frame.setVisible(true);
	frame.setBounds(100, 100, 450, 300);
	
	frame.getContentPane().setBackground(Color.RED);	
	frame.setLayout(null);;
	
	JButton btnNewButton = new JButton("Send Money");
	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SendMoney sendMoney = new SendMoney();
			frame.dispose();
		}
	});
	btnNewButton.setBounds(30, 35, 189, 49);
	frame.add(btnNewButton);
	
		
	JButton btnLoan = new JButton("Loan");
	btnLoan.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			CustomerLoan customerLoan = new CustomerLoan();
			frame.dispose();
		}
	});
	btnLoan.setFont(new Font("Tahoma", Font.PLAIN, 24));
	btnLoan.setBounds(30, 155, 189, 49);
	frame.add(btnLoan);
	
	JButton btnNewButton_1 = new JButton("Back");
	btnNewButton_1.setBounds(0, 0, 85, 21);
	btnNewButton_1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		UserAccountMenu userAccountMenu = new UserAccountMenu();
		frame.dispose();
		}
	});
	frame.add(btnNewButton_1);
}

}
