package com.solvd.mail.main;

import com.solvd.mail.buildings.PostOffice;
import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public final class DeliverableGenerator {
    private enum TYPES {
        EXPRESS, FRAGILE, MILITARY, HUMANITARIAN, MEDICINE, ANIMALS;

        public static ArrayList<String> getNames() {
            ArrayList<String> cities = new ArrayList<>();
            for (TYPES i : values()) {
                cities.add(i.name());
            }
            return cities;
        }

        public static String get(int i) {
            return getNames().get(i);
        }

        public static int size() {
            return getNames().size();
        }
    }

    public static String getDeparture() {
        ArrayList<String> cities = PostOffice.getCities();
        return cities.get((int) (Math.random() * cities.size()));
    }

    public static String getDestination(String departure) {
        ArrayList<String> cities = PostOffice.getCities();
        //cities.remove(departure);
        return cities.get((int) (Math.random() * cities.size()));
    }

    public static Letter getLetter() {
        String departure = getDeparture();
        return new Letter(departure, getDestination(departure), new Date());
    }

    public static Package getPackage() {
        String departure = getDeparture();
        double weight = Math.random() * 1000;
        String type = TYPES.get((int) (Math.random() * TYPES.size()));
        return new Package(departure, getDestination(departure), new Date(), weight, type);
    }
}
