package Controller;

import Model.Assignment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentController {
    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "$Qqhollowpsu45";
    private static final String DATABASE_NAME = "412 LMS"; // Replace "your_database_name" with your actual database name
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME;

    public void addAssignment(Assignment assignment) {
        String sql = "INSERT INTO Assignments(name, description, dueDate) VALUES(?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, assignment.getName());
            pstmt.setString(2, assignment.getDescription());
            pstmt.setString(3, assignment.getDueDate());
            pstmt.executeUpdate();
            System.out.println("Assignment added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding assignment: " + e.getMessage());
        }
    }

    public void removeAssignment(int id) {
        String sql = "DELETE FROM Assignments WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Assignment removed successfully.");
        } catch (SQLException e) {
            System.out.println("Error removing assignment: " + e.getMessage());
        }
    }

    public void updateAssignment(Assignment assignment) {
        String sql = "UPDATE Assignments SET name = ?, description = ?, dueDate = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, assignment.getName());
            pstmt.setString(2, assignment.getDescription());
            pstmt.setString(3, assignment.getDueDate());
            pstmt.setInt(4, assignment.getId());
            pstmt.executeUpdate();
            System.out.println("Assignment updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating assignment: " + e.getMessage());
        }
    }

    public Assignment getAssignment(int id) {
        String sql = "SELECT * FROM Assignments WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Assignment(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("dueDate")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching assignment: " + e.getMessage());
        }
        return null;
    }

    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM Assignments";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String dueDate = rs.getString("dueDate");
                assignments.add(new Assignment(id, name, description, dueDate));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching assignments: " + e.getMessage());
        }
        return assignments;
    }

    public static void main(String[] args) {
        AssignmentController controller = new AssignmentController();

        // Example: Print fetched assignments
        List<Assignment> assignments = controller.getAllAssignments();
        for (Assignment assignment : assignments) {
            System.out.println("ID: " + assignment.getId() + ", Name: " + assignment.getName() + ", Due Date: " + assignment.getDueDate()
                    + ", Description: " + assignment.getDescription());
        }
    }
}
