package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import Model.Quiz;
import Controller.AnswerController;
import Controller.QuizController;
import Controller.QuizCompletionListener;

public class StudentQuizView extends JPanel implements QuizCompletionListener {
    private JList<Quiz> quizList;
    private DefaultListModel<Quiz> quizListModel;
    private JButton startQuizButton;
    private QuizController quizController;

    public StudentQuizView(QuizController quizController) {
        if (quizController == null) {
            throw new IllegalArgumentException("QuizController cannot be null");
        }
        this.quizController = quizController;
        this.quizController.addQuizCompletionListener(this); // Register as a listener
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

        initializeListRendererAndListener();

        startQuizButton = new JButton("Start Quiz");
        startQuizButton.addActionListener(this::startSelectedQuiz);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startQuizButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeListRendererAndListener() {
        quizList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Quiz quiz = (Quiz) value;
                setText(String.format("%s - Due: %s, Course ID: %d%s", quiz.getName(), quiz.getDueDate(), quiz.getCourseId(), quiz.isDone() ? " (Finished)" : ""));
                setForeground(quiz.isDone() ? Color.GRAY : Color.BLACK);
                return this;
            }
        });

        quizList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                boolean hasSelection = quizList.getSelectedIndex() != -1;
                Quiz selectedQuiz = quizList.getSelectedValue();
                startQuizButton.setEnabled(hasSelection && !selectedQuiz.isDone());
            }
        });
    }

    private void startSelectedQuiz(ActionEvent e) {
        Quiz selectedQuiz = quizList.getSelectedValue();
        if (selectedQuiz != null && !selectedQuiz.isDone()) {
            QuizTakingView quizTakingView = new QuizTakingView(selectedQuiz, new AnswerController(), quizController);
            quizTakingView.setVisible(true);
        } else if (selectedQuiz != null) {
            JOptionPane.showMessageDialog(this, "This quiz has already been completed.", "Quiz Unavailable", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No quiz selected. Please select a quiz first.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void quizCompleted() {
        SwingUtilities.invokeLater(this::refreshQuizList);
    }

    public void refreshQuizList() {
        quizListModel.clear();  // Clear the current list
        loadQuizzes();  // Reload the quizzes
        quizList.repaint();
    }

    private void loadQuizzes() {
        List<Quiz> quizzes = quizController.getAllQuizzes();  // Fetch quizzes from the controller
        for (Quiz quiz : quizzes) {
            if (quiz.isActive()) {  // Check if the quiz is active
                quizListModel.addElement(quiz);  // Only add active quizzes
            }
        }
        quizList.revalidate();
    }        
}
