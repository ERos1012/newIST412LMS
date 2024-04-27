package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Controller.QuizController;
import Model.Quiz;

import java.util.List;

public class TeacherQuizView extends JPanel {
    private JList<Quiz> quizList;
    private DefaultListModel<Quiz> quizListModel;
    private JButton createNewQuizButton, removeQuizButton, updateQuizButton;
    private QuizController quizController;

    public TeacherQuizView(QuizController quizController) {
        this.quizController = quizController;
        setSize(800, 500);
        setLayout(new BorderLayout());
        initializeUI();
        loadQuizzes();
    }

    private void initializeUI() {
        quizListModel = new DefaultListModel<>();
        quizList = new JList<>(quizListModel);
        quizList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane quizScrollPane = new JScrollPane(quizList);

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
            quizController.removeQuiz(selectedQuiz.getId());  // Use getId() to pass the quiz's ID
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
    
    public static void main(String[] args) {
        QuizController controller = new QuizController(); // Make sure this is properly implemented
        TeacherQuizView frame = new TeacherQuizView(controller);
        frame.setVisible(true);
    }
}
