package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Controller.QuizController;
import Model.Quiz;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizCreationView extends JFrame {
    private QuizController quizController;
    private Quiz currentQuiz; // Holds the current quiz being edited or created
    private JTextField quizNameField, dueDateField;
    private JPanel questionsPanel; // Panel to display questions
    private List<String> questions; // List to hold questions
    private JScrollPane scrollPane; // Scrollable view for displaying questions

    public QuizCreationView(QuizController quizController) {
        this(quizController, null);
    }

    public QuizCreationView(QuizController quizController, Quiz quiz) {
        this.quizController = quizController;
        this.currentQuiz = quiz;
        this.questions = new ArrayList<>();
        setTitle((quiz == null) ? "Create New Quiz" : "Edit Quiz");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeUI();
        if (quiz != null)
            populateFields(quiz);
    }

    private void initializeUI() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        quizNameField = new JTextField(20);
        dueDateField = new JTextField(10);
        formPanel.add(new JLabel("Quiz Name:"));
        formPanel.add(quizNameField);
        formPanel.add(new JLabel("Due Date:"));
        formPanel.add(dueDateField);
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addMultipleChoiceButton = new JButton("Add Multiple Choice Question");
        JButton addTrueFalseButton = new JButton("Add True/False Question");
        JButton addEssayButton = new JButton("Add Essay Type Question");
    
        addMultipleChoiceButton.addActionListener(e -> addMultipleChoiceQuestion());
        addTrueFalseButton.addActionListener(e -> addTrueFalseQuestion());
        addEssayButton.addActionListener(e -> addEssayQuestion());
    
        buttonPanel.add(addMultipleChoiceButton);
        buttonPanel.add(addTrueFalseButton);
        buttonPanel.add(addEssayButton);
    
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton doneButton = new JButton("Done");
        JButton cancelButton = new JButton("Cancel");
    
        // doneButton.addActionListener(e -> saveQuiz(e)); // Ensure this method is properly defined to handle saving
        cancelButton.addActionListener(e -> dispose());
    
        actionPanel.add(doneButton);
        actionPanel.add(cancelButton);
    
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.add(buttonPanel);
        southPanel.add(createScrollPane());
        southPanel.add(actionPanel);
    
        add(formPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
    }
    
    private JScrollPane createScrollPane() {
        questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
    
        JScrollPane scrollPane = new JScrollPane(questionsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 300)); 
        return scrollPane;
    }

    // private void saveQuiz(ActionEvent e) {
    //     String quizName = quizNameField.getText();
    //     String dueDate = dueDateField.getText();
    //     List<String> questions = new ArrayList<>();
    //     for (String question : questions) {
    //         questions.add(question);
    //     }
    //     Quiz quiz = new Quiz(generateQuizId(), quizName, dueDate, questions);
    //     quizController.saveQuiz(quiz);
    //     dispose();
    // }
    

    private int generateQuizId() {
        // This is a simple approach and should be improved for real applications
        return Math.abs(new Random().nextInt());
    }    

    private void populateFields(Quiz quiz) {
        quizNameField.setText(quiz.getName());
        dueDateField.setText(quiz.getDueDate());
        questions.addAll(quiz.getQuestions().stream().map(Object::toString).toList());
        refreshQuestionsPanel();
    }

    private void refreshQuestionsPanel() {
        questionsPanel.removeAll();
        for (String question : questions) {
            JLabel questionLabel = new JLabel("<html>" + question.replace("\n", "<br>") + "</html>");
            questionsPanel.add(questionLabel);
            questionsPanel.add(Box.createVerticalStrut(10)); // Adds spacing between questions
        }
        questionsPanel.revalidate();
        questionsPanel.repaint();
    }

    private void addMultipleChoiceQuestion() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField questionField = new JTextField(20);
        JTextField optionAField = new JTextField(20);
        JTextField optionBField = new JTextField(20);
        JTextField optionCField = new JTextField(20);
        JTextField optionDField = new JTextField(20);
        JComboBox<String> correctAnswerBox = new JComboBox<>(new String[] { "A", "B", "C", "D" });

        panel.add(new JLabel("Question:"));
        panel.add(questionField);
        panel.add(new JLabel("Option A:"));
        panel.add(optionAField);
        panel.add(new JLabel("Option B:"));
        panel.add(optionBField);
        panel.add(new JLabel("Option C:"));
        panel.add(optionCField);
        panel.add(new JLabel("Option D:"));
        panel.add(optionDField);
        panel.add(new JLabel("Correct Answer:"));
        panel.add(correctAnswerBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Multiple Choice Question",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String questionText = questionField.getText() + "\nA: " + optionAField.getText() +
                    "\nB: " + optionBField.getText() + "\nC: " + optionCField.getText() +
                    "\nD: " + optionDField.getText() + "\nCorrect Answer: " + correctAnswerBox.getSelectedItem();
            questions.add(questionText);
            refreshQuestionsPanel();
        }
    }

    private void addTrueFalseQuestion() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField questionField = new JTextField(20);
        JComboBox<String> answerBox = new JComboBox<>(new String[] { "True", "False" });

        panel.add(new JLabel("Question:"));
        panel.add(questionField);
        panel.add(new JLabel("Correct Answer:"));
        panel.add(answerBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add True/False Question", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String questionText = questionField.getText() + " (Correct answer: " + answerBox.getSelectedItem() + ")";
            questions.add(questionText); // Add the question text to the list
            refreshQuestionsPanel(); // Refresh the UI to display the new question
        }
    }

    private void addEssayQuestion() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField questionField = new JTextField(20);

        panel.add(new JLabel("Question:"));
        panel.add(questionField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Essay Type Question", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String questionText = questionField.getText(); // For essays, just the question is needed
            questions.add(questionText); // Add to the list
            refreshQuestionsPanel(); // Refresh the UI
        }
    }

    public static void main(String[] args) {
        QuizController controller = new QuizController();
        new QuizCreationView(controller).setVisible(true);
    }
}
