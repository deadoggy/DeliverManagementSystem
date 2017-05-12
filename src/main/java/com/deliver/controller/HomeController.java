package com.deliver.controller;

/**
 * Created by deadoggy on 17-4-17.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    public HomeController() {
    }

    @RequestMapping({"/home"})
    public String showHomePage(ModelMap model){
        return "test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "loginPage";
    }
}
