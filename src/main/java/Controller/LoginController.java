package Controller;

import java.sql.*;

public class LoginController {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "$Qqhollowpsu45";
    private static final String DATABASE_NAME = "412lms";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME;

    private String userType = ""; // Field to keep track of user type (teacher or student)

    /**
     * Checks if the username and password match in the Authentication table.
     * If a match is found, sets the user type to "teacher".
     */
    private boolean checkTeacherCredentials(String username, String password) {
        String query = "SELECT * FROM Authentication WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Matching credentials found in the Authentication table (teacher)
                    userType = "teacher";
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks if the username and password match in the StudentAuthentication table.
     * If a match is found, sets the user type to "student".
     */
    private boolean checkStudentCredentials(String username, String password) {
        String query = "SELECT * FROM StudentAuthentication WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Matching credentials found in the StudentAuthentication table (student)
                    userType = "student";
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Matches the username and password in either the Authentication or StudentAuthentication table.
     * Returns true if a match is found in either table.
     */
    public boolean matchUsernamePassword(String username, String password) {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found");
            e.printStackTrace();
            return false;
        }

        // First check for teacher credentials
        if (checkTeacherCredentials(username, password)) {
            return true;
        }

        // If not found, check for student credentials
        if (checkStudentCredentials(username, password)) {
            return true;
        }

        // Return false if no matching username or password found
        return false;
    }

    /**
     * Returns the user type (teacher or student) after a successful login.
     */
    public String getUserType() {
        return userType;
    }
}
