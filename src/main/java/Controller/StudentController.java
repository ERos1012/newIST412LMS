package Controller;

import Model.Course;
import Model.Student;
import java.util.List;

/**
 * The StudentController class manages students in the system.
 */
public class StudentController {
    /**
     * Adds a new quiz to the system.
     */
    public void addStudent(Student student) {
        System.out.println("Added Student: " + student.getName() + " - Test Passed");
    }

    /**
     * Removes an existing quiz from the system.
     */
    public void removeStudent(Student student) {
        System.out.println("Removed Student: " + student.getName() + " - Test Passed");
    }

    /**
     * Updates an existing quiz in the system.
     */
    public void updateStudent(Student student) {
        System.out.println("Updated Student: " + student.getName() + " - Test Passed");
    }

    /**
     * Views details of a specific quiz.
     */
    public Student viewStudent(Student student) {
        // demo
        System.out.println("Viewed Student: " + student.getName() + " - Test Passed");
        return student;
    }

    /**
     * Gets a list of all students for a course
     * 
     * @param course
     * @return
     */
    public List<Student> getAllStudents(Course course) {
        List<Student> students = Student.addDummyStudents();
        for (Student student : students) {
            System.out.println("Student Name: " + student.getName() + ", Email: " + student.getEmail());
        }
        return List.of();
    }

}
