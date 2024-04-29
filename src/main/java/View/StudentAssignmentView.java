package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Controller.AssignmentController;
import java.awt.*;
import java.util.List;

import Controller.CourseController;
import Model.Assignment;
import Model.AssignmentSubmission;
import Model.Course;

public class StudentAssignmentView extends JPanel {
    private AssignmentController manager;
    private JTable activeCoursesTable;
    private DefaultTableModel activeCoursesTableModel;
    private Course course;
    private int userId;


    public StudentAssignmentView(int userId, AssignmentController manager, Course course) {
        this.manager = manager;
        this.course = course;
        this.userId = userId;
        setLayout(new BorderLayout());
        initializeUI();
    }


    private void initializeUI() {
        // Header
        JLabel headerLabel = new JLabel("Assignments for: " + course.getName(), JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(headerLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit Assignment");
        JButton backButton = new JButton("Back");
        JButton allCoursesButton = new JButton("All Courses");

        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(allCoursesButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Submit assignment button action
        submitButton.addActionListener(e -> handleSubmitAssignment());

        // Back button action
        backButton.addActionListener(e -> switchToSelectedCourseView(course));

        allCoursesButton.addActionListener(e -> switchToCourseView());

        // Panel to display active assignments
        JPanel activeCoursesPanel = new JPanel(new BorderLayout());
        activeCoursesTableModel = new DefaultTableModel();
        activeCoursesTableModel.addColumn("ID");
        activeCoursesTableModel.addColumn("Name");
        activeCoursesTableModel.addColumn("Due Date");
        activeCoursesTable = new JTable(activeCoursesTableModel);
        activeCoursesPanel.add(new JScrollPane(activeCoursesTable), BorderLayout.CENTER);
        add(activeCoursesPanel, BorderLayout.CENTER);

        refreshActiveAssignmentsTable();
    }

    private void switchToSelectedCourseView(Course course) {
        AssignmentController assignmentController = new AssignmentController();
        StudentSelectedCourseView studentSelectedCourseView = new StudentSelectedCourseView(course, assignmentController, this.userId);

        // Replace the current panel with the SelectedCourseView
        removeAll();
        setLayout(new BorderLayout());
        add(studentSelectedCourseView, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void switchToCourseView() {
        // Create a new instance of CourseView
        CourseController courseController = new CourseController(); // Assuming you can create a new instance of CourseController
        StudentCourseView studentCourseView = new StudentCourseView(courseController, this.userId);

        // Replace the current panel with the CourseView
        removeAll();
        setLayout(new BorderLayout());
        add(studentCourseView, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void refreshActiveAssignmentsTable() {
        activeCoursesTableModel.setRowCount(0); // Clear existing rows
        List<Assignment> activeAssignments = manager.getActiveAssignments(course);
        for (Assignment assignment : activeAssignments) {
            activeCoursesTableModel.addRow(new Object[] {assignment.getId(), assignment.getName(), assignment.getDueDate()});
        }
    }

    private void handleSubmitAssignment() {
        // Create a dialog for the student to enter their submission details
        JPanel dialogPanel = new JPanel(new GridLayout(0, 1));

        // Remove the prompt for student ID and use the stored user ID directly

        // Create a text area for the student to enter their submission text
        dialogPanel.add(new JLabel("Enter your submission:"));
        JTextArea submissionTextArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(submissionTextArea);
        dialogPanel.add(scrollPane);
        System.out.println(this.userId);

        // Display the dialog
        int option = JOptionPane.showConfirmDialog(
                this,
                dialogPanel,
                "Submit Assignment",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // If the student clicked OK
        if (option == JOptionPane.OK_OPTION) {
            // Retrieve the submission text
            String submissionText = submissionTextArea.getText();

            // Get the selected assignment
            int selectedRow = activeCoursesTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an assignment to submit.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int assignmentId = (int) activeCoursesTableModel.getValueAt(selectedRow, 0);

            // Use the stored user ID directly (assume `userId` is a field in this class)
            int studentId = userId; // Replace the previous user input with the stored user ID

            // Check if there is already a submission for the student, assignment, and course
            boolean hasSubmitted = manager.hasExistingSubmission(studentId, assignmentId, course.getId());
            if (hasSubmitted) {
                // Display a message indicating that the student has used all their attempts
                JOptionPane.showMessageDialog(this, "You have used all your attempts for this assignment.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create an AssignmentSubmission object
            AssignmentSubmission submission = new AssignmentSubmission();

            // Populate the AssignmentSubmission object
            submission.setStudentId(this.userId); // Use the automatically provided user ID
            submission.setAssignmentId(assignmentId);
            submission.setCourseId(course.getId());
            submission.setTextSubmission(submissionText);

            // Call the AssignmentController's submitAssignment method with the submission object
            manager.submitAssignment(submission);

            // Show a success message
            JOptionPane.showMessageDialog(this, "Assignment submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
