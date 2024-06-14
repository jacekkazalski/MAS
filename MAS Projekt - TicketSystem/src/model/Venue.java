package model;

public class Venue extends DataModel{
    private int maxCapacity;
    private String name;
    private String address;

    public Venue(int maxCapacity, String name, String address) {
        this.maxCapacity = maxCapacity;
        this.name = name;
        this.address = address;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
