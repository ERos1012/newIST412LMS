package Factory;

import Model.*;

public class TrueOrFalseQuestionCreator extends QuestionCreator {
    @Override
    public Question createQuestion() {
        return new TrueOrFalseQuestion("Is the sky blue?", true);
    }
}