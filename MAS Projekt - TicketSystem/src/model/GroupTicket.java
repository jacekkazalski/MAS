package model;

public class GroupTicket extends Ticket {
    private int numberOfPeople;

    public GroupTicket(double price, int numberOfPeople) {
        super(price);
        this.numberOfPeople = numberOfPeople;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "  Number of people: " + numberOfPeople+"}";
    }
}
