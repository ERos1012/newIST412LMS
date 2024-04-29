package View;

import java.util.List;
import Controller.GradeController;
import Model.Grade;


public class GradeViewHandler {
    private GradeController gradeController;

    public GradeViewHandler() {
        gradeController = new GradeController();
    }

   public void displayStudentGrades(int studentId) {
    // Retrieve grades for the student
    List<Grade> grades = gradeController.getGradesForStudent(studentId);
    
    // Display the grades
    System.out.println("Grades for student with ID " + studentId + ":");
    for (Grade grade : grades) {
        System.out.println(grade.getGrade());
    }
}

}
