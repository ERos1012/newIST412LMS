package Controller;

import Model.Course;
import Model.Quiz;

import java.util.List;

/**
 * The QuizController class manages quizzes in the system.
 */
public class QuizController {

    /**
     * Adds a new quiz to the system.
     */
    public Quiz addQuiz(Quiz quiz) {
        System.out.println("Quiz added: " + quiz.getName() + " " + quiz.getId() + " " + quiz.getDueDate() + " " + quiz.getQuestions());
        return quiz;
    }

    /**
     * Removes an existing quiz from the system.
     */
    public void removeQuiz(Quiz quiz) {
        System.out.println("Quiz removed: " + quiz.getId() + " " + quiz.getName() + " " + quiz.getDueDate() + " " + quiz.getQuestions());
    }

    /**
     * Updates an existing quiz in the system.
     */
    public void updateQuiz(Quiz quiz) {
        System.out.println("Quiz updated: " + quiz.getName() + " " + quiz.getId() + " " + quiz.getDueDate() + " " + quiz.getQuestions());
    }

    /**
     * Views details of a specific quiz.
     */
    public Quiz viewQuiz(Quiz quiz) {
        if (quiz == null) {
            return new Quiz(0, 0, 0, "Default Quiz", "Default Due Date", List.of()); 
        }
        // logic for retrieving and returning a Quiz object
        return quiz;
    }

    /**
     * Gets a list of all quizzes for a course
     * @param course
     * @return
     */
    public List<Quiz> getAllQuizzes(Course course){
        return List.of();
    }

    /**
     * Assigns a grade to a quiz for a student.
     * @param studentId
     * @param courseId
     * @param quizId
     * @param grade
     */
    public void gradeQuiz(int studentId, int courseId, int quizId, int grade)
    {
        System.out.println("Quiz graded: --> " + "Student number " + studentId + " has a grade of " + grade);
    }
}
