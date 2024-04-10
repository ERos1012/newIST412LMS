package View;

import javax.swing.*;

import Controller.QuizController;
import Model.Quiz;

/**
 * The QuizView class represents a graphical user interface for displaying quiz details.
 */
public class QuizView extends JPanel {
    private JLabel idLabel;
    private JLabel courseIdLabel;
    private JLabel gradeLabel;
    private QuizController quizController;

    public QuizView() {
        super();
        quizController = new QuizController();

        idLabel = new JLabel("ID: ");
        courseIdLabel = new JLabel("Course ID: ");
        gradeLabel = new JLabel("Grade: ");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(idLabel);
        panel.add(courseIdLabel);
        panel.add(gradeLabel);

        add(panel);

        updateQuizDetails();
    }

    private void updateQuizDetails() {
        // Attempt to retrieve a Quiz object; handle null within viewQuiz or return a default/dummy Quiz if none exists
        Quiz quiz = quizController.viewQuiz(null); // Assume viewQuiz is adapted to handle or avoid null
        if (quiz != null) { // Check if quiz is not null to avoid NullPointerException
            idLabel.setText("ID: " + quiz.getId());
            courseIdLabel.setText("Course ID: " + quiz.getCourseId());
            gradeLabel.setText("Grade: " + quiz.getGrade());
        } else {
            // Set default text or clear labels if no quiz is retrieved
            idLabel.setText("ID: Not available");
            courseIdLabel.setText("Course ID: Not available");
            gradeLabel.setText("Grade: Not available");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quiz Details");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new QuizView());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
