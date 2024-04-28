package Controller;

import java.sql.*;

public class LoginController {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "jisquz-hatdod-1gyqVu";
    private static final String DATABASE_NAME = "412lms";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME + "?useSSL=false";

    private String userType = ""; // Field to keep track of user type (teacher or student)
    private int userId = -1; // Field to keep track of the logged-in user's ID

    private boolean checkTeacherCredentials(String username, String password) {
        String query = "SELECT id, username, password FROM Authentication WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userType = "teacher";
                    userId = resultSet.getInt("id"); // Store the user ID
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkStudentCredentials(String username, String password) {
        String query = "SELECT id, username, password FROM StudentAuthentication WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userType = "student";
                    userId = resultSet.getInt("id"); // Store the user ID
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean matchUsernamePassword(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found");
            e.printStackTrace();
            return false;
        }

        return checkTeacherCredentials(username, password) || checkStudentCredentials(username, password);
    }

    public String getUserType() {
        return userType;
    }

    public int getUserId() {
        return userId;
    }
}
