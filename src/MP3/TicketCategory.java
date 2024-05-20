package MP3;

public enum TicketCategory {
    Standard(100), BackstageAccess(200), EarlyDoors(300);
    public int price;

    TicketCategory(int price) {
        this.price = price;
    }
}
