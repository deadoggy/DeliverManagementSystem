package com.deliver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;

/**
 * Created by deadoggy on 17-5-31.
 */

@RestController
public class FormController {

    @Autowired
    FormService formService;


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String getFormData(HttpServletRequest request){
        try{
            StringBuffer strBuf = new StringBuffer();
            String line;
            BufferedReader jsonReader = request.getReader();

            while((line = jsonReader.readLine()) != null){
                strBuf.append(line);
            }

            JSONObject json = JSON.parseObject(strBuf.toString());
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

            Calendar beg = Calendar.getInstance();
            beg.setTime(format.parse(json.getString("beg")));

            Calendar end = Calendar.getInstance();
            end.setTime(format.parse(json.getString("end")));

            String company = json.getString("company");

            return this.formService.dispatcher(company, beg, end, json.getString("opt"));


        }catch(Exception e){
            return "{\"result\": \"failure\"}";
        }
    }


}
