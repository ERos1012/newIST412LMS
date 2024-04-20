package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Quiz class represents a quiz in the system, capable of containing various
 * types of questions.
 */
public class Quiz {
    private int id;
    private int courseId;
    private String name;
    private String dueDate;
    private List<Question> questions;

    /**
     * Constructs a new Quiz object with the specified details.
     */
    public Quiz(int id, int courseId, String name, String dueDate, List<Question> questions) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
        this.dueDate = dueDate;
        this.questions = (questions == null) ? new ArrayList<>() : questions; // Ensure list is never null
    }

    // Getter methods remain unchanged, but getQuestions now returns List<Question>
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Gets the ID of the quiz.
     * 
     * @return The ID of the quiz.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the ID of the course associated with the quiz.
     * 
     * @return The ID of the course.
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Gets the name of the quiz.
     * 
     * @return The name of the quiz.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the due date of the quiz.
     * 
     * @return The due date of the quiz.
     */
    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", name='" + name + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", questionsCount=" + questions.size() +
                '}';
    }

    // Remove or finalize the setId method based on your requirements
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = (questions == null) ? new ArrayList<>() : questions;
    }

}
