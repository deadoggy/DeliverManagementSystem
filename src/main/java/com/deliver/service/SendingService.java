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
import javafx.scene.control.DateCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SendingService {
    @Autowired
    private SendingRecordRepository sendingRecordRepository;


    public boolean addSendingRecord(JSONObject jsonObject) {
        try {
            String id = jsonObject.getString("mSendingRecordId");
            if (sendingRecordRepository.findByMSendingRecordId(id) != null) {
                throw new Exception("已经存在此记录");
            }
            SendingRecord sendingRecord = JSON.parseObject(jsonObject.toString(), SendingRecord.class);
            sendingRecord.setmSendTime(new Timestamp(System.currentTimeMillis()));
            sendingRecordRepository.saveAndFlush(sendingRecord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SendingRecord getSendingRecordById(String sendingRecordId) {
        return sendingRecordRepository.findByMSendingRecordId(sendingRecordId);
    }

}
