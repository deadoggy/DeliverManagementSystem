package com.deliver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.SmartCupboardRepository;
import com.deliver.model.SmartCupboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 91574 on 2017/5/9.
 */
@Service
public class SmartCupboardService {
    @Autowired
    private SmartCupboardRepository smartCupboardRepository;

    @Transactional
    public String getAllSmartCupboard(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        List<SmartCupboard> allSmartCupboard = smartCupboardRepository.findAll();
        return JSON.toJSONString(allSmartCupboard);
    }

    @Transactional
    public String getSmartCupboardById(String id) {
        SmartCupboard smartCupboard = smartCupboardRepository.findByMCupboardId(id);
        if (smartCupboard == null) {
            return null;
        } else {
            return JSON.toJSONString(smartCupboard);
        }
    }

    @Transactional
    public String addSmartCupboard(JSONObject jsonObject) {
        String id = jsonObject.getString("smartCupBoardId");
        try {
            if (smartCupboardRepository.findByMCupboardId(id) == null) {
                SmartCupboard smartCupboard=jsonObject.toJavaObject(SmartCupboard.class);
                smartCupboardRepository.saveAndFlush(smartCupboard);
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("state","success");
                return jsonObject1.toString();
            }
            else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
