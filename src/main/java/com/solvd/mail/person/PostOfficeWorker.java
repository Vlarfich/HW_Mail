package com.solvd.mail.person;

import java.util.Objects;

public class PostOfficeWorker extends com.solvd.mail.person.Person {
    private String position;
    private static int current_id = 1;
    private final int id = current_id++;

    public PostOfficeWorker(String name, int age, int experience, int yearsOfWork, String position) {
        super(name, age, experience, yearsOfWork);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("*%16s", getClass().getSimpleName()) + " [" +
                id + ": " +
                super.toString() + " " +
                position + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PostOfficeWorker that = (PostOfficeWorker) o;
        return id == that.id && position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, id);
    }
}
