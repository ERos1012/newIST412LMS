package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ActionListener loginListener; // Declaration of loginListener field

    public LoginView() {
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
                // Simulate login (Replace this with your actual login logic)
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                if (isValidLogin(username, password)) {
                    // If login successful, notify listener
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

    // Simulates login validation
    private boolean isValidLogin(String username, String password) {
        // Replace this with your actual login logic
        return username.equals("admin") && password.equals("password");
    }
}
