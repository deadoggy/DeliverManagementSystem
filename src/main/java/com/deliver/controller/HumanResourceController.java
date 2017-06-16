package com.deliver.controller;

import com.alibaba.fastjson.JSON;
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
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Date;

/**
 * Created by deadoggy on 17-5-31.
 */

@RestController
public class HumanResourceController {


    @Autowired
    private HumanManageService humanManageService;

    @Autowired
    private EmployeeRepository employeeRepository;


    private String Hash(String name){
        String salt = new Date().toString();
        MessageDigest md;
        String numt = "1234567890";
        try{
            md = MessageDigest.getInstance("MD5");
            String context = name + salt;
            md.update(context.getBytes());
            byte digest[] = md.digest();
            StringBuilder retStr = new StringBuilder();

            for(int i=0; i<4; i+=2){
                int index = digest[i] < 0 ? digest[i] + 128 : digest[i];
                retStr.append(numt.charAt(index%10));
            }
            return retStr.toString();

        }catch(Exception e){
            return  "invalid";
        }

    }

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
            String pos = obj.getString("pos");

            StringBuilder id = new StringBuilder();

            if(null != pos && pos.compareTo("manager")==0){
                id.append("001");
            }else{
                id.append("002");
            }

            String name = obj.getString("name");
            id.append(Hash(name));
            boolean gender;
            if(0 == obj.getString("gender").compareTo("male")){
                id.append("01");
                gender = Constant.MALE;
            }else{
                id.append("02");
                gender = Constant.FEMALE;
            }
            Integer age = obj.getInteger("age");
            String tele = obj.getString("tele");
            Float salary = obj.getFloat("salary");
            String pw = obj.getString("passwd");


            this.humanManageService

                    .addNewEmployee(id.toString(), name, age, gender,salary, tele, "0", pw);

            return "{\"result\": \"success\", \"id\" : \""+ id +"\"}";

        }catch(Exception e){
            return "{\"result\":\"failure\"}";
        }
    }

    @RequestMapping(value = "/check_id", method = RequestMethod.GET)
    public String checkId(HttpServletRequest request){
        return request.getRemoteUser();
    }

    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    public String uploadImg(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file){
        try{
            FTPService ftp = FTPService.getInstantce();


//            String fileName = file.getOriginalFilename();
//            String suffix = fileName.substring(fileName.lastIndexOf("."));
            ftp.upload(file.getInputStream(), request.getRemoteUser());
            return "{\"result\":\"success\"}";
        }catch(Exception e){
            e.printStackTrace();
            return "{\"result\":\"failure\"}";
        }

    }

    @RequestMapping(value = "/update_info", method = RequestMethod.POST)
    public String updateEmployee(HttpServletRequest request, HttpServletResponse response){
        try{
            BufferedReader in = request.getReader();

            StringBuilder body = new StringBuilder();

            String line;

            while(null != (line = in.readLine())){
                body.append(line);
            }

            JSONObject obj = JSON.parseObject(body.toString());

            String id = obj.getString("id");

            String attribute = obj.getString("attribute");

            String val = obj.getString("val");
            boolean res;

            if(0 == attribute.compareTo("Salary")){
                res = this.humanManageService.changeAttribute(id, attribute, Float.parseFloat(val));
            }else{
                res = this.humanManageService.changeAttribute(id, attribute, val);
            }

            return res? "true" : "false";

        }catch(Exception e){
            return "false";
        }

    }

    @RequestMapping(value = "/list_em", method = RequestMethod.GET)
    public String listAll(){
        return humanManageService.listAll();
    }

    @RequestMapping(value = "/remove_em/{id}", method = RequestMethod.GET)
    public String removeEmployee(@PathVariable String id){
        if(this.humanManageService.removeEmployee(id)){
            return "true";
        }else{
            return "false";
        }
    }

}
