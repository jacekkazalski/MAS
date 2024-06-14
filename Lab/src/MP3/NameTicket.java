package MP3;

public class NameTicket extends Ticket {
    private final String firstName;
    private final String lastName;

    public NameTicket( Event event, String firstName, String lastName) {
        super( event);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getTicketDetails() {
        return super.getTicketDetails() + " owner: " + firstName + " " + lastName;
    }

}
