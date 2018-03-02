package com.WeekendWarrior.WeekendWarrior.models.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddBooking {

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

    @NotNull(message = "Enter the zip code of the booking.")
    private String zip_Code;

    public AddBooking(){}

    public AddBooking(String company, Date booking_Date, String street, String city, String state, String zip_Code){
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

    public String getZip_Code() {return zip_Code;}

    public void setZip_Code(String zip_Code) {this.zip_Code = zip_Code;}

}
