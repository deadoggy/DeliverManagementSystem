package com.deliver.service;

import com.alibaba.fastjson.JSONObject;
import com.deliver.model.DeliverCompany;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by 91574 on 2017/5/14.
 */
public class DeliverCompanyServiceTest extends BaseServiceTest {
    @Autowired
    private DeliverCompanyService deliverCompanyService;

    @Test
    public void add(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mCompanyId","1");
        jsonObject.put("mName","顺风");
        boolean add=deliverCompanyService.addDeliverCompany(jsonObject);
        assertTrue(add);
    }

    @Test
    public void get(){
        DeliverCompany deliverCompany=deliverCompanyService.findDeliverCompanyById("111");
        System.out.println(deliverCompany.getmName());
    }

}
