package MP2;

import java.util.ArrayList;
import java.util.Date;

public class Factory {
    private final int id;
    private final String name;
    // Asocjacja z atrybutem 1-*
    public ArrayList<ProductionInformation> producedCars = new ArrayList<>();

    public Factory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addCar(Car car, Date date) {
        ProductionInformation productionInformation = new ProductionInformation(car, this, date);
        if (!producedCars.contains(productionInformation)) {
            producedCars.add(productionInformation);
            car.setProductionInformation(productionInformation);
        }
    }

    public void addCar(ProductionInformation productionInformation) {
        producedCars.add(productionInformation);
    }

    @Override
    public String toString() {
        return "Factory" + name;
    }
}
