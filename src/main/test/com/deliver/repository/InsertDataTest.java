package com.deliver.repository;

import com.deliver.dao.PointRepository;
import com.deliver.model.Employee;
import com.deliver.model.Point;
import com.deliver.service.HumanManageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void insertEmployee(){
        this.humanManageService.addNewEmployee("1452716", "zhang", 22,200,"0","123456");
    }

    @Test
    public void insertPoint(){
        Point pt = new Point("0", "同济大学嘉定校区","上海", "上海", "嘉定区", "曹安公路", "4800");
        this.pointRepository.save(pt);
    }

}
