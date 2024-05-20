package MP4;

public class Main {
    public static void main(String[] args) {
        try{
            Ticket ticket1 = new Ticket(1, 200);
            Ticket ticket2 = new Ticket(2, 200);
            Ticket ticket3 = new Ticket(3, -10);
            Ticket ticket4 = new Ticket(2, 200);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}
