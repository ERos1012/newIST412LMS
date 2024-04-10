package View;

import Controller.QuizController;
import Model.Quiz;
import Model.Question;
import Factory.QuestionCreator;
import Factory.MultipleChoiceQuestionCreator;

import java.util.ArrayList;
import java.util.List;

public class QuizApp {

    public static void main(String[] args) {
        QuizController quizController = new QuizController();

        QuestionCreator mcqCreator = new MultipleChoiceQuestionCreator();
        Question mcq = mcqCreator.createQuestion();


        // Add questions to a list
        List<Question> questions = new ArrayList<>();
        questions.add(mcq);

        Quiz newQuiz = new Quiz(1, 101, 100, "Intro to Java Quiz", "2024-05-01", questions);

        // Add the quiz to the system
        quizController.addQuiz(newQuiz);

        // View the added quiz
        Quiz viewedQuiz = quizController.viewQuiz(newQuiz);
        System.out.println("Viewed Quiz: " + viewedQuiz.getName());

        newQuiz = new Quiz(newQuiz.getId(), newQuiz.getCourseId(), 95, newQuiz.getName(), newQuiz.getDueDate(), newQuiz.getQuestions());
        quizController.updateQuiz(newQuiz);

        // Remove the quiz from the system
        quizController.removeQuiz(newQuiz);

        // Assign a grade to the quiz for a student (demonstrative)
        quizController.gradeQuiz(123, newQuiz.getCourseId(), newQuiz.getId(), 85);
    }
}
