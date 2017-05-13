package com.deliver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deliver.dao.ShelfRepository;
import com.deliver.model.Shelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String getAllShelf(Integer page, Integer size) {
        Pageable p = new PageRequest(page, size);
        Page<Shelf> allShelf = shelfRepository.findAll(p);
        return JSON.toJSONString(allShelf);
    }

    @Transactional
    public String getShelfById(String id) {
        Shelf shelf = shelfRepository.findByMShelfId(id);
        if (shelf == null) {
            return null;
        } else {
            return JSON.toJSONString(shelf);
        }
    }


    @Transactional
    public boolean addShelf(JSONObject jsonObject) {
        String shelfId = jsonObject.getString("shelfId");
        try {
            if (shelfRepository.findByMShelfId(shelfId) == null) {
                Shelf shelf = jsonObject.toJavaObject(Shelf.class);
                shelfRepository.saveAndFlush(shelf);
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }


}
