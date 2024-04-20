package Model;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MultipleChoiceQuestion extends Question {
    private String choiceA, choiceB, choiceC, choiceD, correctAnswer;

    public MultipleChoiceQuestion(String text, String choiceA, String choiceB, String choiceC, String choiceD, String correctAnswer) {
        super(text);
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void addQuestionToDatabase(Connection con, int quizId) throws SQLException {
        String sql = "INSERT INTO quiz_questions (quiz_id, question_text, type, choice_a, choice_b, choice_c, choice_d, correct_answer) VALUES (?, ?, 'Multiple Choice', ?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, quizId);
            pstmt.setString(2, text);
            pstmt.setString(3, choiceA);
            pstmt.setString(4, choiceB);
            pstmt.setString(5, choiceC);
            pstmt.setString(6, choiceD);
            pstmt.setString(7, correctAnswer);
            pstmt.executeUpdate();
        }
    }

    @Override
    public String getText() {
        return text;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    
}