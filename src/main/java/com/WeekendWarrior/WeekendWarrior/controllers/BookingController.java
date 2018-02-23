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

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addBookingForm(Model model){
        AddBooking addBooking = new AddBooking();
        model.addAttribute("title", "Add Booking");
        model.addAttribute("addBooking", addBooking);
        return "booking/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddWrestlerForm(@ModelAttribute
                                         @Valid AddBooking addBooking,
                                         Errors errors,
                                         Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Booking");
            return "booking/add";
        }

        //System.out.println("*********************************** Should save booking here");
        bookingDAO.save(new Booking(addBooking));

        return "redirect:/";
    }

}
