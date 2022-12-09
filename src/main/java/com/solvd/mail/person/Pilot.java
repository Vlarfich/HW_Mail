package com.solvd.mail.person;

import com.solvd.mail.parcel.Letter;
import com.solvd.mail.vehichle.Plane;

import java.util.Objects;

public class Pilot extends com.solvd.mail.person.Person {
    private int flightTime;
    private static int current_id = 1;
    private final int id = current_id++;
    private Plane plane;

    public Pilot(String name, int age, int experience, int yearsOfWork, int flightTime, Plane plane) {
        super(name, age, experience, yearsOfWork);
        this.flightTime = Math.abs(flightTime);
        this.plane = plane;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @Override
    public String toString() {
        return String.format("*%16s", getClass().getSimpleName()) + " [" +
                id + ", " +
                super.toString() +
                ", FlightTime:" + flightTime +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pilot pilot = (Pilot) o;
        return flightTime == pilot.flightTime && id == pilot.id && plane.equals(pilot.plane);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flightTime, id, plane);
    }
}
