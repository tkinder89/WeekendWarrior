package com.WeekendWarrior.WeekendWarrior.controllers;

import com.WeekendWarrior.WeekendWarrior.models.Booking;
import com.WeekendWarrior.WeekendWarrior.models.Wrestler;
import com.WeekendWarrior.WeekendWarrior.models.data.BookingDAO;
import com.WeekendWarrior.WeekendWarrior.models.data.WrestlerDAO;
import com.WeekendWarrior.WeekendWarrior.models.form.AddBooking;
import com.WeekendWarrior.WeekendWarrior.models.form.AddWrestler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "booking")
public class BookingController {

    @Autowired
    WrestlerDAO wrestlerDAO;

    @Autowired
    BookingDAO bookingDAO;

    @RequestMapping(value = "add/{wrestler_Id}", method = RequestMethod.GET)
    public String addBookingForm(Model model,
                                 @ModelAttribute
                                 @PathVariable int wrestler_Id){
        Wrestler booked = wrestlerDAO.findOne(wrestler_Id);
        AddBooking addBooking = new AddBooking();
        model.addAttribute("wrestler", booked);
        model.addAttribute("title", "Add Booking");
        model.addAttribute("addBooking", addBooking);
        return "booking/add";
    }

    @RequestMapping(value = "add/{wrestler_Id}", method = RequestMethod.POST)
    public String processAddBookingForm(@ModelAttribute
                                        @Valid AddBooking addBooking,
                                        Errors errors,
                                        @PathVariable int wrestler_Id,
                                        Model model){
        Wrestler booked = wrestlerDAO.findOne(wrestler_Id);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Booking");
            return "booking/add";
        }

        Booking booking = new Booking(addBooking);

        booking.setWrestler(wrestlerDAO.findOne(wrestler_Id));

        bookingDAO.save(booking);
        booked.AddBooking(booking);
        wrestlerDAO.save(booked);

        return "redirect:/wrestler/${wrestler_Id}";
    }

}
