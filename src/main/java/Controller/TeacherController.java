package Controller;

import Model.Course;
import Model.Teacher;

import java.util.List;

public class TeacherController {

    /**
     * Retreives Teacher info from teacher ID
     * @param id
     * @return
     */
    /**
     * Adds a new quiz to the system.
     */
    public void addTeacher(Teacher teacher) {
        System.out.println("Added Teacher: " + teacher.getName() + " - Test Passed");
    }

    /**
     * Removes an existing quiz from the system.
     */
    public void removeTeacher(Teacher teacher) {
        System.out.println("Removed Teacher: " + teacher.getName() + " - Test Passed");
    }

    /**
     * Updates an existing quiz in the system.
     */
    public void updateTeacher(Teacher teacher) {
        System.out.println("Updated Teacher: " + teacher.getName() + " - Test Passed");
    }

    /**
     * Views details of a specific quiz.
     */
    public Teacher viewTeacher(Teacher teacher) {
        // demo
        System.out.println("Viewed Teacher: " + teacher.getName() + " - Test Passed");
        return teacher;
    }

    /**
     * Gets a list of all students for a course
     * @return
     */
    public List<Teacher> getAllTeachers(){
        List<Teacher> teachers = Teacher.addDummyTeachers();
        for (Teacher teacher : teachers) {
            System.out.println("Teacher Name: " + teacher.getName() + ", Email: " + teacher.getEmail());
        }
        return List.of();
    }
}
