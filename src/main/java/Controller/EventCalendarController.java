package Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventCalendarController {
    private List<EventController> events;

    public EventCalendarController() {
        this.events = new ArrayList<>();
    }

    /**
     * Adds a new event to the event calendar.
     * @param title The title of the event.
     * @param dateTime The date and time of the event.
     */
    public void addEvent(String title, LocalDateTime dateTime) {
        EventController event = new EventController(title, dateTime);
        events.add(event);
    }

    /**
     * Removes an event from the event calendar.
     * @param event The event to be removed.
     * @return true if the event was removed successfully, false otherwise.
     */
    public boolean removeEvent(EventController event) {
        return events.remove(event);
    }

    /**
     * Gets all events in the event calendar.
     * @return A list of all events in the event calendar.
     */
    public List<EventController> getAllEvents() {
        return events;
    }

    /**
     * Formats and returns all events in the event calendar as a string.
     * @return A formatted string of all events in the event calendar.
     */
    public String getAllEventsAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (EventController event : events) {
            String formattedDateTime = event.getDateTime().format(formatter);
            stringBuilder.append("Title: ").append(event.getTitle())
                    .append(", Date/Time: ").append(formattedDateTime).append("\n");
        }

        return stringBuilder.toString();
    }

    public void addEvent(EventController event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEvent'");
    }
}
