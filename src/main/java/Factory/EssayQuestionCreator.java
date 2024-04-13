package Factory;

import Model.EssayQuestion;
import Model.Question;

/**
 * A concrete creator class that produces EssayQuestion objects.
 */
public class EssayQuestionCreator extends QuestionCreator {

    private String questionText;
    private String modelAnswer;

    /**
     * Constructor for EssayQuestionCreator that requires only the question text.
     * @param questionText The text of the essay question.
     */
    public EssayQuestionCreator(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Constructor for EssayQuestionCreator that includes both question text and a model answer.
     * @param questionText The text of the essay question.
     * @param modelAnswer The model answer for the essay question.
     */
    public EssayQuestionCreator(String questionText, String modelAnswer) {
        this(questionText);
        this.modelAnswer = modelAnswer;
    }

    /**
     * Creates an EssayQuestion object with the provided text and model answer.
     * @return A new EssayQuestion instance.
     */
    @Override
    public Question createQuestion() {
        if (modelAnswer != null) {
            return new EssayQuestion(questionText, modelAnswer);
        } else {
            return new EssayQuestion(questionText);
        }
    }
}
