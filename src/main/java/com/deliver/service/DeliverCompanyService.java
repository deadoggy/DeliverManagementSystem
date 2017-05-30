package com.deliver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.DeliverCompanyRepository;
import com.deliver.model.DeliverCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 91574 on 2017/5/14.
 */

@Service
public class DeliverCompanyService {
    @Autowired
    private DeliverCompanyRepository deliverCompanyRepository;

    @Transactional
    public boolean addDeliverCompany(String id,String name){
        try{
            DeliverCompany deliverCompany=new DeliverCompany();
            deliverCompany.setmCompanyId(id);
            deliverCompany.setmName(name);
            deliverCompany.setmBills(null);
            deliverCompany.setmDayForm(null);
            deliverCompany.setmHourForm(null);
            deliverCompany.setmMonthForm(null);
            deliverCompany.setmPackage(null);
            deliverCompany.setmYearForm(null);
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

    @Transactional
    public List<DeliverCompany> findAllDeliverCompany(){
        return deliverCompanyRepository.findAll();
    }

    @Transactional
    public DeliverCompany getDeliverCompany(String name){
        return deliverCompanyRepository.findByMName(name);
    }
}


