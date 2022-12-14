package com.solvd.mail.vehichle;

import java.util.Objects;

public class Car extends LandVehicle {
    private boolean accidentParticipation;

    public Car(String model, int maxSpeed, double status, int yearOfTechnicalInspection, int yearOfProduction,
               int mileage, boolean accidentParticipation) {
        super(model, maxSpeed, status, yearOfTechnicalInspection, yearOfProduction, mileage);
        this.accidentParticipation = accidentParticipation;
    }

    public boolean isAccidentParticipation() {
        return accidentParticipation;
    }

    public void setAccidentParticipation(boolean accidentParticipation) {
        if (!this.accidentParticipation)
            this.accidentParticipation = accidentParticipation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return accidentParticipation == car.accidentParticipation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accidentParticipation);
    }
}
