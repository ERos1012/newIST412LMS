package Model;

/**
 * Represents an essay question, which requires a text-based answer.
 */
public class EssayQuestion implements Question {
    private String questionText;
    private String modelAnswer; // Optional, could be used for grading or as a sample answer

    /**
     * Constructs an EssayQuestion with the specified question text.
     * @param questionText The text of the essay question.
     */
    public EssayQuestion(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Constructs an EssayQuestion with both question text and a model answer.
     * @param questionText The text of the essay question.
     * @param modelAnswer The model answer for the essay question.
     */
    public EssayQuestion(String questionText, String modelAnswer) {
        this(questionText);
        this.modelAnswer = modelAnswer;
    }

    /**
     * Returns the question text.
     * @return The text of the question.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Returns the model answer.
     * @return The model answer of the question.
     */
    public String getModelAnswer() {
        return modelAnswer;
    }

    /**
     * Implements displayQuestion from the Question interface.
     * @return The question text.
     */
    @Override
    public String displayQuestion() {
        return questionText;
    }
}
