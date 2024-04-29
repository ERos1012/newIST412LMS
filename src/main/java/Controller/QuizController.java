package Controller;

import Model.Quiz;
import Model.TrueOrFalseQuestion;
import Model.EssayQuestion;
import Model.MultipleChoiceQuestion;
import Model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizController {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Darkless@1228"; 
    private static final String DATABASE_NAME = "412lms"; 
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE_NAME + "?useSSL=false";

    public QuizController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }

    public Quiz addOrUpdateQuiz(Quiz quiz) {
        if (quiz.getId() > 0) {
            return updateQuiz(quiz);
        } else {
            return addQuiz(quiz);
        }
    }

    public Quiz addQuiz(Quiz quiz) {
        final String sql = "INSERT INTO quiz (courseId, name, dueDate) VALUES (?, ?, ?);";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, quiz.getCourseId());
            pstmt.setString(2, quiz.getName());
            pstmt.setString(3, quiz.getDueDate());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    quiz.setId(generatedKeys.getInt(1));
                }
            }

            addQuizQuestions(quiz.getId(), quiz.getQuestions());
            return quiz;
        } catch (SQLException e) {
            System.err.println("Error adding quiz: " + e.getMessage());
            return null;
        }
    }

    public Quiz updateQuiz(Quiz quiz) {
        final String sql = "UPDATE quiz SET courseId = ?, name = ?, dueDate = ? WHERE id = ?;";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, quiz.getCourseId());
            pstmt.setString(2, quiz.getName());
            pstmt.setString(3, quiz.getDueDate());
            pstmt.setInt(4, quiz.getId());
            pstmt.executeUpdate();
            return quiz;
        } catch (SQLException e) {
            System.err.println("Error updating quiz: " + e.getMessage());
            return null;
        }
    }

    public void removeQuiz(Quiz quiz) {
        final String sql = "DELETE FROM quiz WHERE id = ?;";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, quiz.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error removing quiz: " + e.getMessage());
        }
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        final String sql = "SELECT id, courseId, name, dueDate FROM quiz;";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                List<Question> questions = getQuestionsForQuizId(rs.getInt("id"));
                Quiz quiz = new Quiz(
                    rs.getInt("id"),
                    rs.getInt("courseId"),
                    rs.getString("name"),
                    rs.getString("dueDate"),
                    questions
                );
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching quizzes: " + e.getMessage());
        }
        return quizzes;
    }

    private void addQuizQuestions(int quizId, List<Question> questions) {
        final String sql = "INSERT INTO quiz_questions (quiz_id, question_text, type) VALUES (?, ?, ?);";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (Question question : questions) {
                pstmt.setInt(1, quizId);
                pstmt.setString(2, question.getText());
                pstmt.setString(3, question.getClass().getSimpleName()); // Store the type of the question
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            System.err.println("Error adding quiz questions: " + e.getMessage());
        }
    }
    
    
    private List<Question> getQuestionsForQuizId(int quizId) {
        List<Question> questions = new ArrayList<>();
        final String questionSql = "SELECT question_text, correct_answer, type, choice_a, choice_b, choice_c, choice_d FROM quiz_questions WHERE quiz_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(questionSql)) {
            pstmt.setInt(1, quizId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String type = rs.getString("type");
                    Question question = null;
                    switch (type) {
                        case "MultipleChoice":
                            question = new MultipleChoiceQuestion(
                                rs.getString("question_text"),
                                rs.getString("choice_a"),
                                rs.getString("choice_b"),
                                rs.getString("choice_c"),
                                rs.getString("choice_d"),
                                rs.getString("correct_answer")
                            );
                            break;
                        case "TrueFalse":
                            question = new TrueOrFalseQuestion(
                                rs.getString("question_text"),
                                rs.getString("correct_answer")
                            );
                            break;
                        case "Essay":
                            question = new EssayQuestion(
                                rs.getString("question_text")
                            );
                            break;
                    }
                    if (question != null) {
                        questions.add(question);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching questions for quiz: " + e.getMessage());
        }
        return questions;
    }
    
     /**
     * Views details of a specific quiz.
     */
    public Quiz viewQuiz(Quiz quiz) {
        if (quiz == null) {
            return new Quiz(0, 0, "Default Quiz", "2024-01-01", new ArrayList<>());
        }
        // logic for retrieving and returning a Quiz object
        return quiz;
    }


    /**
     * Assigns a grade to a quiz for a student
     */
    public void gradeQuiz(int studentId, int courseId, int quizId, int grade)
    {
        System.out.println("Quiz graded: --> " + "Student number " + studentId + " has a grade of " + grade);
    }

    public void addQuestionToQuiz(Question question, int quizId) {
        if (question != null) {
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                question.addQuestionToDatabase(con, quizId);
            } catch (SQLException e) {
                System.err.println("Error adding question to quiz: " + e.getMessage());
            }
        }
    }
    
}
