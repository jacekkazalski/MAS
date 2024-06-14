package model;

public abstract class Ticket extends DataModel{
    private double price;
    public enum statusEnum{AVAILABLE, RESERVED, SOLD}
    private statusEnum status = statusEnum.AVAILABLE;
    private Order order;
    private Event event;

    protected Ticket(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public statusEnum getStatus() {
        return status;
    }

    public void setStatus(statusEnum status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String getInfo() {
        return "";
    }
}
