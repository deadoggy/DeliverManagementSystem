package com.deliver.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.constant.Constant;
import com.deliver.dao.*;
import com.deliver.model.DeliverCompany;
import com.deliver.model.Package;
import com.deliver.model.ProxyChargeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by deadoggy on 17-5-4.
 * 报表系统的Service类
 * 返回Json字符串
 */

//taken_sum: 取件总量
//post_fee: 邮费统计
//package_info:包裹信息
//send_rec: 寄件记录
@Service
public class FormService {

    @Autowired
    private HourFormRepository hourFormRepository;

    @Autowired
    private DayFormRepository dayFormRepository;

    @Autowired
    private MonthFormRepository monthFormRepository;

    @Autowired
    private DeliverCompanyRepository deliverCompanyRepository;

    @Autowired
    private ProxyChargeRecordRepository proxyChargeRecordRepository;

    @Autowired
    private PackageRepository packageRepository;

    private static String opt[] = {"taken_sum", "post_fee", "package_info", "send_rec"};

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat fileFormater = new SimpleDateFormat("yyyy_MM_dd");



    /*Calender -> String*/
    private static String CalendarToString(Calendar cal){
        StringBuilder retStr = new StringBuilder();
        retStr.append(cal.get(Calendar.YEAR))
                .append("/")
                .append(cal.get(Calendar.MONTH))
                .append("/")
                .append(cal.get(Calendar.DAY_OF_MONTH))
                .append("/")
                .append(cal.get(Calendar.HOUR));
        return retStr.toString();

    }

    /*获取一个时间段内按照天数统计的函数, 包括end中的天数*/
    public String getTakenSumByDayInPeriod(Calendar beg, Calendar end, DeliverCompany company){
        try{
            List<Integer> allData;

            if(null == company){
                allData = this.dayFormRepository.findInPeriod(beg.getTime(), end.getTime());
            }else{
                allData = this.dayFormRepository.findInPeriodOfCompany(company, beg.getTime(), end.getTime());
            }

            //JSON

            JSONObject retJsonObject = new JSONObject();
            retJsonObject.put("result", "success");
            retJsonObject.put("beg", CalendarToString(beg));
            retJsonObject.put("end", CalendarToString(end));

            JSONArray dataArr = new JSONArray();
            dataArr.addAll(allData);
            retJsonObject.put("data", dataArr);

            return retJsonObject.toJSONString();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*获取一个时间段内按照天数统计的代收邮费的数据*/
    public String getProxyChargeInPeriod(Calendar beg, Calendar end, DeliverCompany company){
        try{
            List<ProxyChargeRecord> data;
            if(null == company){
                data = this.proxyChargeRecordRepository.getByDate(beg.getTime(), end.getTime());
            }else{
                data = this.proxyChargeRecordRepository.getByDateAndCompany(beg.getTime(), end.getTime(), company);
            }

            JSONArray dataArr = new JSONArray();


            for(ProxyChargeRecord item : data){
                JSONArray record = new JSONArray();
                record.add(item.getmPackage().getmPackageId());
                record.add(formater.format(item.getmDate()));
                record.add(item.getmFee());
                dataArr.add(record);
            }

            JSONObject ret = new JSONObject();
            ret.put("result", "success");
            ret.put("beg", this.formater.format(beg.getTime()));
            ret.put("end", this.formater.format(end.getTime()));
            ret.put("data", dataArr);

            return ret.toJSONString();

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*获取一个时间段内某公司的所有的包裹信息，返回服务器上一个文件的位置*/
    public String getPackageInfo(Calendar beg, Calendar end, DeliverCompany company){
        try{
            StringBuilder fileNameBuilder = new StringBuilder();
            fileNameBuilder.append(this.fileFormater.format(beg.getTime()))
                    .append("_")
                    .append(this.fileFormater.format(end.getTime()));
            if(null != company){
                fileNameBuilder.append("_")
                .append(company.getmName());
            }
            fileNameBuilder.append(".csv");

            List<Package> list;
            list =
                    company != null ?
                    this.packageRepository.getByCompanyAndReceTime(company, new Timestamp(beg.getTimeInMillis()), new Timestamp(end.getTimeInMillis()))
                    :this.packageRepository.getByReceTime(new Timestamp(beg.getTimeInMillis()), new Timestamp(end.getTimeInMillis()));

            File out = new File("File/" + fileNameBuilder.toString());

            FileWriter of = new FileWriter(out);
            of.write("Id, rece_time, receiver, rece_tele, company, taken, taken_time, in_cup, pos_1, pos_layer, pos_col");
            for(Package p : list){
                StringBuilder item = new StringBuilder();
                item.append(p.getmPackageId()).append(",")
                        .append(formater.format(p.getmReceiveTime())).append(",")
                        .append(p.getmReceiverName()).append(",")
                        .append(p.getmReceiverTele()).append(",")
                        .append(p.getmCompany()).append(",")
                        .append(p.ismTaken()).append(",")
                        .append(formater.format(p.getmTakenTime())).append(",");

                if(p.ismCupOrShelf() == Constant.POSITION_IN_CUPBOARD){
                    item.append("smart_board").append(",")
                    .append(p.getmPosition().getmCup().getmCupboardId()).append(",");
                }else{
                    item.append("shelf").append(",")
                    .append(p.getmPosition().getmShelf().getmShelfId()).append(",");
                }
                item.append(p.getmPosition().getmLayer()).append(",");
                item.append(p.getmPosition().getmColumn()).append("\n");
                of.write(item.toString());
            }

            of.flush();
            of.close();

            FTPService.getInstantce().upload(fileNameBuilder.toString(), fileNameBuilder.toString());

            return "{\"result\": \"success\", \"url\":\"/File/" + fileNameBuilder.toString() + "\"}";

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String dispatcher(String company, Calendar beg, Calendar end, String opt){

        String ret = null;
        int cmp=0;
        DeliverCompany comObj = null;
        if(null != company){
            comObj = this.deliverCompanyRepository.findByMName(company);
            if(null == comObj){
                return "{\"result\":\"failure\", \"reason\": \"no such company\"}";
            }
        }


        for(int i=0; i<FormService.opt.length; i++){
            if(0 == opt.compareTo(FormService.opt[i])){
                cmp = (cmp + 1) << i;
                break;
            }
        }

        switch(cmp){
            case 0 : return "{\"result\": \"failure\", \"reason\": \"no such opt\"}";
            case 1 : ret = this.getTakenSumByDayInPeriod(beg, end,comObj); //taken_sum
                break;
            case 2 : ret = this.getProxyChargeInPeriod(beg, end, comObj); //post_fee
                break;
            case 4 : ret = this.getPackageInfo(beg, end, comObj);
                break;
            case 8 : //TODO
                break;
        }

        return "test";
    }

}
