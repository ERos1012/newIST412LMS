package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import Controller.AnswerController;
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

        quizList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Quiz) {
                    Quiz quiz = (Quiz) value;
                    setText(String.format("%s - Due: %s, Course ID: %d", quiz.getName(), quiz.getDueDate(), quiz.getCourseId()));
                }
                return this;
            }
        });

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
        // This line starts the quiz using the selected quiz
        new QuizTakingView(selectedQuiz, new AnswerController());
    } else {
        JOptionPane.showMessageDialog(this, "Please select a quiz to start.", "Error", JOptionPane.WARNING_MESSAGE);
    }
}


    private void reviewSelectedQuiz(ActionEvent e) {
        Quiz selectedQuiz = quizList.getSelectedValue();
        if (selectedQuiz != null) {
            JOptionPane.showMessageDialog(this, "Reviewing Quiz: " + selectedQuiz.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a quiz to review.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadQuizzes() {
        quizListModel.removeAllElements();  // Clear existing quizzes from the list model
        List<Quiz> quizzes = quizController.getAllQuizzes();  // Fetch quizzes from the controller
        
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes found.");  // Debugging output
        }
        
        // Iterate through each quiz and only add it to the list if it's active
        for (Quiz quiz : quizzes) {
            if (quiz.isActive()) {  // Check if the quiz is active
                quizListModel.addElement(quiz);  // Only add active quizzes
                System.out.println("Loaded quiz: " + quiz.getName());  // Debugging output for active quizzes
            } else {
                System.out.println("Inactive quiz not loaded: " + quiz.getName());  // Debugging output for inactive quizzes
            }
        }
    }        
}
