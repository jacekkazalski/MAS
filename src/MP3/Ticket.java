import java.util.EnumSet;

// wieloaspektowe, klasa abstrakcyjna
public abstract class Ticket {
    private double price;
    // dynamiczne spłaszczenie
    private EnumSet<TicketCategory> ticketCategory;
    private Event event;

    public Ticket(double price, Event event) {
        this.price = price;
        this.event = event;
        this.ticketCategory = EnumSet.of(TicketCategory.Standard);
    }

    public void addCategory(TicketCategory ticketCategory) {
        this.ticketCategory.add(ticketCategory);
    }

    public void removeCategory(TicketCategory ticketCategory) {
        if (this.ticketCategory.size() > 1) {
            this.ticketCategory.remove(ticketCategory);
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

    ;

}
