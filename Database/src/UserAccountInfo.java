import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserAccountInfo {

    JFrame frame = new JFrame();

    public UserAccountInfo() {
        // TODO Auto-generated constructor stub

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 1000, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.RED);

        JLabel lblNewLabel = new JLabel("Account Info");
        lblNewLabel.setBounds(151, 10, 400, 29);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBackground(Color.BLACK);
        frame.add(lblNewLabel);

        JLabel lblNamesurname = new JLabel("Name:"+LoginUser.name);
        lblNamesurname.setBounds(37, 49, 400, 29);
        lblNamesurname.setForeground(Color.WHITE);
        lblNamesurname.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNamesurname.setBackground(Color.BLACK);
        frame.add(lblNamesurname);

        JTextField txtName = new JTextField(LoginUser.name);
        txtName.setBounds(400, 49, 200, 29);
        frame.add(txtName);

        JButton btnUpdateName = new JButton("Update");
        btnUpdateName.setBounds(620, 49, 100, 29);
        btnUpdateName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUserAccountInfo("name", txtName.getText());
                lblNamesurname.setText("Name:" + txtName.getText());
            }
        });
        frame.add(btnUpdateName);

        JLabel lblSurname = new JLabel("Surname:"+LoginUser.surname);
        lblSurname.setForeground(Color.WHITE);
        lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblSurname.setBackground(Color.BLACK);
        lblSurname.setBounds(37, 88, 200, 29);
        frame.add(lblSurname);

        JTextField txtSurname = new JTextField(LoginUser.surname);
        txtSurname.setBounds(400, 88, 200, 29);
        frame.add(txtSurname);

        JButton btnUpdateSurname = new JButton("Update");
        btnUpdateSurname.setBounds(620, 88, 100, 29);
        btnUpdateSurname.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUserAccountInfo("surname", txtSurname.getText());
                lblSurname.setText("Surname:" + txtSurname.getText());
            }
        });
        frame.add(btnUpdateSurname);

        JLabel lblEmail = new JLabel("E-mail:"+LoginUser.email);
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblEmail.setBackground(Color.BLACK);
        lblEmail.setBounds(37, 128, 400, 29);
        frame.add(lblEmail);

        JTextField txtEmail = new JTextField(LoginUser.email);
        txtEmail.setBounds(400, 128, 200, 29);
        frame.add(txtEmail);

        JButton btnUpdateEmail = new JButton("Update");
        btnUpdateEmail.setBounds(620, 128, 100, 29);
        btnUpdateEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUserAccountInfo("email", txtEmail.getText());
                lblEmail.setText("E-mail:" + txtEmail.getText());
            }
        });
        frame.add(btnUpdateEmail);

        JLabel lblAdress = new JLabel("Adress:"+LoginUser.adress);
        lblAdress.setForeground(Color.WHITE);
        lblAdress.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblAdress.setBackground(Color.BLACK);
        lblAdress.setBounds(37, 163, 400, 29);
        frame.add(lblAdress);

        JTextField txtAdress = new JTextField(LoginUser.adress);
        txtAdress.setBounds(400, 163, 200, 29);
        frame.add(txtAdress);

        JButton btnUpdateAdress = new JButton("Update");
        btnUpdateAdress.setBounds(620, 163, 100, 29);
        btnUpdateAdress.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUserAccountInfo("address", txtAdress.getText());
                lblAdress.setText("Adress:" + txtAdress.getText());
            }
        });
        frame.add(btnUpdateAdress);

        JLabel lblPhoneNumber = new JLabel("Phone NO:"+LoginUser.mobileNo);
        lblPhoneNumber.setForeground(Color.WHITE);
        lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblPhoneNumber.setBackground(Color.BLACK);
        lblPhoneNumber.setBounds(37, 202, 400, 29);
        frame.add(lblPhoneNumber);

        JTextField txtPhoneNumber = new JTextField(LoginUser.mobileNo);
        txtPhoneNumber.setBounds(400, 202, 200, 29);
        frame.add(txtPhoneNumber);

        JButton btnUpdatePhoneNumber = new JButton("Update");
        btnUpdatePhoneNumber.setBounds(620, 202, 100, 29);
        btnUpdatePhoneNumber.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUserAccountInfo("mobile_no", txtPhoneNumber.getText());
                lblPhoneNumber.setText("Phone NO:" + txtPhoneNumber.getText());
            }
        });
        frame.add(btnUpdatePhoneNumber);

        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserAccountMenu userAccountMenu = new UserAccountMenu();
                frame.dispose();
            }
        });
        btnNewButton.setBounds(0, 0, 85, 21);
        frame.add(btnNewButton);
    }

    private void updateUserAccountInfo(String field, String value) {
        String url = "jdbc:mysql://localhost:3306/project";
        String username = "root";
        String password = "orkun123";

        String sql = "UPDATE Customer SET " + field + " = ? WHERE Cust_ID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, value);
            pstmt.setString(2, LoginUser.userID); 

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "User account info updated successfully.","",JOptionPane.DEFAULT_OPTION);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


