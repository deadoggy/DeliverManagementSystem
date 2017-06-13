package com.deliver.controller;

/**
 * Created by deadoggy on 17-4-17.
 */

import com.deliver.constant.Constant;
import com.deliver.dao.DeliverCompanyRepository;
import com.deliver.dao.EmployeeRepository;
import com.deliver.dao.PackageRepository;
import com.deliver.model.DeliverCompany;
import com.deliver.model.Employee;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Controller
public class HomeController {

    @Autowired
    EmployeeRepository employeeRepository;

    public HomeController() {
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    @RequestMapping({"/home", "/"})
    public String homePage(HttpServletRequest request, HttpServletResponse response){

        Employee em = employeeRepository.findByMEmployeeId(/*request.getRemoteUser()*/"1452716");

        String idStr, nameStr, posStr;
        if(null == em){
            idStr = "null";
            nameStr = "name";
            posStr = "null";
        }else {
            idStr = em.getmEmployeeId();
            nameStr = em.getmName();
            posStr = em.getmPosition().size() == 0? "" : em.getmPosition().get(0).getmName();
        }

        Cookie id = new Cookie("id", idStr);
        Cookie name = new Cookie("name", nameStr);


        Cookie pos = new Cookie("pos", posStr);

        response.addCookie(id);
        response.addCookie(name);
        response.addCookie(pos);


        return "manage_cup";
    }

    @RequestMapping({"/cupboard"})
    public String manageCupboardPage(HttpServletRequest request, HttpServletResponse response){
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

    @RequestMapping({"/send_record"})
    public String sendRecordPage(){
        return "send_record";
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

    @RequestMapping({"/contraband_inquiry"})
    public String contrabandInquiryPage() { return "contraband_inquiry"; }

}
