package Controller;

import java.util.List;

import Model.Course;

/**
 * The CourseController class manages courses in the system.
 */
public class CourseController {

    // Dummy list of courses
    private List<Course> courses = Course.getDummyCourses();

    /**
     * Adds a new course to the system.
     */
    public void addCourse(Course course) {
        System.out.println("Course added: " + course.getName() + " " + course.getId() + " " + course.getProgram() + " " + course.getInstructor());
    }

    /**
     * Removes an existing course from the system.
     */
    public void removeCourse(int id) {
        System.out.println("Course removed: " + id);
    }

    /**
     * Updates an existing course in the system.
     */
    public void updateCourse(Course course) {
        System.out.println("Course updated: " + course.getName() + " " + course.getId() + " " + course.getProgram() + " " + course.getInstructor());
    }

    /**
     * Views details of a specific course.
     */
    public Course viewCourse(Course course) {
        // demo
        System.out.println("Course details: " + course.getName() + " " + course.getId() + " " + course.getProgram() + " " + course.getInstructor());
        return course;
    }

    /**
     * Views a list of all courses in the system.
     */
    public void viewCourseList(Course course) {
    }

    /**
     * Views students enrolled in a specific course.
     */
    public void viewCourseStudents(Course course) {
    }

    /**
     * Views instructors assigned to a specific course.
     */
    public void viewCourseInstructors(Course course) {
    }

    /**
     * Views assignments associated with a specific course.
     */
    public void viewCourseAssignments(Course course) {
    }

    /**
     * Views quizzes associated with a specific course.
     */
    public void viewCourseQuizzes(Course course) {
    }

    /**
     * Views grades for a specific course.
     */
    public void viewCourseGrades(Course course) {
    }

    /**
     * Returns a list of all courses.
     * 
     * @return List of all courses.
     */
    public List<Course> getAllCourses() {
        return courses;
    }
}
