package View;

import javax.swing.*;

import Controller.CourseController;
import Controller.GradeController;
import Controller.LoginController;
import Controller.QuizController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MainView class represents the home page of the application with a
 * navigation bar
 * to direct users to different functionalities like Assignment, Course, Grade,
 * Message, and Quiz views,
 * displaying them under the navigation bar and inside the same window.
 */
public class MainView extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout); // Panel that contains different views
    private LoginController loginController;
    private int userId; // To store the logged-in user's ID
    private String userType = ""; // To keep track of the user type

    public MainView() {
        super("Group 4 LMS");
        userType = "";
        loginController = new LoginController();
        initializeUI();
    }

    private void initializeUI() {
        // Navigation panel setup
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Buttons for each view
        JButton dashboardButton = new JButton("Dashboard");
        JButton courseButton = new JButton("Courses");
        JButton gradeButton = new JButton("Grades");
        JButton messageButton = new JButton("Messages");
        JButton quizButton = new JButton("Quizzes");
        JButton logoutButton = new JButton("Logout");

        // Add buttons to the navigation panel
        navigationPanel.add(dashboardButton);
        navigationPanel.add(courseButton);
        navigationPanel.add(gradeButton);
        navigationPanel.add(quizButton);
        navigationPanel.add(messageButton);
        navigationPanel.add(logoutButton);

        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView(loginController);

        loginView.setLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userId = loginController.getUserId();
                // Get the user type from the login controller
                userType = loginController.getUserType();

                // Switch to the appropriate dashboard view based on the user type
                if (userType.equals("teacher")) {
                    cardLayout.show(cardsPanel, "Dashboard");
                } else if (userType.equals("student")) {
                    cardLayout.show(cardsPanel, "StudentDashboard");
                    JPanel studentCourseView = new StudentCourseView(new CourseController(), userId);
                    cardsPanel.add(studentCourseView, "StudentCourse");
                    JPanel studentQuizView = new StudentQuizView(new QuizController(), userId);
                    cardsPanel.add(studentQuizView, "StudentQuiz");
                    JPanel studentGradeView = new StudentGradeView(userId);
                    cardsPanel.add(studentGradeView, "StudentGrade");
                }


                // Show the navigation panel after login
                navigationPanel.setVisible(true);
            }
        });


        navigationPanel.setVisible(false); // Hide navigation panel initially

        // Add LoginView to the cardsPanel
        cardsPanel.add(loginView, "Login");

        // Instantiate and add views based on user type
        JPanel dashboardView = new DashboardView();
        cardsPanel.add(dashboardView, "Dashboard");

        JPanel courseView = new CourseView(new CourseController());
        cardsPanel.add(courseView, "Course");

        // Instantiate GradeController
        GradeController gradeController = new GradeController();

        // Create GradeView with GradeController
        JPanel gradeView = new GradeView(gradeController);

        // Add gradeView to cardsPanel
        cardsPanel.add(gradeView, "Grade");

        // Instantiate and add quiz views for both teacher and student
        QuizController quizController = new QuizController();
        JPanel teacherQuizView = new TeacherQuizView(quizController);
        cardsPanel.add(teacherQuizView, "TeacherQuiz");

        // Instantiate and add student views
        JPanel studentDashboardView = new StudentDashboardView();
        cardsPanel.add(studentDashboardView, "StudentDashboard");



//         JPanel studentQuizView = new StudentQuizView();
//         cardsPanel.add(studentQuizView, "StudentQuiz");

        // Set action listeners to switch views based on user type
        dashboardButton.addActionListener(e -> {
            if (userType.equals("teacher")) {
                cardLayout.show(cardsPanel, "Dashboard");
            } else if (userType.equals("student")) {
                cardLayout.show(cardsPanel, "StudentDashboard");
            }
        });

        courseButton.addActionListener(e -> {
            if (userType.equals("teacher")) {
                cardLayout.show(cardsPanel, "Course");
            } else if (userType.equals("student")) {
                cardLayout.show(cardsPanel, "StudentCourse");
                System.out.println(this.userId);
            }
        });

        gradeButton.addActionListener(e -> {
            if (userType.equals("teacher")) {
                cardLayout.show(cardsPanel, "Grade");
            } else if (userType.equals("student")) {
                cardLayout.show(cardsPanel, "StudentGrade");
            }
        });

        quizButton.addActionListener(
                e -> cardLayout.show(cardsPanel, userType.equals("teacher") ? "TeacherQuiz" : "StudentQuiz"));

        messageButton.addActionListener(e -> {
            if (userType.equals("teacher")) {
                // Create a new instance of MessageView
                MessageView messageView = new MessageView(userId, userType);

                // Show the MessageView as a modal dialog
                JDialog dialog = new JDialog(MainView.this, "Message", true);
                dialog.add(messageView);
                dialog.setSize(500, 400);
                dialog.setLocationRelativeTo(MainView.this);
                dialog.setVisible(true);
            } else if (userType.equals("student")) {
                // Create a new instance of MessageView
                StudentMessageView studentMessageView = new StudentMessageView(userId, userType);

                // Show the MessageView as a modal dialog
                JDialog dialog = new JDialog(MainView.this, "Message", true);
                dialog.add(studentMessageView);
                dialog.setSize(500, 400);
                dialog.setLocationRelativeTo(MainView.this);
                dialog.setVisible(true);
            }
        });

        logoutButton.addActionListener(e -> {
            // Hide the navigation panel and switch to the LoginView
            navigationPanel.setVisible(false);
            cardLayout.show(cardsPanel, "Login");
            userType = ""; // Clear the user type upon logout
        });


        // Set up the main frame layout
        setLayout(new BorderLayout());
        add(navigationPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);

        // Frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void setupViewSwitchers(JButton dashboardButton, JButton courseButton, JButton gradeButton, JButton quizButton, JButton messageButton) {
        dashboardButton.addActionListener(e -> cardLayout.show(cardsPanel, userType.equals("teacher") ? "Dashboard" : "StudentDashboard"));
        gradeButton.addActionListener(e -> cardLayout.show(cardsPanel, userType.equals("teacher") ? "Grade" : "StudentGrade"));
        quizButton.addActionListener(e -> cardLayout.show(cardsPanel, userType.equals("teacher") ? "Quiz" : "StudentQuiz"));
        messageButton.addActionListener(e -> showMessageView());
    }

    private void showMessageView() {
        if (!userType.isEmpty()) {
            MessageView messageView = new MessageView(userId, userType); // Initialize with user ID and type
            JDialog dialog = new JDialog(this, "Message", true);
            dialog.add(messageView);
            dialog.setSize(500, 400);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }
}
