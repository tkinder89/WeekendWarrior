package com.WeekendWarrior.WeekendWarrior.models.form;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class AddBooking {

    @NotEmpty(message = "Please enter the Company of the booking.")
    private String company;

    @NotEmpty(message = "Enter the Date of the booking.")
    private Date booking_Date;

    @NotEmpty(message = "Enter the Street of the booking address.")
    private String street;

    @NotEmpty(message = "Enter the City of the booking.")
    private String city;

    @NotEmpty(message = "Enter the State of the booking.")
    private String state;

    @NotEmpty(message = "Enter the Zip Code of the booking.")
    private int zip_Code;

    public AddBooking(){}

    public AddBooking(String company, Date booking_Date, String street, String city, String state, int zip_Code){
        setCompany(company);
        setBooking_Date(booking_Date);
        setStreet(street);
        setCity(city);
        setState(state);
        setZip_Code(zip_Code);
    }

    public String getCompany() {return company;}

    public void setCompany(String company) {this.company = company;}

    public Date getBooking_Date() {return booking_Date;}

    public void setBooking_Date(Date booking_Date) {this.booking_Date = booking_Date;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}

    public int getZip_Code() {return zip_Code;}

    public void setZip_Code(int zip_Code) {this.zip_Code = zip_Code;}

}
