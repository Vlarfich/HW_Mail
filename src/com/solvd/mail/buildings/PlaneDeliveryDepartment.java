package com.solvd.mail.buildings;

import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import com.solvd.mail.person.Driver;
import com.solvd.mail.person.Pilot;

import java.util.ArrayList;

public class PlaneDeliveryDepartment extends Building {
    private int amountOfPlanes;
    private int amountOfPilots;
    private ArrayList<Pilot> pilots = new ArrayList<>();

    public PlaneDeliveryDepartment(String name, int amountOfPlanes, int amountOfPilots) {
        super(name);
        this.amountOfPlanes = Math.abs(amountOfPlanes);
        this.amountOfPilots = Math.abs(amountOfPilots);
    }

    public PlaneDeliveryDepartment(String name) {
        super(name);
        this.amountOfPlanes = 0;
        this.amountOfPilots = 0;
    }

    public int getAmountOfPlanes() {
        return amountOfPlanes;
    }

    public void setAmountOfPlanes(int amountOfPlanes) {
        this.amountOfPlanes = Math.abs(amountOfPlanes);
    }

    public int getAmountOfPilots() {
        return amountOfPilots;
    }

    public void setAmountOfPilots(int amountOfPilots) {
        this.amountOfPilots = Math.abs(amountOfPilots);
    }

    public void addPilot(Pilot pilot) {
        pilots.add(pilot);
    }

    public boolean sendingDay(ArrayList<Letter> letters, ArrayList<Package> packages) {
        if (letters == null && packages == null)
            return false;

        ArrayList<Pilot> pilots1 = new ArrayList<>(pilots);
        double probability = 0;
        for (int i = 0; i < pilots1.size(); i++) {
            probability += pilots1.get(i).getPlane().getStatus();
        }
        probability *= 100;
        probability /= pilots1.size();

        System.out.println("Plane delivery probability = " + probability);

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
