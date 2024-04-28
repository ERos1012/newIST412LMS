package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Question {
    protected String text;

    public Question(String text) {
        this.text = text;
    }

    public abstract void addQuestionToDatabase(Connection con, int quizId) throws SQLException;

    public abstract String getText();

    public abstract int getQuestionId();

    public abstract List<String> getChoices();
}