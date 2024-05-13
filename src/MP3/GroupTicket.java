public class GroupTicket extends Ticket {
    private int groupSize;

    public GroupTicket(double price, Event event, int groupSize) {
        super(price, event);
        this.groupSize = groupSize;
    }

    public double getPricePerPerson() {
        return super.getPrice() / this.groupSize;
    }

    @Override
    public String getTicketDetails() {
        return super.getTicketDetails() + " Group size: " + this.groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}
