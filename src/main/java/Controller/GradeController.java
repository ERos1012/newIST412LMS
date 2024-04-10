package Controller;

import Model.Grade;

import java.util.ArrayList;
import java.util.List;

/**
 * The GradeController class manages grades in the system.
 */
public class GradeController {

    /**
     * Adds a new grade to the system.
     * @param grade
     */
    public void addGrade(Grade grade) {
    }

    /**
     * Removes an existing grade from the system.
     * @param grade
     */
    public void removeGrade(Grade grade) {
    }

    /**
     * Updates an existing grade in the system.
     * @param grade
     * @param newGradeValue
     */
    public void updateGrade(Grade grade, double newGradeValue) {
    }

    /**
     * Views details of a specific grade.
     */
    public Grade viewGrade(Grade grade) {
        // demo
       return grade;
    }

    /**
     * View a specific quiz grade for a particular student
     * @param studentId
     * @param courseID
     * @param quizId
     * @return
     */
    public int viewQuizGrade(int studentId, int courseID, int quizId)
    {
        // Code to get quiz grade from the system
        // Demo grade value
        int grade = 85;
        return grade;
    }

    /**
     * view all grades for a course for a particular student
     * @param studentId
     * @param CourseID
     * @return
     */
    public List<Integer> viewCourseGrades(int studentId, int CourseID)
    {
        // Code to get all grades for the specified course from the system
        List<Integer> grades = new ArrayList<>();
        // Demo grade values
        grades.add(80);
        grades.add(75);
        grades.add(90);
        return grades;
    }

}
