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


    public boolean addSendingRecord(String id,Package aPackage,String sName,String sTele,String sAddress,String sCity,
                                    String sProvince,String rName,String rTele,String rAddress,String rCity,
                                    String rProvince,double weight) {
        try {
            SendingRecord sendingRecord = new SendingRecord();
            sendingRecord.setmSending_record_id(id);
//            package这里需要确定下传入什么参数
            sendingRecord.setmPackage(aPackage);
            sendingRecord.setmSenderName(sName);
            sendingRecord.setmSenderTele(sTele);
            sendingRecord.setmSenderAddress(sAddress);
            sendingRecord.setmSenderCity(sCity);
            sendingRecord.setmSenderProvince(sProvince);
            sendingRecord.setmReceiverName(rName);
            sendingRecord.setmReceiverTele(rTele);
            sendingRecord.setmReceiverAddress(rAddress);
            sendingRecord.setmReceiverCity(rCity);
            sendingRecord.setmReceiverProvince(rProvince);
            sendingRecord.setmWeight(weight);
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
