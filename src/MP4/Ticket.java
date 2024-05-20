package MP4;

import java.util.HashSet;
import java.util.Set;

public class Ticket {
    private int id;
    private static final Set<Integer> idSet = new HashSet<Integer>();
    private int price;
    public Ticket(int id, int price) {
        if(idSet.contains(id)) {
            throw new IllegalArgumentException("Ticket already exists");
        } else if(price < 0) {
            throw new IllegalArgumentException("Ticket price cannot be negative");
        }
        else {
            idSet.add(id);
            this.price = price;
            this.id = id;
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price > 0){
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }
}
