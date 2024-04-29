package Controller;

public class GradeSubmissionHandler {
    private GradeController gradeController;

    public GradeSubmissionHandler(GradeController gradeController) {
        this.gradeController = gradeController;
    }

    public void submitGrade(int studentId, int courseId, int grade) {
        gradeController.assignGrade(studentId, courseId, grade);
    }
}
