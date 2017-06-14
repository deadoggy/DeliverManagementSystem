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
import java.util.*;

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

    @Autowired
    StoragePositionreRepository storagePositionreRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionRepository positionRepository;

    private String[]  nameRepo = {"李明", "王明", "王佳琦", "李普凡", "任浩然", "杜翼超"};

    private String[]  cityRepo = {"上海", "北京", "杭州", "苏州", "无锡"};

    private String[]  provinceRepo = {"上海", "浙江", "江苏", "广东"};

    private String[]  addressRepo = {"陕西路4800", "北京路4800", "建国路4800", "新华路4800"};

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


    Set<Double> repo = new HashSet<>();

    boolean dupl(Double d){
        Iterator<Double> itr = repo.iterator();
        while(itr.hasNext()){
            Double val = itr.next();
            if(val.compareTo(d)==0){
                return true;
            }
        }
        return false;
    }

    private String randomNumber(int size){

        Double key = Math.random();

        while(dupl(key)){
            key =Math.random();
        }

        repo.add(key);


        StringBuilder packageId = new StringBuilder(Integer.toString((int)Math.ceil(key * Math.pow(10, size))));

        if(size > packageId.length()){
            for(int j=0;j<size-packageId.length(); j++){
                packageId.append(j);
            }
        }else{
            packageId.delete(0, packageId.length() - size);
        }

        return packageId.toString();
    }



    @Test
    public void insertEmployee(){

        this.humanManageService
                .addNewEmployee("1452716", "张尹嘉", (int)(Math.random()*100), true,
                        (float)Math.random()*10000, randomNumber(13),"100","123456");

        Employee em = this.employeeRepository.findByMEmployeeId("1452716");
        List<Position> position = new ArrayList<>();

        Position manager = positionRepository.findByMId(100);
        position.add(manager);
        em.setmPosition(position);

        employeeRepository.save(em);

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

        DeliverCompany com = this.deliverCompanyRepository.findByMCompanyId("200");
        List<StoragePosition> posList = this.storagePositionreRepository.findAll();
        Calendar dateCal = Calendar.getInstance();
        dateCal.set(2015,10,1);


        for(int i=0; i< 50; i++){


            Calendar takenTime = Calendar.getInstance();
            takenTime.set(2017,11,1,12,0);

            StringBuilder packageId = new StringBuilder(Integer.toString((int)Math.ceil(Math.random() * 1000000)));

            if(7 > packageId.length()){
                for(int j=0;j<7-packageId.length(); j++){
                    packageId.append(j);
                }
            }else{
                packageId.delete(0, packageId.length()-7);
            }

            Package p = new Package();
            p.setmPackageId(packageId.toString());
            p.setmReceiveTime(new Timestamp(dateCal.getTime().getTime()));
            p.setmProxyChargeFee(0.0);
            p.setmTaken(true);
            p.setmTakenTime(new Timestamp(takenTime.getTimeInMillis()));
            p.setmReceiverName(this.nameRepo[i%6]);
            p.setmReceiverTele(randomNumber(13));
            p.setmCupOrShelf(i/2==0?Constant.POSITION_IN_SHELF:Constant.POSITION_IN_CUPBOARD);
            p.setmCompany(com);
            p.setmPosition(posList.get(i%posList.size()));
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

        DeliverCompany com = this.deliverCompanyRepository.findByMCompanyId("200");

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

    @Test
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

        for(int i=0; i<30; i++){

            Package p = pkg_list.get(i+1);

            if(!p.ismTaken()){
                continue;
            }

            SendingRecord sr = new SendingRecord();
            sr.setmSending_record_id(randomNumber(7));
            sr.setmPackage(p);
            sr.setmSenderName(nameRepo[i%6]);
            sr.setmSenderTele(randomNumber(13));
            sr.setmSenderAddress(addressRepo[(i+1)%4]);
            sr.setmSenderCity(cityRepo[(i+1)%5]);
            sr.setmSenderProvince(provinceRepo[(i+1)%4]);
            sr.setmReceiverName(p.getmReceiverName());
            sr.setmReceiverTele(p.getmReceiverTele());
            sr.setmReceiverAddress(addressRepo[i%4]);
            sr.setmReceiverCity(cityRepo[i%5]);
            sr.setmReceiverProvince(provinceRepo[i%4]);
            sr.setmWeight(10.87);
            sr.setmCompany(com);
            sr.setmSendTime(p.getmReceiveTime());
            this.sendingRecordRepository.saveAndFlush(sr);
        }


    }

    @Test
    public void insert(){
        //insertPoint();

//        insertCompany();
//
        insertEmployee();
//
        insertPackage();
//
        insertDayForm();
//
        insertProxyChargeRecord();

        insertSendRec();
    }

}
