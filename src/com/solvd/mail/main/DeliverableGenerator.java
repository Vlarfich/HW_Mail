package com.solvd.mail.main;

import com.solvd.mail.buildings.PostOffice;
import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public final class DeliverableGenerator {
    public static String getDeparture() {
        ArrayList<String> cities = PostOffice.getCities();
        return cities.get((int) (Math.random() * cities.size()));
    }

    public static String getDestination(String departure) {
        ArrayList<String> cities = PostOffice.getCities();
        //cities.remove(departure);
        return cities.get((int) (Math.random() * cities.size()));
    }

    public static @NotNull Letter getLetter() {
        String departure = getDeparture();
        return new Letter(departure, getDestination(departure), new Date());
    }

    public static Package getPackage() {
        String departure = getDeparture();
        double weight = Math.random() * 1000;

        ArrayList<String> types = new ArrayList<>(Arrays.asList("Express", "Fragile", "Military",
                "Humanitarian", "Medicine", "Animals"));
        String type = types.get((int) (Math.random() * types.size()));
        return new Package(departure, getDestination(departure), new Date(), weight, type);
    }
}
