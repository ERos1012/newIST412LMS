package Controller;

import Model.*;

import java.time.LocalDateTime;
import java.util.List;

public class FacadeController {

    private final AssignmentController assignmentController;
    private final GradeController gradeController;
    private final MessageController messageController;
    private final QuizController quizController;
    private final StudentController studentController;
    private final TeacherController teacherController;
    private final CourseController courseController;
    private final EventCalendarController eventCalendarController;

    public FacadeController() {
        assignmentController = new AssignmentController();
        gradeController = new GradeController();
        messageController = new MessageController();
        quizController = new QuizController();
        studentController = new StudentController();
        teacherController = new TeacherController();
        courseController = new CourseController();
        eventCalendarController = new EventCalendarController();
    }

    // Methods for AssignmentController

    // public Assignment addAssignment(Assignment assignment) {
    //     return assignmentController.addAssignment(assignment);
    // }

    // public void removeAssignment() {
    //     assignmentController.removeAssignment());
    // }

    // public void updateAssignment() {
    //     assignmentController.updateAssignment();
    // }

    // public Assignment viewAssignment(Assignment assignment) {
    //     return assignmentController.viewAssignment(assignment);
    // }

    // public List<String> getAllAssignments(Course course) {
    //     return assignmentController.getAllAssignments(course);
    // }

    // Methods for GradeController

    public void addGrade(Grade grade) {
        gradeController.addGrade(grade);
    }

    public void removeGrade(Grade grade) {
        gradeController.removeGrade(grade);
    }

    public void updateGrade(Grade grade, double newGradeValue) {
        gradeController.updateGrade(grade, newGradeValue);
    }

    public Grade viewGrade(Grade grade) {
        return gradeController.viewGrade(grade);
    }

    public int viewQuizGrade(int studentId, int courseId, int quizId) {
        return gradeController.viewQuizGrade(studentId, courseId, quizId);
    }

    public List<Integer> viewCourseGrades(int studentId, int courseId) {
        return gradeController.viewCourseGrades(studentId, courseId);
    }

    // Methods for MessageController

    public void sendMessage(Message message) {
        messageController.sendMessage(message);
    }

    public Message viewMessage(Message message) {
        return messageController.viewMessage(message);
    }

    public void deleteMessage(Message message) {
        messageController.deleteMessage(message);
    }

    public List<String> getStudentMessages(Student student) {
        return messageController.getStudentMessages(student);
    }

    public List<String> getTeacherMessages(Teacher teacher) {
        return messageController.getTeacherMessages(teacher);
    }

    // Methods for QuizController

    public Quiz addQuiz(Quiz quiz) {
        return quizController.addQuiz(quiz);
    }

    public void removeQuiz(Quiz quiz) {
        quizController.removeQuiz(quiz);
    }

    public void updateQuiz(Quiz quiz) {
        quizController.updateQuiz(quiz);
    }

    public Quiz viewQuiz(Quiz quiz) {
        return quizController.viewQuiz(quiz);
    }

    public List<Quiz> getAllQuizzes(Course course) {
        return quizController.getAllQuizzes(course);
    }

    public void gradeQuiz(int studentId, int courseId, int quizId, int grade) {
        quizController.gradeQuiz(studentId, courseId, quizId, grade);
    }

    // Methods for StudentController

    public void addStudent(Student student) {
        studentController.addStudent(student);
    }

    public void removeStudent(Student student) {
        studentController.removeStudent(student);
    }

    public void updateStudent(Student student) {
        studentController.updateStudent(student);
    }

    public Student viewStudent(Student student) {
        return studentController.viewStudent(student);
    }

    public List<Student> getAllStudents(Course course) {
        return studentController.getAllStudents(course);
    }

    // Methods for TeacherController

    public void addTeacher(Teacher teacher) {
        teacherController.addTeacher(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teacherController.removeTeacher(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        teacherController.updateTeacher(teacher);
    }

    public Teacher viewTeacher(Teacher teacher) {
        return teacherController.viewTeacher(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherController.getAllTeachers();
    }

    // Methods for CourseController

    public void addCourse(Course course) {
        courseController.addCourse(course);
    }

    public void removeCourse(int id) {
        courseController.removeCourse(id);
    }

    public void updateCourse(Course course) {
        courseController.updateCourse(course);
    }

    public Course viewCourse(Course course) {
        return courseController.viewCourse(course);
    }

    public void viewCourseList(Course course) {
        courseController.viewCourseList(course);
    }

    public void viewCourseStudents(Course course) {
        courseController.viewCourseStudents(course);
    }

    public void viewCourseInstructors(Course course) {
        courseController.viewCourseInstructors(course);
    }

    public void viewCourseAssignments(Course course) {
        courseController.viewCourseAssignments(course);
    }

    public void viewCourseQuizzes(Course course) {
        courseController.viewCourseQuizzes(course);
    }

    public void viewCourseGrades(Course course) {
        courseController.viewCourseGrades(course);
    }

    public List<Course> getAllCourses() {
        return courseController.getAllCourses();
    }

    // Methods for EventCalendarController

    public EventCalendarController getEventCalendarController() {
        return eventCalendarController;
    }

    public void addEventToCalendar(EventController event) {
        eventCalendarController.addEvent(event);
    }

    public void removeEventFromCalendar(EventController event) {
        eventCalendarController.removeEvent(event);
    }

    public List<EventController> getAllEvents() {
        return eventCalendarController.getAllEvents();
    }

    public String getAllEventsAsString() {
        return eventCalendarController.getAllEventsAsString();
    }

}
