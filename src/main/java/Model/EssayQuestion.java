package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Represents an essay question, which requires a text-based answer.
 */
public class EssayQuestion extends Question {
    public EssayQuestion(String text) {
        super(text);
    }

    @Override
    public void addQuestionToDatabase(Connection con, int quizId) throws SQLException {
        String sql = "INSERT INTO quiz_questions (quiz_id, question_text, type) VALUES (?, ?, 'Essay');";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, quizId);
            pstmt.setString(2, text);
            pstmt.executeUpdate();
        }
    }
}