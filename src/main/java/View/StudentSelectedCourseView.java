package View;

import javax.swing.*;
import java.awt.*;

import Controller.CourseController;
import Controller.AssignmentController;
import Model.Course;
import View.StudentAssignmentView;

public class StudentSelectedCourseView extends JPanel {
    private final AssignmentController assignmentController;
    private Course course;

    public StudentSelectedCourseView(Course course, AssignmentController assignmentController) {
        this.course = course;
        this.assignmentController = assignmentController;
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        //Header
        JLabel headerLabel = new JLabel("Course: " + course.getName(), JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(headerLabel, BorderLayout.NORTH);
        // Display course information
        JTextArea courseInfoTextArea = new JTextArea();
        courseInfoTextArea.setEditable(false);
        courseInfoTextArea.setText(String.format("Program: %s\nInstructor: %s", course.getProgram(), course.getInstructor()));
        add(new JScrollPane(courseInfoTextArea), BorderLayout.CENTER);

        // Panel for buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        JButton viewAssignmentsButton = new JButton("View Assignments");

        buttonPanel.add(backButton);
        buttonPanel.add(viewAssignmentsButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Back button action
        backButton.addActionListener(e -> switchToCourseView());
        // View Assignments button action
        viewAssignmentsButton.addActionListener(e -> switchToAssignmentListView(course));
    }


    private void switchToCourseView() {
        // Create a new instance of CourseView
        CourseController courseController = new CourseController();  // Assuming you can create a new instance of CourseController
        StudentCourseView studentCourseView = new StudentCourseView(courseController);

        // Replace the current panel with the SelectedCourseView
        removeAll();
        setLayout(new BorderLayout());
        add(studentCourseView, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void switchToAssignmentListView(Course course) {
        // Create a new instance of AssignmentListView
        AssignmentController assignmentController = new AssignmentController();
        StudentAssignmentView studentAssignmentView = new StudentAssignmentView(assignmentController, course);

        // Replace the current view with the new instance of AssignmentListView
        removeAll();
        setLayout(new BorderLayout());
        add(studentAssignmentView, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

}
