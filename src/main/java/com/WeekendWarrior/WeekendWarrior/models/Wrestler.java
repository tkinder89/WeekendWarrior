package com.WeekendWarrior.WeekendWarrior.models;

import com.WeekendWarrior.WeekendWarrior.models.form.AddUser;
import com.WeekendWarrior.WeekendWarrior.models.form.AddWrestler;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="WRESTLER")
public class Wrestler {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "wrestler_Id")
    private int id;

    @OneToMany
    @JoinColumn(foreignKey=@ForeignKey(name="FK_BOOKING_WRESTLER"))
    private List<Booking> bookings = new ArrayList<>();

    @NotEmpty(message = "You need to enter your wrestling name.")
    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "feet")
    private int feet = 0;

    @Column(name = "inches")
    private int inches = 0;

    @Column(name = "weight")
    private int weight;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "totalHeight")
    private  String totalHeight;

    @OneToOne
    private User user;


    // Constructors
    public Wrestler(){}

    public Wrestler(AddWrestler addWrestler){
        setName(addWrestler.getName());
        setFeet(addWrestler.getFeet());
        setInches(addWrestler.getInches());
        setWeight(addWrestler.getWeight());
        setCity(addWrestler.getCity());
        setState(addWrestler.getState());
    }
    public Wrestler(AddUser addUser){
        setName(addUser.getName());
        setFeet(addUser.getFeet());
        setInches(addUser.getInches());
        setWeight(addUser.getWeight());
        setCity(addUser.getCity());
        setState(addUser.getState());
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public int getFeet() {return feet;}

    public void setFeet(int feet) {
        this.feet = feet;
        setTotalHeight(feet +"' " + getInches() + '"');
    }

    public int getInches() {return inches;}

    public void setInches(int inches) {
        this.inches = inches;
        setTotalHeight(getFeet() +"' " + inches + '"');
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

    public String getTotalHeight() {
        return totalHeight;
    }

    private void setTotalHeight(String totalHeight) {
        this.totalHeight = totalHeight;
    }

    public void AddBooking(Booking booking){
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
