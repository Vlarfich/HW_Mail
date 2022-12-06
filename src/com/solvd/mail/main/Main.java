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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws EBuildingNameIsNULL {
        Menu();
    }

    public static boolean printMenu() {
        System.out.println("""
                * (1) : PRINT INFORMATION
                * (2) : GENERATE AND ADD LETTER
                * (3) : GENERATE AND ADD PACKAGE
                * (4) : SENDING DAY
                * (5) : ADD WORKERS TO OFFICE
                * (6) : EXIT""");
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
                    System.out.println(plank + "\n" +
                            postOffice + plank + "\n                                              * YOUR TOTAL BILL :  " +
                            totalCost + "$\t");
                }
                case 2 -> {
                    Letter l = DeliverableGenerator.getLetter();
                    totalCost += postOffice.send(l);
                    System.out.println("Generated Letter: " + l);
                }
                case 3 -> {
                    Package p = DeliverableGenerator.getPackage();
                    totalCost += postOffice.send(p);
                    System.out.println("Generated Package: " + p);
                }
                case 4 -> {
                    boolean success = postOffice.sendingDay();
                    if (success) {
                        System.out.println("   *** Sending day went successfully ***");
                    } else {
                        System.out.println(("  *** Nothing was sent today ***"));
                    }
                }
                case 5 -> {
                    postOffice.addWorker(PostOfficeGenerator.getDeliveryMan());
                    postOffice.addWorker(PostOfficeGenerator.getPostOfficeWorker());
                    postOffice.addWorker(PostOfficeGenerator.getDriver());
                    postOffice.addWorker(PostOfficeGenerator.getPilot());
                }
                default -> choice = 6;
            }
        } while (choice != 6);
        System.out.println(plank + "\n                                              * YOUR TOTAL BILL :  " +
                totalCost + "$\t");
        return totalCost != 0;
    }


}
