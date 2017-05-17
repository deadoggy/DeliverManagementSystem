package com.deliver.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.RefuseRepository;
import com.deliver.model.Refuse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deadoggy on 17-5-17.
 */

@Service
public class RefuseService {

    @Autowired
    private RefuseRepository refuseRepository;

    public void addRefuse(String packageId, String reason){

        Timestamp timestamp = new Timestamp(new Date().getTime());

        Refuse item = new Refuse(packageId, timestamp, reason, new java.sql.Date(new Date().getTime()));

        this.refuseRepository.save(item);
    }

    public String searchRefuse(String packageId){
        List<Refuse> list = this.refuseRepository.findByMPackageId(packageId);

        JSONObject retObj = new JSONObject();

        retObj.put("package_id", packageId);

        JSONArray arr = new JSONArray();

        Iterator<Refuse> itr = list.iterator();

        SimpleDateFormat timeFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while(itr.hasNext()){
            JSONObject item = new JSONObject();

            Refuse rec = itr.next();

            item.put("time", timeFmt.format(rec.getmTime()));
            item.put("reason", rec.getmReason());

            arr.add(item);
        }

        retObj.put("result", arr);

        return retObj.toJSONString();
    }

    public String searchRefuseByDate(Date date){
        List<Refuse> list = this.refuseRepository.findByMDate(new java.sql.Date(date.getTime()));

        JSONArray retArr = new JSONArray();


        Iterator<Refuse> itr = list.iterator();

        while(itr.hasNext()){
            Refuse rec = itr.next();
            JSONObject item = new JSONObject();
            item.put("package_id",rec.getmPackageId());
            item.put("reason", rec.getmReason());
            retArr.add(item);
        }

        return retArr.toJSONString();
    }

    public String searchRefuseInPeriod(Calendar beg, Calendar end){

        List<Refuse> list = this.refuseRepository.findByMDate(new java.sql.Date(beg.getTimeInMillis()));

        Calendar p = (Calendar) beg.clone();
        p.add(Calendar.DAY_OF_MONTH, 1);
        while(p.compareTo(end)<=0){
            list.addAll(this.refuseRepository.findByMDate(new java.sql.Date(p.getTimeInMillis())));
        }

        JSONArray retArr = new JSONArray();

        Iterator<Refuse> itr = list.iterator();

        SimpleDateFormat timeFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while(itr.hasNext()){
            Refuse rec = itr.next();
            JSONObject item = new JSONObject();
            item.put("package_id", rec.getmPackageId());
            item.put("reason", rec.getmReason());
            item.put("time", timeFmt.format(rec.getmTime()));

            retArr.add(item);
        }

        return retArr.toJSONString();

    }
}
