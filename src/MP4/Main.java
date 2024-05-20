package MP4;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Organizer organizer1 = new Organizer(1, "EventPOL");
        Organizer organizer2 = new Organizer(2, "Koncertix");

        Venue stadium = new Venue(1,20000,"Stadion Miejski");
        Venue basement = new Venue(2, 2,"Piwnica");

        Event event1 = new Event("Juwenalia", organizer1,stadium);
        Event event2 = new Event("Piwniczalia", organizer2, basement);
        organizer2.addEvent(event2);
        Artist artist1 = new Artist(1,"Kombi");
        Artist artist2 = new Artist(2, " Pendrive");
        event1.addShow(artist1, new Date(20,05,2024));
        event2.addShow(artist2, new Date(20,05,2024));

        event1.addTicket(new Ticket(1,100), organizer1);
        event2.addTicket(new Ticket(2,100), organizer2);
        event2.addTicket(new Ticket(3,100), organizer2);
        // event2.addTicket(new Ticket(4,100), organizer2);

        event1.setHeadliner(artist1);

    }
}