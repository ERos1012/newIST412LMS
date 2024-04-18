package Controller;

import Model.EmailAPI;
import Model.Message;
import Model.Student;
import Model.Teacher;

import java.util.List;

/**
 * The MessageController class manages messages in the system.
 */
public class MessageController {
    private EmailAPI emailAPI = new EmailAPI();

    /**
     * Sends an email with the provided details.
     *
     * @param senderName The name of the sender.
     * @param receiverEmail The email address of the receiver.
     * @param messageContent The content of the message.
     */
    public void sendEmail(String senderName, String receiverEmail, String messageContent) {
        EmailAPI.sendEmail(senderName, receiverEmail, messageContent);
        System.out.println("Email sent from " + senderName + " to " + receiverEmail + " with message: " + messageContent);
    }


    /**
     * Method to send email notifications
     */
    private void sendEmailNotification(Message message) {
        String name = "Sender Name";  // This should be fetched from user data based on senderId
        String email = "receiver@example.com";  // This should be fetched from user data based on receiverId
        EmailAPI.sendEmail(name, email, message.getMessage());
    }

    /**
     * Views a message.
     * In a real-world application, this would fetch a message from a data store.
     */
    public Message viewMessage(Message message) {
        return message;
    }

    /**
     * Deletes a message.
     * This would typically involve removing the message from a data store.
     */
    public void deleteMessage(Message message) {
        System.out.println("Message deleted: ID " + message.getId());
    }

    /**
     * Gets all of a student's messages.
     * This would fetch messages from a data store.
     * @param student
     * @return List of messages
     */
    public List<String> getStudentMessages(Student student) {
        // Mock data, replace with actual fetch logic
        return List.of("Message 1 from Student", "Message 2 from Student");
    }

    /**
     * Gets all of a teacher's messages.
     * This would fetch messages from a data store.
     * @param teacher
     * @return List of messages
     */
    public List<String> getTeacherMessages(Teacher teacher) {
        // Mock data, replace with actual fetch logic
        return List.of("Message 1 from Teacher", "Message 2 from Teacher");
    }
}
