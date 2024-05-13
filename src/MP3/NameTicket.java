public class NameTicket extends Ticket {
    private String firstName;
    private String lastName;

    public NameTicket(double price, Event event, String firstName, String lastName) {
        super(price, event);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getTicketDetails() {
        return super.getTicketDetails() + " owner: " + firstName + " " + lastName;
    }

}
