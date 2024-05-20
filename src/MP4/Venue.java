package MP4;

public class Venue {
    private int id;
    private int maxCapacity;
    private String venueName;

    public Venue(int id, int maxCapacity, String venueName) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.venueName = venueName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
}
