package com.deliver.repository;

import com.deliver.dao.DayFormRepository;
import com.deliver.dao.DeliverCompanyRepository;
import com.deliver.model.DayForm;
import com.deliver.model.DeliverCompany;
import com.deliver.model.Package;
import com.deliver.service.FTPService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.lang.System.out;

/**
 * Created by deadoggy on 17-6-3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration

public class PersistenceTest {


    @Autowired
    DeliverCompanyRepository deliverCompanyRepository;

    @Autowired
    DayFormRepository dayFormRepository;

    @Test
    public void cascadeTest(){
        DeliverCompany com = deliverCompanyRepository.findByMId(2);
        Set<Package> list = com.getmPackage();

        for(Package p : list){
            String id = p.getmPackageId();
            out.print(p.getmId());
        }

        List<DayForm> dayFormList = this.dayFormRepository.findAll();

        out.print("done");

    }

    @Test
    public void ftpTest(){
        FTPService ftp = FTPService.getInstantce();
        try{

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
