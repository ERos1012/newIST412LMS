package Model;

/**
 * The Question interface for all types of quiz questions.
 */
public interface Question {
    String displayQuestion();

    String getType();

    String getText();

    String getCorrectAnswer();

    void setAnswer(String answer);


}
