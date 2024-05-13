public class NameGroupTicket extends NameTicket {
    private GroupTicket groupTicket;

    public NameGroupTicket(double price, Event event, String firstName, String lastName, int groupSize) {
        super(price, event, firstName, lastName);
        this.groupTicket = new GroupTicket(price, event, groupSize);
    }

    public double getPricePerPerson() {
        return groupTicket.getPricePerPerson();
    }

    @Override
    public String getTicketDetails() {
        return super.getTicketDetails() + " Group size: " + groupTicket.getGroupSize();
    }
}
