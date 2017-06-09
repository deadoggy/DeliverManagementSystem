package com.deliver.repository;

import com.deliver.dao.DayFormRepository;
import com.deliver.dao.DeliverCompanyRepository;
import com.deliver.model.DayForm;
import com.deliver.model.DeliverCompany;
import com.deliver.model.Package;
import com.deliver.service.DeliverCompanyService;
import com.deliver.service.FTPService;
import com.deliver.service.FormService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.security.MessageDigest;
import java.util.Calendar;
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

    @Autowired
    FormService formService;

    @Autowired
    DeliverCompanyService deliverCompanyService;

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


    @Test
    public void md5Test(){
        String res = Hash("ksfff");
    }


    private String Hash(String name){
        String salt = new Date().toString();
        MessageDigest md;
        String numt = "1234567890";
        try{
            md = MessageDigest.getInstance("MD5");
            String context = name + salt;
            md.update(context.getBytes());
            byte digest[] = md.digest();
            StringBuilder retStr = new StringBuilder();

            for(int i=0; i<4; i++){
                int index = digest[i] < 0 ? digest[i] + 128 : digest[i];
                retStr.append(numt.charAt(index%10));
            }
            return retStr.toString();

        }catch(Exception e){
            return  "invalid";
        }

    }

    @Test
    public void takenSumTest(){
        DeliverCompany deliverCompany=deliverCompanyService.findDeliverCompanyById("0000");

        Calendar beg = Calendar.getInstance();
        beg.set(2014,Calendar.NOVEMBER,12);
        Calendar end = Calendar.getInstance();
        end.set(2015,Calendar.AUGUST,01);

        String res = formService.dispatcher("Spoon", beg, end, "send_rec");

        System.out.print(res);
    }

}
