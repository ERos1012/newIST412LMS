package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Controller.QuizController;
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizCreationView extends JFrame {
    private QuizController quizController;
    private Quiz currentQuiz; // Holds the current quiz being edited or created
    private JTextField quizNameField, dueDateField;
    private JPanel questionsPanel; // Panel to display questions
    private List<Question> questions; // List to hold Question objects
    private JScrollPane scrollPane; // Scrollable view for displaying questions
    private JComboBox<Course> courseSelector; // Dropdown for selecting course

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
        if (quiz != null) {
            populateFields(quiz);
        }
    }

    private void initializeUI() {
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        quizNameField = new JTextField(20);
        dueDateField = new JTextField(10);

        courseSelector = new JComboBox<>();
//        populateCourseSelector();

        formPanel.add(new JLabel("Course:"));
        formPanel.add(courseSelector);
        formPanel.add(new JLabel("Quiz Name:"));
        formPanel.add(quizNameField);
        formPanel.add(new JLabel("Due Date:"));
        formPanel.add(dueDateField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addMultipleChoiceButton = new JButton("Add Multiple Choice Question");
        JButton addTrueFalseButton = new JButton("Add True/False Question");
        JButton addEssayButton = new JButton("Add Essay Question");

        addMultipleChoiceButton.addActionListener(this::addMultipleChoiceQuestion);
        addTrueFalseButton.addActionListener(this::addTrueFalseQuestion);
        addEssayButton.addActionListener(this::addEssayQuestion);

        buttonPanel.add(addMultipleChoiceButton);
        buttonPanel.add(addTrueFalseButton);
        buttonPanel.add(addEssayButton);

        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(this::saveQuiz);
        cancelButton.addActionListener(e -> dispose());

        actionPanel.add(saveButton);
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

    private void saveQuiz(ActionEvent e) {
        String quizName = quizNameField.getText().trim();
        String dueDate = dueDateField.getText().trim();
        int courseId = getCurrentCourseId();  // This might return -1 if no course is selected
    
        if (quizName.isEmpty() || dueDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and due date for the quiz.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (currentQuiz == null || currentQuiz.getId() <= 0) {
            currentQuiz = new Quiz(0, courseId, quizName, dueDate, new ArrayList<>(), false); // Create a quiz with or without a course
            currentQuiz = quizController.addQuiz(currentQuiz);
        } else {
            currentQuiz.setCourseId(courseId);
            currentQuiz.setName(quizName);
            currentQuiz.setDueDate(dueDate);
            quizController.updateQuiz(currentQuiz);
        }
    }
    
    private int getCurrentCourseId() {
        Course selectedCourse = (Course) courseSelector.getSelectedItem();
        return (selectedCourse != null) ? selectedCourse.getId() : -1;
    }
    
    private void populateFields(Quiz quiz) {
        quizNameField.setText(quiz.getName());
        dueDateField.setText(quiz.getDueDate());
        this.questions = quiz.getQuestions();
        refreshQuestionsPanel();
    }

    private void refreshQuestionsPanel() {
        questionsPanel.removeAll(); // Clear existing content
        int questionNumber = 1; // Start numbering questions from 1
    
        for (Question question : questions) {
            // Build the HTML string to represent the question and its details
            StringBuilder questionText = new StringBuilder("<html><b>" + questionNumber + ". " + question.getText() + "</b><br>");
    
            // Check the type of question and add choices or correct answers accordingly
            if (question instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) question;
                questionText.append("A: ").append(mcq.getChoiceA()).append("<br>")
                            .append("B: ").append(mcq.getChoiceB()).append("<br>")
                            .append("C: ").append(mcq.getChoiceC()).append("<br>")
                            .append("D: ").append(mcq.getChoiceD()).append("<br>")
                            .append("<b>Correct Answer: ").append(mcq.getCorrectAnswer()).append("</b></html>");
            } else if (question instanceof TrueOrFalseQuestion) {
                TrueOrFalseQuestion tfq = (TrueOrFalseQuestion) question;
                questionText.append("<b>Correct Answer: ").append(tfq.getCorrectAnswer()).append("</b></html>");
            } else if (question instanceof EssayQuestion) {
                // Essay questions do not have multiple choices or a correct answer in the traditional sense
                questionText.append("<i>Essay question</i></html>");
            }
    
            // Create a JLabel with the constructed HTML text
            JLabel questionLabel = new JLabel(questionText.toString());
            questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding around the label
    
            // Add the label to the panel and a separator for visual clarity
            questionsPanel.add(questionLabel);
            questionsPanel.add(Box.createVerticalStrut(20)); // Adds spacing between questions
    
            questionNumber++; // Increment the question number for the next question
        }
    
        questionsPanel.revalidate(); // Revalidate panel layout
        questionsPanel.repaint(); // Repaint the panel
    }
    
    private void addMultipleChoiceQuestion(ActionEvent e) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JTextField questionField = new JTextField(20);
        JTextField optionAField = new JTextField(20);
        JTextField optionBField = new JTextField(20);
        JTextField optionCField = new JTextField(20);
        JTextField optionDField = new JTextField(20);
        JComboBox<String> correctAnswerBox = new JComboBox<>(new String[]{"A", "B", "C", "D"});
    
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
            MultipleChoiceQuestion mcQuestion = new MultipleChoiceQuestion(
                questionField.getText(),
                optionAField.getText(),
                optionBField.getText(),
                optionCField.getText(),
                optionDField.getText(),
                correctAnswerBox.getSelectedItem().toString()
            );
    
            // Ensure there's a quiz where to add the question
            if (currentQuiz == null || currentQuiz.getId() <= 0) {
                // Prompt to save the quiz details first
                saveQuiz(null);  // Adjust this method to not dispose unless completely done
            }
    
            // Check if the quiz was successfully saved and has an ID
            if (currentQuiz != null && currentQuiz.getId() > 0) {
                quizController.addQuestionToQuiz(mcQuestion, currentQuiz.getId());
                questions.add(mcQuestion);
                refreshQuestionsPanel();
            } else {
                JOptionPane.showMessageDialog(this, "Quiz must be saved before adding questions.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    
    
    private void addTrueFalseQuestion(ActionEvent e) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JTextField questionField = new JTextField(20);
        JComboBox<String> correctAnswerBox = new JComboBox<>(new String[]{"True", "False"});
    
        panel.add(new JLabel("Question:"));
        panel.add(questionField);
        panel.add(new JLabel("Correct Answer:"));
        panel.add(correctAnswerBox);
    
        int result = JOptionPane.showConfirmDialog(this, panel, "Add True/False Question",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (questionField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in the question field.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            TrueOrFalseQuestion tfQuestion = new TrueOrFalseQuestion(
                questionField.getText().trim(),
                correctAnswerBox.getSelectedItem().toString()
            );
    
            if (currentQuiz == null || currentQuiz.getId() <= 0) {
                saveQuiz(null); // Make sure the quiz is saved and has an ID
            }
    
            if (currentQuiz != null && currentQuiz.getId() > 0) {
                quizController.addQuestionToQuiz(tfQuestion, currentQuiz.getId());
                questions.add(tfQuestion);
                refreshQuestionsPanel();
            }
        }
    }
    
    
    private void addEssayQuestion(ActionEvent e) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JTextField questionField = new JTextField(20);
    
        panel.add(new JLabel("Question:"));
        panel.add(questionField);
    
        int result = JOptionPane.showConfirmDialog(this, panel, "Add Essay Question",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (questionField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in the question field.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            EssayQuestion essayQuestion = new EssayQuestion(questionField.getText().trim());
    
            if (currentQuiz == null || currentQuiz.getId() <= 0) {
                saveQuiz(null); // Make sure the quiz is saved and has an ID
            }
    
            if (currentQuiz != null && currentQuiz.getId() > 0) {
                quizController.addQuestionToQuiz(essayQuestion, currentQuiz.getId());
                questions.add(essayQuestion);
                refreshQuestionsPanel();
            }
        }
    }
    
    

    public static void main(String[] args) {
        QuizController controller = new QuizController(); // Make sure this is properly implemented
        new QuizCreationView(controller).setVisible(true);
    }
}
