package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import Model.Assignment;

public class StudentAssignmentView extends JFrame {
    private JList<Assignment> assignmentsList;
    private DefaultListModel<Assignment> assignmentsListModel;
    private JButton attachButton;
    private JButton submitButton;
    private File attachedFile;
    private JTextArea detailsTextArea;
    
    public StudentAssignmentView() {
        setTitle("Student Assignment View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeUI();
        loadDummyData();
    }

    private void initializeUI() {
        assignmentsListModel = new DefaultListModel<>();
        assignmentsList = new JList<>(assignmentsListModel);
        assignmentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        assignmentsList.addListSelectionListener(this::assignmentSelected);

        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        attachButton = new JButton("Attach File");
        submitButton = new JButton("Submit Assignment");
        attachButton.setEnabled(false);
        submitButton.setEnabled(false);
        actionPanel.add(attachButton);
        actionPanel.add(submitButton);
        detailsPanel.add(actionPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(assignmentsList), detailsPanel);
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);

        attachButton.addActionListener(this::handleAttachFile);
        submitButton.addActionListener(this::handleSubmitAssignment);
    }

    private void loadDummyData() {
        assignmentsListModel.addElement(new Assignment(1, "Math Homework", "Complete exercises 1-10 on page 45", "2024-05-01", true));
        assignmentsListModel.addElement(new Assignment(2, "Science Project", "Prepare a presentation on renewable energy", "2024-05-15", true));
        assignmentsListModel.addElement(new Assignment(3, "Literature Essay", "Write an essay on 'To Kill a Mockingbird'", "2024-04-30", true));
    }

    private void assignmentSelected(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Assignment selectedAssignment = assignmentsList.getSelectedValue();
            if (selectedAssignment != null) {
                detailsTextArea.setText(
                    "ID: " + selectedAssignment.getId() + "\n" +
                    "Name: " + selectedAssignment.getName() + "\n" +
                    "Details: " + selectedAssignment.getDescription() + "\n" +
                    "Due Date: " + selectedAssignment.getDueDate()
                );
                attachButton.setEnabled(true);
                submitButton.setEnabled(true);
            }
        }
    }

    private void handleAttachFile(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            attachedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Attached: " + attachedFile.getName(), "File Attached", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void handleSubmitAssignment(ActionEvent e) {
        if (attachedFile == null) {
            JOptionPane.showMessageDialog(this, "Please attach a file before submitting!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Assignment selectedAssignment = assignmentsList.getSelectedValue();
        if (selectedAssignment != null) {
            JOptionPane.showMessageDialog(this, "Submitted assignment ID " + selectedAssignment.getId() + " with file " + attachedFile.getName(), "Submission Complete", JOptionPane.INFORMATION_MESSAGE);
            attachedFile = null; // Reset the attached file after submission
        }
    }

    public static void main(String[] args) {
        StudentAssignmentView frame = new StudentAssignmentView();
        frame.setVisible(true);
    }
}
