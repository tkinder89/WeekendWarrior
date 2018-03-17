package com.WeekendWarrior.WeekendWarrior.models.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class AddWrestler {

    @NotEmpty(message = "*You need to enter your wrestling name.")
    private String name;

    private int feet;

    private int inches;

    private int weight;

    private String city;

    private String state;

    public AddWrestler(){}

    public AddWrestler(String name, int feet, int inches, int weight, String city, String state){
        setName(name);
        setFeet(feet);
        setInches(inches);
        setWeight(weight);
        setCity(city);
        setState(state);
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

    public int getInches() {return inches;}

    public void setInches(int inches) {
        this.inches = inches;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {this.weight = weight;}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}



}

