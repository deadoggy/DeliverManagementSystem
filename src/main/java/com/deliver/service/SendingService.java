package com.deliver.service;

/**
 * Created by 91574 on 2017/5/7.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.SendingRecordRepository;
import com.deliver.model.DeliverCompany;
import com.deliver.model.Package;
import com.deliver.model.SendingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendingService {
    @Autowired
    private SendingRecordRepository sendingRecordRepository;

    @Transactional
    public JSONObject addSendingRecord(JSONObject jsonObj) {
        try {
            String id=jsonObj.getString("sendingRecordId");
            if(sendingRecordRepository.findBySendingRecord(id)!=null){
                throw new Exception("已经存在此记录");
            }
            SendingRecord sendingRecord=JSON.parseObject(jsonObj.toString(),SendingRecord.class);
            sendingRecordRepository.saveAndFlush(sendingRecord);
            JSONObject retJsonObj=new JSONObject();
            retJsonObj.put("state","success");
            return retJsonObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Transactional
    public String getSendingRecordById(String sendingRecordId){
        try{
            SendingRecord sendingRecord=sendingRecordRepository.findBySendingRecord(sendingRecordId);
            return JSON.toJSONString(sendingRecord);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
