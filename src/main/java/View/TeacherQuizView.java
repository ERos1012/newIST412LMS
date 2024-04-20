package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Controller.QuizController;
import Model.Quiz;

import java.util.List;

public class TeacherQuizView extends JFrame {
    private JList<Quiz> quizList;
    private DefaultListModel<Quiz> quizListModel;
    private JButton createNewQuizButton, removeQuizButton, updateQuizButton;
    private QuizController quizController;
    private Quiz quiz;

    public TeacherQuizView(QuizController quizController) {
        this.quizController = quizController;
        setTitle("Quiz Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeUI();
        loadQuizzes();
    }

    private void initializeUI() {
        quizListModel = new DefaultListModel<>();
        quizList = new JList<>(quizListModel);
        quizList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane quizScrollPane = new JScrollPane(quizList);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createNewQuizButton = new JButton("Create New Quiz");
        removeQuizButton = new JButton("Remove Quiz");
        updateQuizButton = new JButton("Update Quiz");

        createNewQuizButton.addActionListener(this::navigateToCreateQuiz);
        removeQuizButton.addActionListener(this::removeSelectedQuiz);
        updateQuizButton.addActionListener(this::updateSelectedQuiz);

        buttonPanel.add(createNewQuizButton);
        buttonPanel.add(removeQuizButton);
        buttonPanel.add(updateQuizButton);
        add(buttonPanel, BorderLayout.SOUTH);

        add(quizScrollPane, BorderLayout.CENTER);
    }

    private void navigateToCreateQuiz(ActionEvent e) {
        QuizCreationView creationView = new QuizCreationView(quizController);
        creationView.setVisible(true);
        creationView.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                loadQuizzes();
            }
        });
    }

    private void removeSelectedQuiz(ActionEvent e) {
        Quiz selectedQuiz = quizList.getSelectedValue();
        if (selectedQuiz != null) {
            quizController.removeQuiz(selectedQuiz);
            loadQuizzes(); // Reload quizzes after removal
            JOptionPane.showMessageDialog(this, "Quiz removed: " + selectedQuiz.getName(), "Remove Quiz",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No quiz selected to remove!", "Remove Quiz",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateSelectedQuiz(ActionEvent e) {
        Quiz selectedQuiz = quizList.getSelectedValue();
        if (selectedQuiz != null) {
            QuizCreationView updateView = new QuizCreationView(quizController, selectedQuiz);
            updateView.setVisible(true);
            updateView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    loadQuizzes();
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "No quiz selected to update!", "Update Quiz",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadQuizzes() {
        quizListModel.removeAllElements();
        List<Quiz> quizzes = quizController.getAllQuizzes();
        for (Quiz quiz : quizzes) {
            quizListModel.addElement(quiz);
        }
    }

    public static void main(String[] args) {
        QuizController controller = new QuizController(); // Make sure this is properly implemented
        TeacherQuizView frame = new TeacherQuizView(controller);
        frame.setVisible(true);
    }
}
