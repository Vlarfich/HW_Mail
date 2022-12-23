package com.solvd.mail.buildings;

import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import com.solvd.mail.person.Driver;
import com.solvd.mail.person.Pilot;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PlaneDeliveryDepartment extends Building {
    private int amountOfPlanes;
    private int amountOfPilots;
    private final ArrayList<Pilot> pilots = new ArrayList<>();

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

    public final boolean sendingDay(ArrayList<Letter> letters, ArrayList<Package> packages) {
        if (letters == null && packages == null) return false;

        ArrayList<Pilot> pilots1 = new ArrayList<>(pilots);
        double probability = 0;
        for (Pilot pilot : pilots1) {
            probability += pilot.getPlane().getStatus();
        }
        probability *= 100;
        probability /= pilots1.size();

        LogManager.getLogger(PlaneDeliveryDepartment.class).info("Plane delivery probability = " + probability);

        boolean sentSMTHG = false;

        for (Letter letter : letters) {
            int prob = (int) (Math.random() * 100);
            if (prob <= probability) {
                sentSMTHG = true;
                letter.setDelivered(true);
            } else {
                if (!letter.isDelivered()) letter.setDelivered(false);
            }
        }

        for (Package aPackage : packages) {
            int prob = (int) (Math.random() * 100);
            if (prob <= probability) {
                sentSMTHG = true;
                aPackage.setDelivered(true);
            } else {
                if (!aPackage.isDelivered()) aPackage.setDelivered(false);
            }
        }

        return sentSMTHG;
    }

    public String getPilots() {
        String res = "";
        res += pilots.stream().map(x -> x.toString()).collect(Collectors.joining("\n"));
//        for (Pilot s : pilots) {
//            res += s.toString() + "\n";
//        }
        return res;
    }

}
