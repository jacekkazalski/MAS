package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Organiser extends DataModel {
    private final Set<Event> events = new HashSet<>();
    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    public Organiser(String name, String phoneNumber, String email, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
    // Tworzy wydarzenie o wybranych parametrach
    public void organiseEvent(String name, LocalDate startDate, LocalDate endDate, Venue venue,
                              EventCategory eventCategory) {
        Event event = new Event(name, startDate, endDate, venue, eventCategory);
        events.add(event);
    }

    public Set<Event> getEvents() {
        return events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getInfo() {
        return "";
    }
}
