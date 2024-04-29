package View;

import Controller.MessageController;
import Model.Message;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class MessageView extends JPanel {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout);
    private MessageController messageController;
    private JTextArea messageBodyField;
    private JTextField recipientIdField;
    private JComboBox<String> receiverTypeComboBox;  // Dropdown for selecting receiver type
    private int userId; // Store the user ID
    private String userType; // Store the user type

    public MessageView(int userId, String userType) {
        super();
        this.userId = userId;
        this.userType = userType;
        messageController = new MessageController();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel navigationPanel = setupNavigationPanel();
        setupMessagePanels();
        add(navigationPanel, BorderLayout.NORTH);
        add(cardsPanel, BorderLayout.CENTER);
        System.out.println("message view ID: " + userId);
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
            java.util.List<Message> messages = messageController.getAllMessagesForUser(this.userId, this.userType);
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
        JPanel createMessagePanel = new JPanel(new GridLayout(6, 2));
        recipientIdField = new JTextField();
        messageBodyField = new JTextArea(5, 20);
        receiverTypeComboBox = new JComboBox<>(new String[]{"student", "teacher"});

        JButton sendButton = new JButton("Send Message");

        createMessagePanel.add(new JLabel("Recipient ID:"));
        createMessagePanel.add(recipientIdField);
        createMessagePanel.add(new JLabel("Receiver Type:"));
        createMessagePanel.add(receiverTypeComboBox);
        createMessagePanel.add(new JLabel("Message:"));
        createMessagePanel.add(new JScrollPane(messageBodyField));
        sendButton.addActionListener(e -> {
            try {
                int recipientId = Integer.parseInt(recipientIdField.getText().trim());
                String message = messageBodyField.getText().trim();
                String receiverType = (String) receiverTypeComboBox.getSelectedItem();  // Get selected receiver type
                if (message.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Message cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                messageController.sendMessage(new Message(this.userId, recipientId, message, this.userType, receiverType, new Timestamp(System.currentTimeMillis())));
                JOptionPane.showMessageDialog(this, "Message sent!");
                messageBodyField.setText(""); // Clear the message field after sending
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please ensure recipient ID is numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        createMessagePanel.add(sendButton);
        cardsPanel.add(createMessagePanel, "Create Message");
    }
}
