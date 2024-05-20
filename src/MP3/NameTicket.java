package MP3;

public class NameTicket extends Ticket {
    private String firstName;
    private String lastName;

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
