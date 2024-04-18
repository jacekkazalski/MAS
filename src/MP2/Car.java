package MP2;

import java.util.ArrayList;
import java.util.Date;

public class Car {
    private final int id;
    private final String model;
    private final String vin;
    // Asocjacja zwykła *-1
    public Manufacturer manufacturer;
    // Asocjacja kwalifikowana *-1
    public Owner owner;
    // Asocjacja z atrybutem *-1
    public ProductionInformation productionInformation;
    // Kompozycja
    public ArrayList<ServiceInformation> serviceInformations = new ArrayList<>();

    public Car(int id, String vinNumber, String model) {
        this.id = id;
        this.vin = vinNumber;
        this.model = model;
    }

    // Asocjacja zwykła *-1
    public void setManufacturer(Manufacturer manufacturer) {
        if (!manufacturer.equals(this.manufacturer)) {
            this.manufacturer = manufacturer;
            manufacturer.addCar(this);
        }
    }

    // Asocjacja kwalifikowana *-1
    public void setOwner(Owner owner) {
        if (!owner.equals(this.owner)) {
            this.owner = owner;
            owner.addCar(this);
        }
    }

    // Asocjacja z atrybutem *-1
    public void setProductionInformation(Factory factory, Date date) {
        productionInformation = new ProductionInformation(this, factory, date);
        factory.addCar(productionInformation);
    }

    public void setProductionInformation(ProductionInformation productionInformation) {
        this.productionInformation = productionInformation;
    }

    // Kompozycja 1-*
    public void addServiceInformation(boolean passed, Date date) {
        serviceInformations.add(new ServiceInformation(passed, date, this));
    }

    public String getVin() {
        return vin;
    }

    @Override
    public String toString() {
        return "Car information\n{" +
                "\nid: " + id +
                "\nVIN: " + vin +
                "\nModel name: " + model +
                "\nManufacturer: " + manufacturer +
                "\nProduction information: " + productionInformation +
                "\nOwner: " + owner +
                "\nService information: " + serviceInformations + "\n}";
    }

    public class ServiceInformation {
        private final boolean passed;
        private final Date date;
        private Car car;

        public ServiceInformation(boolean passed, Date date, Car car) {
            this.passed = passed;
            this.date = date;
            this.car = car;
        }

        @Override
        public String toString() {
            return "passed: " + passed + " on " + date;
        }
    }
}
