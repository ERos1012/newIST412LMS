package Model;

public class Answer {
    private int answerId;        // Unique identifier for the answer
    private int questionId;      // The ID of the question being answered
    private int studentId;    // Identifier for the student who answered
    private String answerText;   // The actual answer text or choice provided by the student
    private boolean isCorrect;   // To store if the answer was correct, can be set after evaluation

    // Constructor
    public Answer(int answerId, int questionId, int studentId, String answerText, boolean isCorrect) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.studentId = studentId;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    // Getters and Setters
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    // Additional Methods
    public void evaluateAnswer(String correctAnswer) {
        // Simple evaluation logic, can be expanded for different question types
        this.isCorrect = this.answerText.equalsIgnoreCase(correctAnswer);
    }
}
