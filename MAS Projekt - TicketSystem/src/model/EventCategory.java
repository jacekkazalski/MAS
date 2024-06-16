package model;


import java.util.HashSet;
import java.util.Set;

public class EventCategory extends DataModel {
    private final Set<Event> eventSet = new HashSet<>();
    private String categoryName;

    public EventCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addEvent(Event event) {
        eventSet.add(event);
    }

    public void removeEvent(Event event) {
        eventSet.remove(event);
    }

    @Override
    public String getInfo() {
        return "";
    }
}
