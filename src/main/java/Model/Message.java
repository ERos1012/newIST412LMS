package Model;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private Timestamp timestamp;
    private String senderType;
    private String receiverType;  // Added receiver type

    // Constructor used when creating a message to send
    public Message(int senderId, int receiverId, String content, String senderType, String receiverType, Timestamp timestamp) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.senderType = senderType;
        this.receiverType = receiverType;  // Initialize receiver type
        this.timestamp = timestamp;
    }

    // Constructor used when retrieving a message from the database
    public Message(int id, int senderId, int receiverId, String content, Timestamp timestamp, String senderType, String receiverType) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
        this.senderType = senderType;
        this.receiverType = receiverType;  // Initialize receiver type
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getSenderId() { return senderId; }
    public int getReceiverId() { return receiverId; }
    public String getContent() { return content; }
    public Timestamp getTimestamp() { return timestamp; }
    public String getSenderType() { return senderType; }
    public String getReceiverType() { return receiverType; }  // Getter for receiver type
}
