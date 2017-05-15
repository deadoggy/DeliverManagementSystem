package com.deliver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.DeliverCompanyRepository;
import com.deliver.model.DeliverCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/14.
 */

@Service
public class DeliverCompanyService {
    @Autowired
    private DeliverCompanyRepository deliverCompanyRepository;

    @Transactional
    public boolean addDeliverCompany(JSONObject jsonObject){
        try{
            String id=jsonObject.getString("mCompanyId");
            if(deliverCompanyRepository.findByMCompanyId(id)!=null){
                throw new Exception("已经存在此记录");
            }
            DeliverCompany deliverCompany=JSON.parseObject(jsonObject.toString(),DeliverCompany.class);
            deliverCompanyRepository.saveAndFlush(deliverCompany);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public DeliverCompany findDeliverCompanyById(String id){
        return deliverCompanyRepository.findByMCompanyId(id);
    }

}


