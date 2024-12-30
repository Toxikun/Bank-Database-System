import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class EmployeeAccount {
    private JFrame frame = new JFrame("Employee Account Management");
    private JTable viewJTable = new JTable();
    private JTable loanTable = new JTable();
    private JButton approveButton = new JButton("Approve Loan");
    private JButton denyButton = new JButton("Deny Loan");
    private JButton insertButton = new JButton("Insert Money");
    private JButton deleteButton = new JButton("Delete User");
    private JTextField moneyTextField = new JTextField(10);
    private JTextField userIDField = new JTextField(10);

    public EmployeeAccount() {
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.RED);
        frame.setBounds(100, 100, 1200, 800);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        setupComponents();
        frame.setVisible(true);
    }

    private void setupComponents() {
        JLabel lblNewLabel = new JLabel("ADMIN PAGE");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(400, 10, 200, 30);
        frame.add(lblNewLabel);

        setupUserTable();
        setupLoanTable();
        setupActionButtons();
        setupTextFieldAndLabels();
    }

    private void setupUserTable() {
        JLabel lblUserDetails = new JLabel("User Details:");
        lblUserDetails.setForeground(Color.WHITE);
        lblUserDetails.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblUserDetails.setBounds(50, 50, 200, 30);
        frame.add(lblUserDetails);

        viewJTable.setBounds(50, 90, 1100, 150);
        viewJTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        viewJTable.setRowHeight(30);
        viewJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.add(viewJTable);

        viewJTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showLoanDetails();
            }
        });

        populateUserTable();
    }

    private void setupLoanTable() {
        JLabel lblLoanDetails = new JLabel("Loan Details:");
        lblLoanDetails.setForeground(Color.WHITE);
        lblLoanDetails.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblLoanDetails.setBounds(50, 250, 200, 30);
        frame.add(lblLoanDetails);

        loanTable.setBounds(50, 290, 1100, 150);
        loanTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
        loanTable.setRowHeight(30);
        loanTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.add(loanTable);
    }

    private void setupActionButtons() {
        approveButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        approveButton.setBounds(50, 450, 200, 30);
        approveButton.addActionListener(this::approveLoan);
        frame.add(approveButton);

        denyButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        denyButton.setBounds(260, 450, 200, 30);
        denyButton.addActionListener(this::denyLoan);
        frame.add(denyButton);

        insertButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        insertButton.setBounds(50, 500, 200, 30);
        insertButton.addActionListener(this::insertMoney);
        frame.add(insertButton);

        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        deleteButton.setBounds(260, 500, 200, 30);
        deleteButton.addActionListener(this::deleteUser);
        frame.add(deleteButton);

        // Initially disable buttons until a loan is selected
        approveButton.setEnabled(false);
        denyButton.setEnabled(false);
    }

    private void setupTextFieldAndLabels() {
        JLabel lblInsertMoney = new JLabel("Insert Money:");
        lblInsertMoney.setForeground(Color.WHITE);
        lblInsertMoney.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblInsertMoney.setBounds(50, 550, 200, 30);
        frame.add(lblInsertMoney);

        moneyTextField.setBounds(260, 550, 200, 30);
        frame.add(moneyTextField);

        JLabel lblUserId = new JLabel("User ID:");
        lblUserId.setForeground(Color.WHITE);
        lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblUserId.setBounds(50, 600, 200, 30);
        frame.add(lblUserId);

        userIDField.setBounds(260, 600, 200, 30);
        frame.add(userIDField);
    }

    private void populateUserTable() {
        String sql = "SELECT* FROM Customer"; // Adjust the SQL query based on your database schema
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "orkun123");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Cust_ID", "Name","Surname", "Email", "Balance" }, 0);
            while (rs.next()) {
                int id = rs.getInt("Cust_ID");
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                String surname = rs.getString("Surname");
                Double BALANCE = rs.getDouble("Balance");
                tableModel.addRow(new Object[]{id, name, email,surname,BALANCE});
            }
            viewJTable.setModel(tableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    private void showLoanDetails() {
        int selectedRow = viewJTable.getSelectedRow();
        if (selectedRow != -1) {
            String userId = viewJTable.getValueAt(selectedRow, 0).toString();
            // Fetch loan details for the selected user and populate the loanTable
            String sql = "SELECT LoanAmount, Account_No FROM transaction_view WHERE Account_No = ?";
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "orkun123");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userId);
                ResultSet rs = pstmt.executeQuery();
                DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"LoanAmount", "Account_No"}, 0);
                boolean hasLoans = false;
                while (rs.next()) {
                    hasLoans = true;
                    int loanAmount = rs.getInt("LoanAmount");
                    int accountNo = rs.getInt("Account_No");
                    tableModel.addRow(new Object[]{loanAmount, accountNo});
                }
                loanTable.setModel(tableModel);
                approveButton.setEnabled(hasLoans);
                denyButton.setEnabled(hasLoans);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No user selected.");
        }
    }


    private void approveLoan(ActionEvent e) {
        int row = loanTable.getSelectedRow();
        if (row != -1) {
            String loanAmount = loanTable.getValueAt(row, 0).toString();
            // Implementation to approve the loan
            JOptionPane.showMessageDialog(frame, "Loan of " + loanAmount + " Approved");
            // Update LoanStatus in database, refresh tables etc.
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a loan to approve.");
        }
    }
    private void denyLoan(ActionEvent e) {
        int row = loanTable.getSelectedRow();
        if (row != -1) {
            int accountNo = Integer.parseInt(loanTable.getValueAt(row, 1).toString());
            String sql = "DELETE FROM transaction WHERE Account_No = ?";
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "orkun123");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, accountNo);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(frame, "Loan denied successfully.");
                    showLoanDetails(); // Refresh loan details
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error denying loan: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a loan to deny.");
        }
    }


    private void insertMoney(ActionEvent e) {
        if (!moneyTextField.getText().isEmpty() && !userIDField.getText().isEmpty()) {
            double amount = Double.parseDouble(moneyTextField.getText());
            int userId = Integer.parseInt(userIDField.getText());
            String sql = "UPDATE project.Account SET Balance = Balance + ? WHERE Cust_ID = ?";
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "orkun123");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, amount);
                pstmt.setInt(2, userId);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(frame, "Money inserted successfully.");
                    populateUserTable(); // Optionally refresh user table
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to insert money.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please fill all fields.");
        }
    }

    private void deleteUser(ActionEvent e) {
        int row = viewJTable.getSelectedRow();
        if (row != -1) {
            int userId = Integer.parseInt(viewJTable.getValueAt(row, 0).toString());
            String sql = "DELETE FROM Customer WHERE Cust_ID = ?";
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "orkun123");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(frame, "User deleted successfully.");
                    populateUserTable(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(frame, "No user found with the specified ID.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error deleting user: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to delete.");
        }
    }

  
}