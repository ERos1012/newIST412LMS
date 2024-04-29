package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Grade;

public class GradeController {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Darkless@1228";
    private static final String DATABASE_NAME = "412lms";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME + "?useSSL=false";

    public void assignGrade(int studentId, int courseId, int gradeValue) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO grades (student_id, course_id, grade) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, studentId);
                pstmt.setInt(2, courseId);
                pstmt.setInt(3, gradeValue);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Grade assigned successfully.");
                } else {
                    System.out.println("Failed to assign grade.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Grade> getGradesForStudent(int studentId) {
        List<Grade> grades = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM grades WHERE student_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, studentId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Grade grade = new Grade(
                                rs.getInt("id"),
                                rs.getInt("student_id"),
                                rs.getInt("course_id"),
                                rs.getInt("grade")
                        );
                        grades.add(grade);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }
}