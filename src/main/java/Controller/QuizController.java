package Controller;

import Model.Question;
import Model.Quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;
/**
 * The QuizController class manages quizzes in the system.
 */
public class QuizController {
    private Map<String, List<String>> quizzes;
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "$Qqhollowpsu45";
    private static final String DATABASE_NAME = "412 LMS";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME;

    public QuizController() {
        quizzes = new HashMap<>();
        quizzes.put("Quiz 1: Math Basics", List.of("What is 2+2?", "Solve for x in 2x=8."));
        quizzes.put("Quiz 2: World History", List.of("Who discovered America?", "Name the year when World War II ended."));
    }


    /**
     * Adds a new quiz to the system.
     */
    public Quiz addQuiz(Quiz quiz) {
        final String sql = "INSERT INTO quizzes (name, due_date) VALUES (?, ?);";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, quiz.getName());
            pstmt.setString(2, quiz.getDueDate());
            pstmt.executeUpdate();
    
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    quiz.setId(generatedKeys.getInt(1)); // Assuming 'id' is auto-generated
                }
            }
    
            addQuizQuestions(quiz.getId(), quiz.getQuestions()); // Separate method to handle questions insertion
            return quiz;
        } catch (SQLException e) {
            System.err.println("Error adding quiz: " + e.getMessage());
            return null;
        }
    }
    
    private void addQuizQuestions(int quizId, List<Question> list) {
        final String sql = "INSERT INTO quiz_questions (quiz_id, question) VALUES (?, ?);";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (Question question : list) {
                pstmt.setInt(1, quizId);
                pstmt.setString(2, question.getText()); // Fix: Use question.getText() instead of question
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            System.err.println("Error adding quiz questions: " + e.getMessage());
        }
    }
    

    /**
     * Removes an existing quiz from the system.
     */
    public void removeQuiz(Quiz quiz) {
        System.out.println("Quiz removed: " + quiz.getId() + " " + quiz.getName() + " " + quiz.getDueDate() + " " + quiz.getQuestions());
    }

    /**
     * Updates an existing quiz in the system.
     */
    public void updateQuiz(Quiz quiz) {
        final String sql = "UPDATE quizzes SET name = ?, due_date = ? WHERE id = ?;";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, quiz.getName());
            pstmt.setString(2, quiz.getDueDate());
            pstmt.setInt(3, quiz.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Quiz updated successfully: " + quiz.getName());
            }
        } catch (SQLException e) {
            System.err.println("Error updating quiz: " + e.getMessage());
        }
    }
    
    /**
     * Views details of a specific quiz.
     */
    public Quiz viewQuiz(Quiz quiz) {
        if (quiz == null) {
            return new Quiz(0, 0, "Default Quiz", "2024-01-01", new ArrayList<>());
        }
        // logic for retrieving and returning a Quiz object
        return quiz;
    }

    /**
     * Gets a list of all quizzes for a course
     * @param course
     * @return
     */
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        // Include 'course_id' in your SQL query to match the Quiz constructor requirements
        final String sql = "SELECT id, course_id, name, due_date FROM quizzes;";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Fetching questions for each quiz ID using a hypothetical method 'getQuestionsForQuizId'
                List<Question> questions = getQuestionsForQuizId(rs.getInt("id"));
                // Now correctly using the constructor with all required fields
                Quiz quiz = new Quiz(
                    rs.getInt("id"),
                    rs.getInt("course_id"),
                    rs.getString("name"),
                    rs.getString("due_date"),
                    questions
                );
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching quizzes: " + e.getMessage());
        }
        return quizzes;
    }
    
    private List<Question> getQuestionsForQuizId(int quizId) {
        List<Question> questions = new ArrayList<>();
        final String questionSql = "SELECT question_text, correct_answer FROM quiz_questions WHERE quiz_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(questionSql)) {
            pstmt.setInt(1, quizId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Assuming the 'Question' class has a suitable constructor
                    questions.add(new Question(rs.getString("question_text"), rs.getString("correct_answer")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching questions for quiz: " + e.getMessage());
        }
        return questions;
    }
    

    /**
     * Assigns a grade to a quiz for a student
     */
    public void gradeQuiz(int studentId, int courseId, int quizId, int grade)
    {
        System.out.println("Quiz graded: --> " + "Student number " + studentId + " has a grade of " + grade);
    }
}
