package com.solvd.mail.main;

import com.solvd.mail.buildings.CarDeliveryDepartment;
import com.solvd.mail.buildings.PlaneDeliveryDepartment;
import com.solvd.mail.buildings.PostOffice;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Letter l1 = DeliverableGenerator.getLetter();
        Package p1 = DeliverableGenerator.getPackage();
        int totalCost = 0;
        System.out.println(l1.toString() + p1);
        PostOffice postOffice = PostOfficeGenerator.getPostOffice();
        totalCost += postOffice.send(l1);
        totalCost += postOffice.send(p1);
        Scanner sc = new Scanner(System.in);
        /*System.out.println("ENTER AMOUNT OF UNITS OF DELIVERY ( | 2):");
        int index = sc.nextInt() / 2;
        for (int i = 0; i < index; i++) {
            Letter letter = DeliverableGenerator.getLetter();
            totalCost += postOffice.send(letter);
            Package pac = DeliverableGenerator.getPackage();
            totalCost += postOffice.send(pac);
        }*/

        System.out.println("\n-------------------------------------------------------------------------\n");
        System.out.println("Is Letter 1 sent: " + postOffice.checkDelivery(l1));
        System.out.println("Is Package 1 sent: " + postOffice.checkDelivery(p1));
        System.out.println("\n-------------------------------------------------------------------------\n");
        System.out.println(postOffice);
        postOffice.sendingDay();
        System.out.println("\n-------------------------------------------------------------------------\n");
        System.out.println(postOffice);
        System.out.println();
        System.out.println("\n-------------------------------------------------------------------------\n");
        System.out.println("Is Letter 1 sent: " + postOffice.checkDelivery(l1));
        System.out.println("Is Package 1 sent: " + postOffice.checkDelivery(p1));
        System.out.println("\n-------------------------------------------------------------------------\t");
        System.out.println("                                              * YOUR TOTAL BILL :  " + totalCost + "$\t");

        int choice = 0;
        do {
            printMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n-------------------------------------------------------------------------\t");
                    System.out.println(postOffice);
                    System.out.println("\n-------------------------------------------------------------------------\t");
                    System.out.println("                                              * YOUR TOTAL BILL :  " + totalCost + "$\t");
                    break;
                case 2:
                    Letter l = DeliverableGenerator.getLetter();
                    totalCost += postOffice.send(l);
                    System.out.println("Generated Letter: " + l);
                    break;
                case 3:
                    Package p = DeliverableGenerator.getPackage();
                    totalCost += postOffice.send(p);
                    System.out.println("Generated Package: " + p);
                    break;
                default:
                    choice = 4;
            }
        } while (choice != 4);
        System.out.println("\n-------------------------------------------------------------------------\t");
        System.out.println("                                              * YOUR TOTAL BILL :  " + totalCost + "$\t");
    }

    public static void printMenu() {
        System.out.println("* (1) : PRINT INFORMATION\n* (2) : GENERATE AND ADD LETTER\n" +
                "* (3) : GENERATE AND ADD PACKAGE\n* (4) : EXIT");
    }


}
