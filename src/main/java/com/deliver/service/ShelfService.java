package com.deliver.service;

import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 91574 on 2017/5/9.
 */
@Service
public class ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    @Transactional
    public JSONObject getAllShelf(){
        JSONObject retJsonObj=new JSONObject();

    }
}
