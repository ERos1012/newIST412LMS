package Model;

public class AssignmentSubmission {
    private int id;
    private int studentId;
    private int assignmentId;
    private int courseId;
    private String textSubmission;

    // Constructor
    public AssignmentSubmission(int id, int studentId, int assignmentId, int courseId, String textSubmission) {
        this.id = id;
        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.courseId = courseId;
        this.textSubmission = textSubmission;
    }

    public AssignmentSubmission() {

    }

    // Getter and Setter methods for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter methods for other fields
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTextSubmission() {
        return textSubmission;
    }

    public void setTextSubmission(String textSubmission) {
        this.textSubmission = textSubmission;
    }

}
