package com.solvd.mail.buildings;

import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import com.solvd.mail.person.DeliveryMan;
import com.solvd.mail.person.Driver;
import com.solvd.mail.person.Pilot;
import com.solvd.mail.person.PostOfficeWorker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PostOffice extends Building {
    private int openingTime;
    private int closingTime;
    private final CarDeliveryDepartment carDeliveryDepartment;
    private final PlaneDeliveryDepartment planeDeliveryDepartment;
    private final ArrayList<PostOfficeWorker> postOfficeWorkers = new ArrayList<>();
    private final ArrayList<DeliveryMan> deliveryMEN = new ArrayList<>();
    private final ArrayList<Letter> airLetters = new ArrayList<>();
    private final ArrayList<Letter> carLetters = new ArrayList<>();
    private final ArrayList<Package> airPackages = new ArrayList<>();
    private final ArrayList<Package> carPackages = new ArrayList<>();

    private final Map<Integer, Letter> ALL_LETTERS = new HashMap<>();
    private final Map<Integer, Package> ALL_PACKAGES = new HashMap<>();

    enum EUROPEAN_CITIES {
        VITEBSK(55.187222, 30.205116),
        MOGILEV(53.9168, 30.3449),
        MINSK(53.893009, 27.567444),
        GOMEL(52.4345, 30.9754),
        GRODNA(53.669353, 23.813131),
        BREST(52.097622, 23.734051),
        VILNIUS(54.687157, 25.279652),
        PRAHA(50.0833, 14.4667),
        PARIS(48.856614, 2.3522219),
        LONDON(51.509865, -0.118092),
        KIYIV(50.450001, 30.523333);

        private final double latitude;
        private final double longitude;

        EUROPEAN_CITIES(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public boolean checkString(String str) {

            return EUROPEAN_CITIES.getCities().stream().anyMatch((x) -> x.equals(str));
        }

        public static ArrayList<String> getCities() {
            return new ArrayList<>(Arrays.stream(EUROPEAN_CITIES.values()).map((x) -> x.name()).sorted().toList());

        }

        public static EUROPEAN_CITIES getIndex(int a) {
            return values()[a];
        }
    }

    ;

    enum NORTH_AMERICA_CITIES {
        NEW_YORK(40.730610, -73.935242),
        CHICAGO(41.881832, -87.623177),
        VANCOUVER(49.246292, -123.116226),
        TORONTO(43.651070, -79.347015),
        BOSTON(42.361145, -71.057083),
        ATLANTA(33.753746, -84.386330),
        SANDIEGO(32.715736, -117.161087),
        LOSANGELES(34.052235, -118.243683),
        CHARLOTTE(35.227085, -80.843124),
        HOUSTON(29.749907, -95.358421),
        DALLAS(32.779167, -96.808891),
        SANFRANCISCO(37.773972, -122.431297),
        DENVER(39.742043, -104.991531),
        PHILADELPHIA(39.952583, -75.165222);

        private final double latitude;
        private final double longitude;

        NORTH_AMERICA_CITIES(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public boolean checkString(String str) {

            return NORTH_AMERICA_CITIES.getCities().stream().anyMatch((x) -> x.equals(str));

        }

        public static ArrayList<String> getCities() {
            return new ArrayList<>(Arrays.stream(NORTH_AMERICA_CITIES.values()).map((x) -> x.name()).sorted().toList());
        }

        public static NORTH_AMERICA_CITIES getIndex(int a) {
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
        ArrayList<String> cities = EUROPEAN_CITIES.getCities();
        cities.addAll(NORTH_AMERICA_CITIES.getCities());
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

    public void addWorker(Driver d) {
        carDeliveryDepartment.addDriver(d);
    }

    public void addWorker(Pilot p) {
        planeDeliveryDepartment.addPilot(p);
    }

    public int send(Letter letter) {
        int cost;
        if (letter.getDeparture().equals(letter.getDestination())) {
            addLetter(letter);
            cost = 5;
        } else {
            ArrayList<String> europe = EUROPEAN_CITIES.getCities();
            ArrayList<String> northernAmerica = NORTH_AMERICA_CITIES.getCities();

            String dep = letter.getDeparture();
            String dest = letter.getDestination();

            if ((europe.contains(letter.getDeparture()) && europe.contains(letter.getDestination()))) {
                carLetters.add(letter);
                cost = 10 *
                        (int) Math.sqrt(Math.pow(EUROPEAN_CITIES.valueOf(dep).latitude - EUROPEAN_CITIES.valueOf(dest).latitude, 2) +
                                Math.pow(EUROPEAN_CITIES.valueOf(dep).longitude - EUROPEAN_CITIES.valueOf(dest).longitude, 2));
            } else if ((northernAmerica.contains(letter.getDeparture()) && northernAmerica.contains(letter.getDestination()))) {
                carLetters.add(letter);
                cost = 10 *
                        (int) Math.sqrt(Math.pow(NORTH_AMERICA_CITIES.valueOf(dep).latitude - NORTH_AMERICA_CITIES.valueOf(dest).latitude, 2) +
                                Math.pow(NORTH_AMERICA_CITIES.valueOf(dep).longitude - NORTH_AMERICA_CITIES.valueOf(dest).longitude, 2));
            } else {
                airLetters.add(letter);

                int edep = europe.indexOf(letter.getDeparture());
                int edes = europe.indexOf(letter.getDestination());
                int adep = northernAmerica.indexOf(letter.getDeparture());
                int ades = northernAmerica.indexOf(letter.getDestination());

                int a = Math.max(edep, edes);
                int b = Math.max(adep, ades);
                EUROPEAN_CITIES e = EUROPEAN_CITIES.getIndex(a);
                NORTH_AMERICA_CITIES n = NORTH_AMERICA_CITIES.getIndex(b);

                cost = 10 *
                        (int) Math.sqrt(Math.pow(e.latitude - n.latitude, 2) +
                                Math.pow(e.longitude - n.longitude, 2));
            }
        }
        ALL_LETTERS.put(letter.getID(), letter);
        letter.setCost(cost);
        return cost;
    }

    public int send(Package pac) {
        int cost = 0;
        if (pac.getType().equals("EXPRESS")) {
            cost += 200;
        }
        if (pac.getDeparture().equals(pac.getDestination())) {
            addPackage(pac);
            cost += (int) (0.5 * pac.getWeight());

        } else {
            ArrayList<String> europe = EUROPEAN_CITIES.getCities();
            ArrayList<String> northernAmerica = NORTH_AMERICA_CITIES.getCities();
            String dep = pac.getDeparture();
            String dest = pac.getDestination();
            if ((europe.contains(pac.getDeparture()) && europe.contains(pac.getDestination()))) {
                carPackages.add(pac);
                cost += (int) (pac.getWeight() * 0.25 *
                        (int) Math.sqrt(Math.pow(EUROPEAN_CITIES.valueOf(dep).latitude - EUROPEAN_CITIES.valueOf(dest).latitude, 2) +
                                Math.pow(EUROPEAN_CITIES.valueOf(dep).longitude - EUROPEAN_CITIES.valueOf(dest).longitude, 2)));
            } else if ((northernAmerica.contains(pac.getDeparture()) && northernAmerica.contains(pac.getDestination()))) {
                carPackages.add(pac);
                cost += (int) (pac.getWeight() * 0.25 *
                        (int) Math.sqrt(Math.pow(NORTH_AMERICA_CITIES.valueOf(dep).latitude - NORTH_AMERICA_CITIES.valueOf(dest).latitude, 2) +
                                Math.pow(NORTH_AMERICA_CITIES.valueOf(dep).longitude - NORTH_AMERICA_CITIES.valueOf(dest).longitude, 2)));
            } else {
                airPackages.add(pac);

                int edep = europe.indexOf(pac.getDeparture());
                int edes = europe.indexOf(pac.getDestination());
                int adep = northernAmerica.indexOf(pac.getDeparture());
                int ades = northernAmerica.indexOf(pac.getDestination());

                int a = Math.max(edep, edes);
                int b = Math.max(adep, ades);

                EUROPEAN_CITIES e = EUROPEAN_CITIES.getIndex(a);
                NORTH_AMERICA_CITIES n = NORTH_AMERICA_CITIES.getIndex(b);

                cost += (int) (0.2 * pac.getWeight() *
                        (int) Math.pow(Math.pow(e.latitude - n.latitude, 2) +
                                Math.pow(e.longitude - n.longitude, 2), 0.5));
            }
        }
        ALL_PACKAGES.put(pac.getID(), pac);
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
        StringBuilder res = new StringBuilder("PostOffice  - " + super.getName());

        res.append("\nairLetters:\n");
        if (airLetters.isEmpty())
            res.append("     ...");
        res.append(airLetters.stream().map(x -> x.toString()).collect(Collectors.joining()));


        res.append("\nairPackages:\n");
        if (airPackages.isEmpty())
            res.append("     ...");
        res.append(airPackages.stream().map(x -> x.toString()).collect(Collectors.joining()));


        res.append("\ncarLetters:\n");
        if (carLetters.isEmpty())
            res.append("     ...");
        res.append(carLetters.stream().map(x -> x.toString()).collect(Collectors.joining()));


        res.append("\ncarPackages:\n");
        if (carPackages.isEmpty())
            res.append("     ...");
        res.append(carPackages.stream().map(x -> x.toString()).collect(Collectors.joining()));


        res.append("\nlocalLetters:\n");
        if (getLetters().isEmpty())
            res.append("     ...");
        res.append(getLetters().stream().map(x -> x.toString()).collect(Collectors.joining()));


        res.append("\nlocalPackages:\n");
        if (getPackages().isEmpty())
            res.append("     ...");
        res.append(getPackages().stream().map(x -> x.toString()).collect(Collectors.joining()));

        return res.toString();
    }

    public boolean sendingDay() {
        ArrayList<DeliveryMan> delivers = new ArrayList<>(deliveryMEN);
        double probability = 0;
        for (DeliveryMan deliver : delivers) {
            probability += deliver.getBicycle().getStatus();
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

    public String getWorkers() {
        String res = "";
        res += postOfficeWorkers.stream().map(x -> x.toString()).collect(Collectors.joining("\n")) + "\n";
        res += deliveryMEN.stream().map(x -> x.toString()).collect(Collectors.joining("\n")) + "\n";

        res += carDeliveryDepartment.getDrivers();
        res += planeDeliveryDepartment.getPilots();
        return res;
    }

    public Map<Integer, Letter> getALL_LETTERS() {
        return ALL_LETTERS;
    }

    public Map<Integer, Package> getALL_PACKAGES() {
        return ALL_PACKAGES;
    }
}
