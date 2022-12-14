package com.solvd.mail.person;

import com.solvd.mail.vehichle.Bicycle;

import java.util.Objects;

public class DeliveryMan extends Person {
    private int maxAmountOfLetters;
    private static int current_id = 1;
    private final int id = current_id++;
    private Bicycle bicycle;

    public DeliveryMan(String name, int age, int experience, int yearsOfWork, int maxAmountOfLetters, Bicycle bicycle) {
        super(name, age, experience, yearsOfWork);
        this.maxAmountOfLetters = maxAmountOfLetters;
        this.bicycle = bicycle;
    }

    public int getMaxAmountOfLetters() {
        return maxAmountOfLetters;
    }

    public void setMaxAmountOfLetters(int maxAmountOfLetters) {
        this.maxAmountOfLetters = maxAmountOfLetters;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    @Override
    public String toString() {
        return String.format("*%16s", getClass().getSimpleName()) + " [" +
                id + ", " +
                super.toString() +
                ", Maximum Letters per Ride:" + maxAmountOfLetters +
                ']' + String.format("  %f",bicycle.getStatus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeliveryMan that = (DeliveryMan) o;
        return maxAmountOfLetters == that.maxAmountOfLetters && id == that.id && bicycle.equals(that.bicycle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxAmountOfLetters, id, bicycle);
    }
}
