package Controller;

import Model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseController {

    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Darkless@1228";
    private static final String DATABASE_NAME = "412lms";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME;

    public void addCourse(Course course) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String sql = "INSERT INTO Courses(name, program, instructor) VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, course.getName());
                pstmt.setString(2, course.getProgram());
                pstmt.setString(3, course.getInstructor());
                pstmt.executeUpdate();
                System.out.println("Course added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    public void removeCourse(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String sql = "UPDATE Courses SET isActive = 0 WHERE id = ?";
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    System.out.println("Course flagged as inactive.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error removing course: " + e.getMessage());
        }
    }

    public void updateCourse(Course course) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String sql = "UPDATE Courses SET name = ?, program = ?, instructor = ? WHERE id = ?";
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setString(1, course.getName());
                    pstmt.setString(2, course.getProgram());
                    pstmt.setString(3, course.getInstructor());
                    pstmt.setInt(4, course.getId());
                    pstmt.executeUpdate();
                    System.out.println("Course updated successfully.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String sql = "SELECT * FROM Courses";
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        Course course = new Course(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("program"),
                                rs.getString("instructor"),
                                rs.getBoolean("isActive") // Fetch the isActive column
                        );
                        courses.add(course);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching courses: " + e.getMessage());
        }
        return courses;
    }

    public List<Course> getActiveCourses() {
        List<Course> activeCourses = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String sql = "SELECT * FROM Courses WHERE isActive = 1";
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        Course course = new Course(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("program"),
                                rs.getString("instructor"),
                                rs.getBoolean("isActive")
                        );
                        activeCourses.add(course);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching active courses: " + e.getMessage());
        }
        return activeCourses;
    }

        public Course getCourse(int id) {
            String sql = "SELECT * FROM Courses WHERE id = ?";
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        Course course = new Course(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("program"),
                                rs.getString("instructor"),
                                rs.getBoolean("isActive")
                        );
                        return course;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error fetching course: " + e.getMessage());
            }
            return null;
        }

    }
