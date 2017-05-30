package com.deliver.service;

import com.alibaba.fastjson.JSONObject;
import com.deliver.model.Package;
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
        boolean flag=packageService.addPackage("103","顺风","zhang","123465789"
                ,false,1);
        assertTrue(flag);
    }
}
