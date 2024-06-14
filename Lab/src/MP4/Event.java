package MP4;

import java.util.*;

public class Event {
    private String eventName;
    private Organizer organizedBy;
    private Set<Ticket> tickets = new HashSet<>();
    // Ograniczenie subset
    private Artist headliner;
    // Bag
    public ArrayList<ArtistEvent> shows = new ArrayList<>();
    private Venue venue;
    public Event(String eventName, Organizer organizedBy, Venue venue) {
        this.eventName = eventName;
        this.organizedBy = organizedBy;
        this.venue = venue;
        organizedBy.addEvent(this);
    }
    public void addShow(Artist artist, Date showTime) {
        ArtistEvent artistEvent = new ArtistEvent(artist, this, showTime);
        this.shows.add(artistEvent);
        artist.addShow(artistEvent);
    }
    public void addShow(ArtistEvent artistEvent) {
        this.shows.add(artistEvent);
    }
    public void setHeadliner(Artist artist){
        if (this.shows.stream().anyMatch(artistEvent -> artistEvent.getArtist().equals(artist))){
            this.headliner = artist;
        } else {
            throw new IllegalArgumentException("Chosen artist is not in the show list");
        }
    }
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Organizer getOrganizedBy() {
        return organizedBy;
    }

    public void setOrganizedBy(Organizer organizedBy) {
        this.organizedBy = organizedBy;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }
    // Ograniczenie własne
    public void addTicket(Ticket ticket, Organizer organizer) {
        if(this.organizedBy == organizer){
            if(this.tickets.size() >= this.venue.getMaxCapacity())
            {
                throw new IllegalArgumentException("Venue capacity exceeded");
            } else {
                this.tickets.add(ticket);
                ticket.setEvent(this);
            }

        } else {
            throw new IllegalArgumentException("Organizer id does not match this event");
        }

    }
    public void addTicket(Set<Ticket> tickets, Organizer organizer) {
        if(this.organizedBy == organizer){
            if(this.tickets.size() >= this.venue.getMaxCapacity())
            {
                throw new IllegalArgumentException("Venue capacity exceeded");
            } else {
                this.tickets.addAll(tickets);
                tickets.stream().forEach(ticket -> ticket.setEvent(this));
            }
        } else {
            throw new IllegalArgumentException("Organizer id does not match this event");
        }
    }
}
