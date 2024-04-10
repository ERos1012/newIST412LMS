package Controller;

import Model.Assignment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.DatabaseManager;

public class AssignmentController {
    private DatabaseManager dbManager;

    public AssignmentController() {
        // Initialize the DatabaseManager with the path to your database file
        this.dbManager = new DatabaseManager("assignment_database.db");
    }

    public void addAssignment(Assignment assignment) {
        String sql = "INSERT INTO assignments(name, description, due_date) VALUES(?,?,?)";
        dbManager.executeUpdate(sql, assignment.getName(), assignment.getDescription(), assignment.getDueDate());
        System.out.println("Assignment added successfully.");
    }

    public void removeAssignment(int id) {
        String sql = "DELETE FROM assignments WHERE id = ?";
        dbManager.executeUpdate(sql, id);
        System.out.println("Assignment removed successfully.");
    }

    public void updateAssignment(Assignment assignment) {
        String sql = "UPDATE assignments SET name = ?, description = ?, due_date = ? WHERE id = ?";
        dbManager.executeUpdate(sql, assignment.getName(), assignment.getDescription(), assignment.getDueDate(),
                assignment.getId());
        System.out.println("Assignment updated successfully.");
    }

    public Assignment getAssignment(int id) {
        String sql = "SELECT * FROM assignments WHERE id = ?";
        try {
            ResultSet rs = dbManager.executeQuery(sql, id);
            if (rs != null && rs.next()) {
                return new Assignment(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("due_date")
                // Assuming an ID field or constructor in Assignment for ID
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching assignment: " + e.getMessage());
        }
        return null;
    }

    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments";
        try {
            ResultSet rs = dbManager.executeQuery(sql);
            while (rs != null && rs.next()) {
                assignments.add(new Assignment(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("due_date")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching assignments: " + e.getMessage());
        }
        return assignments;
    }
}
