package Controller;

import Model.Message;
import Model.Student;
import Model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageController {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Darkless@1228";
    private static final String DATABASE_NAME = "412lms";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME + "?useSSL=false";

    public MessageController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }

    public void sendMessage(Message message) {
        String sql = "INSERT INTO Messages (sender_id, receiver_id, message, timestamp, sender_type, receiver_type) VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, message.getSenderId());
            pstmt.setInt(2, message.getReceiverId());
            pstmt.setString(3, message.getContent());
            pstmt.setTimestamp(4, message.getTimestamp());
            pstmt.setString(5, message.getSenderType());
            pstmt.setString(6, message.getReceiverType());  // Ensure this aligns with your updated Message model
            pstmt.executeUpdate();
            System.out.println("Message sent successfully.");
        } catch (SQLException e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }

    public Message viewMessage(int messageId) {
        final String sql = "SELECT * FROM Messages WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, messageId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Message(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        rs.getString("message"),
                        rs.getTimestamp("timestamp"),
                        rs.getString("sender_type"),
                        rs.getString("receiver_type")  // Handle retrieval of the receiver type
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error viewing message: " + e.getMessage());
        }
        return null;
    }

    public void deleteMessage(int messageId) {
        final String sql = "DELETE FROM Messages WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, messageId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Message deleted successfully: ID " + messageId);
            } else {
                System.out.println("No message found with ID: " + messageId);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting message: " + e.getMessage());
        }
    }

    public List<Message> getAllMessagesForUser(int userId, String userType) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM Messages WHERE receiver_id = ? AND receiver_type = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, userType);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    messages.add(new Message(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        rs.getString("message"),
                        rs.getTimestamp("timestamp"),
                        rs.getString("sender_type"),
                        rs.getString("receiver_type")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching messages: " + e.getMessage());
        }
        return messages;
    }

    public String getSenderName(int id, String senderType) {
        String username = null;
        String tableName;

        if (senderType.equalsIgnoreCase("teacher")) {
            tableName = "Authentication";
        } else if (senderType.equalsIgnoreCase("student")) {
            tableName = "StudentAuthentication";
        } else {
            throw new IllegalArgumentException("Invalid sender type. Sender type must be either 'teacher' or 'student'.");
        }

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT username FROM " + tableName + " WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        username = rs.getString("username");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the username (null if not found)
        return username;
    }

}
