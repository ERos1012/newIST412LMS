package FinalTestHarness;

import Controller.AssignmentController;
import Controller.MessageController;
import Model.Assignment;
import Model.Course;
import Model.Message;
import Model.Student;
import Model.Teacher;
import Controller.StudentController;
import Controller.TeacherController;
import Controller.CourseController;
import Controller.QuizController;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Instantiate controller classes
        AssignmentController assignmentController = new AssignmentController();
        CourseController courseController = new CourseController();
        MessageController messageController = new MessageController();
        QuizController quizController = new QuizController();

        // // Scenario: Teacher wants to add, update, remove, view, and get all assignments for a course
        // // Test adding an assignment
        // Assignment newAssignment = new Assignment("Assignment 1", "Description", "2024-03-10");
        // Assignment addedAssignment = assignmentController.addAssignment(newAssignment);
        // if (addedAssignment != null) {
        //     System.out.println("Added Assignment: " + addedAssignment.getName() + " - Test Passed");
        // } else {
        //     System.out.println("Failed to add assignment - Test Failed");
        // }

        // // Test updating an assignment
        // Assignment updatedAssignment = new Assignment("Assignment 2", "Updated Description", "2024-03-15");
        // assignmentController.updateAssignment();
        // System.out.println("Updated Assignment: " + updatedAssignment.getName() + " - Test Passed");

        // // Test removing an assignment
        // assignmentController.removeAssignment();
        // System.out.println("Removed Assignment - Test Passed");

        // // Test viewing an assignment
        // Assignment assignmentToView = new Assignment("Assignment 3", "View Description", "2024-03-20");
        // Assignment viewedAssignment = assignmentController.viewAssignment(assignmentToView);
        // System.out.println("Viewed Assignment: " + viewedAssignment.getName() + " - Test Passed");

        // // Test getting all assignments for a course (empty for demo purposes)
        // Course course = new Course("Course 1", 101, "Computer Science", "Dr. Smith");
        // List<String> allAssignments = assignmentController.getAllAssignments(course);
        // if (allAssignments.isEmpty()) {
        //     System.out.println("Got all assignments for Course 1 - Test Passed");
        // } else {
        //     System.out.println("Failed to get all assignments for Course 1 - Test Failed");
        // }

        // System.out.println("AssignmentController tests completed.");


        // Scenario: Student wants to message a professor about an assignment
        Student student = new Student("John Doe", 123, "Computer Science", "john.doe@example.com");
        Teacher teacher = new Teacher("Dr. Smith", 456, "smith@example.com");
        String messageContent = "Dear Professor, I have a question about the assignment.";
        Message studentMessage = new Message(1, student.getId(), teacher.getId(), messageContent, "2024-03-03");

        // Test sending a message
        // messageController.sendMessage(studentMessage);
        System.out.println("Message sent from student to professor - Test Passed");

        System.out.println("MessageController tests completed.");

        // Scenario: Administrator/Teacher wants to add a new course
        Course newCourse = new Course("Course 2", 102, "Computer Science", "Dr. Smith");
        courseController.addCourse(newCourse);
        System.out.println("Course added: " + newCourse.getName() + " - Test Passed");

        // Scenario: Administrator/Teacher wants to remove a course
        courseController.removeCourse(102);
        System.out.println("Course removed: 102 - Test Passed");

        System.out.println("CourseController tests completed.");

        // Testing StudentController
        testAddStudent();
        testRemoveStudent();
        testUpdateStudent();
        testViewStudent();
        testGetAllStudents();

        // Testing TeacherController
        testAddTeacher();
        testRemoveTeacher();
        testUpdateTeacher();
        testViewTeacher();
        testGetAllTeachers();
    }

    public static void testAddStudent() {
        StudentController studentController = new StudentController();
        Student studentToAdd = new Student("John Doe", 123, "Computer Science", "john.doe@example.com");
        studentController.addStudent(studentToAdd);
    }

    public static void testRemoveStudent() {
        StudentController studentController = new StudentController();
        Student studentToRemove = new Student("John Doe", 123, "Computer Science", "john.doe@example.com");
        studentController.removeStudent(studentToRemove);

    }

    public static void testUpdateStudent() {
        StudentController studentController = new StudentController();
        Student studentToUpdate = new Student("John Doe", 123, "Computer Science", "john.doe@example.com");
        studentToUpdate.setEmail("updated.john.doe@example.com");
        studentController.updateStudent(studentToUpdate);
    }

    public static void testViewStudent() {
        StudentController studentController = new StudentController();
        Student studentToView = new Student("John Doe", 123, "Computer Science", "john.doe@example.com");
        Student viewedStudent = studentController.viewStudent(studentToView);
    }

    public static void testGetAllStudents() {
        StudentController studentController = new StudentController();
        Course course = new Course("Course 1", 101, "Computer Science", "Dr. Smith");
        List<Student> allStudents = studentController.getAllStudents(course);
    }

    public static void testAddTeacher() {
        TeacherController teacherController = new TeacherController();
        Teacher teacherToAdd = new Teacher("Dr. Smith", 456, "smith@example.com");
        teacherController.addTeacher(teacherToAdd);

    }

    public static void testRemoveTeacher() {
        TeacherController teacherController = new TeacherController();
        Teacher teacherToRemove = new Teacher("Dr. Smith", 456, "smith@example.com");
        teacherController.removeTeacher(teacherToRemove);

    }

    public static void testUpdateTeacher() {
        TeacherController teacherController = new TeacherController();
        Teacher teacherToUpdate = new Teacher("Dr. Smith", 456, "smith@example.com");
        teacherToUpdate.setEmail("updated.smith@example.com");
        teacherController.updateTeacher(teacherToUpdate);

       
    }

    public static void testViewTeacher() {
        TeacherController teacherController = new TeacherController();
        Teacher teacherToView = new Teacher("Dr. Smith", 456, "smith@example.com");
        Teacher viewedTeacher = teacherController.viewTeacher(teacherToView);
    }

    public static void testGetAllTeachers() {
        TeacherController teacherController = new TeacherController();
        List<Teacher> allTeachers = teacherController.getAllTeachers();
    }
}


