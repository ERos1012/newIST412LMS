package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Controller.QuizController;
import Model.Quiz;
import java.util.List;

public class StudentQuizView extends JPanel {
    private JList<Quiz> quizList;
    private DefaultListModel<Quiz> quizListModel;
    private JButton startQuizButton;
    private JButton reviewQuizButton;
    private QuizController quizController;

    public StudentQuizView(QuizController quizController) {
        this.quizController = quizController;
        initializeUI();
        loadQuizzes();  // This method will be called to fetch and display quizzes
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        quizListModel = new DefaultListModel<>();
        quizList = new JList<>(quizListModel);
        quizList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(quizList);
        add(scrollPane, BorderLayout.CENTER);

        startQuizButton = new JButton("Start Quiz");
        reviewQuizButton = new JButton("Review Quiz");

        startQuizButton.addActionListener(this::startSelectedQuiz);
        reviewQuizButton.addActionListener(this::reviewSelectedQuiz);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startQuizButton);
        buttonPanel.add(reviewQuizButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startSelectedQuiz(ActionEvent e) {
        Quiz selectedQuiz = quizList.getSelectedValue();
        if (selectedQuiz != null) {
            // Here you would start the quiz for the student
            JOptionPane.showMessageDialog(this, "Starting Quiz: " + selectedQuiz.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a quiz to start.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reviewSelectedQuiz(ActionEvent e) {
        Quiz selectedQuiz = quizList.getSelectedValue();
        if (selectedQuiz != null) {
            // Here you would open the review page for the selected quiz
            JOptionPane.showMessageDialog(this, "Reviewing Quiz: " + selectedQuiz.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a quiz to review.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadQuizzes() {
        quizListModel.removeAllElements();
        List<Quiz> quizzes = quizController.getAllQuizzes(); // Fetch all quizzes from the controller
        for (Quiz quiz : quizzes) {
            quizListModel.addElement(quiz);
        }
    }
}
