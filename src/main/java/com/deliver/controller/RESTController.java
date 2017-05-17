package com.deliver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by deadoggy on 17-5-17.
 */

@RestController
public class RESTController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){        return "test_rest";
    }

}
