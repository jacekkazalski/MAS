package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Order extends DataModel{
    private LocalDateTime date;
    public enum orderStatus{STARTED, PAID, CANCELLED, COMPLETE}
    private orderStatus status = Order.orderStatus.STARTED;
    private final Set<Ticket> tickets = new HashSet<>();
    private Payment payment;
    private Discount discount;
    private final Client client;

    protected Order(LocalDateTime date, Client client) {
        this.date = date;
        this.client = client;
    }
    public void addTicket(Ticket ticket) {
        ticket.setOrder(this);
        tickets.add(ticket);
    }
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }
    public void applyDiscount(Discount discount) {
        this.discount = discount;
    }
    public double getTotalPrice() {
       return tickets.stream().mapToDouble(Ticket::getPrice).sum();
    }
    public void cancelOrder(){
        this.status = Order.orderStatus.CANCELLED;
    }
    public void startPayment(Payment payment){
        this.payment = payment;
    }
    //Gettery settery
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public orderStatus getStatus() {
        return status;
    }

    public void setStatus(orderStatus status) {
        this.status = status;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String getInfo() {
        return "";
    }

}
