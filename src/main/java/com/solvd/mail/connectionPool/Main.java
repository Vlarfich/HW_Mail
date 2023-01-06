package com.solvd.mail.connectionPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {

    public void run() {
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
        System.out.println(Thread.currentThread().getName() + " (Start) " + formater.format(new Date()));
        processmessage();
        System.out.println(Thread.currentThread().getName() + " (End) " + formater.format(new Date()));
    }

    private void processmessage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread();
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Finished *");
    }
}