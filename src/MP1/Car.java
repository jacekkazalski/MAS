package MP1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class Car implements Serializable {
    private static ArrayList<Car> extent = new ArrayList<>();
    private int fuelCapacity;
    private double fuelUsePer100;
    private String modelName;
    private Integer horsepower;
    private Date productionDate;
    private ArrayList<Date> serviceDates;

    public Car(int fuelCapacity, double fuelUsePer100, Integer horsepower, Date productionDate, String modelName) {
        this.fuelCapacity = fuelCapacity;
        this.fuelUsePer100 = fuelUsePer100;
        this.modelName = modelName;
        this.horsepower = horsepower;
        this.productionDate = productionDate;
        this.serviceDates = new ArrayList<>();

        extent.add(this);
    }

    public double calculateRange()
    {
        return fuelCapacity/fuelUsePer100*100;
    }
    public static Optional<Car> findLargestTank()
    {
        return extent.stream().max((c1,c2) -> Integer.compare(c1.fuelCapacity, c2.fuelCapacity));
    }
    public static void showExtent(){
        System.out.println(extent);
    }
    public void addServiceDate()
    {
        serviceDates.add(new Date());
    }
    public void addServiceDate(Date serviceDate)
    {
        serviceDates.add(serviceDate);
    }
    public static void writeExtent(ObjectOutputStream stream) throws IOException
    {
        stream.writeObject(extent);
    }
    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        extent = (ArrayList<Car>) stream.readObject();
    }

    @Override
    public String toString() {
        return "Car{" +
                "fuelCapacity=" + fuelCapacity +
                ", fuelUsePer100=" + fuelUsePer100 +
                ", modelName='" + modelName + '\'' +
                ", horsepower=" + horsepower +
                ", productionDate=" + productionDate +
                ", serviceDates=" + serviceDates +
                '}';
    }
}
