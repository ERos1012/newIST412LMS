package Controller;

import Model.Assignment;
import Model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentController {

    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "$Qqhollowpsu45";
    private static final String DATABASE_NAME = "412 LMS";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME;

    public static void addAssignment(Assignment assignment) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO Assignments(name, description, dueDate, courseId) VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, assignment.getName());
            pstmt.setString(2, assignment.getDescription());
            pstmt.setString(3, assignment.getDueDate());
            pstmt.setInt(4, assignment.getCourseId());
            pstmt.executeUpdate();
            System.out.println("Assignment added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding assignment: " + e.getMessage());
        }
    }

    public static void removeAssignment(int id) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE Assignments SET isActive = 0 WHERE id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("Assignment flagged as inactive.");
            }
        } catch (Exception e) {
            System.out.println("Error removing assignment: " + e.getMessage());
        }
    }

    public static void updateAssignment(Assignment assignment) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = "UPDATE Assignments SET name = ?, description = ?, dueDate = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, assignment.getName());
            pstmt.setString(2, assignment.getDescription());
            pstmt.setString(3, assignment.getDueDate());
            pstmt.setInt(4, assignment.getId());
            pstmt.executeUpdate();
            System.out.println("Assignment updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating assignment: " + e.getMessage());
        }
    }

    public static List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM Assignments";
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Assignment assignment = new Assignment(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("dueDate"),
                            rs.getBoolean("isActive"),
                            rs.getInt("courseId")
                    );
                    assignments.add(assignment);
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching assignments: " + e.getMessage());
        }
        return assignments;
    }

//    // Main method for testing
//    public static void main(String[] args) {
//        // Example: Read all assignments
//        readAllAssignments();
//
//        // Example: Add new assignment
//        Assignment newAssignment = new Assignment("New Assignment", "Description", "2024-04-12");
//        addAssignment(newAssignment);
//
//        // Example: Read all assignments after adding
//        readAllAssignments();
//
//        // Example: Remove assignment
//        removeAssignment(1); // Assuming the assignment ID to remove is 1
//
//        // Example: Read all assignments after removing
//        readAllAssignments();
//
//        // Example: Update assignment
//        Assignment updatedAssignment = new Assignment(2, "Updated Assignment", "Updated Description", "2024-04-15");
//        updateAssignment(updatedAssignment);
//
//        // Example: Read all assignments after updating
//        readAllAssignments();
//    }

    public List<Assignment> getActiveAssignments(Course course) {
        List<Assignment> activeAssignments = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = ("SELECT * FROM Assignments WHERE isActive = 1 AND courseId = " + course.getId());
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Assignment assignment = new Assignment(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("dueDate"),
                            rs.getBoolean("isActive"),
                            rs.getInt("courseId")
                    );
                    activeAssignments.add(assignment);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching active assignments: " + e.getMessage());
        }
        return activeAssignments;
    }
    public Assignment getAssignment(int id) {
        String sql = "SELECT * FROM Assignments WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Assignment(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("dueDate"),
                            rs.getBoolean("isActive"),
                            rs.getInt("courseId")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching assignment: " + e.getMessage());
        }
        return null;
    }
}
