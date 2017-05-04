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
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

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


    /*获取一天之内各个小时的快递取货数量*/
    public String getTakenSumByHourInDay(Integer year, Integer month, Integer day, DeliverCompany company) throws Exception{
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
            Page<HourForm> originData = this.hourFormRepository.findBymYearAndmMonthAndmDayAndmCompany(year, month, day, company);

            JSONArray dataArr = new JSONArray();

            Iterator<HourForm> itr = originData.iterator();

            while(itr.hasNext()){
                HourForm item = itr.next();
            }

            //将数据加入retJsonObj
            retJsonObj.put("key", dataArr);

            return retJsonObj.toJSONString();
        }catch(Exception e){
            throw e;
        }


    }

    public String getTakenSumByHourInDay(Calendar date, DeliverCompany company) throws Exception{
        try{
            return this.getTakenSumByHourInDay(
                    date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), company
            );
        }catch (Exception e){
            throw e;
        }

    }

//    /*获取几天内的按照小时统计的函数*/
//    public String getTakenSumByHourInPeriod(Calendar beg, Calendar end, DeliverCompany company){
//
//    }
}
