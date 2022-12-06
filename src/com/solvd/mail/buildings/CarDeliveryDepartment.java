package com.solvd.mail.buildings;

import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import com.solvd.mail.person.Driver;

import java.util.ArrayList;

public class CarDeliveryDepartment extends Building {
    private int amountOfCars;
    private int amountOfDrivers;
    private final ArrayList<Driver> drivers = new ArrayList<>();

    public CarDeliveryDepartment(String name, int amountOfCars, int amountOfDrivers) {
        super(name);
        this.amountOfCars = Math.abs(amountOfCars);
        this.amountOfDrivers = Math.abs(amountOfDrivers);
    }

    public CarDeliveryDepartment(String name) {
        super(name);
        this.amountOfCars = 0;
        this.amountOfDrivers = 0;
    }

    public int getAmountOfCars() {
        return amountOfCars;
    }

    public void setAmountOfCars(int amountOfCars) {
        this.amountOfCars = Math.abs(amountOfCars);
    }

    public int getAmountOfDrivers() {
        return amountOfDrivers;
    }

    public void setAmountOfDrivers(int amountOfDrivers) {
        this.amountOfDrivers = Math.abs(amountOfDrivers);
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public final boolean sendingDay(ArrayList<Letter> letters, ArrayList<Package> packages) {
        if (letters == null && packages == null)
            return false;

        ArrayList<Driver> drivars1 = new ArrayList<>(drivers);
        double probability = 0;
        for (Driver driver : drivars1) {
            probability += driver.getCar().getStatus();
        }
        probability *= 100;
        probability /= drivars1.size();

        System.out.println("Car delivery probability = " + probability);

        boolean sentSMTHG = false;

        for (Letter letter : letters) {
            int prob = (int) (Math.random() * 100);
            if (prob <= probability) {
                sentSMTHG = true;
                letter.setDelivered(true);
            } else {
                if (!letter.isDelivered())
                    letter.setDelivered(false);
            }
        }

        for (Package aPackage : packages) {
            int prob = (int) (Math.random() * 100);
            if (prob <= probability) {
                sentSMTHG = true;
                aPackage.setDelivered(true);
            } else {
                if (!aPackage.isDelivered())
                    aPackage.setDelivered(false);
            }
        }

        return sentSMTHG;
    }

    public String getDrivers(){
        String res = "";
        for(Driver s : drivers){
            res += s.toString() + "\n";
        }
        return res;
    }

}
