package com.justdoit.controller;


import com.justdoit.POJOs.User;
import com.justdoit.service.HouseService;
import com.justdoit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model){
        log.debug("login");
        return "login";
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user); //we do model.addAttribute(user) here to pass the
        // user attributes which are then displayed on the registration view
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, int houseId, BindingResult bindingResult, Model model) {
        //TODO do something with houseId here

        User userExists = userService.listUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Mmmmm.. There is already a user registered with this email address.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("failureMessage", "Sorry " + user.getFirstName() + ", some " +
                    "fields are incorrect; can you try again?");
            return "registration";

        } else {
            userService.saveUser(user, houseId);
            model.addAttribute("successMessage", user.getFirstName() + ", you have been " +
                    "successfully registered as a member of " + houseService.listByHouseId(houseId) + ".");
            model.addAttribute("user", new User());
            return "registration";
        }

    }
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String index(Authentication auth, Model model){
        // get id of currently authenticated user
//        int user_id = userService.listUserByEmail(auth.getName()).getUserId();
//        model.addAttribute("user_id", user_id);
        log.trace("Attempting to access home page");
        return "home";
    }

//    @RequestMapping(value="/403")
//    public String Error403(){
//        return "403";
//    }
}