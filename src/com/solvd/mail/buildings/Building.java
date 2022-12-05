package com.solvd.mail.buildings;

import com.solvd.mail.parcel.Letter;
import com.solvd.mail.parcel.Package;

import java.util.ArrayList;

public abstract class Building {
    private int id;
    private String name;
    private int floor;
    private double squareFeet;
    private ArrayList<Package> packages = new ArrayList<>();
    private ArrayList<Letter> letters = new ArrayList<>();

    public Building(String name, int floor, double squareFeet) {
        this.name = name;
        this.floor = Math.abs(floor);
        this.squareFeet = Math.abs(squareFeet);
    }

    public Building(String name) {
        this.name = name;
        this.floor = 0;
        this.squareFeet = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null)
            this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = Math.abs(floor);
    }

    public double getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(double squareFeet) {
        this.squareFeet = Math.abs(squareFeet);
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

    public ArrayList<Letter> getLetters() {
        return letters;
    }

    public void setLetters(ArrayList<Letter> letters) {
        this.letters = letters;
    }

    public void addPackage(Package pac) {
        packages.add(pac);
    }

    public void addLetter(Letter letter) {
        letters.add(letter);
    }

    public boolean sendAll() {
        for (Letter i : letters) {
            i.setDelivered(true);
        }
        for (Package i : packages) {
            i.setDelivered(true);
        }
        return true;
    }

    abstract public boolean sendingDay(ArrayList<Letter> l, ArrayList<Package> p);

}
