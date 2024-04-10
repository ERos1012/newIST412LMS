package Model;

import java.time.LocalDateTime;

public class Event {
    private String title;
    private LocalDateTime dateTime;

    public Event(String title, LocalDateTime dateTime) {
        this.title = title;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
