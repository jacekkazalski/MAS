package MP3;

public class NameGroupTicket extends NameTicket {
    private GroupTicket groupTicket;

    public NameGroupTicket( Event event, String firstName, String lastName, int groupSize) {
        super( event, firstName, lastName);
        this.groupTicket = new GroupTicket( event, groupSize);
    }

    public double getPricePerPerson() {
        return groupTicket.getPricePerPerson();
    }

    @Override
    public String getTicketDetails() {
        return super.getTicketDetails() + " Group size: " + groupTicket.getGroupSize();
    }
}
