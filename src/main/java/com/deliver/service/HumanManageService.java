package com.deliver.service;

import com.deliver.dao.AttendanceRepository;
import com.deliver.dao.EmployeeRepository;
import com.deliver.dao.PointRepository;
import com.deliver.model.Attendance;
import com.deliver.model.Employee;
import com.deliver.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by deadoggy on 17-5-9.
 */

@Service
public class HumanManageService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public boolean checkin(String employeeId){
        try{
            Calendar today = Calendar.getInstance();

            Date date = new Date(today.getTimeInMillis());

            Employee employee = this.employeeRepository.findByMEmployeeId(employeeId);

            if(null == employee){
                throw new Exception("No such a employee");
            }

            Attendance record = this.attendanceRepository.findByMEmployeeAndMDate(employee, date);

            if(null == record){ // 如果没有这条记录, 就新建一个
                record = new Attendance(employee, date, new Timestamp(new java.util.Date().getTime()), null, employee.getmPoint());

                attendanceRepository.save(record);

                return true;
            }else{ // 如果有这条记录, 就检查是否两个时间都有,如果有就 throw Exception

                if(null == record.getmEndTime()){
                    throw new Exception("already have two records");
                }else{
                    record.setmEndTime(new Timestamp(new java.util.Date().getTime()));
                    attendanceRepository.save(record);
                    return true;
                }

            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addNewEmployee(String employeeId, String name,int age, float salary, String pointId, String pwd){

        try{
            if(pointId == null || employeeId == null){
                throw new Exception("invalid point or employee");
            }

            Point point = this.pointRepository.findByMBusinessPointId(pointId);

            if (point == null) {
                throw new Exception("invalid point id");
            }
            if(null == pwd){
                pwd = "123456";
            }

            SelfBcryptEncoder encoder = new SelfBcryptEncoder();

            pwd = encoder.encipher(employeeId, pwd);

            Employee noob = new Employee(employeeId, name, age, salary, point, new Timestamp(new java.util.Date().getTime()), pwd);

            this.employeeRepository.save(noob);

            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean removeEmployee(String employeeId){
        try{
            Employee employee = this.employeeRepository.findByMEmployeeId(employeeId);
            if(null == employee){
                throw new Exception("no such an employee");
            }
            this.employeeRepository.delete(employee);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /*
    *attribute:
    *   Name
    *   Age
    *   Salary
    *   Point
    *   Pwd
    * */
    public boolean changeAttribute(String employeeId, String attribute, Object newValue){
        try{
            if(null == employeeId || null == attribute || null == newValue){
                throw new Exception("invalid param");
            }

            Employee employee = this.employeeRepository.findByMEmployeeId(employeeId);

            if(null == employee){
                throw new Exception("no such an employee");
            }

            Field[] allFiled = employee.getClass().getFields();
            String fieldName = "m" + attribute;
            for(Field field : allFiled){
                if(0 == field.getName().compareTo(fieldName)){

                    String methodName = "set" + fieldName;
                    Method method = employee.getClass().getMethod(methodName);
                    method.invoke(employee, newValue);
                    return true;
                }
            }
            throw new Exception("no such attribute");

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
