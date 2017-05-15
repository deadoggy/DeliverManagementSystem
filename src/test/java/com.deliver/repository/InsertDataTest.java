package com.deliver.repository;

import com.deliver.constant.Constant;
import com.deliver.dao.DeliverCompanyRepository;
import com.deliver.dao.PackageRepository;
import com.deliver.dao.PointRepository;
import com.deliver.model.DeliverCompany;
import com.deliver.model.Employee;
import com.deliver.model.Package;
import com.deliver.model.Point;
import com.deliver.service.HumanManageService;
import com.deliver.service.ShortMesgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by deadoggy on 17-5-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class InsertDataTest {

    @Autowired
    HumanManageService humanManageService;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    DeliverCompanyRepository deliverCompanyRepository;


    @Test
    public void insertEmployee(){
        this.humanManageService.addNewEmployee("1452716", "zhang", 22,200,"0","123456");
    }

    @Test
    public void insertCompany(){
        DeliverCompany company = new DeliverCompany();
        company.setmCompanyId("0000");
        company.setmName("Spoon");
        this.deliverCompanyRepository.save(company);
    }

    @Test
    public void insertPackage(){
        Package p = new Package();
        p.setmPackageId("00000000001");
        p.setmReceiveTime(new Timestamp(new Date().getTime()));
        p.setmProxyChargeFee(0.0d);
        p.setmTaken(false);
        p.setmReceiverName("老逼威");
        p.setmReceiverTele("18221238151");
        p.setmCupOrShelf(Constant.POSITION_IN_CUPBOARD);

        this.packageRepository.save(p);
    }

    @Test
    public void insertPoint(){
        Point pt = new Point("0", "同济大学嘉定校区","上海", "上海", "嘉定区", "曹安公路", "4800");
        this.pointRepository.save(pt);
    }



}
