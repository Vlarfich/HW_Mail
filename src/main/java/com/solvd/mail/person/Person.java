package com.solvd.mail.person;

import java.util.Objects;

public abstract class Person {
    private String name;
    private int age;
    private int experience;
    private int yearsOfWork;

    public Person(String name, int age, int experience, int yearsOfWork) {
        this.name = name;
        this.age = Math.abs(age);
        this.experience = Math.abs(experience);
        this.yearsOfWork = Math.abs(yearsOfWork);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null)
            this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = Math.abs(age);
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = Math.abs(experience);
    }

    public int getYearsOfWork() {
        return yearsOfWork;
    }

    public void setYearsOfWork(int yearsOfWork) {
        this.yearsOfWork = Math.abs(yearsOfWork);
    }

    @Override
    public String toString() {
        return name + ", Age:" + age + ", Experience-yow: " + experience + "-" + yearsOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && experience == person.experience && yearsOfWork == person.yearsOfWork && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, experience, yearsOfWork);
    }
}
