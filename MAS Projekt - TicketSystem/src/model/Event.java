package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Event extends DataModel {
    private final Set<Ticket> tickets = new HashSet<>();
    private final Set<EventCategory> eventCategories = new HashSet<>();
    private final Set<EventArtist> shows = new HashSet<>();
    private final Venue venue;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int availableGrpSeats = 0;
    private double ticketPrice = 0;
    private double grpTicketPrice = 0;

    protected Event(String name, LocalDate startDate, LocalDate endDate, Venue venue, EventCategory eventCategory) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.eventCategories.add(eventCategory);
        eventCategory.addEvent(this);
    }

    public void addNamedTicket(double price, String firstName, String lastName) {
        tickets.add(new NamedTicket(price, firstName, lastName));
    }

    public void addGroupTicket(double price, int numberOfPeople) {
        tickets.add(new GroupTicket(price, numberOfPeople));
    }

    public void addShow(Artist artist, LocalDateTime showTime) {
        this.shows.add(new EventArtist(artist, this, showTime));
    }

    /* Generuje bilety z uwzględnieniem wybranej lokalizacji oraz odkłada wybrany procent miejsc na bilety grupowe.
        Ilość miejsc zostaje zapisana, cena biletu grupowego na osobę = cena * 0,8 */
    public void generateTicketsForVenue(double groupPercent, double ticketPrice) {
        int allTickets = venue.getMaxCapacity();
        int groupTickets = (int) (allTickets * groupPercent) / 100;
        int namedTickets = allTickets - groupTickets;
        System.out.println(namedTickets);
        for (int i = 0; i < namedTickets; i++) {
            Ticket ticket = new NamedTicket(ticketPrice);
            ticket.setEvent(this);
            tickets.add(ticket);
            System.out.println("Ticket added");
        }
        availableGrpSeats = groupTickets;
        this.ticketPrice = ticketPrice;
        grpTicketPrice = ticketPrice * 0.8;
    }
    // Generuje bilet grupowy dla wybranej liczby osób
    public GroupTicket generateGroupTicket(int groupSize) {
        GroupTicket ticket = new GroupTicket(grpTicketPrice * groupSize, groupSize);
        ticket.setEvent(this);
        tickets.add(ticket);
        return ticket;
    }
    // Zwraca pierwszy dostępny bilet dla zamówienia
    public Ticket findFirstAvailableTicket() {
        for (Ticket ticket : tickets) {
            if (ticket.getStatus() == Ticket.statusEnum.AVAILABLE) {
                return ticket;
            }
        }
        return null;
    }

    // Zwraca liczbę dostępnych miejsc dla pojedyńczego biletu grupowego -> Max liczba osób na bilecie grupowym to 6
    public Integer[] getAvailableGrpSeats() {
        int maxSeats = Math.min(availableGrpSeats, 6);
        Integer[] seats = new Integer[maxSeats];
        for (int i = 0; i < maxSeats; i++) {
            seats[i] = i + 1;
        }

        return seats;
    }

    // Zwraca wartości typów biletów dla combobox przy zakupie
    public String[] getAvailableTicketTypes() {
        String[] types;
        if (!tickets.stream().map(Ticket::getStatus).toList().contains(Ticket.statusEnum.AVAILABLE)) {
            types = new String[]{"Brak dostępnych biletów"};
        } else if (availableGrpSeats < 1) {
            types = new String[]{"Wybierz rodzaj biletu", "Imienny"};
        } else {
            types = new String[]{"Wybierz rodzaj biletu", "Imienny", "Grupowy"};
        }
        return types;
    }

    public double getGrpTicketPrice() {
        return grpTicketPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public Set<EventCategory> getEventCategories() {
        return eventCategories;
    }

    public Set<EventArtist> getShows() {
        return shows;
    }

    public Venue getVenue() {
        return venue;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getInfo() {
        return "Event: " + "no. " + getObjectId() + " " + name + " " + startDate + " " + endDate;
    }
}
