package com.solvd.mail.buildings;

import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import com.solvd.mail.person.Driver;

import java.util.ArrayList;

public class CarDeliveryDepartment extends Building {
    private int amountOfCars;
    private int amountOfDrivers;
    private ArrayList<Driver> drivers = new ArrayList<>();

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

    public boolean sendingDay(ArrayList<Letter> letters, ArrayList<Package> packages) {
        if (letters == null && packages == null)
            return false;

        ArrayList<Driver> drivars1 = new ArrayList<>(drivers);
        double probability = 0;
        for (int i = 0; i < drivars1.size(); i++) {
            probability += drivars1.get(i).getCar().getStatus();
        }
        probability *= 100;
        probability /= drivars1.size();

        System.out.println("Car delivery probability = " + probability);

        boolean sentSMTHG = false;

        for (int i = 0; i < letters.size(); i++) {
            int prob = (int) (Math.random() * 100);
            if (prob <= probability) {
                sentSMTHG = true;
                letters.get(i).setDelivered(true);
            } else {
                if (!letters.get(i).isDelivered())
                    letters.get(i).setDelivered(false);
            }
        }

        for (int i = 0; i < packages.size(); i++) {
            int prob = (int) (Math.random() * 100);
            if (prob <= probability) {
                sentSMTHG = true;
                packages.get(i).setDelivered(true);
            } else {
                if (!packages.get(i).isDelivered())
                    packages.get(i).setDelivered(false);
            }
        }

        return sentSMTHG;
    }

}
