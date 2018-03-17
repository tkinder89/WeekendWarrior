package com.WeekendWarrior.WeekendWarrior.models.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class AddUser {

    @NotEmpty(message = "*You need to enter your E-Mail address.")
    private String email;

    @NotEmpty(message = "*You need to enter your password.")
    private String password;

    private String firstName;

    private String lastName;

    @NotEmpty(message = "*You need to enter your wrestling name.")
    private String name;

    private int feet;

    private int inches;

    private int weight;

    @NotEmpty(message = "*You need to enter your hometown.")
    private String city;

    @NotEmpty(message = "*You need to enter your home state.")
    private String state;

    //Constructors

    public AddUser(){}

    public AddUser(String name, int feet, int inches, int weight, String city, String state){

        setName(name);
        setFeet(feet);
        setInches(inches);
        setWeight(weight);
        setCity(city);
        setState(state);
    }

    //Getters and Setters

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

    public void setWeight(int weight){
        if(Integer.toString(weight) == ""){
            this.weight = 0;
        }else{
            this.weight = weight;}
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
