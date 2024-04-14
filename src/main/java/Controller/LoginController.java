package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "$Qqhollowpsu45";
    private static final String DATABASE_NAME = "your_database_name";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME;

    public boolean matchUsernamePassword(String username, String password) {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL JDBC Driver not found.");
            return false;
        }

        String sql = "SELECT id FROM Authentication WHERE username = ? AND password = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Username and password match, return true
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error matching username and password: " + ex.getMessage());
        }
        // Username and password do not match, or an error occurred
        return false;
    }

    public static void main(String[] args) {
        // Example usage
        LoginController loginController = new LoginController();
        String username = "example_username";
        String password = "example_password";
        boolean isValid = loginController.matchUsernamePassword(username, password);
        if (isValid) {
            System.out.println("Username and password are valid.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}

