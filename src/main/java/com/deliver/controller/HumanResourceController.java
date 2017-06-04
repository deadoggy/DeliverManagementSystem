package com.deliver.controller;

import com.alibaba.fastjson.JSONObject;
import com.deliver.constant.Constant;
import com.deliver.dao.EmployeeRepository;
import com.deliver.model.Employee;
import com.deliver.service.FTPService;
import com.deliver.service.HumanManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * Created by deadoggy on 17-5-31.
 */

@RestController
public class HumanResourceController {


    @Autowired
    private HumanManageService humanManageService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/findEmById/{id}", method = RequestMethod.GET)
    public String findEmById(@PathVariable String id){
        return humanManageService.findByEmployeeId(id);
    }

    @RequestMapping(value = "/findEmByName/{name}", method = RequestMethod.GET)
    public String findEmByName(@PathVariable String name){
        return humanManageService.findByEmployName(name);
    }

    @RequestMapping(value = "/save_em", method = RequestMethod.POST)
    public String saveEmployee(HttpServletRequest request){
        try{
            StringBuilder strBuilder = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while((line = reader.readLine()) != null){
                strBuilder.append(line);
            }


            JSONObject obj = JSONObject.parseObject(strBuilder.toString());

            String id = Long.toString(this.employeeRepository.count() + 1);

            String name = obj.getString("name");
            boolean gender;
            if(0 == obj.getString("gender").compareTo("male")){
                gender = Constant.MALE;
            }else{
                gender = Constant.FEMALE;
            }
            Integer age = obj.getInteger("age");
            String tele = obj.getString("tele");
            Float salary = obj.getFloat("salary");
            String pw = obj.getString("passwd");

            this.humanManageService
                    .addNewEmployee(id, name, age, gender,salary, tele, "0", pw);

            return "{\"result\": \"success\", \"id\" : \""+ id +"\"}";

        }catch(Exception e){
            return "{\"result\":\"failure\"}";
        }
    }

    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    public String uploadImg(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file){
        try{
            FTPService ftp = FTPService.getInstantce();


//            String fileName = file.getOriginalFilename();
//            String suffix = fileName.substring(fileName.lastIndexOf("."));
            ftp.upload(file.getInputStream(), /*request.getRemoteUser()*/"133333");
            return "{\"result\":\"success\"}";
        }catch(Exception e){
            e.printStackTrace();
            return "{\"result\":\"failure\"}";
        }

    }

}
