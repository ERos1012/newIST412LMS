package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.GradeController;
import Controller.GradeSubmissionHandler;

public class GradeView extends JPanel {
    private JTextField studentIdField;
    private JTextField courseIdField;
    private JTextField gradeField;
    private JButton assignButton;

    private GradeSubmissionHandler submissionHandler;

    public GradeView(GradeController gradeController) {
        super(new BorderLayout());
        // Initialize the GradeSubmissionHandler
        submissionHandler = new GradeSubmissionHandler(gradeController);

        // Input fields for student ID, course ID, and grade
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        inputPanel.add(studentIdField);
        inputPanel.add(new JLabel("Course ID:"));
        courseIdField = new JTextField();
        inputPanel.add(courseIdField);
        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        // Assign button to assign grade
        assignButton = new JButton("Assign Grade");
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignGrade();
            }
        });

        // Add input panel and assign button to the main panel
        add(inputPanel, BorderLayout.CENTER);
        add(assignButton, BorderLayout.SOUTH);
    }

    /**
     * Assigns a grade to the student.
     */
    private void assignGrade() {
        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            int courseId = Integer.parseInt(courseIdField.getText());
            int gradeValue = Integer.parseInt(gradeField.getText());

            // Call the GradeSubmissionHandler to submit the grade
            submissionHandler.submitGrade(studentId, courseId, gradeValue);

            // Display a success message
            JOptionPane.showMessageDialog(this, "Grade assigned successfully.");

            // Clear the input fields after assigning the grade
            studentIdField.setText("");
            courseIdField.setText("");
            gradeField.setText("");
        } catch (NumberFormatException ex) {
            // Display an error message if the input is not valid
            JOptionPane.showMessageDialog(this, "Please enter valid values for Student ID, Course ID, and Grade.");
        }
    }

    /**
     * The main method to launch the GradeView.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradeView(new GradeController()));
    }
}
