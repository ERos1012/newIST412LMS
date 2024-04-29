package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import Model.Quiz;
import Model.TrueOrFalseQuestion;
import Model.Question;
import Model.Answer;
import Model.EssayQuestion;
import Model.MultipleChoiceQuestion;
import Controller.AnswerController;
import Controller.QuizCompletionListener;
import Controller.QuizController;

public class QuizTakingView extends JFrame {
    private Quiz quiz;
    private int currentQuestionIndex = 0; // Changed from static to instance variable
    private JLabel questionLabel;
    private JPanel choicesPanel;
    private ButtonGroup choicesGroup;
    private JButton nextButton;
    private AnswerController answerController;
    private QuizController quizController;
    private List<QuizCompletionListener> listeners = new ArrayList<>();

    public QuizTakingView(Quiz quiz, AnswerController answerController, QuizController quizController) {
        if (quiz.isDone()) {
            JOptionPane.showMessageDialog(null, "You have already taken this quiz.", "Quiz Unavailable",
                    JOptionPane.ERROR_MESSAGE);
            return; // Exit the constructor to prevent the quiz window from opening
        }
        this.quiz = quiz;
        this.answerController = answerController;
        this.quizController = quizController;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Taking Quiz: " + quiz.getName());
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        questionLabel = new JLabel("", JLabel.CENTER);
        questionLabel.setFont(new Font("Serif", Font.BOLD, 16));

        choicesPanel = new JPanel();
        choicesPanel.setLayout(new GridLayout(5, 1)); // 5 possible choices, change as needed

        nextButton = new JButton("Next Question");
        nextButton.addActionListener(this::handleNextQuestion);

        add(questionLabel, BorderLayout.NORTH);
        add(choicesPanel, BorderLayout.CENTER);
        add(nextButton, BorderLayout.SOUTH);

        loadQuestion();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestionIndex < quiz.getQuestions().size()) {
            Question currentQuestion = quiz.getQuestions().get(currentQuestionIndex);
            questionLabel
                    .setText("<html>Q" + (currentQuestionIndex + 1) + ": " + currentQuestion.getText() + "</html>");

            switch (currentQuestion.getClass().getSimpleName()) {
                case "MultipleChoiceQuestion":
                    loadMultipleChoiceQuestion((MultipleChoiceQuestion) currentQuestion);
                    break;
                case "TrueOrFalseQuestion":
                    loadTrueOrFalseQuestion((TrueOrFalseQuestion) currentQuestion);
                    break;
                case "EssayQuestion":
                    loadEssayQuestion((EssayQuestion) currentQuestion);
                    break;
            }

            revalidate();
            repaint();
        } else {
            endQuiz();
        }
    }

    private void loadMultipleChoiceQuestion(MultipleChoiceQuestion question) {
        choicesPanel.removeAll();
        choicesGroup = new ButtonGroup();
        for (String choice : question.getChoices()) {
            JRadioButton choiceButton = new JRadioButton(choice);
            choicesGroup.add(choiceButton);
            choicesPanel.add(choiceButton);
        }
    }

    private void loadTrueOrFalseQuestion(TrueOrFalseQuestion question) {
        choicesPanel.removeAll();
        choicesGroup = new ButtonGroup();
        List<String> choices = List.of("True", "False");
        for (String choice : choices) {
            JRadioButton choiceButton = new JRadioButton(choice);
            choicesGroup.add(choiceButton);
            choicesPanel.add(choiceButton);
        }
    }

    private void loadEssayQuestion(EssayQuestion question) {
        choicesPanel.removeAll();
        JTextArea textArea = new JTextArea(5, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        choicesPanel.add(scrollPane);
    }

    private void handleNextQuestion(ActionEvent e) {
        if (currentQuestionIndex < quiz.getQuestions().size()) {
            // Get selected answer
            String selectedAnswer = getSelectedAnswer();
            if (selectedAnswer == null) {
                JOptionPane.showMessageDialog(this, "Please select an option.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Save the answer using AnswerController
            Question currentQuestion = quiz.getQuestions().get(currentQuestionIndex);
            Answer answer = new Answer(0, currentQuestion.getQuestionId(), 0, selectedAnswer, false); // Simplified
            answerController.saveAnswer(answer);

            currentQuestionIndex++;
            loadQuestion();
        } else {
            endQuiz();
        }
    }

    private String getSelectedAnswer() {
        return Collections.list(choicesGroup.getElements()).stream()
                .filter(AbstractButton::isSelected)
                .findFirst()
                .map(AbstractButton::getText)
                .orElse(null);
    }

    private void endQuiz() {
        quizController.markQuizAsCompleted(quiz.getId());
        notifyQuizCompletion();
        JOptionPane.showMessageDialog(this, "You have completed the quiz!", "Quiz Completed",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
        currentQuestionIndex = 0; // Reset the index for future quizzes
    }

    private void notifyQuizCompletion() {
        for (QuizCompletionListener listener : listeners) {
            listener.quizCompleted();
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz(1, 0, "Sample Quiz", "2021-01-01", null, true, false);
        AnswerController answerController = new AnswerController();
        QuizController quizController = new QuizController();
        new QuizTakingView(quiz, answerController, quizController);
    }
}
