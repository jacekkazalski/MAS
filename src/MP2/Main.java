package MP2;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car(1, "1G1JC52F747262646","Opel Astra");
        Car car2 = new Car(2, "4DRAPER52A932641","Volkswagen Polo");

        Owner owner1 = new Owner(1, "Jan", "Kowalski");
        Owner owner2 = new Owner(2, "Jakub","Nowak");

        Factory factory1 = new Factory(1, "FSO");

        Manufacturer manufacturer1 = new Manufacturer(1,"Opel");
        Manufacturer manufacturer2 = new Manufacturer(2,"Volkswagen");

        manufacturer1.addCar(car1);
        car2.setManufacturer(manufacturer2);

        owner1.addCar(car1);
        car2.setOwner(owner2);
        try{
            System.out.println(owner1.findCar("1G1JC52F747262646"));
            System.out.println(owner2.findCar("1G1JC52F747262646"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        factory1.addCar(car1,new Date(2024, 4, 12));
        car2.setProductionInformation(factory1, new Date(2024, 2, 10));

        car1.addServiceInformation(true, new Date(2024,3,1));
        car2.addServiceInformation(false, new Date(2024,2,10));

        System.out.println(car1);
        System.out.println(car2);
    }
}
