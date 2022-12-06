package com.solvd.mail.main;

import com.solvd.mail.buildings.CarDeliveryDepartment;
import com.solvd.mail.buildings.PlaneDeliveryDepartment;
import com.solvd.mail.buildings.PostOffice;
import com.solvd.mail.exceptions.EBuildingNameIsNULL;
import com.solvd.mail.parcel.Deliverable;
import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import com.solvd.mail.person.DeliveryMan;
import com.solvd.mail.person.Driver;
import com.solvd.mail.person.Pilot;
import com.solvd.mail.person.PostOfficeWorker;
import com.solvd.mail.vehichle.Bicycle;
import com.solvd.mail.vehichle.Car;
import com.solvd.mail.vehichle.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws EBuildingNameIsNULL {
        Menu();
    }

    public static boolean printMenu() {
        logger.info("""
                
                * (1) : PRINT INFORMATION
                * (2) : GENERATE AND ADD LETTER
                * (3) : GENERATE AND ADD PACKAGE
                * (4) : SENDING DAY
                * (5) : ADD WORKERS TO OFFICE
                * (6) : PRINT WORKERS
                * (7) : EXIT""");
        return true;
    }

    public static boolean Menu() throws EBuildingNameIsNULL {
        String plank = "\n-------------------------------------------------------------------------\t";
        Scanner sc = new Scanner(System.in);
        PostOffice postOffice = PostOfficeGenerator.getPostOffice();
        int totalCost = 0;
        int choice = 0;
        do {
            printMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    logger.info(plank + "\n" +
                            postOffice + plank + "\n                                              * YOUR TOTAL BILL :  " +
                            totalCost + "$\t");
                }
                case 2 -> {
                    Letter l = DeliverableGenerator.getLetter();
                    totalCost += postOffice.send(l);
                    logger.info("\nGenerated Letter: " + l);
                }
                case 3 -> {
                    Package p = DeliverableGenerator.getPackage();
                    totalCost += postOffice.send(p);
                    logger.info("\nGenerated Package: " + p);
                }
                case 4 -> {
                    boolean success = postOffice.sendingDay();
                    if (success) {
                        logger.info("\n   *** Sending day went successfully ***");
                    } else {
                        logger.info("\n  *** Nothing was sent today ***");
                    }
                }
                case 5 -> {
                    postOffice.addWorker(PostOfficeGenerator.getDeliveryMan());
                    postOffice.addWorker(PostOfficeGenerator.getPostOfficeWorker());
                    postOffice.addWorker(PostOfficeGenerator.getDriver());
                    postOffice.addWorker(PostOfficeGenerator.getPilot());
                }
                case 6 -> {
                    logger.info("\n" + postOffice.getWorkers());
                }
                default -> choice = 7;
            }
        } while (choice != 7);
        logger.info(plank + "\n                                              * YOUR TOTAL BILL :  " +
                totalCost + "$\t");
        return totalCost != 0;
    }


}
