package MP2;

import java.util.ArrayList;

public class Manufacturer {
    private final int id;
    private final String name;
    public ArrayList<Car> cars = new ArrayList<>();

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addCar(Car car) {
        if (!cars.contains(car)) {
            cars.add(car);
            car.setManufacturer(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
