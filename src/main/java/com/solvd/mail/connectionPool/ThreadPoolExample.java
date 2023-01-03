package com.solvd.mail.connectionPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.text.SimpleDateFormat;


class Tasks implements Runnable {
    private String taskName;

    private int amountOfFinishes = 0;
    private boolean goodToGo_First = true;
    private boolean goodToGo_Second = true;


    public Tasks(String str) {
        taskName = str;
    }

    public void run() {
        try {
            while (!goodToGo_First) {
                Thread.sleep(200);
            }
            goodToGo_First = !goodToGo_Second;

            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            System.out.println("Initialization time: " + taskName + " = " + sdf.format(dt) + "  " + amountOfFinishes);

            while (!goodToGo_Second) {
                Thread.sleep(200);
            }
            goodToGo_Second = !goodToGo_First;

            Thread.sleep(1000);
            System.out.println(taskName + " is complete.");
            amountOfFinishes++;
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}

public class ThreadPoolExample {
    static final int MAX_TH = 5;

    public static void main(String[] args) {
        Runnable rb1 = new Tasks("task 1");
        Runnable rb2 = new Tasks("task 2");
        Runnable rb3 = new Tasks("task 3");
        Runnable rb4 = new Tasks("task 4");
        Runnable rb5 = new Tasks("task 5");
        Runnable rb6 = new Tasks("task 6");
        Runnable rb7 = new Tasks("task 7");

        ExecutorService pl = Executors.newFixedThreadPool(MAX_TH);

        for (int i = 0; i < 8; i++) {
            pl.execute(rb1);
            pl.execute(rb2);
            pl.execute(rb3);
            pl.execute(rb4);
            pl.execute(rb5);
            pl.execute(rb6);
            pl.execute(rb7);
        }
    }
}