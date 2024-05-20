package MP3;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Event concert = new Event(new Date(2024, 2, 20), "Foo Fighters");
        Event onlineConcert = null;
        Event onlineEvent = null;
        try {
            URI uri = new URI("https://pinkfloydlive.com");
            URI uri2 = new URI("https://mysteriousevent.com");
            onlineConcert = new Event(new Date(2020, 10, 20), uri, "Pink Floyd");
            onlineEvent = new Event(new Date(2024, 12, 2), uri2);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        Ticket nameTicket = new NameTicket( concert, "Jan", "Kowalski");
        nameTicket.addCategory(TicketCategory.EarlyDoors);
        Ticket groupTicket = new GroupTicket( onlineConcert, 5);
        groupTicket.removeCategory(TicketCategory.Standard);
        Ticket nameGroupTicket = new NameGroupTicket( onlineEvent, "Jan", "Nowak", 5);
        ArrayList<Ticket> ticketList = new ArrayList<>();
        ticketList.add(nameTicket);
        ticketList.add(groupTicket);
        ticketList.add(nameGroupTicket);
        for (Ticket ticket : ticketList) {
            System.out.println(ticket.getTicketDetails());
        }


    }
}