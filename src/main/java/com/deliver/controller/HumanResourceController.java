package com.deliver.controller;

import com.deliver.model.Employee;
import com.deliver.service.HumanManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by deadoggy on 17-5-31.
 */

@RestController
public class HumanResourceController {


    @Autowired
    private HumanManageService humanManageService;

    @RequestMapping(value = "/findEmById/{id}", method = RequestMethod.GET)
    public String findEmById(@PathVariable String id){
        return humanManageService.findByEmployeeId(id);
    }

    @RequestMapping(value = "findEmByName/{name}", method = RequestMethod.GET)
    public String findEmByName(@PathVariable String name){
        return humanManageService.findByEmployName(name);
    }




}
