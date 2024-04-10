package Factory;

import Model.Question;

/**
 * The abstract creator that declares the factory method.
 */
public abstract class QuestionCreator {
    public abstract Question createQuestion();
}