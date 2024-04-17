package View;

import javax.swing.*;

import Controller.MessageController;
import Model.Message;

/**
 * The MessageView class represents a graphical user interface for displaying message details.
 */
public class MessageView extends JFrame{
    private JLabel idLabel;
    private JLabel senderIdLabel;
    private JLabel receiverIdLabel;
    private JLabel messageLabel;
    private JLabel dateLabel;
    private MessageController messageController;

    /**
     * Constructs a new MessageView object.
     */
    public MessageView() {
        super();
        // Initialize the MessageController
        messageController = new MessageController();

        // Initialize labels
        idLabel = new JLabel("ID: ");
        senderIdLabel = new JLabel("Sender ID: ");
        receiverIdLabel = new JLabel("Receiver ID: ");
        messageLabel = new JLabel("Message: ");
        dateLabel = new JLabel("Date: ");

        // Create a panel to hold the labels
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(idLabel);
        panel.add(senderIdLabel);
        panel.add(receiverIdLabel);
        panel.add(messageLabel);
        panel.add(dateLabel);

        // Add the panel to the frame
        add(panel);

        // Retrieve message details from the controller and update labels
        updateMessageDetails();
    }


    /**
     * Updates the labels with message details.
     */
    private void updateMessageDetails() {
        Message message = messageController.viewMessage(new Message()); // Pass a Message object
        if (message != null) {
            idLabel.setText("ID: " + message.getId());
            senderIdLabel.setText("Sender ID: " + message.getSenderId());
            receiverIdLabel.setText("Receiver ID: " + message.getReceiverId());
            messageLabel.setText("Message: " + message.getMessage());
            dateLabel.setText("Date: " + message.getDate());
        }
    }
    

    /**
     * The main method to launch the MessageView.
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MessageView::new);
    }
}
