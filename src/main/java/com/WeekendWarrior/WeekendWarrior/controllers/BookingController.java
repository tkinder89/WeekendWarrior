package com.WeekendWarrior.WeekendWarrior.controllers;

import com.WeekendWarrior.WeekendWarrior.UserService;
import com.WeekendWarrior.WeekendWarrior.models.Booking;
import com.WeekendWarrior.WeekendWarrior.models.User;
import com.WeekendWarrior.WeekendWarrior.models.Wrestler;
import com.WeekendWarrior.WeekendWarrior.models.data.BookingDAO;
import com.WeekendWarrior.WeekendWarrior.models.data.RoleRepository;
import com.WeekendWarrior.WeekendWarrior.models.data.WrestlerDAO;
import com.WeekendWarrior.WeekendWarrior.models.form.AddBooking;
import com.WeekendWarrior.WeekendWarrior.models.form.AddWrestler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.net.URL;

@Controller
@RequestMapping(value = "booking")
public class BookingController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    WrestlerDAO wrestlerDAO;

    @Autowired
    BookingDAO bookingDAO;

    @RequestMapping(value = "add/{wrestler_Id}", method = RequestMethod.GET)
    public String addBookingForm(Model model,
                                 @ModelAttribute
                                 @PathVariable int wrestler_Id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("user", user);

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());


        Wrestler booked = wrestlerDAO.findOne(wrestler_Id);

        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("title", "Add Booking");
            return "booking/add";
        }

        Booking booking = new Booking(addBooking);

        booking.setWrestler(wrestlerDAO.findOne(wrestler_Id));

        bookingDAO.save(booking);
        booked.AddBooking(booking);
        wrestlerDAO.save(booked);

        return "redirect:/wrestler/view/{wrestler_Id}";
    }

    @RequestMapping(path = "remove/{wrestler_Id}/{booking_id}", method = RequestMethod.GET)
    public String removeBooking(@ModelAttribute
                                @PathVariable int wrestler_Id,
                                @ModelAttribute
                                @PathVariable int booking_id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if(user.getId() == wrestler_Id) {
            Wrestler wrestler = wrestlerDAO.findOne(wrestler_Id);
            bookingDAO.delete(booking_id);

            return "redirect:/wrestler/view/"+ wrestler.getId();
        }else{
            return "redirect:/";
        }
    }

    @RequestMapping(path = "view", method = RequestMethod.GET)
    public String viewBooking(Model model){
        String googleMap = "https://maps.googleapis.com/maps/api/js?key=AIzaSyBy96z7gN-tNEqMcUxRRaoboY-6E6abmFI&callback=initMap";
        model.addAttribute(googleMap, "googleMapAPI");

        return "booking/view";
    }


}
