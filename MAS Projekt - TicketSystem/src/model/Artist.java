package model;

import java.util.HashSet;
import java.util.Set;

public class Artist extends DataModel {
    private final Set<EventArtist> shows = new HashSet<>();
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addShow(EventArtist show) {
        shows.add(show);
    }

    public Set<EventArtist> getShows() {
        return shows;
    }

    @Override
    public String getInfo() {
        return "";
    }
}
