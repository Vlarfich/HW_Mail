package com.solvd.mail.parcel;

import java.util.*;

public class Letter implements Deliverable {
    private String departure;
    private String destination;
    private Date dateOfDispatch;
    private boolean delivered;
    private static int current_id = 1;
    private final int id = current_id++;

    private int cost;

    public Letter(String departure, String destination, Date dateOfDispatch) {
        this.departure = departure;
        this.destination = destination;
        this.dateOfDispatch = dateOfDispatch;
        this.delivered = false;
    }

    @Override
    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        if (departure != null)
            this.departure = departure;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        if (destination != null)
            this.destination = destination;
    }

    public Date getDateOfDispatch() {
        return dateOfDispatch;
    }

    public void setDateOfDispatch(Date dateOfDispatch) {
        if (dateOfDispatch != null)
            this.dateOfDispatch = dateOfDispatch;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        if (!this.delivered)
            this.delivered = delivered;
    }

    @Override
    public String toString() {
        return String.format("*%16s", getClass().getSimpleName()) +
                " [" + id + ", " +
                departure + " -> " +
                destination + ", " +
                dateOfDispatch + "] \t" + cost + "$ \t" + isDelivered() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter = (Letter) o;
        return id == letter.id && departure.equals(letter.departure) && destination.equals(letter.destination) &&
                dateOfDispatch.equals(letter.dateOfDispatch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, destination, dateOfDispatch, id);
    }

    @Override
    public int getID() {
        return id;
    }
}
