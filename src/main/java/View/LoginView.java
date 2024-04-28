package View;

import Controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ActionListener loginListener; // Declaration of loginListener field
    private LoginController loginController;

    public LoginView(LoginController loginController) {
        this.loginController = loginController;

        // Initialize components
        usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Add components to the panel
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get username and password from text fields
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());

                // Call loginController method to check authentication
                if (loginController.matchUsernamePassword(username, password)) {
                    // Retrieve the user type and user ID from the LoginController
                    String userType = loginController.getUserType();
                    int userId = loginController.getUserId(); // Retrieve the user ID

                    // Display appropriate message based on user type
                    if (userType.equals("student")) {
                        JOptionPane.showMessageDialog(LoginView.this, "Student successfully logged in", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                    } else if (userType.equals("teacher")) {
                        JOptionPane.showMessageDialog(LoginView.this, "Teacher successfully logged in", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                    }

                    // Notify listener, perhaps passing the userId or doing something with it here if needed
                    if (loginListener != null) {
                        loginListener.actionPerformed(e);
                    }
                } else {
                    // Display error message for invalid login
                    JOptionPane.showMessageDialog(LoginView.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Setter for login listener
    public void setLoginListener(ActionListener listener) {
        this.loginListener = listener;
    }
}
