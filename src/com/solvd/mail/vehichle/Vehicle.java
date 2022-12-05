package com.solvd.mail.vehichle;

import java.util.Objects;

public abstract class Vehicle {
    private String model;
    private int maxSpeed;
    private double status;  // working or not

    public Vehicle(String model, int maxSpeed, double status) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return maxSpeed == vehicle.maxSpeed && Double.compare(vehicle.status, status) == 0 && model.equals(vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maxSpeed, status);
    }
}
