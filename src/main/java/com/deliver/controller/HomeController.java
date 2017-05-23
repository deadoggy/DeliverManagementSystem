package com.deliver.controller;

/**
 * Created by deadoggy on 17-4-17.
 */

import com.deliver.dao.PackageRepository;
import com.deliver.model.Package;
import com.deliver.service.CAPTCHAService;
import com.deliver.service.ShortMesgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class HomeController {



    public HomeController() {
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping({"/home"})
    public String showHomePage(){
        return "manage_cup";
    }


}
