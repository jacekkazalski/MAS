package MP4;

import java.util.ArrayList;

public class Organizer {
    private int id;
    private String name;
    // Ograniczenie ordered
    private ArrayList<Event> events = new ArrayList<>();

    public Organizer(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void addEvent(Event event){
        this.events.add(event);
        event.setOrganizedBy(this);
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
}
