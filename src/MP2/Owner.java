package MP2;

import java.util.Map;
import java.util.TreeMap;

public class Owner {
    private final int id;
    private final String firstName;
    private final String lastName;
    // Asocjacja kwalifikowana vin 1-*
    public Map<String, Car> ownedCars = new TreeMap<>();

    public Owner(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addCar(Car car) {
        if (!ownedCars.containsKey(car.getVin())) {
            ownedCars.put(car.getVin(), car);
            car.setOwner(this);
        }
    }

    public Car findCar(String vin) throws Exception {
        if (!ownedCars.containsKey(vin)) {
            throw new Exception("Car not found");
        }
        return ownedCars.get(vin);
    }

    public void printOwnedCars() {
        System.out.println("Owner info: id:" + id + " " + firstName + " " + lastName);
        System.out.println("Owned cars: " + ownedCars);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
