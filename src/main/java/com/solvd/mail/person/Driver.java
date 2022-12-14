package com.solvd.mail.person;

import com.solvd.mail.vehichle.Car;
import com.solvd.mail.vehichle.Plane;

import java.util.Arrays;
import java.util.Objects;

public class Driver extends Person {
    private char[] drivingCategories;
    private static int current_id = 1;
    private final int id = current_id++;
    private Car car;

    public Driver(String name, int age, int experience, int yearsOfWork, char[] categories, Car car) {
        super(name, age, experience, yearsOfWork);
        this.drivingCategories = categories;
        this.car = car;
    }

    public char[] getDrivingCategories() {
        return drivingCategories;
    }

    public void setDrivingCategories(char[] drivingCategories) {
        this.drivingCategories = drivingCategories;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        StringBuilder categories = new StringBuilder("{");
        for (int i = 0; i < drivingCategories.length - 1; i++) {
            categories.append(drivingCategories[i]).append(", ");
        }
        categories.append(drivingCategories[drivingCategories.length - 1]).append("}");
        return String.format("*%16s", getClass().getSimpleName()) + " [" +
                id + ", " +
                super.toString() +
                ", Driving Categories:" + categories +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return id == driver.id && Arrays.equals(drivingCategories, driver.drivingCategories) && car.equals(driver.car);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), id, car);
        result = 31 * result + Arrays.hashCode(drivingCategories);
        return result;
    }
}
