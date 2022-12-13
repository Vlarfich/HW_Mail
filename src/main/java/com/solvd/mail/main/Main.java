package com.solvd.mail.main;

import com.solvd.mail.buildings.PostOffice;
import com.solvd.mail.exceptions.EBuildingNameIsNull;
import com.solvd.mail.myLinkedList.MyLinkedList;
import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

//
//                                                Iterable                                              Map
//                                                   |                                                   |
//                                                   ⌄                                               SortedMap
//                                               Collection
//                                                   |
//                          List                   Queue                   Set
//





public class Main {
    static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws EBuildingNameIsNull {
        System.out.println("HOW ARE YA");
        System.out.println(2+2);
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addElement("HELLO");
        list.addElement("HOW ARE YOU");
        list.addElement(null);
        list.add("HI", "YO", null, "WHAT'S UP");
        list.add("HEHEHEHE");
        logger.info(list);
        logger.info(list.size());
        logger.info(list.get(2));
        Object[] mas = list.asArray();
        logger.info(list.remove(null));
        logger.info(list);
        list.remove(0);
        list.remove(0);
        logger.info(list);
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
                * (7) : ADD 20 LETTERS & PACKAGES
                * (8) : EXIT""");
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
                    logger.info("Generated Letter: " + l);
                }
                case 3 -> {
                    Package p = DeliverableGenerator.getPackage();
                    totalCost += postOffice.send(p);
                    logger.info("Generated Package: " + p);
                }
                case 4 -> {
                    boolean success = postOffice.sendingDay();
                    if (success) {
                        logger.info("   *** Sending day went successfully ***");
                    } else {
                        logger.info("  *** Nothing was sent today ***");
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
                case 7 -> {
                    for (int i = 0; i < 20; i++) {
                        totalCost += postOffice.send(DeliverableGenerator.getLetter());
                        totalCost += postOffice.send(DeliverableGenerator.getPackage());
                    }
                    logger.info("Sent 20 Letters/Packages");
                }
                default -> choice = 8;
            }
        } while (choice != 8);
        logger.info(plank + "\n                                              * YOUR TOTAL BILL :  " +
                totalCost + "$\t");

        /*postOffice.getALL_LETTERS().forEach((k, v) -> {
            System.out.println(k + " Letter, " + v.isDelivered());
        });*/

        return totalCost != 0;
    }


}
