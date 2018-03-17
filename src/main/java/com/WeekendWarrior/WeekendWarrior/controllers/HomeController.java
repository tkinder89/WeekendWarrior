package com.WeekendWarrior.WeekendWarrior.controllers;

import com.WeekendWarrior.WeekendWarrior.UserService;
import com.WeekendWarrior.WeekendWarrior.models.User;
import com.WeekendWarrior.WeekendWarrior.models.Wrestler;
import com.WeekendWarrior.WeekendWarrior.models.data.BookingDAO;
import com.WeekendWarrior.WeekendWarrior.models.data.RoleRepository;
import com.WeekendWarrior.WeekendWarrior.models.data.WrestlerDAO;
import com.WeekendWarrior.WeekendWarrior.models.form.AddWrestler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private WrestlerDAO wrestlerDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping(value = "")
    public String index(Model model){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());

            model.addAttribute("user", user);
            model.addAttribute("title", "Wrestlers");
            model.addAttribute("wrestlers", wrestlerDAO.findAll());

            return "index";



    }

}
