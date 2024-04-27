package View;

import Controller.MessageController;
import Model.Message;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class MessageView extends JPanel {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout); // Panel that contains different views
    private MessageController messageController; // Controller for message functionalities
    private JTextArea messageBodyField; // Global declaration to ensure accessibility

    public MessageView() {
        super(); // Initialize JPanel
        messageController = new MessageController();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JPanel navigationPanel = setupNavigationPanel();
        setupMessagePanels();

        add(navigationPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);
    }

    private JPanel setupNavigationPanel() {
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] views = {"Dashboard", "Inbox", "Create Message"};
        for (String view : views) {
            JButton button = new JButton(view);
            button.addActionListener(e -> cardLayout.show(cardsPanel, view));
            navigationPanel.add(button);
        }
        return navigationPanel;
    }

    private void setupMessagePanels() {
        JPanel messageDashboardPanel = new JPanel(new GridLayout(1, 2));
        JButton inboxButton = new JButton("Inbox");
        JButton createMessageButton = new JButton("Create Message");

        inboxButton.addActionListener(e -> cardLayout.show(cardsPanel, "Inbox"));
        createMessageButton.addActionListener(e -> cardLayout.show(cardsPanel, "Create Message"));

        messageDashboardPanel.add(inboxButton);
        messageDashboardPanel.add(createMessageButton);
        cardsPanel.add(messageDashboardPanel, "Dashboard");

        setupInboxPanel();
        setupCreateMessagePanel();
    }

    private void setupInboxPanel() {
        JPanel inboxPanel = new JPanel(new BorderLayout());
        JList<String> messageList = new JList<>();
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            int userId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter your ID:"));
            java.util.List<Message> messages = messageController.getAllMessagesForUser(userId);
            DefaultListModel<String> model = new DefaultListModel<>();
            for (Message message : messages) {
                model.addElement("From: " + message.getSenderId() + " - " + message.getContent());
            }
            messageList.setModel(model);
        });
        inboxPanel.add(new JScrollPane(messageList), BorderLayout.CENTER);
        inboxPanel.add(refreshButton, BorderLayout.SOUTH);
        cardsPanel.add(inboxPanel, "Inbox");
    }

    private void setupCreateMessagePanel() {
        JPanel createMessagePanel = new JPanel(new GridLayout(5, 2));
        JTextField senderIdField = new JTextField();
        JTextField recipientIdField = new JTextField();
        messageBodyField = new JTextArea(5, 20);
        JButton sendButton = new JButton("Send Message");

        createMessagePanel.add(new JLabel("Sender ID:"));
        createMessagePanel.add(senderIdField);
        createMessagePanel.add(new JLabel("Recipient ID:"));
        createMessagePanel.add(recipientIdField);
        createMessagePanel.add(new JLabel("Message:"));
        createMessagePanel.add(new JScrollPane(messageBodyField));
        sendButton.addActionListener(e -> {
            try {
                int senderId = Integer.parseInt(senderIdField.getText().trim());
                int recipientId = Integer.parseInt(recipientIdField.getText().trim());
                String message = messageBodyField.getText().trim();
                if (message.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Message cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                messageController.sendMessage(new Message(senderId, recipientId, message, new Timestamp(System.currentTimeMillis())));
                JOptionPane.showMessageDialog(this, "Message sent!");
                messageBodyField.setText(""); // Clear the message field after sending
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please ensure IDs are numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        createMessagePanel.add(sendButton);
        cardsPanel.add(createMessagePanel, "Create Message");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Message System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.add(new StudentMessageView());
            frame.setVisible(true);
        });
    }
}
