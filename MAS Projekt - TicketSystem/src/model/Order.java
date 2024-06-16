package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Order extends DataModel {
    public enum orderStatus {STARTED, PAID, CANCELLED, COMPLETE}
    private final Set<Ticket> tickets = new HashSet<>();
    private final Client client;
    private LocalDateTime date;
    private orderStatus status = Order.orderStatus.STARTED;
    private Payment payment;
    private Discount discount;
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
        // Jeżeli po usunięciu kwota zamówienia jest zbyt niska to usuwamy rabat
        if(discount!=null  && getTotalPriceBeforeDiscount() < discount.getMinOrderValue()){
            this.discount = null;
        }
    }

    public void applyDiscount(Discount discount) {

        this.discount = discount;
    }
    private double getTotalPriceBeforeDiscount(){
        return tickets.stream().mapToDouble(Ticket::getPrice).sum();
    }
    public double getTotalPrice() {
        if (discount != null) {
            return getTotalPriceBeforeDiscount() - getTotalPriceBeforeDiscount() * discount.getPercentOff() /100;
        }
        return getTotalPriceBeforeDiscount();

    }

    public Discount getDiscount() {
        return discount;
    }

    public void cancelOrder() {
        this.status = Order.orderStatus.CANCELLED;
    }

    public void startPayment(Payment payment) {
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

    public Client getClient() {
        return client;
    }

    @Override
    public String getInfo() {
        StringBuilder info =
                new StringBuilder("Order " + getObjectId() + " by " + client.getFirstName() + " " + client.getLastName() + "\n" + "Total price: " + getTotalPrice() + "\n");
        for (Ticket ticket : tickets) {
            info.append(ticket.getInfo()).append("\n");
        }
        return info.toString();
    }
}
