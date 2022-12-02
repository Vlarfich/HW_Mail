package com.solvd.mail.main;

import com.solvd.mail.buildings.CarDeliveryDepartment;
import com.solvd.mail.buildings.PlaneDeliveryDepartment;
import com.solvd.mail.buildings.PostOffice;
import com.solvd.mail.person.DeliveryMan;
import com.solvd.mail.person.Driver;
import com.solvd.mail.person.Pilot;
import com.solvd.mail.person.PostOfficeWorker;
import com.solvd.mail.vehichle.Bicycle;
import com.solvd.mail.vehichle.Car;
import com.solvd.mail.vehichle.Plane;

import java.util.ArrayList;
import java.util.Arrays;

public final class PostOfficeGenerator {
    private enum names {
        George, Michael, Stephen, Kevin, Chris,
        Draymond, Nicola, Davis, Margaret, Lucy, Paul, Gretta, Lena, Daisy;

        public static ArrayList<String> getNames() {
            ArrayList<String> cities = new ArrayList<>();
            for (names i : values()) {
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

    private enum officeNames {
        SECRET_OFFICE, PALM_OFFICE, RIVER_OFFICE,
        DESERT_OFFICE, ISLAND_OFFICE, MOUNTAIN_OFFICE, UNDERWATER_OFFICE;

        public static ArrayList<String> getNames() {
            ArrayList<String> cities = new ArrayList<>();
            for (officeNames i : values()) {
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

    private static ArrayList<String> positions = new ArrayList<>();
    private static ArrayList<String> planeModels = new ArrayList<>();
    private static ArrayList<String> carModels = new ArrayList<>();
    private static ArrayList<Character> categories = new ArrayList<>();
    private static ArrayList<String> bikeModels = new ArrayList<>();
    static {
        positions.addAll(Arrays.asList("Manager", "Consultant", "Security", "Cleaner", "Accountant", "Cashier"));
        planeModels.addAll(Arrays.asList("BOEING", "IS-21", "HELICOPTER", "MI-8"));
        carModels.addAll(Arrays.asList("NISSAN", "BMW", "HYUNDAI", "FERRARI", "PORSHE", "VOLKSWAGEN", "CHEVROLET", "FORD"));
        categories.addAll(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G'));
        bikeModels.addAll(Arrays.asList("AIST", "MINSK", "SHADOW", "BBB", "A2-123"));
    }


    public final static PostOfficeWorker getPostOfficeWorker() {
        String name = names.get((int) (Math.random() * names.size()));
        int age = (int) (Math.random() * 40) + 20;
        int experience = age - 20 - (int) (Math.random() * (age - 20));
        int yearsOfWork = experience / 2;
        String position = positions.get((int) (Math.random() * positions.size()));
        return new PostOfficeWorker(name, age, experience, yearsOfWork, position);
    }

    public final static Plane getPlane() {
        String model = planeModels.get((int) (Math.random() * planeModels.size()));
        int maxSpeed = (int) (Math.random() * 2000) + 500;
        double status = Math.random() * 0.70 + 0.30;
        int maxH = (int) (Math.random() * 4000) + 4000;
        int maxW = (int) (Math.random() * 12000) + 2000;
        int weight = (int) (Math.random() * 2000) + 2000;
        return new Plane(model, maxSpeed, status, maxH, maxW, weight);
    }

    public final static Pilot getPilot() {
        String name = names.get((int) (Math.random() * names.size()));
        int age = (int) (Math.random() * 40) + 20;
        int experience = age - 20 - (int) (Math.random() * (age - 20));
        int yearsOfWork = experience / 2;
        int flightTime = (int) (Math.random() * 100000);
        return new Pilot(name, age, experience, yearsOfWork, flightTime, getPlane());
    }

    public final static Car getCar() {

        String model = carModels.get((int) (Math.random() * carModels.size()));
        int maxSpeed = (int) (Math.random() * 150) + 100;
        int mileage = (int) (Math.random() * 60000);
        double status = Math.random() * 0.90 + 0.10;
        int yotinsp = (int) (Math.random() * 3);
        int prod = (int) (Math.random() * 32) + 1990;
        int check = (int) (Math.random() * 1);
        boolean accident = check == 0;
        return new Car(model, maxSpeed, status, yotinsp, prod, mileage, accident);
    }

    public final static Driver getDriver() {

        String name = names.get((int) (Math.random() * names.size()));
        int age = (int) (Math.random() * 40) + 20;
        int experience = age - 20 - (int) (Math.random() * (age - 20));
        int yearsOfWork = experience / 2;
        int index = (int) (Math.random() * (categories.size())) + 1;
        char[] drivingCategories = new char[index];
        for (int i = 0; i < index; i++) {
            drivingCategories[i] = categories.get(i);
        }
        return new Driver(name, age, experience, yearsOfWork, drivingCategories, getCar());
    }

    public final static Bicycle getBicycle() {

        String model = bikeModels.get((int) (Math.random() * bikeModels.size()));
        int maxSpeed = (int) (Math.random() * 150) + 100;
        int mileage = (int) (Math.random() * 60000);
        double status = Math.random() * 0.90 + 0.10;
        int yotinsp = (int) (Math.random() * 3);
        int prod = (int) (Math.random() * 32) + 1990;
        int check = (int) (Math.random() * 1);
        boolean switchable = check == 0;
        return new Bicycle(model, maxSpeed, status, yotinsp, prod, mileage, switchable);
    }

    public final static DeliveryMan getDeliveryMan() {
        String name = names.get((int) (Math.random() * names.size()));
        int age = (int) (Math.random() * 10) + 20;
        int experience = age - 20 - (int) (Math.random() * (age - 10));
        int yearsOfWork = experience / 2;
        int maxAmountOfLetters = (int) (Math.random() * 20);
        return new DeliveryMan(name, age, experience, yearsOfWork, maxAmountOfLetters, getBicycle());
    }

    public final static CarDeliveryDepartment getCarDeliveryDepartment() {
        String name = officeNames.get((int) (Math.random() * officeNames.size()));
        int amOfCars = (int) (Math.random() * 50) + 1;
        int amOfDrivers = (int) (Math.random() * 50) + 1;
        CarDeliveryDepartment res = new CarDeliveryDepartment(name, amOfCars, amOfDrivers);
        for (int i = 0; i < amOfDrivers; i++) {
            res.addDriver(getDriver());
        }

        return res;
    }

    public final static PlaneDeliveryDepartment getPlaneDeliveryDepartment() {
        String name = officeNames.get((int) (Math.random() * officeNames.size()));
        int amOfPlanes = (int) (Math.random() * 50) + 1;
        int amOfPilots = (int) (Math.random() * 50) + 1;
        PlaneDeliveryDepartment res = new PlaneDeliveryDepartment(name, amOfPlanes, amOfPilots);
        for (int i = 0; i < amOfPilots; i++) {
            res.addPilot(getPilot());
        }
        return res;
    }

    public final static PostOffice getPostOffice() {
        String name = officeNames.get((int) (Math.random() * officeNames.size()));
        int floor = (int) (Math.random() * 5) + 1;
        int sqft = (int) (Math.random() * 200) + 100;
        int open = (int) (Math.random() * 4) + 5;
        int closing = (int) (Math.random() * (22 - open)) + open + 2;
        PostOfficeWorker pow = getPostOfficeWorker();
        PostOffice postOffice = new PostOffice(name, floor, sqft, open, closing, pow, getCarDeliveryDepartment(), getPlaneDeliveryDepartment());
        int index = (int) (Math.random() * 20) + 2;
        for (int i = 0; i < index; i++)
            postOffice.addWorker(getDeliveryMan());
        return postOffice;
    }
}
