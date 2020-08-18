package com.justdoit.controller;


import com.justdoit.POJOs.DB.House;
import com.justdoit.POJOs.DB.User;
import com.justdoit.service.HouseService;
import com.justdoit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HouseService houseService;

    @RequestMapping(value={"/", "/login"})
    public String login(){
        log.debug("login");
        return "login";
    }

    @RequestMapping("/registration" )
    public String registration(Model model){
        // model.addAttribute(user) below passes User attributes which are then displayed on the Registration pg
        model.addAttribute("user", new User());
        model.addAttribute("house", new House());
        return "registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@Valid User user, BindingResult bindingResultUser,
                                @Valid House house, BindingResult bindingResultHouse,
                                Model model) {

        User userExists = userService.listUserByEmail(user.getEmail());
        House houseExists = houseService.listByHouseName(house.getHouseName());

        if (userExists != null) {
            bindingResultUser.rejectValue("email", "error.user",
                            "Mmmmm.. There is already a user registered with this email address.");
        }

        if (bindingResultUser.hasErrors() || bindingResultHouse.hasErrors()) {
            model.addAttribute("failureMessage", "Sorry " + user.getFirstName() + ", some " +
                    "fields are incorrect; can you try again?");
            return "registration";

        } else {
            if (houseExists == null) {
                houseService.saveHouse(house);
            }
            userService.saveUser(user, house);
            model.addAttribute("successMessage", user.getFirstName() + ", you have been " +
                    "successfully registered as a new member of " + house.getHouseName() + ".");
            // these reset the fields (make them blank) after a user is successfully registered
            model.addAttribute("user", new User());
            model.addAttribute("house", new House());
            return "registration";
        }

    }


    @RequestMapping("/home")
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