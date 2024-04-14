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
    private ActionListener loginListener;

    public LoginView() {
        // Initialize components
        usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Add components to the panel
        setLayout(new GridLayout(3, 3));
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Placeholder for alignment
        add(loginButton);

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                if (loginListener != null) {
                    loginListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Login"));
                }
            }
        });
    }

    public void setLoginListener(ActionListener listener) {
        this.loginListener = listener;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginController loginController = new LoginController();
            LoginView loginView = new LoginView();
            loginView.setLoginListener(e -> {
                String username = loginView.usernameTextField.getText();
                String password = new String(loginView.passwordField.getPassword());
                if (loginController.matchUsernamePassword(username, password)) {
                    JOptionPane.showMessageDialog(loginView, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(loginView, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            JFrame frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(loginView);
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setVisible(true);
        });
    }
}
