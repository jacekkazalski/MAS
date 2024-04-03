package MP1;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        String file = "./extent.json";
        try{
            Car.readExtent(new ObjectInputStream(new FileInputStream(file)));
        }catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        Car car1 = new Car(40,7.0,200,new Date(2023, Calendar.JUNE,20),"Opel");
        Car car2 = new Car(50,7.0,300,new Date(2012, Calendar.MAY,10), "POLONEZ");

        System.out.println(Car.findLargestTank());
        System.out.println(car1.getRange());
        car2.addServiceDate();
        car2.addServiceDate(new Date(2024, Calendar.JANUARY, 15));
        System.out.println(car2);
        try{
            Car.writeExtent(new ObjectOutputStream(new FileOutputStream(file)));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        Car.showExtent();


    }
}
