package MP3;

public enum TicketCategory {
    Standard(100), BackstageAccess(200), EarlyDoors(300);
    public int price;

    private TicketCategory(int price) {
        this.price = price;
    }
}
