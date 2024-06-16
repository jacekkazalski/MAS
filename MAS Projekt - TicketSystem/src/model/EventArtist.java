package model;

import java.time.LocalDateTime;

public class EventArtist {
    private Artist artist;
    private Event event;
    private LocalDateTime showTime;

    protected EventArtist(Artist artist, Event event, LocalDateTime showTime) {
        this.artist = artist;
        this.event = event;
        this.showTime = showTime;
        artist.addShow(this);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
