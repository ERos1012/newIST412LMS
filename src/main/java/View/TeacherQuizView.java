package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Controller.QuizController;
import Model.Quiz;

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

        add(quizScrollPane, BorderLayout.NORTH);

        loadQuizzes();
    }

    private void navigateToCreateQuiz(ActionEvent e) {
        new QuizCreationView(quizController, quiz).setVisible(true);
    }

    private void removeSelectedQuiz(ActionEvent e) {
        Quiz selectedQuiz = quizList.getSelectedValue();
        if (selectedQuiz != null) {
            quizListModel.removeElement(selectedQuiz);
            quizController.removeQuiz(selectedQuiz);
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
        } else {
            JOptionPane.showMessageDialog(this, "No quiz selected to update!", "Update Quiz",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadQuizzes() {
        quizListModel.removeAllElements();
        Quiz quiz1 = new Quiz(1, 2, "Math Basics", "2024-05-01", new ArrayList<>());
        Quiz quiz2 = new Quiz(2, 2, "History 101", "2024-06-15", new ArrayList<>());
        Quiz quiz3 = new Quiz(3, 3, "Science Fundamentals", "2024-07-20", new ArrayList<>());
        quizListModel.addElement(quiz1);
        quizListModel.addElement(quiz2);
        quizListModel.addElement(quiz3);
    }

    public static void main(String[] args) {
        QuizController controller = new QuizController(); // Ensure this controller is properly instantiated
        TeacherQuizView frame = new TeacherQuizView(controller);
        frame.setVisible(true);
    }
}
