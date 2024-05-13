import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private Date date;
    private ArrayList<Object> eventType = new ArrayList<>();

    public Event(Date date, String bandName) {
        this.date = date;
        this.eventType.add(new Concert(bandName));
    }

    public Event(Date date, URI eventLink) {
        this.date = date;
        this.eventType.add(new OnlineEvent(eventLink));
    }

    public Event(Date date, URI eventLink, String bandName) {
        this.date = date;
        this.eventType.add(new Concert(bandName));
        this.eventType.add(new OnlineEvent(eventLink));
    }

    @Override
    public String toString() {
        return "Date: " + date.toString() + ", EventType: " + eventType;
    }

    private class OnlineEvent {
        private URI eventLink;

        public OnlineEvent(URI eventLink) {
            this.eventLink = eventLink;
        }

        @Override
        public String toString() {
            return super.toString() + " Link: " + eventLink.toString();
        }
    }

    public class Concert {
        private String bandName;

        public Concert(String bandName) {
            this.bandName = bandName;
        }

        @Override
        public String toString() {
            return super.toString() + " Band: " + bandName;
        }
    }
}
