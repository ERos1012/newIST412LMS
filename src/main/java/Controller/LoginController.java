package Controller;

import java.sql.*;

public class LoginController {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "$Qqhollowpsu45";
    private static final String DATABASE_NAME = "412 LMS";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME;

    public boolean matchUsernamePassword(String username, String password) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found");
            e.printStackTrace();
            return false;
        }

        String query = "SELECT * FROM Authentication WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Username found, check if the password matches
                    if (resultSet.getString("password").equals(password)) {
                        return true; // Return true if the password matches
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions
        }
        return false; // Return false if no matching username or password found
    }
}

