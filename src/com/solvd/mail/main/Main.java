package com.solvd.mail.main;

import com.solvd.mail.buildings.PostOffice;
import com.solvd.mail.exceptions.EBuildingNameIsNull;
import com.solvd.mail.myLinkedList.MyLinkedList;
import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {

    static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws EBuildingNameIsNull {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addElement("HELLO");
        list.addElement("HOW ARE YOU");
        list.addElement(null);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.get(2));
        Object[] mas = list.asArray();
        System.out.println(list.remove(null));
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
        //Menu();
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

    public static boolean Menu() throws EBuildingNameIsNull {
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
