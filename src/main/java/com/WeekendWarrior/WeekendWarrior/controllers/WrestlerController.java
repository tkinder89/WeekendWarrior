package com.WeekendWarrior.WeekendWarrior.controllers;

import com.WeekendWarrior.WeekendWarrior.UserService;
import com.WeekendWarrior.WeekendWarrior.models.Booking;
import com.WeekendWarrior.WeekendWarrior.models.User;
import com.WeekendWarrior.WeekendWarrior.models.Wrestler;
import com.WeekendWarrior.WeekendWarrior.models.data.BookingDAO;
import com.WeekendWarrior.WeekendWarrior.models.data.RoleRepository;
import com.WeekendWarrior.WeekendWarrior.models.data.WrestlerDAO;
import com.WeekendWarrior.WeekendWarrior.models.form.AddWrestler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("wrestler")
public class WrestlerController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WrestlerDAO wrestlerDAO;

    @Autowired
    private BookingDAO bookingDAO;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddWrestlerForm(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        AddWrestler addWrestler = new AddWrestler();
        model.addAttribute("user", user);
        model.addAttribute("title", "Add Wrestler");
        model.addAttribute("addWrestler", addWrestler);
        return "wrestler/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddWrestlerForm(@ModelAttribute
                                             @Valid AddWrestler addWrestler,
                                         Errors errors,
                                         Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("title", "Add Wrestler");
            return "wrestler/add";
        }

        //System.out.println("*********************************** Should save wrestler here");
        wrestlerDAO.save(new Wrestler(addWrestler));

        return "redirect:/";
    }

    @RequestMapping(value="edit/{wrestler_Id}", method = RequestMethod.GET)
    public String editWrestlerInfo(@ModelAttribute
                                   @PathVariable int wrestler_Id,
                                   Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

            if(user.getId() == wrestler_Id) {
                Wrestler editWrestler = wrestlerDAO.findOne(wrestler_Id);
                AddWrestler wrestlerToEdit = new AddWrestler(
                        editWrestler.getName(),
                        editWrestler.getFeet(),
                        editWrestler.getInches(),
                        editWrestler.getWeight(),
                        editWrestler.getCity(),
                        editWrestler.getState()
                );
                model.addAttribute("user", user);
                model.addAttribute("title", "Edit Profile");
                model.addAttribute("addWrestler", wrestlerToEdit);
                model.addAttribute("edit", true);
                return "wrestler/add";
            }else{
                return "redirect:/wrestler/view/{wrestler_Id}";
            }

    }

    @RequestMapping(value="edit/{wrestler_Id}", method = RequestMethod.POST)
    public String processEditWrestlerForm(@ModelAttribute
                                              @Valid AddWrestler editWrestler,
                                          @PathVariable int wrestler_Id,
                                          Errors errors,
                                          Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("title", "Edit Profile");
            model.addAttribute("edit", true);
            return "wrestler/add";
        }

        Wrestler updateWrestler = wrestlerDAO.findOne(wrestler_Id);
        updateWrestler.setName(editWrestler.getName());
        updateWrestler.setFeet(editWrestler.getFeet());
        updateWrestler.setInches(editWrestler.getInches());
        updateWrestler.setWeight(editWrestler.getWeight());
        updateWrestler.setCity(editWrestler.getCity());
        updateWrestler.setState(editWrestler.getState());

        wrestlerDAO.save(updateWrestler);
        return "redirect:/";
    }

    @RequestMapping(path = "view/{wrestler_Id}", method = RequestMethod.GET)
    public String viewProfile(@ModelAttribute
                              @PathVariable int wrestler_Id,
                              Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Wrestler viewWrestler = wrestlerDAO.findOne(wrestler_Id);
        model.addAttribute("user", user);
        model.addAttribute("wrestler", viewWrestler);
        model.addAttribute("title", viewWrestler.getName());

        if (user != null && user.getId()  == wrestler_Id){
            model.addAttribute("editBooking", wrestler_Id);
        }

        return "wrestler/view";
    }

}
