package com.deliver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.model.Package;
import com.deliver.model.SendingRecord;
import com.deliver.service.PackageService;
import com.deliver.service.SendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by 91574 on 2017/5/29.
 */
@RestController
public class SendingRecordController {
    @Autowired
    private SendingService sendingService;
    @Autowired
    private PackageService packageService;

    @RequestMapping(value = "/sendrecord/{id}", method = RequestMethod.GET)
    public String getSendRecordById(@PathVariable String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            List<SendingRecord> sendingRecordList = sendingService.getSendingRecords(id);
            if (sendingRecordList == null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "找不到对应id的发件记录");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                JSONArray jsonArray = new JSONArray();
                for (SendingRecord sendingRecord:sendingRecordList) {
                    JSONObject sendingRecordObject = new JSONObject();
                    sendingRecordObject.put("id", sendingRecord.getmSending_record_id());
                    sendingRecordObject.put("sName",sendingRecord.getmSenderName());
                    sendingRecordObject.put("sAddress",sendingRecord.getmSenderAddress());
                    sendingRecordObject.put("sCity",sendingRecord.getmSenderCity());
                    sendingRecordObject.put("sProvince",sendingRecord.getmSenderProvince());
                    sendingRecordObject.put("sTele",sendingRecord.getmSenderTele());
                    sendingRecordObject.put("sTime",sendingRecord.getmSendTime());
                    sendingRecordObject.put("rName",sendingRecord.getmReceiverName());
                    sendingRecordObject.put("rAddress",sendingRecord.getmReceiverAddress());
                    sendingRecordObject.put("rCity",sendingRecord.getmReceiverCity());
                    sendingRecordObject.put("rProvince",sendingRecord.getmReceiverProvince());
                    sendingRecordObject.put("rTele",sendingRecord.getmReceiverTele());
                    sendingRecordObject.put("weight",sendingRecord.getmWeight());
                    jsonArray.add(sendingRecordObject);
                }
                jsonObject.put("sendingRecordList", jsonArray);
                return jsonObject.toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/sendrecord", method = RequestMethod.POST)
    public String addSendRecord(HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        try {
            String id = httpServletRequest.getParameter("id");
            if (sendingService.getSendingRecordById(id) != null) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "已经存在对应id的寄件记录");
                return jsonObject.toString();
            }
            //Package aPackage = packageService.getPackage(httpServletRequest.getParameter("packageId"));
            boolean flag = sendingService.addSendingRecord(id, httpServletRequest.getParameter("sName"),
                    httpServletRequest.getParameter("sTele"), httpServletRequest.getParameter("sAdderss"),
                    httpServletRequest.getParameter("sCity"), httpServletRequest.getParameter("sProvince"),
                    httpServletRequest.getParameter("rName"), httpServletRequest.getParameter("rTele"),
                    httpServletRequest.getParameter("rAdderss"), httpServletRequest.getParameter("rCity"),
                    httpServletRequest.getParameter("rProvince"), Double.valueOf(httpServletRequest.getParameter("weight")));
            if (flag == false) {
                jsonObject.put("status", "fail");
                jsonObject.put("reason", "寄件信息不正确");
                return jsonObject.toString();
            } else {
                jsonObject.put("status", "ok");
                return jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "fail");
            jsonObject.put("reason", "请求失败");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(value = "/sendrecord",method = RequestMethod.GET)
    public String getAll(){
        try{
            List<SendingRecord> sendingRecordList=sendingService.getAll();
            if(sendingRecordList==null){
                return "{\"status\": \"fail\", \"reason\" : \"请求失败\"}";
            }else{
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("status", "ok");
                JSONArray jsonArray = new JSONArray();
                for (SendingRecord sendingRecord:sendingRecordList) {
                    JSONObject sendingRecordObject = new JSONObject();
                    sendingRecordObject.put("id", sendingRecord.getmSending_record_id());
                    sendingRecordObject.put("sName",sendingRecord.getmSenderName());
                    sendingRecordObject.put("sAddress",sendingRecord.getmSenderAddress());
                    sendingRecordObject.put("sCity",sendingRecord.getmSenderCity());
                    sendingRecordObject.put("sProvince",sendingRecord.getmSenderProvince());
                    sendingRecordObject.put("sTele",sendingRecord.getmSenderTele());
                    sendingRecordObject.put("sTime",sendingRecord.getmSendTime());
                    sendingRecordObject.put("rName",sendingRecord.getmReceiverName());
                    sendingRecordObject.put("rAddress",sendingRecord.getmReceiverAddress());
                    sendingRecordObject.put("rCity",sendingRecord.getmReceiverCity());
                    sendingRecordObject.put("rProvince",sendingRecord.getmReceiverProvince());
                    sendingRecordObject.put("rTele",sendingRecord.getmReceiverTele());
                    sendingRecordObject.put("weight",sendingRecord.getmWeight());
                    jsonArray.add(sendingRecordObject);
                }
                jsonObject.put("sendingRecordList", jsonArray);
                return jsonObject.toJSONString();
            }
        }catch (Exception e){
            e.printStackTrace();
            return "{\"status\": \"fail\", \"reason\" : \"请求失败\"}";
        }
    }
}
