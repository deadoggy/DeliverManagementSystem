package com.Deliver.Controller;

/**
 * Created by deadoggy on 17-4-17.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    public HomeController() {
    }

    @RequestMapping({"/"})
    public String showHomePage(ModelMap model){
        return "test";
    }
}
