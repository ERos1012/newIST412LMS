package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import Controller.AssignmentController;
import Model.Assignment;
import Model.Course;
import Model.AssignmentSubmission;

public class AssignmentSubmissionsView extends JPanel {
    private final Course course;
    private final Assignment assignment;
    private final AssignmentController controller;
    private JTable submissionsTable;
    private DefaultTableModel submissionsTableModel;

    public AssignmentSubmissionsView(Course course, Assignment assignment, AssignmentController controller) {
        this.course = course;
        this.assignment = assignment;
        this.controller = controller;
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        // Header
        JLabel headerLabel = new JLabel("Submissions for Assignment \"" + assignment.getName() + "\" in Course \"" + course.getName() + "\"", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(headerLabel, BorderLayout.NORTH);

        // Table to display submissions
        submissionsTableModel = new DefaultTableModel();
        submissionsTableModel.addColumn("Student ID");
        submissionsTableModel.addColumn("Text Submission");
        submissionsTable = new JTable(submissionsTableModel);
        add(new JScrollPane(submissionsTable), BorderLayout.CENTER);

        // Load and display submissions
        loadAndDisplaySubmissions();

        addBackButton();
    }

    private void loadAndDisplaySubmissions() {
        // Fetch submissions for the given course and assignment using the controller
        List<AssignmentSubmission> submissions = controller.getAssignmentSubmissions(assignment.getId(), course.getId());

        // Clear existing rows in the table model
        submissionsTableModel.setRowCount(0);

        // Add rows to the table model for each submission
        for (AssignmentSubmission submission : submissions) {
            submissionsTableModel.addRow(new Object[] { submission.getStudentId(), submission.getTextSubmission() });
        }
    }

    private void addBackButton() {
        // Create a back button
        JButton backButton = new JButton("Back");

        // Set up the action listener for the back button
        backButton.addActionListener(e -> goBackToAssignmentListView());

        // Add the back button to the panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void goBackToAssignmentListView() {
        // Replace the current panel with the AssignmentListView
        removeAll();
        setLayout(new BorderLayout());
        AssignmentListView assignmentListView = new AssignmentListView(controller, course);
        add(assignmentListView, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
