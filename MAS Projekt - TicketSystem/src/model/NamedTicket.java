package model;

public class NamedTicket extends Ticket {
    private String firstName;
    private String lastName;

    public NamedTicket(double price) {
        super(price);
    }

    public NamedTicket(double price, String firstName, String lastName) {
        super(price);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "  " + firstName + " " + lastName + "}";
    }
}
