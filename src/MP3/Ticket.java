package MP3;

import java.util.EnumSet;

public abstract class Ticket {
    private double price;
    private final EnumSet<TicketCategory> ticketCategory;
    private final Event event;

    public Ticket(Event event) {
        this.event = event;
        this.ticketCategory = EnumSet.of(TicketCategory.Standard);
        for (TicketCategory t: ticketCategory){
            this.price += t.price;
        }
    }

    public void addCategory(TicketCategory ticketCategory) {
        this.ticketCategory.add(ticketCategory);
        this.price += ticketCategory.price;
    }

    public void removeCategory(TicketCategory ticketCategory) {
        if (this.ticketCategory.size() > 1) {
            this.ticketCategory.remove(ticketCategory);
            this.price -= ticketCategory.price;
        } else {
            System.out.println("Cannot remove last remaining ticket category");
        }

    }

    public double getPrice() {
        return price;
    }

    public Event getEvent() {
        return event;
    }

    public EnumSet<TicketCategory> getTicketCategory() {
        return ticketCategory;
    }

    public String getTicketDetails() {
        return "\n" + event.toString() + "\nTicket: " + ticketCategory.toString() + " Price: " + price;
    }

}
