package Controller;

import java.time.LocalDateTime;

public class EventController {
    private String title;
    private LocalDateTime dateTime;

    /**
     * Constructs a new EventController with the specified title and date/time.
     * @param title The title of the event.
     * @param dateTime The date and time of the event.
     */
    public EventController(String title, LocalDateTime dateTime) {
        this.title = title;
        this.dateTime = dateTime;
    }

    /**
     * Gets the title of the event.
     * @return The title of the event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the date and time of the event.
     * @return The date and time of the event.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the title of the event.
     * @param title The new title of the event.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the date and time of the event.
     * @param dateTime The new date and time of the event.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "EventController{" +
                "title='" + title + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
