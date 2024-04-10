package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventCalendar {
    private List<Event> events;

    public EventCalendar() {
        this.events = new ArrayList<>();
    }

    public void addEvent(String title, LocalDateTime dateTime) {
        Event event = new Event(title, dateTime);
        events.add(event);
    }

    public boolean removeEvent(Event event) {
        return events.remove(event);
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public String getAllEventsAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Event event : events) {
            String formattedDateTime = event.getDateTime().format(formatter);
            stringBuilder.append("Title: ").append(event.getTitle())
                    .append(", Date/Time: ").append(formattedDateTime).append("\n");
        }

        return stringBuilder.toString();
    }
}
