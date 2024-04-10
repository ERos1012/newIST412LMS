package Model;

/**
 * The Grade class represents a grade in the system.
 */
public class Grade {
    private int id;
    private int studentId;
    private int courseId;
    private int grade;

    /**
     * Constructs a new Grade object with the specified ID, student ID, course ID, and grade.
     * 
     * @param id The ID of the grade.
     * @param studentId The ID of the student associated with the grade.
     * @param courseId The ID of the course associated with the grade.
     * @param grade The numerical grade value.
     */
    public Grade(int id, int studentId, int courseId, int grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }
    
    // empty constructor
    public Grade() {
        this.id = 0;
        this.studentId = 0;
        this.courseId = 0;
        this.grade = 0;
    }
    
    /**
     * Gets the ID of the grade.
     * 
     * @return The ID of the grade.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets the ID of the student associated with the grade.
     * 
     * @return The ID of the student.
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Gets the ID of the course associated with the grade.
     * 
     * @return The ID of the course.
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Gets the numerical grade value.
     * 
     * @return The grade value.
     */
    public int getGrade() {
        return grade;
    }
}
