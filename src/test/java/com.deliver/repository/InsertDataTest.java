package com.deliver.repository;

import com.deliver.constant.Constant;
import com.deliver.dao.*;
import com.deliver.model.*;
import com.deliver.model.Package;
import com.deliver.service.HumanManageService;
import com.deliver.service.ShortMesgService;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by deadoggy on 17-5-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class InsertDataTest {

    @Autowired
    HumanManageService humanManageService;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    DeliverCompanyRepository deliverCompanyRepository;

    @Autowired
    DayFormRepository dayFormRepository;

    @Autowired
    ProxyChargeRecordRepository proxyChargeRecordRepository;

    @Autowired
    SendingRecordRepository sendingRecordRepository;

    public static String randomString(){
        long randSalt = (long)Math.floor(Math.random() * 1000000000);
        Calendar seed = Calendar.getInstance();
        seed.setTimeInMillis(new Date().getTime() + randSalt);
        String seedStr = Long.toString(seed.getTimeInMillis());
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(seedStr.getBytes());
            byte[] digest = md.digest();
            for(int i =0; i<digest.length; i++){
                digest[i] = digest[i]<0?(byte)(digest[i]+128) : digest[i];
                digest[i] = (byte)(digest[i]%26 + 65);

            }
            String ret = new String(digest);
            return ret;
        }catch(Exception e){
            return null;
        }

    }


    @Test
    public void insertEmployee(){

        this.humanManageService
                .addNewEmployee("1452716", randomString(), (int)(Math.random()*100), true,
                        (float)Math.random()*10000, randomString(),"0","123456");
//        for(int i=0; i<50; i++){
//            this.humanManageService
//                    .addNewEmployee(randomString(), randomString(), (int)(Math.random()*100), true,
//                            (float)Math.random()*10000, randomString(),"0",randomString());
//        }
    }

    //@Test
    public void insertCompany(){
        DeliverCompany company = new DeliverCompany();
        company.setmCompanyId("0000");
        company.setmName("Spoon");
        this.deliverCompanyRepository.save(company);
    }

    @Test
    public void insertPackage(){

        DeliverCompany com = this.deliverCompanyRepository.findByMCompanyId("0000");
        Calendar dateCal = Calendar.getInstance();
        dateCal.set(2014,10,1);


        for(int i=0; i< 500; i++){
            Package p = new Package();
            p.setmPackageId(randomString());
            p.setmReceiveTime(new Timestamp(dateCal.getTime().getTime()));
            p.setmProxyChargeFee(0.0);
            p.setmTaken(false);
            p.setmReceiverName(randomString());
            p.setmReceiverTele(randomString());
            p.setmCupOrShelf(i/2==0?Constant.POSITION_IN_SHELF:Constant.POSITION_IN_CUPBOARD);
            p.setmCompany(com);
            this.packageRepository.save(p);
            dateCal.add(Calendar.DAY_OF_MONTH,1);
        }

    }

    //@Test
    public void insertPoint(){
        Point pt = new Point("0", "同济大学嘉定校区","上海", "上海", "嘉定区", "曹安公路", "4800");
        this.pointRepository.save(pt);
    }

    @Test
    public void insertDayForm(){

        DeliverCompany com = this.deliverCompanyRepository.findByMCompanyId("0000");

        Calendar dateCal = Calendar.getInstance();
        dateCal.set(2014,10,1);
        for(int i=0; i<600;i++){
            DayForm item = new DayForm();
            item.setmDate(dateCal.getTime());
            item.setmSum((int)(1000* Math.random()));
            item.setmCompany(com);
            this.dayFormRepository.save(item);
            dateCal.add(Calendar.DAY_OF_MONTH,1);
        }
    }

    //@Test
    public void insertProxyChargeRecord(){
        try{
            List<Package> pl = this.packageRepository.findAll();
            for(Package p : pl){
                ProxyChargeRecord pcr = new ProxyChargeRecord();
                pcr.setmPackage(p);
                pcr.setmDate(new Date(p.getmReceiveTime().getTime()));
                pcr.setmFee(p.getmProxyChargeFee());
                pcr.setmSendorReceive(Constant.PROXY_CHARGE_RECE);
                this.proxyChargeRecordRepository.save(pcr);
            }
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void insertSendRec(){

        List<Package> pkg_list = this.packageRepository.findAll();

        DeliverCompany com = this.deliverCompanyRepository.findAll().get(0);

        for(int i=0; i<100; i++){

            Package p = pkg_list.get(i);

            SendingRecord sr = new SendingRecord();

            sr.setmSending_record_id(this.randomString());
            sr.setmPackage(p);
            sr.setmSenderName(randomString());
            sr.setmSenderName(randomString());
            sr.setmSenderTele(randomString());
            sr.setmSenderAddress(randomString());
            sr.setmSenderCity(randomString());
            sr.setmSenderProvince(randomString());
            sr.setmReceiverName(p.getmReceiverName());
            sr.setmReceiverTele(p.getmReceiverTele());
            sr.setmReceiverAddress(randomString());
            sr.setmReceiverCity(randomString());
            sr.setmReceiverProvince(randomString());
            sr.setmWeight(10.87);
            sr.setmCompany(com);
            sr.setmSendTime(p.getmReceiveTime());
            this.sendingRecordRepository.saveAndFlush(sr);
        }


    }

    @Test
    public void insert(){
        insertPoint();

        insertCompany();

        insertEmployee();

        insertPackage();

        insertDayForm();

        insertProxyChargeRecord();
    }

}
