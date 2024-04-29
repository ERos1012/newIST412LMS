package Controller;

import Model.Assignment;
import Model.AssignmentSubmission;
import Model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class AssignmentController {

    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Darkless@1228";
    private static final String DATABASE_NAME = "412lms";
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

    public void submitAssignment(AssignmentSubmission submission) {
        // SQL query to insert the assignment submission
        String sql = "INSERT INTO AssignmentSubmissions (studentId, assignmentId, courseId, submissionText) VALUES (?, ?, ?, ?)";

        // Connection to the database
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Set the parameters for the SQL query
            pstmt.setInt(1, submission.getStudentId());
            pstmt.setInt(2, submission.getAssignmentId());
            pstmt.setInt(3, submission.getCourseId());
            pstmt.setString(4, submission.getTextSubmission());

            // Execute the SQL query
            pstmt.executeUpdate();
            System.out.println("Assignment submitted successfully.");

        } catch (SQLException e) {
            // Handle any SQL exceptions
            System.out.println("Error submitting assignment: " + e.getMessage());
        }
    }


    public boolean hasExistingSubmission(int studentId, int assignmentId, int courseId) {
        String sql = "SELECT COUNT(*) FROM AssignmentSubmissions WHERE studentId = ? AND assignmentId = ? AND courseId = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, assignmentId);
            pstmt.setInt(3, courseId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking existing submission: " + e.getMessage());
        }
        return false;
    }

    public List<AssignmentSubmission> getAssignmentSubmissions(int assignmentId, int courseId) {
        List<AssignmentSubmission> submissions = new ArrayList<>();
        // SQL query to fetch assignment submissions based on the given assignment ID and course ID
        String sql = "SELECT * FROM AssignmentSubmissions WHERE assignmentId = ? AND courseId = ?";

        // Establish a connection to the database
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Set the parameters for the SQL query
            pstmt.setInt(1, assignmentId);
            pstmt.setInt(2, courseId);

            // Execute the query and iterate over the results
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Create an AssignmentSubmission object for each row in the result set
                    AssignmentSubmission submission = new AssignmentSubmission(
                            rs.getInt("id"),
                            rs.getInt("studentId"),
                            rs.getInt("assignmentId"),
                            rs.getInt("courseId"),
                            rs.getString("submissionText")
                    );
                    // Add the submission to the list
                    submissions.add(submission);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching assignment submissions: " + e.getMessage());
        }
        return submissions;
    }

}
