package com.WeekendWarrior.WeekendWarrior.models.form;

import org.hibernate.validator.constraints.NotEmpty;

public class AddWrestler {

    @NotEmpty(message = "You need to enter your wrestling name.")
    private String name;

    private int feet;

    private int inches;

    private int weight;

    private String hometown;

    public AddWrestler(){}

    public AddWrestler(String name, int feet, int inches, int weight, String hometown){
        setName(name);
        setFeet(feet);
        setInches(inches);
        setWeight(weight);
        setHometown(hometown);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFeet() {
        return feet;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }

    public int getInches() {
        return inches;
    }

    public void setInches(int inches) {
        this.inches = inches;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }





}

