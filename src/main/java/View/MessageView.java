package View;

import javax.swing.*;
import Controller.MessageController;
import Model.EmailAPI;
import Model.Message;
import java.awt.*;

public class MessageView extends JFrame {
    private JLabel idLabel;
    private JLabel senderIdLabel;
    private JLabel receiverIdLabel;
    private JLabel messageLabel;
    private JLabel dateLabel;
    private JTextField emailTextField;
    private JTextField senderNameTextField; // Text field for sender's name
    private JTextArea messageTextArea;
    private JButton sendEmailButton;
    private MessageController messageController;
    private JLabel statusLabel;

    public MessageView() {
        super("Message View");

        // Initialize the MessageController
        messageController = new MessageController();

        // Initialize labels
        idLabel = new JLabel("ID: ");
        senderIdLabel = new JLabel("Sender ID: ");
        receiverIdLabel = new JLabel("Receiver ID: ");
        dateLabel = new JLabel("Date: ");
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);

        // Initialize text fields for email and sender's name
        emailTextField = new JTextField(20);
        emailTextField.setToolTipText("Enter recipient's email");
        senderNameTextField = new JTextField(20);
        senderNameTextField.setToolTipText("Enter sender's name");

        // Initialize text area for the message
        messageTextArea = new JTextArea(5, 20);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);

        // Initialize and add the Send Email button
        sendEmailButton = new JButton("Send Email");
        sendEmailButton.addActionListener(e -> onSendEmailClicked());

        // Create a panel to hold the labels, text fields, and button
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(idLabel);
        panel.add(senderIdLabel);
        panel.add(receiverIdLabel);
        panel.add(dateLabel);
        panel.add(new JLabel("Sender's Name:"));
        panel.add(senderNameTextField);
        panel.add(new JLabel("Recipient's Email:"));
        panel.add(emailTextField);
        panel.add(new JLabel("Message:"));
        panel.add(messageScrollPane);
        panel.add(sendEmailButton);
        panel.add(statusLabel);

        // Add the panel to the frame
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void onSendEmailClicked() {
        System.out.println("Send Email button clicked");
        String receiverEmail = emailTextField.getText();
        String senderName = senderNameTextField.getText();  // Retrieve the sender's name from the text field
        String messageContent = messageTextArea.getText();

        if (!receiverEmail.isEmpty() && !senderName.isEmpty() && !messageContent.isEmpty()) {
            try {
                EmailAPI.sendEmail(senderName, receiverEmail, messageContent);
                System.out.println("Email sent to: " + receiverEmail + " with message: " + messageContent);
                statusLabel.setForeground(Color.GREEN);
                statusLabel.setText("Email sent successfully to: " + receiverEmail);
            } catch (Exception e) {
                System.out.println("Failed to send email: " + e.getMessage());
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Failed to send email: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to send email: Invalid sender name, email address, or message content.");
            statusLabel.setText("Invalid sender name, email address, or message content.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MessageView::new);
    }
}
