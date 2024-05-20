package MP4;

import java.util.ArrayList;
import java.util.Date;

public class Artist {
    private int id;
    private String name;
    private ArrayList<ArtistEvent> shows = new ArrayList<>();

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void addShow(Event event, Date showTime) {
        ArtistEvent artistEvent = new ArtistEvent(this, event, showTime);
        this.shows.add(artistEvent);
        event.addShow(artistEvent);
    }
    public void addShow(ArtistEvent event) {
        this.shows.add(event);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArtistEvent> getShows() {
        return shows;
    }

    public void setShows(ArrayList<ArtistEvent> shows) {
        this.shows = shows;
    }
}
