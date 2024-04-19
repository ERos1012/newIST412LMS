package Model;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Question {
    protected String text;

    public Question(String text) {
        this.text = text;
    }

    public abstract void addQuestionToDatabase(Connection con, int quizId) throws SQLException;
}