package com.deliver.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.DayFormRepository;
import com.deliver.dao.HourFormRepository;
import com.deliver.dao.MonthFormRepository;
import com.deliver.dao.YearFormRepository;
import com.deliver.model.DeliverCompany;
import com.deliver.model.HourForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by deadoggy on 17-5-4.
 * 报表系统的Service类
 * 返回Json字符串
 */


@Service
public class FormService {

    @Autowired
    private HourFormRepository hourFormRepository;

    @Autowired
    private DayFormRepository dayFormRepository;

    @Autowired
    private MonthFormRepository monthFormRepository;

    @Autowired
    private YearFormRepository yearFormRepository;


    /*Calender -> String*/
    private static String CalendarToString(Calendar cal){
        StringBuilder retStr = new StringBuilder();
        retStr.append(cal.get(Calendar.YEAR))
                .append("-")
                .append(cal.get(Calendar.MONTH))
                .append("-")
                .append(cal.get(Calendar.DAY_OF_MONTH))
                .append("-")
                .append(cal.get(Calendar.HOUR));
        return retStr.toString();

    }

    /*获取一天之内各个小时的快递取货数量*/
    public String getTakenSumByHourInDay(Integer year, Integer month, Integer day, DeliverCompany company){
        try{
            if(null == year || null == month || null == day){
                throw new Exception("日期不合法");
            }
            //添加年月日
            JSONObject retJsonObj = new JSONObject();
            retJsonObj.put("year", year);
            retJsonObj.put("month", month);
            retJsonObj.put("day", day);

            //找出一天内的数据
            List<Integer> originData = this.hourFormRepository.findMSumByMYearAndMMonthAndMDayAndMCompany(year, month, day, company);

            JSONArray dataArr = new JSONArray();

            dataArr.addAll(originData);

            //将数据加入retJsonObj
            retJsonObj.put("key", dataArr);

            return retJsonObj.toJSONString();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }


    }

    /*获取一天之内各个小时的快递取货数量*/
    public String getTakenSumByHourInDay(Calendar date, DeliverCompany company){
        try{
            return this.getTakenSumByHourInDay(
                    date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), company
            );
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /*获取一个时间段内的按照小时统计的函数, 包括end中小时对应的数据*/
    public String getTakenSumByHourInPeriod(Calendar beg, Calendar end, DeliverCompany company){
        try{
            //获取所有的数据
            LinkedList<Integer> list = new LinkedList<>();
            Calendar flag = (Calendar) beg.clone();
            while(flag.compareTo(end) <= 0){
                list.addAll(this.hourFormRepository.findMSumByMYearAndMMonthAndMDayAndMCompany(flag.get(Calendar.YEAR), flag.get(Calendar.MONTH), flag.get(Calendar.DAY_OF_MONTH), company));
                flag.add(Calendar.DAY_OF_MONTH,1);
            }
            //处理小时
            int begHour = beg.get(Calendar.HOUR_OF_DAY), endHour = end.get(Calendar.HOUR_OF_DAY);
            if(0 != begHour){
                for(int i=0; i<begHour;i++){
                    list.remove(0);
                }
            }
            if(23 != endHour){
                for(int i=0; i<23 - endHour; i++){
                    list.removeLast();
                }
            }
            //转换成JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("begTime", CalendarToString(beg));
            jsonObj.put("endTime", CalendarToString(end));

            JSONArray jsonArray = new JSONArray();
            jsonArray.addAll(list);

            jsonObj.put("data", jsonArray);
            return jsonObj.toJSONString();

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*获取一个月内各天的数据*/
    public String getTakenSumByDayInMonth(Integer year, Integer month, DeliverCompany company){
        try{
            //TODO
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*获取一年内各天的数据*/
    public String getTakenSumByDayInYear(Integer year, DeliverCompany company){
        try{
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /*获取一个时间段内按照天数统计的函数, 包括end中的天数*/
    public String getTakenSumByDayInPeriod(Calendar beg, Calendar end, DeliverCompany company){
        try{
            //TODO:
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*获取一年内各月的数据*/
    public String getTakenSumByMonthInYear(Integer year, DeliverCompany company){
        try{
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
    /*获取一个时间段内按照月统计的函数, 包括end中的天数*/
    public String getTakenSumByMonthInPeriod(Calendar beg, Calendar end, DeliverCompany company){
        try{
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
