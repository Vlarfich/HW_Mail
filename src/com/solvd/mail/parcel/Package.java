package com.solvd.mail.parcel;

import java.util.Date;
import java.util.Objects;

public class Package implements Deliverable {
    private String departure;
    private String destination;
    private Date dateOfDispatch;
    private double weight;
    private String type;
    private boolean delivered;
    private static int current_id = 1;
    private final int id = current_id++;

    private int cost;

    public Package(String departure, String destination, Date dateOfDispatch, double weight, String type) {
        this.departure = departure;
        this.destination = destination;
        this.dateOfDispatch = dateOfDispatch;
        this.weight = weight;
        this.type = type;
        this.delivered = false;
    }

    @Override
    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateOfDispatch() {
        return dateOfDispatch;
    }

    public void setDateOfDispatch(Date dateOfDispatch) {
        this.dateOfDispatch = dateOfDispatch;
    }

    @Override
    public boolean isDelivered() {
        return delivered;
    }

    @Override
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        String wei = String.format("%.2f", weight);
        return String.format("*%16s", getClass().getSimpleName()) + " [" +
                id + ", " +
                departure + " -> " +
                destination + ", |" +
                wei + "| (" +
                type + "), " +
                dateOfDispatch + "] \t" + cost + "$ \t" + isDelivered() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return Double.compare(aPackage.weight, weight) == 0 && id == aPackage.id &&
                departure.equals(aPackage.departure) && destination.equals(aPackage.destination) &&
                dateOfDispatch.equals(aPackage.dateOfDispatch) && type.equals(aPackage.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, destination, dateOfDispatch, weight, type, id);
    }

    @Override
    public int getID() {
        return id;
    }
}
