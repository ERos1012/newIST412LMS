package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TrueOrFalseQuestion extends Question {
    private String correctAnswer;

    public TrueOrFalseQuestion(String text, String correctAnswer) {
        super(text);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void addQuestionToDatabase(Connection con, int quizId) throws SQLException {
        String sql = "INSERT INTO quiz_questions (quiz_id, question_text, type, correct_answer) VALUES (?, ?, 'True/False', ?);";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, quizId);
            pstmt.setString(2, text);
            pstmt.setString(3, correctAnswer);
            pstmt.executeUpdate();
        }
    }

    @Override
    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getChoices() {
        return List.of("True", "False");
    }

    public int getQuestionId() {
        return 0;
    }
}