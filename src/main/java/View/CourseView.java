package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Controller.CourseController;
import Model.Course;

/**
 * The CourseView class represents a graphical user interface for displaying course details.
 */
public class CourseView extends JPanel {
    private JTextArea courseTextArea = new JTextArea();
    private JButton addButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton viewButton;
    private final CourseController courseController;

    /**
     * Constructs a new CourseView object.
     */
    public CourseView(CourseController courseController) {
        super();
        this.courseController = courseController;

        initializeUI();
        updateCourseDetails();
    }

    /**
     * Initializes the UI components.
     */
    private void initializeUI() {
        // Text fields for entering course details
        JTextField nameField = new JTextField(20);
        JTextField idField = new JTextField(10);
        JTextField programField = new JTextField(20);
        JTextField instructorField = new JTextField(20);

        // Add button to trigger course addition
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve course details from text fields
                String name = nameField.getText().trim();
                String idText = idField.getText().trim();
                String program = programField.getText().trim();
                String instructor = instructorField.getText().trim();

                // Check if any field is empty
                if (name.isEmpty() || idText.isEmpty() || program.isEmpty() || instructor.isEmpty()) {
                    JOptionPane.showMessageDialog(CourseView.this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit without adding the course
                }

                // Parse ID to integer
                int id;
                try {
                    id = Integer.parseInt(idText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CourseView.this, "Invalid ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit without adding the course
                }

                // Create new course object
                Course newCourse = new Course(name, id, program, instructor);

                // Add course to the system
                courseController.addCourse(newCourse);
                System.out.println("Course added: Name - " + name + ", ID - " + id + ", Program - " + program + ", Instructor - " + instructor);
                // Update course details after adding
                updateCourseDetails();
            }
        });

        // View button to display course details
        viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the Course Controller to view course details
                courseController.viewCourse(new Course("IST", 120, "Engineering", "Dr. Lee"));
            }
        });

        // Update button to update course details
        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseController.updateCourse(new Course("Math", 101, "Engineering", "Dr. Smith"));
            }
        });

        // Remove button to remove a course
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseController.removeCourse(101);
                // Update course details after removing
                updateCourseDetails();
            }
        });

        // Panel to hold text fields and buttons for adding, viewing, updating, and removing courses
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Course Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Course ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Program:"));
        inputPanel.add(programField);
        inputPanel.add(new JLabel("Instructor:"));
        inputPanel.add(instructorField);
        inputPanel.add(addButton);
        inputPanel.add(viewButton);
        inputPanel.add(updateButton);
        inputPanel.add(removeButton);

        // Add the input panel to the CourseView
        add(inputPanel, BorderLayout.NORTH);
    }

    /**
     * Updates the UI to display course details using separate panels for each course.
     */
    private void updateCourseDetails() {
        // Create a panel to hold all course panels
        JPanel coursesPanel = new JPanel();
        coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS));

        // Get all courses
        List<Course> courses = courseController.getAllCourses();
        for (Course course : courses) {
            // Create a panel for each course
            JPanel coursePanel = new JPanel();
            coursePanel.setLayout(new BorderLayout());

            // Create a label to display course details
            JLabel courseLabel = new JLabel("Name: " + course.getName() + ", ID: " + course.getId() +
                    ", Program: " + course.getProgram() + ", Professor: " + course.getInstructor());

            // Create a favorite/unfavorite button
            JButton favoriteButton = new JButton("Favorite");
            favoriteButton.addActionListener(new FavoriteButtonActionListener(course, favoriteButton));

            // Add course label and favorite/unfavorite button to the course panel
            coursePanel.add(courseLabel, BorderLayout.CENTER);
            coursePanel.add(favoriteButton, BorderLayout.EAST);

            // Add the course panel to the coursesPanel
            coursesPanel.add(coursePanel);
        }

        // Remove the old courseTextArea
        remove(courseTextArea);

        // Add the coursesPanel to the CourseView
        add(new JScrollPane(coursesPanel), BorderLayout.CENTER);

        // Refresh the CourseView
        revalidate();
        repaint();
    }

    /**
     * Action listener for favorite/unfavorite button.
     */
    private class FavoriteButtonActionListener implements ActionListener {
        private Course course;
        private JButton button;
        private boolean isFavorite;

        public FavoriteButtonActionListener(Course course, JButton button) {
            this.course = course;
            this.button = button;
            this.isFavorite = false;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            isFavorite = !isFavorite;
            if (isFavorite) {
                button.setText("Favorited");
                // Change button color, icon, etc. to indicate favorited state
            } else {
                button.setText("Favorite");
                // Change button color, icon, etc. to indicate unfavorited state
            }

            // Handle favorite/unfavorite action accordingly
            if (isFavorite) {
                // Add course to favorites
                System.out.println("Course added to favorites: " + course.getName());
            } else {
                // Remove course from favorites
                System.out.println("Course removed from favorites: " + course.getName());
            }
        }
    }

    /**
     * The main method to launch the CourseView.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        CourseController courseController = new CourseController();
        SwingUtilities.invokeLater(() -> new CourseView(courseController));
    }
}
