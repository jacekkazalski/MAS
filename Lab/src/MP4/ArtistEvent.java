package MP4;

import java.util.Date;

public class ArtistEvent {
    private Artist artist;
    private Event event;
    private Date showTime;
    public ArtistEvent(Artist artist, Event event, Date showTime) {
        this.artist = artist;
        this.event = event;
        this.showTime = showTime;
        artist.addShow(this);
        event.addShow(this);
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

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }
}
