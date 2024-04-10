package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.AssignmentController;
import Model.Assignment;

/**
 * The AssignmentView class represents a graphical user interface for managing assignment details.
 */
public class AssignmentView extends JPanel {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel descriptionLabel;
    private JTextField descriptionTextField;
    private JLabel dueDateLabel;
    private JTextField dueDateTextField;
    private AssignmentController assignmentController;

    /**
     * Constructs a new AssignmentView object.
     */
    public AssignmentView() {
        super();
        // Initialize the AssignmentController
        assignmentController = new AssignmentController();

        // Initialize labels
        nameLabel = new JLabel("Name: ");
        nameTextField = new JTextField(20); // Text field for name
        descriptionLabel = new JLabel("Description: ");
        descriptionTextField = new JTextField(20); // Text field for description
        dueDateLabel = new JLabel("Due Date: ");
        dueDateTextField = new JTextField(20); // Text field for due date

        // Create buttons
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton updateButton = new JButton("Update");
        JButton viewButton = new JButton("View");
        JButton submitButton = new JButton("Submit");

        // Create a panel to hold the labels, text fields, and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(descriptionLabel);
        panel.add(descriptionTextField);
        panel.add(dueDateLabel);
        panel.add(dueDateTextField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(updateButton);
        panel.add(viewButton);
        panel.add(submitButton);

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call method to add assignment
                int id = 0; // Assuming id is 0 for now
                String name = nameTextField.getText();
                String description = descriptionTextField.getText();
                String dueDate = dueDateTextField.getText();
                Assignment newAssignment = new Assignment(id, name, description, dueDate);
                assignmentController.addAssignment(newAssignment);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call method to remove assignment
                int id = 0; // Assuming id is 0 for now
                assignmentController.removeAssignment(id);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call method to update assignment
                int id = 0; // Assuming id is 0 for now
                assignmentController.updateAssignment(new Assignment(id, nameTextField.getText(), descriptionTextField.getText(), dueDateTextField.getText()));
            }
        });

        // viewButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         // Call method to view assignment
        //         int id = 0; // Assuming id is 0 for now
        //         String name = nameTextField.getText();
        //         String description = descriptionTextField.getText();
        //         String dueDate = dueDateTextField.getText();
        //         Assignment newAssignment = new Assignment(id, name, description, dueDate);
        //         assignmentController.viewAssignment(newAssignment);
        //     }
        // });

        // submitButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         // Call method to submit assignment
        //         assignmentController.submitAssignment();
        //     }
        // });

        // Add the panel to the MainView
        add(panel);
    }

    /**
     * The main method to launch the AssignmentView.
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { new AssignmentView(); });
    }
}
