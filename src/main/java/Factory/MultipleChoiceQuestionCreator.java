package Factory;

import java.util.List;

import Model.*;

public class MultipleChoiceQuestionCreator extends QuestionCreator {
    @Override
    public Question createQuestion() {
        return new MultipleChoiceQuestion("What is the capital of France?", List.of("Paris", "London", "Berlin", "Madrid"));
    }
}