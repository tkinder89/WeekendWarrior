package com.WeekendWarrior.WeekendWarrior.controllers;

import com.WeekendWarrior.WeekendWarrior.UserService;
import com.WeekendWarrior.WeekendWarrior.models.User;
import com.WeekendWarrior.WeekendWarrior.models.Wrestler;
import com.WeekendWarrior.WeekendWarrior.models.data.WrestlerDAO;
import com.WeekendWarrior.WeekendWarrior.models.form.AddUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private WrestlerDAO wrestlerDAO;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        AddUser addUser = new AddUser();
        modelAndView.addObject("addUser", addUser);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid AddUser addUser,
                                      //User user,
                                      BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(addUser.getEmail());

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            User user = new User(addUser);

            userService.saveUser(user);
            wrestlerDAO.save(new Wrestler(addUser));
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("addUser", new AddUser());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(value="/user/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("userMessage","Content Available Only for Users");
        modelAndView.setViewName("user/home");
        return modelAndView;
    }


}