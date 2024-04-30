package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Controller.GradeController;
import Model.Grade;

public class StudentGradeView extends JPanel {
    private JLabel userIdLabel;
    private JTable gradesTable;
    private DefaultTableModel gradesTableModel;
    private GradeController gradeController;
    private int userId; // Current user ID

    public StudentGradeView(int userId) {
        super();
        this.userId = userId;
        gradeController = new GradeController();
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        // Initialize the user ID label
        userIdLabel = new JLabel("User ID: " + userId);
        add(userIdLabel, BorderLayout.NORTH);

        // Initialize the grades table and its model
        gradesTableModel = new DefaultTableModel();
        gradesTableModel.addColumn("Course ID");
        gradesTableModel.addColumn("Grade");

        gradesTable = new JTable(gradesTableModel);

        // Add the table inside a JScrollPane to allow scrolling
        JScrollPane scrollPane = new JScrollPane(gradesTable);
        add(scrollPane, BorderLayout.CENTER);

        // Initial update of the student's grades
        updateStudentGrades();
    }

    public void updateStudentGrades() {
        // Fetch grades for the given user ID using GradeController
        List<Grade> grades = gradeController.getGradesForStudent(this.userId);

        // Clear existing rows in the table model
        gradesTableModel.setRowCount(0);

        // Check if there are grades available for the student
        if (!grades.isEmpty()) {
            // Iterate through the list of grades and add them to the table model
            for (Grade grade : grades) {
                gradesTableModel.addRow(new Object[]{gradeController.getCourseName(grade.getCourseId()), grade.getGrade()});
            }
        } else {
            // If there are no grades available, you can add a row with "N/A"
            gradesTableModel.addRow(new Object[]{"N/A", "N/A"});
        }

        // Update the user ID label with the current user ID
        userIdLabel.setText("User: " + gradeController.getUsername(userId));
    }

    // Add a refresh method to update the table with the current user's grades
    public void refresh() {
        updateStudentGrades();
    }
}
