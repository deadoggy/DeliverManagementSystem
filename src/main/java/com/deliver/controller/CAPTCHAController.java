package com.deliver.controller;

import com.deliver.service.CAPTCHAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by deadoggy on 17-5-17.
 */

@RestController
public class CAPTCHAController {
    @Autowired
    CAPTCHAService captchaService;



    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public void code(HttpServletResponse response, HttpServletRequest request){
        this.captchaService.getRandcode(request,response);
    }

    @RequestMapping(value = "/checkCode", method = RequestMethod.GET)
    public String checkCode(HttpServletResponse response, HttpServletRequest request){
        String val = request.getParameter("code");

        if(this.captchaService.check(request, val)){
            return "true";
        }else{
            return "false";
        }

    }
}
