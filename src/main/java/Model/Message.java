package Model;

/**
 * The Message class represents a message in the system.
 */
public class Message {
    private int id;
    private int senderId;
    private int receiverId;
    private String message;
    private String date;

    /**
     * Constructs a new Message object with the specified ID, sender ID, receiver ID, message content, and date.
     * 
     * @param id The ID of the message.
     * @param senderId The ID of the sender of the message.
     * @param receiverId The ID of the receiver of the message.
     * @param message The content of the message.
     * @param date The date when the message was sent.
     */
    public Message(int id, int senderId, int receiverId, String message, String date) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.date = date;
    }
    
    public Message(){
    }

    /**
     * Gets the ID of the message.
     * 
     * @return The ID of the message.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets the ID of the sender of the message.
     * 
     * @return The ID of the sender.
     */
    public int getSenderId() {
        return senderId;
    }

    /**
     * Gets the ID of the receiver of the message.
     * 
     * @return The ID of the receiver.
     */
    public int getReceiverId() {
        return receiverId;
    }

    /**
     * Gets the content of the message.
     * 
     * @return The content of the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the date when the message was sent.
     * 
     * @return The date when the message was sent.
     */
    public String getDate() {
        return date;
    }
}
