package com.deliver.service;

import com.deliver.dao.PointRepository;
import com.deliver.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 91574 on 2017/6/8.
 */
@Service
public class BusinessPointService {
    @Autowired
    private PointRepository pointRepository;

    public Point getPoint(String id){
        return pointRepository.findByMBusinessPointId(id);
    }
}
