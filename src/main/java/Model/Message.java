package Model;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private Timestamp timestamp;
    private String senderType;  // Ensure this aligns with your database schema.

    // Constructor used when creating a message to send
    public Message(int senderId, int receiverId, String content, String senderType, Timestamp timestamp) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.senderType = senderType;
        this.timestamp = timestamp;
    }

    // Constructor used when retrieving a message from the database
    public Message(int id, int senderId, int receiverId, String content, Timestamp timestamp, String senderType) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
        this.senderType = senderType;
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getSenderId() { return senderId; }
    public int getReceiverId() { return receiverId; }
    public String getContent() { return content; }
    public Timestamp getTimestamp() { return timestamp; }
    public String getSenderType() { return senderType; }

    // You might add setters if you need to modify any properties after object creation.
}
