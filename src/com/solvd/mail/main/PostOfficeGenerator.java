package com.solvd.mail.main;

import com.solvd.mail.buildings.CarDeliveryDepartment;
import com.solvd.mail.buildings.PlaneDeliveryDepartment;
import com.solvd.mail.buildings.PostOffice;
import com.solvd.mail.exceptions.*;
import com.solvd.mail.person.DeliveryMan;
import com.solvd.mail.person.Driver;
import com.solvd.mail.person.Pilot;
import com.solvd.mail.person.PostOfficeWorker;
import com.solvd.mail.vehichle.Bicycle;
import com.solvd.mail.vehichle.Car;
import com.solvd.mail.vehichle.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public final class PostOfficeGenerator {
    private enum NAMES {
        GEORGE, MICHAEL, STEPHEN, KEVIN, CHRIS,
        DRAYMOND, NICOLA, DAVIS, MARGARET, LUCY,
        PAUL, GRETTA, LENA, DAISY;

        public static ArrayList<String> getNames() {
            ArrayList<String> cities = new ArrayList<>();
            for (NAMES i : values()) {
                cities.add(i.name());
            }

            cities.add(null);

            return cities;
        }

        public static String get(int i) {
            return getNames().get(i);
        }

        public static int size() {
            return getNames().size();
        }
    }

    private enum OFFICE_NAMES {
        SECRET_OFFICE, PALM_OFFICE, RIVER_OFFICE,
        DESERT_OFFICE, ISLAND_OFFICE, MOUNTAIN_OFFICE, UNDERWATER_OFFICE;

        public static ArrayList<String> getNames() {
            ArrayList<String> cities = new ArrayList<>();
            for (OFFICE_NAMES i : values()) {
                cities.add(i.name());
            }

            cities.add(null);

            return cities;
        }

        public static String get(int i) {
            return getNames().get(i);
        }

        public static int size() {
            return getNames().size();
        }
    }

    private static final ArrayList<String> positions = new ArrayList<>();
    private static final ArrayList<String> planeModels = new ArrayList<>();
    private static final ArrayList<String> carModels = new ArrayList<>();
    private static final ArrayList<Character> categories = new ArrayList<>();
    private static final ArrayList<String> bikeModels = new ArrayList<>();

    static final Logger logger = LogManager.getLogger(PostOfficeGenerator.class);

    //static final Logger logger = Logger.getLogger(PostOfficeGenerator.class.getName());
    static {
        positions.addAll(Arrays.asList("Manager", "Consultant", "Security", "Cleaner", "Accountant", "Cashier"));
        positions.sort(Comparator.naturalOrder());
        planeModels.addAll(Arrays.asList("BOEING", "IS-21", "HELICOPTER", "MI-8"));
        carModels.addAll(Arrays.asList("NISSAN", "BMW", "HYUNDAI", "FERRARI", "PORSHE", "VOLKSWAGEN", "CHEVROLET", "FORD"));
        categories.addAll(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G'));
        bikeModels.addAll(Arrays.asList("AIST", "MINSK", "SHADOW", "BBB", "A2-123"));
    }


    public static PostOfficeWorker getPostOfficeWorker() {
        String name;
        try {
            name = NAMES.get((int) (Math.random() * NAMES.size()));
            if (name == null) {
                throw new EPostOWNameIsNull("POW NAME IS NULL");
            }
        } catch (EPostOWNameIsNull e) {
            logger.info(e.getMessage());
            name = e.getMessage();
            //System.err.println(name);
        }
        int age = (int) (Math.random() * 40) + 20;
        int experience = age - 20 - (int) (Math.random() * (age - 20));
        int yearsOfWork = experience / 2;
        String position = positions.get((int) (Math.random() * positions.size()));
        return new PostOfficeWorker(name, age, experience, yearsOfWork, position);
    }

    public static Plane getPlane() {
        String model = planeModels.get((int) (Math.random() * planeModels.size()));
        int maxSpeed = (int) (Math.random() * 2000) + 500;
        double status = Math.random() * 0.70 + 0.30;
        int maxH = (int) (Math.random() * 4000) + 4000;
        int maxW = (int) (Math.random() * 12000) + 2000;
        int weight = (int) (Math.random() * 2000) + 2000;
        int amount = (int) (Math.random() * 5) + 1;
        return new Plane(model, maxSpeed, status, maxH, maxW, weight, amount);
    }

    public static Pilot getPilot() {
        String name;
        try {
            name = NAMES.get((int) (Math.random() * NAMES.size()));
            if (name == null) {
                throw new EPilotNameIsNull("PILOT NAME IS NULL");
            }
        } catch (EPilotNameIsNull e) {
            logger.info(e.getMessage());
            name = e.getMessage();
            //System.err.println(name);
        }
        int age = (int) (Math.random() * 40) + 20;
        int experience = age - 20 - (int) (Math.random() * (age - 20));
        int yearsOfWork = experience / 2;
        int flightTime = (int) (Math.random() * 100000);
        return new Pilot(name, age, experience, yearsOfWork, flightTime, getPlane());
    }

    public static Car getCar() {

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

    public static Driver getDriver() {
        String name;
        try {
            name = NAMES.get((int) (Math.random() * NAMES.size()));
            if (name == null) {
                throw new ExcDriverNameIsNull("DRIVER NAME IS NULL");
            }
        } catch (ExcDriverNameIsNull e) {
            logger.info(e.getMessage());
            name = e.getMessage();
        }
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

    public static Bicycle getBicycle() {

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

    public static DeliveryMan getDeliveryMan() {
        String name;
        try {
            name = NAMES.get((int) (Math.random() * NAMES.size()));
            if (name == null) {
                throw new EDeliveryManNameIsNull("DELIVERYMAN NAME IS NULL");
            }
        } catch (EDeliveryManNameIsNull e) {
            logger.warn(e.getMessage());
            name = e.getMessage();
        }
        int age = (int) (Math.random() * 10) + 20;
        int experience = age - 20 - (int) (Math.random() * (age - 10));
        int yearsOfWork = experience / 2;
        int maxAmountOfLetters = (int) (Math.random() * 20);
        return new DeliveryMan(name, age, experience, yearsOfWork, maxAmountOfLetters, getBicycle());
    }

    public static CarDeliveryDepartment getCarDeliveryDepartment() {
        String name = OFFICE_NAMES.get((int) (Math.random() * OFFICE_NAMES.size()));
        int amOfCars = (int) (Math.random() * 50) + 1;
        int amOfDrivers = (int) (Math.random() * 50) + 1;
        CarDeliveryDepartment res = new CarDeliveryDepartment(name, amOfCars, amOfDrivers);
        for (int i = 0; i < amOfDrivers; i++) {
            res.addDriver(getDriver());
        }

        return res;
    }

    public static PlaneDeliveryDepartment getPlaneDeliveryDepartment() {
        String name = OFFICE_NAMES.get((int) (Math.random() * OFFICE_NAMES.size()));
        int amOfPlanes = (int) (Math.random() * 50) + 1;
        int amOfPilots = (int) (Math.random() * 50) + 1;
        PlaneDeliveryDepartment res = new PlaneDeliveryDepartment(name, amOfPlanes, amOfPilots);
        for (int i = 0; i < amOfPilots; i++) {
            res.addPilot(getPilot());
        }
        return res;
    }

    public static PostOffice getPostOffice() throws EBuildingNameIsNull {
        String name = OFFICE_NAMES.get((int) (Math.random() * OFFICE_NAMES.size()));
        int floor = (int) (Math.random() * 5) + 1;
        int sqft = (int) (Math.random() * 200) + 100;
        int open = (int) (Math.random() * 4) + 5;
        int closing = (int) (Math.random() * (22 - open)) + open + 2;
        PostOfficeWorker pow = getPostOfficeWorker();
        CarDeliveryDepartment cdd = getCarDeliveryDepartment();
        PlaneDeliveryDepartment pdd = getPlaneDeliveryDepartment();
        PostOffice postOffice = new PostOffice(name, floor, sqft, open, closing, pow, cdd, pdd);
        if (postOffice.getName() == null || cdd.getName() == null || pdd.getName() == null) {
            String logMessage = "";
            if(postOffice.getName() == null){
                logMessage += "POST OFFICE NAME, ";
            }
            if(cdd.getName() == null){
                logMessage += "CAR DELIVERY DEPARTMENT, ";
            }
            if(pdd.getName() == null){
                logMessage += "PLANE DELIVERY DEPARTMENT, ";
            }
            logMessage += "NAME IS NULL";
            logger.fatal(logMessage);
            throw new EBuildingNameIsNull(logMessage);
        }

        int index = (int) (Math.random() * 20) + 2;
        for (int i = 0; i < index; i++)
            postOffice.addWorker(getDeliveryMan());
        return postOffice;
    }
}
