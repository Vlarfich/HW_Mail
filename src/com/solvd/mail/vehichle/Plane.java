package com.solvd.mail.vehichle;

public class Plane extends FlyingVehicle {
    private int amountOfEngines;

    public Plane(String model, int maxSpeed, double status, int maximumHeight, int maximumWeightOfPackages, int weight,
                 int amountOfEngines) {
        super(model, maxSpeed, status, maximumHeight, maximumWeightOfPackages, weight);
        this.amountOfEngines = (amountOfEngines != 0) ? Math.abs(amountOfEngines) : 1;
    }


}
