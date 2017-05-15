package com.deliver.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by 91574 on 2017/5/15.
 */
public class PackageServiceTest extends BaseServiceTest{
    @Autowired
    private PackageService packageService;

    @Test
    public void add(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mPackageId","100");
        jsonObject.put("mCompany","顺风");
        jsonObject.put("mReceiverName","zhang");
        jsonObject.put("mReceiverTele","123465789");
        jsonObject.put("mCupOrShelf",false);
        jsonObject.put("storageId",1);
        boolean flag=packageService.addPackage(jsonObject);
        assertTrue(flag);
    }
}
