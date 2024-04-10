package Model;

public class TrueOrFalseQuestion implements Question{
    private String questionText;
    private boolean answer;

    public TrueOrFalseQuestion(String questionText, boolean answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    @Override
    public String displayQuestion() {
        return "True or False: " + questionText;
    }

    // Getters and setters
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}