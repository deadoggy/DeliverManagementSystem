package com.deliver.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.AttendanceRepository;
import com.deliver.dao.EmployeeRepository;
import com.deliver.dao.PointRepository;
import com.deliver.dao.PositionRepository;
import com.deliver.model.Attendance;
import com.deliver.model.Employee;
import com.deliver.model.Point;
import com.deliver.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @Autowired
    private PositionRepository positionRepository;

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

    public boolean addNewEmployee(String employeeId, String name,int age, boolean gender, float salary, String phone, String pointId, String pwd){

        try{
            if(pointId == null || employeeId == null){
                throw new Exception("invalid point or employee");
            }

            Point point = this.pointRepository.findAll().get(0);

            if (point == null) {
                throw new Exception("invalid point id");
            }
            if(null == pwd){
                pwd = "123456";
            }

            String positionId = employeeId.substring(0,3);






            SelfBcryptEncoder encoder = new SelfBcryptEncoder();

            pwd = encoder.encipher(employeeId, pwd);

            Employee noob = new Employee(employeeId, name, age, gender, salary, phone, point, new Timestamp(new java.util.Date().getTime()), pwd);

            List<Position> posList = new ArrayList<>();

            Position position;

            if(0==positionId.compareTo("001")){
                position = this.positionRepository.findByMId(100);
            }else{
                position = this.positionRepository.findByMId(200);
            }
            posList.add(position);

            noob.setmPosition(posList);

            this.employeeRepository.saveAndFlush(noob);

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



    public Class<?> getParameterType(String methodName, Class<?> targetClass){
        try{
            Method[] meths = targetClass.getDeclaredMethods();
            for(Method m : meths){
                if(0 == m.getName().compareTo(methodName)){
                    Class<?>[] paraList =  m.getParameterTypes();
                    if(null != paraList){
                        return paraList[0];
                    }else{
                        return null;
                    }
                }
            }

            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*
    *attribute:
    *   Name
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

            Field[] allFiled = employee.getClass().getDeclaredFields();
            String fieldName = "m" + attribute;
            for(Field field : allFiled){
                if(0 == field.getName().compareTo(fieldName)){

                    String methodName = "set" + fieldName;
                    Class<?> paraType = this.getParameterType(methodName, employee.getClass());
                    if(null != paraType){
                        Method method = employee.getClass().getMethod(methodName, paraType);
                        method.invoke(employee, newValue);
                        employeeRepository.saveAndFlush(employee);
                        return true;
                    }
                }
            }
            throw new Exception("no such attribute");

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String findByEmployeeId(String id){
        try{
            JSONObject ret = new JSONObject();
            Employee employee = this.employeeRepository.findByMEmployeeId(id);

            ret.put("result", "success");
            ret.put("id", employee.getmEmployeeId());
            ret.put("name", employee.getmName());
            ret.put("gender", employee.ismGender()? "male" : "female");
            ret.put("age", employee.getmAge());
            ret.put("phone", employee.getmPhone() != null ? employee.getmPhone() : "none");
            ret.put("salary", employee.getmSalary());
            ret.put("point", employee.getmPoint().getmName());
            List<Position> pos = employee.getmPosition();
            if(0 == pos.size()){
                ret.put("pos", "");
            }else{
                ret.put("pos", employee.getmPosition().get(0).getmName());
            }

            return ret.toJSONString();

        }catch(Exception e){
            return "{\"result\": \"fail\", \"reason\" : \"no such id\"}";
        }
    }

    public String findByEmployName(String name){
        try{
            JSONObject ret = new JSONObject();

            List<Employee> emList = this.employeeRepository.findByMName(name);

            ret.put("result", "success");
            ret.put("data", this.generateList(emList));
            return ret.toJSONString();

        }catch(Exception e){
            return "{\"result\": \"fail\", \"reason\" : \"no such name\"}";
        }
    }

    public String listAll(){
        try{
            JSONObject retObj = new JSONObject();
            List<Employee> list = this.employeeRepository.findAll();


            retObj.put("result", "success");
            retObj.put("data", this.generateList(list));

            return retObj.toJSONString();

        }catch(Exception e){
            return "{\"result\": \"failure\"}";
        }
    }

    private JSONArray generateList(List<Employee> list){
        JSONArray dataArr = new JSONArray();

        for(Employee employee : list){
            JSONObject item = new JSONObject();
            item.put("id", employee.getmEmployeeId());
            item.put("name", employee.getmName());
            item.put("gender", employee.ismGender()? "male" : "female");
            item.put("age", employee.getmAge());
            item.put("phone", employee.getmPhone() != null ? employee.getmPhone() : "none");
            item.put("salary", employee.getmSalary());
            item.put("point", employee.getmPoint().getmName());

            List<Position> pos = employee.getmPosition();
            if(0 == pos.size()){
                item.put("pos", "");
            }else{
                item.put("pos", employee.getmPosition().get(0).getmName());
            }

            dataArr.add(item);
        }

        return dataArr;
    }

}
