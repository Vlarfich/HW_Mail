package com.solvd.mail.buildings;

import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import com.solvd.mail.person.DeliveryMan;
import com.solvd.mail.person.Pilot;
import com.solvd.mail.person.PostOfficeWorker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class PostOffice extends Building {
    private int openingTime;
    private int closingTime;
    private final CarDeliveryDepartment carDeliveryDepartment;
    private final PlaneDeliveryDepartment planeDeliveryDepartment;
    private ArrayList<PostOfficeWorker> postOfficeWorkers = new ArrayList<>();
    private ArrayList<DeliveryMan> deliveryMEN = new ArrayList<>();
    private ArrayList<Letter> airLetters = new ArrayList<>();
    private ArrayList<Letter> carLetters = new ArrayList<>();
    private ArrayList<Package> airPackages = new ArrayList<>();
    private ArrayList<Package> carPackages = new ArrayList<>();

    enum europeanCities {
        Vitebsk(55.187222, 30.205116),
        Mogilev(53.9168, 30.3449),
        Minsk(53.893009, 27.567444),
        Gomel(52.4345, 30.9754),
        Grodna(53.669353, 23.813131),
        Brest(52.097622, 23.734051),
        Vilnius(54.687157, 25.279652),
        Praha(50.0833, 14.4667),
        Paris(48.856614, 2.3522219),
        London(51.509865, -0.118092),
        Kiyv(50.450001, 30.523333);

        private final double latitude;
        private final double longtitude;

        europeanCities(double latitude, double longtitude) {
            this.latitude = latitude;
            this.longtitude = longtitude;
        }

        public boolean checkString(String str) {
            for (europeanCities i : values()) {
                if (str.equals(i.name())) {
                    return true;
                }
            }
            return false;
        }

        public static ArrayList<String> getCities() {
            ArrayList<String> cities = new ArrayList<>();
            for (europeanCities i : europeanCities.values()) {
                cities.add(i.name());
            }
            return cities;
        }

        public static europeanCities getIndex(int a) {
            return values()[a];
        }
    }

    ;

    enum northAmericaCities {
        NewYork(40.730610, -73.935242),
        Chicago(41.881832, -87.623177),
        Vancouver(49.246292, -123.116226),
        Toronto(43.651070, -79.347015),
        Boston(42.361145, -71.057083),
        Atlanta(33.753746, -84.386330),
        SanDiego(32.715736, -117.161087),
        LosAngeles(34.052235, -118.243683),
        Charlotte(35.227085, -80.843124),
        Houston(29.749907, -95.358421),
        Dallas(32.779167, -96.808891),
        SanFrancisco(37.773972, -122.431297),
        Denver(39.742043, -104.991531),
        Philadelphia(39.952583, -75.165222);

        private final double latitude;
        private final double longtitude;

        northAmericaCities(double latitude, double longtitude) {
            this.latitude = latitude;
            this.longtitude = longtitude;
        }

        public boolean checkString(String str) {
            for (northAmericaCities i : values()) {
                if (str.equals(i.name())) {
                    return true;
                }
            }
            return false;
        }

        public static ArrayList<String> getCities() {
            ArrayList<String> cities = new ArrayList<>();
            for (northAmericaCities i : northAmericaCities.values()) {
                cities.add(i.name());
            }
            return cities;
        }

        public static northAmericaCities getIndex(int a) {
            return values()[a];
        }
    }

    ;

    public PostOffice(String name, int floor, double squareFeet, int openingTime, int closingTime,
                      PostOfficeWorker pow, CarDeliveryDepartment cdd, PlaneDeliveryDepartment pdd) {
        super(name, floor, squareFeet);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.postOfficeWorkers.add(pow);
        this.carDeliveryDepartment = cdd;
        this.planeDeliveryDepartment = pdd;
    }

    public static ArrayList<String> getCities() {
        ArrayList<String> cities = new ArrayList<>();
        for (String i : europeanCities.getCities()) {
            cities.add(i);
        }
        for (String i : northAmericaCities.getCities()) {
            cities.add(i);
        }
        return cities;
    }


    public int getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(int openingTime) {
        this.openingTime = Math.abs(openingTime);
    }

    public int getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = Math.abs(closingTime);
    }


    public void addWorker(PostOfficeWorker pow) {
        postOfficeWorkers.add(pow);
    }

    public void addWorker(DeliveryMan dm) {
        deliveryMEN.add(dm);
    }


    public int send(Letter letter) {
        int cost;
        if (letter.getDeparture().equals(letter.getDestination())) {
            addLetter(letter);
            cost = 5;
            letter.setCost(cost);
            return cost;
        }
        ArrayList<String> europe = europeanCities.getCities();
        ArrayList<String> northernAmerica = northAmericaCities.getCities();

        String dep = letter.getDeparture();
        String dest = letter.getDestination();

        if ((europe.contains(letter.getDeparture()) && europe.contains(letter.getDestination()))) {

            carLetters.add(letter);
            cost = 10 *
                    (int) Math.pow(Math.pow(europeanCities.valueOf(dep).latitude - europeanCities.valueOf(dest).latitude, 2) +
                            Math.pow(europeanCities.valueOf(dep).longtitude - europeanCities.valueOf(dest).longtitude, 2), 1 / 2);

            letter.setCost(cost);
            return cost;
        }
        if ((northernAmerica.contains(letter.getDeparture()) && northernAmerica.contains(letter.getDestination()))) {
            carLetters.add(letter);
            cost = 10 *
                    (int) Math.pow(Math.pow(northAmericaCities.valueOf(dep).latitude - northAmericaCities.valueOf(dest).latitude, 2) +
                            Math.pow(northAmericaCities.valueOf(dep).longtitude - northAmericaCities.valueOf(dest).longtitude, 2), 1 / 2);
            letter.setCost(cost);
            return cost;
        }
        airLetters.add(letter);

        int edep = europe.indexOf(letter.getDeparture());
        int edes = europe.indexOf(letter.getDestination());
        int adep = northernAmerica.indexOf(letter.getDeparture());
        int ades = northernAmerica.indexOf(letter.getDestination());

        int a = Math.max(edep, edes);
        int b = Math.max(adep, ades);
        europeanCities e = europeanCities.getIndex(a);
        northAmericaCities n = northAmericaCities.getIndex(b);

        cost = 10 *
                (int) Math.pow(Math.pow(e.latitude - n.latitude, 2) +
                        Math.pow(e.longtitude - n.longtitude, 2), 1 / 2);
        letter.setCost(cost);
        return cost;
    }

    public int send(Package pac) {
        int cost;
        if (pac.getDeparture().equals(pac.getDestination())) {
            addPackage(pac);
            cost = (int) (0.5 * pac.getWeight());
            pac.setCost(cost);
            return cost;
        }
        ArrayList<String> europe = europeanCities.getCities();
        ArrayList<String> northernAmerica = northAmericaCities.getCities();
        String dep = pac.getDeparture();
        String dest = pac.getDestination();
        if ((europe.contains(pac.getDeparture()) && europe.contains(pac.getDestination()))) {
            carPackages.add(pac);
            cost = (int) (pac.getWeight() * 0.25 *
                    (int) Math.pow(Math.pow(europeanCities.valueOf(dep).latitude - europeanCities.valueOf(dest).latitude, 2) +
                            Math.pow(europeanCities.valueOf(dep).longtitude - europeanCities.valueOf(dest).longtitude, 2), 1 / 2));
            pac.setCost(cost);
            return cost;
        }
        if ((northernAmerica.contains(pac.getDeparture()) && northernAmerica.contains(pac.getDestination()))) {
            carPackages.add(pac);
            cost = (int) (pac.getWeight() * 0.25 *
                    (int) Math.pow(Math.pow(northAmericaCities.valueOf(dep).latitude - northAmericaCities.valueOf(dest).latitude, 2) +
                            Math.pow(northAmericaCities.valueOf(dep).longtitude - northAmericaCities.valueOf(dest).longtitude, 2), 1 / 2));
            pac.setCost(cost);
            return cost;
        }
        airPackages.add(pac);

        int edep = europe.indexOf(pac.getDeparture());
        int edes = europe.indexOf(pac.getDestination());
        int adep = northernAmerica.indexOf(pac.getDeparture());
        int ades = northernAmerica.indexOf(pac.getDestination());

        int a = Math.max(edep, edes);
        int b = Math.max(adep, ades);

        europeanCities e = europeanCities.getIndex(a);
        northAmericaCities n = northAmericaCities.getIndex(b);

        cost = (int) (0.2 * pac.getWeight() *
                (int) Math.pow(Math.pow(e.latitude - n.latitude, 2) +
                        Math.pow(e.longtitude - n.longtitude, 2), 1 / 2));
        pac.setCost(cost);
        return cost;
    }

    public boolean checkDelivery(Letter letter) {
        for (Letter l : airLetters) {
            if (l.equals(letter)) {
                if (l.isDelivered()) {
                    airLetters.remove(l);
                    return true;
                }
                break;
            }
        }
        for (Letter l : carLetters) {
            if (l.equals(letter)) {
                if (l.isDelivered()) {
                    carLetters.remove(l);
                    return true;
                }
                break;
            }
        }
        for (Letter l : getLetters()) {
            if (l.equals(letter)) {
                if (l.isDelivered()) {
                    getLetters().remove(l);
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public final boolean checkDelivery(Package letter) {
        for (Package l : airPackages) {
            if (l.equals(letter)) {
                if (l.isDelivered()) {
                    airPackages.remove(letter);
                    return true;
                }
                break;
            }
        }
        for (Package l : carPackages) {
            if (l.equals(letter)) {
                if (l.isDelivered()) {
                    carPackages.remove(l);
                    return true;
                }
                break;
            }
        }
        for (Package l : getPackages()) {
            if (l.equals(letter)) {
                if (l.isDelivered()) {
                    getPackages().remove(l);
                    return true;
                }
                break;
            }
        }
        return false;
    }

    @Override
    public String toString() {
//        String delivers = "";
//        for (int i = 0; i < deliveryMEN.size(); i++) {
//            delivers += deliveryMEN.get(i) + "\n";
//        }
        return "PostOffice{" +super.getName() +
                ", \nairLetters=\n" + airLetters +
                ", \nairPackages=\n" + airPackages +
                ", \ncarLetters=\n" + carLetters +
                ", \ncarPackages=\n" + carPackages +
                ", \nlocalLetters=\n" + getLetters() +
                ", \nlocalPackages=\n" + getPackages() +
                //"\n*------------*\n" + delivers +
                '}';
    }

    public boolean sendingDay() {
        ArrayList<DeliveryMan> delivers = new ArrayList<>(deliveryMEN);
        double probability = 0;
        for (int i = 0; i < delivers.size(); i++) {
            probability += delivers.get(i).getBicycle().getStatus();
        }
        probability *= 100;
        probability /= delivers.size();

        boolean sentSMTHG = false;

        for (int i = 0; i < getLetters().size(); i++) {
            int prob = (int) (Math.random() * 100);
            if (prob <= probability) {
                sentSMTHG = true;
                getLetters().get(i).setDelivered(true);
            } else {
                if (!getLetters().get(i).isDelivered())
                    getLetters().get(i).setDelivered(false);
            }
        }

        for (int i = 0; i < getPackages().size(); i++) {
            int prob = (int) (Math.random() * 100);
            if (prob <= probability) {
                sentSMTHG = true;
                getPackages().get(i).setDelivered(true);
            } else {
                if (!getPackages().get(i).isDelivered())
                    getPackages().get(i).setDelivered(false);
            }
        }
        boolean carSent = carDeliveryDepartment.sendingDay(carLetters, carPackages);
        boolean planeSent = planeDeliveryDepartment.sendingDay(airLetters, airPackages);

        return carSent || planeSent;
    }

    @Override
    public boolean sendingDay(ArrayList<Letter> l, ArrayList<Package> p) {
        return sendingDay();
    }


}
