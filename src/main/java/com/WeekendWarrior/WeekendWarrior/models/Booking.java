package com.WeekendWarrior.WeekendWarrior.models;

import com.WeekendWarrior.WeekendWarrior.models.form.AddBooking;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Booking {

    @Id
    @GeneratedValue
    @Column(name = "booking_id")
    private int id;

    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="FK_WRESTLER_BOOKING"))
    private Wrestler wrestler;

    @NotEmpty(message = "Please enter the Company of the booking.")
    private String company;

    @NotNull(message = "Enter the Date of the booking.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date booking_Date;

    @NotEmpty(message = "Enter the Street of the booking address.")
    private String street;

    @NotEmpty(message = "Enter the City of the booking.")
    private String city;

    @NotEmpty(message = "Enter the State of the booking.")
    private String state;

    @NotNull(message = "Enter the Zip Code of the booking.")
    private String zip_Code;

    private String bookingLocation;

    // Constructors

    public Booking(){}

    public Booking(AddBooking addBooking){
        setCompany(addBooking.getCompany());
        setBooking_Date(addBooking.getBooking_Date());
        setStreet(addBooking.getStreet());
        setCity(addBooking.getCity());
        setState(addBooking.getState());
        setZip_Code(addBooking.getZip_Code());
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getBooking_Date() {
        return booking_Date;
    }

    public void setBooking_Date(Date booking_Date) {
        this.booking_Date = booking_Date;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        setBookingLocation(city + ", " + getState());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        setBookingLocation(getCity() + ", " + state);
    }

    public String getZip_Code() {
        return zip_Code;
    }

    public void setZip_Code(String zip_Code) {
        this.zip_Code = zip_Code;
    }

    public String getBookingLocation() {
        return bookingLocation;
    }
    private void setBookingLocation(String bookingLocation) {
        this.bookingLocation = bookingLocation;
    }

    public void setWrestler(Wrestler wrestler){
        this.wrestler = wrestler;
    }

    public Wrestler getWrestler() {
        return wrestler;
    }
}
