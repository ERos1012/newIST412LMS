package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controller.AssignmentController;
import Controller.CourseController;
import java.awt.*;
import java.util.List;
import Model.Course;

public class StudentCourseView extends JPanel {
    private CourseController manager;
    private AssignmentController assignmentController;
    private JList<Course> courseJList;
    private DefaultListModel<Course> listModel;
    private JTable activeCoursesTable;
    private DefaultTableModel activeCoursesTableModel;
    private int userId;

    public StudentCourseView(CourseController manager, int userId) {
        super();
        this.userId = userId;
        this.manager = manager;
        this.listModel = new DefaultListModel<>();
        setLayout(new BorderLayout());
        initializeUI();
    }


    private void initializeUI() {

        JLabel headerLabel = new JLabel("Your Courses", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(headerLabel, BorderLayout.NORTH);

        System.out.println("student course view ID: " + userId);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton viewButton = new JButton("View Course");

//        buttonPanel.add(addButton);
//        buttonPanel.add(removeButton);
//        buttonPanel.add(updateButton);
        buttonPanel.add(viewButton);
        add(buttonPanel, BorderLayout.SOUTH);
//
//        // Add button action
//        addButton.addActionListener(e -> showCourseDialog(null));
//
//        // Remove button action
//        removeButton.addActionListener(e -> showRemoveCourseDialog());
//
//        // Update button action
//        updateButton.addActionListener(e -> {
//            // Create a dialog to enter the course ID
//            JTextField idField = new JTextField(10);
//            JPanel idPanel = new JPanel();
//            idPanel.add(new JLabel("ID:"));
//            idPanel.add(idField);
//
//            int result = JOptionPane.showConfirmDialog(this, idPanel, "Enter Course ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//            if (result == JOptionPane.OK_OPTION) {
//                String idText = idField.getText().trim();
//                if (!idText.isEmpty()) {
//                    int id = Integer.parseInt(idText);
//                    Course course = manager.getCourse(id);
//                    if (course != null) {
//                        showCourseDialog(course);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "Please enter Course ID!", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });

        // View button action
        viewButton.addActionListener(e -> viewSelectedCourse());

        // Panel to display active courses
        JPanel activeCoursesPanel = new JPanel(new BorderLayout());
        activeCoursesTableModel = new DefaultTableModel();
        activeCoursesTableModel.addColumn("ID");
        activeCoursesTableModel.addColumn("Name");
        activeCoursesTableModel.addColumn("Program");
        activeCoursesTableModel.addColumn("Instructor");
        activeCoursesTable = new JTable(activeCoursesTableModel);
        activeCoursesPanel.add(new JScrollPane(activeCoursesTable), BorderLayout.CENTER);
        // Add the activeCoursesPanel to the main panel
        add(activeCoursesPanel, BorderLayout.CENTER);


        refreshCourseList();
        refreshActiveCoursesTable();

    }

    private void viewSelectedCourse() {
        int selectedRow = activeCoursesTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) activeCoursesTableModel.getValueAt(selectedRow, 0);
            Course course = manager.getCourse(id);
            if (course != null) {
                // Create a new SelectedCourseView instance with the selected course
                StudentSelectedCourseView studentSelectedCourseView = new StudentSelectedCourseView(course, assignmentController, this.userId);

                // Replace the current panel with the SelectedCourseView
                removeAll();
                setLayout(new BorderLayout());
                add(studentSelectedCourseView, BorderLayout.CENTER);
                revalidate();
                repaint();
                System.out.println(userId);
            } else {
                JOptionPane.showMessageDialog(this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course from the list.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showCourseDialog(Course course) {
        // Text fields pre-populated with course data if updating, empty if adding
        JTextField nameField = new JTextField(course != null ? course.getName() : "", 20);
        JTextField programField = new JTextField(course != null ? course.getProgram() : "", 20);
        JTextField instructorField = new JTextField(course != null ? course.getInstructor() : "", 20);

        // Set up the panel to get user input
        JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
        dialogPanel.add(new JLabel("Name:"));
        dialogPanel.add(nameField);
        dialogPanel.add(new JLabel("Program:"));
        dialogPanel.add(programField);
        dialogPanel.add(new JLabel("Instructor:"));
        dialogPanel.add(instructorField);

        // Show a confirm dialog to get user input
        int result = JOptionPane.showConfirmDialog(this, dialogPanel,
                course != null ? "Update Course" : "Add Course",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (course == null) {
                // Create a new Course object with the input data
                Course newCourse = new Course(); // Assuming there's a default constructor
                newCourse.setName(nameField.getText());
                newCourse.setProgram(programField.getText());
                newCourse.setInstructor(instructorField.getText());
                // Add the new course using the controller
                manager.addCourse(newCourse);
            } else {
                // Update the current course object with the new data
                course.setName(nameField.getText());
                course.setProgram(programField.getText());
                course.setInstructor(instructorField.getText());
                // Update the course using the controller
                manager.updateCourse(course);
            }
            // Refresh the list to show changes
            refreshCourseList();
            refreshActiveCoursesTable();
        }
    }

    private void showRemoveCourseDialog() {
        // Text field to input course ID
        JTextField idField = new JTextField(10);

        // Set up the panel to get user input
        JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
        dialogPanel.add(new JLabel("Course ID:"));
        dialogPanel.add(idField);

        // Show a confirm dialog to get user input
        int result = JOptionPane.showConfirmDialog(this, dialogPanel,
                "Remove Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Get the ID entered by the user
            int courseId;
            try {
                courseId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid course ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Remove the course with the entered ID
            manager.removeCourse(courseId);
            refreshCourseList();
            refreshActiveCoursesTable();
        }
    }

    private void refreshCourseList() {
        listModel.removeAllElements();
        List<Course> courses = manager.getAllCourses();
        for (Course course : courses) {
            listModel.addElement(course);
        }
    }

    private void refreshActiveCoursesTable() {
        activeCoursesTableModel.setRowCount(0); // Clear existing rows
        List<Course> activeCourses = manager.getActiveCourses();
        for (Course course : activeCourses) {
            activeCoursesTableModel.addRow(new Object[]{course.getId(), course.getName(), course.getProgram(), course.getInstructor(), "View"});
        }
    }
}
