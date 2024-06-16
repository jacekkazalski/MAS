package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Client extends DataModel {
    private final ArrayList<Discount> discounts = new ArrayList<>();
    private final Set<Order> orders = new HashSet<>();
    private String firstName;
    private String lastName;
    private String email;
    private Order activeOrder;

    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void createOrder() {
        Order order = new Order(LocalDateTime.now(), this);
        activeOrder = order;
        orders.add(order);
    }

    public void addDiscount(double percentOff, double discount, LocalDate validFrom, LocalDate validTo) {
        this.discounts.add(new Discount(percentOff, discount, validFrom, validTo));
    }

    public void removeDiscount(Discount discount) {
        this.discounts.remove(discount);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Order getActiveOrder() {
        if (activeOrder == null) {
            activeOrder = new Order(LocalDateTime.now(), this);
        }
        return activeOrder;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getInfo() {
        return "";
    }
}
