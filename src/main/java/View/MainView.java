package View;

import javax.swing.*;
import Controller.CourseController;
import Controller.LoginController;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainView extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout); // Panel that contains different views
    private LoginController loginController;
    private int userId; // To store the logged-in user's ID
    private String userType = ""; // To keep track of the user type

    public MainView() {
        super("Group 4 LMS");
        loginController = new LoginController();
        initializeUI();
    }

    private void initializeUI() {
        // Navigation panel setup
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton dashboardButton = new JButton("Dashboard");
        JButton courseButton = new JButton("Course");
        JButton gradeButton = new JButton("Grade");
        JButton messageButton = new JButton("Message");
        JButton quizButton = new JButton("Quiz");

        // Add buttons to the navigation panel
        navigationPanel.add(dashboardButton);
        navigationPanel.add(courseButton);
        navigationPanel.add(gradeButton);
        navigationPanel.add(messageButton);
        navigationPanel.add(quizButton);

        LoginView loginView = new LoginView(loginController);
        loginView.setLoginListener(e -> {
            userId = loginController.getUserId(); // Fetch the user ID
            userType = loginController.getUserType(); // Fetch the user type

            if (userType.equals("teacher")) {
                cardLayout.show(cardsPanel, "Dashboard");
            } else if (userType.equals("student")) {
                cardLayout.show(cardsPanel, "StudentDashboard");
            }
            navigationPanel.setVisible(true); // Show the navigation panel after login
        });

        navigationPanel.setVisible(false); // Hide navigation panel initially
        cardsPanel.add(loginView, "Login");

        // Instantiate and add views based on user type
        JPanel dashboardView = new DashboardView();
        cardsPanel.add(dashboardView, "Dashboard");
        JPanel courseView = new CourseView(new CourseController());
        cardsPanel.add(courseView, "Course");
        JPanel gradeView = new GradeView(new Model.Grade());
        cardsPanel.add(gradeView, "Grade");
        JPanel quizView = new QuizView();
        cardsPanel.add(quizView, "Quiz");
        JPanel studentDashboardView = new StudentDashboardView();
        cardsPanel.add(studentDashboardView, "StudentDashboard");
        JPanel studentCourseView = new StudentCourseView(new CourseController());
        cardsPanel.add(studentCourseView, "StudentCourse");
        JPanel studentGradeView = new StudentGradeView(new Model.Grade());
        cardsPanel.add(studentGradeView, "StudentGrade");

        // Set action listeners to switch views
        setupViewSwitchers(dashboardButton, courseButton, gradeButton, quizButton, messageButton);

        // Set up the main frame layout
        setLayout(new BorderLayout());
        add(navigationPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void setupViewSwitchers(JButton dashboardButton, JButton courseButton, JButton gradeButton, JButton quizButton, JButton messageButton) {
        dashboardButton.addActionListener(e -> cardLayout.show(cardsPanel, userType.equals("teacher") ? "Dashboard" : "StudentDashboard"));
        courseButton.addActionListener(e -> cardLayout.show(cardsPanel, userType.equals("teacher") ? "Course" : "StudentCourse"));
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
