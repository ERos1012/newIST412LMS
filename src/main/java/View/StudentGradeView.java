// StudentGradeView.java
package View;

import java.util.List;
import javax.swing.*;
import Controller.GradeController;
import Model.Grade;

public class StudentGradeView extends JPanel {
    private JLabel studentIdLabel;
    private JTextArea gradeTextArea; // Use JTextArea for multiline text
    private GradeController gradeController;

    public StudentGradeView() {
        super();
        gradeController = new GradeController();
        studentIdLabel = new JLabel("Student ID: ");
        gradeTextArea = new JTextArea(10, 20); // Rows, Columns
        gradeTextArea.setEditable(false); // Make it read-only

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(studentIdLabel);
        panel.add(new JScrollPane(gradeTextArea)); // Add a scroll pane for long grades list
        add(panel);
    }

    public void updateStudentGrades(int studentId) {
        List<Grade> grades = gradeController.getGradesForStudent(studentId);
        if (!grades.isEmpty()) {
            StringBuilder gradeText = new StringBuilder();
            for (Grade grade : grades) {
                gradeText.append("Course ID: ").append(grade.getCourseId()).append(", Grade: ").append(grade.getGrade()).append("\n");
            }
            gradeTextArea.setText(gradeText.toString().trim()); // Set text in the text area
            studentIdLabel.setText("Student ID: " + studentId);
        } else {
            gradeTextArea.setText("N/A"); // Show N/A if no grades
            studentIdLabel.setText("Student ID: " + studentId);
        }
    }

    // Add a method to refresh the view after assigning a grade
    public void refresh(int studentId) {
        updateStudentGrades(studentId);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGradeView());
    }
}
