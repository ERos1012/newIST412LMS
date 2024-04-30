package Controller;

import Model.Answer;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/412lms?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "$Qqhollowpsu45";

    public AnswerController() {
        // Optionally initialize database connection
    }

    /**
     * Saves an answer to the database.
     * @param answer The answer object to be saved.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean saveAnswer(Answer answer) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO answers (questionId, studentId, answerText, isCorrect) VALUES (?, ?, ?, ?)")) {
            pstmt.setInt(1, answer.getQuestionId());
            pstmt.setInt(2, answer.getStudentId());
            pstmt.setString(3, answer.getAnswerText());
            pstmt.setBoolean(4, answer.isCorrect());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error saving answer: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all answers for a specific question.
     * @param questionId The ID of the question.
     * @return a list of answers.
     */
    public List<Answer> getAnswersByQuestionId(int questionId) {
        List<Answer> answers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM answers WHERE questionId = ?")) {
            pstmt.setInt(1, questionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    answers.add(new Answer(
                            rs.getInt("answerId"),
                            rs.getInt("questionId"),
                            rs.getInt("studentId"),
                            rs.getString("answerText"),
                            rs.getBoolean("isCorrect")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving answers: " + e.getMessage());
        }
        return answers;
    }

    /**
     * Evaluates and updates the correctness of an answer.
     * @param answer The answer to be evaluated.
     * @param correctAnswer The correct answer text.
     * @return true if the answer is correct, false otherwise.
     */
    public boolean evaluateAndUpdateAnswer(Answer answer, String correctAnswer) {
        answer.setCorrect(answer.getAnswerText().equalsIgnoreCase(correctAnswer));
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE answers SET isCorrect = ? WHERE answerId = ?")) {
            pstmt.setBoolean(1, answer.isCorrect());
            pstmt.setInt(2, answer.getAnswerId());
            pstmt.executeUpdate();
            return answer.isCorrect();
        } catch (SQLException e) {
            System.err.println("Error updating answer correctness: " + e.getMessage());
            return false;
        }
    }
}
