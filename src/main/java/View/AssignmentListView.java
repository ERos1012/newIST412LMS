package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Controller.AssignmentController;
import java.awt.*;
import java.util.List;
import Model.Assignment;

public class AssignmentListView extends JPanel {
    private AssignmentController manager;
    private JList<Assignment> assignmentJList;
    private DefaultListModel<Assignment> listModel;
    private JTable activeCoursesTable;
    private DefaultTableModel activeCoursesTableModel;

    public AssignmentListView(AssignmentController manager) {
        this.manager = manager;
        this.listModel = new DefaultListModel<>();
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton updateButton = new JButton("Update");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add button action
        addButton.addActionListener(e -> showAssignmentDialog(null));

        // Remove button action
        removeButton.addActionListener(e -> showRemoveAssignmentDialog());

        // Update button action
        updateButton.addActionListener(e -> {
            Assignment selected = assignmentJList.getSelectedValue();
            if (selected != null) {
                showAssignmentDialog(selected);
            }
        });

        // Panel to display active courses
        JPanel activeCoursesPanel = new JPanel(new BorderLayout());
        activeCoursesTableModel = new DefaultTableModel();
        activeCoursesTableModel.addColumn("ID");
        activeCoursesTableModel.addColumn("Name");
        activeCoursesTableModel.addColumn("Due Date");
        activeCoursesTable = new JTable(activeCoursesTableModel);
        activeCoursesPanel.add(new JScrollPane(activeCoursesTable), BorderLayout.CENTER);
        add(activeCoursesPanel, BorderLayout.CENTER);

        refreshAssignmentList();
        refreshActiveCoursesTable();
    }

    private void showAssignmentDialog(Assignment assignment) {
        // Text fields pre-populated with assignment data if updating, empty if adding
        JTextField nameField = new JTextField(assignment != null ? assignment.getName() : "", 20);
        JTextField descField = new JTextField(assignment != null ? assignment.getDescription() : "", 20);
        JTextField dateField = new JTextField(assignment != null ? assignment.getDueDate() : "", 20);

        // Set up the panel to get user input
        JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
        dialogPanel.add(new JLabel("Name:"));
        dialogPanel.add(nameField);
        dialogPanel.add(new JLabel("Description:"));
        dialogPanel.add(descField);
        dialogPanel.add(new JLabel("Due Date:"));
        dialogPanel.add(dateField);

        // Show a confirm dialog to get user input
        int result = JOptionPane.showConfirmDialog(this, dialogPanel,
                assignment != null ? "Update Assignment" : "Add Assignment",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (assignment == null) {
                // Create a new Assignment object with the input data
                Assignment newAssignment = new Assignment(); // Assuming there's a default constructor
                newAssignment.setName(nameField.getText());
                newAssignment.setDescription(descField.getText());
                newAssignment.setDueDate(dateField.getText());
                // Add the new assignment using the controller
                manager.addAssignment(newAssignment);
            } else {
                // Update the current assignment object with the new data
                assignment.setName(nameField.getText());
                assignment.setDescription(descField.getText());
                assignment.setDueDate(dateField.getText());
                // Update the assignment using the controller
                manager.updateAssignment(assignment);
            }
            // Refresh the list to show changes
            refreshAssignmentList();
            refreshActiveCoursesTable();
        }
    }

    private void showRemoveAssignmentDialog() {
        // Text field to input assignment ID
        JTextField idField = new JTextField(10);

        // Set up the panel to get user input
        JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
        dialogPanel.add(new JLabel("Assignment ID:"));
        dialogPanel.add(idField);

        // Show a confirm dialog to get user input
        int result = JOptionPane.showConfirmDialog(this, dialogPanel,
                "Remove Assignment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Get the ID entered by the user
            int assignmentId;
            try {
                assignmentId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid assignment ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Remove the assignment with the entered ID
            manager.removeAssignment(assignmentId);
            refreshAssignmentList();
            refreshActiveCoursesTable();
        }
    }


    private void refreshAssignmentList() {
        listModel.removeAllElements();
        List<Assignment> assignments = manager.getAllAssignments();
        for (Assignment assignment : assignments) {
            listModel.addElement(assignment);
        }
    }

    private void refreshActiveCoursesTable() {
        activeCoursesTableModel.setRowCount(0); // Clear existing rows
        List<Assignment> activeAssignments = manager.getActiveAssignments(); // Assuming a method in AssignmentController to fetch active assignments
        for (Assignment assignment : activeAssignments) {
            activeCoursesTableModel.addRow(new Object[] {assignment.getId(), assignment.getName(), assignment.getDueDate()});
        }
    }
}
