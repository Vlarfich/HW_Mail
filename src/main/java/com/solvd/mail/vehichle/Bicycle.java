package com.solvd.mail.vehichle;

import java.util.Objects;

public class Bicycle extends LandVehicle {
    private boolean speedSwitching;

    public Bicycle(String model, int maxSpeed, double status, int yearOfTechnicalInspection, int yearOfProduction,
                   int mileage, boolean speedSwitching) {
        super(model, maxSpeed, status, yearOfTechnicalInspection, yearOfProduction, mileage);
        this.speedSwitching = speedSwitching;
    }

    public boolean isSpeedSwitching() {
        return speedSwitching;
    }

    public void setSpeedSwitching(boolean speedSwitching) {
        if (!this.speedSwitching)
            this.speedSwitching = speedSwitching;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bicycle bicycle = (Bicycle) o;
        return speedSwitching == bicycle.speedSwitching;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speedSwitching);
    }
}
