package com.deliver.service;

import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.EmployeeRepository;
import com.deliver.dao.PointRepository;
import com.deliver.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by deadoggy on 17-5-30.
 */

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PointRepository pointRepository;




//    public String saveEmployee(String name, boolean gender, int age, String phone, double salary, String point){
//        try{
//
//        }catch(Exception e){
//
//        }
//    }

}
