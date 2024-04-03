package MP2;

import java.util.Date;

public class ProductionInformation {
    public Car car;
    public Factory factory;
    public Date productionDate;

    public ProductionInformation(Car car, Factory factory, Date productionDate) {
        this.car = car;
        this.factory = factory;
        this.productionDate = productionDate;
    }

    @Override
    public String toString() {
        return car.getVin() + " " + factory;
    }
}
