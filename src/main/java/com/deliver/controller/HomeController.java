package com.deliver.controller;

/**
 * Created by deadoggy on 17-4-17.
 */

import com.deliver.dao.PackageRepository;
import com.deliver.model.Package;
import com.deliver.service.CAPTCHAService;
import com.deliver.service.ShortMesgService;
import org.hibernate.validator.constraints.pl.REGON;
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
    public String homePage(){
        return "manage_cup";
    }

    @RequestMapping({"/cupboard"})
    public String manageCupboardPage(){
        return "manage_cup";
    }

    @RequestMapping({"/taken_record"})
    public String takenRecordPage(){
        return "taken_record";
    }

    @RequestMapping({"/send_package"})
    public String sendPackagePage(){
        return "send_package";
    }

    @RequestMapping({"/store_package"})
    public String storePackagePage(){
        return "store_package";
    }

    @RequestMapping({"/shelf_cup_transfer"})
    public String shelfCupTransferPage(){
        return "shelf_cup_transfer";
    }

    @RequestMapping({"/generate_form"})
    public String generateFormPage(){
        return "generate_form";
    }

    @RequestMapping({"/account_manage"})
    public String accountManagePage(){
        return "account_manage";
    }

    @RequestMapping({"/search_package"})
    public String searchPackagePage(){
        return "search_package";
    }

    @RequestMapping({"/taken_out_time"})
    public String takenOutTimePage(){
        return "taken_out_time";
    }

    @RequestMapping({"/advanced_manage"})
    public String advancedManagePage(){
        return "advanced_manage";
    }

    @RequestMapping({"/down"})
    public String down(){
        return "test";
    }

}
